package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class MostrarLista extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarLista dialog = new MostrarLista();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarLista() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton cancelButtonListaGrados = new JButton("Ver lista de Grados");
		cancelButtonListaGrados.setFont(new Font("Tahoma", Font.BOLD, 11));
		cancelButtonListaGrados.setForeground(new Color(255, 255, 255));
		cancelButtonListaGrados.setBackground(new Color(51, 153, 153));
		cancelButtonListaGrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarTrabajosGradoDirigidos mtgd = new MostrarTrabajosGradoDirigidos();
				mtgd.setVisible(true);
			}
		});
		cancelButtonListaGrados.setActionCommand("Cancel");
		cancelButtonListaGrados.setBounds(114, 131, 201, 21);
		contentPanel.add(cancelButtonListaGrados);
		
		JButton buttonListaTesis = new JButton("Ver Lista de tesis");
		buttonListaTesis.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonListaTesis.setForeground(new Color(255, 255, 255));
		buttonListaTesis.setBackground(new Color(51, 153, 153));
		buttonListaTesis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarTesisRedactadas mtr = new MostrarTesisRedactadas();
				mtr.setVisible(true);
			}
		});
		buttonListaTesis.setActionCommand("Cancel");
		buttonListaTesis.setBounds(114, 88, 201, 21);
		contentPanel.add(buttonListaTesis);
		
		JButton buttonListaEducacion = new JButton("Ver lista de Estudios");
		buttonListaEducacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonListaEducacion.setForeground(new Color(255, 255, 255));
		buttonListaEducacion.setBackground(new Color(51, 153, 153));
		buttonListaEducacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarEducacion me = new MostrarEducacion();
				me.setVisible(true);
			}
		});
		buttonListaEducacion.setActionCommand("Cancel");
		buttonListaEducacion.setBounds(114, 52, 201, 21);
		contentPanel.add(buttonListaEducacion);
		
		JButton buttonListaLaboral = new JButton("Experiencia Laboral");
		buttonListaLaboral.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonListaLaboral.setForeground(new Color(255, 255, 255));
		buttonListaLaboral.setBackground(new Color(51, 153, 153));
		buttonListaLaboral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarExperiencia me = new MostrarExperiencia();
				me.setVisible(true);
			}
		});
		buttonListaLaboral.setActionCommand("Cancel");
		buttonListaLaboral.setBounds(114, 165, 201, 21);
		contentPanel.add(buttonListaLaboral);
		
		JButton buttonListaInvestigadores = new JButton("Ver lista de Investigadores");
		buttonListaInvestigadores.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonListaInvestigadores.setForeground(new Color(255, 255, 255));
		buttonListaInvestigadores.setBackground(new Color(51, 153, 153));
		buttonListaInvestigadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarInvestigadores mi = new MostrarInvestigadores();
				mi.setVisible(true);
			}
		});
		buttonListaInvestigadores.setActionCommand("Cancel");
		buttonListaInvestigadores.setBounds(114, 21, 201, 21);
		contentPanel.add(buttonListaInvestigadores);
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
