package Database.view;

import Database.controller.DatabaseAppController;

import javax.swing.*;

public class DatabasePanel
{
	private DatabaseAppController baseController;
	private JLabel titleLabel;
	private SpringLayout baseLayout;
	
	public DatabasePanel(DatabaseAppController baseController)
	{
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		titleLabel = new JLabel("Database");
		
	/**
	 * Calls all the methods for making the Panel and implementing it.	
	 */
		setupPanel();
		setupLayout();
		setupListeners();
	}
	/**
	 * Sets up the Panel.
	 */
	private void setupPanel()
	{
		this.setLayout(baseLayout);
	}
	
	private void setLayout(SpringLayout baseLayout)
	{
		
		
	}
	/**
	 * This is where all of the auto-generated code goes when the panel is set up.
	 */
	private void setupLayout()
	{

	}
	
	
	/**
	 * Sets up the button listeners.
	 */
	private void setupListeners()
	{
		
	}

}
