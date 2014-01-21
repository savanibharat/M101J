package com.tengen;

import java.io.StringWriter;
import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldMongoDBSparkFreeMarkerStyle {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {

		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(
				HelloWorldSparkFreeMarkerStyle.class, "/");


		MongoClient client=new MongoClient(new ServerAddress("localhost",27017));

		DB database=client.getDB("course");
		final DBCollection collection=database.getCollection("hello");
		
		
		Spark.get(new Route("/") {
			StringWriter write = new StringWriter();

			@Override//handle method creates a template for hello.ftl 
			public Object handle(Request request, Response response) {// handle method will turn to response of get method
																		
				try { // return "Hello world Spark Style";
					Template template = configuration
							.getTemplate("/resources/hello.ftl");


					DBObject document=collection.findOne();//it returns  return findOne( new BasicDBObject() );
														   //BasicDBObject extends BasicBSONObject public class BasicDBObject extends BasicBSONObject implements DBObject
														   //public class BasicBSONObject extends LinkedHashMap<String,Object>
														   //public class LinkedHashMap<K,V>    extends HashMap<K,V>   implements Map<K,V>
					
					
					template.process(document, write);//Template is expecting a map 
				} catch (Exception e) {

					halt(500);
					e.printStackTrace();
				}
				return write;
			}
		});
	}


}
