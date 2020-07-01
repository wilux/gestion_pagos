package net.neflores.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.neflores.model.Domicilio;
import net.neflores.model.Empleado;
import net.neflores.model.Empresa;
import net.neflores.model.Pago;
import net.neflores.model.Usuario;
import net.neflores.service.IEmpleadosService;
import net.neflores.service.IEmpresasService;
import net.neflores.service.IPagosService;
import net.neflores.service.IUsuarioService;
import net.neflores.util.Utileria;

@Controller
@RequestMapping(value = "/pagos")
public class PagosController {

	// Fecha actual servidor
	Date date = Calendar.getInstance().getTime();
	DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
	String fechaHoy = dateFormat.format(date);

	@Autowired
	private IPagosService servicePagos;

	@Autowired
	private IUsuarioService serviceUsuarios;

	@Autowired
	private IEmpresasService serviceEmpresas;

	@Autowired
	private IEmpleadosService serviceEmpleados;

	@GetMapping(value = "/index")
	public String mostrarPagos(Model model, Pago pago, Authentication auth) {

		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		pago.setUsuario(usuario);
		List<Pago> listaSimple = servicePagos.buscarPorUsuario(usuario.getIdUsuario());
		model.addAttribute("pagos", listaSimple);
		// Busco los datos de las Empresas y los dejo disponibles
		List<Empresa> listaEmpresas = serviceEmpresas.buscarPorUsuario(usuario.getIdUsuario());

		model.addAttribute("empresas", listaEmpresas);

		return "pagos_empleado/listPagos";
	}
	
	@GetMapping(value = "/proveedor/index")
	public String mostrarPagosProveedores(Model model, Pago pago, Authentication auth) {

		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		pago.setUsuario(usuario);
		List<Pago> listaSimple = servicePagos.buscarPorUsuario(usuario.getIdUsuario());
		model.addAttribute("pagos", listaSimple);
		// Busco los datos de las Empresas y los dejo disponibles
		List<Empresa> listaEmpresas = serviceEmpresas.buscarPorUsuario(usuario.getIdUsuario());

		model.addAttribute("empresas", listaEmpresas);

		return "pagos_proveedor/listPagos";
	}

	@GetMapping(value = "/createPagoEmpleado")
	public String crear(Model model, Pago pago, Empresa empresa, BindingResult result, RedirectAttributes attributes,
			Authentication auth) {

		// Indico el usuario con el que estoy trabajando
		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		pago.setUsuario(usuario);
		// Agrego fecha
		pago.setFechaCreacion(fechaHoy);
		// Busco los datos de las Empresas y los dejo disponibles
		List<Empresa> listaEmpresas = serviceEmpresas.buscarPorUsuario(usuario.getIdUsuario());
		model.addAttribute("empresa", listaEmpresas);
		// Busco los datos de los empleados y los dejo disponibles
		List<Empleado> listaEmpleados = serviceEmpleados.buscarPorUsuario(usuario.getIdUsuario());
		model.addAttribute("empleados", listaEmpleados);
		// Busco los datos de los pagos y los dejo disponibles

		return "pagos_empleado/formPagos";
	}
	
	@GetMapping(value = "/createPagoProveedor")
	public String crearPagoProveedor(Model model, Pago pago, Empresa empresa, BindingResult result, RedirectAttributes attributes,
			Authentication auth) {

		// Indico el usuario con el que estoy trabajando
		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		pago.setUsuario(usuario);
		// Agrego fecha
		pago.setFechaCreacion(fechaHoy);
		// Busco los datos de las Empresas y los dejo disponibles
		List<Empresa> listaEmpresas = serviceEmpresas.buscarPorUsuario(usuario.getIdUsuario());
		model.addAttribute("empresa", listaEmpresas);
		// Busco los datos de los empleados y los dejo disponibles
		List<Empleado> listaEmpleados = serviceEmpleados.buscarPorUsuario(usuario.getIdUsuario());
		model.addAttribute("empleados", listaEmpleados);
		// Busco los datos de los pagos y los dejo disponibles

		return "pagos_proveedor/formPagos";
	}

	@PostMapping(value = "/save")
	public String guardar(Model model, Pago pago, Empresa empresa, BindingResult result, RedirectAttributes attributes,
			Authentication auth, @RequestParam String idEmpresa) throws ParseException {

		/**
		 * if (result.hasErrors()) { attributes.addFlashAttribute("msg", "Existen
		 * errores!"); return "pagos/formPagos"; }
		 **/
		// Referenciamos con el usuario
		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);

