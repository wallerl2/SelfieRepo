package com.mooc.selfie.handler;

import com.mooc.selfie.model.Image;

public interface ImageHandler<T extends Image> {

	public void handle(T t) throws Exception;
	public void handle(T[] t) throws Exception;
	
}
