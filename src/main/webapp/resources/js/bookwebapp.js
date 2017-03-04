/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('#deleteForm').submit(function() {
    var action = "AuthorController"
    $.ajax({
        url  : action,
        type : 'POST',
        data : $('#deleteForm, #listForm').serialize(),
    });
    return false;
});
$('#editForm').submit(function() {
    $.ajax({
        url  : action,
        type : 'POST',
        data : $('#editForm, #listForm').serialize(),
    });
    return false;
});
