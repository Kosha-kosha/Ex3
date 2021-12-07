import com.mysql.cj.jdbc.result.ResultSetImpl;

import java.sql.*;
public class MainDatabaseHandler extends Configs{
    Connection dbConnection;
    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException
    {
        String connectionString = "jdbc:mysql://" + "127.0.0.1" + ":"
                + "3306" + "/" + "declaration";

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser,dbPass);

        return dbConnection;
    }
    public void addDeclaration(Declaration declaration){
        MainDatabaseHandler mDbHandler = new MainDatabaseHandler();
        String insert = "INSERT INTO " + Const.DECLARATION_TABLE + "(" +
               Const.DECLARATION_id + "," + Const.DECLARATION_shipperNumber + "," + Const.DECLARATION_consigneeNumber + ","
               + Const.DECLARATION_typeDeclaration + "," + Const.DECLARATION_seats + "," + Const.DECLARATION_positions + ","
               + Const.DECLARATION_paymentMethodCode + "," + Const.DECLARATION_shipperCountryCode + ","
               + Const.DECLARATION_consigneeCountryCode + "," + Const.DECLARATION_declarantNumber + ","
               + Const.DECLARATION_numberTS + "," + Const.DECLARATION_grossWeight + "," + Const.DECLARATION_netWeight + ","
                + Const.DECLARATION_status + "," + Const.DECLARATION_goodsCode + ")" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            System.out.printf("Данные о декларации записались в DB...");
            preparedStatement.setString(1, declaration.getIdDeclaration()); // нужен string, а поле int
            preparedStatement.setString(2, declaration.getShipperNumber());
            preparedStatement.setString(3, declaration.getConsigneeNumber());
            preparedStatement.setString(4, declaration.getTypeDeclaration());
            preparedStatement.setString(5, declaration.getSeats());
            preparedStatement.setString(6, declaration.getPositions());
            preparedStatement.setString(7, declaration.getPaymentMethodCode());
            preparedStatement.setString(8, declaration.getShipperCountryCode());
            preparedStatement.setString(9, declaration.getConsigneeCountryCode());
            preparedStatement.setString(10, declaration.getDeclarantNumber());
            preparedStatement.setString(11, declaration.getNumberTS());
            preparedStatement.setString(12, declaration.getGrossWeight());
            preparedStatement.setString(13, declaration.getNetWeight());
            preparedStatement.setString(14, declaration.getStatus());
            preparedStatement.setString(15, declaration.getGoodsCode());



            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getDeclartion(String typeDecl) throws SQLException, ClassNotFoundException {
        Declaration declaration = new Declaration();
        //ResultSet resultSet = null;
        Statement stmt = null;
        String select = "";
        if(typeDecl.equals("ALL")) {
            select = "SELECT * FROM " + "declaration";
            //try {
        }
        if(typeDecl.equals("EX1")){
            select = "SELECT * FROM " + "declaration WHERE type_declaration =" + typeDecl;
        }
        if (typeDecl.equals("IM")){
            select = "SELECT * FROM " + "declaration WHERE type_declaration =" + "possbl_loxov";
        }
            stmt = getDbConnection().createStatement();

            ResultSet resultSet = stmt.executeQuery(select);
            resultSet.next();
//

            return resultSet;
        //} catch (SQLException | ClassNotFoundException throwables) {
        //    throwables.printStackTrace();
        //}


    }


    public String getIdDeclaration(String goodCode) throws SQLException, ClassNotFoundException {
        String idDeclatation = null;
        //ResultSet resultSet = null;
        Statement stmt = null;
        String select = "SELECT id_declaration FROM " + "declaration WHERE goods_code =" + goodCode;
        //try {

        stmt = getDbConnection().createStatement();

        ResultSet resultSet = stmt.executeQuery(select);
        resultSet.next();
        idDeclatation = resultSet.getString("id_declaration");
//

        return idDeclatation;
    }

    public void deleteDeclaration(String deleteIdDec) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        String update = "DELETE FROM declaration WHERE id_declaration = " + deleteIdDec;

        stmt = getDbConnection().createStatement();
        stmt.executeUpdate(update);


    }
}


