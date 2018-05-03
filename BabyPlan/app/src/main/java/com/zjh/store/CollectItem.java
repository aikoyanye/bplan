package com.zjh.store;

public class CollectItem {
	private int id;
	private int itemimage;
	private String itemname;
	private String itemfeature;
	private double itemprice;
	public CollectItem(int id,int itemimage,String itemname,String itemfeature,double itemprice) {
		this.id=id;
		this.itemimage=itemimage;
		this.itemname=itemname;
		this.itemfeature=itemfeature;
		this.itemprice=itemprice;
	}
	public CollectItem() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public int getItemimage() {
		return itemimage;
	}
	public void setItemimage(int itemimage) {
		this.itemimage=itemimage;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname=itemname;
	}
	public String getItemfeature() {
		return itemfeature;
	}
	public void setItemfeature(String itemfeature) {
		this.itemfeature=itemfeature;
	}
	public double getItemprice() {
		return itemprice;
	}
	public void setItemprice(double itemprice) {
		this.itemprice=itemprice;
	}
}
