package com.internetware.util;

import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.internetware.entity.api.LocalLifeRequest;
import com.internetware.entity.api.LocalLifeResponse;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequest {

	private final static String API_URL = "http://59.110.30.187:8888/syncTasks";
	
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	public static OkHttpClient client = new OkHttpClient();

	//private final static String API_URL = "https://requestb.in/ssaawhss";

	// 打印日志信息
	private final static Logger logger = LoggerFactory.getLogger(HttpRequest.class);
	
	public static LocalLifeResponse sendLLDianPingPost(String pkgName, String versionName, String method, HashMap<String, String> argsMap) throws IOException {
		
		Gson gson = new Gson();
		LocalLifeRequest llreq = new LocalLifeRequest();
		llreq.setArgsJSONStr(gson.toJson(argsMap));
		llreq.setMethodName(method);
		llreq.setPkgName(pkgName);
		llreq.setVersionName(versionName);

		String json = gson.toJson(llreq);
		logger.info(json);
		
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(API_URL).post(body).build();
		Response response = client.newCall(request).execute();
		
		String result = response.body().string();
		LocalLifeResponse llres = gson.fromJson(result, LocalLifeResponse.class);
		
		logger.info(result);
		
		return llres;
	}

}