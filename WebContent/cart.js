/**
 * Fetch all session storage data and output in cart
 */

window.onload = function () {
    getCartItems();
    getTotalPrice();
}

function getCartItems() {
    $.ajax({
        type: "get",
        url: "cart",
        success: function (content) {
            document.getElementById("order-items").innerHTML = content;
        }
    });
}

function getTotalPrice() {
    $.ajax({
        type: 'get',
        url: 'order-price',
        success: function (content) {
            console.log(content);
            document.getElementById('order-title').innerHTML = content;
        }
    });
}

async function updateQuantity(bid, number) {
    console.table(number, bid);
    await $.ajax({
        type: "get",
        url: "update-qty",
        data: {bid, number}
    });
    setTimeout(() => {
        getCartItems();
        getTotalPrice();
    });
}