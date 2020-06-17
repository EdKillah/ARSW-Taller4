/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.intro.app.tallernetworking;

/**
 *
 * @author Eduard Jimenez
 */
import java.net.*;
import java.io.*;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        
        try {
            serverSocket = new ServerSocket(32000);
           
        } catch (IOException e) {
            System.err.println("Could not listen on port: 32000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Servidor escuchando por el puerto 32000...");
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(
                    "Mensaje: " + inputLine
            );
            try{
                Double numero = Double.parseDouble(inputLine);
                numero = Math.pow(numero, 2);    
                outputLine = "Respuesta: " + inputLine +" numero: "+numero;
            }catch(NumberFormatException e){
                outputLine = "Respuesta: Entrada no valida. Error: " +e;
            }
            out.println(outputLine);
            if (outputLine.equals("Respuesta: Bye.")) {
                break;
            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
    
}
