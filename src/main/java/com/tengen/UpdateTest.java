package com.tengen;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateTest.
 */
public class UpdateTest {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws UnknownHostException the unknown host exception
	 */
	public static void main(String[] args) throws UnknownHostException {

		DBCollection collection = createCollection();
		//update method updates only one document that it marches first
		//whereas the remove method does damage to all documents whose search criteria is matched
		List<String> names = Arrays.asList("alice", "bobby", "cathy", "david","ethan");
		collection.drop();
		for (String name : names) {

			collection.insert(new BasicDBObject("_id", name));
		}
		System.out.println("Document replaced");
		collection.update(new BasicDBObject("_id", "alice"),
							new BasicDBObject("age", 24));//whole cell replacement
		printCollection(collection);
		System.out.println();
		System.out.println("new field added and doc not replaced $set");
		
		collection.update(new BasicDBObject("_id", "alice"),
						  new BasicDBObject("$set",new BasicDBObject("gender","F")));

		printCollection(collection);
		System.out.println();
		System.out.println("upsert ");
		collection.update(new BasicDBObject("_id","frank"),
						  new BasicDBObject("$set",new BasicDBObject("gender","M")),true,false);
		
		printCollection(collection);
		System.out.println();
		
		
		System.out.println("upsert:false   multi:true");
		collection.update(new BasicDBObject(),
						  new BasicDBObject("$set",
								  					new BasicDBObject("title","Dr.")),false,true);
		printCollection(collection);
		
		System.out.println();
		System.out.println("remove operation");
		//collection.remove(new BasicDBObject());//removes all documents one by one. Think and then proceed for this operation
		collection.remove(new BasicDBObject("_id","alice"));
		printCollection(collection);
		
	}
	

	/**
	 * Creates the collection.
	 *
	 * @return the dB collection
	 * @throws UnknownHostException the unknown host exception
	 */
	public static DBCollection createCollection() throws UnknownHostException {

		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		DBCollection lines = db.getCollection("UpdateTest");
		return lines;
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
/*Document replaced
{ "_id" : "bobby"}
{ "_id" : "cathy"}
{ "_id" : "david"}
{ "_id" : "ethan"}
{ "_id" : "alice" , "age" : 24}

new field added and doc not replaced $set
{ "_id" : "bobby"}
{ "_id" : "cathy"}
{ "_id" : "david"}
{ "_id" : "ethan"}
{ "_id" : "alice" , "age" : 24 , "gender" : "F"}

upsert 
{ "_id" : "bobby"}
{ "_id" : "cathy"}
{ "_id" : "david"}
{ "_id" : "ethan"}
{ "_id" : "alice" , "age" : 24 , "gender" : "F"}
{ "_id" : "frank" , "gender" : "M"}

upsert:false   multi:true
{ "_id" : "bobby" , "title" : "Dr."}
{ "_id" : "cathy" , "title" : "Dr."}
{ "_id" : "david" , "title" : "Dr."}
{ "_id" : "ethan" , "title" : "Dr."}
{ "_id" : "alice" , "age" : 24 , "gender" : "F" , "title" : "Dr."}
{ "_id" : "frank" , "gender" : "M" , "title" : "Dr."}

remove operation
{ "_id" : "bobby" , "title" : "Dr."}
{ "_id" : "cathy" , "title" : "Dr."}
{ "_id" : "david" , "title" : "Dr."}
{ "_id" : "ethan" , "title" : "Dr."}
{ "_id" : "frank" , "gender" : "M" , "title" : "Dr."}

 * */
 