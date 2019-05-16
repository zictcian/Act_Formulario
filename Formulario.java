package MedinaFlores;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.Color;
import java.awt.Cursor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Formulario extends JFrame implements ActionListener{
	
	
	static JFrame ventana;
	private DefaultTableModel model;
	private JTable jt;
	private JButton botonAgregar;
	private JButton botonModificar;
	private JButton botonEliminar;
	private JTextField cajaDato;
	private JTextField cajaPrecio;
	private JTextField cajaId;
	private JLabel textDato;
	private JLabel textPrecio;
	private JLabel textId;
	private int contadorId=0; 
	private Formulario i;
	
	
	public Formulario() {
		
		configurarVentana();
		crearComponentes();

	}
	
	private void crearComponentes() {
		
		String data[][] = {};
		String label[] = {"ID", "NOMBRE", "PRECIO"};
		
		
		//JTable jt = new JTable(data,label);
		
		
		
		botonAgregar = new JButton();
		botonAgregar.setText(" Agregar ");
		botonAgregar.setBounds(350,50, 100, 30);
		botonAgregar.addActionListener(this);
		ventana.add(botonAgregar);
		
		
		botonModificar = new JButton();
		botonModificar.setText(" Modificar ");
		botonModificar.setBounds(350,150, 100, 30);
		botonModificar.addActionListener(this);
		botonModificar.setVisible(false);
		ventana.add(botonModificar);
		
		botonEliminar = new JButton();
		botonEliminar.setText(" Eliminar ");
		botonEliminar.setBounds(350,250, 100, 30);
		botonEliminar.addActionListener(this);
		botonEliminar.setVisible(false);
		ventana.add(botonEliminar);
		
		textDato = new JLabel();
		textDato.setText(" Nombre ");
		textDato.setBounds(500,20, 100, 30);
		textDato.setForeground(Color.darkGray);
		ventana.add(textDato);
		
		textPrecio = new JLabel();
		textPrecio.setText(" Precio ");
		textPrecio.setBounds(500,120, 100, 30);
		textPrecio.setForeground(Color.darkGray);
		ventana.add(textPrecio);
		
		textId = new JLabel();
		textId.setText(" ID  ");
		textId.setBounds(500,220, 100, 30);
		textId.setForeground(Color.darkGray);
		ventana.add(textId);
		
		cajaDato = new JTextField();
		cajaDato.setBounds(480,50,150,30);
		cajaDato.setBorder(null);
		ventana.add(cajaDato);
		
		cajaPrecio = new JTextField();
		cajaPrecio.setBounds(480,150,150,30);
		cajaPrecio.setBorder(null);
		ventana.add(cajaPrecio);
		
		cajaId = new JTextField();
		cajaId.setBounds(480,250,150,30);
		cajaId.setBorder(null);
		cajaId.setEnabled(true);
		ventana.add(cajaId);
		

		//model.addRow(new Object[] {"5", "Pozol", "$18.00"});
		try {
			model =   new DefaultTableModel(data,label);
			jt = new JTable(model);
			jt.setCellSelectionEnabled(false);
			ListSelectionModel select = jt.getSelectionModel();
			select.addListSelectionListener(jt);
			JScrollPane sp = new JScrollPane(jt);
			sp.setBounds(10, 10, 300, 500);
			sp.setEnabled(false);
			ventana.add(sp);
			
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(this, " error ");
		}
	
		//visible o no
		
	}
	public void valueChanged(ListSelectionEvent e) {
		String Data = null;
		
		int[] row = jt.getSelectedRows(); // selecciona la posicion de la fila
		
		for(int i = 0; i< row.length; i++) {
			Data = (String) jt.getValueAt(row[i], 0);
		}
		
		cajaDato.setText((String) jt.getValueAt((Integer.valueOf(Data)-1), 1));
		cajaPrecio.setText((String) jt.getValueAt((Integer.valueOf(Data)-1), 2));
	}

	private void configurarVentana() {
		

		ventana.setTitle("CalcularIva.exe");
		ventana.setSize(700,500);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(null);
		ventana.setResizable(true);
		ventana.getContentPane().setBackground(Color.lightGray);
		ventana.setCursor(Cursor.HAND_CURSOR);
	    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

	public void addRow(String str1, String str2, String str3) {
		try {
		model.addRow(new Object[] {str1,str2,str3}); //Añade nuevos elementos
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(this, " error de inseccion ");
		}
	}
	
	public void modificarRow(String str1, String str2, String str3) {
		try {
		model.addRow(new Object[] {str1,str2,str3});
		
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(this, " error de inseccion ");
		}
	}
	
	public void removeRow(int valor) {
		try {
			if(model.getRowCount() > 0) {  //Verifica que tiene elementos y elimina
				model.removeRow(valor-1);
			}
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(this, " error de eliminacion ");
		}
	}
	
	public static void main(String[] args){
		ventana = new JFrame();
        Formulario i = new Formulario();
        ventana.setVisible(true);
       
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object fuente = e.getSource();
		if(fuente == botonAgregar) {
			String stringDato = cajaDato.getText();
			String stringPrecio = cajaPrecio.getText();
			
			contadorId++;
			addRow(String.valueOf(contadorId), stringDato, stringPrecio);
			
		}else if(fuente == botonModificar) {
			model.setValueAt(cajaDato.getText(), (Integer.valueOf(cajaId.getText())-1), 1);
			model.setValueAt(cajaPrecio.getText(), (Integer.valueOf(cajaId.getText())-1), 2);
		}else if(fuente == botonEliminar) {
			cajaDato.setText("");
			cajaPrecio.setText("");
			try {
				String aux=cajaId.getText();
				int auxiliar = Integer.valueOf(aux);
				removeRow(auxiliar);
				contadorId--;
				for(int i=0;i<=model.getRowCount();i++) {
					model.setValueAt(i+1, i, 0);
				}
				
			}catch(Exception e1) {
				
			}
			
		}
		
		if(contadorId <= 0) {
			botonModificar.setVisible(false);
			botonEliminar.setVisible(false);
		} else {
			botonModificar.setVisible(true);
			botonEliminar.setVisible(true);
		}
	}
}

