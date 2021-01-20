/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketshilosserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class AttendToClient extends Thread {

    Socket socket;
    String randomNumber;

    public AttendToClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        talkToClient();
    }

    private void talkToClient() {
        try {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            randomNumber = randomNumber();
            System.out.println("El número aleatorio es: " + randomNumber);
            do {
                
                String lineFromClient = br.readLine();
                System.out.println(lineFromClient);
                if (randomNumber.equals(lineFromClient)) {
                    bw.write("Lo has adivinado");
                    bw.newLine();
                    bw.flush();
                    /*randomNumber ="" + (new Random().nextInt(10) +1);
                    System.out.println("El nuevo número es: " + randomNumber);*/
                } else {
                    bw.write("No lo has adivinado");
                    bw.newLine();
                    bw.flush();
                }
            } while (true);

        } catch (IOException ex) {
            Logger.getLogger(SocketsHilosServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String randomNumber() {
        Random random = new Random();
        String r = "" + (random.nextInt(10) + 1);
        return r;
    }

}
