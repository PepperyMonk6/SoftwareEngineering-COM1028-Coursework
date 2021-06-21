package app;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import db.BaseQuery;

public class LabReq1 extends BaseQuery{

	public LabReq1(String configFilePath) throws FileNotFoundException {
		super(configFilePath);
	}
	
	/* -------------------------------------------------------------
	 * TODO: getActual() to be completed as part of the coursework.
	 * --------------------------------------------------------------
	 */
	/* ---------------------------------------------------------------------
	 * The getActual() method returns yours requirement code's output.
	 * In this instance, the return type is a String, you are free to choose
	 * other return types depending on the requirement. You are allowed to 
	 * write additional helper methods.
	 * ---------------------------------------------------------------------
	 */
	
	public ArrayList<String> getActual() throws SQLException {
		HashMap<String, Integer> actors = new HashMap<String, Integer>();
		ArrayList<String> lastNames = new ArrayList<String>();

		Actor a;//hello
		
		ResultSet rs = this.getResultSet("Select * from actor");
		
		while(rs.next()) {
			Integer actor_id = rs.getInt("actor_id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			a = new Actor(actor_id, first_name, last_name);

			if(!actors.containsKey(last_name)) {
				actors.put(last_name, 1);
			}	else {
				for(Map.Entry<String, Integer> x : actors.entrySet()) {
					int number = x.getValue();
					if(x.getKey().equals(last_name)) {
					actors.put(last_name, number + 1);
					}
				}
			}
		}
		for(Map.Entry<String, Integer> x : actors.entrySet()) {
			String lastName = x.getKey();
			int number = x.getValue();
			if(number > 3) {
				lastNames.add(lastName);
			}
		}
		return lastNames;
	}
	
	
	/* -------------------------------------------------------------
	 * TODO: printOutput() to be completed as part of the coursework.
	 * --------------------------------------------------------------
	 */
	/* ----------------------------------------------------------------------
	 * The printOutput() method prints result of your requirement code 
	 * onto the console for the end-user to view. This method should
	 * rely on the requirement code results obtained through the getActual() 
	 * method, decorate it in a human friendly format and display the results 
	 * on the console. It is possible that this method may need to get additional 
	 * data to make the output human friendly. For example, if the requirement 
	 * code returns only the customer IDs, this method may additionally 
	 * want to fetch the customer names to make the output human-friendly.
	 * You are allowed to write additional helper methods.
	 * ----------------------------------------------------------------------
	 */
	
	public void printOutput() throws SQLException{
		ArrayList<String> actors = getActual();
		String result = "Reapeted last name is: ";
		for(String ln : actors) {
			System.out.println(result + ln);
		}
	}

}
