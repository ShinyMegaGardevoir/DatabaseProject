package Database.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Database.controller.DatabaseAppController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DatabasePanel extends JPanel
{
	private DatabaseAppController baseController;
	private JLabel titleLabel;
	private SpringLayout baseLayout;
	private JButton queryButton;
	private JScrollPane displayPane;
	private JTextArea displayArea;
	private JTable resultsTable;
	
	
	public DatabasePanel(DatabaseAppController baseController)
	{
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		titleLabel = new JLabel("Database");
		queryButton = new JButton("Click here.");
		displayArea = new JTextArea(15,30);
		
	/**
	 * Calls all the methods for making the Panel and implementing it.	
	 */
//		setupDisplayPane();
		setupTable();
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
		this.setSize(800,800);
		
		
	}
	
	private void setupTable()
	{
		DefaultTableModel basicData = new DefaultTableModel(baseController.getDataController().testResults(), baseController.getDataController().getMetaDataTitles());
		resultsTable = new JTable(basicData);
		displayPane = new JScrollPane(resultsTable);
		
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
				String [] temp = baseController.getDataController().getMetaDataTitles();
				for(String current : temp)
				{
					displayArea.setText(displayArea.getText() +"Column : " + current + "\n");
				}
				
			}
		});
		
	}

}
