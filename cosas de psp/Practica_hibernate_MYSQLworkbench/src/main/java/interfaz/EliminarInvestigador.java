package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import entidades.Investigador;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class EliminarInvestigador extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldInvestigadorABuscar;
	private JLabel lblNewLabelResultadoEncontrado;
	private Investigador investigadorEncontrado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EliminarInvestigador dialog = new EliminarInvestigador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EliminarInvestigador() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldInvestigadorABuscar.getText();
				investigadorEncontrado = buscarInvestigadorPorNombre(nombre);
				if (investigadorEncontrado != null) {
					lblNewLabelResultadoEncontrado.setText("Investigador encontrado: " + investigadorEncontrado.getInvNombre());
				} else {
					lblNewLabelResultadoEncontrado.setText("Investigador no encontrado.");
				}
			}
		});
		btnBuscar.setBounds(76, 120, 257, 29);
		contentPanel.add(btnBuscar);

		textFieldInvestigadorABuscar = new JTextField();
		textFieldInvestigadorABuscar.setBounds(76, 63, 257, 19);
		contentPanel.add(textFieldInvestigadorABuscar);
		textFieldInvestigadorABuscar.setColumns(10);

		JLabel lblNewLabel = new JLabel("Inserta el nombre del investigador");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(76, 83, 257, 13);
		contentPanel.add(lblNewLabel);

		JLabel lblTextoResultado = new JLabel("Resultado:");
		lblTextoResultado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTextoResultado.setBackground(new Color(255, 200, 0));
		lblTextoResultado.setForeground(Color.BLUE);
		lblTextoResultado.setBounds(10, 29, 98, 13);
		contentPanel.add(lblTextoResultado);

		lblNewLabelResultadoEncontrado = new JLabel("");
		lblNewLabelResultadoEncontrado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabelResultadoEncontrado.setBounds(91, 29, 296, 13);
		contentPanel.add(lblNewLabelResultadoEncontrado);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (investigadorEncontrado != null) {
					eliminarInvestigador(investigadorEncontrado);
					lblNewLabelResultadoEncontrado.setText("Investigador eliminado.");
				}
			}
		});
		btnEliminar.setBounds(76, 170, 257, 29);
		contentPanel.add(btnEliminar);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(cancelButton);
	}

	private Investigador buscarInvestigadorPorNombre(String nombre) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "FROM Investigador WHERE invNombre = :nombreInvestigador";
			Query<Investigador> query = session.createQuery(hql, Investigador.class);
			query.setParameter("nombreInvestigador", nombre);
			return query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	private void eliminarInvestigador(Investigador investigador) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			session.delete(investigador);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}
}
