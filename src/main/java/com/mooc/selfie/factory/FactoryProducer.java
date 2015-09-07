package com.mooc.selfie.factory;

import com.mooc.selfie.handler.ImageSaveSupport;
import com.mooc.selfie.handler.SaveSupport;

public class FactoryProducer {
	private static SaveSupport saveSupport;
	
	public static AbstractVideoFactory getVideoFactory(){
		return new VideoFactory();
	}
	
	public static SaveSupport getSaveSupport(){
		if (saveSupport == null){
			saveSupport = new ImageSaveSupport();
		}
		return saveSupport;
	}

}
