/**
 * JavaScript to search items
 */

window.onload = function() {
    document.getElementById('book').onkeypress = function(e) {
        if (e.code == "Enter") {
            searchByName();
        }
    };
};

function searchByName() {
    search();
};

function searchByCategory() {
    search();
};

function search() {
    var nameField = document.getElementById("book");
    var categoryField = document.getElementById("category");

    var name = "";
    var cat = "";

    name = nameField.value;
    cat = categoryField.value;

    console.log(cat + " : " + name);
    
        $.ajax({
            type: "get",
            url: "book",
            data: { name: name, category: cat },
            success: function(content) {
                var result = content;
                console.log(result);
                var catalogue = document.getElementById("catalogue");
                
                console.log(catalogue);
                catalogue.innerHTML = result;
            }
        });
        
        nameField.value = "";
}

