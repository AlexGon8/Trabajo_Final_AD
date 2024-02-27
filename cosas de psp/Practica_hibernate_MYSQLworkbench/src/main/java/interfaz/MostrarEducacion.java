package interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import entidades.Educacion;
import util.HibernateUtil;

public class MostrarEducacion extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;

    public MostrarEducacion() {
        setBounds(100, 100, 650, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        String[] columnNames = {"ID", "Grado", "Campo", "Descripción", "ID Investigador"};
        Object[][] data = obtenerDatosEducacion();

        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
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

    private Object[][] obtenerDatosEducacion() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("FROM Educacion");
        List<Educacion> lista = query.list();

        Object[][] data = new Object[lista.size()][5];

        for (int i = 0; i < lista.size(); i++) {
            Educacion educacion = lista.get(i);
            data[i][0] = educacion.getEdId();
            data[i][1] = educacion.getEdGrado();
            data[i][2] = educacion.getEdCampo();
            data[i][3] = educacion.getEdDescripcion();
            data[i][4] = educacion.getInvestigador() != null ? educacion.getInvestigador().getInvId() : null;
        }

        session.getTransaction().commit();
        session.close();

        return data;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MostrarEducacion dialog = new MostrarEducacion();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
