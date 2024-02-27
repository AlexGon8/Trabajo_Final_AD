package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entidades.Investigador;
import entidades.TesisRedactadas;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class AgregarTesis extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldNombreTesis;
    private JTextField textFieldDescripcionTesis;
    private JComboBox<Investigador> comboBoxInvestigadores;

    public AgregarTesis() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNewLabelNombreTesis = new JLabel("Nombre de Tesis");
        lblNewLabelNombreTesis.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabelNombreTesis.setBounds(49, 28, 149, 13);
        contentPanel.add(lblNewLabelNombreTesis);

        JLabel lblNewLabelDescripcionTesis = new JLabel("Descripcion");
        lblNewLabelDescripcionTesis.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabelDescripcionTesis.setBounds(49, 64, 149, 18);
        contentPanel.add(lblNewLabelDescripcionTesis);

        JLabel lblNewLabelInvestigador = new JLabel("Investigador");
        lblNewLabelInvestigador.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabelInvestigador.setBounds(49, 110, 149, 18);
        contentPanel.add(lblNewLabelInvestigador);

        textFieldNombreTesis = new JTextField();
        textFieldNombreTesis.setBounds(245, 27, 169, 19);
        contentPanel.add(textFieldNombreTesis);
        textFieldNombreTesis.setColumns(10);

        textFieldDescripcionTesis = new JTextField();
        textFieldDescripcionTesis.setBounds(245, 63, 169, 19);
        contentPanel.add(textFieldDescripcionTesis);
        textFieldDescripcionTesis.setColumns(10);

        comboBoxInvestigadores = new JComboBox<>();
        comboBoxInvestigadores.setBounds(208, 111, 206, 21);
        contentPanel.add(comboBoxInvestigadores);
        loadResearchersIntoComboBox();

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        {
            JButton ButtonGuardar = new JButton("Guardar");
            ButtonGuardar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    guardarTesis();
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

    private void guardarTesis() {
        String nombreTesis = textFieldNombreTesis.getText();
        String descripcionTesis = textFieldDescripcionTesis.getText();
      
        Investigador investigadorSeleccionado = (Investigador) comboBoxInvestigadores.getSelectedItem();

        TesisRedactadas nuevaTesis = new TesisRedactadas();
        nuevaTesis.setTesisNombre(nombreTesis);
        nuevaTesis.setTesisDescripcion(descripcionTesis);
        nuevaTesis.setInvestigador(investigadorSeleccionado);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(nuevaTesis);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
}
