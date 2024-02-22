package chat.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите Ваше имя:");
        String name = scanner.nextLine();
        InetAddress inetAddress = InetAddress.getLocalHost();
        Socket socket = new Socket(inetAddress, 5100);
        ChatClient chatClient = new ChatClient(socket, name);

        InetAddress currentAddress = socket.getInetAddress();

        System.out.println("InetAddress: " + currentAddress);
        String remoteIp = currentAddress.getHostAddress();
        System.out.println("Remote IP: " + remoteIp);
        System.out.println("LocalPort: " + socket.getLocalPort());
        System.out.println("Добро пожаловать в чат, " + name);
        System.out.println("Для отправки личных сообщений укажите в сообщении @имя.");
        chatClient.listenServer();
        chatClient.sendMessageToServer();
    }
}
