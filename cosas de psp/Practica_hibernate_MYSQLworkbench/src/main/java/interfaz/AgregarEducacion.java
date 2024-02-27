package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import entidades.Educacion;
import entidades.Investigador;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import util.HibernateUtil; // Asegúrate de importar HibernateUtil

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AgregarEducacion extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldGrado;
    private JTextField textFieldCampo;
    private JComboBox<Investigador> comboBoxInvestigadoresDesplegable; // Asegúrate de que es del tipo correcto
    private JTextField textFieldDescripcion;

  
    public AgregarEducacion() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNewLabelGrado = new JLabel("Grado");
        lblNewLabelGrado.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabelGrado.setBounds(38, 38, 174, 13);
        contentPanel.add(lblNewLabelGrado);

        JLabel lblNewLabelCampo = new JLabel("Campo");
        lblNewLabelCampo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabelCampo.setBounds(38, 78, 174, 13);
        contentPanel.add(lblNewLabelCampo);

        JLabel lblNewLabelDescripcion = new JLabel("Descripcion");
        lblNewLabelDescripcion.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabelDescripcion.setBounds(38, 118, 174, 13);
        contentPanel.add(lblNewLabelDescripcion);

        JLabel lblNewLabelInvestigador = new JLabel("Investigador");
        lblNewLabelInvestigador.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabelInvestigador.setBounds(38, 158, 174, 19);
        contentPanel.add(lblNewLabelInvestigador);

        textFieldGrado = new JTextField();
        textFieldGrado.setBounds(182, 37, 233, 19);
        contentPanel.add(textFieldGrado);
        textFieldGrado.setColumns(10);

        textFieldCampo = new JTextField();
        textFieldCampo.setBounds(182, 77, 233, 19);
        contentPanel.add(textFieldCampo);
        textFieldCampo.setColumns(10);

        textFieldDescripcion = new JTextField();
        textFieldDescripcion.setBounds(182, 117, 233, 19);
        contentPanel.add(textFieldDescripcion);
        textFieldDescripcion.setColumns(10);

        comboBoxInvestigadoresDesplegable = new JComboBox<>(); // Asegúrate de que se carguen los datos MAN
        comboBoxInvestigadoresDesplegable.setBounds(182, 157, 233, 21);
        contentPanel.add(comboBoxInvestigadoresDesplegable);
        loadResearchersIntoComboBox(); // Cargar investigadores en el comboBox

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        {
            JButton ButtonGuardar = new JButton("Guardar");
            ButtonGuardar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    guardarEducacion();
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
            comboBoxInvestigadoresDesplegable.addItem(investigador);
        }

        session.getTransaction().commit();
        session.close();
    }

    private void guardarEducacion() {
        String grado = textFieldGrado.getText();
        String campo = textFieldCampo.getText();
        String descripcion = textFieldDescripcion.getText();
        Investigador investigadorSeleccionado = (Investigador) comboBoxInvestigadoresDesplegable.getSelectedItem();

        Educacion nuevaEducacion = new Educacion();
        nuevaEducacion.setEdGrado(grado);
        nuevaEducacion.setEdCampo(campo);
        nuevaEducacion.setEdDescripcion(descripcion);
        nuevaEducacion.setInvestigador(investigadorSeleccionado);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(nuevaEducacion);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
}
