package com.internetware.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.internetware.entity.api.DZDPdiscountInfo;
import com.internetware.entity.api.DZDPpromotionInfo;
import com.internetware.entity.api.DZDPshop;
import com.internetware.entity.api.LocalLifeResponse;
import com.internetware.entity.api.MTCity;
import com.internetware.entity.api.MTCouponItem;
import com.internetware.entity.api.MTResponseResult;
import com.internetware.entity.api.ReturnJSONStr;

public class CommonFunc {
	
	private static Gson gson = new Gson();
	
	public static ReturnJSONStr<MTResponseResult<MTCouponItem>> sortByDistance(
			ReturnJSONStr<MTResponseResult<MTCouponItem>> mtreturnJSONStr) {

		String regex = "(?<distance>[1-9][0-9]*\\.?[0-9]*)";
		Pattern p = Pattern.compile(regex);
		Matcher m;
		for (int i = 0; i < mtreturnJSONStr.getResult().getList().size(); i++) {
			String tmp = mtreturnJSONStr.getResult().getList().get(i).getDistance();
			m = p.matcher(tmp);
			while (m.find()) {
				tmp = m.group("distance");
			}
			if (mtreturnJSONStr.getResult().getList().get(i).getDistance()
					.substring(mtreturnJSONStr.getResult().getList().get(i).getDistance().length() - 2).equals("km")) {
				mtreturnJSONStr.getResult().getList().get(i).setDistance(tmp);
			} else {
				mtreturnJSONStr.getResult().getList().get(i).setDistance("" + (Float.valueOf(tmp) / 1000));
			}
		}
		// 根据美团的距离进行排序
		Comparator<MTCouponItem> c = new Comparator<MTCouponItem>() {
			@Override
			public int compare(MTCouponItem o1, MTCouponItem o2) {
				float dis1, dis2;
				dis1 = Float.valueOf(o1.getDistance());
				dis2 = Float.valueOf(o2.getDistance());
				if (dis1 > dis2)
					return 1;
				else
					return -1;
			}
		};

		// 只有当店铺数目大于2时，才需要进行距离排序
		if (mtreturnJSONStr.getResult().getList().size() >= 2) {
			mtreturnJSONStr.getResult().getList().sort(c);
		}

		return mtreturnJSONStr;
	}
	
	public static ReturnJSONStr<MTResponseResult<MTCity>> getCityList(String cityName) throws IOException {

		HashMap<String, String> para = new HashMap<String, String>();
		para.put("keyword", cityName);
		LocalLifeResponse resultGetCityList = HttpRequest.sendLLDianPingPost("com.sankuai.meituan", "7.4.2",
				"getCityList", para);
		ReturnJSONStr<MTResponseResult<MTCity>> rtnGetCityList = gson.fromJson(resultGetCityList.getReturnJSONStr(),
				new TypeToken<ReturnJSONStr<MTResponseResult<MTCity>>>() {
				}.getType());

		return rtnGetCityList;
	}

	public static ReturnJSONStr<MTResponseResult<MTCouponItem>> searchCouponList(String keyword, String mtCItyID, 
			String lat, String lng) throws IOException {
		// POST请求构造
		HashMap<String, String> para = new HashMap<String, String>();
		LocalLifeResponse result;
		para.put("keyword", keyword);
		para.put("cityId", mtCItyID);
		para.put("latlng", lat + "," + lng);
		para.put("start", "0");
		para.put("limit", "20");
		result = HttpRequest.sendLLDianPingPost("com.sankuai.meituan", "7.4.2", "searchCouponList", para);
		ReturnJSONStr<MTResponseResult<MTCouponItem>> mtreturnJSONStr = gson.fromJson(result.getReturnJSONStr(),
				new TypeToken<ReturnJSONStr<MTResponseResult<MTCouponItem>>>() {
				}.getType());

		return mtreturnJSONStr;
	}
	
	public static ReturnJSONStr<ArrayList<DZDPshop>> searchShopList(String mtMinDisShopName, 
			String dzdpCityID, String lat, String lng) throws IOException {
		HashMap<String, String> para2 = new HashMap<String, String>();
		para2.put("keyword", mtMinDisShopName);
		para2.put("lat", lat);
		para2.put("lng", lng);
		para2.put("cityId", dzdpCityID);

		LocalLifeResponse result;
		result = HttpRequest.sendLLDianPingPost("com.dianping.v1", "9.8.2", "searchShopList", para2);

		ReturnJSONStr<ArrayList<DZDPshop>> dzdpReturnJSONStr = gson.fromJson(result.getReturnJSONStr(),
				new TypeToken<ReturnJSONStr<ArrayList<DZDPshop>>>() {
				}.getType());

		return dzdpReturnJSONStr;
	}

	public static ReturnJSONStr<ArrayList<DZDPdiscountInfo>> getDiscountInfo(String focusShopID, String dzdpCityID)
			throws IOException {
		HashMap<String, String> para3 = new HashMap<String, String>();
		para3.put("shopId", focusShopID);
		para3.put("cityId", dzdpCityID);

		LocalLifeResponse result;
		result = HttpRequest.sendLLDianPingPost("com.dianping.v1", "9.8.2", "getDiscountInfo", para3);

		ReturnJSONStr<ArrayList<DZDPdiscountInfo>> dzdpreturnJSONStr = gson.fromJson(result.getReturnJSONStr(),
				new TypeToken<ReturnJSONStr<ArrayList<DZDPdiscountInfo>>>() {
				}.getType());

		return dzdpreturnJSONStr;
	}

	public static ReturnJSONStr<ArrayList<DZDPpromotionInfo>> getPromotionInfo(String focusShopID, String dzdpCityID)
			throws IOException {
		HashMap<String, String> para3 = new HashMap<String, String>();
		para3.put("shopId", focusShopID);
		para3.put("cityId", dzdpCityID);

		LocalLifeResponse result;
		result = HttpRequest.sendLLDianPingPost("com.dianping.v1", "9.8.2", "getPromotionInfo", para3);
		ReturnJSONStr<ArrayList<DZDPpromotionInfo>> dzdpreturnJSONStr3 = gson.fromJson(result.getReturnJSONStr(),
				new TypeToken<ReturnJSONStr<ArrayList<DZDPpromotionInfo>>>() {
				}.getType());
		return dzdpreturnJSONStr3;
	}
	
}
