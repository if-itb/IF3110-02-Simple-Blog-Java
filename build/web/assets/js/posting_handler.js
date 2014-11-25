function ConfirmDelete(nomor)
{
  var x = confirm("Apakah Anda yakin menghapus post ini?");
  if (x)
	  return window.location.assign('delete.php?id='+nomor);
  else
	return false;
}
function cekInputan(){
    var judul = document.getElementById('Judul').value;
    var tanggal = document.getElementById('Tanggal').value;
    var konten = document.getElementById('Konten').value;
    var dataString = 'judul='+ judul + '&tanggal=' + tanggal + '&konten=' + konten;
    
        if(judul=='' || tanggal =='' || konten =='')
        {
            alert('Silahkan lengkapi form yang tersedia');
			return false;
        }
        else
        {
            if(bandingkanTangal(tanggal)){

            }else{
                alert('Tanggal tidak benar. Silahkan masukkan tanggal lain.');
                return false;
			}
		}
}
function bandingkanTangal(input){
    var date1   =   new Date();
    var date2   =   new Date(input);

    if (date1.getTime() > date2.getTime()){
		alert(date1 + 'vs' + date2);
        return false;
    }else{
        return true;
    }
}