package interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import entidades.TrabajosGradoDirigidos;
import util.HibernateUtil;

public class MostrarTrabajosGradoDirigidos extends JFrame {

    private JTable table;

    public MostrarTrabajosGradoDirigidos() {
        setTitle("Lista de Trabajos de Grado Dirigidos");
        setBounds(100, 100, 650, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));

        String[] columnNames = {"ID", "Nombre", "Alumno", "Nivel", "Tipo", "ID Investigador"};
        Object[][] data = obtenerDatosTrabajosGradoDirigidos();

        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private Object[][] obtenerDatosTrabajosGradoDirigidos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<TrabajosGradoDirigidos> query = session.createQuery("FROM TrabajosGradoDirigidos", TrabajosGradoDirigidos.class);
        List<TrabajosGradoDirigidos> lista = query.list();

        Object[][] data = new Object[lista.size()][6];

        for (int i = 0; i < lista.size(); i++) {
            TrabajosGradoDirigidos trabajo = lista.get(i);
            data[i][0] = trabajo.getTgId();
            data[i][1] = trabajo.getTgNombre();
            data[i][2] = trabajo.getTgAlumno();
            data[i][3] = trabajo.getTgNivel();
            data[i][4] = trabajo.getTgTipo();
            data[i][5] = trabajo.getInvestigador() != null ? trabajo.getInvestigador().getInvId() : null;
        }

        session.getTransaction().commit();
        session.close();

        return data;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MostrarTrabajosGradoDirigidos frame = new MostrarTrabajosGradoDirigidos();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
