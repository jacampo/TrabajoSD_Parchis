package Servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br;
		BufferedWriter bw;
		Socket s;
		try {
			s = new Socket("10.11.62.10", 8080);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			
			
			System.out.println(br.readLine());
			bw.write("soy el cliente \r\n");
			bw.flush();
			
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			
		}
	}

}
