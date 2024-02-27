package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;


import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import entidades.Educacion;
import entidades.ExperienciaLaboral;
import entidades.Investigador;
import entidades.TesisRedactadas;
import entidades.TrabajosGradoDirigidos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.github.javafaker.Faker;	

public class PantallaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(131, 22, 165, 21);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonesAgregar ba = new BotonesAgregar();
				ba.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarInvestigador ei = new EliminarInvestigador();
				ei.setVisible(true);
			}
		});
		btnEliminar.setBounds(131, 168, 165, 21);
		contentPane.add(btnEliminar);
		//lo mencionaste en una clase y busque metodos y al parecer ya alguien habia oensado en este tipo de cosas "com.github.javafaker"
		JButton btnGenerarDatos = new JButton("Generar Datos");
		btnGenerarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			        Session session = HibernateUtil.getSessionFactory().openSession();
			        Transaction transaction = null;
			        Faker faker = new Faker(); // esto esta guay, se trae de git, verificar pom
			        /*
			         * haber si no peta con 1000, no lo pude llegar aprobar, solo probe cuando tenia investigador y solo 10 argumentos
			         * Pon a prueba la base de datos !!!!!!!!!!!!!!!!
			         */
			        try {
			            transaction = session.beginTransaction();
			            for (int i = 0; i < 1000; i++) {
			                // Crear y guardar el investigador
			                Investigador investigador = new Investigador();
			                investigador.setInvNombre(faker.name().fullName());
			                investigador.setInvExtension(faker.number().numberBetween(1000, 9999));
			                investigador.setInvEmail(faker.internet().emailAddress());
			                investigador.setInvSni(faker.number().numberBetween(1, 10));
			                investigador.setInvProdep(faker.number().numberBetween(1, 10));
			                investigador.setInvCategoria(faker.number().numberBetween(0, 5));
			                investigador.setInvCubiculo(faker.number().numberBetween(1, 20));
			                session.save(investigador);

			                // Crear y guardar datos relacionados con el investigador *(segunda parte del metodo, no comprobada(
			                Educacion educacion = new Educacion();
			                educacion.setInvestigador(investigador); // Asociar el investigador a la educación
			                educacion.setEdGrado(faker.educator().course());
			                educacion.setEdCampo(faker.educator().university());
			                educacion.setEdDescripcion(faker.lorem().sentence());
			                session.save(educacion);

			                ExperienciaLaboral experiencia = new ExperienciaLaboral();
			                experiencia.setInvestigador(investigador); // Asociar el investigador a la experiencia
			                experiencia.setElInstituto(faker.company().name());
			                experiencia.setElPeriodo(faker.date().past(365 * 30, TimeUnit.DAYS).toString()); 
			                experiencia.setElDescripcion(faker.lorem().sentence());
			                session.save(experiencia);

			                TesisRedactadas tesis = new TesisRedactadas();
			                tesis.setInvestigador(investigador); // Asociar el investigador a la tesis
			                tesis.setTesisNombre(faker.book().title());
			                tesis.setTesisDescripcion(faker.lorem().sentence());
			                session.save(tesis);

			                TrabajosGradoDirigidos trabajo = new TrabajosGradoDirigidos();
			                trabajo.setInvestigador(investigador); // Asociar el investigador al trabajo
			                trabajo.setTgNombre(faker.book().title()); //porque no jeje
			                trabajo.setTgAlumno(faker.name().fullName());
			                trabajo.setTgNivel(faker.educator().secondarySchool());
			                trabajo.setTgTipo("Tesis");//ya se me acabo la imaginacion son las 0303 am
			                session.save(trabajo);
			            }

			            transaction.commit();
			        } catch (RuntimeException ex) {
			            if (transaction != null) transaction.rollback();
			            ex.printStackTrace();
			        } finally {
			            session.close();
			        }
			    
			}
		});
		btnGenerarDatos.setBounds(131, 54, 165, 21);
		contentPane.add(btnGenerarDatos);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscador search = new Buscador();
				search.setVisible(true);
			}
		});
		btnBuscar.setBounds(131, 137, 165, 21);
		contentPane.add(btnBuscar);
		
		JButton btnMostrarLista = new JButton("Mostrar Lista");
		btnMostrarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarLista ai = new MostrarLista();
				ai.setVisible(true);
			}
		});
		btnMostrarLista.setBounds(131, 85, 165, 21);
		contentPane.add(btnMostrarLista);
	}

}
