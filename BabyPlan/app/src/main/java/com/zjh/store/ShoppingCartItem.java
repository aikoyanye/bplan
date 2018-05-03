package com.zjh.store;

import android.icu.util.VersionInfo;

public class ShoppingCartItem {
	private int id;
	private boolean isChoosed;
	private int itemimage;
	private String itemname;
	private String itemattr;
	private double itemprice;
	private int itemamount;
	public boolean isCheck=false;
	public ShoppingCartItem(int id,int itemimage,String itemname,String itemattr,double itemprice,int itemamount) {
		this.id=id;
		this.itemimage=itemimage;
		this.itemname=itemname;
		this.itemattr=itemattr;
		this.itemprice=itemprice;
		this.itemamount=itemamount;
	}
	
	public ShoppingCartItem() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public boolean isChoosed() {
		return isChoosed;
	}
	public void setChoosed(boolean choosed) {
		this.isChoosed=choosed;
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
	public String getItemattr() {
		return itemattr;
	}
	public void setItemattr(String itemattr) {
		this.itemattr=itemattr;
	}
	public double getItemprice() {
		return itemprice;
	}
	public void setItemprice(double itemprice) {
		this.itemprice=itemprice;
	}
	public int getItemamount() {
		return itemamount;
	}
	public void setItemamount(int itemamount) {
		this.itemamount=itemamount;
	}
}
