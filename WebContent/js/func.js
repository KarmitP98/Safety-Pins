function addToCart(bid) {
    $.ajax({
        url: 'addToCart',
        method: 'post',
        data: {bid}
    });
}