package com.mooc.selfie.factory;

import com.mooc.selfie.model.Video;
import com.mooc.selfie.util.video.VideoConverter;

public abstract class  AbstractVideoFactory {
	
	public VideoConverter getVideoConverter(){
		return null;
	}
	
	public Video getVideo(){
		return null;
	}

}
