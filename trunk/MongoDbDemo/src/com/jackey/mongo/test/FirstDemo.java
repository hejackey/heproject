package com.jackey.mongo.test;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class FirstDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*Mongo m = new Mongo();
		Mongo m = new Mongo( "localhost" );*/
		Mongo m;
		try {
			m = new Mongo( "localhost" , 27017 );
			DB db = m.getDB( "mydb" );
			
			for (String s : m.getDatabaseNames()) {
	            System.out.println("database is == "+s);
	        }

			
			DBCollection con = db.getCollection("ticket");
			
			/*BasicDBObject doc = new BasicDBObject();
	        doc.put("name", "MongoDB");
	        doc.put("type", "database");
	        doc.put("count", 1);
	        BasicDBObject info = new BasicDBObject();
	        info.put("x", 203);
	        info.put("y", 102);
	        doc.put("info", info);
	        con.insert(doc);*/
			BasicDBObject doc = new BasicDBObject();
			doc.put("name", "MongoDB");
			System.out.println(con.findOne(doc));

			con.createIndex(new BasicDBObject("name",-1));
			
			List<DBObject> list = con.getIndexInfo();
			for(DBObject obj : list){
				System.out.println(obj);
				
			}
			System.out.println(con.getCount());
			DBCursor cursor = con.find();
			while(cursor.hasNext())
				System.out.println(cursor.next());
			
			Set<String> colls = db.getCollectionNames();

			for (String s : colls) {
			    System.out.println(s);
			}
			

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}

		

	}

}
