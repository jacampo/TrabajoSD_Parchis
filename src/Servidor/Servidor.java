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
		
		
		/*
		 * final CountDownLatch count = new CountDownLatch(4);
		ExecutorService pool = Executors.newFixedThreadPool(4);
		List<Future<Socket>> l = new LinkedList<Future<Socket>>();
		try {
			ServerSocket ss = new ServerSocket(8080);
			List<Future<Socket>> lista = new LinkedList<Future<Socket>>();
			int i = 0;
			while(i < 4) {
				try {
						final Socket s = ss.accept();
						l.add(pool.submit(new AtenderPeticion(s,count)));
						i++;
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
			count.await();
			Thread.sleep(10000);
			
			Vector<Socket> v = new Vector<>();
			v.add(lista.get(0).get());
			v.add(lista.get(1).get());
			v.add(lista.get(2).get());
			v.add(lista.get(3).get());
			System.out.println("Hola");
			Partida p = new Partida(v);
			p.jugar();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		finally {
			pool.shutdown();
		}*/
	}

}
