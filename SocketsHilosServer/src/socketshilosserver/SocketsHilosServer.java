/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketshilosserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class SocketsHilosServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         final int PORT = 45000;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor conectado");
            while(true){
                Socket socket = serverSocket.accept();
                new AttendToClient(socket).start();
            }

        } catch (IOException ex) {
            Logger.getLogger(SocketsHilosServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
