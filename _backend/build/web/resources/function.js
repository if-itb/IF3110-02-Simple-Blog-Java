/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function DeletePost(){
    var cc = confirm("Apakah anda yakin menghapus post ini?");
    if(cc){
        return true;
    } else{
        return false;
    }
}

