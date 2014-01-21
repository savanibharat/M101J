package com.tengen;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

// TODO: Auto-generated Javadoc
/**
 * The Class HelloWorldSparkFreeMarkerStyle.
 */
public class HelloWorldSparkFreeMarkerStyle {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(
				HelloWorldSparkFreeMarkerStyle.class, "/");

		Spark.get(new Route("/") {
			StringWriter write = new StringWriter();

			@Override//handle method creates a template for hello.ftl 
			public Object handle(Request request, Response response) {// handle method will turn to response of get method
																		
				try { // return "Hello world Spark Style";
					Template template = configuration
							.getTemplate("/resources/hello.ftl");

					Map<String, Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name", "FreeMarker");/*
														 *  * <html> <body> <h1>
														 * FreeMarker </h1>
														 * </body> </html>
														 */

					template.process(helloMap, write);
				} catch (Exception e) {

					halt(500);
					e.printStackTrace();
				}
				return write;
			}
		});
	}

}
