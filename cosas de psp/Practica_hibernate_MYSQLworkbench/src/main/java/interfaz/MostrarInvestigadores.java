package interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import entidades.Investigador;
import util.HibernateUtil;

public class MostrarInvestigadores extends JFrame {

    private JTable table;

    public MostrarInvestigadores() {
        setTitle("Lista de Investigadores");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));

        String[] columnNames = {"ID", "Nombre", "Email", "Categoría"};
        Object[][] data = obtenerDatosInvestigadores();

        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private Object[][] obtenerDatosInvestigadores() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("FROM Investigador");
        List<Investigador> lista = query.list();

        Object[][] data = new Object[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            Investigador investigador = lista.get(i);
            data[i][0] = investigador.getInvId();
            data[i][1] = investigador.getInvNombre();
            data[i][2] = investigador.getInvEmail();
            data[i][3] = investigador.getInvCategoria(); 
        }

        session.getTransaction().commit();
        session.close();

        return data;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MostrarInvestigadores frame = new MostrarInvestigadores();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}