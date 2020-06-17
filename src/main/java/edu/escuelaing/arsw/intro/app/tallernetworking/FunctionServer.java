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

public class FunctionServer {

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
            try {

                Double number = validateInput(inputLine);
                outputLine = "Respuesta: " + inputLine + " function: " + number;
            } catch (NumberFormatException | FunctionServerException e) {
                outputLine = "Respuesta: Entrada no valida. Error: " + e;
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

    private static Double validateInput(String inputLine) throws FunctionServerException {
        String[] nuevo = inputLine.split(" ");
        String function = "fun:cos";
        Double number;

        if (nuevo.length > 1) {
            function = nuevo[0];
            number = Double.parseDouble(nuevo[1]);
        } else {
            number = Double.parseDouble(nuevo[0]);
        }
        return validateFunction(function, number);

    }

    private static Double validateFunction(String function, Double number) throws FunctionServerException {

        Double res;
        if (function.equals("fun:sin")) {
            res = Math.sin(number);
        } else if (function.equals("fun:tan")) {
            res = Math.tan(number);
        } else if(function.equals("fun:cos")) {
            res = Math.cos(number);
        }else{
            System.out.println("Entra en else");
            throw new FunctionServerException(FunctionServerException.MAL_FORMED_FUNCTION);
            
        }
        return res;
    }

}
