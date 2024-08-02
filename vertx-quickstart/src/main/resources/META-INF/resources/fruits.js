function refresh() {
    $.get('/fruits', function (fruits) {
        var list = '';
        (fruits || []).forEach(function (fruit) {
            list = list
                + '<tr>'
                + '<td>' + fruit.id + '</td>'
                + '<td>' + fruit.name + '</td>'
                + '<td>' + fruit.color + '</td>'
                + '<td><a href="#" onclick="deleteFruit(' + fruit.id + ')">Delete</a></td>'
                + '</tr>'
        });
        if (list.length > 0) {
            list = ''
                + '<table><thead><th>Id</th><th>Name</th><th>Color</th><th></th></thead>'
                + list
                + '</table>';
        } else {
            list = "No fruits in database"
        }
        $('#all-fruits').html(list);
    });
}

function deleteFruit(id) {
    $.ajax('/fruits/' + id, {method: 'DELETE'}).then(refresh);
}


$(document).ready(function () {

    $('#create-fruit-button').click(function () {
        var fruitName = $('#fruit-name').val();
        var fruitColor = $('#fruit-color').val();
        $.post({
            url: '/fruits',
            contentType: 'application/json',
            data: JSON.stringify({name: fruitName,color: fruitColor})
        }).then(refresh)
    });

    refresh();
});
