package com.tengen;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class FindAndModify {

	public static void main(String[] args) throws UnknownHostException {
		
		DBCollection collection = createCollection();
		collection.drop();
		
		final String counterId="abc";
		int first;
		int numNeeded;
		
		numNeeded=2;
		first=getRange(counterId, numNeeded, collection);
		System.out.println("Range: "+first+"-"+(first+numNeeded-1));
		
		numNeeded=3;
		first=getRange(counterId, numNeeded, collection);
		System.out.println("Range: "+first+"-"+(first+numNeeded-1));
		
		numNeeded=10;
		first=getRange(counterId, numNeeded, collection);
		System.out.println("Range: "+first+"-"+(first+numNeeded-1));
		
		System.out.println();
		printCollection(collection);
	}

	public static DBCollection createCollection() throws UnknownHostException {

		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		DBCollection lines = db.getCollection("FindAndModify");
		return lines;
	}
	
	private static int getRange(String id, int range, DBCollection collection){
		
		DBObject doc=collection.findAndModify(
				new BasicDBObject("_id",id),null,null,false,
				new BasicDBObject("$inc",new BasicDBObject("counter",range)),
				true,true);
		
		return (Integer)doc.get("counter")-range+1;
	}
	
	/**
	 * Prints the collection.
	 *
	 * @param collection the collection
	 */
	public static void printCollection(DBCollection collection) {

		DBCursor cursor = collection.find();

		try {
			while (cursor.hasNext()) {

				System.out.println(cursor.next());

			}
		} finally {

			cursor.close();

		}
	}
	
}
/*
 *Range: 1-2
Range: 3-5
Range: 6-15

{ "_id" : "abc" , "counter" : 15}
 
 * 
 * */
 