let cart= JSON.parse(localStorage.getItem("cart")) || [];
function loadCart()
{
    let cart= JSON.parse(localStorage.getItem("cart")) || [];
    let cartItems= document.getElementById("cart-items");
    let emptyCart = document.getElementById("empty-cart");
    let cartTable = document.getElementById("cart-table");
    
    if (!cartItems) {
        console.error("Cart items element not found");
        return;
    }
    
    if (cart.length === 0) {
        if (emptyCart) emptyCart.style.display = "block";
        if (cartTable) cartTable.style.display = "none";
        return;
    }
    
    if (emptyCart) emptyCart.style.display = "none";
    if (cartTable) cartTable.style.display = "table";
    
    let totalAmount=0;
    cartItems.innerHTML="";

    let htmlContent = "";
    cart.forEach((item,index) => {
        let itemTotal= item.price * item.quantity;
        totalAmount+=itemTotal;

        htmlContent +=`

        <tr>
            <td><img src="${item.imageurl}" width="50"></td>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td>
                <button class="btn btn-sm btn-secondary" onclick="changeQuantity(${index},-1)">-</button>
                ${item.quantity}
                <button class="btn btn-sm btn-secondary" onclick="changeQuantity(${index},1)">+</button>
            </td>

            <td>₹ ${itemTotal.toFixed(2)}</td>
            <td><button class="btn btn-danger btn-sm" onclick="removeItem(${index})" title="Remove item">×</button>
            </td>
        </tr>
        `;
    });
    cartItems.innerHTML = htmlContent;
    const totalElement = document.getElementById("total-amount");
    if (totalElement) {
        totalElement.innerText = totalAmount.toFixed(2);
    }
}

function addToCart(id,name,price,imageurl)
{
    console.log("Adding product to cart:", encodeURIComponent(id), encodeURIComponent(name), encodeURIComponent(price), encodeURIComponent(imageurl));

    price= parseFloat(price);
    let itemIndexCart= cart.findIndex((item) => item.id===id)
    if(itemIndexCart!==-1)
    {
        cart[itemIndexCart].quantity+=1;
    }
    else{
        cart.push({
            id:id,
            name:name,
            price:price,
            imageurl: imageurl,
            quantity:1
        });  
    }
    localStorage.setItem("cart", JSON.stringify(cart));
    updateCartCounter();
}

function updateCartCounter()
{
    const badge = document.querySelector(".cart-badge");
    if (badge) {
        const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
        badge.innerText = totalItems;
    }
}

function changeQuantity(index, change) {
    cart[index].quantity += change;
    if (cart[index].quantity <= 0) {
        cart.splice(index, 1);
    }
    localStorage.setItem("cart", JSON.stringify(cart));
    loadCart();
    updateCartCounter();
}

function removeItem(index) {
    cart.splice(index, 1);
    localStorage.setItem("cart", JSON.stringify(cart));
    loadCart();
    updateCartCounter();
}

function checkout() {
    if (cart.length === 0) {
        alert("Your cart is empty!");
        return;
    }
    alert("Proceeding to payment... (Demo)");
    // In real app, redirect to payment gateway
}

document.addEventListener("DOMContentLoaded", function() {
    loadCart();
    updateCartCounter();
});