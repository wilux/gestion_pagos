package net.neflores.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Empleado")
public class Empleado {
	
	@Id//mapeo con llave primaria
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Genera autroincrementables
	private Integer idEmpleado;
	private String nombre;
	private String apellido;
	private String tipoDocumento;
	private String dni;
	private String cuil;
	private String sexo;
	private String estadoCivil;
	private String fechaNacimiento;
	private String paisNacimiento;
	private String provinciaNacimiento;
	private String cbu;
	private double importe;
	
  //	@OneToOne
  	//@JoinColumn(name="idDomicilio")
//	private Domicilio  domicilio;
	
   
  	@OneToOne
  	@JoinColumn(name="idUsuario")
    private Usuario usuario;
	

	@OneToOne
  	@JoinColumn(name="idEmpresa")
    private Empresa empresa;

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	


	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPaisNacimiento() {
		return paisNacimiento;
	}

	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}

	public String getProvinciaNacimiento() {
		return provinciaNacimiento;
	}

	public void setProvinciaNacimiento(String provinciaNacimiento) {
		this.provinciaNacimiento = provinciaNacimiento;
	}






}
