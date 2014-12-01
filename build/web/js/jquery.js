var main = function() {
    $('.btn').click(function() {
    var post = $('.status-box').val();
    $('<li>').text(post).prependTo('.posts');
    $('.status-box').val('');
    $('.btn').addClass('disabled'); 
});

  $('.btn').addClass('disabled');
}

$(document).ready(main);