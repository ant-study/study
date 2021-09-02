package kr.co.ant.study.student.moonjonghun;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;

public class Request {

	private String url;
	
	private Map<String, Object> parameters = new HashMap<>();
	
	private HttpMethod httpMethod;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, ?> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}
	
	public void put(String key, Object value) {
		parameters.put(key, value);
	}
	
	
}
