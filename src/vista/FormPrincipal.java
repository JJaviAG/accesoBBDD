package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LibrosController;
import excepciones.CamposVaciosException;
import excepciones.IsbnException;
import modelo.Libro;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormPrincipal extends JFrame {

	private JPanel panel;
	LibrosController biblioteca;
	List<Libro> libros;
	int puntero = 0;
	private JLabel lblidLibro, lblTitulo;
	private JLabel lblAutor;
	private JLabel lblEditorial;
	private JLabel lblPrestado;
	private JLabel lblFechaPrestamo;
	private JLabel lblFechaDevoluicion;
	private JLabel lblIsbn;
	private JLabel lblNewLabel;
	private JTextField textFieldIdlibro;
	private JTextField textFieldTitulo;
	private JTextField textFieldAutor;
	private JTextField textFieldEditorial;
	private JTextField textFieldFechaPrestamo;
	private JTextField textFieldFechaDevolucion;
	private JTextField textFieldIsbn;
	private JTextField textFieldFechaAlta;
	private JCheckBox chckbxNewCheckBoxPrestado;
	private JButton btnPrimero;
	private JButton btnAdelante;
	private JButton btnAtras, btnUltimo;
	private JButton btnNuevo, btnEditar, btnBorrar, btnGuardar;
	private JButton btnDeshacer;
	boolean botonControl = true;

	public FormPrincipal() throws ClassNotFoundException, SQLException, CamposVaciosException, IsbnException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 411);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);

		biblioteca = new LibrosController();
		biblioteca.abrirConexion();
		libros = biblioteca.getConsultaLibrosPst("select * from libros order by titulo");
		mostrarLibros(libros);
		biblioteca.cerrarConexion();
		definirVentana();
		deshabilitarCampos(false);
		deshabilitarBotonesMantenimiento(false);
		mostrarLibro(libros.get(puntero));
		definirEventos();
		setVisible(true);
	}

	private void deshabilitarBotonesMantenimiento(boolean b) {
		// TODO Auto-generated method stub
		btnNuevo.setEnabled(!b);
		btnEditar.setEnabled(!b);
		btnBorrar.setEnabled(!b);
		btnDeshacer.setEnabled(b);
		btnGuardar.setEnabled(b);

	}

	private void deshabilitarCampos(boolean b) {
		// TODO Auto-generated method stub
		textFieldIdlibro.setEditable(b);
		textFieldTitulo.setEditable(b);
		textFieldAutor.setEditable(b);
		textFieldEditorial.setEditable(b);
		chckbxNewCheckBoxPrestado.setEnabled(b);
		textFieldFechaPrestamo.setEditable(b);
		textFieldFechaDevolucion.setEditable(b);
		textFieldIsbn.setEditable(b);
		textFieldFechaAlta.setEditable(b);

	}

	private void mostrarLibro(Libro libro) {
		// TODO Auto-generated method stub
		textFieldIdlibro.setText("" + libro.getIdlibro());
		textFieldTitulo.setText(libro.getTitulo());
		textFieldAutor.setText(libro.getAutor());
		textFieldEditorial.setText(libro.getEditorial());
		if (libro.isPestado()) {
			chckbxNewCheckBoxPrestado.setSelected(true);
		} else
			chckbxNewCheckBoxPrestado.setSelected(false);
		textFieldFechaPrestamo.setText("" + libro.getFechaPrestamo());
		textFieldFechaDevolucion.setText("" + libro.getFechaDevolucion());
		textFieldIsbn.setText(libro.getIsbn());
		textFieldFechaAlta.setText("" + libro.getFechaAlta());
	}

	private void borrarCamposLibros() {
		// TODO Auto-generated method stub
		textFieldIdlibro.setText("");
		textFieldTitulo.setText("");
		textFieldAutor.setText("");
		textFieldEditorial.setText("");
		chckbxNewCheckBoxPrestado.setText("");
		textFieldFechaPrestamo.setText("");
		textFieldFechaDevolucion.setText("");
		textFieldIsbn.setText("");
		textFieldFechaAlta.setText("");

	}

	private void camposParaAgregar() {
		// TODO Auto-generated method stub
		textFieldIdlibro.setEditable(false);
		textFieldTitulo.setEditable(true);
		textFieldAutor.setEditable(true);
		textFieldEditorial.setEditable(true);
		chckbxNewCheckBoxPrestado.setEnabled(false);
		textFieldFechaPrestamo.setEditable(false);
		textFieldFechaDevolucion.setEditable(false);
		textFieldIsbn.setEditable(true);
		textFieldFechaAlta.setEditable(false);
	}

	private void deshabilitartextfield(boolean b) {
		// TODO Auto-generated method stub
		textFieldIdlibro.setEditable(b);
		textFieldTitulo.setEditable(b);
		textFieldAutor.setEditable(b);
		textFieldEditorial.setEditable(b);
		chckbxNewCheckBoxPrestado.setEnabled(b);
		textFieldFechaPrestamo.setEditable(b);
		textFieldFechaDevolucion.setEditable(b);
		textFieldIsbn.setEditable(b);
		textFieldFechaAlta.setEditable(b);
	}

	private void habilitarNavegacion(boolean b) {
		// TODO Auto-generated method stub
		btnPrimero.setEnabled(b);
		btnAdelante.setEnabled(b);
		btnAtras.setEnabled(b);
		btnUltimo.setEnabled(b);
	}

	private void habilitarCamposEditar(boolean b) {
		// TODO Auto-generated method stub
		textFieldFechaPrestamo.setEditable(b);
		chckbxNewCheckBoxPrestado.setEnabled(b);
		textFieldFechaDevolucion.setEditable(b);
	}

	private void definirEventos() {
		// TODO Auto-generated method stub
		btnPrimero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				puntero = 0;
				mostrarLibro(libros.get(puntero));

			}
		});
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				puntero = libros.size() - 1;
				mostrarLibro(libros.get(puntero));
			}
		});
		btnAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (puntero < libros.size() - 1) {
					puntero++;
					mostrarLibro(libros.get(puntero));
				}
			}
		});
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (puntero > 0) {
					puntero--;
					mostrarLibro(libros.get(puntero));
				}
			}
		});
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deshabilitarBotonesMantenimiento(true);
				borrarCamposLibros();
				deshabilitarCampos(true);
				camposParaAgregar();
				habilitarNavegacion(false);
			}

		});
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonControl = false;
				deshabilitarBotonesMantenimiento(true);
				habilitarNavegacion(false);
				habilitarCamposEditar(true);

			}

		});
		btnDeshacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deshabilitarBotonesMantenimiento(false);
				mostrarLibro(libros.get(puntero));
				deshabilitartextfield(false);
				habilitarNavegacion(true);

			}

		});
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deshabilitarBotonesMantenimiento(false);

				LibrosController libcontrol = null;

				if (botonControl) {
					String titulo = textFieldTitulo.getText();
					String autor = textFieldAutor.getText();
					String editorial = textFieldEditorial.getText();
					String isbn = textFieldIsbn.getText();
					boolean prestado = false;

					try {
						Libro libro = new Libro(titulo, autor, editorial, isbn, prestado);
						libcontrol = new LibrosController();
						libcontrol.abrirConexion();
						libcontrol.agregarLibroPst(libro);

						libros = libcontrol.getConsultaLibrosPst("select * from libros");
						puntero = libros.size() - 1;
						mostrarLibro(libros.get(puntero));
						deshabilitarCampos(false);
						deshabilitarBotonesMantenimiento(false);
						habilitarNavegacion(true);
						libcontrol.cerrarConexion();
						libcontrol = null;
						JOptionPane.showMessageDialog(null, "libro agregado con exito");
					} catch (ClassNotFoundException | SQLException | CamposVaciosException | IsbnException
							| NumberFormatException e2) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "comprueba que introduces bien los datos");
					}

				} else {
					boolean prestado;
					Libro libro = null;
					
					String titulo = textFieldTitulo.getText();
					String autor = textFieldAutor.getText();
					String editorial = textFieldEditorial.getText();
					String isbn = textFieldIsbn.getText();
					String fechaAlta=textFieldFechaAlta.getText();
					String fechaPrestamo = textFieldFechaPrestamo.getText();
					String fechaDevolucion = textFieldFechaDevolucion.getText();
					
					if (chckbxNewCheckBoxPrestado.isSelected()) {
						prestado = true;
					} else
						prestado = false;

					try {
						
						SimpleDateFormat formatedador = new SimpleDateFormat("yyyy-MM-dd");
						formatedador.setLenient(false);
						java.sql.Date fechaPrestamoFinal;
						java.sql.Date fechaDevolucionFinal = null;
						java.sql.Timestamp fechaAltaFinal;
						java.util.Date fecha=null;
						//fecha prestamo
						fecha=formatedador.parse(fechaPrestamo);
						fechaPrestamoFinal=new Date(fecha.getTime());
						//fecha devolucion
						fecha=formatedador.parse(fechaDevolucion);
						fechaDevolucionFinal=new Date(fecha.getTime());
						//fecha alta timestamp
						fecha=formatedador.parse(fechaAlta);
						fechaAltaFinal=new java.sql.Timestamp(fecha.getTime());
						
						
						//fecha=formatedador.parse(fechaPrestamo);
						
						libcontrol=new LibrosController();
						libcontrol.abrirConexion();
						libro=new Libro(titulo, autor, editorial, isbn, prestado, fechaPrestamoFinal, fechaDevolucionFinal, fechaAltaFinal);
						System.out.println(libro);
						libcontrol.prestarLibroPst(libro);
						libros=libcontrol.getConsultaLibrosPst("select * from libros");
						
						deshabilitarCampos(false);
						deshabilitarBotonesMantenimiento(false);
						habilitarNavegacion(true);
						libcontrol.cerrarConexion();
						libcontrol = null;
					} catch (SQLException | CamposVaciosException | IsbnException | ClassNotFoundException | ParseException  e1) {
						// TODO Auto-generated catch block
						
						JOptionPane.showMessageDialog(null,"COMPRUEBA QUE TIENES LO DATOS BIEN INTRODUCIDOS" );
						deshabilitarBotonesMantenimiento(false);
						
						deshabilitartextfield(false);
						habilitarNavegacion(true);
						
					}
					
				}
				botonControl=true;

			}
		});
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccion = JOptionPane.showOptionDialog(null, "estas seguro de que quieres borrar el registro",
						"¿borrar registro?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null
																													// para
																													// icono
																													// por//
																													// defecto.
						new Object[] { "SI", "NO" }, null);// null para YES, NO y CANCEL

			}
		});
	}

	private void definirVentana() {
		// TODO Auto-generated method stub
		lblidLibro = new JLabel("idLibro");
		lblidLibro.setBounds(10, 40, 46, 18);
		panel.add(lblidLibro);

		lblTitulo = new JLabel("titulo");
		lblTitulo.setBounds(10, 68, 45, 13);
		panel.add(lblTitulo);

		lblAutor = new JLabel("Autor");
		lblAutor.setBounds(10, 91, 45, 13);
		panel.add(lblAutor);

		lblEditorial = new JLabel("Editorial");
		lblEditorial.setBounds(10, 114, 69, 13);
		panel.add(lblEditorial);

		lblPrestado = new JLabel("Prestado");
		lblPrestado.setBounds(10, 229, 69, 13);
		panel.add(lblPrestado);

		lblFechaPrestamo = new JLabel("Fecha Prestamo");
		lblFechaPrestamo.setBounds(10, 137, 81, 13);
		panel.add(lblFechaPrestamo);

		lblFechaDevoluicion = new JLabel("Fecha Devolucion");
		lblFechaDevoluicion.setBounds(10, 160, 81, 13);
		panel.add(lblFechaDevoluicion);

		lblIsbn = new JLabel("Isbn");
		lblIsbn.setBounds(10, 183, 45, 13);
		panel.add(lblIsbn);

		lblNewLabel = new JLabel("Fecha Alta");
		lblNewLabel.setBounds(10, 206, 69, 13);
		panel.add(lblNewLabel);

		textFieldIdlibro = new JTextField();
		textFieldIdlibro.setBounds(101, 40, 108, 19);
		panel.add(textFieldIdlibro);
		textFieldIdlibro.setColumns(10);

		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(101, 65, 108, 19);
		panel.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);

		textFieldAutor = new JTextField();
		textFieldAutor.setBounds(101, 88, 137, 19);
		panel.add(textFieldAutor);
		textFieldAutor.setColumns(10);

		textFieldEditorial = new JTextField();
		textFieldEditorial.setBounds(101, 111, 108, 19);
		panel.add(textFieldEditorial);
		textFieldEditorial.setColumns(10);

		chckbxNewCheckBoxPrestado = new JCheckBox("Prestado");
		chckbxNewCheckBoxPrestado.setBounds(101, 229, 93, 21);
		panel.add(chckbxNewCheckBoxPrestado);

		textFieldFechaPrestamo = new JTextField();
		textFieldFechaPrestamo.setBounds(101, 137, 108, 19);
		panel.add(textFieldFechaPrestamo);
		textFieldFechaPrestamo.setColumns(10);

		textFieldFechaDevolucion = new JTextField();
		textFieldFechaDevolucion.setBounds(101, 160, 108, 19);
		panel.add(textFieldFechaDevolucion);
		textFieldFechaDevolucion.setColumns(10);

		textFieldIsbn = new JTextField();
		textFieldIsbn.setBounds(101, 183, 108, 19);
		panel.add(textFieldIsbn);
		textFieldIsbn.setColumns(10);

		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setBounds(101, 203, 108, 19);
		panel.add(textFieldFechaAlta);
		textFieldFechaAlta.setColumns(10);

		btnPrimero = new JButton("Primero");

		btnPrimero.setBounds(10, 276, 85, 21);
		panel.add(btnPrimero);

		btnAdelante = new JButton("Adelante");

		btnAdelante.setBounds(196, 276, 85, 21);
		panel.add(btnAdelante);

		btnAtras = new JButton("Atras");

		btnAtras.setBounds(101, 276, 85, 21);
		panel.add(btnAtras);

		btnUltimo = new JButton("Ultimo");

		btnUltimo.setBounds(291, 276, 85, 21);
		panel.add(btnUltimo);

		btnNuevo = new JButton("Nuevo");

		btnNuevo.setBounds(6, 9, 85, 21);
		panel.add(btnNuevo);

		btnEditar = new JButton("Editar");

		btnEditar.setBounds(101, 9, 85, 21);
		panel.add(btnEditar);

		btnBorrar = new JButton("Borrar");

		btnBorrar.setBounds(196, 9, 85, 21);
		panel.add(btnBorrar);

		btnGuardar = new JButton("Guardar");

		btnGuardar.setBounds(409, 9, 85, 21);
		panel.add(btnGuardar);

		btnDeshacer = new JButton("Deshacer");

		btnDeshacer.setBounds(291, 9, 108, 21);
		panel.add(btnDeshacer);

	}

	private void mostrarLibros(List<Libro> libros) {
		// TODO Auto-generated method stub
		for (Libro libro : libros) {
			System.out.println(libro.toString());
		}
	}
}
