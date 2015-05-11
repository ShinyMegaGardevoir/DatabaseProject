package Database.controller;

import java.awt.Container;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Database.model.QueryInfo;
import Database.view.DatabaseFrame;

public class DatabaseAppController
{
	private DatabaseFrame appFrame;
	private DatabaseController dataController;
	private ArrayList<QueryInfo> queryList;
	
	public DatabaseAppController()
	{
		dataController = new DatabaseController(this);
		queryList = new ArrayList<QueryInfo>();
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
		loadTimeInformation();
	}

	public ArrayList<QueryInfo> getQueryList()
	{
		return queryList;
	}
	
	private void loadTimeInformation()
	{
		File loadFile = new File("something.txt");
		try
		{
			
			if(loadFile.exists())
			{
				queryList.clear();
				Scanner textScanner = new Scanner(loadFile);
				while(textScanner.hasNext())
				{
					String query = textScanner.nextLine();
					textScanner.next();
					queryList.add(new QueryInfo(query, textScanner.nextLong()));
				}
				textScanner.close();
				JOptionPane.showMessageDialog(getAppFrame(), queryList.size() + " QueryInfo objects were loaded into the application.");
			}
			else
			{
				JOptionPane.showMessageDialog(getAppFrame(), "File not present. No QueryInfo objects loaded.");
			}
		}
		catch(IOException currentError)
		{
			dataController.displayErrors(currentError);
		}
	}
	
	public void saveTimeInformation()
	{
		try
		{
			File loadFile = new File("something.txt");
			for(int spot = 0; spot < queryList.size(); spot++)
			{
				if(loadFile.exists())
				{
					PrintWriter writer = new PrintWriter("something.txt", "UTF-8");
					writer.println(queryList.get(spot));
					writer.close();
				}
				else
				{
//					String dataToWrite = getQueryList();
//					FileOutputStream out = new FileOutputStream("something");
//					out.write(dataToWrite);
//					out.close();
				}
			}
		}
		catch(IOException currentError)
		{
			dataController.displayErrors(currentError);
		}
	}

	public int getTimingInfoList()
	{
		int time = 0;
		
		return time;
	}
}

