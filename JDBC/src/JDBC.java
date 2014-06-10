import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

	private String USER;
	private String PASSWORD;
	private String DB_NAME;
	
	private Connection connection;
	private Statement statement;
	
	public JDBC(String user, String password, String db_name){
		this.USER = user;
		this.PASSWORD = password;
		this.DB_NAME = db_name;
	}

	public void connect(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + DB_NAME, USER, PASSWORD);
			System.out.println("Connection Succesful!");
		}
		catch(SQLException e){
			System.out.println("Connection unsuccesful!");
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	public void closeConnection() throws SQLException{
		if(connection != null){
			connection.close();
		}
	}
	
	public ResultSet selectQuery(String query) throws SQLException{
		
		try{
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			return result;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws SQLException{
		JDBC jdbc = new JDBC("root", "", "carti");
		jdbc.connect();
		
		ResultSet result = jdbc.selectQuery("Select * from carte;");
		while(result.next()){
			System.out.print(result.getInt("id") + " ");
			System.out.print(result.getString("denumire"));
			System.out.println("");
		}
		
		jdbc.closeConnection();
	}
}
