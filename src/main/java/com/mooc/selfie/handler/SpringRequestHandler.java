package com.mooc.selfie.handler;

import org.springframework.http.ResponseEntity;

import com.mooc.selfie.model.Image;

public interface SpringRequestHandler {
	public ResponseEntity saveImage(Image image);
	
	public ResponseEntity saveImages(Image[] images) ;

	public ResponseEntity<?> getVideo() ;

}
