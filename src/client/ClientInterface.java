package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import server.ServerInterface;

public interface ClientInterface extends Remote {
	public void recibir(String msg) throws RemoteException;

	public String getNombre() throws RemoteException;

	public void setNombre(String s) throws RemoteException;

	public ServerInterface getServer() throws RemoteException;

	public void setServer(ServerInterface si) throws RemoteException;

	public void showView() throws RemoteException;

	public void updateClient() throws RemoteException;
	
	public void salir() throws RemoteException;

}
