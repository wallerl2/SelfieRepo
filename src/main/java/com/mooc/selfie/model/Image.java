package com.mooc.selfie.model;

import java.io.IOException;

import com.mooc.selfie.factory.FactoryProducer;
import com.mooc.selfie.handler.SaveSupport;

public class Image implements SaveSupport {
	
	private String name;
	private String base64String;	
	private SaveSupport saveSupport;
	
	public Image(){}	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBase64String() {
		return base64String;
	}
	public void setBase64String(String base64String) {
		this.base64String = base64String;
	}
	
	public void save() throws IOException{
		save(this);
	}
	
	public void save(Image image) throws IOException{
		saveSupport = FactoryProducer.getSaveSupport();
		saveSupport.save(image);
	}

}
