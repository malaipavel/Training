import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** A class that manages operations with a MySQL database */
public class JDBC {
	private String user;
	private String password;
	private String db_name;
	private String adress;
	
	private Connection connection;
	private Statement statement;
	
	public JDBC(String user, String password, String db_name, String adress){
		setCredentials(user, password, db_name, adress);
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
	
	public void query(String query){
		try {
			statement = connection.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException{
		JDBC jdbc = new JDBC("root", "", "carti", "jdbc:mysql://127.0.0.1:3306/");
		jdbc.connect();
		
		String query1 = "DROP TABLE IF EXISTS carti ";
		String query2 = "CREATE TABLE carti "
							+ "(carteID INT PRIMARY KEY AUTO_INCREMENT,"
							+ "denumire varchar(50) NOT NULL,"
							+ "data_publ DATE,"
							+ "data_introd DATE,"
							+ "ISBN INT,"
							+ "formate_accesibile ENUM('pdf', 'doc', 'epub', 'mobi'),"
							+ "autorID INT NOT NULL,"
							+ "genID INT NOT NULL,"
							+ "INDEX(denumire));";
		
		String query3 = "DROP TABLE IF EXISTS autori";
		String query4 = "CREATE TABLE autori"
							+ "(autorID INT PRIMARY KEY,"
							+ "nume_prenume varchar(50),"
							+ "data_nast DATE,"
							+ "data_deced DATE,"
							+ "INDEX(nume_prenume));";
		
		String query5 = "DROP TABLE IF EXISTS genuri";
		String query6 = "CREATE TABLE genuri"
			+ "(genID int PRIMARY KEY,"
			+ "denumire_gen varchar(50));";
		
		String query7 = "DROP TABLE IF EXISTS carti_autori;";
		String query8 = "CREATE TABLE carti_autori"
			+ "(carteID INT UNIQUE,"
			+ "autorID INT UNIQUE);";
		
		String query9 = "DROP TABLE IF EXISTS carti_gen;";
		String query10 = "CREATE TABLE carti_gen"
			+ "(carteID INT UNIQUE,"
			+ "genID INT UNIQUE);";
		
		jdbc.query(query1);
		jdbc.query(query2);
		jdbc.query(query3);
		jdbc.query(query4);
		jdbc.query(query5);
		jdbc.query(query6);
		jdbc.query(query7);
		jdbc.query(query8);
		jdbc.query(query9);
		jdbc.query(query10);
		
		/*ResultSet result = null;
		result = jdbc.selectQuery("select * from carte;");
		while(result.next()){
			System.out.print(result.getInt("id") + " ");
			System.out.print(result.getString("denumire"));
			System.out.println("");
		}*/
		
	}
}
