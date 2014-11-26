function modifyDateToLong() {
	//validasi dulu
	var input_date = document.getElementById('post:date_temp').value;
	var res = input_date.match(/^(\d{1,2})\/(\d{1,2})\/(\d{4})$/);
	var retval = false;
	if(res) {
		var dt = new Date(res[3],res[2]-1,res[1]);
		if ((dt.getMonth()+1!=res[2])||(dt.getDate()!=res[1])||(dt.getFullYear()!=res[3])) {
			alert('tanggal tidak valid');
			retval = false;
		} else {
			var today = new Date();
			today.setHours(0);
			today.setMinutes(0);
			today.setSeconds(0);
			today.setMilliseconds(0);
			if (dt < today) {
				alert('tanggal harus lebih besar atau sama dengan hari ini');
				retval = false;
			} else {
				retval = true;
//				retval = false;
				document.getElementById('post:date').value = dt.getTime();
			}
		}
	} else {
		alert('tanggal tidak valid');
		retval = false;
	}
	return retval;
}