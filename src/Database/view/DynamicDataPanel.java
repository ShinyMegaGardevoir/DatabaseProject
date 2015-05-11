package Database.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Database.controller.DatabaseAppController;

public class DynamicDataPanel extends JPanel
{
	private DatabaseAppController baseController;
	private JButton queryButton;
	private SpringLayout baseLayout;
	private String table;
	private ArrayList<JTextField> fieldList;
	
	public DynamicDataPanel(DatabaseAppController baseController, String table)
	{
		this.baseController = baseController;
		this.table = table;
		queryButton = new JButton("Submit query");
		baseLayout = new SpringLayout();
		
		fieldList = new ArrayList<JTextField>();
		
		
		setupPanel(table);
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel(String selectedTable)
	{
		this.setLayout(baseLayout);
		this.add(queryButton);
		int spacing = 50;
		
		String [] columns = baseController.getDataController().getDatabaseColumnNames(selectedTable);
		
		for(int spot = 0; spot < columns.length; spot ++)
		{
			if(!columns[spot].equalsIgnoreCase("id"))
			{
				
			
			JLabel columnLabel = new JLabel(columns[spot]);
			JTextField columnField = new JTextField(20);
			
			this.add(columnLabel);
			this.add(columnField);
			columnField.setName(columns[spot]);
			fieldList.add(columnField);
			
			baseLayout.putConstraint(SpringLayout.WEST, columnLabel, 25, SpringLayout.WEST, this);
			baseLayout.putConstraint(SpringLayout.WEST, columnField, 30, SpringLayout.WEST, columnLabel);
			
			baseLayout.putConstraint(SpringLayout.NORTH, columnLabel, spacing, SpringLayout.NORTH, this);
			baseLayout.putConstraint(SpringLayout.NORTH, columnField, spacing, SpringLayout.NORTH, this);
			
			spacing += 50;
			}
		}
		
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.SOUTH, queryButton, -10, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, queryButton, -123, SpringLayout.EAST, this);
		
	}
	
	private String getFields()
	{
		String fields = "(";
		
		for(int spot = 0; spot < fieldList.size(); spot++)
		{
			fields += "`" + fieldList.get(spot).getName() + "`";
			if(spot == fieldList.size()-1)
			{
				fields += ")";
			}
			else
			{
				fields += ", ";
				
			}
			
		}
		
		return fields;
		
	}
	
	private String getValues()
	{
		String values = "(";
		
		for(int spot = 0; spot < fieldList.size(); spot++)
		{
			values += "'" + fieldList.get(spot).getText() + "'";
			if(spot == fieldList.size()-1)
			{
				values += ");";
			}
			else
			{
				values += ", ";
				
			}
			
		}
		
		return values;
		
	}
	private void setupListeners()
	{
		String query = "INSERT INTO" + "`" + table + "` " + getFields() + " VALUES " + getValues();
		
		queryButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
//				baseController.getDataController().submitUpdateQuery(query);;
				
			}
		}
		
		);
	}

}
