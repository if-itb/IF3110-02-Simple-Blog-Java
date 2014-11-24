/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
alert("PING");
function validateform() {
    var date = document.getElementById("Form:Tanggal");
    var datereg = /^(\d{4})-(\d{1,2})-(\d{1,2})$/;
    if(date.match(datereg)){
        alert("PING");
    }
    else
    {
        alert("PING2");
    }
    return false;
}
