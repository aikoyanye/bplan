package com.zjh.store;

public class AddressItem {
	private String receiver;
	private String telephone;
	private String address_detail;
	private boolean isChecked;
	public AddressItem(String receiver,String telephone,String address_detail) {
		this.receiver=receiver;
		this.telephone=telephone;
		this.address_detail=address_detail;
	}
	public AddressItem() {}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver=receiver;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone=telephone;
	}
	public String getAddress_detail() {
		return address_detail;
	}
	public void setAddress_datail(String address_detail) {
		this.address_detail=address_detail;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean checked) {
		this.isChecked=checked;
	}
}
