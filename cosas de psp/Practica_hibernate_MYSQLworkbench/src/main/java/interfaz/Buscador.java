package interfaz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import entidades.Investigador;
import entidades.Educacion;
import entidades.ExperienciaLaboral;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;
import java.awt.Color;

public class Buscador extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldNombreABuscar;
    private JTable table;
    private JComboBox<String> comboBoxNombreEducacion;
    private JComboBox<String> comboBoxNombreInstitucion;
    private DefaultTableModel tableModel;

    public Buscador() {
        setBounds(100, 100, 703, 649);
        getContentPane().setLayout(null);

        textFieldNombreABuscar = new JTextField();
        textFieldNombreABuscar.setBounds(234, 22, 286, 20);
        getContentPane().add(textFieldNombreABuscar);
        textFieldNombreABuscar.setColumns(10);

        JLabel lblNewLabelBuscarNombre = new JLabel("Nombre");
        lblNewLabelBuscarNombre.setBounds(165, 22, 78, 20);
        getContentPane().add(lblNewLabelBuscarNombre);

        JButton btnNewButtonBuscar = new JButton("Buscar");
        btnNewButtonBuscar.setBackground(new Color(0, 153, 153));
        btnNewButtonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarInvestigadores();
            }
        });
        btnNewButtonBuscar.setBounds(544, 65, 89, 23);
        getContentPane().add(btnNewButtonBuscar);

        comboBoxNombreEducacion = new JComboBox<>();
        comboBoxNombreEducacion.setBackground(new Color(0, 153, 153));
        comboBoxNombreEducacion.setBounds(48, 65, 195, 22);
        getContentPane().add(comboBoxNombreEducacion);
        loadEducacionIntoComboBox();

        comboBoxNombreInstitucion = new JComboBox<>();
        comboBoxNombreInstitucion.setBackground(new Color(0, 153, 153));
        comboBoxNombreInstitucion.setBounds(284, 65, 236, 22);
        getContentPane().add(comboBoxNombreInstitucion);
        loadInstitucionIntoComboBox();

        JLabel lblNewLabelFiltrarEducacion = new JLabel("Filtrar por Educacion");
        lblNewLabelFiltrarEducacion.setBounds(48, 51, 195, 14);
        getContentPane().add(lblNewLabelFiltrarEducacion);

        JLabel lblNewLabelFiltrarInstitucion = new JLabel("Filtrar por Institucion");
        lblNewLabelFiltrarInstitucion.setBounds(284, 51, 195, 14);
        getContentPane().add(lblNewLabelFiltrarInstitucion);

        JLabel lblNewLabelBuscadorInvestigadores = new JLabel("Buscador de investigadores");
        lblNewLabelBuscadorInvestigadores.setBounds(533, 51, 157, 14);
        getContentPane().add(lblNewLabelBuscadorInvestigadores);

        JButton btnNewButtonATRAS = new JButton("Atras");
        btnNewButtonATRAS.setBackground(new Color(0, 153, 153));
        btnNewButtonATRAS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButtonATRAS.setBounds(10, 11, 59, 23);
        getContentPane().add(btnNewButtonATRAS);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Email", "Descripción"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 100, 680, 500);
        getContentPane().add(scrollPane);
    }

    private void loadEducacionIntoComboBox() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<String> query = session.createQuery("SELECT DISTINCT e.edGrado FROM Educacion e", String.class);
            List<String> educaciones = query.list();
            for (String educacion : educaciones) {
                comboBoxNombreEducacion.addItem(educacion);
            }
        } finally {
            session.close();
        }
    }

    private void loadInstitucionIntoComboBox() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<String> query = session.createQuery("SELECT DISTINCT el.elInstituto FROM ExperienciaLaboral el", String.class);
            List<String> instituciones = query.list();
            for (String institucion : instituciones) {
                comboBoxNombreInstitucion.addItem(institucion);
            }
        } finally {
            session.close();
        }
    }

    private void buscarInvestigadores() {
        String nombre = textFieldNombreABuscar.getText();
        String educacion = (String) comboBoxNombreEducacion.getSelectedItem();
        String institucion = (String) comboBoxNombreInstitucion.getSelectedItem();

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT DISTINCT i FROM Investigador i "
                       + "LEFT JOIN FETCH i.educaciones e "
                       + "LEFT JOIN FETCH i.experienciasLaborales el "
                       + "WHERE (:nombre IS NULL OR i.invNombre = :nombre) "
                       + "AND (:educacion IS NULL OR e.edGrado = :educacion) "
                       + "AND (:institucion IS NULL OR el.elInstituto = :institucion)";

            Query<Investigador> query = session.createQuery(hql, Investigador.class);
            query.setParameter("nombre", nombre.isEmpty() ? null : nombre);
            query.setParameter("educacion", educacion);
            query.setParameter("institucion", institucion);

            List<Investigador> investigadores = query.list();
            actualizarTabla(investigadores);
        } finally {
            session.close();
        }
    }

    private void actualizarTabla(List<Investigador> investigadores) {
        tableModel.setRowCount(0); // Limpiar la tabla
        for (Investigador inv : investigadores) {
            Vector<Object> row = new Vector<>();
            row.add(inv.getInvId());
            row.add(inv.getInvNombre());
            row.add(inv.getInvEmail());
            row.add(inv.getInvDescripcion());
            tableModel.addRow(row);
        }
    }
}
