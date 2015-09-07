package com.mooc.selfie.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import junit.framework.TestCase;

public class SelfieTestCases extends TestCase {

	private final String BASE_URL = "http://localhost:8080/selfie/";
	private final String FILE_PATH = "E:\\img\\";
	private final String VIDEO_PATH = "E:\\download\\";

	public void testJasonImageUpload() {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(BASE_URL + "image");

			File file = getSingleFile(FILE_PATH);

			JSONObject json = new JSONObject();
			json.put("name", file.getName());
			json.put("base64String", getJsonString(file));

			StringEntity params = new StringEntity(json.toString());
			httpPost.addHeader("content-type", "application/json");
			httpPost.addHeader("Accept", "application/json");
			httpPost.setEntity(params);
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

			assertEquals(200, httpResponse.getStatusLine().getStatusCode());
			httpClient.close();
		} catch (Exception e) {
			fail();
		}
	}

	public void testJasonMultipleImageUpload() {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(BASE_URL + "images");

			JSONArray jsonArray = getImagesInFolderAsJsonArray(FILE_PATH);

			StringEntity params = new StringEntity(jsonArray.toString());
			httpPost.addHeader("content-type", "application/json");
			httpPost.addHeader("Accept", "application/json");
			httpPost.setEntity(params);
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			assertEquals(200, httpResponse.getStatusLine().getStatusCode());
			httpClient.close();
		} catch (Exception e) {
			fail();
		}
	}

	public void testVideoResponse() {
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(BASE_URL + "video");

			CloseableHttpResponse response = httpclient.execute(httpget);
			assertEquals(200, response.getStatusLine().getStatusCode());

			InputStreamReader streamReader = new InputStreamReader(response.getEntity().getContent());
			BufferedReader bufferedReader = new BufferedReader(streamReader);

			StringBuffer buffer = new StringBuffer();
			String line = null;

			while ((line = bufferedReader.readLine()) != null) { 
				buffer.append(line);
				line = bufferedReader.readLine();
			}

			assertNotNull(buffer);

			JSONObject videoObject = new JSONObject(buffer.toString());

			byte[] binaryVideo = Base64.decodeBase64(videoObject.getString("base64String"));
			InputStream in = new ByteArrayInputStream(binaryVideo);

			OutputStream outputStream = new FileOutputStream(new File(VIDEO_PATH + videoObject.get("name")));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
		} catch (Exception e) {
			fail();
		}
	}

	private File getSingleFile(String rootDir) throws IOException {
		File rootFile = new File(rootDir);
		String[] list = (rootFile).list();
		File file = null;
		for (String filePath : list) {
			if (!filePath.toLowerCase().endsWith(".jpeg") && !filePath.toLowerCase().endsWith(".jpg")) {
				continue;
			}
			file = new File(rootFile.getAbsoluteFile() + File.separator + filePath);
			break;
		}
		return file;
	}

	private JSONArray getImagesInFolderAsJsonArray(String rootDir) throws IOException {
		File rootFile = new File(rootDir);
		String[] list = (rootFile).list();
		JSONArray jsonArray = new JSONArray();

		for (String filePath : list) {
			if (!filePath.toLowerCase().endsWith(".jpeg") && !filePath.toLowerCase().endsWith(".jpg")) {
				continue;
			}
			File file = new File(rootFile.getAbsoluteFile() + File.separator + filePath);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", file.getName());
			jsonObject.put("base64String", getJsonString(file));
			jsonArray.put(jsonObject);
		}
		return jsonArray;
	}

	private String getJsonString(File file) throws IOException {
		byte[] binaryImage = Files.readAllBytes(file.toPath());
		String imageString = Base64.encodeBase64URLSafeString(binaryImage);
		return imageString;
	}

}
