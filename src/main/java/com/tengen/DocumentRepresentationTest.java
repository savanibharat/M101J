package com.tengen;

import java.util.Arrays;

import com.mongodb.BasicDBObject;

public class DocumentRepresentationTest {

	public static void main(String[] args) {
		
		BasicDBObject doc=new BasicDBObject();
		doc.put("userName", "Bharat");//add single doc
		doc.put("languages", Arrays.asList("Java","C++","C"));//add array
		doc.put("address",new BasicDBObject("Street","101 San fernando").append("Apt", "#147"));//add sub doc
		
	}
	
}
