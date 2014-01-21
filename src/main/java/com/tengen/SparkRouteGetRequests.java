package com.tengen;

import spark.Response;
import spark.Route;
import spark.Spark;
/*
 * What is this program about??
 * Spark has embedded jetty server. So when you start route, spark starts backgroud thread and runs jetty server.
 * Localhost(in our case) sends spark request to jetty and jetty forwards that request to spark handler
 * Spark handler does the lookup for methods GET  GET/test
 * */
// TODO: Auto-generated Javadoc
/**
 * The Class SparkRoute.
 */
public class SparkRouteGetRequests {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		Spark.get(new Route("/") {

			@Override
			public Object handle(spark.Request request, Response response) {

				return "This is a first page";
			}
		});
		Spark.get(new Route("/test") {

			@Override
			public Object handle(spark.Request request, Response response) {

				return "This is test page";
			}
		});
		Spark.get(new Route("/echo/:thing") {

			@Override
			public Object handle(spark.Request request, Response response) {

				return request.params(":thing");
			}
		});

	}
}
