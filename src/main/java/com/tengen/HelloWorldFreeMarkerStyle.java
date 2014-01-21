package com.tengen;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldFreeMarkerStyle {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldSparkStyle.class,
				"/");

		try {
			Template template = configuration
					.getTemplate("/resources/hello.ftl");
			StringWriter write = new StringWriter();

			Map<String, Object> helloMap = new HashMap<String, Object>();
			helloMap.put("name", "FreeMarker");/*
												 * <html> <body> <h1> FreeMarker </h1>
												 *  </body> </html>
												 */

			template.process(helloMap, write);

			System.out.println(write);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
