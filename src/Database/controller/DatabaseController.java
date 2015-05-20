package Database.controller;

import javax.swing.JOptionPane;

import Database.model.QueryInfo;

import java.sql.*;

public class DatabaseController
{
	
	private String connectionString;
	private Connection databaseConnection;
	private DatabaseAppController baseController;
	private String query;
	private String currentQuery;
	private long queryTime;
	
	/**
	 * The database controller, passed through the base controller and calls for the program
	 * to check the driver and set-up a connection between the SQL server and the program.
	 * @param baseController The controller that runs the program.
	 */
	public DatabaseController(DatabaseAppController baseController)
	{
		this.connectionString = "jdbc:mysql://localhost/pokemon_turf_wars?user=root";
		this.baseController = baseController;
		
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
	 * Makes the connection string.
	 * @param pathToDBServer The path to the Database Server (this will be an IP or Localhost)
	 * @param databaseName The Name of the Database being pulled up.
	 * @param userName The Username for getting into the Database.
	 * @param password The password for getting into the Database.
	 */
	public void connectionStringBuilder(String pathToDBServer, String databaseName, String userName, String password)
	{
		connectionString = "jdbc:mysql://";
		connectionString += pathToDBServer;
		connectionString += "/" + databaseName;
		connectionString += "?user=" + userName;
		connectionString += "&password" + password;
	}
	
	public String getQuery()
	{
		return query;
	}
	
	public void setQuery(String query)
	{
		this.query = query;
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
	 * Checks for a Drop statement.
	 * @param query The statement passed to SQL.
	 */
	public void dropStatement(String query)
	{
		this.currentQuery = query;
		String results;
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		try
		{
			if(checkForStructureViolation())
			{
				throw new SQLException("You aren't allowed to drop DB's", "No.", Integer.MAX_VALUE);
			}
			
			if(currentQuery.toUpperCase().contains(" INDEX "))
			{
				results = "The index was ";
			}
			else
			{
				results = "The table was ";
			}
			
			Statement dropStatement = databaseConnection.createStatement();
			int affected = dropStatement.executeUpdate(currentQuery);
			
			dropStatement.close();
			
			if(affected == 0)
			{
				results += "dropped";
				
			}
			endTime = System.currentTimeMillis();
			JOptionPane.showMessageDialog(baseController.getAppFrame(), results);
		}
		catch(SQLException dropError)
		{
			endTime = System.currentTimeMillis();
			displayErrors(dropError);
		}
		
		queryTime = endTime - startTime;
		baseController.getQueryList().add(new QueryInfo(query, queryTime));
		
		}
	
	
	/**
	 * Generic version of the select query method. Will work with any database specified by the connectionString value.
	 * @param query The SELECT query to be turned into a ResultSet object.
	 * @return The 2D array of results from said query.
	 */
	public String[][] selectQueryResults(String query)
	{
		
		String[][] results;
		this.currentQuery = query;
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		
		try
		{
			if(checkQueryForDataViolation())
			{
				throw new SQLException("Attempted illegal modification of data", "", Integer.MIN_VALUE);
			}
			
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answer = firstStatement.executeQuery(query);
			int columnCount = answer.getMetaData().getColumnCount();
			answer.last();
			int rowCount = answer.getRow();
			answer.beforeFirst();
			results = new String[rowCount][columnCount];
			
			while(answer.next())
			{
				for(int col = 0; col < columnCount; col++)
				{
					results[answer.getRow() - 1][col] = answer.getString(col + 1);
				}
			}
			
			answer.close();
			firstStatement.close();
			endTime = System.currentTimeMillis();
		}
		catch(SQLException currentException)
			{
			endTime = System.currentTimeMillis();
			results = new String[][] { {"The query was unsuccessful."},
									   {"You might want to use a better query."},
									   {currentException.getMessage()}
									 };
			displayErrors(currentException);
			}
		queryTime = endTime - startTime;
		baseController.getQueryList().add(new QueryInfo(query, queryTime));
		return results;
		}
	
	
	
	/**
	 * Checks for a structure violation
	 * @return True if the query contains "DATABASE".
	 */
	
	private boolean checkForStructureViolation()
	{
		if(currentQuery.toUpperCase().contains(" DATABASE "))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * Checks for queries that cause data violations (DROP, TRUNCATE, SET, and ALTER) and returns true if
	 * any of them are found.
	 * @return If Data is being edited (DROP, TRUNCATE, SET, ALTER).
	 */

	private boolean checkQueryForDataViolation()
	{
		if(query.toUpperCase().contains(" DROP ")
						|| query.toUpperCase().contains(" TRUNCATE ")
						|| query.toUpperCase().contains(" SET ")
						|| query.toUpperCase().contains(" ALTER "))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Gets the results
	 * @return The Columns and Rows.
	 */
	
	public String [][] realResults()
	{
		String [][] results;
		query = "SELECT * FROM `INNODB_SYS_COLUMNS`";
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);
			
			int columnCount = answers.getMetaData().getColumnCount();
			answers.last();
			int numberOfRows = answers.getRow();
			answers.beforeFirst();
			
			
			results = new String [numberOfRows][columnCount];
			while(answers.next())
			{
				for(int col = 0; col < columnCount; col++)
				{
					results[answers.getRow()-1][col] = answers.getString(col + 1);
				}
				
			}
			answers.close();
			firstStatement.close();
			endTime = System.currentTimeMillis();
		}
		catch(SQLException currentError)
		{
			endTime = System.currentTimeMillis();
			results = new String [][] {{"empty"}};
			displayErrors(currentError);
		}
		
		queryTime = endTime - startTime;
		baseController.getQueryList().add(new QueryInfo(query, queryTime));
		return results;
		
	}
	/**
	 * Set-up the connection with the SQL server, displays an error message if it fails.
	 */
	public void setupConnection()
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
	
	/**
	 * Displays the Table that holds the data from the database.
	 * @return The names of the table and the table itself.
	 */
	public String displayTables()

	{
		String tableNames = "";
		query = "SHOW TABLES";
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		
		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);
			while(answers.next())
			{
				tableNames += answers.getString(1) + "\n";
			}
			answers.close();
			firstStatement.close();
			endTime = System.currentTimeMillis();
		}
		catch(SQLException currentError)
		{
			endTime = System.currentTimeMillis();
			displayErrors(currentError);
		}
		
		queryTime = endTime - startTime;
		baseController.getQueryList().add(new QueryInfo(query, queryTime));
		
		return tableNames;
	}
	/**
	 * Meta-Data is good for getting the data from the database. It starts with the column count, and then
	 * moves to the column name.
	 * @return The names of the columns on the table.
	 */
	public String [] getMetaDataTitles()
	{
		String [] columns = null;
		String query = "SELECT * FROM `INNODB_SYS_COLUMNS`";
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		
		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);
			ResultSetMetaData answerData = answers.getMetaData();
			
