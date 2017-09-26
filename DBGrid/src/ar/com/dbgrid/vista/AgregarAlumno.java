package ar.com.dbgrid.vista;

import javax.swing.JFrame;
import javax.swing.JTextField;

import ar.com.dbgrid.dao.AlumnoDao;
import ar.com.dbgrid.modelo.Alumno;
import ar.com.dbgrid.modelo.AlumnoObservable;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AgregarAlumno extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static AlumnoObservable getObserver() {
		return OBSERVER;
	}

	private final static AlumnoObservable OBSERVER = new AlumnoObservable();

	private JTextField txt_apel_nombre;
	private JLabel lblNombreApellido;
	private JButton btnSubmit;
	
	public  AgregarAlumno() {
		setTitle("REGISTRO DE ALUMNO");
		setBounds(100, 100, 312, 108);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		txt_apel_nombre = new JTextField("Ej: Padilla, Ismael"); 
		txt_apel_nombre.setBounds(25, 32, 135, 20);
		getContentPane().add(txt_apel_nombre);
		txt_apel_nombre.setColumns(10);
		
		lblNombreApellido = new JLabel("apellido y nombre");
		lblNombreApellido.setBounds(40, 11, 113, 14);
		getContentPane().add(lblNombreApellido);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(this);

		btnSubmit.setBounds(184, 31, 89, 23);
		getContentPane().add(btnSubmit);

		
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnSubmit){
			AlumnoDao alum= new AlumnoDao();
			alum.insertToAlumno(txt_apel_nombre.getText());
			Alumno alumno = new Alumno(txt_apel_nombre.getText());
    		OBSERVER.setChanged();
    		OBSERVER.notifyObservers(alumno);
			dispose();			
		}
	}
}
