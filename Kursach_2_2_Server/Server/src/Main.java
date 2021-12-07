
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {
    public static ServerSocket server;
    public static BufferedWriter writer;
    public static BufferedReader reader;

    public static void main(String[] args) throws IOException {

        try (ServerSocket server = new ServerSocket(8000);) {

            System.out.println("Server Started!");
            while (true)

                try {

                    Socket socket = null;
                    //socket = server.accept();


                    while (true) {
                        socket = server.accept();  // сервер принимает запрос на подключение клиента
                        System.out.println(" >> " + "Client No:" + " started!");
                        ServerLogic sct = new ServerLogic(socket); // отправляем запрос в отдельный поток
                        sct.start();
                    }


                } catch (IOException e) {
                    e.printStackTrace();

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }


        }


    }
}

