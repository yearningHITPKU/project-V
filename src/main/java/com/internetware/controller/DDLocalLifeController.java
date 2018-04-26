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
import com.internetware.entity.adapter.VBLocation;
import com.internetware.entity.adapter.DireItem;
import com.internetware.entity.adapter.Reprompt;
import com.internetware.entity.adapter.ReqAdapter;
import com.internetware.entity.adapter.ResAdapter;
import com.internetware.entity.adapter.ResBody;
import com.internetware.entity.api.ApiReturn;
import com.internetware.entity.api.DZDPdiscountInfo;
import com.internetware.entity.api.DZDPpromotionInfo;
import com.internetware.entity.api.DZDPshop;
import com.internetware.entity.api.MTCity;
import com.internetware.entity.api.MTCouponItem;
import com.internetware.entity.api.MTResponseResult;
import com.internetware.entity.api.ReturnJSONStr;
import com.internetware.entity.api.VoiceBoxLocation;
import com.internetware.entity.api.dpdiscount;
import com.internetware.entity.api.mtdiscount;
import com.internetware.entity.api.posInfo;
import com.internetware.util.CommonFunc;
import com.internetware.util.Constants;
import com.internetware.util.okhttp;

@RestController
@RequestMapping("/skills")
public class DDLocalLifeController {
	private ReturnJSONStr<MTResponseResult<MTCouponItem>> mtCouponList;
	private ReturnJSONStr<ArrayList<DZDPshop>> dzdpShopList;
	private ReturnJSONStr<ArrayList<DZDPdiscountInfo>> dzdpDiscountInfo;								
	private ReturnJSONStr<ArrayList<DZDPpromotionInfo>> dzdpPromoInfo;
	private String focusShopID = "";// 大众点评ShopID
	// 打印日志信息
	private final Logger logger = LoggerFactory.getLogger(DDLocalLifeController.class);
	private Gson gson = new Gson();
	private VBLocation voiceBoxLocation;
	private String dzdpCityID = "-1";
	private String mtCityID = "-1";
	private posInfo position;
	private List<mtdiscount> foodlist;
	private List<dpdiscount> dfoodlist;
	private String[] number = new String[] { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四",
			"十五" };
	private String[] number1 = new String[] { "壹", "贰", "叁" };

