package dao_mongo;

import com.mongodb.rx.client.MongoClient;
import com.mongodb.rx.client.MongoClients;
import com.mongodb.rx.client.MongoCollection;
import com.mongodb.rx.client.MongoDatabase;
import org.bson.Document;

public class ReactiveMongoDriver {
    public static ReactiveDao getDao() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = client.getDatabase("online-shop");
        MongoCollection<Document> customers = db.getCollection("customers");
        MongoCollection<Document> items = db.getCollection("items");

        return new ReactiveDaoImpl(customers, items);
    }
}
