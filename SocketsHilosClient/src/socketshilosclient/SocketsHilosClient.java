/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketshilosclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class SocketsHilosClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        final String HOST = "192.168.0.178";
        final String HOSTCLASE = "192.168.103.6";
        final int PORT = 45000;

        try {
            Socket socket = new Socket(HOSTCLASE, PORT);

            talkToServer(socket);
        } catch (IOException ex) {
            Logger.getLogger(SocketsHilosClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void talkToServer(Socket socket) {
        try {
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            Scanner sc = new Scanner(System.in);
            String lineTypedByUser;
            System.out.println("Adivina el número aleatorio entre uno y diez");
            do {
                lineTypedByUser = sc.nextLine();
                bw.write(lineTypedByUser);
                bw.newLine();
                bw.flush();
                System.out.println(br.readLine());
            } while (true);

        } catch (IOException ex) {
            Logger.getLogger(SocketsHilosClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
