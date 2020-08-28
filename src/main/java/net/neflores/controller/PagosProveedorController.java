package net.neflores.controller;

import java.text.ParseException;
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
import net.neflores.model.Empresa;
import net.neflores.model.Pago;
import net.neflores.model.Proveedor;
import net.neflores.model.Usuario;
import net.neflores.service.IEmpresasService;
import net.neflores.service.IPagosService;
import net.neflores.service.IProveedoresService;
import net.neflores.service.IUsuarioService;
import net.neflores.util.NachaPago;
import net.neflores.util.EscribirArchivo;

@Controller
@RequestMapping(value = "/pagos/proveedor")
public class PagosProveedorController {

	//Fecha
	NachaPago fecha = new NachaPago();

	@Autowired
	private IPagosService servicePagos;

	@Autowired
	private IUsuarioService serviceUsuarios;

	@Autowired
	private IEmpresasService serviceEmpresas;

	@Autowired
	private IProveedoresService serviceProveedor;
	
	@GetMapping(value = "/index")
	public String mostrarPagosProveedores(Model model, Pago pago, Authentication auth) {

		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		pago.setUsuario(usuario);
		List<Pago> listaSimple = servicePagos.buscarPorUsuarioyPrestacion(usuario.getIdUsuario(), "PROVEEDOR");
		model.addAttribute("pagos", listaSimple);
		// Busco los datos de las Empresas y los dejo disponibles
		List<Empresa> listaEmpresas = serviceEmpresas.buscarPorUsuario(usuario.getIdUsuario());
		
		model.addAttribute("empresas", listaEmpresas);

		return "pagos_proveedor/listPagos";
	}

	@GetMapping(value = "/createPagoProveedor")
	public String crear(Model model, Pago pago, Empresa empresa, BindingResult result, RedirectAttributes attributes,
			Authentication auth) {

		// Indico el usuario con el que estoy trabajando
		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		pago.setUsuario(usuario);
		// Agrego fecha

		pago.setFechaCreacion(fecha.fechaHoy());
		// Busco los datos de las Empresas y los dejo disponibles
		List<Empresa> listaEmpresas = serviceEmpresas.buscarPorUsuario(usuario.getIdUsuario());
		model.addAttribute("empresa", listaEmpresas);
		// Busco los datos de los empleados y los dejo disponibles
		List<Proveedor> listaProveedores = serviceProveedor.buscarPorUsuario(usuario.getIdUsuario());
		model.addAttribute("proveedor", listaProveedores);
		// Busco los datos de los pagos y los dejo disponibles

		return "pagos_proveedor/formPagos";
	}
	
	@GetMapping(value = "/createProveedor")
	public String crearProveedor(Proveedor proveedor) {
		return "pagos_proveedor/importe";
	}
	
	@PostMapping(value = "/saveImporte")
	public String guardarImporte(Model model, Proveedor proveedor, BindingResult result, RedirectAttributes attributes,
			Authentication auth) {
		if (result.hasErrors()) {
			return "proveedores/formProveedor";
		}
		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		proveedor.setUsuario(usuario);
		proveedor.setStatus(1);
		serviceProveedor.guardar(proveedor);
		attributes.addFlashAttribute("msg", "Los datos del Proveedor fueron guardados!");
		return "redirect:/pagos/proveedor/createPagoProveedor";
	}
	
	
	@GetMapping("/importe/{id}")
	public String importe(@PathVariable("id") int idProveedor, Model model) {
		Proveedor proveedor = serviceProveedor.buscarPorId(idProveedor);
		model.addAttribute("proveedor", proveedor);
		return "pagos_proveedor/importe";
	}
	

	
	//-----------------------------------------------------
	// Guardar y Generar archivo txt
	//-----------------------------------------------------
	
	@PostMapping(value = "/save")
	public String guardar(Pago pago, BindingResult result, RedirectAttributes attributes,
			Authentication auth, @RequestParam Integer idEmpresa) throws ParseException {


	if (result.hasErrors()) { attributes.addFlashAttribute("msg", "Existen errores!"); 
		return "pagos_proveedor/formPagos"; 
		
	}

		// Referenciamos con el usuario
		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		pago.setUsuario(usuario);

		// Agrego fecha actual del servidor con formato dd-MM-yyyy
		pago.setFechaCreacion(fecha.fechaCreacion());
		
		// Sumamos importes
		List<Proveedor> listaSimple = serviceProveedor.buscarPorUsuario(usuario.getIdUsuario());
		double importeTotal = listaSimple.stream().mapToDouble(o -> o.getImporte()).sum();
		pago.setTotalPagado(importeTotal);

		// Calculo cantidad
		int cantidadEmpleados = listaSimple.size();
		pago.setCantidadPagos(cantidadEmpleados); 
		
		Empresa empresa = serviceEmpresas.buscarPorId(idEmpresa);
		
		// Asigno el id de la empresa seleccionada al pago
		empresa.setIdEmpresa(idEmpresa);
		pago.setEmpresas(empresa);
		
		// ------------------Armado de archivo ----------------------------------------

		NachaPago nacha = new NachaPago();
		String cabecera = nacha.cabecera(empresa.getCuenta(), cantidadEmpleados,importeTotal);
		String cuerpo =  nacha.cuerpoProveedor(empresa.getCuit(), pago.getFechaAcred(), pago.getPrestacion(), pago.getSubprestacion(), empresa.getNombreEmpresa(), listaSimple);
		
		
		// Nombre del archivo
		String nombreFile = fecha.fechaHoy() + "_" + empresa.getNombreEmpresa() + "_" + "_V4";

		// Agregamos nombre a la bd
		pago.setArchivo(nombreFile);

		// Escribimos el archivo
		EscribirArchivo.crearArchivo(cabecera, cuerpo, nombreFile);

		// Guardamos en BD
		servicePagos.guardar(pago);
		attributes.addFlashAttribute("msg", "Se genero el archivo!");

		return "redirect:/pagos/proveedor/index";

	}
	
}
