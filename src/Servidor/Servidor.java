package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
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
					
					
					//de momento de prueba con los 4 asi
					
					
					Socket s1 = ss.accept();
					Socket s2 = ss.accept();
					Socket s3 = ss.accept();
					Socket s4 = ss.accept();
					
					Vector<Socket> v = new Vector<>();
					v.add(s1);
					v.add(s2);
					v.add(s3);
					v.add(s4);
					
					
					Partida p = new Partida(v);
					p.jugar();
					
					
					//l.add(pool.submit(new AtenderPeticion(s)));
					}
					
					//pool.execute(new AtenderPeticion(s));
				catch(IOException e) {
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
