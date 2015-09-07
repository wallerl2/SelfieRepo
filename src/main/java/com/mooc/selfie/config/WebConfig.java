package com.mooc.selfie.config;


import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mooc.selfie.handler.ImageHandler;
import com.mooc.selfie.handler.ImageHandlerImpl;
import com.mooc.selfie.handler.ImageSaveSupport;
import com.mooc.selfie.handler.SaveSupport;
import com.mooc.selfie.handler.SpringRequestHandler;
import com.mooc.selfie.handler.SpringRequestHandlerImpl;
import com.mooc.selfie.handler.VideoHandler;
import com.mooc.selfie.handler.VideoHandlerImpl;
import com.mooc.selfie.model.Image;
import com.mooc.selfie.model.Video;
import com.mooc.selfie.util.video.FFMpegConverter;
import com.mooc.selfie.util.video.JpegImagesToMovieConverter;



@Configuration
@EnableWebMvc
@ComponentScan("com.mooc.selfie")
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver  = new InternalResourceViewResolver();
		resolver.setPrefix("WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;		
	}	
	
	@Bean
	public MultipartResolver multipartResolver() throws IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setUploadTempDir(new FileSystemResource("/tmp/uploads"));
		return multipartResolver;
	}
	
	@Override 
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJacksonHttpMessageConverter());
       // addDefaultHttpMessageConverters(converters);
    }
	
	
	@Bean 
	public MappingJacksonHttpMessageConverter mappingJacksonHttpMessageConverter(){
		MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.canSerialize(Image.class);
		mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
		mapper.setVisibility(JsonMethod.GETTER, JsonAutoDetect.Visibility.NONE);		
		converter.setObjectMapper(mapper);
		return converter;		
	}
	
	/*@Bean
	public SaveSupport saveSupport(){
		return new ImageSaveSupport();
	}*/	
	
	@Bean
	public VideoHandler videoHandlerImpl(){
		return new VideoHandlerImpl();
	}
	
	@Bean
	public ImageHandler imageHandlerImpl(){
		return new ImageHandlerImpl();
	}
	
	@Bean
	public Video video(){
		return new Video();
	}
	
	/*@Bean
	public JpegImagesToMovieConverter jpegImagesToMovieConverter(){
		return new JpegImagesToMovieConverter();
	}*/
	
	@Bean
	public SpringRequestHandler springRequestHandlerImpl(){
		return new SpringRequestHandlerImpl();
	}
	
	@Bean
	public FFMpegConverter fFMpegConverter() {
		return new FFMpegConverter();
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
	}

}
