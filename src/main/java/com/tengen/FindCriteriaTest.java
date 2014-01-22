package com.tengen;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

/**
 * The Class FindTest.
 */
public class FindCriteriaTest {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws UnknownHostException
	 *             the unknown host exception
	 */
	public static void main(String[] args) throws UnknownHostException {

		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		DBCollection collection = db.getCollection("findCriteriaTest");

		collection.drop();

		for (int i = 0; i < 10; i++) {

			collection.insert(new BasicDBObject("x", new Random().nextInt(2))
					.append("y", new Random().nextInt(100)));
		}

		// DBObject query=new BasicDBObject("x",0);

		DBObject query = new BasicDBObject("x", 0)
												.append("y",new BasicDBObject("$gt", 10).append("$lt", 90));//This is bit tedious way to query
		
		//Another way to run a query is Using QueryBuilder

		QueryBuilder queryBuilder=QueryBuilder.start("x").is(0).and("y").greaterThan(10).lessThan(90);
		
		long count = collection.count(queryBuilder.get());
		System.out.println("Total docs for " + collection.getName()
				+ " collection are " + count);
		System.out.println();

		DBCursor cursor = collection.find(queryBuilder.get());
		System.out.println("Printing for find()");// when using find() remember
													// that you need to close
													// the cursor. because
													// cursor would be open on
													// server
													// if we were traversing for
													// 100 or 1000's of
													// documents. so put it in
													// try finally block
		try {

			while (cursor.hasNext()) {

				DBObject cur = cursor.next();
				System.out.println(cur);
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			cursor.close();
		}
		System.out.println();

	}
}