			columns = new String[answerData.getColumnCount()];
			
			for(int column = 0; column < answerData.getColumnCount(); column++)
			{
				columns[column] = answerData.getColumnName(column+1);
			}
			endTime = System.currentTimeMillis();
			answers.close();
			firstStatement.close();
		}
		catch(SQLException currentException)
		{
			endTime = System.currentTimeMillis();
			columns = new String [] {"empty"};
			displayErrors(currentException);
			
		}
//		baseController.getTimingInfoList().add(new QueryInfo(queryTime, endTime - startTime));
		queryTime = endTime - startTime;
		baseController.getQueryList().add(new QueryInfo(query, queryTime));
		
		return columns;
	}
	/**
	 * 
	 * @param tableName The name of the table that is pulled up.
	 * @return The names of the columns in the Table/Database.
	 */
	public String [] getDatabaseColumnNames(String tableName)
	{
		String [] columns = null;
		String query = "SELECT * FROM `"+ tableName +"`";
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		
		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);
			ResultSetMetaData answerData = answers.getMetaData();
			
			columns = new String[answerData.getColumnCount()];
			
			for(int column = 0; column < answerData.getColumnCount(); column++)
			{
				columns[column] = answerData.getColumnName(column+1);
			}
			endTime = System.currentTimeMillis();
			answers.close();
			firstStatement.close();
		}
		catch(SQLException currentException)
		{
			endTime = System.currentTimeMillis();
			columns = new String [] {"empty"};
			displayErrors(currentException);
			
		}
		
		queryTime = endTime - startTime;
		baseController.getQueryList().add(new QueryInfo(query, queryTime));
		
		return columns;
	}
	
	/**
	 * Makes a 2D array that returns the results (fields) from the table. 
	 * @return The results from the table.
	 */
	public String [][] testResults()
	{
		String[][] results;
		String query = "SHOW TABLES";
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		
		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);
			
			answers.last();
			int numberOfRows = answers.getRow();
			answers.beforeFirst();
			
			results = new String [numberOfRows][1];
			
			while(answers.next())
			{
				results[answers.getRow()-1][0] = answers.getString(1);
			}
			
		answers.close();
		firstStatement.close();
		endTime = System.currentTimeMillis();
		}
		catch(SQLException currentException)
		{
			endTime = System.currentTimeMillis();
			results = new String [][] {{"empty"}};
			displayErrors(currentException);
			
		}
		
		queryTime = endTime - startTime;
		baseController.getQueryList().add(new QueryInfo(query, queryTime));
		return results;
	}
	
	/**
	 * Inserts a sample query into the database. 
	 * @return Puts sample data into the database in the given table.
	 */
	public int insertSample()
	{
		int rowsAffected = -1;
		String query = "INSERT INTO `pokemon_turf_wars`.`teams`"
				+ "(`id`, `owner`, `team`)" 
				+ "VALUES (6, 6,'Me');";
		long endTime, startTime;
		startTime = System.currentTimeMillis();
		try
		{
			Statement insertStatement = databaseConnection.createStatement();
			rowsAffected = insertStatement.executeUpdate(query);
			insertStatement.close();
			endTime = System.currentTimeMillis();
		}
		catch(SQLException currentError)
		{
			endTime = System.currentTimeMillis();
			displayErrors(currentError);
		}
		
		queryTime = endTime - startTime;
		baseController.getQueryList().add(new QueryInfo(query, queryTime));
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
	/**
	 * Updates a query.
	 */
	public void submitUpdateQuery(String query)
	{
		this.query = query;
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		endTime = 0;
		try
		{
			Statement updateStatement = databaseConnection.createStatement();
			updateStatement.executeUpdate(query);
			endTime = System.currentTimeMillis();
		}
		catch(SQLException currentError)
		{
			endTime = System.currentTimeMillis();
			displayErrors(currentError);
		}
		baseController.getQueryList().add(new QueryInfo(query, endTime - startTime));
	}

}
