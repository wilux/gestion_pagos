package net.neflores.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Pago")
public class Pago {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idPago;
	
  	@OneToOne
  	@JoinColumn(name="idUsuario")
    private Usuario usuario;
	

	private String fechaCreacion;
	private String fechaAcred;
	private int cantidadPagos;
	private double totalPagado;
	private String prestacion;
	private String subprestacion;
	private String referencia;
	private String archivo;
	
	@OneToOne
	@JoinColumn(name="idEmpresa")
	private Empresa empresas;


	public Empresa getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Empresa empresas) {
		this.empresas = empresas;
	}

	public Integer getIdPago() {
		return idPago;
	}

	public void setIdPago(Integer idPago) {
		this.idPago = idPago;
	}


	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaAcred() {
		return fechaAcred;
	}

	public void setFechaAcred(String fechaAcred) {
		this.fechaAcred = fechaAcred;
	}

	public int getCantidadPagos() {
		return cantidadPagos;
	}

	public void setCantidadPagos(int cantidad) {
		this.cantidadPagos = cantidad;
	}

	public double getTotalPagado() {
		return totalPagado;
	}

	public void setTotalPagado(double precioTotal) {
		this.totalPagado = precioTotal;
	}

	public String getPrestacion() {
		return prestacion;
	}

	public void setPrestacion(String prestacion) {
		this.prestacion = prestacion;
	}

	public String getSubprestacion() {
		return subprestacion;
	}

	public void setSubprestacion(String subprestacion) {
		this.subprestacion = subprestacion;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	@Override
	public String toString() {
		return "Pago [idPago=" + idPago + ", usuario=" + usuario + ", fechaCreacion=" + fechaCreacion + ", fechaAcred="
				+ fechaAcred + ", cantidadPagos=" + cantidadPagos + ", totalPagado=" + totalPagado + ", prestacion="
				+ prestacion + ", subprestacion=" + subprestacion + ", referencia=" + referencia + ", archivo="
				+ archivo + ", empresas=" + empresas + "]";
	}



	
}
