import dao_mongo.ReactiveMongoDriver;
import io.reactivex.netty.protocol.http.server.HttpServer;
import netty_http_server.HttpClient;
import netty_http_server.RxNettyHttpClient;
import rx.Observable;

public class Main {
    public static void main(String[] args) {
        HttpClient client = new RxNettyHttpClient(ReactiveMongoDriver.getDao());

        HttpServer
                .newServer(8080)
                .start((req, resp) -> {
                    Observable<String> response = client.getResponse(req);

                    return resp.writeString(response);
                })
                .awaitShutdown();
    }
}
