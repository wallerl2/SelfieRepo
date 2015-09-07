package com.mooc.selfie.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;

public class Base64Converter {
	
	public static String convertToBase64String(byte[] input){
		return Base64.encodeBase64URLSafeString(input);
	}
	
	public static InputStream decodeBase64String(String input){
		byte[] binaryImage = Base64.decodeBase64(input);
		return new ByteArrayInputStream(binaryImage);
	}

}
