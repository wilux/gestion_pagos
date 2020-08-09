package net.neflores.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Empresa")//coincidir con nombre de tabla
public class Empresa {

	
	@Id//mapeo con llave primaria
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Genera autroincrementables
	private Integer idEmpresa;
	private String nombreEmpresa;
	private String cuit;
    private String cuenta;
    private int status;
    
  //relacion 1 a 1
  	@OneToOne
  	//indicamos nombre de la columna que hace de clave foranea 
  	@JoinColumn(name="idUsuario")
    private Usuario usuario;
  	


	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Empresa [idEmpresa=" + idEmpresa + ", nombreEmpresa=" + nombreEmpresa + ", cuit=" + cuit + ", cuenta="
				+ cuenta + ", usuario=" + usuario + "]";
	}



    
}