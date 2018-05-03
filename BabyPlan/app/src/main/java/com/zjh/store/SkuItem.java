package com.zjh.store;

public class SkuItem {
	//	private String id;//id
	private String skuSize;//尺码
	private String skuColor;//颜色
	private String skuIamgeUrl;//图片路径
	//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	public SkuItem(String skuSize,String skuColor) {
		this.skuSize=skuSize;
		this.skuColor=skuColor;
	}
	public String getSkuSize() {
		return skuSize;
	}
	public void setSkuSize(String skuSize) {
		this.skuSize = skuSize;
	}
	public String getSkuColor() {
		return skuColor;
	}
	public void setSkuColor(String skuColor) {
		this.skuColor = skuColor;
	}
	public String getSkuIamgeUrl() {
		return skuIamgeUrl;
	}
	public void setSkuIamgeUrl(String skuIamgeUrl) {
		this.skuIamgeUrl = skuIamgeUrl;
	}
}