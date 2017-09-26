package ar.com.dbgrid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ar.com.dbgrid.database.Conexion;

public class AlumnoDao {
	
	Connection con = Conexion.getConnection();
	ResultSet rs = null;
	
	public ResultSet getAllAlumnos(){
		
        try
        {
            Statement s = con.createStatement();
            rs = s.executeQuery("select id as Legajo, Apel_Nombre as [Apellido y Nombre], dbo.fn_porcentaje_carrera(a.id) as Porcentaje from Alumno a ");
                    
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return rs;
        
	}
	
	public void insertToAlumno(String apel_nomb){
		try{
			String query = "insert into Alumno (ID, APEL_NOMBRE) values (?,?)";
			PreparedStatement s = con.prepareStatement(query);
			
			int id=busquedaDeId()+1;

			s.setInt(1, id);
			s.setString(2, apel_nomb);
			s.executeUpdate();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private int busquedaDeId()
	{
		try {
			rs=null;
			String query = "select MAX(ID) as id from Alumno";
			PreparedStatement s = con.prepareStatement(query);
			rs = s.executeQuery();

			if(rs.next())
			{return rs.getInt(1);}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
