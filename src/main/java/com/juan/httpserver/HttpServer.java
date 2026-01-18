package com.juan.httpserver;

import com.juan.httpserver.config.Configuration;
import com.juan.httpserver.config.ConfigurationManager;

public class HttpServer {

    public static void main (String[] args){

        System.out.println("Server Starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using ports: " + conf.getPort());
        System.out.println("Using webroot: " + conf.getWebroot());
    }
}
