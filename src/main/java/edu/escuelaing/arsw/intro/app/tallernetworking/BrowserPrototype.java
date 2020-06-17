/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.intro.app.tallernetworking;

import java.io.*;
import java.net.*;

/**
 * Clase que me permite acceder a una pagina web y me guarda la informacion en un archivo html
 * @author Eduard Jimenez
 */
public class BrowserPrototype {

    public static void main(String[] args) throws Exception {
        String urlUser = args[0];
        URL url = new URL(urlUser);
        FileWriter archivo = new FileWriter("resultado.html");
        try (BufferedReader reader
                = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                archivo.write(inputLine);
            }
            archivo.close();
        } catch (IOException x) {
            System.err.println(x);
        }
    }

}
