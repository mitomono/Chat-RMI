package server;

import java.rmi.*;

import client.ClientInterface;
import java.util.ArrayList;

public interface ServerInterface extends Remote {

	public void difundir(String msg) throws RemoteException;

	public void registrar(ClientInterface client) throws RemoteException;

	public void desconectar(ClientInterface client) throws RemoteException;

	public ArrayList<String> getUsers() throws RemoteException;
	
	public void showView() throws RemoteException;
}
