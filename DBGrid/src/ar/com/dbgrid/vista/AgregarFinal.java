package ar.com.dbgrid.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.com.dbgrid.dao.FinalesDao;
import ar.com.dbgrid.dao.MateriaDao;
import ar.com.dbgrid.modelo.AgregarFinalObservable;
import ar.com.dbgrid.modelo.Final;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class AgregarFinal extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private JPanel contentPane;
	private JTextField txt_nota;
	private int idMateria;
	private int nota;
	private int idAlumno;
	private JButton btnAgregarContinue = new JButton("AGREGAR Y CONTINUAR");
	private JButton btnAgregarExit = new JButton("AGREGAR Y FINALIZAR");
	private JComboBox <MateriaDao> comboMateria = new JComboBox<MateriaDao>();
	
	private final static AgregarFinalObservable OBSERVER = new AgregarFinalObservable();
	private JTextField txt_ID;
	
	public static AgregarFinalObservable getObserver() {
		return OBSERVER;
	}

	public AgregarFinal(int idAlumno){
		this.idAlumno = idAlumno;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 446, 156);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MateriaDao agregarMaterias = new MateriaDao();
		agregarMaterias.mostrar(comboMateria,idAlumno);//se agrega las materias
		comboMateria.addActionListener(this);
		comboMateria.setBounds(41, 58, 331, 28);
		contentPane.add(comboMateria);
		
		txt_nota = new JTextField();
		txt_nota.setBounds(380, 58, 40, 28);
		contentPane.add(txt_nota);
		txt_nota.setColumns(10);

		btnAgregarContinue.addActionListener(this);
		btnAgregarContinue.setBounds(10, 11, 172, 23);
		contentPane.add(btnAgregarContinue);
		
		btnAgregarExit.addActionListener(this);
		btnAgregarExit.setBounds(197, 11, 162, 23);
		contentPane.add(btnAgregarExit);
		
		txt_ID = new JTextField();
		txt_ID.setEnabled(false);
		txt_ID.setBounds(10, 58, 21, 28);
		contentPane.add(txt_ID);
		txt_ID.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 45, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblNota = new JLabel("Nota");
		lblNota.setBounds(385, 45, 35, 14);
		contentPane.add(lblNota);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnAgregarExit){
			ingresoDeFinal();
			comboMateria.removeItemAt(comboMateria.getSelectedIndex());
			dispose();
		}
		else if(e.getSource()==this.btnAgregarContinue){
			ingresoDeFinal();
			if(this.nota >= 4)
			comboMateria.removeItemAt(comboMateria.getSelectedIndex());
			this.txt_nota.setText("");
		}
		else if(e.getSource()== this.comboMateria)
			txt_ID.setText(""+comboMateria.getItemAt(comboMateria.getSelectedIndex()).getId());				
	}
	
	private void ingresoDeFinal(){
		idMateria = comboMateria.getItemAt(comboMateria.getSelectedIndex()).getId();	
		FinalesDao finales = new FinalesDao();
		nota = Integer.parseInt(txt_nota.getText());
		finales.insertToFinal(idMateria, nota, idAlumno);	
		Final fn = new Final(idAlumno, idMateria, nota);
		OBSERVER.setChanged();
		OBSERVER.notifyObservers(fn);					
	}
	
	public int getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}
}
