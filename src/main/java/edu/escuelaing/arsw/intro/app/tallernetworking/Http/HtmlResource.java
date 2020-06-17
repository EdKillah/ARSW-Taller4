package edu.escuelaing.arsw.intro.app.tallernetworking.Http;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.imageio.ImageIO;

/**
 *
 * @author Eduard Jimenez
 */
public class HtmlResource {

    public void writeText(OutputStream clientSocket, PrintWriter out, File archivoEncontrado) throws IOException {
        //System.out.println("RECIEN ENTRO");
        StringBuilder cadena = new StringBuilder();
        String line = null;
        //System.out.println("EAASJKSKHKJDDH ardchivo encontrado: "+archivoEncontrado+"..");
        FileReader prueba = new FileReader(archivoEncontrado);
        BufferedReader reader = new BufferedReader(prueba);

        //System.out.println("HA CREADO EL READER");
        while ((line = reader.readLine()) != null) {
            cadena.append(line);
        }
        //System.out.println("PASO POR EL WHILE");
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html");
        out.println();
        out.println(cadena);

    }

}
