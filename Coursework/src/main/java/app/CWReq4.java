package app;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import db.BaseQuery;

public class CWReq4 extends BaseQuery{

	public CWReq4(String configFilePath) throws FileNotFoundException {
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
	
	public Map<String, String> getActual() throws SQLException {
		List<Inventory> inventoryItems = this.getInventory();
		List<Rental> rentalItems = this.getRental();
		List<Payment> paymentItems = this.getPayment();
		List<Film> filmItems = this.getFilm();
		List<Double> sortedcosts = new ArrayList<Double>();
		Map<Integer, Double> result = new HashMap<Integer, Double>();
		Map<String, Double> result2 = new HashMap<String, Double>();
		Map<String, String> result3 = new LinkedHashMap<String, String>();
		Map<Integer, Inventory> rentalIDs = new HashMap<Integer, Inventory>();
		
		for(Inventory inventoryItem : inventoryItems) {
			for(Rental rentalItem : rentalItems) {
				if(inventoryItem.getInventory_id() == rentalItem.getInventory_id()) {
					rentalIDs.put(rentalItem.getRental_id(), inventoryItem);
				}
			}
		}
		for(Map.Entry<Integer, Inventory> x : rentalIDs.entrySet()) {
			for(Payment paymentItem : paymentItems) {
				if(paymentItem.getRental_id() == x.getKey()) {
					if(!result.containsKey(x.getValue().getFilm_id())) {
						result.put(x.getValue().getFilm_id(), paymentItem.getAmount());
					}	else {
						result.put( x.getValue().getFilm_id(), result.get(x.getValue().getFilm_id()) + paymentItem.getAmount());
						}
				}
			}
		}
		
		for(Film film : filmItems) {
			for(Map.Entry<Integer, Double> x : result.entrySet()) {
				if(film.getFilmID().equals(x.getKey())) {
					result2.put(film.getTitle(), x.getValue());
				}
			}
		}
		
		for(Map.Entry<String, Double> x : result2.entrySet()) {
			sortedcosts.add(x.getValue());
		}
		Collections.sort(sortedcosts);
		int i = sortedcosts.size() - 1;
		while( i > sortedcosts.size() - 11) {
			for(Map.Entry<String, Double> x : result2.entrySet()) {
				if(result2.get(x.getKey()) == sortedcosts.get(i)) {
					DecimalFormat df1 = new DecimalFormat("#.##");
					String str = df1.format(sortedcosts.get(i--));
					String str1 = str.replaceAll(",", ".");
					result3.put(x.getKey(), str1);
				}
			}
		}
		return result3;
	}
	
	private List<Inventory> getInventory() throws SQLException {
		List<Inventory> items = new ArrayList<Inventory>();
		
		ResultSet rs = this.getResultSet("Select * from inventory");
		

		while(rs.next()) {
			int inventory_id = rs.getInt("inventory_id");
			int film_id = rs.getInt("film_id");
			Inventory inventory = new Inventory(inventory_id, film_id);
			items.add(inventory);
		}
		return items;
	}
	
	private List<Rental> getRental() throws SQLException {
		List<Rental> items = new ArrayList<Rental>();
		
		ResultSet rs = this.getResultSet("Select * from rental");
		
		while(rs.next()) {
		int rental_id = rs.getInt("rental_id");
		int	inventory_id = rs.getInt("inventory_id");
		Rental rental = new Rental(rental_id, inventory_id);
		items.add(rental);
		}
		return items;
	}
	
	private List<Payment> getPayment() throws SQLException {
		List<Payment> items = new ArrayList<Payment>();
		
		ResultSet rs = this.getResultSet("Select * from payment");
		
		while(rs.next()) {
			int payment_id = rs.getInt("payment_id");
			int rental_id = rs.getInt("rental_id");
			Double amount = rs.getDouble("amount");
			Payment payment = new Payment(payment_id, rental_id, amount);
			items.add(payment);
		}
		return items;
	}
	
	private List<Film> getFilm() throws SQLException {
		List<Film> items = new ArrayList<Film>();
		
		ResultSet rs = this.getResultSet("Select * from film");
		
		while(rs.next()) {
			int film_id = rs.getInt("film_id");
			String title = rs.getString("title");
			String description = rs.getString("description");
			double rental_rate = rs.getDouble("rental_rate");
			Film film = new Film(film_id, title, description, rental_rate);
			items.add(film);
		}
		return items;
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
		Map<String, String> sortedByRevenue = this.getActual();
		for(Map.Entry<String, String> x : sortedByRevenue.entrySet()) {
			System.out.println(x.getKey() + " - " + x.getValue() + " (£)");
		}
		System.out.println();
	}

}
