package Database.controller;

import javax.swing.JOptionPane;

import java.sql.*;

public class DatabaseController
{
	
	private String connectionString;
	private Connection databaseConnection;
	private DatabaseAppController baseController;
	
	/**
	 * The database controller, passed through the base controller and calls for the program
	 * to check the driver and set-up a connection between the SQL server and the program.
	 * @param baseController The controller that runs the program.
	 */
	public DatabaseController(DatabaseAppController baseController)
	{
		this.baseController = baseController;
		this.connectionString = "jdbc:mySQL://localhost/pokemon_turf_wars?user=root";
		checkDriver();
		setupConnection();
	}
	/**
	 * Checks for the SQL driver. If it doesn't exist the program displays an error message and then shuts down.
	 */
	private void checkDriver()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception currentException)
		{
			displayErrors(currentException);
			System.exit(1);
		}
	}
	
	/**
	 * Closes the connection to the database and displays an error message if it fails.
	 */
	public void closeConnection()
	{
		try
		{
			databaseConnection.close();
		}
		catch (SQLException currentException)
		{
			displayErrors(currentException);
		}
	}
	/**
	 * Set-up the connection with the SQL server, displays an error message if it fails.
	 */
	private void setupConnection()
	{
		try
		{
			databaseConnection = DriverManager.getConnection(connectionString);
		}
		catch(SQLException currentException)
		{
			displayErrors(currentException);
		}
		
	}
	
	
	public String displayTables()

	{
		String tableNames = "";
		String query = "SHOW TABLES";
		
		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);
			while(answers.next())
			{
				tableNames += answers.getString(1) + "\n";
			}
		}
		catch(SQLException currentError)
		{
			displayErrors(currentError);
		}
		
		return tableNames;
	}
	
	public int insertSample()
	{
		int rowsAffected = -1;
		String query = "INSERT INTO `pokemon_turf_wars`.`teams`"
				+ "(`owner`, `id`, `team`)" 
				+ "VALUES (6, 6,'Me');";
		
		try
		{
			Statement insertStatement = databaseConnection.createStatement();
			rowsAffected = insertStatement.executeUpdate(query);
			insertStatement.close();
		}
		catch(SQLException currentError)
		{
			displayErrors(currentError);
		}
		
		return rowsAffected;
	}
	
	/**
	 * Gets the errors and displays them. Displays the Exception, the SQL State, and the Error code.
	 * @param currentException The variable giving the exception/error.
	 */
	public void displayErrors(Exception currentException)
	{
		JOptionPane.showMessageDialog(baseController.getAppFrame(), "Exception: " + currentException.getMessage());
		if(currentException instanceof SQLException)
		{
			JOptionPane.showMessageDialog(baseController.getAppFrame(), "SQL State: " + ((SQLException) currentException).getSQLState());
			JOptionPane.showMessageDialog(baseController.getAppFrame(), "SQL Error Code: " + ((SQLException) currentException).getErrorCode());
		}
	}

}
