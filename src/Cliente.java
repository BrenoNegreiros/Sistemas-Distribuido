
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;

import java.util.ArrayList;

import java.util.Scanner;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout.Alignment;

public class Cliente extends JFrame {

	Socket s;

	PrintWriter pw;

	String nome;

	Scanner scan;
	
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JTextField quantidadeMemoria;
	private javax.swing.JTextField quantidadeProcessador;
	private javax.swing.JTextField totalMemoria;
	private javax.swing.JTextField totalProcessador;
	private javax.swing.JTextField bloqueadosCPU;
	private javax.swing.JTextField bloqueadosMemoria;
	private javax.swing.JTextField recebidosCPU;
	private javax.swing.JTextField recebidosMemoria;
	ArrayList<String> lista = new ArrayList<>();
	private int qtProcessador;
	private int qtMemoria;
	private int blockProcessador;
	private int blockMemoria;
	private String status;
	// private ArrayList<String> Idcliente = new ArrayList<>();

	private class ouvirServer implements Runnable {

		@Override

		public void run() {

			try {

				String text;

				while ((text = scan.nextLine()) != null) {

					String[] msgPartida = text.split(" ");
					lista.add(text);
					
					
					status = msgPartida[0];
					if(status.equals("enviado")) {
					qtMemoria = qtMemoria + Integer.parseInt(msgPartida[1]);
					qtProcessador = qtProcessador + Integer.parseInt(msgPartida[2]);
					
					totalMemoria.setText(qtMemoria + "");
					totalProcessador.setText(qtProcessador + "");
					}else {
						if(status.equals("bloqueado")) {
							
							qtMemoria = qtMemoria + Integer.parseInt(msgPartida[1]);
							qtProcessador = qtProcessador + Integer.parseInt(msgPartida[2]);
							
							blockMemoria =( blockMemoria + Integer.parseInt(msgPartida[1]) *-1);
							blockProcessador = (blockProcessador + Integer.parseInt(msgPartida[2])*-1);
							
							
							System.out.println(qtMemoria+"");
							
//							totalMemoria.setText(qtMemoria + "");
//							totalProcessador.setText(qtProcessador + "");
							
							// problema
							
							recebidosMemoria.setText(blockMemoria + "");
							recebidosCPU.setText(blockProcessador + "");
						}
					}

				}

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

	}

	public Cliente(String nome) {

		super("Sistemas Distribuido : " + nome);

		this.nome = nome;

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (quantidadeMemoria.getText().equals("") && quantidadeProcessador.getText().equals("")) {
					System.exit(1);
				}
				pw.println(
						"enviado" + " " + "-" + quantidadeMemoria.getText() + " " + "-" + quantidadeProcessador.getText());

				pw.flush();

				System.exit(1);

			}
		});

		confg();

		initComponents();

