package com.mooc.selfie.handler;

import com.mooc.selfie.model.Image;

public class ImageHandlerImpl implements ImageHandler<Image>{
	
	@Override
	public void handle(Image image) throws Exception {
		image.save();
	}

	@Override
	public void handle(Image[] images) throws Exception {
		for (Image image : images) {
			image.save();
		}
	}

}
