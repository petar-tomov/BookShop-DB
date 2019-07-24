import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

public class DBConnector {
	
	private static Connection connect = null;
	
	static Connection getConnection() {
		try {
			Class.forName("org.h2.Driver");
			connect = DriverManager.getConnection
					("jdbc:h2:tcp://localhost/D:/University, 2nd year/Практикум по ООП и БД/projectMaster/driver/DB", "sa", "");
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connect;	
	}
	
	/*static void fillComboBook(JComboBox jComboBox) {

        String command = "select ISBN, TITLE, YEAR, PRICE, GENRE from BOOK";
        PreparedStatement state;
        jComboBox.removeAllItems();
        try {
            state = connect.prepareStatement(command);
            ResultSet resultSet = state.executeQuery();

            jComboBox.addItem("ISBN Title Author Year Price Genre");
            while(resultSet.next()) {
                String line = resultSet.getString("ISBN")+" "+
                		resultSet.getString("Title")+" "+
                		resultSet.getString("Year")+ " "+
                		resultSet.getString("Price")+ " "+
                		resultSet.getString("Genre");
                jComboBox.addItem(line);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
	
	static void fillComboBook(JComboBox jComboBox) {

        String command = "select TITLE from BOOK";
        PreparedStatement state;
        jComboBox.removeAllItems();
        try {
            state = connect.prepareStatement(command);
            ResultSet resultSet = state.executeQuery();

            
            while(resultSet.next()) {
                String line = resultSet.getString("Title");
                jComboBox.addItem(line);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
    /*static void fillComboAuthor(JComboBox jComboBox) {

        String command = "select FIRST_NAME, LAST_NAME from AUTHOR";
        PreparedStatement state;
        jComboBox.removeAllItems();
        try {
            state = connect.prepareStatement(command);
            ResultSet rs = state.executeQuery();

            jComboBox.addItem("FirstName LastName");
            while(rs.next()) {
                String line = rs.getString("First_Name")+"   "+rs.getString("Last_Name");
                jComboBox.addItem(line);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }*/
    
    
    static void fillComboAuthor(JComboBox jComboBox) {

        String command = "select LAST_NAME from AUTHOR";
        PreparedStatement state;
        jComboBox.removeAllItems();
        try {
            state = connect.prepareStatement(command);
            ResultSet rs = state.executeQuery();

            
            while(rs.next()) {
                String line = rs.getString("Last_Name");
                jComboBox.addItem(line);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    
    static void fillComboBookAuthor(JComboBox jComboBox) {

    	//TO DO: join BOOK, BOOK_AUTHOR, AUTHOR 
        String command = "select FIRST_NAME, LAST_NAME from AUTHOR";
        PreparedStatement state;
        jComboBox.removeAllItems();
        try {
            state = connect.prepareStatement(command);
            ResultSet rs = state.executeQuery();

            jComboBox.addItem("FirstName LastName");
            while(rs.next()) {
                String line = rs.getString("First_Name")+"   "+rs.getString("Last_Name");
                jComboBox.addItem(line);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    
    static void fillComboGenre(JComboBox jComboBox) {

        String command = "select NAME from GENRE";
        PreparedStatement state;
        jComboBox.removeAllItems();
        try {
            state = connect.prepareStatement(command);
            ResultSet rs = state.executeQuery();

            //jComboBox.addItem("Name");
            while(rs.next()) {
                String line = rs.getString("Name");
                jComboBox.addItem(line);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
