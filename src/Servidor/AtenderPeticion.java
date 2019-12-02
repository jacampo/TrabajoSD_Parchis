package Servidor;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Callable;

public class AtenderPeticion implements Callable<Socket>{

	Socket s;
		
	public AtenderPeticion(Socket s) {
		super();
		this.s = s;
	}

	@Override
	public Socket call() throws Exception {
		try {

		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			
		}
		return null;
	}

}
