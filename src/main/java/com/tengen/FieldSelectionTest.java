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

public class FieldSelectionTest {

	public static void main(String[] args) throws UnknownHostException {

		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		DBCollection collection = db.getCollection("findCriteriaTest");

		collection.drop();

		Random rand=new Random();
		
		for (int i = 0; i < 10; i++) {

			collection.insert(new BasicDBObject("x", rand.nextInt(2))
					.append("y", rand.nextInt(100))
					.append("z", rand.nextInt(1000)));
		}

		// DBObject query=new BasicDBObject("x",0);

		DBObject query =QueryBuilder.start("x").is(0).and("y")
				.greaterThan(10).lessThan(90).get();

		long count = collection.count(query);
		System.out.println("Total docs for " + collection.getName()
				+ " collection are " + count);
		System.out.println();

		DBCursor cursor = collection.find(query,new BasicDBObject("z",true).append("_id", false));
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
/*
 *
Total docs for findCriteriaTest collection are 6

Printing for find()
{ "z" : 888}
{ "z" : 813}
{ "z" : 685}
{ "z" : 23}
{ "z" : 509}
{ "z" : 288}


 * 
 * 
 * */
 