const BASE_URL="http://localhost:8080"

function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

async function loadProducts()
{
    console.log('Starting to load products from:', `${BASE_URL}/products`);
    try{
        const response= await fetch(`${BASE_URL}/products`);
        console.log('Response status:', response.status);
        if (!response.ok) {
            throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }
        const products= await response.json();
        console.log('Products received:', products);
        console.log('Number of products:', products.length);

        let trendingList= document.getElementById("trending-products");
        let clothingList= document.getElementById("clothing-products");
        let electronicsList= document.getElementById("electronics-products");

        trendingList.innerHTML="";
        clothingList.innerHTML="";
        electronicsList.innerHTML="";

        let trendingHtml = "";
        let clothingHtml = "";
        let electronicsHtml = "";
        
        products.forEach(product => {
            const productCard = `
                <div class="col-lg-4 col-md-6">
                    <div class="card h-100">
                        <img src="${escapeHtml(product.imageurl)}" class="card-img-top" alt="${escapeHtml(product.name)}">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title">${escapeHtml(product.name)}</h5>
                            <p class="card-text">${escapeHtml(product.description)}</p>
                            <p class="price"><strong>₹${escapeHtml(product.price.toString())}</strong></p>
                            <button class="btn btn-primary mt-auto"
                            onclick="addToCart(${product.id},'${escapeHtml(product.name)}',${product.price},'${escapeHtml(product.imageurl)}')">
                            Add to Cart
                            </button>
                        </div>
                    </div>
                </div>
            `;

            if(product.category === "Clothing") {
                clothingHtml += productCard;
            }
            else if(product.category === "Electronics") {
                electronicsHtml += productCard;
            }
            else {
                trendingHtml += productCard;
            }
        });
        
        trendingList.innerHTML = trendingHtml;
        clothingList.innerHTML = clothingHtml;
        electronicsList.innerHTML = electronicsHtml;
    }
    catch(error)
    {
        console.error("Error fetching Products:", error);
        console.error('Full error details:', error.message);
        
        // Show error message to user
        const trendingList = document.getElementById("trending-products");
        if (trendingList) {
            trendingList.innerHTML = '<div class="col-12"><div class="alert alert-danger">Failed to load products. Please check if the backend server is running on port 8080.</div></div>';
        }
    }
   
}