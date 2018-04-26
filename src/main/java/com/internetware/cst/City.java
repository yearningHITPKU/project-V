package com.internetware.cst;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.internetware.entity.api.ApiReturn;
import com.internetware.entity.api.dpcity;
import com.internetware.util.HttpRequest;
import com.internetware.util.okhttp;

public class City {
	private static City instance;
	private Map<String,dpcity> citymap;
	public City() {
		super();
		createmap();
	}

	private void createmap() {
		// TODO Auto-generated method stub
			//大众点评
			String Json = "{\r\n" + 
					"      \"pkgName\": \"com.dianping.v1\",\r\n" + 
					"      \"versionName\": \"9.8.2\",\r\n" + 
					"      \"methodName\": \"getCityList\",\r\n" + 
					"      \"argsJSONStr\": \"\\\"\\\"\"\r\n" + 
					"}";
			String result0 = null;
			try {
				okhttp ok = new okhttp();
				result0 = ok.post("http://59.110.30.187:8888/syncTasks", Json);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Gson gson = new Gson();
			ApiReturn msg = gson.fromJson(result0, ApiReturn.class);
			JSONObject json;
			try {
				json = new JSONObject(msg.getReturnJSONStr());
				JSONArray list = json.getJSONArray("result");
				citymap = new HashMap<String,dpcity>();
				int i;
				for(i=0;i<list.length();i++)
				{
				JSONObject obj = (JSONObject) list.get(i);
				dpcity city = gson.fromJson(obj.toString(), dpcity.class);
				citymap.put(city.getName(), city);
				}
				System.out.println(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static City getInstance() {
		if(instance==null) 	
			instance = new City();
		return instance;
	}

	public Map<String, dpcity> getCitymap() {
		return citymap;
	}

	public void setCitymap(Map<String, dpcity> citymap) {
		this.citymap = citymap;
	}
	
}
