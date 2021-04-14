package sample;

import java.io.*;
import java.net.Socket;

public class Client {

   //static void createWriterReader() throws IOException {

   //

   //        System.out.println("Connected to server...");

   //        String action = reader.readLine();
   //        System.out.printf(action);

   //}

public String openApp() throws IOException {


        String action = Main.reader.readLine();
        System.out.printf(action);
        return action;
}
void dataToServer(String login, String password, String action) throws IOException {

        System.out.println("Connected to server...");
        Main.writer.write(login);
        Main.writer.newLine();
        Main.writer.flush();

        Main.writer.write(password);
        Main.writer.newLine();
        Main.writer.flush();

        Main.writer.write(action);
        Main.writer.newLine();
        Main.writer.flush();

}

}
