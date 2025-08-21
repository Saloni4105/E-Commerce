import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class BackendServer {
    private static final int PORT = 8080;
    
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("✅ Backend Server Started Successfully!");
            System.out.println("🌐 Server URL: http://localhost:" + PORT);
            System.out.println("📦 Products API: http://localhost:" + PORT + "/products");
            System.out.println("🛑 Press Ctrl+C to stop server\n");
            
            ExecutorService executor = Executors.newFixedThreadPool(10);
            
            while (true) {
                Socket client = server.accept();
                executor.submit(() -> handleRequest(client));
            }
        } catch (IOException e) {
            System.err.println("❌ Server Error: " + e.getMessage());
        }
    }
    
    private static void handleRequest(Socket client) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            
            String requestLine = in.readLine();
            if (requestLine == null) return;
            
            System.out.println("📥 Request: " + requestLine);
            
            // CORS Headers
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: application/json");
            out.println("Access-Control-Allow-Origin: *");
            out.println("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
            out.println("Access-Control-Allow-Headers: *");
            out.println();
            
            if (requestLine.contains("GET /products")) {
                sendProductsResponse(out);
            } else if (requestLine.contains("GET /")) {
                sendStatusResponse(out);
            } else {
                sendNotFoundResponse(out);
            }
            
        } catch (IOException e) {
            System.err.println("Request handling error: " + e.getMessage());
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                // Ignore
            }
        }
    }
    
    private static void sendProductsResponse(PrintWriter out) {
        String products = """
        [
          {
            "id": 1,
            "name": "Cotton T-Shirt",
            "description": "Comfortable cotton t-shirt perfect for daily wear",
            "price": 299.0,
            "category": "Clothing",
            "imageurl": "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=300&h=300&fit=crop"
          },
          {
            "id": 2,
            "name": "Blue Jeans",
            "description": "Classic blue denim jeans with perfect fit",
            "price": 899.0,
            "category": "Clothing",
            "imageurl": "https://images.unsplash.com/photo-1542272604-787c3835535d?w=300&h=300&fit=crop"
          },
          {
            "id": 3,
            "name": "Gaming Laptop",
            "description": "High performance gaming laptop with latest specs",
            "price": 45000.0,
            "category": "Electronics",
            "imageurl": "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=300&h=300&fit=crop"
          },
          {
            "id": 4,
            "name": "Smartphone",
            "description": "Latest Android smartphone with amazing camera",
            "price": 15000.0,
            "category": "Electronics",
            "imageurl": "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=300&h=300&fit=crop"
          },
          {
            "id": 5,
            "name": "Running Sneakers",
            "description": "Comfortable running shoes for all terrains",
            "price": 1299.0,
            "category": "Trending",
            "imageurl": "https://images.unsplash.com/photo-1549298916-b41d501d3772?w=300&h=300&fit=crop"
          },
          {
            "id": 6,
            "name": "Wireless Headphones",
            "description": "Premium wireless headphones with noise cancellation",
            "price": 2999.0,
            "category": "Electronics",
            "imageurl": "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=300&h=300&fit=crop"
          }
        ]
        """;
        out.println(products);
        System.out.println("✅ Sent products data");
    }
    
    private static void sendStatusResponse(PrintWriter out) {
        out.println("{\"status\":\"Server is running\",\"message\":\"Backend API is working!\"}");
    }
    
    private static void sendNotFoundResponse(PrintWriter out) {
        out.println("{\"error\":\"Endpoint not found\"}");
    }
}