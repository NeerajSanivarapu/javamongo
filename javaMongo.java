package mongoJava;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class javaMongo{
	public static void main(String[] args) {
	// creating connection
		
		
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = mongoClient.getDatabase("javaMongo");
		MongoCollection<Document> collection = database.getCollection("employees");
		
		
		
		
		//inserting a single document
		Document document = new Document();
		document.put("name", "Neeraj");
		document.put("city", "plvd");
		document.put("age", 23);
		collection.insertOne(document);

		
	System.out.println("inserting is done"+  document);
		
		
		//insertMany
		
		
		
		Document document1 = new Document("name", "Ram").append("age", 26).append("city", "Hyderabad");
	      Document document2 = new Document("name", "Robert").append("age", 27).append("city", "Vishakhapatnam");
	      Document document3 = new Document("name", "Rhim").append("age", 30).append("city", "Delhi");
	      List<Document> list = new ArrayList<Document>();
	      list.add(document1);
	      list.add(document2);
	      list.add(document3);
	      collection.insertMany(list);
	      System.out.println("many documents Inserted"+" "+list);
	      
	      
	
		//read 
		
		
		Document searchQuery = new Document();
		searchQuery.put("name", "Neeraj");
		FindIterable<Document> cursor = collection.find(searchQuery);
		try (final MongoCursor<Document> cursorIterator = cursor.cursor()) {
		    while (cursorIterator.hasNext()) {
	        System.out.println(cursorIterator.next());
		        
		    }
		    System.out.println("Read is done");
	}
		
		
		//update
		
		
		Document query = new Document();
		query.put("name", "Neeraj");

		Document newDocument = new Document();
		newDocument.put("name", "Reddy");

		Document updateObject = new Document();
		updateObject.put("$set", newDocument);

		collection.updateOne(query, updateObject);
		
		System.out.println("update is done");
		
		
		
	// delete
		
		
		
	Document delete = new Document();
		delete.put("name", "Neeraj");

		collection.deleteOne(delete);
		System.out.println("delete is done");

	}
	}