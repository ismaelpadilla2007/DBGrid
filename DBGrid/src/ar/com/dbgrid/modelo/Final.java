package ar.com.dbgrid.modelo;

import java.math.BigDecimal;

public class Final {

	private int id_alumno;
	private int id_materia;
	private BigDecimal nota;
	
	public Final(int id_alumno,int id_materia, BigDecimal nota)
	{
		this.id_alumno=id_alumno;
		this.id_materia=id_materia;
		this.nota=nota;
	}
	public Final(int id_alumno,int id_materia, int nota)
	{
		this.id_alumno=id_alumno;
		this.id_materia=id_materia;
	}
	public int getId_alumno() {
		return id_alumno;
	}
	
	public void setId_alumno(int id_alumno) {
		this.id_alumno = id_alumno;
	}
	public int getId_materia() {
		return id_materia;
	}
	public void setId_materia(int id_materia) {
		this.id_materia = id_materia;
	}
	public BigDecimal getNota() {
		return nota;
	}
	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}

}
