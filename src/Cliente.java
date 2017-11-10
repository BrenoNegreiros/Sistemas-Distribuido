
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;

import java.util.ArrayList;

import java.util.Scanner;

import javax.swing.*;

public class Cliente extends JFrame {

	Socket s;

	PrintWriter pw;

	String nome;

	Scanner scan;

	private javax.swing.JButton jButton1;

	//private javax.swing.JButton jButton2;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JPanel jPanel1;

	private javax.swing.JTextField jTextField1;

	private javax.swing.JTextField jTextField2;

	//private ArrayList<String> Idcliente = new ArrayList<>();

	private class ouvirServer implements Runnable {

		@Override

		public void run() {

			try {

				String aux;

				while ((aux = scan.nextLine()) != null) {

					System.out.println(aux);

				}

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

	}

	public Cliente(String nome) {

		super("chat : " + nome);

		this.nome = nome;
		
		
		  addWindowListener (new WindowAdapter () {
              public void windowClosing (WindowEvent e) {
            	  if(jTextField1.getText().equals("")&&jTextField2.getText().equals("")) {
            		  System.exit(1);
            	  }
            	  pw.println(nome + " " + "-" + jTextField1.getText() + " " + "-" + jTextField2.getText());

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

		pw.println(nome + " " + jTextField1.getText() + " " + jTextField2.getText());

		pw.flush();

		jTextField1.setEnabled(false);
		jTextField2.setEnabled(false);
		
		jTextField1.requestFocus();

		jTextField2.requestFocus();

	}

	/*
	 * 
	 * FAZ AQUI EM
	 * 
	 * BAIXO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * 
	 * !!!!
	 */

//	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
//		try {
//			if (jTextField1.getText().equals("") && jTextField2.getText().equals("")) {
//				s.close();
//				System.exit(1);
//				
//			} else {
//
//				pw.println(nome + " " + "-" + jTextField1.getText() + " " + "-" + jTextField2.getText());
//
//				pw.flush();
//
//				s.close();
//				System.exit(1);
//
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	private void initComponents() {
		  jPanel1 = new javax.swing.JPanel();
	        jLabel1 = new javax.swing.JLabel();
	        jTextField1 = new javax.swing.JTextField();
	        jLabel2 = new javax.swing.JLabel();
	        jButton1 = new javax.swing.JButton();
	        jTextField2 = new javax.swing.JTextField();

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	        jLabel1.setText("Memoria");

	        jLabel2.setText("Processador");

	        jButton1.setText("Enviar");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton1ActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGap(71, 71, 71)
	                        .addComponent(jButton1))
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGap(26, 26, 26)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel2)
	                            .addComponent(jLabel1))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jTextField1))))
	                .addContainerGap(46, Short.MAX_VALUE))
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap(64, Short.MAX_VALUE)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel1))
	                .addGap(19, 19, 19)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel2)
	                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(25, 25, 25)
	                .addComponent(jButton1)
	                .addGap(26, 26, 26))
	        );

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	        );

	        pack();
		
		/*jPanel1 = new javax.swing.JPanel();

		jLabel1 = new javax.swing.JLabel();

		jTextField1 = new javax.swing.JTextField();

		jLabel2 = new javax.swing.JLabel();

		jButton1 = new javax.swing.JButton();

		jTextField2 = new javax.swing.JTextField();

		jButton2 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

		jLabel1.setText("Memoria");

		jLabel2.setText("Processador");

		jButton1.setText("Enviar");

		jButton1.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				jButton1ActionPerformed(evt);

			}

		});

		jButton2.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {

//				jButton2ActionPerformed(evt);

			}

		});

		jButton2.setText("Sair");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);

		jPanel1.setLayout(jPanel1Layout);

		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

				.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout

						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

						.addGroup(jPanel1Layout.createSequentialGroup().addGap(71, 71, 71).addComponent(jButton1))

						.addGroup(jPanel1Layout.createSequentialGroup().addGap(26, 26, 26)

								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

										.addComponent(jLabel2).addComponent(jLabel1))

								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)

								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

										.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 84,

												javax.swing.GroupLayout.PREFERRED_SIZE)

										.addComponent(jTextField1))))

						.addContainerGap(46, Short.MAX_VALUE))

				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,

						jPanel1Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(jButton2,

								javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)

								.addContainerGap()));

		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jButton2)

						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)

						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)

								.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,

										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)

								.addComponent(jLabel1))

						.addGap(19, 19, 19)

						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)

								.addComponent(jLabel2).addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE,

										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))

						.addGap(25, 25, 25).addComponent(jButton1).addGap(26, 26, 26)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

		getContentPane().setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(

				jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,

				javax.swing.GroupLayout.PREFERRED_SIZE));

		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(

				jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,

				javax.swing.GroupLayout.PREFERRED_SIZE));

		pack();*/
	}

}