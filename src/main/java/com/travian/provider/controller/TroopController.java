package com.travian.provider.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

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

import com.travian.provider.request.TroopEvasionRequest;
import com.travian.provider.request.TroopTrainRequest;
import com.travian.provider.response.EvasionResponse;
import com.travian.provider.response.Status;
import com.travian.provider.response.TroopTrainResponse;
import com.travian.provider.service.TroopService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/troop")
public class TroopController {
	
	
	@Autowired
	private TroopService service;
	
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = Status.class),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
	@RequestMapping(value="/training", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TroopTrainResponse> trainTroop(@RequestBody TroopTrainRequest request, HttpServletRequest servletRequest, @RequestHeader HttpHeaders headers) throws IOException {
		TroopTrainResponse response = service.trainTroop(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = EvasionResponse.class),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
	@RequestMapping(value="/initiateTroopEvation", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EvasionResponse> initiateTroopEvasion(@RequestBody TroopEvasionRequest request, HttpServletRequest servletRequest, @RequestHeader HttpHeaders headers) throws IOException {
		EvasionResponse response = service.initiateTroopEvasion(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = Status.class),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
	@RequestMapping(value="/resolveEvasion", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> resolveEvasion(@RequestBody TroopEvasionRequest request, HttpServletRequest servletRequest, @RequestHeader HttpHeaders headers) throws IOException {
		Status response = service.resolveEvasion(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
