/**
 * JavaScript to search items
 */

window.onload = function () {

    console.log("Search for books")
    search();
    $.ajax({
        type: 'get',
        url: 'user-load',
        success: function (res) {
            console.log(res);
        }
    })
    document.getElementById('book').onkeypress = function (e) {
        if (e.code === "Enter") {
            searchByName();
        }
    };
};

function searchByName() {
    search();
}

function searchByCategory() {
    search();
}

function search() {
    let nameField = document.getElementById("book");
    let categoryField = document.getElementById("category");

    let name = "";
    let cat = "";


    name = nameField.value;
    cat = categoryField.value;
    $.ajax({
        type: "get",
        url: "book",
        data: {name: name, category: cat},
        success: function (content) {
            console.log(content)
            document.getElementById("catalogue").innerHTML = content;
        }
    });

    nameField.value = "";
}

function addItemToCart(bid) {
    $.ajax({
        type: 'get',
        url: 'add-item',
        data: {bid: bid}
    })
}