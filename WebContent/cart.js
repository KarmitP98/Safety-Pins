/**
 * Fetch all session storage data and output in cart
 */
 
 window.onload = function() {
    $.ajax({
            type: "get",
            url: "cart",
            success: function(content) {
                var result = content;
                console.log(result);
                var orderDetail = document.getElementById("order-details");
                 
                console.log(orderDetail);
                orderDetail.innerHTML = result;
            }
        });
}