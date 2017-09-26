package ar.com.dbgrid.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ar.com.dbgrid.database.Conexion;

public class FinalesDao {

	public ResultSet getAllFinlasByAlumno(int idAlumno){
		
		Connection con = Conexion.getConnection();
		String sql = "select M.ID CODIGO, DESCRIPCION, NOTA, "  
					+ "CASE WHEN NOTA >= 4 THEN 'APROBADA' ELSE 'NO APROBADA' END RESULTADO " 
					+ " FROM FINALES F INNER JOIN MATERIA M ON F.ID_MATERIA = M.ID " 
				    + "WHERE F.ID_ALUMNO = ?";
		ResultSet rs = null;
        try
        {
        	PreparedStatement s = con.prepareStatement(sql);
            s.setInt(1, idAlumno);
            rs = s.executeQuery();
        } catch (Exception e)
        {
            e.printStackTrace();
    	} 
    		
        return rs;
	}
	
	public int insertToFinal(int idmat, int nota, int idalum)
	{
		Connection con = Conexion.getConnection();
		String sql = "insert into Finales(id,id_alumno,id_materia,nota) values (?,?,?,?)";
		String query = "select max(id) from finales";
		
		
		try {
			PreparedStatement s = con.prepareStatement(query);
			ResultSet rs= s.executeQuery();
			int id_final;
			if(rs.next())
			{
				id_final=rs.getInt(1)+1;
				PreparedStatement s1 = con.prepareStatement(sql);
				s1.setInt(1, id_final);
				s1.setInt(2, idalum);
				s1.setInt(3, idmat);
				s1.setInt(4, nota);
				return s1.executeUpdate();
			}
			con.close();

		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public int borrarFinal(int idAlumno, int idMateria, BigDecimal nota){
		Connection con = Conexion.getConnection();
		String sql = "DELETE FROM FINALES " 
				    + "WHERE ID_ALUMNO = ? " 
				    + " AND ID_MATERIA = ? "
				    + " AND NOTA = ? ";

		int r = 0;
        try
        {
        	PreparedStatement s = con.prepareStatement(sql);
            s.setInt(1, idAlumno);
            s.setInt(2, idMateria);
            s.setBigDecimal(3, nota);
            
            r = s.executeUpdate();
        } catch (Exception e)
        {
        	r = 0;
            e.printStackTrace();
        } finally {
        	try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
        }
        return r;
       
	}
}
