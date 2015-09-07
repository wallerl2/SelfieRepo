package com.mooc.selfie.handler;

import java.io.IOException;

import com.mooc.selfie.model.Image;

public interface SaveSupport {
	
	public void save(Image image) throws IOException;

}
