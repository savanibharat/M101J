package com.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkStyle {
	
	public static void main(String[] args) {
		
		Spark.get(new Route("/") {
			
			@Override
			public Object handle(Request request, Response response) {//handle method will turn to response of get method
				return "Hello world Spark Style";
			}
		});
		
	}
}
