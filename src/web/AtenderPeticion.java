package web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class AtenderPeticion implements Runnable{

	Socket s;
	
	
	public AtenderPeticion(Socket s) {
		super();
		this.s = s;
	}


	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		try {
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
