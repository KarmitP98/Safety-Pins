/**
 * JavaScript to search items
 */

window.onload = function () {

    search();

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