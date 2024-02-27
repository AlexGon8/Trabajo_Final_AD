package interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import entidades.TesisRedactadas;
import util.HibernateUtil;

public class MostrarTesisRedactadas extends JFrame {

    private JTable table;

    public MostrarTesisRedactadas() {
        setTitle("Lista de Tesis Redactadas");
        setBounds(100, 100, 600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));

        String[] columnNames = {"ID", "Nombre", "Descripción", "ID Investigador"};
        Object[][] data = obtenerDatosTesisRedactadas();

        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private Object[][] obtenerDatosTesisRedactadas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<TesisRedactadas> query = session.createQuery("FROM TesisRedactada", TesisRedactadas.class);
        List<TesisRedactadas> lista = query.list();

        Object[][] data = new Object[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
        	TesisRedactadas tesis = lista.get(i);
            data[i][0] = tesis.getTesisId();
            data[i][1] = tesis.getTesisNombre();
            data[i][2] = tesis.getTesisDescripcion();
            data[i][3] = tesis.getInvestigador() != null ? tesis.getInvestigador().getInvId() : null;
        }

        session.getTransaction().commit();
        session.close();

        return data;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MostrarTesisRedactadas frame = new MostrarTesisRedactadas();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
