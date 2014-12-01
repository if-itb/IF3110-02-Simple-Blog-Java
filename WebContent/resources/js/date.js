function isValidDate(str) {
	// code 1 = not a date
	// code 2 = past date
	// code 3 = invalid date

	var date_patt = /^\d{4}-\d{2}-\d{2}$/;
	if (date_patt.test(str)) {
		var year = +str.substring(0, 4);
		var month = +str.substring(5, 7) - 1;
		var day = +str.substring(8, 10);

		var given = new Date(year, month, day);
		if (!(day === given.getDate() && month === given.getMonth() && year === given
				.getFullYear()))
			return 3;

		var now = new Date();

		if (year === now.getFullYear()) {
			if (month === now.getMonth()) {
				if (day < now.getDate()) {
					return 2;
				}
			} else if (month < now.getMonth()) {
				return 2;
			}
		} else if (year < now.getFullYear()) {
			return 2;
		}

		return 0;

	} else {
		return 1;
	}
}

function checkDate() {
	var date = document.getElementById("dateInput");
	var res = isValidDate(date.value);
	
	if (res == 0) {
		return true;
	} else {
		var error_area = document.getElementById("error_msg");
		error_area.innerHTML = "Error: " + res + " Format tanggal dalam bentuk yyyy-MM-dd dan lebih besar dari hari ini";
		return false;
	}
}