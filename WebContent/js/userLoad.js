window.onload = ev => {
    $.ajax({
        type: 'get',
        url: 'user-load',
        success: function (res) {
            console.log(res);
        }
    })
}