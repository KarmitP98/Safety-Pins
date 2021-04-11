/**
 * Fetch all session storage data and output in cart
 */

window.onload = function () {
    $.ajax({
        type: "get",
        url: "cart",
        success: function (content) {
            document.getElementById("order-items").innerHTML = content;
        }
    });
}