package ar.com.dbgrid.vista;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

import ar.com.dbgrid.dao.AlumnoDao;
import ar.com.dbgrid.modelo.Alumno;
import ar.com.dbgrid.modelo.ConversorResultSetADefaultTableModel;
import ar.com.dbgrid.modelo.Final;



public class FormularioAlumnos  extends JPanel  implements ActionListener, Observer {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private JTable tabla = new JTable();
  
    private JPanel operaciones = new JPanel();
    
    private JButton agregar = new JButton("Agregar");
    private JButton actualizar = new JButton("Actualizar");
    private JButton verFinales = new JButton("Ver Finales");

    /**
     * Crea la ventana con todos sus componentes dentro y la visualiza
     *
     */
    public FormularioAlumnos (){
    	super(new BorderLayout());
    	this.add(new JScrollPane(this.tabla));
    	
    	operaciones.add(new JLabel("Operaciones:"));
    	/* Agrego los botones al Listener */
    	agregar.addActionListener(this);
    	actualizar.addActionListener(this);
    	verFinales.addActionListener(this);
    	/* Agrego los botones al Formulario */
    	operaciones.add(this.agregar);
    	operaciones.add(this.actualizar);
    	operaciones.add(this.verFinales);
    	this.add(operaciones, BorderLayout.SOUTH);
    	
    	this.tabla.setPreferredScrollableViewportSize(new Dimension(500, 175));
    	FormularioFinales.getObserver().addObserver(this); 
    	AgregarAlumno.getObserver().addObserver(this);
    	AgregarFinal.getObserver().addObserver(this);
    }
    /**
     * Mete el modelo que recibe en la tabla.
     * @param modelo
     */
    public void tomaDatos(DefaultTableModel modelo)
    {
        this.tabla.setModel(modelo);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
    	   Object fuente = e.getSource();
    	   if (fuente==agregar)
    	   {
    		   new AgregarAlumno();
    	   }
    	   else if (fuente==actualizar) {
    		   this.actualizarGrilla();
    	   }else if (fuente==verFinales){
    		  
    		  if (this.tabla.getSelectedRowCount() == 1) {
    			  DefaultTableModel tm = (DefaultTableModel) this.tabla.getModel();
    			  new FormularioFinales((int)tm.getValueAt(this.tabla.getSelectedRow(), 0),(String)tm.getValueAt(this.tabla.getSelectedRow(), 1));    			  
    		  }
    	   }
    	
    }



	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof Final){
			this.actualizarGrilla();
		}
		else if(arg1 instanceof Alumno){
			this.actualizarGrilla();
		}
	}
    
	private void actualizarGrilla(){
		DefaultTableModel modelo = new DefaultTableModel();
		AlumnoDao alumnosResultSet = new AlumnoDao();
		ConversorResultSetADefaultTableModel.rellena(alumnosResultSet.getAllAlumnos(), modelo);
		this.tomaDatos(modelo);		
	}
}
