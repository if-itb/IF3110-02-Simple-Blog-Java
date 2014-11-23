/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//<![CDATA[
function validateform() {

    var x = document.forms["myForm"]["Tanggal"].value;
    var y = document.forms["myForm"]["Judul"].value;
    var z = document.forms["myForm"]["Konten"].value;
    if (x == null || x == "" || y == null || y == "" || z == null || z == "") {
        alert("All input must be filled out");
        return false;
    }
    else
    {
	re = /^(\d{4})-(\d{1,2})-(\d{1,2})$/
	if(x != '' && !x.match(re)) {
	alert("Invalid date format: " + x +"\nDate must be in YYYY-MM-DD format");
	return false;
	}
	else{
		var z = new Date(x.substr(0,4),x.substr(5,2)-1,x.substr(8,2),23,59,59,0);
		var y = new Date();
		if(z<y){
			alert("Date must be bigger than " + y);
			return false;
		}
	}
    }

}
//]]>