		setVisible(true);

	}

	private void confg() {

		try {

			s = new Socket("localhost", 5000);

			pw = new PrintWriter(s.getOutputStream());

			scan = new Scanner(s.getInputStream());

			new Thread(new ouvirServer()).start();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		if (quantidadeMemoria.getText().equals("") && quantidadeProcessador.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "CAMPO OBRIGATORIO");
		} else {

			if (quantidadeMemoria.getText() != "" && quantidadeProcessador.getText().equals("")) {
				quantidadeProcessador.setText("0");

			} else {
				if (quantidadeMemoria.getText().equals("") && quantidadeProcessador.getText() != "") {
					quantidadeMemoria.setText("0");
				}

			}
		pw.println("enviado" + " " + quantidadeMemoria.getText() + " " + quantidadeProcessador.getText());
		pw.flush();

		

		quantidadeMemoria.requestFocus();

		quantidadeProcessador.requestFocus();
		
		jButton1.setEnabled(false);
		jButton2.setEnabled(true);
		}
	}
	/*
	 * 
	 * Bloquear Action em baixo!!!!!!!!!!!!!!!!!!!!
	 * 
	 * !!!!
	 */


	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

		if (bloqueadosMemoria.getText().equals("") && bloqueadosCPU.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "CAMPO OBRIGATORIO");
		} else {

			if (bloqueadosMemoria.getText() != "" && bloqueadosCPU.getText().equals("")) {
				bloqueadosCPU.setText("0");

			} else {
				if (bloqueadosMemoria.getText().equals("") && bloqueadosCPU.getText() != "") {
					bloqueadosMemoria.setText("0");
				}

			}
			int bloqueadoMemoria;
			int bloqueadoProcessador;
			int quantidadeMemo;
			int quantidadeCpu;

			bloqueadoMemoria = Integer.parseInt(bloqueadosMemoria.getText());
			bloqueadoProcessador = Integer.parseInt(bloqueadosCPU.getText());
			quantidadeMemo = Integer.parseInt(quantidadeMemoria.getText());
			quantidadeCpu = Integer.parseInt(quantidadeProcessador.getText());

			quantidadeProcessador.setText(quantidadeCpu - bloqueadoProcessador + "");
			quantidadeMemoria.setText(quantidadeMemo - bloqueadoMemoria + "");

			pw.println("bloqueado" + " " + "-" + bloqueadosMemoria.getText() + " " + "-" + bloqueadosCPU.getText());

			

			pw.flush();
			jButton2.setEnabled(false);
		}
		
	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		bloqueadosCPU = new javax.swing.JTextField();
		jLabel6 = new javax.swing.JLabel();
		bloqueadosMemoria = new javax.swing.JTextField();
		jButton2 = new javax.swing.JButton();
		jButton2.setEnabled(false);
		jPanel3 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		totalMemoria = new javax.swing.JTextField();
		totalProcessador = new javax.swing.JTextField();
		jPanel2 = new javax.swing.JPanel();
		recebidosCPU = new javax.swing.JTextField();
		recebidosMemoria = new javax.swing.JTextField();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		quantidadeMemoria = new javax.swing.JTextField();
		quantidadeProcessador = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(new java.awt.GridLayout(2, 2));

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Bloqueados"));

		jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel5.setText("CPU");

		jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel6.setText("Memoria");

		jButton2.setText("Bloquear");
		
		
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(53, 53, 53)
										.addGroup(jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(bloqueadosCPU).addComponent(jLabel5,
														javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
										.addGap(18, 18, 18)
										.addGroup(jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(bloqueadosMemoria, javax.swing.GroupLayout.PREFERRED_SIZE,
														65, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel6)))
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(81, 81, 81)
										.addComponent(jButton2)))
						.addContainerGap(140, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(56, 56, 56)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel6))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(bloqueadosCPU, javax.swing.GroupLayout.DEFAULT_SIZE, 31,
												Short.MAX_VALUE)
										.addComponent(bloqueadosMemoria))
						.addGap(40, 40, 40).addComponent(jButton2).addContainerGap(58, Short.MAX_VALUE)));

		getContentPane().add(jPanel1);

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Total"));

		jLabel3.setText("Memoria:");

		jLabel4.setText("CPU:");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addGap(22, 22, 22)
						.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel3).addComponent(jLabel4))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(totalMemoria).addComponent(totalProcessador,
										javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
						.addContainerGap(120, Short.MAX_VALUE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addGap(52, 52, 52)
						.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel3)
								.addComponent(totalMemoria, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(37, 37, 37)
						.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel4).addComponent(totalProcessador,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(111, Short.MAX_VALUE)));

		getContentPane().add(jPanel3);

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Recursos Recebidos"));

		jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel9.setText("CPU");

		jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel10.setText("Memória");
		
	

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2Layout.setHorizontalGroup(
			jPanel2Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
					.addGap(42)
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jLabel9)
						.addComponent(recebidosCPU, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jLabel10)
						.addComponent(recebidosMemoria, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(109, Short.MAX_VALUE))
		);
		jPanel2Layout.setVerticalGroup(
			jPanel2Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
					.addGap(85)
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel9)
						.addComponent(jLabel10))
					.addGap(18)
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(recebidosCPU, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(recebidosMemoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(100, Short.MAX_VALUE))
		);
		jPanel2.setLayout(jPanel2Layout);

		getContentPane().add(jPanel2);

		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Quantidade"));

		jLabel1.setText("Memória:");

		jLabel2.setText("CPU:");

		jButton1.setText("Enviar");
		
		
		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addGroup(jPanel4Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel4Layout.createSequentialGroup().addGap(48, 48, 48)
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel1).addComponent(jLabel2))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel4Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(quantidadeMemoria).addComponent(quantidadeProcessador,
												javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)))
						.addGroup(jPanel4Layout.createSequentialGroup().addGap(113, 113, 113).addComponent(jButton1)))
						.addContainerGap(128, Short.MAX_VALUE)));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addGap(61, 61, 61)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel1)
								.addComponent(quantidadeMemoria, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(34, 34, 34)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2).addComponent(quantidadeProcessador,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(38, 38, 38).addComponent(jButton1).addContainerGap(44, Short.MAX_VALUE)));

		getContentPane().add(jPanel4);

		pack();
		setLocationRelativeTo( null );
	
		pw.println("enviado" + " " + 0 + " " + 0);
		pw.flush();
		
	}
}