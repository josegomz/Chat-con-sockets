package Servidor;

import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class Servidor {
    public static void main(String[] args) throws IOException{
        //conecta el servidor al puerto al 1712
        
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
            //JOptionPane.showMessageDialog(null,"Servidor activado....");
            System.out.println("servidor activado");
        } catch (IOException e) {
            //JOptionPane.showMessageDialog(null,"No puede escuchar el puerto: 4444.");
            System.out.println("no se puede escuchar el puerto 4444");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Estoy después de aceptar un cliente");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Aceptacion fallida.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        
        
        Protocolo kkp = new Protocolo();

        outputLine = kkp.processInput(null);
        out.println(outputLine);

        while ((inputLine = in.readLine()) != null) {
             outputLine = kkp.processInput(inputLine);
             out.println(outputLine);
             if (outputLine.equals("Adiós."))
                break;
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}