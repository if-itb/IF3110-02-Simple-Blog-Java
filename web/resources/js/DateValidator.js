/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function DateValidator() {
    var date = new Date(document.getElementById("editPost:date").value);
    if (date.toString() === "Invalid Date") {
        alert("Tanggal yang dimasukkan tidak valid");
        date = new Date();
        document.getElementById("editPost:date").value = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
        return false;
    }
    else {
        if (date < new Date(new Date().toLocaleDateString())) {
            alert("Tanggal yang dimasukkan minimal hari ini");
            date = new Date();
            document.getElementById("editPost:date").value = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
            return false;
        }
    }
    document.getElementById("editPost:date").value = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
    return true;
}