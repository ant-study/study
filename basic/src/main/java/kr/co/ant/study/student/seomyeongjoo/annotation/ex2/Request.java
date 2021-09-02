package kr.co.ant.study.student.seomyeongjoo.annotation.ex2;

import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class Request {

	private String url;
	
	private Map<String, String> parameters = new HashMap<>();
	
	private HttpMethod httpMethod;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}
	
	public void put(String key, String value) {
		parameters.put(key, value);
	}
	
	
}
