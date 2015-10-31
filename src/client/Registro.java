
package client;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author adri
 */
@SuppressWarnings("serial")
public class Registro extends javax.swing.JDialog {

	String nombre;

	public Registro(java.awt.Frame parent, boolean modal){
		super(parent, modal);
		setResizable(false);
		initComponents();
		this.addWindowListener(new java.awt.event.WindowAdapter(){
			@Override
			public void windowClosing(java.awt.event.WindowEvent e){
				System.exit(0);
			}
		});
		;
	}

	private void initComponents(){

		labelUsuario = new javax.swing.JLabel();
		nombreUsuario = new javax.swing.JTextField();
		nombreUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					nombre = nombreUsuario.getText();
					dispose();
				}
			}
		});
		botonAceptar = new javax.swing.JButton();
		botonCancelar = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Registro");

		labelUsuario.setText("nombre de usuario");

		botonAceptar.setText("aceptar");
		botonAceptar.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				botonAceptarActionPerformed(evt);
			}
		});

		botonCancelar.setText("cancelar");
		botonCancelar.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				botonCancelarActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
																	 getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
								  .addContainerGap()
								  .addComponent(labelUsuario)
								  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
													.addGroup(layout.createSequentialGroup()
																	  .addComponent(botonAceptar)
																	  .addGap(28,
																			  28,
																			  28)
																	  .addComponent(botonCancelar)
																	  .addGap(0,
																			  90,
																			  Short.MAX_VALUE))
													.addComponent(nombreUsuario))
								  .addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
								  .addContainerGap()
								  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
													.addComponent(labelUsuario)
													.addComponent(nombreUsuario,
																  javax.swing.GroupLayout.PREFERRED_SIZE,
																  javax.swing.GroupLayout.DEFAULT_SIZE,
																  javax.swing.GroupLayout.PREFERRED_SIZE))
								  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
													.addComponent(botonAceptar)
													.addComponent(botonCancelar))
								  .addContainerGap(26, Short.MAX_VALUE)));

		pack();
	}

	private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt){
		nombre = nombreUsuario.getText();
		this.dispose();
	}

	private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt){

		System.exit(0);
	}

	public String getName(){
		this.setVisible(true);
		return nombre;
	}

	private javax.swing.JButton botonAceptar;
	private javax.swing.JButton botonCancelar;
	private javax.swing.JLabel labelUsuario;
	private javax.swing.JTextField nombreUsuario;
}
