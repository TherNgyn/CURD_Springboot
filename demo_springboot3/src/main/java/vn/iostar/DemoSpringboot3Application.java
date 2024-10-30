package vn.iostar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import vn.iostar.config.MySiteMeshFilter;

@SpringBootApplication
public class DemoSpringboot3Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringboot3Application.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<MySiteMeshFilter> siteMeshFilter(){
		FilterRegistrationBean<MySiteMeshFilter> filterRegistrationBean
		= new FilterRegistrationBean<MySiteMeshFilter>();
		filterRegistrationBean.setFilter(new MySiteMeshFilter());
		filterRegistrationBean.addUrlPatterns("/*"); // nap vao tat ca
		return filterRegistrationBean; 
	}
}
