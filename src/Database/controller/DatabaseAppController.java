package Database.controller;

import Database.view.DatabaseFrame;

public class DatabaseAppController
{
	private DatabaseFrame appFrame;
	private DatabaseController dataController;
	public DatabaseAppController()
	{
		dataController = new DatabaseController(this);
		appFrame = new DatabaseFrame(this);
		
	}
	
	/**
	 * Gets the Frame for the program and returns it.
	 * @return The frame for the program.
	 */
	public DatabaseFrame getAppFrame()
	{
		return appFrame;
	}

	/**
	 * Gets the Data controller (controls the data) and returns it.
	 * @return The data controller for the program.
	 */
	public DatabaseController getDataController()
	{
		return dataController;
	}
	
	/**
	 * Starts the program.
	 */
	public void start()
	{
		
	}
	
}

