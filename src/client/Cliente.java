package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import server.ServerInterface;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

/**
 * @author adri
 */
@SuppressWarnings("serial")
public class Cliente extends JFrame implements ActionListener, ClientInterface {

	private ServerInterface server;
	private String nombre;
	private DefaultListModel<String> listClient;

	public Cliente() throws RemoteException{
		listClient = new DefaultListModel<String>();
		initComponents();
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				try{
					salir();
				}catch(RemoteException e1){
					e1.printStackTrace();
				}
			}
		});
	}

	public Cliente(ServerInterface si, String n) throws RemoteException{
		server = si;
		nombre = n;
		listClient = new DefaultListModel<String>();
		initComponents();
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				try{
					salir();
				}catch(RemoteException e1){
					e1.printStackTrace();
				}
			}
		});
	}

	public void salir() throws RemoteException{
		if(JOptionPane.showConfirmDialog(rootPane,
										 "¿Desea realmente salir del chat?",
										 "Salir del sistema",
										 JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			try{
				server.desconectar(this);
			}catch(RemoteException ex){
				Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE,
															  null, ex);
			}
			System.exit(0);
		}
	}

	private void initComponents(){
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		botonEnviar = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		cajaMensage = new javax.swing.JTextField();
		cajaMensage.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					enviar();
				}
			}
		});

		labelConectados = new javax.swing.JLabel();
		labelUsuario = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		listaUsuarios = new javax.swing.JList<String>(listClient);
		listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Chat de Adrian");

		jTextArea1.setEditable(false);
		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		botonEnviar.setText("enviar");
		botonEnviar.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				botonEnviarActionPerformed(evt);
			}
		});

		jButton2.setText("salir");
		jButton2.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				try{
					jButton2ActionPerformed(evt);
				}catch(RemoteException e){
					e.printStackTrace();
				}
			}
		});

		labelConectados.setText("Conectados");

		jScrollPane2.setViewportView(listaUsuarios);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
																			jPanel1);
		jPanel1.setLayout(jPanel1Layout);

		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout
										  .createSequentialGroup()
										  .addContainerGap()
										  .addComponent(jScrollPane2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														0, Short.MAX_VALUE)
										  .addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout
										  .createSequentialGroup()
										  .addContainerGap()
										  .addComponent(jScrollPane2,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														221, Short.MAX_VALUE)
										  .addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
																	 getContentPane());
		layout.setHorizontalGroup(layout
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
								  .addContainerGap()
								  .addGroup(layout.createParallelGroup(Alignment.LEADING)
													.addComponent(labelUsuario)
													.addComponent(jScrollPane1,
																  GroupLayout.DEFAULT_SIZE,
																  351,
																  Short.MAX_VALUE)
													.addComponent(cajaMensage,
																  GroupLayout.DEFAULT_SIZE,
																  351,
																  Short.MAX_VALUE))
								  .addGap(26)
								  .addGroup(layout.createParallelGroup(Alignment.LEADING)
													.addGroup(layout.createSequentialGroup()
																	  .addGap(0)
																	  .addComponent(labelConectados)
																	  .addContainerGap(130,
																					   Short.MAX_VALUE))
													.addGroup(layout.createSequentialGroup()
																	  .addComponent(botonEnviar,
																					GroupLayout.DEFAULT_SIZE,
																					81,
																					Short.MAX_VALUE)
																	  .addGap(18)
																	  .addComponent(jButton2,
																					GroupLayout.DEFAULT_SIZE,
																					90,
																					Short.MAX_VALUE)
																	  .addGap(20))
													.addGroup(layout.createSequentialGroup()
																	  .addComponent(jPanel1,
																					GroupLayout.DEFAULT_SIZE,
																					197,
																					Short.MAX_VALUE)
																	  .addContainerGap()))));
		layout.setVerticalGroup(layout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
								  .addContainerGap()
								  .addGroup(layout.createParallelGroup(Alignment.LEADING)
													.addComponent(labelConectados)
													.addComponent(labelUsuario))
								  .addGap(4)
								  .addGroup(layout.createParallelGroup(Alignment.LEADING)
													.addGroup(layout.createSequentialGroup()
																	  .addComponent(jScrollPane1,
																					GroupLayout.DEFAULT_SIZE,
																					226,
																					Short.MAX_VALUE)
																	  .addGap(25))
													.addGroup(layout.createSequentialGroup()
																	  .addComponent(jPanel1,
																					GroupLayout.DEFAULT_SIZE,
																					GroupLayout.DEFAULT_SIZE,
																					Short.MAX_VALUE)
																	  .addPreferredGap(ComponentPlacement.RELATED)))
								  .addGroup(layout.createParallelGroup(Alignment.BASELINE)
													.addComponent(cajaMensage)
													.addComponent(botonEnviar)
													.addComponent(jButton2))
								  .addGap(34)));

		getContentPane().setLayout(layout);

		pack();
	}

	private
			void
			jButton2ActionPerformed(java.awt.event.ActionEvent evt)
																   throws RemoteException{
		salir();
	}

	private void botonEnviarActionPerformed(java.awt.event.ActionEvent evt){
		enviar();
	}
	
	private void enviar(){
		String msg;
		msg = cajaMensage.getText();
		msg = "[" + getNombre() + "] " + msg;
		try{
			getServer().difundir(msg);
		}catch(RemoteException ex){
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null,
														  ex);
		}
		cajaMensage.setText("");
	}

	public void recibir(String msg) throws RemoteException{
		this.jTextArea1.append(msg + "\n");
	}

	public String getNombre(){
		return nombre;
	}

	public ServerInterface getServer(){
		return server;
	}

	public void setNombre(String s) throws RemoteException{
		nombre = s;
		this.labelUsuario.setText(s);
	}

	public void setServer(ServerInterface si) throws RemoteException{
		server = si;
	}

	public void showView() throws RemoteException{

		this.setVisible(true);
	}

	public void updateClient() throws RemoteException{
		listClient.clear();
		ArrayList<String>usernames = server.getUsers();
		
		for(String s : usernames){
			listClient.addElement(s);
		}
	}

	public static void main(String args[]) throws RemoteException{
		ClientInterface cliente = new Cliente();
		Registro registro = new Registro((Cliente)cliente, true);
		cliente.setNombre(registro.getName());
		if(System.getSecurityManager() == null){
			System.setProperty("java.security.policy", "./src/server.policy");
			System.setSecurityManager(new SecurityManager());
		}
		try{
			Registry registry = LocateRegistry.getRegistry(args[0]);
			ServerInterface svr = (ServerInterface)registry.lookup("Adri_Chat");
			cliente.setServer(svr);
			ClientInterface stub = (ClientInterface)UnicastRemoteObject
					.exportObject((ClientInterface)cliente, 0);
			svr.registrar(stub);
			cliente.showView();

		}catch(Exception e){
			System.err.println("upssss, parece que hay un problema");
			e.printStackTrace();
			System.exit(1);
		}
	}

	private javax.swing.JButton botonEnviar;
	private javax.swing.JTextField cajaMensage;
	private javax.swing.JButton jButton2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JLabel labelConectados;
	private javax.swing.JLabel labelUsuario;
	private javax.swing.JList<String> listaUsuarios;

	@Override
	public void actionPerformed(ActionEvent e){
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
