package com.juan.httpserver.core;

import com.juan.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    private Socket socket;

    public HttpConnectionWorkerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
             inputStream = socket.getInputStream();
             outputStream = socket.getOutputStream();



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

            LOGGER.info("Connection Processing Finished.");
        } catch (IOException e) {
            LOGGER.info("Problems with communication", e);
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {

                }
            }
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {}
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {}
            }


        }
    }
}
