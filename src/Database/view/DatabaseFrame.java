package Database.view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Database.view.DatabasePanel;

import javax.swing.*;

import Database.controller.DatabaseAppController;

public class DatabaseFrame extends JFrame
{
	
	private DatabasePanel appPanel;
	private DatabaseAppController baseController;
	
	public DatabaseFrame(DatabaseAppController baseController)
	{
		this.baseController = baseController;
		appPanel = new DatabasePanel(baseController);
		setupFrame();
	}
	/**
	 * Set-up the frame with the Size, visibility, and content.
	 */
	public void setupFrame()
	{
		this.setSize(900,800);
		this.setResizable(false);
		this.setContentPane(appPanel);
		this.setVisible(true);
	}
	
	public void setupListeners()
	{
		addWindowListener(new WindowListener()
		{

			public void windowActivated(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			public void windowClosed(WindowEvent arg0)
			{
				baseController.saveTimeInformation();
				
			}

			public void windowClosing(WindowEvent arg0)
			{
//				baseController.saveTimingInformation();
				
			}

			public void windowDeactivated(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			public void windowDeiconified(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			public void windowIconified(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			public void windowOpened(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
	}


}
