package com.mooc.selfie.handler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import com.mooc.selfie.model.Image;
import com.mooc.selfie.properties.SelfieProperties;
import com.mooc.selfie.util.Base64Converter;

@Component
public class ImageSaveSupport implements SaveSupport{
	
	private Image image;
	
	public void save(Image image) throws IOException {
		this.image = image;
		ImageThread thread = new ImageThread();
		Thread td = new Thread(thread);
		td.start();		
	}
	
	/*public void save(Image image) throws IOException {
		InputStream in = convertImage(image.getBase64String());
		save(in, image.getName());		
	}*/
	
		
	private InputStream convertImage(String image){
		return Base64Converter.decodeBase64String(image) ;
	}
	
	private void save(InputStream imageStream, String name) throws IOException{
		BufferedImage buffImage = ImageIO.read(imageStream);
		ImageIO.write(buffImage, SelfieProperties.IMAGE_FORMAT, new File(SelfieProperties.FILE_PATH + name));
	}
	
	
	class ImageThread implements Runnable{
		
		public void run(){
			System.out.println("start");
			InputStream in = convertImage(image.getBase64String());
			try{
			System.out.println("middle");
			save(in, image.getName());	
			}catch(Exception e){}
		}
		
		private InputStream convertImage(String image){
			return Base64Converter.decodeBase64String(image) ;
		}
		
		private void save(InputStream imageStream, String name) throws IOException{
			BufferedImage buffImage = ImageIO.read(imageStream);
			ImageIO.write(buffImage, SelfieProperties.IMAGE_FORMAT, new File(SelfieProperties.FILE_PATH + name));
			System.out.println("end");
		}
	}
	
}
