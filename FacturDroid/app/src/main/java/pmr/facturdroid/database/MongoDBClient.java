package pmr.facturdroid.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBClient {

    public MongoDBClient() {
        MongoClient cliente = MongoClients.create("mongodb+srv://Developer:3gughi9KTrPYd3uW@facturapp.exx6an8.mongodb.net/test");
    }




}
