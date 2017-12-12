package server;

import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.sun.net.httpserver.*;

import dataaccess.Database;
import dataaccess.DatabaseException;
import handler.*;

/**
 * Created by Seong-EunCho on 3/2/17.
 */

public class Server {

    private static final int MAX_WAITING_CONNECTIONS = 12;

    private HttpServer server;

    private void run (String portNumber) throws DatabaseException {
        Database db = new Database();
        db.openConnection();
        db.createTables();
        db.closeConnection(true);

        System.out.println("Initializing HTTP Server");
        try {
            server = HttpServer.create(new InetSocketAddress(Integer.parseInt(portNumber)), MAX_WAITING_CONNECTIONS);
        } catch (IOException e){
            e.printStackTrace();
            return;
        }

        server.setExecutor(null);

        System.out.println("Creating contexts");
        server.createContext("/", new FileHandler());
        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/user/login", new LoginHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/fill", new FillHandler());
        server.createContext("/load", new LoadHandler());
        server.createContext("/person", new PersonHandler());
        server.createContext("/event", new EventHandler());

        System.out.println("Starting server");
        server.start();
        System.out.println("Server running on 8080");
    }

    public static void main(String[] args) throws DatabaseException {
        String portNumber = "8080"; //args[0];
        new Server().run(portNumber);

    }
}
