function DeletePost()
{
	var conf = confirm("Apakah Anda yakin menghapus post ini?");
	if(conf)
	{
		//window.location.href= 'deletePost.php?id=' + id_post;
                return true;
	}
        else
                return false;
}

function ValidateFormNewPost()
{
	var judul = document.getElementById("form-newpost:judulpost").value;
	var tanggal = document.getElementById("form-newpost:tanggalpost").value;
	var konten = document.getElementById("form-newpost:kontenpost").value;
	if ((judul == "") || (tanggal == "") || (konten== ""))
	{
		alert("Field tidak boleh ada yang kosong");
		return false;
	}
	else if (!ValidateDate(tanggal))
	{
		alert("Tanggal yang diinput tidak valid");
		return false;
	}
	else
		return true;
}

function ValidateForm()
{
	var judul = document.getElementById("editpost:judulpost").value;
	var tanggal = document.getElementById("editpost:tanggalpost").value;
	var konten = document.getElementById("editpost:kontenpost").value;
	if ((judul == "") || (tanggal == "") || (konten== ""))
	{
		alert("Field tidak boleh ada yang kosong");
		return false;
	}
	else if (!ValidateDate(tanggal))
	{
		alert("Tanggal yang diinput tidak valid");
		return false;
	}
	else
		return true;
}

function ValidateDate(tanggal)
{
	var time_now = new Date();
	var date_now = parseInt(time_now.getDate());
	var month_now = parseInt(time_now.getMonth()) + 1;
	var year_now = parseInt(time_now.getFullYear());

	// Mengambil hari
	var date_regex = /[0-9]{1,2}$/g;
	var date = tanggal.match(date_regex);
	// Mengambil bulan
	var month_regex = /-[0-9]{1,2}-/g;
	var month2 = tanggal.match(month_regex);
	var get_moth = "" + month2;
	var month_regex2 = /[0-9]{1,2}/g;
	var month = get_moth.match(month_regex2);
	// Mengambil tahun
	var year_regex = /^[0-9]{1,4}/g;
	var year = tanggal.match(year_regex);

	// Mengecek kevalid-an tanggal
	if (IsDateValid(date,month,year))
	{
		// Mengecek jika tanggal yang diinput >= tanggal sekarang
		if (year > year_now)
			return true;
		else if (year == year_now)
		{
			if (month > month_now)
				return true;
			else if (month == month_now)
			{
				if (date >= date_now)
					return true;
				else // Tanggal lebih kecil dari tanggal sekarang
					return false;
			}
			else // Bulan lebih kecil dari bulan sekarang
				return false;
		}
		else // Tahun lebih kecil dari tahun sekarang
			return false;
	}
	else // Jika tanggal tidak valid
		return false;
}

function IsDateValid(date, month, year)
{
	if ((month > 0) && (month!=2) && (month<13))	// Jika bukan bulan Februari
	{
		if ((month == 1) || (month==3) || (month==5) || (month==7) || (month==8) || (month==10) || (month==12))
		{
			if ((date<=31) && (date>0))
				return true;
			else
				return false;
		}
		else
		{
			if ((date<=30) && (date>0))
				return true;
			else
				return false;
		}
	}
	else // Jika bulan Februari
	{
		if (IsKabisat(year))
		{
			if ((date>0) && (date<=29))
				return true;
			else
				return false;
		}
		else // Bukan tahun kabisat
		{
			if ((date>0) && (date<=28))
				return true;
			else
				return false;
		}
	}
}

function IsKabisat(year)
{
	return ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0));
}
