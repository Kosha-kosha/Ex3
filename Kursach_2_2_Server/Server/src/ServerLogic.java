import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class ServerLogic extends Thread {
    Socket socket;
    BufferedWriter writer;
    BufferedReader reader;


    ServerLogic(Socket inSocket) {
        socket = inSocket;
    }

    public void send(String massage) throws IOException {
        writer.write(massage);
        writer.newLine();
        writer.flush();
    }
    private static void stringToFile(String data, File file) {
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try {


            writer =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream()));
            reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            DatabaseHandler databaseHandler = new DatabaseHandler();

            System.out.println("Client connected...");


            while (true) {
                int exitAuth = 0;

                while (exitAuth == 0) {

                    String action1 = reader.readLine();
                    System.out.println(action1);



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
                                        send("true");
                                    }


                                } else {
                                    databaseHandler.addUser(newUzer);
                                    {
                                        send("false");
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
                                        send("admin");

                                    } else {

                                        send("true");

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
                                send("admin");

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
                                                send("true");
                                            }
//
//
                                        } else {
                                            databaseHandler.addUser(newUzer);
                                            {
                                                send("false");
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
                                                    send("last");
                                                    count++;
                                                } else {
                                                    send("NotLast");
                                                    count++;
                                                }
                                                send(resultUser.getString("idUsers"));

                                                send(resultUser.getString("login"));

                                                send(resultUser.getString("password"));


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
                                result = mainDatabaseHandler.getDeclartion("ALL");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            int count = 0;
                            do {
                                try {
                                    if (result.isLast()) {
                                        send("last");
                                        count++;
                                    } else {
                                        send("NotLast");
                                        count++;
                                    }
                                    send(result.getString("id_declaration"));

                                    send(result.getString("shipper_number"));

                                    send(result.getString("consignee_number"));

                                    send(result.getString("type_declaration"));

                                    send(result.getString("seats"));

                                    send(result.getString("positions"));

                                    send(result.getString("payment_method_code"));

                                    send(result.getString("shipper_country_code"));

                                    send(result.getString("consignee_country_code"));

                                    send(result.getString("declarant_number"));

                                    send(result.getString("number_ts"));

                                    send(result.getString("gross_weight"));

                                    send(result.getString("net_weight"));

                                    if (result.getString("status") == null) {
                                        send("null");
                                    } else {
                                        send(result.getString("status"));
                                    }

                                    send(result.getString("goods_code"));

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
                                result = mainDatabaseHandler.getDeclartion("ALL");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            int count = 0;
                            do {
                                try {
                                    if (result.isLast()) {
                                        send("last");
                                        count++;
                                    } else {
                                        send("NotLast");
                                        count++;
                                    }
                                    send(result.getString("id_declaration"));

                                    send(result.getString("shipper_number"));

                                    send(result.getString("consignee_number"));

                                    send(result.getString("type_declaration"));

                                    send(result.getString("seats"));

                                    send(result.getString("positions"));

                                    send(result.getString("payment_method_code"));

                                    send(result.getString("shipper_country_code"));

                                    send(result.getString("consignee_country_code"));

                                    send(result.getString("declarant_number"));

                                    send(result.getString("number_ts"));

                                    send(result.getString("gross_weight"));

                                    send(result.getString("net_weight"));

                                    if (result.getString("status") == null) {
                                        send("null");
                                    } else {
                                        send(result.getString("status"));
                                    }

                                    send(result.getString("goods_code"));

                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }

                            } while (result.next());


                            break;
                        }
                        case "viewExport":{
                            System.out.println("читаем из БД");
                            Declaration declaration = new Declaration();

                            MainDatabaseHandler mainDatabaseHandler = new MainDatabaseHandler();
                            ResultSet result = null; //mainDatabaseHandler.getDeclartion(declaration);
                            declaration.setIdDeclaration("11");

                            try {
                                result = mainDatabaseHandler.getDeclartion("EX1");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            int count = 0;
                            do {
                                try {
                                    if (result.isLast()) {
                                        send("last");
                                        count++;
                                    } else {
                                        send("NotLast");
                                        count++;
                                    }
                                    send(result.getString("id_declaration"));

                                    send(result.getString("shipper_number"));

                                    send(result.getString("consignee_number"));

                                    send(result.getString("type_declaration"));

                                    send(result.getString("seats"));

                                    send(result.getString("positions"));

                                    send(result.getString("payment_method_code"));

                                    send(result.getString("shipper_country_code"));

                                    send(result.getString("consignee_country_code"));

                                    send(result.getString("declarant_number"));

                                    send(result.getString("number_ts"));

                                    send(result.getString("gross_weight"));

                                    send(result.getString("net_weight"));

                                    if (result.getString("status") == null) {
                                        send("null");
                                    } else {
                                        send(result.getString("status"));
                                    }

                                    send(result.getString("goods_code"));

                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }

                            } while (result.next());

                            break;
                        }
                        case "viewImport":{
                            System.out.println("читаем из БД");
                            Declaration declaration = new Declaration();

                            MainDatabaseHandler mainDatabaseHandler = new MainDatabaseHandler();
                            ResultSet result = null; //mainDatabaseHandler.getDeclartion(declaration);
                            declaration.setIdDeclaration("11");

                            try {
                                result = mainDatabaseHandler.getDeclartion("IM");
                            } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            int count = 0;
                            do {
                                try {
                                    if (result.isLast()) {
                                        send("last");
                                        count++;
                                    } else {
                                        send("NotLast");
                                        count++;
                                    }
                                    send(result.getString("id_declaration"));

                                    send(result.getString("shipper_number"));

                                    send(result.getString("consignee_number"));

                                    send(result.getString("type_declaration"));

                                    send(result.getString("seats"));

                                    send(result.getString("positions"));

                                    send(result.getString("payment_method_code"));

                                    send(result.getString("shipper_country_code"));

                                    send(result.getString("consignee_country_code"));

                                    send(result.getString("declarant_number"));

                                    send(result.getString("number_ts"));

                                    send(result.getString("gross_weight"));

                                    send(result.getString("net_weight"));

                                    if (result.getString("status") == null) {
                                        send("null");
                                    } else {
                                        send(result.getString("status"));
                                    }

                                    send(result.getString("goods_code"));

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
                                result = mainDatabaseHandler.getDeclartion("ALL");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            int count = 0;
                            do {
                                try {
                                    if (result.isLast()) {
                                        send("last");
                                        count++;
                                    } else {
                                        send("NotLast");
                                        count++;
                                    }
                                    send(result.getString("id_declaration"));

                                    send(result.getString("shipper_number"));

                                    send(result.getString("consignee_number"));

                                    send(result.getString("type_declaration"));

                                    send(result.getString("seats"));

                                    send(result.getString("positions"));

                                    send(result.getString("payment_method_code"));

                                    send(result.getString("shipper_country_code"));

                                    send(result.getString("consignee_country_code"));

                                    send(result.getString("declarant_number"));

                                    send(result.getString("number_ts"));

                                    send(result.getString("gross_weight"));

                                    send(result.getString("net_weight"));

                                    if (result.getString("status") == null) {
                                        send("null");
                                    } else {
                                        send(result.getString("status"));
                                    }

                                    send(result.getString("goods_code"));

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
                        case "method": {
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
                                result = mainDatabaseHandler.getDeclartion("ALL");
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
                                for (count = 0; count < arrayList.size() - 1; count++) {
                                    if (arrayList.get(count) > arrayList.get(count + 1)) {

                                        int dummy = arrayList.get(count);
                                        arrayList.set(count, arrayList.get(count + 1));
                                        arrayList.set(count + 1, dummy);
                                    }
                                    if (count == arrayList.size()) {
                                        break;
                                    }
                                }
                                i++;
                            }

                            System.out.println(arrayList);
                            i = 0;
                            while (i < arrayList.size()) {
                                //arrayList.toString();
                                goodCodeSt = String.valueOf(arrayList.get(i));
                                try {
                                    if (i < arrayList.size() - 1) {
                                        send("NotLast");
                                        count++;
                                    } else {
                                        send("last");
                                        count++;
                                    }
                                    send(mainDatabaseHandler.getIdDeclaration(goodCodeSt));
                                    System.out.println(mainDatabaseHandler.getIdDeclaration(goodCodeSt));
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }

                                i++;
                            }
                            if(reader.readLine().equals("toFile")){
                                i = 0;
                                File file = new File("C:\\Java JDK 8\\Kursach_2_2_Server\\Server\\File.txt");
                                String fileString = "";
                                while (i < arrayList.size()){

                                    goodCodeSt = String.valueOf(arrayList.get(i));
                                    try {
                                        fileString = fileString + mainDatabaseHandler.getIdDeclaration(goodCodeSt) + "\n";
                                        i++;
                                    }catch (ClassNotFoundException e){
                                        e.printStackTrace();
                                    }

                                }
                                stringToFile(fileString,file);
                                System.out.println("Data is saving");
                            }
                            break;
                        }
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}