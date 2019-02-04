package com.travian.account.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.travian.account.controller.AccountController;
import com.travian.account.request.HttpRequest;
import com.travian.account.request.VillageInfoRequest;
import com.travian.account.response.HttpResponse;
import com.travian.account.response.Village;
import com.travian.account.util.VillageUtil;

@Service
public class VillageService {
	private static final Logger Log = LoggerFactory.getLogger(VillageService.class);
	
	
	@Autowired
	private HTTPRequestService httpService;
	
	public List<Village> getVillagesInfo(VillageInfoRequest request){
		List<Village> response = request.getLink().stream().map(e->getVillageInfo(e, request.getCookies(), request.getHost())).collect(Collectors.toList());
		return response;
	}

	private Village getVillageInfo(String link, Map<String, String> cookies, String host){
		try {
			Village village = new Village();
			HttpRequest resourceRequest  = new HttpRequest();
			resourceRequest.setCookies(cookies);
			resourceRequest.setHost(host);
			resourceRequest.setHttpMethod(HttpMethod.GET);
			resourceRequest.setPath("/dorf1.php"+link);
			HttpResponse resourceResponse = httpService.get(resourceRequest);
			VillageUtil.parseResource(resourceResponse);
			return village;
		} catch (Exception e) {
			Log.error("", e);
		}
		return null;
	}
}