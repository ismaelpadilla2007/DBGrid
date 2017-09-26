package ar.com.dbgrid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;

import ar.com.dbgrid.database.Conexion;

public class MateriaDao {

	private String nombre;
	private int id;
	
	public MateriaDao(String nombre,int id){
		this.nombre=nombre;
		this.id=id;
	}
	public MateriaDao() {
		// TODO Auto-generated constructor stub
	}
	
	public void mostrar(JComboBox <MateriaDao> comboMateria, int idAlumno)
	{
		Connection con = Conexion.getConnection();
		String sql = "select m.DESCRIPCION,m.ID "
					+"from (select * from MATERIA) as m "
					+"where m.ID not in (select f.ID_MATERIA from FINALES f where f.ID_ALUMNO = ? and f.NOTA >= 4)";
		//"select * from Materias m where not exits(select 1 from 
		//	finales f where f.idMateria = m.id and f.idalumno = ? and nota >=4)";
		ResultSet rs = null;
		try{
			PreparedStatement s = con.prepareStatement(sql);
			s.setInt(1, idAlumno);
			rs = s.executeQuery();
			while(rs.next())
			{
				comboMateria.addItem(
						new MateriaDao (
								rs.getString(1),
								rs.getInt(2)
								)
						);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String toString ()
	{
		return nombre;
	}
}
