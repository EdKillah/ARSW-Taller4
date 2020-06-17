package edu.escuelaing.arsw.intro.app.tallernetworking.Http;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.imageio.ImageIO;

/**
 *
 * @author Eduard Jimenez
 */
public class ImageResource {

    public void drawImage(OutputStream  clientSocket,PrintWriter out ,String res, File archivoEncontrado) throws IOException {
        
        System.out.println("entra?????? ****final: "+archivoEncontrado);
        System.out.println("ME ENCUENTRO EN IMAGEN Y ESTA ES LA RES: "+res);
        if(res.contains("img/")){
            
            res = res.substring(4,res.length());
            System.out.println("Entro en condicAICIOn: "+res);
        }
        //BufferedImage image = ImageIO.read(new File(System.getProperty("user.dir") + "/resources" + "/suzaku.PNG"));
        BufferedImage image = ImageIO.read(new File("C:\\Users\\Z470\\Documents\\NetBeansProjects\\TallerNetworking\\src\\main\\resources\\img\\"+res)); 
        //BufferedImage image = ImageIO.read(archivoEncontrado);
        //new File("C:\\Users\\Z470\\Documents\\NetBeansProjects\\TallerNetworking\\src\\main\\resources\\img\\bugs.jpg")
                    //C:\Users\Z470\Documents\NetBeansProjects\TallerNetworking\src\main\resources\img\suzaku.png
        //System.out.println("PASA EL PRIMER LINEA");

        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: image/png");
        out.println();
        ImageIO.write(image, "PNG", clientSocket);
        
    }
}
