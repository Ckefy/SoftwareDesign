package dao_mongo;

import com.mongodb.client.model.Filters;
import com.mongodb.rx.client.MongoCollection;
import model.Currency;
import model.Customer;
import model.Item;
import org.bson.Document;
import rx.Observable;

public class ReactiveDaoImpl implements ReactiveDao {
    private final MongoCollection<Document> items;
    private final MongoCollection<Document> customers;

    public ReactiveDaoImpl(MongoCollection<Document> customers, MongoCollection<Document> items) {
        this.items = items;
        this.customers = customers;
    }

    @Override
    public Observable<Customer> getAllCustomers() {
        return customers.find().toObservable().map(Customer::new);
    }

    @Override
    public Observable<Item> getAllItemsForCustomer(long customerId) {
        return customers.find(Filters.eq("id", customerId))
                .toObservable()
                .map(doc -> Currency.valueOf(doc.getString("currency")))
                .flatMap(customerCurrency -> items.find()
                    .toObservable()
                    .map(doc -> new Item(doc).convert(customerCurrency))
                );

    }

    @Override
    public Observable<String> addNewCustomer(Customer customer) {
        return tryInsert(customer.extractDocument(), customers);
    }

    @Override
    public Observable<String> addNewItem(Item item) {
        return tryInsert(item.extractDocument(), items);
    }

    private Observable<String> tryInsert(Document document, MongoCollection<Document> collection) {
        return collection.find(Filters.eq("id", document.getLong("id")))
                .toObservable()
                .singleOrDefault(null)
                .flatMap(doc -> {
                    if (doc != null) {
                        return Observable.just("Can't insert this entity");
                    }

                    return collection.insertOne(document)
                            .asObservable()
                            .isEmpty()
                            .map(empty -> empty ? "Can't insert this entity" : "Added successfully");
                });
    }
}
