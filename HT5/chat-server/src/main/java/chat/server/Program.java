package chat.server;

import com.mysql.cj.xdevapi.Client;
import com.mysql.cj.xdevapi.Session;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5100);
        Server server = new Server(serverSocket);
        server.runServer();
    }
}
