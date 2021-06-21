package app;

public class Rental {
	private int rental_id;
	private int inventory_id;

	public Rental(int rental_id, int inventory_id) {
		this.rental_id = rental_id;
		this.inventory_id = inventory_id;
	}

	public int getRental_id() {
		return rental_id;
	}

	public void setRental_id(int rental_id) {
		this.rental_id = rental_id;
	}

	public int getInventory_id() {
		return inventory_id;
	}

	public void setInventory_id(int inventory_id) {
		this.inventory_id = inventory_id;
	}
}
