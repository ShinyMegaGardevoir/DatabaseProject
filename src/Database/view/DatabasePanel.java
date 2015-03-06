package Database.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Database.controller.DatabaseAppController;

import javax.swing.*;

public class DatabasePanel extends JPanel
{
	private DatabaseAppController baseController;
	private JLabel titleLabel;
	private SpringLayout baseLayout;
	private JButton queryButton;
	private JScrollPane displayPane;
	private JTextArea displayArea;

	
	
	public DatabasePanel(DatabaseAppController baseController)
	{
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		titleLabel = new JLabel("Database");
		queryButton = new JButton("Click here.");
		displayArea = new JTextArea(15,30);
		displayPane = new JScrollPane(displayArea);
		
	/**
	 * Calls all the methods for making the Panel and implementing it.	
	 */
		setupDisplayPane();
		setupPanel();
		
		setupLayout();
		setupListeners();
	}
	
	private void setupDisplayPane()
	{
		displayArea.setWrapStyleWord(true);
		displayArea.setLineWrap(true);
		displayArea.setEditable(false);
		displayArea.setBackground(Color.lightGray);
	}
	/**
	 * Sets up the Panel.
	 */
	private void setupPanel()
	{

		this.setLayout(baseLayout);
		this.add(queryButton);
		this.add(displayPane);
		
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
		queryButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String results = baseController.getDataController().displayTables();
				displayArea.setText(displayArea.getText() +"Rows Affected: " + results + "\n");
			}
		});
		
	}

}
