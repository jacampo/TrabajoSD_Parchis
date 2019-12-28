

package cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		BufferedReader br;
		BufferedWriter bw;
		Socket s;
		Scanner sca = null;
		try {
			s = new Socket("localhost", 8080);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			
			sca=new Scanner(System.in);
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
					String fich = "";
					int a = 0;
					do {
						System.out.println("introduce el numero de la ficha que quieres cambiar");
						try {
							fich = sca.next();
							a = Integer.parseInt(fich);
						}catch (NumberFormatException e) {
							System.out.println("No es un numero");
							a = 0;
						}
					}while(a < 1 || a > 4);
					
					
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
			if(sca!=null) {
				try {
					sca.close();
				}catch (IllegalStateException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
