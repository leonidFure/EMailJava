package dataBase;

import java.sql.*;

public class DB {
    private final String url = "jdbc:sqlserver://";
    private final String serverName= "localhost";
    private final String portNumber = "1433";
    private final String databaseName= "JavaDB";
    private String connectionUrl = url + serverName + ":" + portNumber + ";databaseName=" + databaseName + ";integratedSecurity=true" ;

    private String field;

    public DB(String field){
        this.field = field;
    }
    public String select() {
        String res = field+":<br>";
        Connection con = null;
        try {
            //Указываем название драйвера, если требуется
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Выполняем подключение
            con = DriverManager.getConnection(connectionUrl);

            //Выполняем наш SQL запрос
            String sqlSelect = "SELECT * FROM [JavaDB].[dbo].[Users]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlSelect);

            //Обрабатываем полученные данные от БД
            while (rs.next()) {
                res+=rs.getString(field)+"<br>";
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}
