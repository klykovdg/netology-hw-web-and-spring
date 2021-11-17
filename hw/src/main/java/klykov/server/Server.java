package klykov.server;

import klykov.connection.Connection;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class Server {
    private ExecutorService executor = null;
    private static final int NUM_TREADS = 64;

    public void startSever(int port) {
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) handle(server.accept());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            executor = null;
        }
    }

    private void handle(Socket socket) {
        if (executor == null) {
            executor = Executors.newFixedThreadPool(NUM_TREADS);
        }
        executor.execute(new Connection(socket));
    }
}
