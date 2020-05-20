package vista;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import controlador.LibrosController;
import excepciones.CamposVaciosException;
import excepciones.IsbnException;
import modelo.Libro;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String titulo = "don quijote", autor = "antonio", editorial = "planeta", isbn = "978-84-20400-21-1";
//		boolean prestado = false;
//		String fechaString = "15/06/2020";
//		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
//		formateador.setLenient(false);
//		try {
//			java.util.Date fecha = formateador.parse(fechaString);
//			java.sql.Date fechaPrestamo = new java.sql.Date(fecha.getTime());
//			java.sql.Date fechaDevolucion = new java.sql.Date(fecha.getTime());
//			java.sql.Timestamp fechaAlta = new java.sql.Timestamp(fecha.getTime());
//			Libro lib = new Libro(titulo, autor, editorial, isbn, prestado, fechaPrestamo, fechaDevolucion, fechaAlta);
//			System.out.println(lib);
//		} catch (ParseException | CamposVaciosException | IsbnException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		LibrosController libcontrol = null;
//		try {
//			libcontrol = new LibrosController();// tenemos la tabla libros
//			List<Libro> biblio = libcontrol.getLibros();
//			mostrarLibros(biblio);
//			libcontrol.cerrarConexion();
//
//		} catch (ClassNotFoundException | SQLException | CamposVaciosException | IsbnException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// agregar un libro
//		try {
//			libcontrol.abrirConexion();
//		} catch (ClassNotFoundException | SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		String titul = "la vuelta al mundo de la hormiga miga";
//		String aut = "emili teixidor";
//		String edit = "circulo de lectores";
//		String isb = "6805978809730";
//		boolean prestad = false;
//		Libro li;
//		try {
//			li = new Libro(titul, aut, edit, isb, prestad);
//			if (libcontrol.agregarLibroPst(li)) {
//				System.out.println("libro agregado");
//
//			} else
//				System.out.println("libro no agregado");
//		} catch (IsbnException | CamposVaciosException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// -----------fin---agregar libro.------------------
//
//		ArrayList<Libro> listaBuscar;
//		try {
//
//			listaBuscar = libcontrol.burcarLibroPst("autor", "Perez Reverte;Arturo");
//			System.out.println("busqueda por autor =Perez Reverte;Arturo");
//			mostrarLibros(listaBuscar);
//		} catch (SQLException | CamposVaciosException | IsbnException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 //--borrar registro
//		Libro libroBorrar;
//		try {
//
//			libroBorrar = new Libro("la vuelta al mundo de la hormiga miga", "emili teixidor", "circulo de lectores",
//					"6805978809730", false);
//			int filas = libcontrol.borrarLibroPst(libroBorrar);
//			System.out.println(filas + " registros borrados");
//		} catch (IsbnException | CamposVaciosException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// --fin borrar registro
//		
//		try {
//			int rows=0;
//			Libro libroPres = new Libro(titul, aut, edit, isb, prestad);
//			rows=libcontrol.prestarLibroPst(libroPres);
//			System.out.println(rows+" filas afectadas");
//		} catch (IsbnException | CamposVaciosException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		};
//		
//		
//		
//	}
//
//	private static void mostrarLibros(List<Libro> biblio) {
//		// TODO Auto-generated method stub
//		for (Libro libro : biblio) {
//			System.out.println(libro.toString());
//		}
		try {
			FormPrincipal formprin=new FormPrincipal();
		} catch (ClassNotFoundException | SQLException | CamposVaciosException | IsbnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
