package chat.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable{
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    public static ArrayList<ClientManager> clients = new ArrayList<>();

    public ClientManager(Socket socket){

        try {
            this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clients.add(this);
            name = bufferedReader.readLine();
            System.out.println(name + " зашел в чат.");
            broadcastMessage("Server: " + name + " зашел в чат.");
        } catch (Exception e){
            closeAll(socket, bufferedReader, bufferedWriter);
        }


    }

    private void closeAll(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClient();
        try {
            if (bufferedReader != null) bufferedReader.close();
            if (bufferedWriter != null) bufferedWriter.close();
            if (socket != null) socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void removeClient(){
        clients.remove(this);
        System.out.println(name + " вышел из чата.");
        broadcastMessage("Server: " + name + " вышел из чата.");
    }

    private void broadcastMessage(String message) {
            for (ClientManager client : clients) {
                try {
                    if (!client.equals(this) && message != null) {
                        client.bufferedWriter.write(message);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    }
                }  catch (Exception e) {
                    closeAll(socket, bufferedReader, bufferedWriter);
                }
            }
        }

    private void broadcastMessage(String message, ClientManager clientPersonalMessage) {
        try {
            if (clientPersonalMessage == null) {
                this.bufferedWriter.write("Нет клиента с указанным именем для отправки личного сообщения");
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();

            } else {
                clientPersonalMessage.bufferedWriter.write(message);
                clientPersonalMessage.bufferedWriter.newLine();
                clientPersonalMessage.bufferedWriter.flush();
            }
        } catch (Exception e) {
            closeAll(socket, bufferedReader, bufferedWriter);
        }
    }


        @Override
        public void run() {
        String messageFromClient;
        while (!socket.isClosed()){
            try {
                messageFromClient = bufferedReader.readLine();
                if(nameSplitter(messageFromClient) == null){
                    broadcastMessage(messageFromClient);
                } else {
                 ClientManager clientPersonalMessage = findClient(messageFromClient);
                 broadcastMessage(messageFromClient, clientPersonalMessage);
                }
            } catch (Exception e) {
                closeAll(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    private ClientManager findClient(String value) {
        for(ClientManager client: clients){
            if(client.name.equals(nameSplitter(value)) && !name.equals(nameSplitter(value))){
                return client;
            }
        }
        return null;
    }



    private static String nameSplitter(String value) {
        int indexOfNameStart = value.indexOf("@");
        System.out.println(indexOfNameStart);
        String name = null;

        if (indexOfNameStart == -1) return null;
        else {
            int indexOfNameFinish = value.substring(indexOfNameStart).indexOf(" ");
            System.out.println(indexOfNameFinish);
            if (indexOfNameFinish == -1) {
                name = value.substring(indexOfNameStart + 1);
            } else {
                name = value.substring(indexOfNameStart + 1, indexOfNameFinish + indexOfNameStart);
            }
        }
        return name;
    }
}
