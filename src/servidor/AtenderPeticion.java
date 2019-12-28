package servidor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class AtenderPeticion implements Callable<Socket>{

	private Socket s;
	private CountDownLatch count;
	
	public AtenderPeticion(Socket s, CountDownLatch count) {
		super();
		this.s = s;
		this.count = count;
	}

	@Override
	public Socket call() throws IOException{
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		//bw.write("Esperando a mas jugadores\r\n");
		count.countDown();
		return this.s;
	}
	
	

}
