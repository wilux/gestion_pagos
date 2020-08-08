package net.neflores.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.neflores.model.Empleado;
import net.neflores.model.Proveedor;



public class NachaPago {

	//Constructor Vacio
	public NachaPago() {

	}

	// Fecha actual servidor
	Date date = Calendar.getInstance().getTime();
	DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

	// Fecha creacion
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	public String fechaHoy() {
		String fechaHoy = dateFormat.format(date);

		return fechaHoy;

	}

	public String fechaCreacion() {

		String fechaCreacion = formatter.format(date);

		return fechaCreacion;

	}
	

	public String cabecera(String cuenta, int cantidadEmpleados, double importeTotal) {
		String cabecera;
		String fecha_cabecera = String.format("%-149s", fechaHoy());
		cuenta = String.format("%9s", cuenta).replace(' ', '0');
		var cantidad = String.valueOf(cantidadEmpleados); 
		cantidad = String.format("%6s", cantidadEmpleados).replace(' ', '0');
		DecimalFormat format = new DecimalFormat("0.00");
		var aux = format.format(importeTotal).replaceAll("\\,", "");
		var total = String.format("%14s", aux).replace(' ', '0');
		cabecera = ("1" + cuenta + cantidad + total + fecha_cabecera);
		
		return cabecera;
	}
	
	
	public  String cuerpo(String cuit, String fechaAcred, String prestacion, String subPrestacion, String nombreEmpresa, List<Empleado> listaSimple) {
		String cuerpo = "";
		cuit = String.format("%11s", cuit).replace(' ', '0');
		fechaAcred = String.format(fechaAcred).replaceAll("-", "");
		prestacion = String.format("%-10s", prestacion);
		subPrestacion = String.format("%-10s", subPrestacion);
		nombreEmpresa = String.format("%15s", nombreEmpresa);

		for (Empleado empleado : listaSimple) {

			// formateo el importe
			Double importe = empleado.getImporte();
			DecimalFormat format = new DecimalFormat("0.00");
			var aux = format.format(importe).replaceAll("\\,", "");

			// Genero las lineas del cuerpo
			cuerpo = cuerpo
					+ ("2" + "S" + cuit + String.format("%22s", empleado.getCbu()).replace(' ', '0')
							+ String.format("%22s", empleado.getNombre() + " " + empleado.getApellido()) + fechaAcred
							+ prestacion + subPrestacion + String.format("%-20s", "008")
							+ String.format("%10s", aux).replace(' ', '0') + String.format("%-44s", "P")
							+ nombreEmpresa + "02" + String.format("%11s", empleado.getCuil()).replace(' ', '0'))
					+ "\n";
		}

		
		return cuerpo;
	}
	
	
	
	public  String cuerpoProveedor(String cuit, String fechaAcred, String prestacion, String subPrestacion, String nombreEmpresa, List<Proveedor> listaSimple) {
		String cuerpo = "";
		cuit = String.format("%11s", cuit).replace(' ', '0');
		fechaAcred = String.format(fechaAcred).replaceAll("-", "");
		prestacion = String.format("%-10s", prestacion);
		subPrestacion = String.format("%-10s", subPrestacion);
		nombreEmpresa = String.format("%15s", nombreEmpresa);

		for (Proveedor proveedor : listaSimple) {

			// formateo el importe
			Double importe = proveedor.getImporte();
			DecimalFormat format = new DecimalFormat("0.00");
			var aux = format.format(importe).replaceAll("\\,", "");

			// Genero las lineas del cuerpo
			cuerpo = cuerpo
					+ ("2" + "S" + cuit + String.format("%22s", proveedor.getCbu()).replace(' ', '0')
							+ String.format("%22s", proveedor.getNombre() + " "  + fechaAcred
							+ prestacion + subPrestacion + String.format("%-20s", "008")
							+ String.format("%10s", aux).replace(' ', '0') + String.format("%-44s", "P")
							+ nombreEmpresa + "02" + String.format("%11s", proveedor.getCuit()).replace(' ', '0'))
					+ "\n");
		}

		
		return cuerpo;
	}



}
