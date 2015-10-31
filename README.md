# chat_CS-RMI

chat basico cliente/servidor RMI con interfaz grafica 

hay que adecuar los ficheros server.policy al la ruta de ejecucion adecuada a cada PC

//Fichero server.policy 

grant codeBase "file:///<path>/chatGUI/bin/server/" {
	permission java.security.AllPermission;
};

despues lanzar rmiregistry en el directorio donde estan los ejecutables y a continuacion lanzar el servidor y luego el cliente.
