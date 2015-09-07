package com.mooc.selfie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mooc.selfie.handler.SpringRequestHandler;
import com.mooc.selfie.model.Image;

@Controller
public class SelfieServiceController {

	@Autowired
	SpringRequestHandler requestHandler;

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public ResponseEntity uploadImage(@RequestBody Image image) {
		return requestHandler.saveImage(image);
	}

	@RequestMapping(value = "/images", method = RequestMethod.POST)
	public ResponseEntity uploadImages(@RequestBody Image[] images) {
		return requestHandler.saveImages(images);
	}

	@RequestMapping(value = "/video", method = RequestMethod.GET)
	public ResponseEntity<?> getSelfieVideo() {
		return requestHandler.getVideo();
	}
}
