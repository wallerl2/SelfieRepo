package com.mooc.selfie.factory;

import com.mooc.selfie.model.Video;
import com.mooc.selfie.util.video.FFMpegConverter;
import com.mooc.selfie.util.video.VideoConverter;

public class VideoFactory extends AbstractVideoFactory{

	public VideoConverter getVideoConverter() {
		return new FFMpegConverter();
	}

	public Video getVideo() {
		return new Video();
	}
}
