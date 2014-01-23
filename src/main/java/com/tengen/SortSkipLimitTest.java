package com.tengen;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class SortSkipLimitTest {

	public static void main(String[] args) throws UnknownHostException{

		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		DBCollection lines = db.getCollection("SortSkipLimitTest");

		lines.drop();
		
		Random rand=new Random();
		
		for (int i = 0; i < 10; i++) {
			lines.insert(new BasicDBObject("_id", i).append(
					"start",
					new BasicDBObject("x", rand.nextInt(2)).append("y",
							rand.nextInt(90) + 10))

			.append("end",
					new BasicDBObject("x", rand.nextInt(2)).append("y",
							rand.nextInt(90) + 10)

			));
		}

		//QueryBuilder builder = QueryBuilder.start("start.x").greaterThan(50);
		DBCursor cursor = lines.find()
				
				.sort(new BasicDBObject("start.x",1).append("start.y", -1))
													.skip(2).limit(10);
										//builder.get(),
										//new BasicDBObject("start.y", true).append("_id", false));
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
{ "_id" : 9 , "start" : { "x" : 0 , "y" : 80} , "end" : { "x" : 1 , "y" : 35}}
{ "_id" : 5 , "start" : { "x" : 0 , "y" : 71} , "end" : { "x" : 1 , "y" : 27}}
{ "_id" : 1 , "start" : { "x" : 0 , "y" : 64} , "end" : { "x" : 0 , "y" : 65}}
{ "_id" : 8 , "start" : { "x" : 0 , "y" : 35} , "end" : { "x" : 0 , "y" : 67}}
{ "_id" : 0 , "start" : { "x" : 0 , "y" : 25} , "end" : { "x" : 0 , "y" : 91}}
{ "_id" : 6 , "start" : { "x" : 0 , "y" : 16} , "end" : { "x" : 1 , "y" : 70}}
{ "_id" : 3 , "start" : { "x" : 1 , "y" : 91} , "end" : { "x" : 1 , "y" : 88}}
{ "_id" : 2 , "start" : { "x" : 1 , "y" : 31} , "end" : { "x" : 1 , "y" : 98}}

*/
