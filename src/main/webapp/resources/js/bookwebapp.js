/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//
$(document).ready(function () {
//$('#authorsTab').click(function () {
//        $('#authorsTab').on('show.bs.tab', function () {
//            window.location.href = "AuthorController?action=list";
//        });
    
//});
//$('#booksTab').click(function () {
//        $('#booksTab').on('show.bs.tab', function () {
//            window.location.href = "BookController?action=list";
//        });
    
//    
//    
    if (window.location.href.includes("AuthorController"))
    {
        $('#authorsTab').parent().css('class', 'active');
    }
    else if (window.location.href.includes("BookController"))
    {
        $('#booksTab').parent().css('class', 'active');
    }
    else{
        $('#homeTab').parent().css('class', 'active');
    }
});
