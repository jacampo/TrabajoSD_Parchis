package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import juego.Partida;

public class Servidor {

	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newFixedThreadPool(4);
		List<Future<Socket>> l = new LinkedList<Future<Socket>>();
		try {
			ServerSocket ss = new ServerSocket(8080);		
			while(true) {
				try {
					final Socket s = ss.accept();
					l.add(pool.submit(new AtenderPeticion(s)));
					
					
					//pool.execute(new AtenderPeticion(s));
				}catch(IOException e) {
					e.printStackTrace();
				}
			}

			
			Partida p = new Partida(l.);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			pool.shutdown();
		}
	}

}
