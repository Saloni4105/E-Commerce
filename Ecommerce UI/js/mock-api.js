// Temporary mock data for testing
async function loadProducts() {
    console.log('Loading mock products...');
    
    const mockProducts = [
        // Clothing - 3 items
        {id: 1, name: "Cotton T-Shirt", description: "Comfortable cotton t-shirt", price: 299, category: "Clothing", imageurl: "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=300"},
        {id: 2, name: "Blue Jeans", description: "Classic blue denim jeans", price: 899, category: "Clothing", imageurl: "https://images.unsplash.com/photo-1542272604-787c3835535d?w=300"},
        {id: 3, name: "Hoodie", description: "Warm and cozy hoodie", price: 1299, category: "Clothing", imageurl: "https://images.unsplash.com/photo-1556821840-3a9c6dcb0e78?w=300"},
        
        // Electronics - 3 items
        {id: 4, name: "Gaming Laptop", description: "High performance gaming laptop", price: 45000, category: "Electronics", imageurl: "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=300"},
        {id: 5, name: "Smartphone", description: "Latest Android smartphone", price: 15000, category: "Electronics", imageurl: "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=300"},
        {id: 6, name: "Wireless Headphones", description: "Premium wireless headphones", price: 2999, category: "Electronics", imageurl: "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=300"},
        
        // Trending - 3 items
        {id: 7, name: "Sneakers", description: "Comfortable running shoes", price: 1299, category: "Trending", imageurl: "https://images.unsplash.com/photo-1549298916-b41d501d3772?w=300"},
        {id: 8, name: "Smart Watch", description: "Fitness tracking smartwatch", price: 3999, category: "Trending", imageurl: "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=300"},
        {id: 9, name: "Backpack", description: "Stylish travel backpack", price: 799, category: "Trending", imageurl: "https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=300"}
    ];
    
    displayProducts(mockProducts);
}

function displayProducts(products) {
    let trendingList = document.getElementById("trending-products");
    let clothingList = document.getElementById("clothing-products");
    let electronicsList = document.getElementById("electronics-products");
    
    let trendingHtml = "";
    let clothingHtml = "";
    let electronicsHtml = "";
    
    products.forEach(product => {
        const productCard = `
            <div class="col-lg-4 col-md-6">
                <div class="card h-100">
                    <img src="${product.imageurl}" class="card-img-top" alt="${product.name}">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text">${product.description}</p>
                        <p class="price"><strong>₹${product.price}</strong></p>
                        <button class="btn btn-primary mt-auto" onclick="addToCart(${product.id},'${product.name}',${product.price},'${product.imageurl}')">
                            Add to Cart
                        </button>
                    </div>
                </div>
            </div>
        `;
        
        if(product.category === "Clothing") {
            clothingHtml += productCard;
        } else if(product.category === "Electronics") {
            electronicsHtml += productCard;
        } else {
            trendingHtml += productCard;
        }
    });
    
    trendingList.innerHTML = trendingHtml;
    clothingList.innerHTML = clothingHtml;
    electronicsList.innerHTML = electronicsHtml;
}