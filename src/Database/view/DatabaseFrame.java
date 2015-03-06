package Database.view;

import Database.view.DatabasePanel;

import javax.swing.*;

import Database.controller.DatabaseAppController;

public class DatabaseFrame extends JFrame
{
	
	private DatabasePanel appPanel;
	
	public DatabaseFrame(DatabaseAppController baseController)
	{
		appPanel = new DatabasePanel(baseController);
		setupFrame();
	}
	/**
	 * Set-up the frame with the Size, visibility, and content.
	 */
	public void setupFrame()
	{
		this.setSize(1000,1000);
		this.setResizable(false);
		this.setContentPane(appPanel);
		this.setVisible(true);
	}
	


}
