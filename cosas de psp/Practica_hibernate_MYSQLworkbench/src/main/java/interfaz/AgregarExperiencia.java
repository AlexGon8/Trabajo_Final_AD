package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entidades.Investigador;
import entidades.ExperienciaLaboral;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class AgregarExperiencia extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldInstitucion;
    private JTextField textFieldPeriodo;
    private JTextField textFieldDescripcion;
    private JComboBox<Investigador> comboBoxInvestigadores;

    public AgregarExperiencia() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNewLabelInstitucion = new JLabel("Institucion");
        lblNewLabelInstitucion.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabelInstitucion.setBounds(32, 27, 152, 13);
        contentPanel.add(lblNewLabelInstitucion);

        JLabel lblNewLabelPeriodo = new JLabel("Periodo");
        lblNewLabelPeriodo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabelPeriodo.setBounds(32, 81, 152, 13);
        contentPanel.add(lblNewLabelPeriodo);

        JLabel lblNewLabelDescripcionEXPLaboral = new JLabel("Descripcion");
        lblNewLabelDescripcionEXPLaboral.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabelDescripcionEXPLaboral.setBounds(32, 125, 152, 22);
        contentPanel.add(lblNewLabelDescripcionEXPLaboral);

        JLabel lblNewLabelInvestigadorEXPlaboral = new JLabel("Investigador");
        lblNewLabelInvestigadorEXPlaboral.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabelInvestigadorEXPlaboral.setBounds(32, 179, 152, 19);
        contentPanel.add(lblNewLabelInvestigadorEXPlaboral);

        textFieldInstitucion = new JTextField();
        textFieldInstitucion.setBounds(194, 26, 209, 19);
        contentPanel.add(textFieldInstitucion);
        textFieldInstitucion.setColumns(10);

        textFieldPeriodo = new JTextField();
        textFieldPeriodo.setBounds(194, 80, 209, 19);
        contentPanel.add(textFieldPeriodo);
        textFieldPeriodo.setColumns(10);

        textFieldDescripcion = new JTextField();
        textFieldDescripcion.setBounds(194, 129, 209, 19);
        contentPanel.add(textFieldDescripcion);
        textFieldDescripcion.setColumns(10);

        comboBoxInvestigadores = new JComboBox<>();
        comboBoxInvestigadores.setBounds(194, 179, 209, 21);
        contentPanel.add(comboBoxInvestigadores);
        loadResearchersIntoComboBox();

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        {
            JButton ButtonGuardar = new JButton("Guardar");
            ButtonGuardar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    guardarExperiencia();
                }
            });
            ButtonGuardar.setActionCommand("OK");
            buttonPane.add(ButtonGuardar);
            getRootPane().setDefaultButton(ButtonGuardar);
        }
        {
            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            cancelButton.setActionCommand("Cancel");
            buttonPane.add(cancelButton);
        }
    }

    private void loadResearchersIntoComboBox() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<Investigador> query = session.createQuery("FROM Investigador", Investigador.class);
        List<Investigador> lista = query.list();

        for (Investigador investigador : lista) {
            comboBoxInvestigadores.addItem(investigador);
        }

        session.getTransaction().commit();
        session.close();
    }

    private void guardarExperiencia() {
        String institucion = textFieldInstitucion.getText();
        String periodo = textFieldPeriodo.getText();
        String descripcion = textFieldDescripcion.getText();
        Investigador investigadorSeleccionado = (Investigador) comboBoxInvestigadores.getSelectedItem();

        ExperienciaLaboral nuevaExperiencia = new ExperienciaLaboral();
        nuevaExperiencia.setElInstituto(institucion);
        nuevaExperiencia.setElPeriodo(periodo);
        nuevaExperiencia.setElDescripcion(descripcion);
        nuevaExperiencia.setInvestigador(investigadorSeleccionado);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(nuevaExperiencia);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
}
