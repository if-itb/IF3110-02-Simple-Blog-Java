/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function confirmPublish() {
    var x;
    if (confirm("Do you want publish this post?") == true) {
        return true;
    } else {
        return false;
    }
}

function confirmDelete() {
    var x;
    if (confirm("Do you really want to delete this post?") == true) {
        return true;
    } else {
        return false;
    }
}