import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** A class that manages all operations with a MySQL database */
public class JDBC {
	private String user;
	private String password;
	private String db_name;
	private String adress;
	
	private Connection connection;
	private Statement statement;
	
	public JDBC(String user, String password, String db_name, String adress){
		this.user = user;
		this.password = password;
		this.db_name = db_name;
		this.adress = adress;
	}
	public JDBC(){
	}
	
	public void setCredentials(String user, String passwword, String db_name, String adress){
		this.user = user;
		this.password = passwword;
		this.db_name = db_name;
		this.adress = adress;
	}
	
	/** Coonects to the database */
	public void connect(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(adress + db_name, user, password);
			System.out.println("Connection Succesful!");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
			System.exit(1);
		}
		catch(SQLException e){
			System.out.println("Connection unsuccesful!");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/** Closes the database connection */
	public void closeConnection() throws SQLException{
		if(connection != null){
			connection.close();
		}
	}
	
	public ResultSet selectQuery(String query){
		
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
		JDBC jdbc = new JDBC("root", "", "carti", "jdbc:mysql://127.0.0.1:3306/");
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
