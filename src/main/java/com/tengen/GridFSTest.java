package com.tengen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
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
