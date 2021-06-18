
import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Vector;


public class Server {
    public static ServerSocket server;
    public static BufferedWriter writer;
    public static BufferedReader reader;
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


                while (true) {
                    int exitAuth = 0;

                    while (exitAuth == 0) {

                        String action1 = reader.readLine();
                        System.out.println(action1);

                        DatabaseHandler databaseHandler = new DatabaseHandler();

                        switch (action1) {    // авторизация
                            case "add": {
                                exitAuth = 1;
                                while (true) {
                                    String login = reader.readLine();
                                    String password = reader.readLine();


                                    System.out.println(login);
                                    System.out.println(password);


                                    System.out.println("добавляем");
                                    User newUzer = new User(login, password);
                                    User getUser = new User();
                                    getUser.setLogin(login);
                                    getUser.setPassword(password);
                                    ResultSet result = databaseHandler.getUser(getUser);
                                    int counter = 0;
                                    while (true) {
                                        try {
                                            if (!result.next()) break;
                                        } catch (SQLException throwables) {
                                            throwables.printStackTrace();
                                        }
                                        counter++;
                                    }
                                    if (counter >= 1) {
                                        System.out.println("Пользователь найден!");
                                        {
                                            writer.write("true");
                                            writer.newLine();
                                            writer.flush();
                                        }


                                    } else {
                                        databaseHandler.addUser(newUzer);
                                        {
                                            writer.write("false");
                                            writer.newLine();
                                            writer.flush();
                                        }
                                        break;
                                    }

                                }
                                break;
                            }
                            case "get": {
                                while (true) {
                                    System.out.println("ищем в БД");
                                    String login = reader.readLine();
                                    String password = reader.readLine();


                                    System.out.println(login);
                                    System.out.println(password);

                                    User getUser = new User();

                                    getUser.setLogin(login);
                                    getUser.setPassword(password);
                                    ResultSet result = databaseHandler.getUser(getUser);
                                    int counter = 0;
                                    while (true) {
                                        try {
                                            if (!result.next()) break;
                                        } catch (SQLException throwables) {
                                            throwables.printStackTrace();
                                        }
                                        counter++;
                                    }
                                    if (counter >= 1) {
                                        System.out.println("Пользователь найден!");
                                        if (login.equals("admin") && password.equals("admin")) {
                                            writer.write("admin");
                                            writer.newLine();
                                            writer.flush();

                                        } else {

                                            writer.write("true");
                                            writer.newLine();
                                            writer.flush();

                                            exitAuth = 1;
                                            break;
                                        }

                                    }
                                }
                                break;
                            }
                            case "admin": {
                                System.out.println("ищем в БД");
                                String login = reader.readLine();
                                String password = reader.readLine();


                                System.out.println(login);
                                System.out.println(password);

                                User getUser = new User();

                                getUser.setLogin(login);
                                getUser.setPassword(password);
                                ResultSet result = databaseHandler.getUser(getUser);
                                if (login.equals("admin") && password.equals("admin")) {
                                    writer.write("admin");
                                    writer.newLine();
                                    writer.flush();

                                }
                                int exit = 0;
                                while (exit == 0) {       //меню админа
                                    String actionAdmin = reader.readLine();
                                    switch (actionAdmin) {
                                        case "addUserAdmin": {
//
                                            String admLogin = reader.readLine();
                                            String admPassword = reader.readLine();

                                            System.out.println(actionAdmin);
                                            System.out.println(admLogin);
                                            System.out.println(admPassword);
//
//
                                            System.out.println("добавляем");
                                            User newUzer = new User(admLogin, admPassword);
                                            //User getUser = new User();
                                            getUser.setLogin(admLogin);
                                            getUser.setPassword(admPassword);
                                            result = databaseHandler.getUser(getUser);
                                            int counter = 0;
                                            while (true) {
                                                try {
                                                    if (!result.next()) break;
                                                } catch (SQLException throwables) {
                                                    throwables.printStackTrace();
                                                }
                                                counter++;
                                            }
                                            if (counter >= 1) {
                                                System.out.println("Пользователь найден!");
                                                {
                                                    writer.write("true");
                                                    writer.newLine();
                                                    writer.flush();
                                                }
//
//
                                            } else {
                                                databaseHandler.addUser(newUzer);
                                                {
                                                    writer.write("false");
                                                    writer.newLine();
                                                    writer.flush();
                                                }
                                                break;
                                            }
//
                                            break;
                                        }
                                        case "tableViewUser": {
                                            System.out.println("читаем из БД юзеров");
                                            User user = new User();

                                            //DatabaseHandler databaseHandler1 = new DatabaseHandler();
                                            ResultSet resultUser = null; //mainDatabaseHandler.getDeclartion(declaration);
                                            //declaration.setIdDeclaration("11");


                                            try {
                                                resultUser = databaseHandler.getUserView();
                                            } catch (SQLException throwables) {
                                                throwables.printStackTrace();
                                            } catch (ClassNotFoundException e) {
                                                e.printStackTrace();
                                            }
                                            int count = 0;
                                            do {
                                                try {
                                                    if (resultUser.isLast()) {
                                                        writer.write("last");
                                                        writer.newLine();
                                                        writer.flush();
                                                        count++;
                                                    } else {
                                                        writer.write("NotLast");
                                                        writer.newLine();
                                                        writer.flush();
                                                        count++;
                                                    }
                                                    writer.write(resultUser.getString("idUsers"));
                                                    writer.newLine();
                                                    writer.flush();

                                                    writer.write(resultUser.getString("login"));
                                                    writer.newLine();
                                                    writer.flush();

                                                    writer.write(resultUser.getString("password"));
                                                    writer.newLine();
                                                    writer.flush();


                                                } catch (SQLException throwables) {
                                                    throwables.printStackTrace();
                                                }


                                            } while (resultUser.next());


                                            break;
                                        }
                                        case "exit": {
                                            exit = 1;
                                            break;
                                        }
                                    }

                                }
                                break;
                            }
                            //else )
                        }

                    }


                    // логика приложения
                    int exit = 0;
                    while (exit == 0) {
                        String action2 = reader.readLine();
                        switch (action2) {
                            case "addDec": {
                                System.out.println("добавляем декларацию в БД");
                                //Declaration newDec = new Declaration();
                                String idDeclaration;
                                idDeclaration = UUID.randomUUID().toString();
                                String shipperNumber = reader.readLine(), consigneeNumber = reader.readLine(), typeDeclaration = reader.readLine();
                                String seats = reader.readLine(), positions = reader.readLine(), paymentMethodCode = reader.readLine();
                                String shipperCountryCode = reader.readLine(), consigneeCountryCode = reader.readLine(), declarantNumber = reader.readLine();
                                String numberTS = reader.readLine(), grossWeight = reader.readLine(), netWeight = reader.readLine(), status = "null";
                                String goodsCode = reader.readLine();

                                if (typeDeclaration.equals("EX1") | !consigneeCountryCode.equals("BY")) {
                                    status = "Досм.";
                                } else {
                                    status = "К оформл.";
                                }


                                Declaration newDec = new Declaration(idDeclaration, shipperNumber, consigneeNumber, typeDeclaration, seats, positions, paymentMethodCode,
                                        shipperCountryCode, consigneeCountryCode, declarantNumber, numberTS, grossWeight, netWeight, status, goodsCode);
                                MainDatabaseHandler mainDatabaseHandler = new MainDatabaseHandler();
                                mainDatabaseHandler.addDeclaration(newDec);
                                break;
                            }
                            case "deleteDec": {
                                System.out.println("читаем из БД");
                                Declaration declaration = new Declaration();

                                MainDatabaseHandler mainDatabaseHandler = new MainDatabaseHandler();
                                ResultSet result = null; //mainDatabaseHandler.getDeclartion(declaration);
                                declaration.setIdDeclaration("11");

                                try {
                                    result = mainDatabaseHandler.getDeclartion();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                                int count = 0;
                                do {
                                    try {
                                        if (result.isLast()) {
                                            writer.write("last");
                                            writer.newLine();
                                            writer.flush();
                                            count++;
                                        } else {
                                            writer.write("NotLast");
                                            writer.newLine();
                                            writer.flush();
                                            count++;
                                        }
                                        writer.write(result.getString("id_declaration"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("shipper_number"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("consignee_number"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("type_declaration"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("seats"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("positions"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("payment_method_code"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("shipper_country_code"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("consignee_country_code"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("declarant_number"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("number_ts"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("gross_weight"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("net_weight"));
                                        writer.newLine();
                                        writer.flush();

                                        if (result.getString("status") == null) {
                                            writer.write("null");
                                            writer.newLine();
                                            writer.flush();
                                        } else {
                                            writer.write(result.getString("status"));
                                            writer.newLine();
                                            writer.flush();
                                        }

                                        writer.write(result.getString("goods_code"));
                                        writer.newLine();
                                        writer.flush();

                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }

                                } while (result.next());

                                String deleteIdDec = reader.readLine();
                                if (deleteIdDec.equals("exit")) {
                                    break;
                                } else {
                                    try {
                                        mainDatabaseHandler.deleteDeclaration(deleteIdDec);
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }


                                break;
                            }
                            case "viewDB": {
                                System.out.println("читаем из БД");
                                Declaration declaration = new Declaration();

                                MainDatabaseHandler mainDatabaseHandler = new MainDatabaseHandler();
                                ResultSet result = null; //mainDatabaseHandler.getDeclartion(declaration);
                                declaration.setIdDeclaration("11");

                                try {
                                    result = mainDatabaseHandler.getDeclartion();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                                int count = 0;
                                do {
                                    try {
                                        if (result.isLast()) {
                                            writer.write("last");
                                            writer.newLine();
                                            writer.flush();
                                            count++;
                                        } else {
                                            writer.write("NotLast");
                                            writer.newLine();
                                            writer.flush();
                                            count++;
                                        }
                                        writer.write(result.getString("id_declaration"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("shipper_number"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("consignee_number"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("type_declaration"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("seats"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("positions"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("payment_method_code"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("shipper_country_code"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("consignee_country_code"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("declarant_number"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("number_ts"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("gross_weight"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("net_weight"));
                                        writer.newLine();
                                        writer.flush();

                                        if (result.getString("status") == null) {
                                            writer.write("null");
                                            writer.newLine();
                                            writer.flush();
                                        } else {
                                            writer.write(result.getString("status"));
                                            writer.newLine();
                                            writer.flush();
                                        }

                                        writer.write(result.getString("goods_code"));
                                        writer.newLine();
                                        writer.flush();

                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }

                                } while (result.next());


                                break;
                            }
                            case "editDec": {
                                System.out.println("читаем из БД");
                                Declaration declaration = new Declaration();

                                MainDatabaseHandler mainDatabaseHandler = new MainDatabaseHandler();
                                ResultSet result = null; //mainDatabaseHandler.getDeclartion(declaration);
                                declaration.setIdDeclaration("11");

                                try {
                                    result = mainDatabaseHandler.getDeclartion();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                                int count = 0;
                                do {
                                    try {
                                        if (result.isLast()) {
                                            writer.write("last");
                                            writer.newLine();
                                            writer.flush();
                                            count++;
                                        } else {
                                            writer.write("NotLast");
                                            writer.newLine();
                                            writer.flush();
                                            count++;
                                        }
                                        writer.write(result.getString("id_declaration"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("shipper_number"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("consignee_number"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("type_declaration"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("seats"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("positions"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("payment_method_code"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("shipper_country_code"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("consignee_country_code"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("declarant_number"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("number_ts"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("gross_weight"));
                                        writer.newLine();
                                        writer.flush();

                                        writer.write(result.getString("net_weight"));
                                        writer.newLine();
                                        writer.flush();

                                        if (result.getString("status") == null) {
                                            writer.write("null");
                                            writer.newLine();
                                            writer.flush();
                                        } else {
                                            writer.write(result.getString("status"));
                                            writer.newLine();
                                            writer.flush();
                                        }

                                        writer.write(result.getString("goods_code"));
                                        writer.newLine();
                                        writer.flush();

                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }

                                } while (result.next());

                                String deleteIdDec = reader.readLine();
                                if (deleteIdDec.equals("exit")) {
                                    break;
                                } else {
                                    try {
                                        mainDatabaseHandler.deleteDeclaration(deleteIdDec);
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }

                                System.out.println("добавляем декларацию в БД");

                                String idDeclaration;
                                idDeclaration = UUID.randomUUID().toString();
                                String shipperNumber = reader.readLine(), consigneeNumber = reader.readLine(), typeDeclaration = reader.readLine();
                                String seats = reader.readLine(), positions = reader.readLine(), paymentMethodCode = reader.readLine();
                                String shipperCountryCode = reader.readLine(), consigneeCountryCode = reader.readLine(), declarantNumber = reader.readLine();
                                String numberTS = reader.readLine(), grossWeight = reader.readLine(), netWeight = reader.readLine(), status = null;
                                String goodsCode = reader.readLine();

                                Declaration newDec = new Declaration(idDeclaration, shipperNumber, consigneeNumber, typeDeclaration, seats, positions, paymentMethodCode,
                                        shipperCountryCode, consigneeCountryCode, declarantNumber, numberTS, grossWeight, netWeight, status, goodsCode);

                                mainDatabaseHandler.addDeclaration(newDec);

                                break;
                            }
                            case "exit": {
                                exit = 1;
                                break;
                            }
                            case "method":{
                                System.out.println("читаем из БД");
                                Declaration declaration = new Declaration();

                                String goodCodeSt = null;
                                int goodCodeInt = 0;
                                //Vector vector = new Vector();
                                ArrayList<Integer> arrayList = new ArrayList<Integer>();
                                MainDatabaseHandler mainDatabaseHandler = new MainDatabaseHandler();
                                ResultSet result = null; //mainDatabaseHandler.getDeclartion(declaration);
                                declaration.setIdDeclaration("11");

                                try {
                                    result = mainDatabaseHandler.getDeclartion();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                                int count = 0;
                                do {

                                    goodCodeSt = result.getString("goods_code");
                                    goodCodeInt = Integer.parseInt(goodCodeSt);
                                    arrayList.add(goodCodeInt);


                                } while (result.next());

                                int i = 0;
                                while (i < arrayList.size() - 1) {
                                    for(count = 0; count < arrayList.size() - 1; count++) {
                                        if (arrayList.get(count) > arrayList.get(count + 1)) {

                                            int dummy = arrayList.get(count);
                                            arrayList.set(count, arrayList.get(count + 1));
                                            arrayList.set(count + 1, dummy);
                                        }
                                        if(count == arrayList.size()){
                                            break;
                                        }
                                    }
                                    i ++;
                                }

                                System.out.println(arrayList);
                                i = 0;
                                while (i<arrayList.size()){
                                    //arrayList.toString();
                                    goodCodeSt = String.valueOf(arrayList.get(i));
                                    try {
                                        if (i<arrayList.size()-1) {
                                            writer.write("NotLast");
                                            writer.newLine();
                                            writer.flush();
                                            count++;
                                        } else {
                                            writer.write("last");
                                            writer.newLine();
                                            writer.flush();
                                            count++;
                                        }
                                        writer.write( mainDatabaseHandler.getIdDeclaration(goodCodeSt));
                                        writer.newLine();
                                        writer.flush();
                                        System.out.println( mainDatabaseHandler.getIdDeclaration(goodCodeSt));
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }

                                    i++;
                                }

                                break;
                            }
                        }
                    }
                }

            }catch (NullPointerException e) {
                e.printStackTrace();} catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

