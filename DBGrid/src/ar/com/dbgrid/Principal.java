package ar.com.dbgrid;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import ar.com.dbgrid.dao.AlumnoDao;
import ar.com.dbgrid.modelo.ConversorResultSetADefaultTableModel;
import ar.com.dbgrid.vista.FormularioAlumnos;

public class Principal {

	public static void main(String[] args) {
	
		
		try {
			SwingUtilities.invokeAndWait(new Runnable()
			{
			    public void run() {
			    	createAndShowUI();      
			    }
			});
		} catch (InvocationTargetException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	  private static void createAndShowUI() {
	        JFrame frame = new JFrame("Alumnos");
	        DefaultTableModel modelo = new DefaultTableModel();
	        FormularioAlumnos form = new FormularioAlumnos();
	        frame.add(form);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.pack();
	        frame.setLocationRelativeTo(null);
			AlumnoDao alumnosResultSet = new AlumnoDao();
	        frame.setVisible(true);		
			ConversorResultSetADefaultTableModel.rellena(alumnosResultSet.getAllAlumnos(), modelo);
			form.tomaDatos(modelo);
	    }
	
	
}
