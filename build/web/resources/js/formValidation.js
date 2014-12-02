function Validation()
{
	var tanggal = document.getElementById('Tanggal').value;
	document.getElementById('errormsg').innerHTML="";
	var tanggal1 = parseDate(tanggal);
	if(tanggal1 === null)
	{
		return false;
	}
	else
	{
		var tanggal2 = new Date();
		var result = tanggal1 > tanggal2;
		if(result)
		{
			var tanggal_reverse = tanggal.split("-").reverse().join("-");
			document.getElementById('Tanggal').value=tanggal_reverse;
			return true;
		}
		else
		{
			document.getElementById('errormsg').innerHTML="*Tanggal sudah lewat. Masukkan tanggal yang valid.";
			return false;
		}
	}
}

function parseDate(str) {
  var m = str.match(/^(\d{1,2})-(\d{1,2})-(\d{4})$/);
  var tanggal3;
  if(m)
  {
  	var tanggal3 = new Date(m[3],m[2]-1,m[1]-(-1));
  }
  else
  {
  	document.getElementById("errormsg").innerHTML="*format tanggal salah. (format:dd-mm-yyyy)";
  	var tanggal3 = null;
  }
  return tanggal3;
}