package pl.pawelSz.Spring.Rest.RestService.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Paweł Szymaszek
 * @version 1.1
 * @since 17.10.2017
 */

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private int price;
	private int specialPrice;
	private int qtyToDiscount;

	public Item() {
	}

	public Item(String name, int price, int specialPrice, int qtyToDiscount) {

		this.name = name;
		this.price = price;
		this.specialPrice = specialPrice;
		this.qtyToDiscount = qtyToDiscount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(int specialPrice) {
		this.specialPrice = specialPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQtyToDiscount() {
		return qtyToDiscount;
	}

	public void setQtyToDiscount(int qtyToDiscount) {
		this.qtyToDiscount = qtyToDiscount;
	}

}
