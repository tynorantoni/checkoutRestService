package pl.pawelSz.Spring.Rest.RestService.Model;

public class Item {

	private int id;
	private String name;
	private int price;
	private int specialPrice;
	private int qtyToDiscount;
	private int quantity;
	private int cost;
	
	
	

	

	public Item(int id, String name, int price, int specialPrice, int qtyToDiscount, int quantity, int cost) {
		
		this.id = id;
		this.name = name;
		this.price = price;
		this.specialPrice = specialPrice;
		this.qtyToDiscount = qtyToDiscount;
		this.quantity = quantity;
		this.cost = cost;
	}

	
	public int getCost() {
		return cost;
	}


	public void setCost(int cost) {
		this.cost = cost;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", specialPrice=" + specialPrice
				+ ", qtyToDiscount=" + qtyToDiscount + ", quantity=" + quantity + ", cost=" + cost + "]";
	}
	
	
	
	 
	
	
}
