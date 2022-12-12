package chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server extends Thread{
	public static final int PORT = 10000;
	private ServerSocket serverSocket;
	private List<PrintStream> clients;
	PrintStream out;

	public static void main(String[] args) {			
		try {
			Server server = new Server(); // Criando novo servidor
			System.out.println("Servidor conectado na porta " + PORT);
			server.startServer();		
		} 
		catch (IOException e) {
			System.out.println("Erro ao conectar o servidor: " + e.getMessage());
		}
	}
	// Método construtor
	public Server() throws IOException {
		this.clients = new ArrayList<PrintStream>();
	}
	// Iniciar o servidor
	public void startServer() throws IOException {
		serverSocket = new ServerSocket(PORT);
		clientConnection();
	}
	// Conectar o cliente
	public void clientConnection() throws IOException {
		while(true) {
			Socket clientSocket = serverSocket.accept(); //Aceitar o cliente se conectar no servidor
			System.out.println("Cliente " + clientSocket.getRemoteSocketAddress() + " conectou!");
	
			out = new PrintStream(clientSocket.getOutputStream());
			this.clients.add(out);
			
			ClientTreatment ct = new ClientTreatment(clientSocket.getInputStream(), this);
			new Thread(ct).start();
		}					
	}
	// VERIFICAR AQUI
	// Mandar mensagem para todos
	public void sendToAll(String msg) {
		for (PrintStream client : this.clients) {
				client.println(msg);
		}
	}
	// Classe para tratar cada cliente conectado 
	public class ClientTreatment implements Runnable{
		private InputStream client;
		private Server server;
		
		//Método construtor da classe Client Treatment
		public ClientTreatment(InputStream client, Server server) {
			this.client = client;
			this.server = server;
		}
		@Override
		public void run() {		
			Scanner scan = new Scanner(this.client);
			server.sendToAll(scan.nextLine());
		}	
	}
}
