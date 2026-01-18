package com.juan.httpserver;

import com.juan.httpserver.config.Configuration;
import com.juan.httpserver.config.ConfigurationManager;
import com.juan.httpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    public static void main (String[] args){


        LOGGER.info("Server Starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Using ports: " + conf.getPort());
        LOGGER.info("Using webroot: " + conf.getWebroot());

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
