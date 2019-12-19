

package Cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br;
		BufferedWriter bw;
		Socket s;
		try {
			s = new Socket("localhost", 8080);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			
			String lin=br.readLine();
			while(!lin.equalsIgnoreCase("FIN")) {			
				while(!lin.startsWith("DADO")) {
					System.out.println(lin);
					lin=br.readLine();
				}
				
				System.out.println(lin);
				while(!lin.equalsIgnoreCase("ok")) {					
					if(lin.equalsIgnoreCase("ERROR")) {
						System.out.println("introduce una ficha valida");
					}					
					System.out.println("introduce el numero de la ficha que quieres cambiar");
					Scanner sca=new Scanner(System.in);
					int fich=sca.nextInt();
					
					bw.write("FICHA "+fich+"\r\n");
					bw.flush();
					lin=br.readLine();
				}
				lin=br.readLine();
			}
			System.out.println("final");
			
			
			lin=br.readLine();
			while(!lin.equalsIgnoreCase("FIN")) {
				System.out.println(lin);
				lin=br.readLine();
			}
			

			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			
		}
	}

}
