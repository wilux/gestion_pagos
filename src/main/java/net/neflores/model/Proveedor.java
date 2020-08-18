package net.neflores.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Proveedor")
public class Proveedor {
	
	@Id//mapeo con llave primaria
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Genera autroincrementables
	private Integer idProveedor;
	private String nombre;
	private String cuit;
	private String cbu;
	private double importe;
	private int status;
   
  	@OneToOne
  	@JoinColumn(name="idUsuario")
    private Usuario usuario;
	

	public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
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
		return "Proveedor [idProveedor=" + idProveedor + ", nombre=" + nombre + ", cuit=" + cuit + ", cbu=" + cbu
				+ ", importe=" + importe + ", usuario=" + usuario + "]";
	}

	

}
