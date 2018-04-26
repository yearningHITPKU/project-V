package com.internetware.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.internetware.entity.adapter.DireItem;
import com.internetware.entity.adapter.Reprompt;
import com.internetware.entity.adapter.ReqAdapter;
import com.internetware.entity.adapter.ResAdapter;
import com.internetware.entity.adapter.ResBody;
import com.internetware.entity.api.ApiReturn;
import com.internetware.entity.api.DZDPdiscountInfo;
import com.internetware.entity.api.DZDPpromotionInfo;
import com.internetware.entity.api.DZDPshop;
import com.internetware.entity.api.MTCouponItem;
import com.internetware.entity.api.MTResponseResult;
import com.internetware.entity.api.ReturnJSONStr;
import com.internetware.entity.api.VoiceBoxLocation;
import com.internetware.entity.api.dpdiscount;
import com.internetware.entity.api.mtdiscount;
import com.internetware.entity.mi.MiAttributes;
import com.internetware.entity.mi.MiRequest;
import com.internetware.entity.mi.MiResponse;
import com.internetware.entity.mi.MiResponseBody;
import com.internetware.entity.mi.MiToSpeak;
import com.internetware.util.okhttp;
import com.internetware.util.Constants;
import com.internetware.util.CommonFunc;

@RestController
public class MiLocalLifeController {
	//上个用户没有说退出，那么下个用户依然收到的是上个用户的信息
	private ReturnJSONStr<MTResponseResult<MTCouponItem>> mtCouponList;
	private ReturnJSONStr<ArrayList<DZDPshop>> dzdpShopList;
	private ReturnJSONStr<ArrayList<DZDPdiscountInfo>> dzdpDisInfo;								
	private ReturnJSONStr<ArrayList<DZDPpromotionInfo>> dzdpPromoInfo;
	private String focusShopID = "";// 大众点评ShopID
	private long call_time_1;//第一个API调用所消耗的时间
	private long call_time_2;//第一个API调用所消耗的时间
	// 打印日志信息
	private final Logger logger = LoggerFactory.getLogger(MiLocalLifeController.class);
	VoiceBoxLocation voiceBoxLocation;//音箱的位置
	private List<mtdiscount> foodlist;
	private List<dpdiscount> dfoodlist;
	//　session中持久化的数据
	private HashMap<String, String> attributes;
//	@RequestMapping("/skills/local_life/mi")
//	public ResAdapter index(@RequestBody ReqAdapter rqBody) throws IOException {
//		long start = System.currentTimeMillis();// 统计程序耗时
//		mtCouponList = new ReturnJSONStr<MTResponseResult<MTCouponItem>>();
//		dzdpShopList = new ReturnJSONStr<ArrayList<DZDPshop>>() ;
//		dzdpDisInfo = new ReturnJSONStr<ArrayList<DZDPdiscountInfo>> ();								
//		dzdpPromoInfo = new ReturnJSONStr<ArrayList<DZDPpromotionInfo>>() ;
//		focusShopID = "";// 大众点评ShopID			
//		logger.info(rqBody.toString());// 打印
//		ResAdapter response = new ResAdapter();// 应答报文
//		ResBody resBody = new ResBody();
//		Reprompt repeat = new Reprompt();
//		ArrayList<DireItem> dires = new ArrayList<DireItem>();
//		resBody.setOutputSpeech("");
//		resBody.setReprompt(repeat);
//		resBody.setDirectives(dires);
//		response.setResponse(resBody);
//		response.setVersion(rqBody.getVersion());
//		resBody.setShouldEndSession(false);
//		//response.setResponse(miResBody);
//		/*MiToSpeak miToSpeak = new MiToSpeak();
//		miToSpeak.setType(0);
//		miToSpeak.setText("");
//		miResBody.setTo_speak(miToSpeak);
//		miResBody.setOpen_mic(false);*/
//		
//		//　session中持久化的数据
//		//attributes = rqBody.getSession().getAttributes();
//		attributes = rqBody.getSession().getAttributes();
//		if(attributes==null) {
//			attributes = new HashMap<String,String>();
//		}
//		//response.setSession_attributes(attributes);
//		
//		// 如果用户没说话（静音错误）
//		if(rqBody.getRequest().isNo_response()) {
//			miToSpeak.setType(0);			
//			if(rqBody.getSession().getAttributes().getNoResponseCount() == 0) {
//				attributes.setNotUnderstandCount(0);
//				attributes.setNoResponseCount(1);
//				miToSpeak.setText("您还在旁边吗？您可以说：查一下肯德基的优惠，不说话下次将会自动退出哦~");
//				miResBody.setOpen_mic(true);			
//			} else if(rqBody.getSession().getAttributes().getNoResponseCount() == 1){
//				attributes.setNoResponseCount(2);
//				miToSpeak.setText("退出芭乐生活");
//				response.setIs_session_end(true);	
//			}
//			return response;// 自动转换成JSON
//		}
//			
//		String inputText = rqBody.getQuery();// 用户语音输入
//
//		// 处理小米开放平台发送过来的POST请求报文
//		if (rqBody != null && inputText != null) {
//			// 去语句中的标点符号
//			String glstr = inputText.replaceAll("\\pP", "");
//			// 分析用户意图
//			Map<String, String> slots = extractSlots(inputText);
//			if(attributes != null) {
//				voiceBoxLocation = attributes.getBoxlocation();
//			    logger.info(voiceBoxLocation.getCity() + "　在美团上的id:　" + attributes.getMtCityID());
//				logger.info(voiceBoxLocation.getCity() + "　在大众点评上的id:　" + attributes.getDzdpCityID());
//			}		
//			// 根据用户意图调用对应的业务逻辑
//			if (slots.get("operation").equals("open")) {// 打开应用
//				miResBody.setOpen_mic(true);
//				miResBody.getTo_speak().setType(0);
//				miResBody.getTo_speak().setText(Constants.TOSAYOPEN+","+Constants.TOSAYLOC);
//			} else if (slots.get("operation").equals("compare")) {// 查询同一家店面在不同平台的优惠信息
//				if(rqBody.getSession().getAttributes().getBoxlocation().getCity() == null) {
//					miResBody.getTo_speak().setType(0);
//					miResBody.getTo_speak().setText(Constants.TOSAYLOC);
//					attributes.setLastsaying(Constants.TOSAYLOC);
//					return response;
//				}
//				compare(slots.get("keyword"), miResBody);		
//			} else if (slots.get("operation").equals("foodtype")) {
//				if(rqBody.getSession().getAttributes().getBoxlocation().getCity()==null) {
//					miResBody.getTo_speak().setType(0);
//					miResBody.getTo_speak().setText(Constants.TOSAYLOC);
//					attributes.setLastsaying(Constants.TOSAYLOC);
//					return response;
//				}
//				String tmp = slots.get("keyword");
//				queryfood(tmp, miResBody);
//			} else if (slots.get("operation").equals("findfood")) {
//				logger.debug("findfood!!!!");
//				miResBody.getTo_speak().setText("我在,请问您想吃什么呢？");
//				attributes.setLastsaying(Constants.TOSAYFOOD);
//			} else if(slots.get("operation").equals("querylocation")) {
//				String address = slots.get("address");
//				String gaodeurl = "http://restapi.amap.com/v3/geocode/geo?key=db36532173dc98e44eb3b6733aa1119b&address="+address;
//				okhttp ok = new okhttp();
//				String gaodeResult = ok.get(gaodeurl);
//				logger.info(gaodeResult);
//				JSONObject json;
//				try {
//					json = new JSONObject(gaodeResult);
//					if(json.getInt("count")>0) {
//						JSONArray list = json.getJSONArray("geocodes");
//						JSONObject geocodes = list.getJSONObject(0);
//						logger.debug(geocodes.get("location").toString());
//						voiceBoxLocation.setCity(geocodes.get("city").toString());
//						logger.info("高德地图为您找到的城市名称 =====>" + voiceBoxLocation.getCity());
//						//voiceBoxLocation.setCity("北京市");
//						voiceBoxLocation.setLat(geocodes.get("location").toString().split(",")[1]);
//						voiceBoxLocation.setLng(geocodes.get("location").toString().split(",")[0]);
//						attributes.setBoxlocation(voiceBoxLocation);
//					}else {
//						miResBody.getTo_speak().setText("请告诉我您现在的位置，如：我在北京市海淀区水清木华园");
//						return response;
//					}
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				miResBody.getTo_speak().setText("已经得到您的位置信息"+Constants.TOSAYINSTRUCTION);
//				attributes.setLastsaying("");
//				// 第一次查地理位置信息
//				String cityName = voiceBoxLocation.getCity().substring(0, voiceBoxLocation.getCity().length() - 1);
//				attributes.setMtCityID(CommonFunc.getCityList(cityName).getResult().getList().get(0).getCityId());
//				logger.info(voiceBoxLocation.getCity() + " = " + attributes.getMtCityID() + "(美团)");	
//				attributes.setDzdpCityID(Constants.dzdpCityMapID.get(voiceBoxLocation.getCity().substring(0, voiceBoxLocation.getCity().length() - 1)));
//				logger.info(voiceBoxLocation.getCity() + " = " + attributes.getDzdpCityID() + "(大众点评)");
//			}else if (slots.get("operation").equals("findnumber")) {
//				int select = 0;
//				logger.debug(glstr);
//				for (int i = 0; i < 3; i++) {
//					if (glstr.contains(Constants.number[i])) {
//						select = i + 1;
//						logger.debug(select + "");
//					}else if(glstr.contains(Constants.number1[i])){
//						select = i + 1;
//						logger.debug(select + "");
//					}
//				}
//				miResBody.getTo_speak().setText("您选择的是第" + select + "家店,已为您预定");
//			} else if (slots.get("operation").equals("end")) {
//				response.setIs_session_end(true);
//				miResBody.getTo_speak().setType(0);
//				miResBody.getTo_speak().setText("已为您退出芭乐生活");
//			} else {// 未识别用户意图
//				miResBody.getTo_speak().setType(0);
//					if(rqBody.getSession().getAttributes().getNotUnderstandCount() == 0) {
//						attributes.setNoResponseCount(0);
//						attributes.setNotUnderstandCount(1);
//						miToSpeak.setText("不好意思,芭乐没有找到，请您按照示例说法再说一遍.如:查一下肯德基的优惠信息．");
//						miResBody.setOpen_mic(true);					
//					} else if(rqBody.getSession().getAttributes().getNotUnderstandCount() == 1) {
//						attributes.setNotUnderstandCount(2);
//						miToSpeak.setText("如果您不想查询优惠信息或者附近的好吃的，可以说退出哦！");
//						miResBody.setOpen_mic(true);
//					} else if(rqBody.getSession().getAttributes().getNotUnderstandCount() == 2) {
//						attributes.setNotUnderstandCount(3);
//						miToSpeak.setText("退出芭乐生活");
//						response.setIs_session_end(true);
//					}
//			}
//		} else {
//			logger.info("content is null");
//		}
//		
//		long end = System.currentTimeMillis();   
//		System.out.println("\ncall_time_1 = " + call_time_1 + " ms\n"); 
//		System.out.println("\ncall_time_2 = " + call_time_2 + " ms\n"); 
//        System.out.println("\nTotal run time = " + (end - start) + " ms\n"); 
//        //logger.info("Total run time = " + (end - start) + " ms");
//        
//        logger.debug(response.toString());
//		
//		return response;// 自动转换成JSON
//	}
//
//	private void queryfood(String tmp, MiResponseBody miResBody) {
//		String cityName = voiceBoxLocation.getCity().substring(0, voiceBoxLocation.getCity().length() - 1);
//		try {
//			attributes.setMtCityID(CommonFunc.getCityList(cityName).getResult().getList().get(0).getCityId());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		logger.info(voiceBoxLocation.getCity() + " = " + attributes.getMtCityID() + "(美团)");	
//		attributes.setDzdpCityID(Constants.dzdpCityMapID.get(voiceBoxLocation.getCity().substring(0, voiceBoxLocation.getCity().length() - 1)));
//		logger.info(voiceBoxLocation.getCity() + " = " + attributes.getDzdpCityID() + "(大众点评)");
//			String foodname = tmp==null?attributes.getFoodname():tmp;
//			logger.debug(foodname);
//			foodlist = findfood(attributes.getMtCityID(), voiceBoxLocation, foodname);// 美团查询周边美食
//			if(foodlist==null) {
//				miResBody.getTo_speak().setText("抱歉芭乐没有找到" + foodname);
//			}else {
//				recommend(1);// 排序给用户推荐
//				for (int i = 0; i < 3; i++) {
//					if(Float.parseFloat(foodlist.get(i).getDistance())<1.0){
//						int dis= (int)(Float.parseFloat(foodlist.get(i).getDistance())*1000);
//						miResBody.getTo_speak().setText(miResBody.getTo_speak().getText()+foodlist.get(i).getShopName()+"离您"+dis+"米");
//					}else {
//						miResBody.getTo_speak().setText(miResBody.getTo_speak().getText()+foodlist.get(i).getShopName()+"离您"+foodlist.get(i).getDistance()+"公里");
//					}
//				}
//				String text = miResBody.getTo_speak().getText();
//				miResBody.getTo_speak().setText(text+"已找到离您最近的三家店");
//			}
//	}
//
//	private void recommend(int num) {
//		String regex = "(?<distance>[1-9][0-9]*\\.?[0-9]*)";
//		Pattern p = Pattern.compile(regex);   
//		for(int i=0;i<foodlist.size();i++)
//		{
//			Matcher m = p.matcher(foodlist.get(i).getDistance());
//			while(m.find()) {//将距离信息全部改为1.11这种形式
//				String dis = m.group("distance");
//				if(foodlist.get(i).getDistance().substring(foodlist.get(i).getDistance().length()-2).equals("km")) {
//					foodlist.get(i).setDistance(Float.valueOf(dis)+"");
//            	} else {
//            		foodlist.get(i).setDistance(Float.valueOf(dis)/1000.0+"");
//            	}
//			}
//		}
//		switch (num) {
//		case 1:
//			foodlist.sort(new Comparator<mtdiscount>() {
//
//				@Override
//				public int compare(mtdiscount o1, mtdiscount o2) {
//					float dis1, dis2;
//					dis1 =Float.valueOf(o1.getDistance());
//					dis2 =Float.valueOf(o2.getDistance()); 
//					if(dis1>dis2)
//					return 1;
//					else
//					return -1;
//				}
//				
//			});
//			break;
//		case 2:
//			dfoodlist.sort(new Comparator<dpdiscount>() {
//
//				@Override
//				public int compare(dpdiscount o1, dpdiscount o2) {
//					String commnum1 = o1.getCommentNum().replaceAll("\\pP", "");
//					String commnum2 = o2.getCommentNum().replaceAll("\\pP", "");
//					if (Integer.parseInt(commnum1) < Integer.parseInt(commnum2))
//						return -1;
//					else
//						return 1;
//				}
//			});
//		default:
//			break;
//		}
//	}
//
//	private List<mtdiscount> findfood(String cityid, VoiceBoxLocation poi, String foodname) {
//		List<mtdiscount> mts = null;
//		String Json = "{\r\n" + "      \"pkgName\": \"com.sankuai.meituan\",\r\n"
//				+ "      \"versionName\": \"7.4.2\",\r\n" + "      \"methodName\": \"searchCouponList\",\r\n"
//				+ "      \"argsJSONStr\": \"{\\\"keyword\\\":\\\"" + foodname + "\\\",\\\"cityId\\\":\\\"" + cityid
//				+ "\\\",\\\"latlng\\\":\\\"" + poi.getLat() + "," + poi.getLng()
//				+ "\\\",\\\"start\\\":\\\"0\\\",\\\"limit\\\":\\\"15\\\"}\"\r\n" + "}";
//		String result0 = null;
//		try {
//			okhttp ok = new okhttp();
//			result0 = ok.post("http://59.110.30.187:8888/syncTasks", Json);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		Gson gson = new Gson();
//		ApiReturn msg = gson.fromJson(result0, ApiReturn.class);
//		JSONObject json;
//		try {
//			json = new JSONObject(msg.getReturnJSONStr());
//			JSONObject result = (JSONObject) json.get("result");
//			JSONArray list = result.getJSONArray("list");
//			mts = new ArrayList<mtdiscount>();
//			for (int i = 0; i < list.length(); i++) {
//				mtdiscount mt = gson.fromJson(list.getJSONObject(i).toString(), mtdiscount.class);
//				mts.add(mt);
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return mts;
//	}
//
//	public void compare(String shopName, MiResponseBody miResponseBody) {	
//		long time_tmp = System.currentTimeMillis();
//		Thread[] threads = {
//				new Thread(()->{
//					try {
//						/******************* com.sankuai.meituan -> searchCouponList (查美团，得店铺) (777--1500ms) ***************/
//						mtCouponList = CommonFunc.searchCouponList(shopName, attributes.getMtCityID(),
//								attributes.getBoxlocation().getLat(),attributes.getBoxlocation().getLng() );
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}),			
//				new Thread(()->{
//					 try {
//						 /******************* com.dianping.v1 -> searchShopList (查大众点评相关店铺) 约400---800ms*******************/	
//						dzdpShopList = CommonFunc.searchShopList(shopName, attributes.getDzdpCityID(),
//								attributes.getBoxlocation().getLat(),attributes.getBoxlocation().getLng());
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				})
//		};		
//		// Start all threads
//		Arrays.stream(threads).forEach(Thread::start);
//		Arrays.stream(threads).forEach(t -> {
//		    try { t.join(); }
//		    catch (InterruptedException ignore) {}
//		});
//		call_time_1 = System.currentTimeMillis() - time_tmp;
//		
//		String mtMinDisShopName = "";
//		 
//		boolean isMtEmpty = false;
//		if (mtCouponList == null) {
//			isMtEmpty = true;
//		} else {
//			if(mtCouponList.getResult() == null) {
//				isMtEmpty = true;
//			} else {
//				if (Integer.valueOf(mtCouponList.getResult().getTotalCount()) == 0) {
//					isMtEmpty = true;
//				} else {
//					// 按距离排序
//					CommonFunc.sortByDistance(mtCouponList);
//					// 获取店铺名称
//					for(int q = 0; q < mtCouponList.getResult().getList().size(); q++) {
//						if(mtCouponList.getResult().getList().get(q).getDescList().isEmpty() 
//								|| mtCouponList.getResult().getList().get(q).getDescList() == null) {
//							if(q == mtCouponList.getResult().getList().size() - 1){
//								mtMinDisShopName = mtCouponList.getResult().getList().get(0).getShopName();					
//							}
//						} else {
//							mtMinDisShopName = mtCouponList.getResult().getList().get(q).getShopName();
//							break;
//						}
//					}	
//					mtMinDisShopName = mtMinDisShopName.replace("（", "");
//					mtMinDisShopName = mtMinDisShopName.replace("）", "");	
//					logger.info("店铺名称 = " + mtMinDisShopName);
//					for (int i = 0; i < mtCouponList.getResult().getList().size(); i++) {
//						System.out.println("");
//						logger.info(mtCouponList.getResult().getList().get(i).getShopId());
//						logger.info(mtCouponList.getResult().getList().get(i).getShopName());
//						logger.info(mtCouponList.getResult().getList().get(i).getDistance());
//						for (int j = 0; j < mtCouponList.getResult().getList().get(i).getDescList().size(); j++) {
//							logger.info(mtCouponList.getResult().getList().get(i).getDescList().get(j).getDescription());
//						}
//					}
//					// 美团上需要播报的内容
//					int i = 0;
//					for (; i < mtCouponList.getResult().getList().get(0).getDescList().size(); i++) {
//						if (i == 0) {
//							miResponseBody.getTo_speak().setType(0);
//							miResponseBody.getTo_speak().setText(mtMinDisShopName + ",在美团上的优惠是:" 
//							+ mtCouponList.getResult().getList().get(0).getDescList().get(i).getDescription());
//						} else {
//							miResponseBody.getTo_speak().setText(
//									miResponseBody.getTo_speak().getText() 
//									+ "." 
//									+ mtCouponList.getResult().getList().get(0).getDescList().get(i).getDescription() );
//						}
//					}
//				}
//			}		
//		}
//		
//		if(dzdpShopList == null || isMtEmpty == true) {
//		} else {
//			if(dzdpShopList.getResult() == null || dzdpShopList.getResult().isEmpty()) {
//			} else {
//				if(dzdpShopList.getResult().get(0) == null) {
//				}else {
//					// 遍历大众点评结果，找到与美团对应的店铺
//					for (int i = 0; i < dzdpShopList.getResult().size(); i++) {
//						if (dzdpShopList.getResult().get(i).getName().equals(mtMinDisShopName)) {
//							focusShopID = dzdpShopList.getResult().get(i).getShopId();
//							logger.info(dzdpShopList.getResult().get(i).getShopId());
//							logger.info(dzdpShopList.getResult().get(i).getName());
//							logger.info(dzdpShopList.getResult().get(i).getAddress());
//							logger.info(dzdpShopList.getResult().get(i).getTag());
//							logger.info(dzdpShopList.getResult().get(i).getDistance());
//							break;
//						}
//					}
//					logger.info("shopId = " + focusShopID);
//					if (!focusShopID.equals("")) {
//						time_tmp = System.currentTimeMillis();
//						// 根据店铺ID查找店铺在大众点评上的折扣信息				
//						Thread[] threads2 = {
//								new Thread(()->{
//									try {
//										/****************** com.dianping.v1 -> getDiscountInfo (150--750ms)*******************/	
//										dzdpDisInfo = CommonFunc.getDiscountInfo(focusShopID,attributes.getDzdpCityID());
//									} catch (IOException e) {
//										e.printStackTrace();
//									}
//								}),
//								new Thread(()->{
//									try {
//										/******************* com.dianping.v1 -> getPromotionInfo (200--600ms)*******************/	
//										dzdpPromoInfo = CommonFunc.getPromotionInfo(focusShopID,attributes.getDzdpCityID());
//									} catch (IOException e) {
//										e.printStackTrace();
//									}
//								})
//						};
//						// Start all threads
//						Arrays.stream(threads2).forEach(Thread::start);
//						Arrays.stream(threads2).forEach(t-> {
//						    try { t.join(); }
//						    catch (InterruptedException ignore) {}
//						});
//						
//						call_time_2 = System.currentTimeMillis() - time_tmp;
//						
//					    // 大众点评上需要播报的内容
//						int j = 0;
//						for (; j < dzdpDisInfo.getResult().size(); j++) {
//							if (j == 0) {
//								miResponseBody.getTo_speak().setType(0);
//								miResponseBody.getTo_speak().setText(miResponseBody.getTo_speak().getText() 
//										+ "." + mtMinDisShopName
//										+ ",在大众点评上的优惠是:" + dzdpDisInfo.getResult().get(j).getTitle()
//										+ ",原价" + dzdpDisInfo.getResult().get(j).getValue() + "元"
//										+ ",优惠价" + dzdpDisInfo.getResult().get(j).getPrice() + "元");
//							} else {
//								miResponseBody.getTo_speak().setText(miResponseBody.getTo_speak().getText() 
//										+ "."
//										+ dzdpDisInfo.getResult().get(j).getTitle() + ",原价"
//										+ dzdpDisInfo.getResult().get(j).getValue() + "元" + ",优惠价"
//										+ dzdpDisInfo.getResult().get(j).getPrice() + "元");
//							}
//						}
//
//						for (int k = 0; k < dzdpPromoInfo.getResult().size(); k++) {
//							if (k == 0) {
//								miResponseBody.getTo_speak().setText(miResponseBody.getTo_speak().getText() 
//										+ "." + mtMinDisShopName
//										+ ",在大众点评上的促销活动是:"
//										+ dzdpPromoInfo.getResult().get(k).getTitle());
//							} else {
//								miResponseBody.getTo_speak().setText(miResponseBody.getTo_speak().getText() 
//										+ "."
//										+ dzdpPromoInfo.getResult().get(k).getTitle());
//							}
//						}
//					}
//				}
//			}
//		}
//		if(miResponseBody.getTo_speak().getText() == "") {
//			miResponseBody.getTo_speak().setText("不好意思，芭乐没有找到" + shopName + "的优惠信息，换家店试试");
//		}
//		logger.info("********" + miResponseBody.getTo_speak().getText());
//	}
//	
//	// 通过正则表达式提取用户意图
//	private Map<String, String> extractSlots(String input_text) {
//		// 去标点符号
//		input_text = input_text.replaceAll("\\pP", "");
//		logger.info(input_text);
//
//		Map<String, String> intent = new HashMap<String, String>();
//
//		final String open = "(打开|启动|进入|用一下|我想使用|把).*(芭|八|吧|扒|巴|罢)(乐|了|勒)生活(打开)?(一下)?";
//		final String help = "xxxxxxxxxxxxxxxxxxxx";
//		final String end = "(把|将|给我)?(退出|结束|关闭|关掉|关一下)(芭乐生活)?(退出一下|结束|关闭|关掉|关一下)?";
//		final String compare = "(那|再|在)?" + "(让|请|那|再|在|用)?" + "(让|请|那|再|在|用)?" + "((芭|巴)(乐|勒)生活)?" + "(帮|给|绑|替)?"
//				+ "(我|俺|老子|忙)?" + "(看|砍|查|产|搜|茶|瞧)" + "(吓|下|一|以|看|砍|查|产|搜|茶|瞧|寻|询)?" + "(吓|下|一|以|看|砍|查|产|搜|茶|瞧)?"
//				+ "(下)?" + "(?<keyword>.+)";
//		Pattern pattern = Pattern.compile(compare);
//		Matcher matcher = pattern.matcher(input_text);
//
//		final String query = "(请|让|使|命令)?(你|芭乐生活)?(请)?(给我|帮我)?(查|查询|看|知道|茶)?(一下)?(我)?(附近|附近的|周围|周围的|周边|周边的|跟前|跟前的)?(好吃的|美食)";
//		final String findyouhui = "(让|使|命令)?(芭乐生活)?(查|查询|看|知道|茶)(一下)?优惠信息";
//		final String food = "(告诉)?((芭|巴)(乐|勒)生活)?(我|我们)(想|要)?去?吃?.*";
//		final String number = "(芭乐生活|我(要|想))?(去)?(第)?((一|壹)|(二|贰)|(三|叁)|四|五|六|七|八|九|十|十一|十二|十三|十四|十五).*";
//
//		if(attributes.getLastsaying().contains(Constants.TOSAYOPEN)) {
//			logger.info("");
//			logger.debug("matched---->location");
//			String temp;
//			if(input_text.contains("这")&&input_text.contains("在")) {
//				temp = input_text.substring(input_text.indexOf("在")+1, input_text.indexOf("这"));
//			}else if(input_text.contains("在")&&!input_text.contains("这")) {
//				temp = input_text.substring(input_text.indexOf("在")+1);
//			}else if(!input_text.contains("在")&&input_text.contains("这")) {
//				temp = input_text.substring(0,input_text.indexOf("这"));
//			}else {
//				temp = input_text;
//			}
//			intent.put("address", temp);
//			intent.put("operation", "querylocation");
//			logger.debug(temp);
//		}else {
//			if (input_text.matches(open)) {
//				intent.put("operation", "open");
//				attributes.setLastsaying(Constants.TOSAYOPEN);
//			} else if (input_text.matches(food)) {
//				logger.debug("maches!!food");
//				String foodname = null;
//				String operation = null;
//					if (input_text.indexOf("吃")<(input_text.length()-1)&&input_text.indexOf("吃")>0) {
//						foodname = input_text.substring(input_text.indexOf("吃") + 1);
//						operation = "foodtype";
//					}else if(input_text.indexOf("去")<(input_text.length()-1)&&input_text.indexOf("去")>0) {
//						foodname = input_text.substring(input_text.indexOf("去") + 1);
//						operation = "foodtype";
//					}else if(input_text.indexOf("要")<(input_text.length()-1)&&input_text.indexOf("要")>0) {
//						foodname = input_text.substring(input_text.indexOf("要") + 1);
//						operation = "foodtype";
//					}else if(input_text.indexOf("想")<(input_text.length()-1)&&input_text.indexOf("想")>0) {
//						foodname = input_text.substring(input_text.indexOf("想") + 1);
//						operation = "foodtype";
//					}else {
//						operation = "else";
//					}
//				intent.put("keyword", foodname);
//				intent.put("operation", operation);
//				attributes.setFoodname(foodname);
//			}else if (input_text.matches(query)) {
//				logger.info("");
//				logger.debug("maches!!query");
//				intent.put("operation", "findfood");
//			} else if (input_text.matches(findyouhui)) {
//				logger.info("");
//				logger.debug("maches---->findyouhui");
//				intent.put("operation", "findyouhui");
//			}else if (input_text.matches(number)) {
//				logger.info("");
//				logger.debug("maches!!number");
//				intent.put("operation", "findnumber");
//			} else if (matcher.find()) {
//				logger.info("");
//				logger.debug("matched---->compare");
//				intent.put("operation", "compare");
//				String temp = matcher.group("keyword");
//				String compare2 = "(?<keyword>.+?)" + "(最近|今天|今儿|现在)?" + "(的|得|滴|有)?" + "(什么|啥)?" + "(子|紫|儿)?"
//						+ "(优惠|活动|促销|折扣)?" + "(信息|活动|促销|折扣|优惠|呢|哇|呀|吗|嘛|吧|的|得|滴|有|好|哦|撒)" + "(呢|哇|呀|吗|嘛|吧|巴|把|芭|八|不)?"
//						+ "(好|咯)?" + "(呢|哇|呀|吗|嘛|咯)?";
//				pattern = Pattern.compile(compare2);
//				matcher = pattern.matcher(temp);
//				if (matcher.find()) {
//					intent.put("keyword", matcher.group("keyword"));
//				} else {
//					intent.put("keyword", temp);
//				}
//				attributes.setShopname(intent.get("keyword"));
//				logger.info(intent.get("keyword"));
//				
//			} else if (input_text.matches(end)) {
//				logger.debug("matched---->end");
//				intent.put("operation", "end");
//			} else if (input_text.matches(help)) {
//				logger.debug("matched---->help");
//				intent.put("operation", "help");
//			} else {
//				logger.debug("matched---->else");
//				intent.put("operation", "else");
//			}
//			// final String summary = "";
//			// 多轮对话
//		}
//		return intent;
//	}
}