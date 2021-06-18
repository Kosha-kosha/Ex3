
import java.sql.*;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection()
        throws ClassNotFoundException, SQLException
    {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser,dbPass);

        return dbConnection;
    }
    public void addUser(User user){
        DatabaseHandler dbHandler = new DatabaseHandler();
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USERS_LOGIN + "," + Const.USERS_PASS + ")" +
                "VALUES(?,?)";
        try {
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            System.out.printf("Данные записались в DB...");
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());


            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getUser(User user){
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_LOGIN + "=? AND " + Const.USERS_PASS + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            //System.out.printf("Данные записались в DB...");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());


            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public ResultSet getUserView() throws SQLException, ClassNotFoundException {

        Statement stmt = null;
        String select = "SELECT idUsers, login, password FROM " + "users";
        //try {

        stmt = getDbConnection().createStatement();

        ResultSet resultSet = stmt.executeQuery(select);
        resultSet.next();
        return resultSet;
    }
}
