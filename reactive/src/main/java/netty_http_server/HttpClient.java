package netty_http_server;

import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import rx.Observable;

public interface HttpClient {
    <T>Observable<String> getResponse(HttpServerRequest<T> req);
}
