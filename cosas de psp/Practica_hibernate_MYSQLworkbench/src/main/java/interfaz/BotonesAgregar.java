package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BotonesAgregar extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BotonesAgregar dialog = new BotonesAgregar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BotonesAgregar() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnNewButtonAgregarInvestigador = new JButton("Investigador");
			btnNewButtonAgregarInvestigador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AgregarInvestigador ai = new AgregarInvestigador();
					ai.setVisible(true);
				}
			});
			btnNewButtonAgregarInvestigador.setBounds(140, 25, 147, 42);
			contentPanel.add(btnNewButtonAgregarInvestigador);
		}
		{
			JButton btnNewEducacion = new JButton("Educacion");
			btnNewEducacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AgregarEducacion ai = new AgregarEducacion();
					ai.setVisible(true);
				}
			});
			btnNewEducacion.setBounds(57, 79, 99, 42);
			contentPanel.add(btnNewEducacion);
		}
		{
			JButton btnNewButtonAgregarExperiencia = new JButton("Experiencia");
			btnNewButtonAgregarExperiencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AgregarExperiencia ai = new AgregarExperiencia();
					ai.setVisible(true);
				}
			});
			btnNewButtonAgregarExperiencia.setBounds(278, 78, 99, 42);
			contentPanel.add(btnNewButtonAgregarExperiencia);
		}
		{
			JButton btnNewButtonAgregarTrabajosGrado = new JButton("Trabajos Grado");
			btnNewButtonAgregarTrabajosGrado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AgregarTrabajosDirigidos ai = new AgregarTrabajosDirigidos();
					ai.setVisible(true);
				}
			});
			btnNewButtonAgregarTrabajosGrado.setBounds(233, 141, 115, 42);
			contentPanel.add(btnNewButtonAgregarTrabajosGrado);
		}
		{
			JButton btnNewButtonTesisRedactadas = new JButton("TesisRedactadas");
			btnNewButtonTesisRedactadas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AgregarTesis ai = new AgregarTesis();
					ai.setVisible(true);
				}
			});
			btnNewButtonTesisRedactadas.setBounds(92, 139, 119, 42);
			contentPanel.add(btnNewButtonTesisRedactadas);
		}
		{
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
	}

}
