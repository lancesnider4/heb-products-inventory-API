package com.heb.products.representation;

public class ProductsDetailRO 
{
	private String id;
	private String description;
	private String lastSold;
	private String shelfLife;
	private String department;
	private String price;
	private String unit;
	private String xFor;
	private String cost;
	
	public String getId() { return id; }
	
	public void setId(String id) { this.id = id; }
	
	public String getDescription() { return description; }
	
	public void setDescription(String description) { this.description = description; }
	
	public String getLastSold() { return lastSold; }
	
	public void setLastSold(String lastSold) { this.lastSold = lastSold; }
	
	public String getShelfLife() { return shelfLife; }
	
	public void setShelfLife(String shelfLife) { this.shelfLife = shelfLife; }
	
	public String getDepartment() { return department; }
	
	public void setDepartment(String department) { this.department = department; }
	
	public String getPrice() { return price; }
	
	public void setPrice(String price) { this.price = price; }
	
	public String getUnit() { return unit; }
	
	public void setUnit(String unit) { this.unit = unit; }
	
	public String getxFor() { return xFor; }
	
	public void setxFor(String xFor) { this.xFor = xFor; }
	
	public String getCost() { return cost; }
	
	public void setCost(String cost) { this.cost = cost; }
}
