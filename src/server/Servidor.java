
package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.ClientInterface;
import client.Cliente;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Action;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * @author adri
 */
@SuppressWarnings("serial")
public class Servidor extends javax.swing.JFrame implements ServerInterface {
	private ArrayList<ClientInterface> clientes;
	private DefaultListModel<String> listClient;

	public Servidor(){
		setTitle("Servidor de Chat");
		clientes = new ArrayList<ClientInterface>();
		listClient = new DefaultListModel<String>();
		initComponents();
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				salir();
			}
		});
	}

	public void salir(){
		if(JOptionPane
				.showConfirmDialog(rootPane,
								   "¿Desea realmente cerrar el servidor?",
								   "Salir del sistema",
								   JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			try{
				for(int i = clientes.size() - 1; i >= 0; i--){
					clientes.get(i).recibir("El servidor se ha cerrado");
				}
				System.exit(0);
			}catch(RemoteException ex){
				Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE,
															  null, ex);
			}
		}
	}

	private void initComponents(){

		botonCerrar = new javax.swing.JButton();
		botonCerrar.setAction(action);
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		TextArea = new javax.swing.JTextArea();
		jLabel2 = new javax.swing.JLabel();
		jScrollPane3 = new javax.swing.JScrollPane();
		listaUsuarios = new javax.swing.JList<String>(listClient);
		jLabel1 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		botonCerrar.setText("Cerrar");
		botonCerrar.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				try{
					botonCerrarActionPerformed(evt);
				}catch(RemoteException e){
					e.printStackTrace();
				}
			}
		});

		TextArea.setColumns(20);
		TextArea.setRows(5);
		jScrollPane1.setViewportView(TextArea);

		jLabel2.setText("Mensages del servidor");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
																			jPanel1);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel1Layout
										  .createSequentialGroup()
										  .addContainerGap()
										  .addGroup(jPanel1Layout
															.createParallelGroup(Alignment.LEADING)
															.addGroup(jPanel1Layout
																			  .createSequentialGroup()
																			  .addComponent(jScrollPane1,
																							GroupLayout.DEFAULT_SIZE,
																							324,
																							Short.MAX_VALUE)
																			  .addContainerGap())
															.addGroup(jPanel1Layout
																			  .createSequentialGroup()
																			  .addComponent(jLabel2)
																			  .addGap(190)))));
		jPanel1Layout.setVerticalGroup(jPanel1Layout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout
								  .createSequentialGroup()
								  .addComponent(jLabel2)
								  .addGap(18)
								  .addComponent(jScrollPane1,
												GroupLayout.DEFAULT_SIZE, 205,
												Short.MAX_VALUE)
								  .addContainerGap()));
		jPanel1.setLayout(jPanel1Layout);

		jScrollPane3.setViewportView(listaUsuarios);

		jLabel1.setText("Usuarios");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
																	 getContentPane());
		layout.setHorizontalGroup(layout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
								  .addContainerGap()
								  .addGroup(layout.createParallelGroup(Alignment.LEADING)
													.addComponent(jLabel1)
													.addComponent(jScrollPane3,
																  GroupLayout.DEFAULT_SIZE,
																  176,
																  Short.MAX_VALUE))
								  .addGroup(layout.createParallelGroup(Alignment.LEADING)
													.addGroup(layout.createSequentialGroup()
																	  .addGap(18)
																	  .addComponent(jPanel1,
																					GroupLayout.DEFAULT_SIZE,
																					348,
																					Short.MAX_VALUE)
																	  .addGap(12))
													.addGroup(layout.createSequentialGroup()
																	  .addGap(110)
																	  .addComponent(botonCerrar,
																					GroupLayout.DEFAULT_SIZE,
																					158,
																					Short.MAX_VALUE)
																	  .addGap(98)))
								  .addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
								  .addContainerGap()
								  .addGroup(layout.createParallelGroup(Alignment.LEADING)
													.addGroup(layout.createSequentialGroup()
																	  .addGap(0,
																			  5,
																			  Short.MAX_VALUE)
																	  .addComponent(jLabel1)
																	  .addPreferredGap(ComponentPlacement.RELATED)
																	  .addComponent(jScrollPane3,
																					GroupLayout.PREFERRED_SIZE,
																					258,
																					GroupLayout.PREFERRED_SIZE))
													.addGroup(Alignment.TRAILING,
															  layout.createSequentialGroup()
																	  .addComponent(jPanel1,
																					GroupLayout.DEFAULT_SIZE,
																					251,
																					Short.MAX_VALUE)
																	  .addPreferredGap(ComponentPlacement.RELATED)
																	  .addComponent(botonCerrar)
																	  .addPreferredGap(ComponentPlacement.RELATED)))
								  .addContainerGap(17, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		pack();
	}

	private
			void
			botonCerrarActionPerformed(java.awt.event.ActionEvent evt)
																	  throws RemoteException{
		salir();
	}

	public void difundir(String msg) throws RemoteException{

		for(ClientInterface ci : clientes){
			ci.recibir(msg);
		}

	}

	public void registrar(ClientInterface client) throws RemoteException{
		clientes.add(client);

		String msg = client.getNombre() + " entra al chat\n";
		TextArea.append(msg);
		msg = "<<< SERVER >>> El cliente " + client.getNombre()
				+ " se ha conectado";
		for(ClientInterface ci : clientes){
			ci.updateClient();
			ci.recibir(msg);
		}
		updateClient();

	}

	public void desconectar(ClientInterface client) throws RemoteException{
		String msg = client.getNombre() + " sale del chat\n";
		TextArea.append(msg);
		msg = "<<< SERVER >>> el cliente [" + client.getNombre()
				+ "] ha salido";
		clientes.remove(client);
		for(ClientInterface ci : clientes){
			ci.updateClient();
		}
		this.difundir(msg);
		updateClient();
	}

	public ArrayList<String> getUsers() throws RemoteException{
		ArrayList<String>usr = new ArrayList<>();
		for(ClientInterface ci : clientes){
			usr.add(ci.getNombre());
		}
		return usr;
	}

	public void showView() throws RemoteException{

		this.setVisible(true);
	}

	public void updateClient() throws RemoteException{
		listClient.clear();
		String username;

		for(ClientInterface ci : clientes){
			username = ci.getNombre();
			listClient.addElement(username);
		}
	}

	public static void main(String args[]){
		if(System.getSecurityManager() == null){
			System.setProperty("java.security.policy", "./src/server.policy");
			System.setSecurityManager(new SecurityManager());
		}
		try{
			ServerInterface servidor = new Servidor();
			ServerInterface stub = (ServerInterface)UnicastRemoteObject
					.exportObject(servidor, 0);
			Registry registry = LocateRegistry.getRegistry();
			String nombre = "Adri_Chat";
			registry.rebind(nombre, stub);
			servidor.showView();
		}catch(Exception e){
			System.err.println("fallo !!!!!: ");
			e.printStackTrace();
			System.exit(1);
		}
	}

	private javax.swing.JTextArea TextArea;
	private javax.swing.JButton botonCerrar;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JList<String> listaUsuarios;
	private final Action action = new SwingAction();

	private class SwingAction extends AbstractAction {
		public SwingAction(){
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e){}
	}
}
