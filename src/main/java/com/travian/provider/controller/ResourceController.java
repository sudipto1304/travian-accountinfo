package com.travian.provider.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.travian.provider.request.DeleteTradeRouteRequest;
import com.travian.provider.request.TradeRouteRequest;
import com.travian.provider.request.TroopTrainRequest;
import com.travian.provider.response.Status;
import com.travian.provider.response.TroopTrainResponse;
import com.travian.provider.service.ResourceService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/resource")
public class ResourceController {
	
	
	@Autowired
	private ResourceService service;
	
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = Status.class),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
	@RequestMapping(value="/transfer", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> transferResource(@RequestBody TradeRouteRequest request, HttpServletRequest servletRequest, @RequestHeader HttpHeaders headers) throws IOException, UnirestException, JSONException {
		Status status = service.transferResource(request);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
	
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = Status.class),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
	@RequestMapping(value="/createTradeRoutes", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> createTradeRoute(@RequestBody TradeRouteRequest request, HttpServletRequest servletRequest, @RequestHeader HttpHeaders headers) throws IOException {
		
		Status status = service.createTradeRoutes(request);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
	
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = Status.class),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
	@RequestMapping(value="/deleteAllTradeRoutes", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> deleteTradeRoute(@RequestBody DeleteTradeRouteRequest request, HttpServletRequest servletRequest, @RequestHeader HttpHeaders headers) throws IOException {
		Status status = service.deleteAllTradeRoutes(request);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

}
