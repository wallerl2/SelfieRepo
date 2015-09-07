package com.mooc.selfie.handler;

import java.io.File;
import java.nio.file.Files;

import com.mooc.selfie.factory.AbstractVideoFactory;
import com.mooc.selfie.factory.FactoryProducer;
import com.mooc.selfie.model.Video;
import com.mooc.selfie.properties.SelfieProperties;
import com.mooc.selfie.util.Base64Converter;
import com.mooc.selfie.util.video.VideoConverter;

public class VideoHandlerImpl implements VideoHandler {
	
	public static AbstractVideoFactory videoFactory  = FactoryProducer.getVideoFactory();

	@Override
	public Video handle() throws Exception {
		convertImagesToVideo();
		return getJSONEncodedVideo();
	}

	private void convertImagesToVideo() throws Exception {
		getVideoConverter().convert();
	}

	private Video getJSONEncodedVideo() throws Exception {
		File file = new File(SelfieProperties.VIDEO_PATH);
		Video video = getVideo();
		video.setName(file.getName());
		String base64String  = Base64Converter.convertToBase64String(Files.readAllBytes(file.toPath()));
		video.setBase64String(base64String);
		return video;
	}
	
	protected VideoConverter getVideoConverter(){
		return videoFactory.getVideoConverter();		
	}
	
	protected Video getVideo(){
		return videoFactory.getVideo();
	}

}
