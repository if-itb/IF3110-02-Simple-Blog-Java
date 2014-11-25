/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateform() {
    var date =document.forms["Form"]["Tanggal"].value;
    var judul = document.forms["Form"]["Judul"].value;
    var konten = document.forms["Form"]["Konten"].value;
    if (date == null || date == "" || judul == null || judul == "" || konten == null || konten == "") {
        alert("All input must be filled out");
        return false;
    }
    else
    {
        var datereg = /^(\d{4})-(\d{1,2})-(\d{1,2})$/;
        if(date.match(datereg)){
            var z = new Date(date.substr(0,4),date.substr(5,2)-1,date.substr(8,2),23,59,59,0);
            var y = new Date();
            if(z<y){
                    alert("Date must be bigger than " + y);
                    return false;
            }
        }
        else
        {
            alert("Data must be in YYYY-MM-DD format");
            return false;
        }
        return true;
    }
}