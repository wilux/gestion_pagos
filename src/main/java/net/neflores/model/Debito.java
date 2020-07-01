package net.neflores.model;

public class Debito {
	
	private int idDebito;
	private String cbu;
	private String nombreCliente;
	private String importe1;
	private String importe2;
	private String importe3;
	private Empresa empresa;
	
	public int getIdDebito() {
		return idDebito;
	}
	public void setIdDebito(int idDebito) {
		this.idDebito = idDebito;
	}
	public String getCbu() {
		return cbu;
	}
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getImporte1() {
		return importe1;
	}
	public void setImporte1(String importe1) {
		this.importe1 = importe1;
	}
	public String getImporte2() {
		return importe2;
	}
	public void setImporte2(String importe2) {
		this.importe2 = importe2;
	}
	public String getImporte3() {
		return importe3;
	}
	public void setImporte3(String importe3) {
		this.importe3 = importe3;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
}
