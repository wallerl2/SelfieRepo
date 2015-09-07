package com.mooc.selfie.handler;


import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mooc.selfie.model.Image;
import com.mooc.selfie.model.Video;

public class SpringRequestHandlerImpl implements SpringRequestHandler{
	
	@Autowired
	private VideoHandler videoHandler;

	@Autowired
	private ImageHandler imageHandler;

	public ResponseEntity saveImage(Image image) {		
		try {		
			imageHandler.handle(image);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity saveImages(Image[] images) {
		try {
			imageHandler.handle(images);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getVideo() {
		try {
			Video video = videoHandler.handle();
			return new ResponseEntity(video, HttpStatus.OK);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