	@RequestMapping("/local_life")
	public ResAdapter index(@RequestBody ReqAdapter rqBody) throws IOException {
		long start = System.currentTimeMillis();// 统计程序耗时
		mtCouponList = new ReturnJSONStr<MTResponseResult<MTCouponItem>>();
		dzdpShopList = new ReturnJSONStr<ArrayList<DZDPshop>>() ;
		dzdpDiscountInfo = new ReturnJSONStr<ArrayList<DZDPdiscountInfo>> ();								
		dzdpPromoInfo = new ReturnJSONStr<ArrayList<DZDPpromotionInfo>>() ;
		focusShopID = "";// 大众点评ShopID	
		logger.info(rqBody.toString());
		ResAdapter res = new ResAdapter();// 应答报文
		ResBody resBody = new ResBody();
		Reprompt repeat = new Reprompt();
		repeat.setOutputSpeech("");
		ArrayList<DireItem> dires = new ArrayList<DireItem>();
		resBody.setOutputSpeech("");
		resBody.setReprompt(repeat);
		resBody.setDirectives(dires);
		res.setResponse(resBody);

		if (rqBody.getExtend().getPoi() == null || rqBody.getExtend().getPoi().isEmpty()) {
			resBody.setShouldEndSession(true);
			resBody.setOutputSpeech("很抱歉，您需要先在叮咚智能音箱Ａpp中设置您的地理位置，芭乐生活才能提供这项服务哦");
		} else {
			ArrayList<VBLocation> poi = rqBody.getExtend().getPoi();

			if (poi != null) {
				if(!poi.isEmpty()) {
					voiceBoxLocation = poi.get(0);
					logger.info(voiceBoxLocation.getCity());
					logger.info(voiceBoxLocation.getAddress());
					logger.info(voiceBoxLocation.getLat());
					logger.info(voiceBoxLocation.getLng());
				} else {
					voiceBoxLocation = new VBLocation();
					voiceBoxLocation.setCity("北京市");
					voiceBoxLocation.setLat("39.9924");
					voiceBoxLocation.setLng("116.3115");
				}
			} 

			// 根据city名称搜索出美团上的城市ID
			// com.sankuai.meituan -> getCityList
			String cityName = voiceBoxLocation.getCity().substring(0, voiceBoxLocation.getCity().length() - 1);		
			mtCityID = CommonFunc.getCityList(cityName).getResult().getList().get(0).getCityId();
			logger.info(voiceBoxLocation.getCity() + " = " + mtCityID);

			// 根据city名称搜索出大众点评上的城市ID
			dzdpCityID = Constants.dzdpCityMapID.get(voiceBoxLocation.getCity().substring(0, voiceBoxLocation.getCity().length() - 1));
			logger.info(voiceBoxLocation.getCity() + " = " + dzdpCityID);

			String inputText = rqBody.getRequest().getUtterance();// 用户语音输入

			// 处理叮咚开放平台发送过来的POST请求报文
			if (rqBody != null && inputText != null) {
				String glstr = rqBody.getRequest().getUtterance().replaceAll("\\pP", "");
				// 分析用户意图
				Map<String, String> slots = extractSlots(inputText);
				// 根据用户意图调用对应的业务逻辑
				if (slots.get("operation").equals("open")) {// 打开应用
					resBody.setShouldEndSession(false);;// 会话是否结束
					resBody.setOutputSpeech("芭乐生活已打开,当前城市为" + voiceBoxLocation.getCity() + ",您想知道哪个店铺的优惠信息呢?您可以说帮我查一下附近的美食来查询周边美食");
					repeat.setOutputSpeech("对不起，我没有听明白您的意思，请再说一遍，如：查一下肯德基的优惠信息!或者:我想吃火锅");
				} else if (slots.get("operation").equals("compare")) {// 查询同一家店面在不同平台的优惠信息
					Thread[] threads = {
							new Thread(()->{
								try {
									/******************* com.sankuai.meituan -> searchCouponList (查美团，得店铺) (777--1500ms) ***************/
									mtCouponList = CommonFunc.searchCouponList(slots.get("keyword"), mtCityID,
											voiceBoxLocation.getLat(),voiceBoxLocation.getLng() );
								} catch (IOException e) {
									e.printStackTrace();
								}
							}),
							
							new Thread(()->{
								 try {
									 /******************* com.dianping.v1 -> searchShopList (查大众点评相关店铺) 约400---800ms*******************/	
									dzdpShopList = CommonFunc.searchShopList(slots.get("keyword"), dzdpCityID,
											voiceBoxLocation.getLat(),voiceBoxLocation.getLng());
								} catch (IOException e) {
									e.printStackTrace();
								}
							})
					};
					
					// Start all threads
					Arrays.stream(threads).forEach(Thread::start);
					Arrays.stream(threads).forEach(t -> {
					    try { t.join(); }
					    catch (InterruptedException ignore) {}
					});
					
					boolean isEmpty = false;
					if (mtCouponList == null) {
						isEmpty = true;
					} else {
						if(mtCouponList.getResult() == null) {
							isEmpty = true;
						} else {
							if (Integer.valueOf(mtCouponList.getResult().getTotalCount()) == 0) {
								isEmpty = true;
							}
						}		
					}
					
					// 如果美团有店铺信息
					if (!isEmpty) {
						CommonFunc.sortByDistance(mtCouponList);
						// 获取最近距离的店铺名称
						String mtMinDisShopName = mtCouponList.getResult().getList().get(0).getShopName();
						mtMinDisShopName = mtMinDisShopName.replace("（", "");
						mtMinDisShopName = mtMinDisShopName.replace("）", "");
						logger.info("店铺名称及地址 = " + mtMinDisShopName);
						for (int i = 0; i < mtCouponList.getResult().getList().size(); i++) {
							System.out.println("");
							logger.info(mtCouponList.getResult().getList().get(i).getShopId());
							logger.info(mtCouponList.getResult().getList().get(i).getShopName());
							logger.info(mtCouponList.getResult().getList().get(i).getDistance());
							for (int j = 0; j < mtCouponList.getResult().getList().get(i).getDescList().size(); j++) {
								logger.info(mtCouponList.getResult().getList().get(i).getDescList().get(j)
										.getDescription());
							}
						}
						// 遍历大众点评结果，找到与美团对应的店铺
						for (int i = 0; i < dzdpShopList.getResult().size(); i++) {
							if (dzdpShopList.getResult().get(i).getName().equals(mtMinDisShopName)) {
								focusShopID = dzdpShopList.getResult().get(i).getShopId();
								logger.info(dzdpShopList.getResult().get(i).getShopId());
								logger.info(dzdpShopList.getResult().get(i).getName());
								logger.info(dzdpShopList.getResult().get(i).getAddress());
								logger.info(dzdpShopList.getResult().get(i).getTag());
								logger.info(dzdpShopList.getResult().get(i).getDistance());
								break;
							}
						}
						logger.info("shopId = " + focusShopID);
						// 该店铺在大众点评上
						if (!focusShopID.equals("")) {
							// 根据店铺ID查找店铺在大众点评上的折扣信息				
							Thread[] threads2 = {
									new Thread(()->{
										try {
											/****************** com.dianping.v1 -> getDiscountInfo (150--750ms)*******************/	
											dzdpDiscountInfo = CommonFunc.getDiscountInfo(focusShopID,dzdpCityID);
										} catch (IOException e) {
											e.printStackTrace();
										}
									}),
									new Thread(()->{
										try {
											/******************* com.dianping.v1 -> getPromotionInfo (200--600ms)*******************/	
											dzdpPromoInfo = CommonFunc.getPromotionInfo(focusShopID,dzdpCityID);
										} catch (IOException e) {
											e.printStackTrace();
										}
									})
							};
							
							// Start all threads
							Arrays.stream(threads2).forEach(Thread::start);
							Arrays.stream(threads2).forEach(t-> {
							    try { t.join(); }
							    catch (InterruptedException ignore) {}
							});

							int mtSize = mtCouponList.getResult().getList().get(0).getDescList().size();
							int dzdpSize = dzdpDiscountInfo.getResult().size() + dzdpPromoInfo.getResult().size();
							logger.info("mtSize  = " + mtSize + ",  dzdpSize  = " + dzdpSize);

							if (mtSize + dzdpSize == 0) {
								resBody.setOutputSpeech(mtMinDisShopName  + ",距您"
										+ mtCouponList.getResult().getList().get(0).getDistance() 
										+ "千米" + ",暂时没有优惠信息");
							} else {
								// 美团上需要播报的内容
								int i = 0;
								for (; i < mtSize; i++) {
									//DirectiveItem item = new DirectiveItem();
									if (i == 0) {
										resBody.setOutputSpeech(mtMinDisShopName + ",距您"
												+ mtCouponList.getResult().getList().get(0).getDistance() + "千米"
												+ ",在美团上的优惠是:" + mtCouponList.getResult().getList().get(0).getDescList()
														.get(i).getDescription());
									} else {
										resBody.setOutputSpeech(
												resBody.getOutputSpeech()
												+ "。"
												+ mtCouponList.getResult().getList().get(0).getDescList().get(i).getDescription());
									}
								}

								// 大众点评上需要播报的内容
								int j = 0;
								for (; j < dzdpDiscountInfo.getResult().size(); j++) {
									if (j == 0) {
										if(mtSize == 0) {
											resBody.setOutputSpeech(
													resBody.getOutputSpeech()
													+ "。"
													+ mtMinDisShopName + ",距您"
													+ mtCouponList.getResult().getList().get(0).getDistance() + "千米"
													+ ",在大众点评上的优惠是:"
													+ dzdpDiscountInfo.getResult().get(j).getTitle() + ",原价"
													+ dzdpDiscountInfo.getResult().get(j).getValue() + "元" + ",优惠价"
													+ dzdpDiscountInfo.getResult().get(j).getPrice() + "元");
										} else {
											resBody.setOutputSpeech(
													resBody.getOutputSpeech()
													+ "。"
													+ mtMinDisShopName + ",在大众点评上的优惠是:"
													+ dzdpDiscountInfo.getResult().get(j).getTitle() + ",原价"
													+ dzdpDiscountInfo.getResult().get(j).getValue() + "元" + ",优惠价"
													+ dzdpDiscountInfo.getResult().get(j).getPrice() + "元");
										}								
									} else {
										resBody.setOutputSpeech(
												resBody.getOutputSpeech()
												+ "。"
												+ dzdpDiscountInfo.getResult().get(j).getTitle() + ",原价"
												+ dzdpDiscountInfo.getResult().get(j).getValue() + "元" + ",优惠价"
												+ dzdpDiscountInfo.getResult().get(j).getPrice() + "元");
									}
								}

								for (int k = 0; k < dzdpPromoInfo.getResult().size(); k++) {
									if (k == 0) {
										resBody.setOutputSpeech(
												resBody.getOutputSpeech()
												+ "。"
												+ mtMinDisShopName + ",在大众点评上的促销活动是:"
												+ dzdpPromoInfo.getResult().get(k).getTitle());
									} else {
										resBody.setOutputSpeech(
												resBody.getOutputSpeech()
												+ "。"
												+ dzdpPromoInfo.getResult().get(k).getTitle());
									}
								}
							}
							if (dzdpSize == 0 && mtSize != 0) {
								resBody.setOutputSpeech(
										resBody.getOutputSpeech()
										+ "。"
										+ mtMinDisShopName + "在大众点评上，暂时没有优惠信息");
							}
							logger.info("*******" + resBody.getOutputSpeech());
						} else {// 该店铺不在大众点评上
							int mtSize = mtCouponList.getResult().getList().get(0).getDescList().size();
							if (mtSize == 0) {
								resBody.setOutputSpeech(slots.get("keyword") + "没有优惠信息！");
							} else {
								// 美团
								int i = 0;
								for (; i < mtSize; i++) {
									if (i == 0) {
										resBody.setOutputSpeech(mtMinDisShopName + ",距您"
												+ mtCouponList.getResult().getList().get(0).getDistance() + "千米"
												+ ",在美团上的优惠是:" 
												+ mtCouponList.getResult().getList().get(0).getDescList().get(i).getDescription());
									} else {
										resBody.setOutputSpeech(
												resBody.getOutputSpeech()
												+ "。"
												+ mtCouponList.getResult().getList().get(0).getDescList().get(i).getDescription());
									}
								}
								resBody.setOutputSpeech(
										resBody.getOutputSpeech()
										+ "。"
										+ mtMinDisShopName + "在大众点评上，暂时没有优惠信息");
							}
							resBody.setOutputSpeech(mtMinDisShopName + ",距您"
											+ mtCouponList.getResult().getList().get(0).getDistance() + "千米,"
											+ resBody.getOutputSpeech());
							logger.info("***********" + resBody.getOutputSpeech());
						}
						/************************* 需要音箱重复播报的内容 *********************/
						repeat.setOutputSpeech("不好意思，我没有听清楚，请再说一遍，如：查一下肯德基的优惠信息");
						/************************* 需要音箱重复播报的内容 *********************/
						resBody.setShouldEndSession(false);
					}
				} else if (slots.get("operation").equals("foodtype")) {
					String foodname = slots.get("keyword");
					logger.debug(foodname);
					String mfindcityId = null;// 美团
					String dfindcityId = null;// 大众点评
					String city = voiceBoxLocation.getCity().substring(0, voiceBoxLocation.getProvince().length() - 1);
					logger.debug(city);
					mfindcityId = mfindcityId(city);
					dfindcityId = Constants.dzdpCityMapID
							.get(voiceBoxLocation.getCity().substring(0, voiceBoxLocation.getCity().length() - 1));
					logger.debug(dfindcityId);
					foodlist = findfood(mfindcityId, voiceBoxLocation, foodname);// 美团查询周边美食
					if (foodlist == null) {
						resBody.setOutputSpeech("抱歉芭乐没有找到" + foodname);
					} else {
						recommend(1);// 排序给用户推荐
						for (int i = 0; i < 3; i++) {
							if (Float.parseFloat(foodlist.get(i).getDistance()) < 1.0) {
								int dis = (int) (Float.parseFloat(foodlist.get(i).getDistance()) * 1000);
								resBody.setOutputSpeech(resBody.getOutputSpeech()
										+ "."
										+ foodlist.get(i).getShopName() + "离您" + dis + "米");
							} else {
								resBody.setOutputSpeech(resBody.getOutputSpeech()
										+ "."
										+ foodlist.get(i).getShopName() + "离您" + foodlist.get(i).getDistance() + "公里");
							}
						}
						resBody.setOutputSpeech(resBody.getOutputSpeech()
								+ "."
								+ "已为您找到离您最近的三家店");
					}
					resBody.setShouldEndSession(true);
				} else if (slots.get("operation").equals("findfood")) {
					logger.debug("findfood!!!!");
					resBody.setShouldEndSession(false);
					String province = voiceBoxLocation.getProvince();
					resBody.setOutputSpeech("已经查找到附近美食，您的位置是" + province + "," + "请问您想吃什么");
				}else if (slots.get("operation").equals("findnumber")) {
					int select = 0;
					logger.debug(glstr);
					for (int i = 0; i < 15; i++) {
						if (glstr.contains(number[i])) {
							select = i + 1;
							logger.debug(select + "");
						} else if (glstr.contains(number1[i])) {
							select = i + 1;
							logger.debug(select + "");
						}
					}
					resBody.setShouldEndSession(true);
					resBody.setOutputSpeech("您选择的是第" + select + "家店,已为您预定");
				} else {// 未识别用户意图
					resBody.setOutputSpeech("不好意思,芭乐没有找到，您可以换家店试试");
					repeat.setOutputSpeech("请说出店铺名称，如：让芭乐生活查一下肯德基的优惠信息");
					resBody.setShouldEndSession(true);
				}
			} else {
				logger.info("content is null");
			}

			HashMap<String, String> extend = new HashMap<String, String>();
			extend.put("NO_REC", "0");
		}
		
		res.setVersion("1.0");
		res.setRequestId(rqBody.getRequest().getRequestId());
		//res.setTimestamp(System.currentTimeMillis());

		long end = System.currentTimeMillis();   
        System.out.println("\nTotal run time = " + (end - start) + " ms\n"); 
        logger.info(res.toString());
		return res;// 自动转换成JSON
	}

