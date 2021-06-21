package app;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.BaseQuery;

public class CWReq1 extends BaseQuery{

	public CWReq1(String configFilePath) throws FileNotFoundException {
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
	
	public String getActual() throws SQLException {
		List<Film> films = this.getNumberOfFilms();
		
		String str = Integer.toString(films.size());
		return str;
	}
	
	private List<Film> getNumberOfFilms() throws SQLException {
		ArrayList<Film> films = new ArrayList<Film>();
		
		ResultSet rs = this.getResultSet("Select * from film");
		
		while(rs.next()) {
			Integer id = rs.getInt("film_id");
			String title = rs.getString("title");
			String description = rs.getString("description");
			Double rental_rate = rs.getDouble("rental_rate");
			Film film = new Film(id, title, description, rental_rate);
			films.add(film);
		}
		return films;
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
		String totalNumberOfFilms = getActual();
		System.out.println("Total number of films: " + totalNumberOfFilms);
		System.out.println();
	}

}
