package dao_mongo;

import model.Item;
import model.Customer;

import rx.Observable;

public interface ReactiveDao {
    Observable<Customer> getAllCustomers();

    Observable<Item> getAllItemsForCustomer(long customerId);

    Observable<String> addNewCustomer(Customer customer);

    Observable<String> addNewItem(Item item);
}
