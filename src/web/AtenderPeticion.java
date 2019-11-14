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
		BufferedReader br=null;
		BufferedWriter bw=null;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			
			bw.write("Hola\r\n");
			bw.flush();
			
			System.out.println(br.readLine());
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(br!=null) {
					br.close();
				}
			}	
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}
