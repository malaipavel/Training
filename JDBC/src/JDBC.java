import java.io.IOException;
import java.security.acl.LastOwnerException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

/*
 * Disclaimer : Nu am realizat acelasi model ca acel de aici intentionat - http://ftp.nchu.edu.tw/MySQL/tech-resources/articles/intro-to-normalization.html 
 * Aveam cam aceiasi idee de la inceput, insa dupa ce am descoperit asta, m-am condus dupa modelul de acolo.
 */

/** A class that manages operations with a MySQL database */
public class JDBC {
	private String user;
	private String password;
	private String db_name;
	private String adress;

	private Connection connection;
	private Statement statement;

	public static void main(String[] args) throws SQLException{
		JDBC jdbc = new JDBC("root", "", "carti", "jdbc:mysql://127.0.0.1:3306/");
		jdbc.connect();

		String query = "CREATE DATABASE IF NOT EXISTS carti;";
		String query1 = "DROP TABLE IF EXISTS carte ";
		String query2 = "CREATE TABLE carte "
							+ "(carteID INT PRIMARY KEY AUTO_INCREMENT,"
							+ "denumire varchar(50) NOT NULL,"
							+ "data_introd DATE,"
							+ "ISBN INT,"
							+ "format ENUM('pdf', 'doc', 'epub', 'mobi'),"
							+ "autorID INT,"
							+ "INDEX(denumire));";

		String query3 = "DROP TABLE IF EXISTS autori";
		String query4 = "CREATE TABLE autori"
							+ "(autorID INT PRIMARY KEY AUTO_INCREMENT,"
							+ "nume_prenume varchar(50),"
							+ "INDEX(nume_prenume));";

		String query7 = "DROP TABLE IF EXISTS carti_autori;";
		String query8 = "CREATE TABLE carti_autori"
							+ "(carteID INT UNIQUE,"
							+ "autorID INT UNIQUE);";

		jdbc.query(query);
		jdbc.query(query1);
		jdbc.query(query2);
		jdbc.query(query3);
		jdbc.query(query4);
		jdbc.query(query7);
		jdbc.query(query8);
		
		jdbc.insert("Metamorfoza", 9347682, "epub", "Franz Kafka");
		jdbc.insert("Castelul", 6656823, "mobi", "Franz Kafka");
		jdbc.insert("Procesul", 46536556, "doc", "Franz Kafka");
		jdbc.insert("Idiotul", 6545467, "pdf", "Fyodor Dostoievski");
		jdbc.insert("Fratii Karamazov", 349583948, "epub", "Fyodor Dostoievski");
		jdbc.insert("Ana Karenina", 45634543, "epub", "Fyodor Dostoievski");
		jdbc.insert("Fratii Karamazov", 3495839, "epub", "Fyodor Dostoievski");
		jdbc.insert("Lolita", 34534576, "mobi", "William Shakespeare");
		jdbc.insert("Hamlet", 355353, "mobi", "Franz Kafka");
		jdbc.insert("Odissey", 435654756, "pdf", "Homer");
		
		jdbc.dumpDatabase(null);

		ResultSet result = null;
		result = jdbc.selectQuery("SELECT carte.denumire, carte.ISBN, autori.nume_prenume FROM carte "
			+ "INNER JOIN carti_autori ON carte.carteID = carti_autori.carteID "
			+ "INNER JOIN autori ON autori.autorID = carti_autori.autorID;");
		
		while(result.next()){
			System.out.print( "Denumire carte: " + result.getString("denumire"));
			System.out.print(" ISBN: " + result.getInt("ISBN"));
			System.out.println(" Autor: " + result.getString("nume_prenume"));
		}
	}

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

			statement = connection.createStatement();
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
	
	/*
	 * Inserts a book into the table, and the author in another;
	 */
	public void insert(String denumire, int isbn, String format, String autor) throws SQLException{
		
		//begin transaction
		connection.setAutoCommit(false);
		
		//inserarea in tabelul autor
				PreparedStatement pstmt2 = (PreparedStatement)connection.prepareStatement(
					 "INSERT INTO autori(nume_prenume) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
				pstmt2.setString(1, autor);
				pstmt2.executeUpdate();
				
		//inserarea in tabelul carte
		PreparedStatement pstmt = (PreparedStatement)connection.prepareStatement("INSERT "
			+ "INTO carte(denumire, ISBN, data_introd, format) VALUES(?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, denumire);
		pstmt.setInt(2, isbn);
		pstmt.setDate(3, new Date(System.currentTimeMillis()));
		pstmt.setString(4, "pdf");
		pstmt.executeUpdate();
		
		//Memoram id-urile, pentru a le insera in tabelul jonctiune
		Integer carte_id = null;
		Integer autor_id = null;
		ResultSet res1 = pstmt.getGeneratedKeys();
		ResultSet res2 = pstmt2.getGeneratedKeys();
		
		if(res1.next())
      {
          carte_id = res1.getInt(1);
      }
		if(res2.next())
      {
          autor_id = res2.getInt(1);
      }
		
		System.out.println("autor id " + autor_id);
		statement = connection.createStatement();
		statement.executeUpdate("UPDATE carte SET autorID = " + autor_id + " WHERE carteID = " + carte_id + ";");
		statement.execute("INSERT INTO carti_autori VALUES(" + carte_id + "," + autor_id + ");");
		
		//end transaction
		connection.commit();
		System.out.println("Values inserted..");
	}

	/*
	 * Backs up the specified database, if null, dumps all databases.
	 * Doesnt work well.
	 */
	public void dumpDatabase(String database){

		Process process = null;
		String command = null;
		
		if(database == null){
			 command = "mysqldump -u" + user + " -p" + password + " --all-databases >alldatabases.sql;";
		}
		else{
			 command = "mysqldump -u" + user + " -p" + password + " " + database+ " >" + database + ".sql;";
		}
		
		try {
			process = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", command });
			int result = process.waitFor();
			if(result == 0){
				System.out.println("Backup succesful!");
			}
			else{
				System.out.println("Backup failed! Process terminated with code:" + result);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
