package net.codejava;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import java.util.ArrayList; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class GUIJavaMySql {

	public JFrame frame;
	public static String final_result = "";
	public static JavaMySQLTest jms = new JavaMySQLTest(); 
	
	
	public static void main (String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIJavaMySql window = new GUIJavaMySql();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public GUIJavaMySql() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 768, 664);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JCheckBox CheckBox_Mustard = new JCheckBox("mustard");
		CheckBox_Mustard.setBounds(75, 279, 139, 29);
		frame.getContentPane().add(CheckBox_Mustard);

		JCheckBox CheckBox_Vegetarian = new JCheckBox("vegetarian");
		CheckBox_Vegetarian.setBounds(528, 279, 139, 29);
		frame.getContentPane().add(CheckBox_Vegetarian);

		JCheckBox CheckBox_wheat = new JCheckBox("wheat");
		CheckBox_wheat.setBounds(295, 279, 139, 29);
		frame.getContentPane().add(CheckBox_wheat);

		JCheckBox CheckBox_egg = new JCheckBox("egg");
		CheckBox_egg.setBounds(75, 151, 139, 29);
		frame.getContentPane().add(CheckBox_egg);

		JCheckBox CheckBox_Milk = new JCheckBox("milk");
		CheckBox_Milk.setBounds(295, 151, 139, 29);
		frame.getContentPane().add(CheckBox_Milk);

		JCheckBox CheckBox_sesame = new JCheckBox("sesame");
		CheckBox_sesame.setBounds(528, 151, 139, 29);
		frame.getContentPane().add(CheckBox_sesame);

		JCheckBox CheckBox_Halal = new JCheckBox("halal");
		CheckBox_Halal.setBounds(295, 376, 139, 29);
		frame.getContentPane().add(CheckBox_Halal);

		
		/**
		 * Creates a list of checked items and calls getallergyList.
		 */
		JButton Button_Filter = new JButton("Filter");
		Button_Filter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean[] ls = {CheckBox_egg.isSelected(),
						CheckBox_Milk.isSelected(), 
						CheckBox_sesame.isSelected(),
						CheckBox_Mustard.isSelected(),
						CheckBox_wheat.isSelected(), 
						CheckBox_Vegetarian.isSelected(), 
						CheckBox_Halal.isSelected()};
				getallergyList(ls);
			}
		});

		Button_Filter.setBounds(319, 494, 115, 29);
		frame.getContentPane().add(Button_Filter);
	}
	
	/**
	 * Creates a string containing all the recipes that satisfy the checked restrictions. 
	 */
	public void getallergyList(boolean[] allergies) {
		
		int[] allergyList = {-1, -1, -1, -1, -1, -1, -1};
		
		for(int i = 0; i < allergyList.length; i++) {
			if(allergies[i]) {
				if(i == 0) {
					allergyList[i] = 0;
				}
				else {
					allergyList[i] = i+1;
				}
			}
		}
		
		ArrayList<Integer> a_id = new ArrayList<Integer>();  
		
		int counter = 0;
		
		while(counter < 7) {
			if(allergyList[counter] != -1) {
				a_id.add(allergyList[counter]);
			}
			counter++;
		}
		
		String temp = "select * from recipe where reci_id Not In(select reci from partOfRecipe where ing in (select ingredients from partOfAllergy where ";
		
		for(int i = 0; i < a_id.size() - 1; i++) {
			temp += "allergies= "+a_id.get(i)+" OR ";
			
		}
		if(a_id.size() != 0) {
			temp += "allergies= " + a_id.get(a_id.size()-1)+ "));";
		}
		else {
			temp = "select * from recipe;";
		}
		try {
			Statement statement_al = jms.connection.createStatement();
			ResultSet result = statement_al.executeQuery(temp);
			
			while(result.next()) {
				String reci = result.getString("reci_id");
				String name = result.getString("reci_name");
				String rest = result.getString("rest");
				
				final_result += "<tr><td>" + rest + "</td> <td>" + reci + "</td> <td>" + name + "</td></tr>";
			}
			
			File f = new File("MyMenu.html");
			Desktop.getDesktop().browse(f.toURI());
			String html = "<div><h1>MyMenu</h1> <h2>Results for desired restrictions</h2><table><tr>\n"
					+ "    <th>Restaurant</th>\n"
					+ "    <th>Item ID</th> \n"
					+ "    <th>Item</th>\n"
					+ "  </tr>" + final_result + "</table></div>";
			
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));
				bw.write(html);
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
	}	
	
}








