import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;

public class Server extends JFrame {
	ServerSocket server;
	boolean t = true;
	List<PrintWriter> lpw = new ArrayList<>();
	ArrayList<String> lista = new ArrayList<>();
	private int blockProcessador;
	private int blockMemoria;
	private int qtProcessador, totalprocessador;
	private int qtMemoria, totalmemoria;
	private String status;

	public Server() {
		try {
			server = new ServerSocket(5000);
			while (t) {
				Socket a = server.accept();
				new Thread(new listen(a)).start();
				lpw.add(new PrintWriter(a.getOutputStream()));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// aonde vou distibuir informacoes

	private void mandarTodos(String text) {
		for (PrintWriter w : lpw) {
			try {
				w.println(text);
				w.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class listen implements Runnable {
		Scanner scan;

		public listen(Socket s) {
			try {
				scan = new Scanner(s.getInputStream());
				InetAddress ip = s.getInetAddress();
				List<InetAddress> ass = new ArrayList<>();
				ass.add(ip);
				for (InetAddress i : ass) {
					System.out.println(i.getHostName());
				}
			} catch (Exception e) {
				System.out.println("exception");

				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				String text;
				while ((text = scan.nextLine()) != null) {
					String[] msgPartida = text.split(" ");
					lista.add(text);
					status = msgPartida[0];
					totalmemoria = qtMemoria;
					totalprocessador=qtProcessador;
					if (status.equals("enviado")) {
						qtMemoria = qtMemoria + Integer.parseInt(msgPartida[1]);
						qtProcessador = qtProcessador + Integer.parseInt(msgPartida[2]);
						System.err.println("enviado" + text);
						mandarTodos(text);
					} else {
						if (status.contains("bloqueado")) {
							blockMemoria = blockMemoria + Integer.parseInt(msgPartida[1]);
							blockProcessador = blockProcessador + Integer.parseInt(msgPartida[2]);
							System.err.println("bloqueado" + text);
							mandarTodos(text);
						} else {
							if (status.equals("novo")) {
								mandarTodos("novo" + " " + totalmemoria +  " " + totalprocessador);
							}
						}
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// private class mostrarEvent implements ActionListener {
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// if(jCheckBox1.isSelected()) {
	// if(jCheckBox2.isSelected()) {
	// jTextField1.setText("");
	// jTextField2.setText("");
	// jTextField1.setText(qtMemoria+"");
	// jTextField2.setText(qtProcessador+"");
	// }else {
	// jTextField1.setText("");
	// jTextField2.setText("");
	// jTextField1.setText(qtMemoria+"");
	// }
	// }else {
	// if(jCheckBox2.isSelected()) {
	// jTextField1.setText("");
	// jTextField2.setText("");
	// jTextField2.setText(qtProcessador+"");
	// }else {
	// jTextField1.setText("");
	// jTextField2.setText("");
	// }
	// }
	//
	// }
	//
	//
	// }
	// private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
	//
	// }
	// private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
	//
	//
	// }

	public static void main(String[] args) {
		Server s = new Server();
	}
}
