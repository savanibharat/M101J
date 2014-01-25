package com.tengen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class GridFSTest {

	public static void main(String[] args) throws Exception {
		
		MongoClient client=new MongoClient();
		DB db=client.getDB("course");
		FileInputStream inputStream=null;
		
		GridFS videos=new GridFS(db,"video");//does not create new GridFS it just gives object to manipulate it
											 //returns bucket named video
		try{
			
			inputStream=new FileInputStream("BinarySearch.mp4");
			
			
		}
		catch(FileNotFoundException e){

			e.printStackTrace();
		}
		
		GridFSInputFile video=videos.createFile(inputStream,"BinarySearch.mp4");
		
		BasicDBObject meta=new BasicDBObject("description","Binary Search");
		
		ArrayList<String> tags=new ArrayList<String>();
		tags.add("search");
		tags.add("data structures");
		
		meta.append("tags", tags);
		
		video.setMetaData(meta);
		video.save();
		
		System.out.println(video.get("_id"));
		System.out.println("file saved");
		System.out.println("reading file from mongo");

		GridFSDBFile dbFile=videos.findOne(new BasicDBObject("filename","BinarySearch.mp4"));
		
		FileOutputStream outputStream=new FileOutputStream("BinarySearch_copy.mp4");
		
		dbFile.writeTo(outputStream);
		
		
	}
}
/*
 *> db.video.files.find().pretty()
{
        "_id" : ObjectId("52e390db5202b677a38b5cfe"),
        "chunkSize" : NumberLong(262144),
        "length" : NumberLong(31786618),
        "md5" : "e2ea3e60af248679f62e291f148b9ae8",
        "filename" : "BinarySearch.mp4",
        "contentType" : null,
        "uploadDate" : ISODate("2014-01-25T10:24:27.281Z"),
        "aliases" : null,
        "metadata" : {
                "description" : "Binary Search",
                "tags" : [
                        "search",
                        "data structures"
                ]
        }
}
--------------------------------------------------------------------
of you run same program twice files will be like this.
so be careful to use file names properly

> db.video.files.find().pretty()
{
        "_id" : ObjectId("52e390db5202b677a38b5cfe"),
        "chunkSize" : NumberLong(262144),
        "length" : NumberLong(31786618),
        "md5" : "e2ea3e60af248679f62e291f148b9ae8",
        "filename" : "BinarySearch.mp4",
        "contentType" : null,
        "uploadDate" : ISODate("2014-01-25T10:24:27.281Z"),
        "aliases" : null,
        "metadata" : {
                "description" : "Binary Search",
                "tags" : [
                        "search",
                        "data structures"
                ]
        }
}
{
        "_id" : ObjectId("52e391bc52023e879a4a105e"),
        "chunkSize" : NumberLong(262144),
        "length" : NumberLong(31786618),
        "md5" : "e2ea3e60af248679f62e291f148b9ae8",
        "filename" : "BinarySearch.mp4",
        "contentType" : null,
        "uploadDate" : ISODate("2014-01-25T10:28:12.406Z"),
        "aliases" : null,
        "metadata" : {
                "description" : "Binary Search",
                "tags" : [
                        "search",
                        "data structures"
                ]
        }
}
 * 
 * */