		pago.setUsuario(usuario);
		// int empresaId = empresa.getIdEmpresa();
		// System.out.println("Empresa ID: "+empresaId);

		// Agrego fecha actual del servidor con formato dd-MM-yyyy
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String fechaCreacion = formatter.format(date);
		pago.setFechaCreacion(fechaCreacion);

		// Limpio
		String cuerpo = "";

		// Sumamos importes
		List<Empleado> listaSimple = serviceEmpleados.buscarPorUsuario(usuario.getIdUsuario());
		double importeTotal = listaSimple.stream().mapToDouble(o -> o.getImporte()).sum();
		pago.setTotalPagado(importeTotal);

		// Calculo cantidad
		int cantidadEmpleados = listaSimple.size();
		pago.setCantidadPagos(cantidadEmpleados);

		// Formateo Cabecera
		String cuenta = String.format("%9s", empresa.getCuenta()).replace(' ', '0');
		String cantidad = String.format("%6s", cantidadEmpleados).replace(' ', '0');
		DecimalFormat format = new DecimalFormat("0.00");
		String aux = format.format(importeTotal).replaceAll("\\,", "");
		String total = String.format("%14s", aux).replace(' ', '0');
		// ------------

		String fecha = String.format("%-149s", fechaHoy);
		// -------------
		// Formateo cuerpo
		//// String tipo = pago.getPrestacion();
		String cuit = String.format("%11s", empresa.getCuit()).replace(' ', '0');
		String fechaAcred = String.format(pago.getFechaAcred()).replaceAll("-", "");
		String prestacion = String.format("%-10s", pago.getPrestacion());
		String subPrestacion = String.format("%-10s", pago.getSubprestacion());
		String nombreEmpresa = String.format("%15s", empresa.getNombreEmpresa());

		// ------------------Armado de archivo ----------------------------------------

		// Genero cabecera
		String cabecera = ("1" + cuenta + cantidad + total + fecha);

		// Genero el cuerpo
		for (Empleado empleado : listaSimple) {

			// formateo el importe
			Double importe = empleado.getImporte();
			String aux2 = format.format(importe).replaceAll("\\,", "");

			// Genero las lineas del cuerpo
			cuerpo = cuerpo
					+ ("2" + "S" + cuit + String.format("%22s", empleado.getCbu()).replace(' ', '0')
							+ String.format("%22s", empleado.getNombre() + " " + empleado.getApellido()) + fechaAcred
							+ prestacion + subPrestacion + String.format("%-20s", "008")
							+ String.format("%10s", aux2).replace(' ', '0') + String.format("%-44s", "P")
							+ nombreEmpresa + "02" + String.format("%11s", empleado.getCuil()).replace(' ', '0'))
					+ "\n";
		}

		// Armamos nombre del archivo
		String nombreFile = fechaHoy + "_" + empresa.getNombreEmpresa() + "_" + "_V4";

		// Lo agregamos a la bd
		pago.setArchivo(nombreFile);

		// Generamos el archivo

		Utileria.crearArchivo(cabecera, cuerpo, nombreFile);

		// Asigno el id de la empresa seleccionada
		empresa.setIdEmpresa(Integer.parseInt(idEmpresa));
		pago.setEmpresas(empresa);

		// Guardamos en BD
		servicePagos.guardar(pago);
		attributes.addFlashAttribute("msg", "Se genero el archivo!");

		return "redirect:/pagos/index";

	}

	@GetMapping("/importe/{id}")
	public String importe(@PathVariable("id") int idEmpleado, Model model) {
		Empleado empleado = serviceEmpleados.buscarPorId(idEmpleado);
		model.addAttribute("empleado", empleado);
		return "pagos_empleado/importe";
	}

	// Empleados

	@PostMapping(value = "/saveImporte")
	public String guardarImporte(Model model, Empleado empleado, BindingResult result, RedirectAttributes attributes,
			Authentication auth) {
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			return "empleados/formEmpleados";
		}
		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		empleado.setUsuario(usuario);
		serviceEmpleados.guardar(empleado);
		attributes.addFlashAttribute("msg", "Los datos de las Empleados fueron guardados!");
		return "redirect:/pagos/create";
	}

	@GetMapping(value = "/createEmpleado")
	public String crearEmpleado(Empleado empleado) {
		return "pagos_empleado/importe";
	}

}
