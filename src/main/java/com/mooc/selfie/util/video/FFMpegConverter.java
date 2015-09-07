package com.mooc.selfie.util.video;

import java.io.File;
import com.mooc.selfie.properties.SelfieProperties;

public class FFMpegConverter implements VideoConverter {

	@Override
	public void convert() throws Exception {
		File filePath = new File(SelfieProperties.FILE_PATH);
		String commands = "ffmpeg -framerate 1 -f image2 -i " + filePath + "\\image%3d.jpg " + "-vf scale=320:-1 " +"-r 25 "
				+ SelfieProperties.VIDEO_PATH;
		Process process = Runtime.getRuntime().exec(commands);
		Thread.sleep(2000);
	}
}
