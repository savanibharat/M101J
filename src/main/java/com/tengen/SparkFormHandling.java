package com.tengen;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class SparkFormHandling {

	public static void main(String[] args) {
		// configure free marker
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

		// configure routes
		Spark.get(new Route("/") {

			@Override
			public Object handle(final Request request, final Response response) {
				try {

					Map<String, Object> fruitsMap = new HashMap<String, Object>();
					fruitsMap.put("fruits",
							Arrays.asList("apple", "banana", "orange", "peach"));

					Template fruitPicker = configuration
							.getTemplate("/resources/fruit.ftl");
					StringWriter writer = new StringWriter();
					fruitPicker.process(fruitsMap, writer);
					return writer;

				} catch (Exception e) {

					halt(500);
					e.printStackTrace();
					return null;
				}

			}
		});
		
		Spark.post(new Route("/favorite_fruit") {
			
			@Override
			public Object handle(final Request request, final Response response) {

				final String fruit=request.queryParams("fruit");
				if(fruit==null){
					
					return "Select one fruit";
				}
				else{
					
					return "Your favorite fruit is "+fruit;
					
				}
			}
		});

	}
}
