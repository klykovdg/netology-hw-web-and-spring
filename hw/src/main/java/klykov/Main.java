package klykov;

import klykov.server.Server;

public class Main {

    public static void main (String... args) {
        new Server().startSever(9999);
    }
}
