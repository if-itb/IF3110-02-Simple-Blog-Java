/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function confirmPublish() {
    var x;
    if (confirm("Do you want publish this post?") === true) {
        return true;
    } else {
        return false;
    }
}

function confirmDelete() {
    var x;
    if (confirm("Do you really to delete this post?") === true) {
        return true;
    } else {
        return false;
    }
}

function confirmValidate() {
    var x;
    if (confirm("Are you sure?") === true) {
        return true;
    } else {
        return false;
    }

}