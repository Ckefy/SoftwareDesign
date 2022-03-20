package netty_http_server;

import dao_mongo.ReactiveDao;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import model.Currency;
import model.Customer;
import model.Item;
import rx.Observable;

public class RxNettyHttpClient implements HttpClient {
    private final ReactiveDao database;

    public RxNettyHttpClient(ReactiveDao database) {
        this.database = database;
    }

    @Override
    public <T> Observable<String> getResponse(HttpServerRequest<T> req) {
        String name = req.getDecodedPath().substring(1);

        switch (name) {
            case "add_item":
                return this.addNewItem(req);
            case "add_customer":
                return this.addNewCustomer(req);
            case "get_customers":
                return this.getAllCustomers();
            case "get_items_for_customer":
                return this.getAllItemsForCustomer(req);
            default:
                return Observable.just("Default backend for method: " + name + "\n\n\nLIST OF ALLOWED METHODS:\n\n" + this.getMethods());
        }
    }

    private Observable<String> getAllCustomers() {
        return this.database.getAllCustomers().map(Object::toString);
    }

    private <T> Observable<String> getAllItemsForCustomer(HttpServerRequest<T> req) {
        long customerId = Long.parseLong(this.extractQuetyPatameter(req, "id"));

        return this.database.getAllItemsForCustomer(customerId).map(Object::toString);
    }

    private <T> Observable<String> addNewCustomer(HttpServerRequest<T> req) {
        long customerId = Long.parseLong(this.extractQuetyPatameter(req, "id"));
        Currency currency = Currency.valueOf(this.extractQuetyPatameter(req, "currency"));
        String login = this.extractQuetyPatameter(req, "login");

        Customer customer = new Customer(customerId, currency, login);

        return this.database.addNewCustomer(customer).map(Object::toString);
    }

    private <T> Observable<String> addNewItem(HttpServerRequest<T> req) {
        long itemId = Long.parseLong(this.extractQuetyPatameter(req, "id"));
        Currency currency = Currency.valueOf(this.extractQuetyPatameter(req, "currency"));
        String name = this.extractQuetyPatameter(req, "name");
        double price = Double.parseDouble(this.extractQuetyPatameter(req, "price"));

        Item item = new Item(itemId, currency, name, price);

        return this.database.addNewItem(item).map(Object::toString);
    }

    private <T> String extractQuetyPatameter(HttpServerRequest<T> req, String paramenerName) {
        return req.getQueryParameters().get(paramenerName).get(0);
    }

    private String getMethods() {
        return "Add new item: 'add_item' with query parameters 'id', 'currency', 'name' and 'price'\n" +
                "Usage example: /add_item?id=1&name=Something&currency=USD&price=100\n\n" +
                "Add new customer: 'add_customer' with query parameters 'id', 'currency' and 'login'\n" +
                "Usage example: /add_customer?id=1&login=OriginalLogin&currency=RUB\n\n" +
                "Show all customers: 'get_customers' withount query parameters\n" +
                "Usage example: /get_customers\n\n" +
                "Show all items with currency of certain customer: 'get_items_for_customer' with query parameter 'id'\n" +
                "Usage example: /get_items_for_customer?id=1\n\n";
    }
}
