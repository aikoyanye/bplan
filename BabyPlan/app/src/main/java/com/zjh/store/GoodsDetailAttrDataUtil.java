package com.zjh.store;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;


public class GoodsDetailAttrDataUtil {
	/**
	 * 清空状态
	 */
	public static List<GoodsDetailAttrBean> clearAdapterStates(List<GoodsDetailAttrBean> mList) {
		int size = mList.size();
		for (int i = 0; i < size; i++) {
			GoodsDetailAttrBean bean = mList.get(i);
			bean.setStates("1");
			mList.set(i, bean);
		}
		return mList;
	}


	/**
	 * 设置选中状态
	 * @param key
	 */
	public static List<GoodsDetailAttrBean> setAdapterStates(List<GoodsDetailAttrBean> mList,String key) {
		int size = mList.size();
		for (int i = 0; i < size; i++) {
			GoodsDetailAttrBean bean = mList.get(i);
			String str=bean.getName();
			if(str.equals(key)){
				bean.setStates("0");
			}else{
				bean.setStates("1");
			}
			mList.set(i, bean);
		}
		return mList;
	}
	/**
	 * 更新适配器状态
	 *
	 * @param states
	 */
	public static List<GoodsDetailAttrBean> updateAdapterStates(List<GoodsDetailAttrBean> mList,String states, int postion) {
		int size = mList.size();
		for (int i = 0; i < size; i++) {
			GoodsDetailAttrBean bean = mList.get(i);
			if (i == postion) {
				bean.setStates(states);
			} else {
				if (!bean.getStates().equals("2")) {
					bean.setStates("1");
				}
			}
			mList.set(i, bean);
		}
		return mList;
	}
	/**
	 * 点击颜色后，获取该颜色对应的所有尺码列表
	 *
	 * @param colorStr
	 */
	public static List<String> getSizeListByColor(List<SkuItem> mList,String colorStr) {
		List<String> list = null;
		if (!TextUtils.isEmpty(colorStr)) {
			list = new ArrayList<String>();
			for (SkuItem itme : mList) {
				String color = itme.getSkuColor();
				String size = itme.getSkuSize();
				if (color.equals(colorStr)) {
					list.add(size);
				}
			}
		}
		return list;
	}

	/**
	 * 点击尺码后，获取该尺码对应的所有颜色列表
	 *
	 */
	public static List<String> getColorListBySize(List<SkuItem> mList,String sizeStr) {
		List<String> list = null;
		list = new ArrayList<String>();
		for (SkuItem itme : mList) {
			String color = itme.getSkuColor();
			String size = itme.getSkuSize();
			if (size.equals(sizeStr)) {
				list.add(color);
			}
		}
		return list;
	}
	/**
	 * @param mList 尺码列表/颜色列表
	 * @param list  该颜色对应的尺码列表/颜色列表
	 * @param key 之前选中的尺码/颜色
	 * @return
	 */
	public static List<GoodsDetailAttrBean> setSizeOrColorListStates(List<GoodsDetailAttrBean> mList,List<String> list, String key) {
		int size = mList.size();
		List<GoodsDetailAttrBean> list2 = new ArrayList<GoodsDetailAttrBean>();
		for (int i = 0; i < size; i++) {
			GoodsDetailAttrBean bean = mList.get(i);
			String name = bean.getName();
			for (String str : list) {
				if (name.equals(str)) {
					bean.setStates("1");
					if (key.equals(str)) {
						bean.setStates("0");
					}
					break;
				} else {
					bean.setStates("2");
				}
			}
			list2.add(bean);
		}
		return list2;
	}
}
