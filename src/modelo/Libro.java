package modelo;

import java.security.Timestamp;
import java.util.Date;

import excepciones.CamposVaciosException;
import excepciones.IsbnException;

public class Libro {
	private int idlibro;
	private String titulo, autor, editorial, isbn;
	private boolean prestado;
	private java.util.Date fechaPrestamo,fechaDevolucion;
	private java.sql.Timestamp fechaAlta;
	public Libro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Libro(String titulo, String autor, String editorial, String isbn, boolean prestado) throws IsbnException, CamposVaciosException {
		super();
		this.setTitulo(titulo);
		this.setAutor(autor);
		this.setEditorial(editorial);
		this.setIsbn(isbn);
		this.setPrestado(prestado);
	}

	

	public Libro(String titulo, String autor, String editorial, String isbn, boolean prestado, Date fechaPrestamo,
			Date fechaDevolucion, java.sql.Timestamp fechaAlta) throws CamposVaciosException, IsbnException {
		super();
		this.setTitulo(titulo);
		this.setAutor(autor);
		this.setEditorial(editorial);
		this.setIsbn(isbn);
		this.setPrestado(prestado);
		this.setFechaPrestamo(fechaPrestamo);
		this.setFechaDevolucion(fechaDevolucion);
		this.setFechaAlta(fechaAlta);
	}

	

	public Libro(int idlibro, String titulo, String autor, String editorial, String isbn, boolean prestado,
			Date fechaPrestamo, Date fechaDevolucion, java.sql.Timestamp fechaAlta) throws CamposVaciosException, IsbnException {
		super();
		this.setIdlibro(idlibro);
		this.setTitulo(titulo);
		this.setAutor(autor);
		this.setEditorial(editorial);
		this.setIsbn(isbn);
		this.setPrestado(prestado);
		this.setFechaPrestamo(fechaPrestamo);
		this.setFechaDevolucion(fechaDevolucion);
		this.setFechaAlta(fechaAlta);
	}

	public int getIdlibro() {
		return idlibro;
	}

	public void setIdlibro(int idlibro) {
		this.idlibro = idlibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) throws CamposVaciosException {
		if (titulo.equals("") | titulo.isEmpty()) {
			throw new CamposVaciosException();
		}
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) throws CamposVaciosException {
		if (titulo.equals("") | titulo.isEmpty()) {
			throw new CamposVaciosException();
		}
		this.editorial = editorial;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) throws IsbnException, CamposVaciosException {
		if (titulo.equals("") | titulo.isEmpty()) {
			throw new CamposVaciosException();
		}
		if (!compruebaIsbn(isbn)) {
			throw new IsbnException();
		}
		this.isbn = isbn;
	}

	private boolean compruebaIsbn(String isbn) throws IsbnException {
		// TODO Auto-generated method stub
		boolean valido = true;
		String cuenta = isbn.replaceAll("-", "");
		int acu = 0;
		int resto = 0;
		int dc = 0;
		try {
			dc = Integer.parseInt(Character.toString(cuenta.charAt(cuenta.length() - 1)));
		} catch (StringIndexOutOfBoundsException e) {
			// TODO: handle exception
			return false;
		}

		for (int i = 0; i < cuenta.length() - 1; i++) {

			if (i % 2 == 0) {
				acu += Integer.parseInt(Character.toString(cuenta.charAt(i))) * 1;
			} else
				acu += Integer.parseInt(Character.toString(cuenta.charAt(i))) * 3;

		}

		resto = acu % 10;
		resto = 10 - resto;
		if (resto == 10) {
			resto = 0;
		}
		if (resto != dc) {
			throw new IsbnException();
		}
		return valido;
	}

	public boolean isPestado() {
		return prestado;
	}

	public void setPrestado(boolean pestado) {
		this.prestado = pestado;
	}

	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public java.sql.Timestamp getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(java.sql.Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Libro [idlibro=" + idlibro + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial
				+ ", isbn=" + isbn + ", pestado=" + prestado + ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolucion="
				+ fechaDevolucion + ", fechaAlta=" + fechaAlta + "]";
	}

	

}
