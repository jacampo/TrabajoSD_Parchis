package web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {

	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newCachedThreadPool();
		try {
			ServerSocket ss = new ServerSocket(8080);		
			while(true) {
				try {
					final Socket s = ss.accept();
					pool.execute(new AtenderPeticion(s));
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			pool.shutdown();
		}
	}

}