	private void recommend(int num) {
		String regex = "(?<distance>[1-9][0-9]*\\.?[0-9]*)";
		Pattern p = Pattern.compile(regex);
		for (int i = 0; i < foodlist.size(); i++) {
			Matcher m = p.matcher(foodlist.get(i).getDistance());
			while (m.find()) {// 将距离信息全部改为1.11这种形式
				String dis = m.group("distance");
				if (foodlist.get(i).getDistance().substring(foodlist.get(i).getDistance().length() - 2).equals("km")) {
					foodlist.get(i).setDistance(Float.valueOf(dis) + "");
				} else {
					foodlist.get(i).setDistance(Float.valueOf(dis) / 1000.0 + "");
				}
			}
		}
		switch (num) {
		case 1:
			foodlist.sort(new Comparator<mtdiscount>() {

				@Override
				public int compare(mtdiscount o1, mtdiscount o2) {
					float dis1, dis2;
					dis1 = Float.valueOf(o1.getDistance());
					dis2 = Float.valueOf(o2.getDistance());
					/*
					 * //String regex = "(?<distance>[1-9][0-9]*\\.?[0-9]*)";
					 * 
					 * // 提取距离 String distance1 = o1.getDistance(); Pattern p =
					 * Pattern.compile(regex); Matcher m = p.matcher(o1.getDistance());
					 * while(m.find()) { distance1 = m.group("distance"); }
					 * //logger.info(distance1);
					 * if(o1.getDistance().substring(o1.getDistance().length()-2).equals("km")) {
					 * dis1 = Float.valueOf(distance1); } else { dis1 = (float)
					 * (Float.valueOf(distance1)/1000.0); } // 提取距离 String distance2 =
					 * o2.getDistance(); m = p.matcher(o2.getDistance()); while(m.find()) {
					 * distance2 = m.group("distance"); }
					 * if(o2.getDistance().substring(o2.getDistance().length()-2).equals("km")) {
					 * dis2 = Float.valueOf(distance2); } else { dis2 = (float)
					 * (Float.valueOf(distance2)/1000.0); }
					 */
					if (dis1 > dis2)
						return 1;
					else
						return -1;
				}

			});
			break;
		case 2:
			dfoodlist.sort(new Comparator<dpdiscount>() {

				@Override
				public int compare(dpdiscount o1, dpdiscount o2) {
					String commnum1 = o1.getCommentNum().replaceAll("\\pP", "");
					String commnum2 = o2.getCommentNum().replaceAll("\\pP", "");
					if (Integer.parseInt(commnum1) < Integer.parseInt(commnum2))
						return -1;
					else
						return 1;
				}
			});
		default:
			break;
		}
	}

