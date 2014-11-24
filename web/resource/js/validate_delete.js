function validatedelete(teks)
{
	var x;
	if (confirm("Apakah Anda yakin hapus user ini?")==true)
		{
			x=window.location.href="DeleteUser?param="+teks;
		}else{
			x="cancel"; 
		}
}