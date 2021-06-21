package testapp;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import app.CWReq4;
import junit.framework.TestCase;

public class TestCWReq4 extends TestCase {
	
	private CWReq4 r;

	public TestCWReq4(String testName) throws FileNotFoundException {
		super(testName);
		r = new CWReq4("src/test/java/testapp/DBconfiguration1.json");
	}
	
    @Override
    protected void setUp() throws Exception {
    	System.out.println("\n\n\n\n");
    	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    	System.out.println("Running tests in " + this.getClass().getName() + "...");
    	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
    	r.openconn();
    }
    
    @Override
    protected void tearDown() throws Exception {
    	r.closeconn();
    	System.out.println("Finished tests in " + this.getClass().getName());
    	System.out.println("-------------------------------------------------------\n\n");
    }
    
	/* -------------------------------------------------------------
	 * TODO: getExpected() to be completed as part of the coursework.
	 * --------------------------------------------------------------
	 */
	/* ---------------------------------------------------------------------
	 * The getExpected() method returns yours requirements's expected results.
	 * These results can be obtained by writing complex SQL queries 
	 * and using the ResultSet API to obtain the expected results. Unlike 
	 * the requirement code, where you talk to the database multiple times 
	 * to get tables into memory and compute over them, the expected results 
	 * can be obtained by running SQL queries which have advanced SQL 
	 * primitives in it such as SUM, COUNT, ORDER BY, GROUP BY, etc.
	 * In this instance, the return type is a String, you are free to choose
	 * other return types depending on the requirement. You are allowed to 
	 * write additional helper methods.
	 * ---------------------------------------------------------------------
	 */
    
    private Map<String, String> getExpected() throws SQLException {
    	Map<String, String> result = new LinkedHashMap<String, String>();
    	String query = "SELECT title, SUM(amount) as earned\n"
    			+ "FROM ((inventory INNER JOIN rental ON inventory.inventory_id = rental.inventory_id) "
    			+ "INNER JOIN payment ON rental.rental_id = payment.rental_id) "
    			+ "inner join film on inventory.film_id = film.film_id\n"
    			+ "GROUP BY inventory.film_id\n"
    			+ "ORDER BY earned desc\n"
    			+ "LIMIT 10";
    	
    	ResultSet rs = r.getResultSet(query);
    	//SELECT title, SUM(amount) as earned FROM ((inventory INNER JOIN rental ON inventory.inventory_id = rental.inventory_id) INNER JOIN payment ON rental.rental_id = payment.rental_id) inner join film on inventory.film_id = film.film_id GROUP BY inventory.film_id ORDER BY earned desc LIMIT 10
    	while(rs.next()) {
    		String title = rs.getString("title");
    		double earned = rs.getDouble("earned");
    		result.put(title, Double.toString(earned));
    	}
    	return result;
    }
	
	/* -------------------------------------------------------------
	 * TODO: testAndOutput() to be modified, if required, as part 
	 * of the coursework.
	 * --------------------------------------------------------------
	 */
	/* ---------------------------------------------------------------------
	 * The testAndOutput() method does two things:
	 *    1. Invokes the method which prints output from the requirement 
	 *    	 code on to the console in a human-friendly format.
	 *    2. Compares the expected result with the actual result.
	 *    
	 * This method may need to be modified if you are using a type which 
	 * is other than a string for returning your expected and actual results.
	 * ---------------------------------------------------------------------
	 */
    /**
     *  Output Results and Test Sample Requirement
     * @throws FileNotFoundException 
     * @throws SQLException 
     */
    
    public void testAndOutput() throws FileNotFoundException, SQLException
    {
    	r.printOutput();
    	Map<String, String> actual = r.getActual();
    	Map<String, String> expected = getExpected();
    	assertEquals(expected, actual);
    }
}
