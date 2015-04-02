package Database.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Database.controller.DatabaseAppController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.Font;
import java.sql.ResultSet;

public class DatabasePanel extends JPanel
{
	private DatabaseAppController baseController;
	private SpringLayout baseLayout;
	private JLabel titleLabel;
	private JButton queryButton;
	private JScrollPane displayPane;
	private JTextArea displayArea;
	private JTable resultsTable;
	private JTextField textField;
	private JTextField otherField;
	private JLabel username;
	private JPasswordField samplePassword;
	private JLabel lblPassword;
	private JButton otherButton;
	private CellRenderer cellRenderer;
	
	
	public DatabasePanel(DatabaseAppController baseController)
	{
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		titleLabel = new JLabel("Database");
		queryButton = new JButton("Click here.");
		displayArea = new JTextArea(15,30);
		samplePassword = new JPasswordField(null, 20);
		baseLayout.putConstraint(SpringLayout.WEST, samplePassword, 124, SpringLayout.WEST, this);
		cellRenderer = new CellRenderer();
	/**
	 * Calls all the methods for making the Panel and implementing it.	
	 */
		
		setupTable();
		setupPanel();
//		setupDisplayPane();
		setupLayout();
		setupListeners();
	}
	
	private void setupDisplayPane()
	{
		displayArea.setWrapStyleWord(true);
		displayArea.setLineWrap(true);
//		displayArea.setEditable(false);
		displayArea.setBackground(Color.lightGray);
	}
	/**
	 * Sets up the Panel with the size, layout, and any other necessary pieces.
	 */
	private void setupPanel()
	{

		this.setLayout(baseLayout);
		this.add(queryButton);
		this.add(displayPane);
		this.add(titleLabel);
		
		this.add(samplePassword);
		this.setSize(800,800);
		
		
		
	
		
		samplePassword.setEchoChar('è');
		samplePassword.setFont(new Font("Serif", Font.BOLD, 32));
		samplePassword.setForeground(Color.CYAN);
		
		textField = new JTextField();
		
		
		textField.setColumns(10);
		
		
				
		
		otherField = new JTextField();
		
		
		otherField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		
		
		
		JButton otherButton = new JButton("Submit");
		
		
		
		
		
	}
	/**
	 * Sets up the table with the data. Adds a scroll pane.
	 */
	private void setupTable()
	{
//    	DefaultTableModel basicData = new DefaultTableModel(baseController.getDataController().testResults(), baseController.getDataController().getMetaDataTitles());
	//	resultsTable = new JTable(basicData);
		
		
		
		JTable tableData = new JTable(new DefaultTableModel(baseController.getDataController().testResults(), baseController.getDataController().getMetaDataTitles()));
		displayPane = new JScrollPane(tableData);
		for(int spot = 0; spot < tableData.getColumnCount(); spot++)
		{
			tableData.getColumnModel().getColumn(spot).setCellRenderer(cellRenderer);
		}
	}
	
	
	/**
	 * This is where all of the auto-generated code goes when the panel is set up.
	 */
	private void setupLayout()
	{


		baseLayout.putConstraint(SpringLayout.NORTH, samplePassword, 28, SpringLayout.SOUTH, displayPane);
		baseLayout.putConstraint(SpringLayout.NORTH, displayPane, 150, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, displayPane, 150, SpringLayout.WEST, this);
	
	
	
		baseLayout.putConstraint(SpringLayout.NORTH, titleLabel, 25, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, titleLabel, 322, SpringLayout.WEST, this);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		baseLayout.putConstraint(SpringLayout.NORTH, queryButton, 75, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, queryButton, 325, SpringLayout.WEST, this);

	}
	

	
	
	/**
	 * Sets up the button listeners to tell when a button is clicked.
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
