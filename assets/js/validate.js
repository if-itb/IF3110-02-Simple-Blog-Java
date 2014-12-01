function postValidation() {
    var Judul = document.getElementById("Judul");
	var Tanggal = document.getElementById("Tanggal");
	var Konten = document.getElementById("Konten");
	
	if (Judul.value=="")
	{
		alert('Title has to be filled');
		return false;
	}
	
	if (Tanggal.value=="")
	{
		alert('Date has to be filled');
		return false;
	}
	
	if (Konten.value=="")
	{
		alert('Content has to be filled');
		return false;
	}
	
	if (Tanggal.value!="")
	{
		var arrTanggal = Tanggal.value.split("-");
		
		var tanggal = new Date();
		tanggal.setFullYear(arrTanggal[0], parseInt(arrTanggal[1])-1, arrTanggal[2]);
		var date = new Date();
		
		if (tanggal < date)
		{
			alert('Date has to be same as or more than today!');
			return false;
		}
	}
	
	return true;
}

function resetForm(FormId){
	document.forms[FormId].reset();
}

function makeYear(Tanggal){
	var tahun = Tanggal.charAt(0) + Tanggal.charAt(1) + Tanggal.charAt(2) + Tanggal.charAt(3);
	return parseInt(tahun);
}

function makeMonth(Tanggal){
	var bulan = Tanggal.charAt(5) + Tanggal.charAt(6);
	return parseInt(bulan);
}

function makeDay(Tanggal){
	var hari = Tanggal.charAt(8) + Tanggal.charAt(9);
	return parseInt(hari);
}