	private posInfo extractpoi(String string) {
		// 提取用户的位置信息
		JSONArray array;
		posInfo result = new posInfo();
		try {
			array = new JSONArray(string);
			JSONObject com = (JSONObject) array.get(0);
			// System.out.println(com.get("province"));
			result.setLat((String) com.get("lat"));
			result.setLng((String) com.get("lng"));
			result.setProvince((String) com.get("province"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		;
		return result;
	}

	private String mfindcityId(String name) {
		// 向api发送请求
		String cityName = name;
		String cityId = null;
		String Json = "{\r\n" + "      \"pkgName\": \"com.sankuai.meituan\",\r\n"
				+ "      \"versionName\": \"7.4.2\",\r\n" + "      \"methodName\": \"getCityList\",\r\n"
				+ "      \"argsJSONStr\": \"{\\\"keyword\\\":" + "\\\"" + cityName + "\\\"}\"\r\n" + "}";
		String result0 = null;
		try {
			okhttp ok = new okhttp();
			result0 = ok.post("http://59.110.30.187:8888/syncTasks", Json);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Gson gson = new Gson();
		ApiReturn msg = gson.fromJson(result0, ApiReturn.class);
		JSONObject json;
		try {
			json = new JSONObject(msg.getReturnJSONStr());
			JSONObject result = (JSONObject) json.get("result");
			JSONArray list = result.getJSONArray("list");
			MTCity city = gson.fromJson(list.getJSONObject(0).toString(), MTCity.class);
			cityId = city.getCityId();
			// System.out.println(city.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return cityId;
	}

	private List<mtdiscount> findfood(String cityid, VBLocation poi, String foodname) {
		List<mtdiscount> mts = null;
		String Json = "{\r\n" + "      \"pkgName\": \"com.sankuai.meituan\",\r\n"
				+ "      \"versionName\": \"7.4.2\",\r\n" + "      \"methodName\": \"searchCouponList\",\r\n"
				+ "      \"argsJSONStr\": \"{\\\"keyword\\\":\\\"" + foodname + "\\\",\\\"cityId\\\":\\\"" + cityid
				+ "\\\",\\\"latlng\\\":\\\"" + poi.getLat() + "," + poi.getLng()
				+ "\\\",\\\"start\\\":\\\"0\\\",\\\"limit\\\":\\\"15\\\"}\"\r\n" + "}";
		String result0 = null;
		try {
			okhttp ok = new okhttp();
			result0 = ok.post("http://59.110.30.187:8888/syncTasks", Json);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Gson gson = new Gson();
		ApiReturn msg = gson.fromJson(result0, ApiReturn.class);
		JSONObject json;
		try {
			json = new JSONObject(msg.getReturnJSONStr());
			JSONObject result = (JSONObject) json.get("result");
			JSONArray list = result.getJSONArray("list");
			mts = new ArrayList<mtdiscount>();
			for (int i = 0; i < list.length(); i++) {
				mtdiscount mt = gson.fromJson(list.getJSONObject(i).toString(), mtdiscount.class);
				mts.add(mt);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return mts;
	}

	private List<dpdiscount> searchShopList(String cityid, VoiceBoxLocation poi, String foodname) {
		List<dpdiscount> dps = null;
		// 大众点评
		String Json = "{\r\n" + "		      \"pkgName\": \"com.dianping.v1\",\r\n"
				+ "		      \"versionName\": \"9.8.2\",\r\n" + "		      \"methodName\": \"searchShopList\",\r\n"
				+ "		      \"argsJSONStr\": \"{\\\"keyword\\\":\\\"" + foodname + "\\\",\\\"lat\\\":\\\""
				+ poi.getLat() + "\\\",\\\"lng\\\":\\\"" + poi.getLng() + "\\\",\\\"cityId\\\":\\\"" + cityid
				+ "\\\"}\"\r\n" + "		}";

		String result0 = null;
		try {
			okhttp ok = new okhttp();
			result0 = ok.post("http://59.110.30.187:8888/syncTasks", Json);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		logger.debug(result0);
		Gson gson = new Gson();
		ApiReturn msg = gson.fromJson(result0, ApiReturn.class);
		JSONObject json;
		try {
			logger.debug(msg.getReturnJSONStr());
			json = new JSONObject(msg.getReturnJSONStr());
			JSONArray list = json.getJSONArray("result");
			dps = new ArrayList<dpdiscount>();
			for (int i = 0; i < list.length(); i++) {
				dpdiscount dp = gson.fromJson(list.get(i).toString(), dpdiscount.class);
				dps.add(dp);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return dps;
	}
	
	// 通过正则表达式提取用户意图
	private Map<String, String> extractSlots(String input_text) {
		// 去标点符号
		input_text = input_text.replaceAll("\\pP", "");
		logger.info(input_text);

		Map<String, String> intent = new HashMap<String, String>();

		final String open = "(打开|启动|进入).*(芭|八|吧|扒|巴|罢)(乐|了|勒)生活";

		final String help = "xxxxxxxxxxxxxxxxxxxx";

		final String compare = "(那|再|在)?" + "(让|请|那|再|在|用)?" + "(让|请|那|再|在|用)?" + "(芭|八|吧|扒|巴|罢)?"+"(乐|了|勒)?"+"(生活)?" + "(帮|给|绑|替)?"
				+ "(我|俺|老子|忙)?" + "(看|砍|查|产|搜|茶|瞧)" + "(吓|下|一|以|看|砍|查|产|搜|茶|瞧|寻|询)?" + "(吓|下|一|以|看|砍|查|产|搜|茶|瞧)?"
				+ "(下)?" + "(?<keyword>.+)";
		Pattern pattern = Pattern.compile(compare);
		Matcher matcher = pattern.matcher(input_text);

		final String query = "(请|让|使|命令)?(你|芭乐生活)?(请)?(给我|帮我)?(查|查询|看|知道|茶)(一下)?(我)?(附近|附近的|周围|周围的|周边|周边的)?(好吃的|美食)";
		final String findyouhui = "(让|使|命令)?(芭乐生活)?(查|查询|看|知道|茶)(一下)?优惠信息";
		final String stop = "(让|使|命令)?(芭乐生活)(停|别说话|好)";
		final String food = "(告诉)?((芭|巴)(乐|勒)生活)?我(想|要)吃.*";
		final String number = "(芭乐生活|我(要|想))?(去)?(第)?((一|壹)|(二|贰)|(三|叁)|四|五|六|七|八|九|十|十一|十二|十三|十四|十五).*";
		final String renqi = "(给我)?(查询|找|按)?人气(排名|最高)?(的)?";// 评论最多的
		if (input_text.matches(open)) {
			intent.put("operation", "open");
		} else if (input_text.matches(food)) {
			logger.debug("maches!!food");
			if (input_text.contains("吃")) {
				// logger.debug(input_text.substring(input_text.indexOf("吃")+1));
				intent.put("keyword", input_text.substring(input_text.indexOf("吃") + 1));
			} else {
				intent.put("keyword", input_text);
			}
			/*
			 * Pattern pattern = Pattern.compile(food); Matcher matcher =
			 * pattern.matcher(input_text); while(matcher.find()) {
			 * logger.info(matcher.group("keyword")); intent.put("keyword",
			 * matcher.group("keyword")); }
			 */
			intent.put("operation", "foodtype");
		} else if (input_text.matches(renqi)) {
			logger.debug("renqi");
			intent.put("operation", "renqi");
		} else if (input_text.matches(query)) {
			logger.debug("maches!!query");
			intent.put("operation", "findfood");
		} else if (input_text.matches(findyouhui)) {
			logger.debug("maches!!findyouhui");
			intent.put("operation", "findyouhui");
		} else if (input_text.matches(stop)) {
			logger.debug("maches!!stop");
			intent.put("operation", "stop");
		} else if (input_text.matches(number)) {
			logger.debug("maches!!stop");
			intent.put("operation", "findnumber");
		} else if (matcher.find()) {
			intent.put("operation", "compare");
			String temp = matcher.group("keyword");
			String compare2 = "(?<keyword>.+?)" + "(最近|今天|今儿|现在)?" + "(的|得|滴|有)?" + "(什么|啥)?" + "(子|紫|儿)?"
					+ "(优惠|活动|促销|折扣)?" + "(信息|活动|促销|折扣|优惠|呢|哇|呀|吗|嘛|吧|的|得|滴|有|好|哦|撒)" + "(呢|哇|呀|吗|嘛|吧|巴|把|芭|八|不)?"
					+ "(好|咯)?" + "(呢|哇|呀|吗|嘛|咯)?";
			pattern = Pattern.compile(compare2);
			matcher = pattern.matcher(temp);
			if (matcher.find()) {
				intent.put("keyword", matcher.group("keyword"));
			} else {
				intent.put("keyword", temp);
			}
			logger.info(intent.get("keyword"));

		} else if (input_text.matches(help)) {
			logger.debug("matched!!help");
			intent.put("operation", "help");

		} else {
			logger.debug("matched!!help");
			intent.put("operation", "no");

		}
		// final String summary = "";
		// 多轮对话
		return intent;
	}
}