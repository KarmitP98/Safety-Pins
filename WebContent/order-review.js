window.onload = ev => {

    //Get User Address
    getUserInfo();
    //Get Order Total
    getOrderTotal();
}

function getUserInfo() {
    $.ajax({
        type: "post",
        url: "user",
        dataType: "json",
        data: {address: true, paymentInfo: true},
        success: function (response) {
            console.log(response)
        }
    });
}

function getOrderTotal() {
    $.ajax({
        type: "post",
        url: "cart",
        success: function (content) {
            document.getElementById("order-items").innerHTML = content;
        }
    });
}