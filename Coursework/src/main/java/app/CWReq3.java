package app;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.BaseQuery;

public class CWReq3 extends BaseQuery{

	public CWReq3(String configFilePath) throws FileNotFoundException {
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
	
	public Map<String, Integer> getActual() throws SQLException {	//was String
		Map<String, Integer> categories = this.getNumberForCategory();
		
		return categories;
	}
	
	private Map<Integer, Integer> getListOfFilms() throws SQLException {
		Map<Integer, Integer> numberOfFilms = new HashMap<Integer, Integer>();
		
		ResultSet rs = this.getResultSet("Select * from film_category");
		
		while(rs.next()) {
			Integer film_id = rs.getInt("film_id");
			Integer category_id = rs.getInt("category_id");
			FilmCategory fc = new FilmCategory(film_id, category_id);
			if(!numberOfFilms.containsKey(fc.getCategory_id())) {
				numberOfFilms.put(fc.getCategory_id(), 1);
			}	else {
					for(Map.Entry<Integer, Integer> x : numberOfFilms.entrySet()) {
						int number = x.getValue();
						if(x.getKey().equals(fc.getCategory_id())) {
							numberOfFilms.put(fc.getCategory_id(), number + 1);
						}
					}
				}
		}
		return numberOfFilms;
	}
	
	private Map<String, Integer> getNumberForCategory() throws SQLException {
		Map<String, Integer> categories = new HashMap<String, Integer>();
		Map<Integer, Integer> numberOfFilms = this.getListOfFilms();
		
		ResultSet rs = this.getResultSet("Select * from category");
		
		while(rs.next()) {
			Integer category_id = rs.getInt("category_id");
			String category_name = rs.getString("name");
			Category category = new Category(category_id, category_name);
			for(Map.Entry<Integer, Integer> x : numberOfFilms.entrySet()) {
				int tmp1 = x.getKey();
				int tmp2 = x.getValue();
				if(category.getCategory_id() == tmp1) {
				categories.put(category.getName(), tmp2);
				}
			}
		}
		return categories;
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
		Map<String, Integer> totalNumber = getActual();
		System.out.println("Total number of films in each category:\n");
		for(Map.Entry<String, Integer> x : totalNumber.entrySet()) {
			System.out.println(x.getKey() + " - " + x.getValue());
		}
		System.out.println();
	}

}
