package pl.pawelSz.Spring.Rest.RestService.Model;

/**
* @author Paweł Szymaszek
* @version 1.1
* @since 17.10.2017
*/

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class Basket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;
	private int quantity;
	private int cost;

	@OneToOne
	@JoinTable(name = "BASKET_ORDERS", joinColumns = { @JoinColumn(name = "ORDER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ID") })
	private Item item;

	public Basket() {
	}

	public Basket(int quantity, int cost, Item items) {

		this.quantity = quantity;
		this.cost = cost;
		this.item = items;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Item getItems() {
		return item;
	}

	public void setItems(Item items) {
		this.item = items;
	}

}