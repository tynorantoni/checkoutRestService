package pl.pawelSz.Spring.Rest.RestService.Model;

public class Item {

	private int id;
	private String name;
	private int price;
	private int specialPrice;
	private int qtyToDiscount;
	private int quantity;
	private int cost;
	
	
	

	

	public Item(Builder builder) {
		
		this.id = builder.id;
		this.name =  builder.name;
		this.price =  builder.price;
		this.specialPrice =  builder.specialPrice;
		this.qtyToDiscount =  builder.qtyToDiscount;
		this.quantity =  builder.quantity;
		this.cost =  builder.cost;
		
	}

	public static class Builder{
		
		private int id;
		private String name;
		private int price;
		private int specialPrice;
		private int qtyToDiscount;
		private int quantity;
		private int cost;
		
		public Builder id(int id){
			this.id=id;
			return this;
		}
		public Builder name(String name){
			this.name=name;
			return this;
		}
		public Builder price(int price){
			this.price=price;
			return this;
		}
		public Builder specialPrice(int specialPrice){
			this.specialPrice=specialPrice;
			return this;
		}
		public Builder qtyToDiscount(int qtyToDiscount){
			this.qtyToDiscount=qtyToDiscount;
			return this;
		}
		public Builder quantity(int quantity){
			this.quantity=quantity;
			return this;
		}
		public Builder cost(int cost){
			this.cost=cost;
			return this;
		}
		public Item build(){
			return new Item(this);
		}
		
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
