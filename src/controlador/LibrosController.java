package controlador;

import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import excepciones.CamposVaciosException;
import excepciones.IsbnException;
import modelo.Libro;

public class LibrosController {
	private final static String drv = "com.mysql.jdbc.Driver";
	private final static String db = "jdbc:mysql://localhost:3306/biblioteca?useSSL=false";
	private final static String user = "root";
	private final static String pass = "";
	private Connection cn;// importamos la libreria

	private Statement st;// para realizar las consltas
	private ResultSet rs;//cursor donde se guardan las consultas
	private List<Libro> libros;
	private PreparedStatement pst;

	public LibrosController() throws ClassNotFoundException, SQLException, CamposVaciosException, IsbnException {
		super();
		//abrirConexion();
		//libros = getConsultaLibrosPst("select * from libros order by titulo");
		// TODO Auto-generated constructor stub
	}

	public List<Libro> getConsultaLibrosPst(String consulta)
			throws SQLException, CamposVaciosException, IsbnException {
		// TODO Auto-generated method stub
		ArrayList<Libro> lista = null;
		pst = cn.prepareStatement(consulta);
		rs = pst.executeQuery();
		rs.last();// me voy a la ultima fila
		int tam = rs.getRow();// esto devuelve el numero de la fila
		rs.beforeFirst();// volvemos al principio antes de la primera fila
		if (tam > 0) {
			lista = new ArrayList<Libro>();
			while (rs.next()) {
				int idlibro = rs.getInt("idlibros");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String editorial = rs.getString("editorial");
				String isbn = rs.getString("isbn");
				boolean prestado = rs.getBoolean("prestado");
				java.sql.Date fechaPrestamo = rs.getDate("fechaPrestamo");
				java.sql.Date fechaDevolucion = rs.getDate("fechaDevolucion");
				java.sql.Timestamp fechaAlta = rs.getTimestamp("fechaAlta");
				Libro libro = new Libro(idlibro, titulo, autor, editorial, isbn, prestado, fechaPrestamo,
						fechaDevolucion, fechaAlta);
				lista.add(libro);
				libro = null;

			}

		}
		return lista;
	}

	public void abrirConexion() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName(drv);
		cn = DriverManager.getConnection(db, user, pass);
		System.out.println("la conexion se realizo con exito");

	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public void cerrarConexion() throws SQLException {
		// TODO Auto-generated method stub
		if (rs != null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		if (pst != null) {
			pst.close();
		}
		if (cn != null) {
			cn.close();
		}
		System.out.println("Conexion cerrada");
	}

	public boolean agregarLibroPst(Libro libro) throws SQLException {
		boolean agregado = false;
		String titulo = libro.getTitulo();
		String autor = libro.getAutor();
		String editorial = libro.getEditorial();
		String isbn = libro.getIsbn();
		boolean prestado = libro.isPestado();
		String sql = "insert into libros values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparestatement = cn.prepareStatement(sql);
		java.util.Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		// tenemos q decir qe son cada una de las interrogaciones(si no las hubiera nos
		// saltariamos este paso)
		preparestatement.setInt(1, 0);
		preparestatement.setString(2, titulo);
		preparestatement.setString(3, autor);
		preparestatement.setString(4, editorial);
		preparestatement.setBoolean(5, prestado);
		preparestatement.setDate(6, null);
		preparestatement.setDate(7, null);
		preparestatement.setString(8, isbn);
		preparestatement.setTimestamp(9, time);
		preparestatement.executeUpdate();
		preparestatement = null;
		agregado = true;
		// fin comment
		return agregado;

	}

	public ArrayList<Libro> burcarLibroPst(String campo, String cadenaBusqueda)
			throws SQLException, CamposVaciosException, IsbnException {

		ArrayList<Libro> lista = null;
		String sql = "select * from libros where " + campo + " = ?";
		PreparedStatement prepareStatement = cn.prepareStatement(sql);
		prepareStatement.setString(1, cadenaBusqueda);
		rs = prepareStatement.executeQuery();
		rs.last();// me voy a la ultima fila
		int tam = rs.getRow();// esto devuelve el numero de la fila
		rs.beforeFirst();// volvemos al principio antes de la primera fila
		if (tam > 0) {
			lista = new ArrayList<Libro>();
			while (rs.next()) {
				int idlibro = rs.getInt("idlibros");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String editorial = rs.getString("editorial");
				String isbn = rs.getString("isbn");
				boolean prestado = rs.getBoolean("prestado");
				java.sql.Date fechaPrestamo = rs.getDate("fechaPrestamo");
				java.sql.Date fechaDevolucion = rs.getDate("fechaDevolucion");
				java.sql.Timestamp fechaAlta = rs.getTimestamp("fechaAlta");
				Libro libro = new Libro(idlibro, titulo, autor, editorial, isbn, prestado, fechaPrestamo,
						fechaDevolucion, fechaAlta);
				lista.add(libro);
				libro = null;

			}
			

		}
		return lista;
	}

	public int  borrarLibroPst(Libro libroBorrar) throws SQLException {
		// TODO Auto-generated method stub
		int filas=0;
		String campo="isbn";
		String sql="delete from libros where "+campo+" =?";
		PreparedStatement prestatement=cn.prepareStatement(sql);
		prestatement.setString(1,libroBorrar.getIsbn());
		filas=prestatement.executeUpdate();//filas contiene las filas afectadas por ese borrado
		return filas;
	}

	
//meto/***do gerenico de modificaion (en este caso prestado prestamo devolucion )
	public int  prestarLibroPst(Libro libroPres) throws SQLException {
		int rows=0;
		String sql="Update libros set prestado =?, fechaPrestamo =?, fechaDevolucion =? where isbn=?";
		
			PreparedStatement preparedStatement =cn.prepareStatement(sql);//obtenemos la fecha del sistema u calculamos la de devolucion
			/*java.util.Date fecha=new java.util.Date();//fecha del sistema
			Calendar cale=new GregorianCalendar();
			cale.setTime(fecha);//definimos el objeto calendar a partir de un DAte
			cale.add(Calendar.DAY_OF_MONTH,5);//agreagamos 5 dias
			java.util.Date FechaDev=cale.getTime();//construimos a partir de un calendar
			java.sql.Date fechaPrestamo=new java.sql.Date(fecha.getTime());
			java.sql.Date fechaDevolucion =new java.sql.Date(fecha.getTime());*/
			preparedStatement.setBoolean(1,libroPres.isPestado());
			preparedStatement.setDate(2, (java.sql.Date) libroPres.getFechaPrestamo());
			preparedStatement.setDate(3, (java.sql.Date) libroPres.getFechaDevolucion());
			preparedStatement.setString(4, libroPres.getIsbn());
			rows=preparedStatement.executeUpdate();
		return rows;
	
		
	}
}
