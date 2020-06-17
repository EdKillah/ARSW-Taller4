/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.intro.app.tallernetworking;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Clase que muestra informacion detallada de los metodos ofrecidos por la clas URL.
 * @author Eduard Jimenez
 */
public class URLManager {

    public static void main(String[] args) {

        try {
            URL site = new URL("http://ldbn.escuelaing.edu.co:80/");
            System.out.println("getProtocol: " + site.getProtocol());
            System.out.println("getAuthority: " + site.getAuthority());
            System.out.println("getHost: " + site.getHost());
            System.out.println("getPort: " + site.getPort());
            System.out.println("getPath: " + site.getPath());
            System.out.println("getQuery: " + site.getQuery());
            System.out.println("getFile: " + site.getFile());
            System.out.println("getRef: " + site.getRef());
            

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
