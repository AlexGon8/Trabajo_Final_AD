package interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import entidades.ExperienciaLaboral;
import util.HibernateUtil;

public class MostrarExperiencia extends JFrame {

    private JTable table;

    public MostrarExperiencia() {
        setTitle("Lista de Experiencias Laborales");
        setBounds(100, 100, 550, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));

        String[] columnNames = {"ID", "Instituto", "Periodo", "Descripción", "ID Investigador"};
        Object[][] data = obtenerDatosExperiencias();

        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private Object[][] obtenerDatosExperiencias() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<ExperienciaLaboral> query = session.createQuery("FROM ExperienciaLaboral", ExperienciaLaboral.class);
        List<ExperienciaLaboral> lista = query.list();

        Object[][] data = new Object[lista.size()][5];

        for (int i = 0; i < lista.size(); i++) {
            ExperienciaLaboral experiencia = lista.get(i);
            data[i][0] = experiencia.getElId();
            data[i][1] = experiencia.getElInstituto();
            data[i][2] = experiencia.getElPeriodo();
            data[i][3] = experiencia.getElDescripcion();
            data[i][4] = experiencia.getInvestigador() != null ? experiencia.getInvestigador().getInvId() : null;
        }

        session.getTransaction().commit();
        session.close();

        return data;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MostrarExperiencia frame = new MostrarExperiencia();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
