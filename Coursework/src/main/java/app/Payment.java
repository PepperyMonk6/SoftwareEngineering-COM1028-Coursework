package app;

public class Payment {
	private int payment_id;
	private int rental_id;
	private double amount;
	
	public Payment(int payment_id, int rental_id, double amount) {
		this.payment_id = payment_id;
		this.rental_id = rental_id;
		this.amount = amount;
	}

	public int getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}

	public int getRental_id() {
		return rental_id;
	}

	public void setRental_id(int rental_id) {
		this.rental_id = rental_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
