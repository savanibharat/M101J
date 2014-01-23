package com.tengen;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * The Class FindTest.
 */
public class FindTest {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws UnknownHostException the unknown host exception
	 */
	public static void main(String[] args) throws UnknownHostException {

		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		DBCollection collection = db.getCollection("findTest");

		collection.drop();

		for (int i = 0; i < 10; i++) {

			collection
					.insert(new BasicDBObject("x", new Random().nextInt(100)));
		}
		System.out.println("findOne()");
		DBObject doc = collection.findOne();
		System.out.println(doc);

		System.out.println();

		DBCursor cursor = collection.find();
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
		
		long count=collection.count();
		System.out.println("Total docs for "+collection.getName()+" collection are "+count);
		
	}
}
/*
findOne()
{ "_id" : { "$oid" : "52dfb8e25202056b948554bc"} , "x" : 57}

Printing for find()
{ "_id" : { "$oid" : "52dfb8e25202056b948554bc"} , "x" : 57}
{ "_id" : { "$oid" : "52dfb8e25202056b948554bd"} , "x" : 20}
{ "_id" : { "$oid" : "52dfb8e25202056b948554be"} , "x" : 92}
{ "_id" : { "$oid" : "52dfb8e25202056b948554bf"} , "x" : 2}
{ "_id" : { "$oid" : "52dfb8e25202056b948554c0"} , "x" : 1}
{ "_id" : { "$oid" : "52dfb8e25202056b948554c1"} , "x" : 66}
{ "_id" : { "$oid" : "52dfb8e25202056b948554c2"} , "x" : 10}
{ "_id" : { "$oid" : "52dfb8e25202056b948554c3"} , "x" : 31}
{ "_id" : { "$oid" : "52dfb8e25202056b948554c4"} , "x" : 34}
{ "_id" : { "$oid" : "52dfb8e25202056b948554c5"} , "x" : 71}

Total docs for findTest collection are 10

*/