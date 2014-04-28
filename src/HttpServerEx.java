import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * Created by Admin on 28.04.14.
 */
public class HttpServerEx implements HttpHandler {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 10);
        server.createContext("/", new HttpServerEx());
        server.start();
        System.out.println("Server started\nPress any key to stop...");
        System.in.read();
        server.stop(0);
        System.out.println("Server stoped");
    }

    @Override
    public void handle(HttpExchange exc) throws IOException {
        exc.sendResponseHeaders(200, 0);
        PrintWriter out = new PrintWriter(exc.getResponseBody());
        out.println( "Hello moto!" );
        out.close();
        exc.close();
    }

}
