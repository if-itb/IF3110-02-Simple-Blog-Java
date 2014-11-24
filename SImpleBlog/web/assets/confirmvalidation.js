/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function confirmChoice() {
    var x;
    if (confirm("Do you really want to delete this post?") == true) {
        return true;
    } else {
        return false;
    }
}