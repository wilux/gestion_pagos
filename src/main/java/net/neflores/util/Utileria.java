package net.neflores.util;

import java.io.PrintWriter;

public class Utileria {
	
	private static String RUTA = "C:\\Users\\wilux\\Documents\\MEGAsync\\bpn_gestion\\src\\main\\resources\\templates\\tmp\\";
	
	private static String NOMBRE;
	
	public static void crearArchivo(String cabecera, String cuerpo, String nombreFile) {
		
		NOMBRE = nombreFile;

		
        try {
           	     	
           //Linux
        	// PrintWriter writer = new PrintWriter("/home/wilux/Documents/workspace-spring-tool-suite-4-4.1.0.RELEASE/empleos/tmp/filename.txt", "UTF-8");
        	
        	//Windows
        	PrintWriter writer = new PrintWriter(RUTA+nombreFile+".txt", "UTF-8");
        	writer.println(cabecera);
            writer.println(cuerpo);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
	
        }
	}
	
	



	public static String getRUTA() {
		return RUTA;
	}





	public static void setRUTA(String rUTA) {
		RUTA = rUTA;
	}


	public static String getNombre() {
		return NOMBRE;
	}


}
	 

    

