package com.tengen;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

// TODO: Auto-generated Javadoc
/**
 * The Class InsertTest.
 */
public class InsertTest {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws UnknownHostException the unknown host exception
	 */
	public static void main(String[] args) throws UnknownHostException {

		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");

		DBCollection collection = courseDB.getCollection("insertTest");

		collection.drop();

		DBObject doc = new BasicDBObject("_id", new ObjectId())
				.append("xa", 10);
		DBObject doc2 = new BasicDBObject("_id", new ObjectId()).append("yb",
				12);

		collection.insert(Arrays.asList(doc, doc2));

		// System.out.println(doc);
		// collection.insert(Arrays.asList(doc,doc));MongoException$DuplicateKey

		// System.out.println(collection.insert(Arrays.asList(doc,doc2)));{
		// "serverUsed" : "/127.0.0.1:27017" , "n" : 0 , "connectionId" : 6 ,
		// "err" : null , "ok" : 1.0}

	}
}
