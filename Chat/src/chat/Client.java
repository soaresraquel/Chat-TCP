package chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client{
	private Socket clientSocket;
	PrintStream out;
	Scanner scan;
	
	public static void main(String[] args) throws IOException {
		Client client = new Client();
		client.startClient();
	}
	// Método construtor
	public Client() {
		 scan = new Scanner(System.in);
	}
	// Iniciar conexão do cliente no servidor
	public void startClient() throws UnknownHostException, IOException {
		clientSocket = new Socket("", Server.PORT);
		System.out.println("Cliente " + clientSocket.getRemoteSocketAddress() + " conectado ao servidor!");
			
		receiveMessage();
			
		out = new PrintStream(clientSocket.getOutputStream());
		sendMessage();	
	}
	// Enviar mensagem
	private void sendMessage() throws IOException {
		String msg;
		do {
			System.out.println("Digite a mensagem: ");
			msg = scan.nextLine();
			out.println(msg);
		}
		while(!msg.equalsIgnoreCase("exit"));
	}
	// Receber mensagem
	private void receiveMessage() throws IOException{
		Receive r = new Receive(clientSocket.getInputStream());
		new Thread(r).start();
	}
	// Classe que recebe mensagem do servidor
	public class Receive implements Runnable{
		
		private InputStream server;
		
		//Método construtor da classe Receive
		public Receive(InputStream server) {
			this.server = server;
		}
		@Override
		public void run() {
			Scanner scan = new Scanner(this.server);
			while(scan.hasNextLine()) {
				System.out.println("Cliente " + clientSocket.getRemoteSocketAddress() + " disse: " + scan.nextLine());
			}
		}
	}
}