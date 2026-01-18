package com.juan.httpserver;

import com.juan.httpserver.config.Configuration;
import com.juan.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static void main (String[] args){

        System.out.println("Server Starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using ports: " + conf.getPort());
        System.out.println("Using webroot: " + conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            //TODO we would write

            String html ="<html><head><tittle>Simple Java HTTP Server</tittle><body><h1>This page was served using my Simple Java HTTP Server</h1></body></head></html>";
            final String CRLF = "\n\r"; //13, 10

            String response =
                    "HTTP/1.1 200 OK" + CRLF +//Status Line : HTTP VERSION RESPONSE_CODE RESPONSE_MESSAGE;
                    "Content-Length: " + html.getBytes().length + CRLF + //Header
                    CRLF +
                    html +
                    CRLF + CRLF;

            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
