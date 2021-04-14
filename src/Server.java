
import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Server {
    public static void main(String[] args) throws IOException {

        try (ServerSocket server = new ServerSocket(8000);) {

            System.out.println("Server Started!");
            while(true)

            try (
                    Socket socket = server.accept();

                 BufferedWriter writer =
                         new BufferedWriter(
                                 new OutputStreamWriter(
                                        socket.getOutputStream()));
                 BufferedReader reader =
                         new BufferedReader(
                                 new InputStreamReader(
                                         socket.getInputStream()))
            )

            {

                System.out.println("Client connected...");


                String login = reader.readLine();
                String password = reader.readLine();
                String action = reader.readLine();


                System.out.println(login);
                System.out.println(password);
                System.out.println(action);

                DatabaseHandler databaseHandler = new DatabaseHandler();

                switch (action){
                    case "add":
                        System.out.println("добавляем");
                        User newUzer = new User(login, password);

                        databaseHandler.addUser(newUzer);
                        break;
                    case "get":
                        System.out.println("ищем в БД");
                        User getUser = new User();

                        getUser.setLogin(login);
                        getUser.setPassword(password);
                        ResultSet result = databaseHandler.getUser(getUser);
                        int counter = 0;
                        while(true){
                            try {
                                if (!result.next()) break;
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            counter++;
                        }
                        if(counter>=1){
                            System.out.println("Пользователь найден!");

                            writer.write("true");
                            writer.newLine();
                            writer.flush();


                        }
                        //else )
                }

                //  newUzer.getData(login,password);

                //writer.write("HELLO WORLD " + reqest.length());
                //writer.newLine();
                //writer.flush();
               // }
/*
                if(action.equals("add")){
                    System.out.println("добавляем");
                User newUzer = new User(login, password);
                DatabaseHandler databaseHandler = new DatabaseHandler();
                databaseHandler.addUser(newUzer);
                }

                if(action.equals("get")){
                    System.out.println("ищем в БД");
                    User getUser = new User();
                    DatabaseHandler databaseHandler = new DatabaseHandler();
                    getUser.setLogin(login);
                    getUser.setPassword(password);
                    ResultSet result = databaseHandler.getUser(getUser);
                    int counter = 0;
                    while(true){
                        try {
                            if (!result.next()) break;
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        counter++;
                    }
                    if(counter>=1){
                        System.out.println("Пользователь найден!");

                            writer.write("true");
                            writer.newLine();
                            writer.flush();
                    }
                }
              //  newUzer.getData(login,password);

                //writer.write("HELLO WORLD " + reqest.length());
                //writer.newLine();
                //writer.flush();*/
            }catch (NullPointerException e) {
                e.printStackTrace();}


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void addData(){

    }
}

