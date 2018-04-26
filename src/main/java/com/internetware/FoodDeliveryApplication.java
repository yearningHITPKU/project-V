package com.internetware;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.internetware.util.HttpRequest;
import com.internetware.entity.api.DZDPCity;
import com.internetware.entity.api.LocalLifeResponse;
import com.internetware.entity.api.ReturnJSONStr;
import com.internetware.util.Constants;

@SpringBootApplication
public class FoodDeliveryApplication {
	public static void main(String[] args) throws IOException {

		Gson gson = new Gson();
		HashMap<String, String> paraVBA = new HashMap<String, String>();
		LocalLifeResponse resultVBA;
		resultVBA = HttpRequest.sendLLDianPingPost("com.dianping.v1", "9.8.2", "getCityList", paraVBA);
		ReturnJSONStr<ArrayList<DZDPCity>> dzdpReturnJSONStrLocation = gson.fromJson(resultVBA.getReturnJSONStr(),
				new TypeToken<ReturnJSONStr<ArrayList<DZDPCity>>>() {
				}.getType());

		for (int k = 0; k < dzdpReturnJSONStrLocation.getResult().size(); k++) {		
			Constants.dzdpCityMapID.put(dzdpReturnJSONStrLocation.getResult().get(k).getName(),
					dzdpReturnJSONStrLocation.getResult().get(k).getCityId());
		}

		SpringApplication.run(FoodDeliveryApplication.class, args);

	}
}
