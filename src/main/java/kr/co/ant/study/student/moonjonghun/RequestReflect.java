package kr.co.ant.study.student.moonjonghun;

import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestReflect // extends HttpServlet
{

	public static void main(String[] args) {
		
		//콘솔 입력값을가져온다.
		Scanner sc = new Scanner(System.in);
		RequestReflect rr = new RequestReflect();
		
		//
		while(sc.hasNextLine()) {
			String url = sc.next();
			
			log.info("접근하려는 url : {}", url);
			
			String result = rr.reflectionHandler(url);
			
		}
	}
	
	public String reflectionHandler (String url) {
		// array[0] = method, array[1] = params
		String[] urlArray = url.split("\\?");
		
		String methodName = urlArray[0];
		String[] paramName = {};
		String[] paramValue = {};
		
		String[] params = urlArray[1].split("&");
		//파라미터 셋팅
		for(int i = 0 ; i < params.length; i++) {
			String[] val = params[i].split("=");
			paramName[i] = val[0];
			paramValue[i] = val[1];
		}
		
		
		//요청url에 따라 다른 Controller를 요청하는 방법
		
		
		return null;
	} 
}
