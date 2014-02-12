package com.tengen;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Hinting {

	public static void main(String[] args) throws UnknownHostException {
		
		MongoClient client=new MongoClient();
		DB db=client.getDB("test");
		
		BasicDBObject query=new BasicDBObject("a",40000);
		query.append("b", 40000);
		query.append("c", 40000);
		
		DBCollection c=db.getCollection("foo");
		
		//DBObject doc=c.find(query).hint("a_1_b_-1_c_1").explain();
		
		BasicDBObject myHint=new BasicDBObject("a",1).append("b", -1).append("c", 1);
		
		DBObject doc=c.find(query).hint(myHint).explain();
		
		
		for (String s : doc.keySet()) {
			
			System.out.printf("%25s:%s\n",s,doc.get(s));
			
		}
		
	}
}
/*                 cursor:BasicCursor
               isMultiKey:false
                        n:0
          nscannedObjects:0
                 nscanned:0
  nscannedObjectsAllPlans:0
         nscannedAllPlans:0
             scanAndOrder:false
                indexOnly:false
                  nYields:0
              nChunkSkips:0
                   millis:0
              indexBounds:{ }
                 allPlans:[ { "cursor" : "BasicCursor" , "n" : 0 , "nscannedObjects" : 0 , "nscanned" : 0 , "indexBounds" : { }}]
                   server:SavaniBharat:27017

 * */
 