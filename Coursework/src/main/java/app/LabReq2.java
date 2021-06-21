package app;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.BaseQuery;

public class LabReq2 extends BaseQuery{

	public LabReq2(String configFilePath) throws FileNotFoundException {
		super(configFilePath);
	}//hello1
	
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
	
	public ArrayList<String> getActual()throws SQLException {
		ArrayList<String> actors = new ArrayList<String>();
		ArrayList<Integer> moonActorIds = new ArrayList<Integer>();
		
		ResultSet rs = this.getResultSet("Select * from film");
		 //select * from where title = 'KARATE MOON'
		Integer karateFilmId = 0;
		while(rs.next()) {
			String title = rs.getString("title");
			int film_id = rs.getInt("film_id");
			if(title.equals("KARATE MOON")) {
				karateFilmId = film_id;
			}
		}

		ResultSet rs1 = this.getResultSet("Select * from actor");
		
		//Select actor_id, concat(first_name,' ',last_name) as full_name from actor
		
		ResultSet rs2 = this.getResultSet("Select * from film_actor");
		//select * from film_actor where film_id = " + karateFilmId
		
		while(rs2.next()) {
			Integer moonActor_id = rs2.getInt("actor_id");
			Integer film_id = rs2.getInt("film_id");

			if(karateFilmId.equals(film_id)) {
				moonActorIds.add(moonActor_id);
			}
		}
		
		while(rs1.next()) {
			Integer actor_id = rs1.getInt("actor_id");
			String first_name = rs1.getString("first_name");
			String last_name = rs1.getString("last_name");
			String actorFullName = first_name + " " + last_name;
			
			if(moonActorIds.contains(actor_id)) {
				actors.add(actorFullName);
			}
		}
		return actors;
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
		for(String a : actors) {
			System.out.println(a);
		}
	}

}
