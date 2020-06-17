package edu.escuelaing.arsw.intro.app.tallernetworking.Http;

/**
 *
 * @author Eduard Jimenez.
 */
import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpServer implements Runnable{

    private final Socket clientSocket;
    private ServerSocket serverSocket;

    public HttpServer(final Socket clientSocket) throws IOException {
        serverSocket = null;

        //Socket clientSocket = null;
        this.clientSocket = clientSocket;

//        while (true) {
//            try {
//                System.out.println("Listo para recibir ...");
//            } catch (IOException e) {
//                System.err.println("Accept failed.");
//                System.exit(1);
//            }
//
//            
//
//        }
        

    }

    private void prepareRequest(Socket clientSocket) throws IOException {
        PrintWriter out;
        BufferedReader in;

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
        String inputLine, outputLine, res = "";
        int contador = 0;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            if (contador == 0) {
                res = inputLine;
                contador++;
            }

            if (!in.ready()) {
                break;
            }
        }
        outputLine = "";

        if (res.substring(0, 3).equals("GET")) {
            res = res.substring(5, res.length() - 9);

            File archivoEncontrado = buscarArchivo(res);

            if (archivoEncontrado != null) {
                System.out.println("NO ES NULO: " + archivoEncontrado);
                getRequestFile(archivoEncontrado, out, res, clientSocket);
            } else {
                System.out.println("LAMENTABLEMENTE ES NULO: " + archivoEncontrado);
                outputLine = error(outputLine, res);
                out.println(outputLine);
            }
        }

        out.close();
        in.close();
    }

    private void getRequestFile(File archivoEncontrado, PrintWriter out,
            String res, Socket clientSocket) throws IOException {

        System.out.println(res + " ME ENCUENTRO EN GETREQUEST Y ESTE ES EL FILE: " + archivoEncontrado);
        if (res.contains("png") || res.contains("jpg")) {
            ImageResource imgr = new ImageResource();
            imgr.drawImage(clientSocket.getOutputStream(), out, res, archivoEncontrado);
        } else if (res.contains("html")) {
            HtmlResource texto = new HtmlResource();
            texto.writeText(clientSocket.getOutputStream(), out, archivoEncontrado);

        }
    }

    private File buscarArchivo(String res) {
        BuscarArchivo find = new BuscarArchivo();
        System.out.println("RESPUESTA: " + res);
        System.out.println("ARCHIVO SUSEM: " + System.getProperty("user.dir") + "\\src\\main\\resources\\img\\" + res);
        return new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + res); //"index.html"

    }

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(32000);
            System.out.println("Listo para recibir ...");
            ExecutorService pool = Executors.newCachedThreadPool();
            while (true) {
                Socket socket = serverSocket.accept();
                HttpServer req = new HttpServer(socket);
                pool.execute(req);
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 32000.");
            System.exit(1);
        }

    }

    private String error(String outputLine, String res) {

        outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Title of the document</title>\n"
                + "</head>"
                + "<body>"
                + "<h1>ERROR 404.<p><div style='color:red'>" + res.toUpperCase() + "</div>" + " NO ENCONTRADO</p></h1>"
                + "</body>"
                + "</html>";
        return outputLine;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            prepareRequest(clientSocket);
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (IOException ex) {
            System.err.println("Run exception while executing thread.");
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
