package Project.DAO;
import java.sql.*;
public class DAOgem {
    public static Connection getConnection(String id ,String name,String origin, double value) throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/fund";
        String username ="root";
        String password ="1234";
        try(Connection con = DriverManager.getConnection(url, username, password)) {
            Class.forName(driver).newInstance();
            System.out.println("Connected successfully!");
            insert(con,id,name,origin,value);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public static Connection getConnection(String id ,String color, int transparency, int gemCutting) throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/fund";
        String username ="root";
        String password ="1234";
        try(Connection con = DriverManager.getConnection(url, username, password)) {
            Class.forName(driver).newInstance();
            System.out.println("Connected successfully!");
            insertV(con,id,color,transparency,gemCutting);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    private static void insertV(Connection con, String id,String color, int transparency, int gemCutting) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("insert into visualparametrs(id,color,transparency,gemCutting) values(?,?,?,?)");
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, color);
        preparedStatement.setString(3, String.valueOf(transparency));
        preparedStatement.setString(4, String.valueOf(gemCutting));
        preparedStatement.executeUpdate();
        System.out.println("Record is inserted into visualparametrs table!");
    }

    private static void insert(Connection con ,String id, String name ,String origin, double value) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("insert into rock(id,name,origin,value) values(?,?,?,?)");
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, origin);
        preparedStatement.setString(4, String.valueOf(value));
        preparedStatement.executeUpdate();
        System.out.println("Record is inserted into Gem table!");
    }
}
