import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Server started on http://localhost:8080");
        System.out.println("Products API: http://localhost:8080/products");
        
        while (true) {
            Socket client = server.accept();
            handleRequest(client);
        }
    }
    
    static void handleRequest(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream());
        
        String line = in.readLine();
        if (line != null && line.contains("GET")) {
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: application/json");
            out.println("Access-Control-Allow-Origin: *");
            out.println();
            
            if (line.contains("/products")) {
                out.println("[");
                out.println("{\"id\":1,\"name\":\"Cotton T-Shirt\",\"description\":\"Comfortable cotton t-shirt\",\"price\":299.0,\"category\":\"Clothing\",\"imageurl\":\"https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=300\"},");
                out.println("{\"id\":2,\"name\":\"Blue Jeans\",\"description\":\"Classic blue denim jeans\",\"price\":899.0,\"category\":\"Clothing\",\"imageurl\":\"https://images.unsplash.com/photo-1542272604-787c3835535d?w=300\"},");
                out.println("{\"id\":3,\"name\":\"Gaming Laptop\",\"description\":\"High performance gaming laptop\",\"price\":45000.0,\"category\":\"Electronics\",\"imageurl\":\"https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=300\"},");
                out.println("{\"id\":4,\"name\":\"Smartphone\",\"description\":\"Latest Android smartphone\",\"price\":15000.0,\"category\":\"Electronics\",\"imageurl\":\"https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=300\"},");
                out.println("{\"id\":5,\"name\":\"Sneakers\",\"description\":\"Comfortable running shoes\",\"price\":1299.0,\"category\":\"Trending\",\"imageurl\":\"https://images.unsplash.com/photo-1549298916-b41d501d3772?w=300\"}");
                out.println("]");
            } else {
                out.println("{\"message\":\"Server is running! Try /products\"}");
            }
        }
        
        out.flush();
        client.close();
    }
}