<?php

function formatDateDiff($start, $end=null) { 
    if(!($start instanceof DateTime)) { 
        $start = new DateTime($start); 
    } 
    
    if($end === null) { 
        $end = new DateTime(); 
    } 
    
    if(!($end instanceof DateTime)) { 
        $end = new DateTime($start); 
    } 
    
    $interval = $end->diff($start); 
    
    $format = array(); 
    if($interval->y !== 0) { 
        $format[] = "%y TAHUN"; 
    } 
    if($interval->m !== 0) { 
        $format[] = "%m BULAN"; 
    } 
    if($interval->d !== 0) { 
        $format[] = "%d HARI"; 
    } 
    if($interval->h !== 0) { 
        $format[] = "%h JAM"; 
    } 
    if($interval->i !== 0) { 
        $format[] = "%i MENIT"; 
    } 
    if($interval->s !== 0) { 
        if(!count($format)) { 
            return "BARU SAJA"; 
        } else { 
            $format[] = "%s DETIK"; 
        } 
    } 
    
    if(count($format) > 1) { 
        $format = array_shift($format)." dan ".array_shift($format); 
    } else { 
        $format = array_pop($format); 
    } 
    
    return $interval->format($format." YANG LALU"); 
} 
	//creating connection to database
	$link = mysqli_connect('localhost', 'kevhnmay94', "", 'simpleblog');
	$nama = mysqli_escape_string($link, $_POST['nama']);
	$email = mysqli_escape_string($link, $_POST['email']);
	$id = mysqli_escape_string($link, $_POST['pid']);
	$komentar = mysqli_escape_string($link, $_POST['komentar']);
	date_default_timezone_set('Asia/Jakarta'); 
	$tanggal = date("Y-m-d H:i:s");
	
	$query = "INSERT INTO `comments`(`pid`, `name`, `date`, `comment`, `email`) 
	VALUES ($id,'$nama','$tanggal','$komentar','$email')";
	
	//insert these data to database
	if(!mysqli_query($link, $query))
	{
		die('Error ' + mysqli_errno($link));
	}
	
	//tampilkan data ke screen
	$query = "SELECT * FROM `comments` WHERE pid=$id ORDER BY date DESC";
	if(!($results = mysqli_query($link, $query)))
	{
		die('Error ' + mysqli_errno($link));
	}
	
	foreach($results as $result)
	{
 ?>
	<li class="art-list-item">
		<div class="art-list-item-title-and-time">
			<h2 class="art-list-title"><?php echo $result['name']; ?></h2>
			<?php
				//formatting tanggal
				$date = date_create($result['date']);
				
			 ?>
			<div class="art-list-time"><?php 
			echo formatDateDiff($date);?></div>
		</div>
		<p><?php echo nl2br($result['comment']); ?></p>
	</li>
<?php 
	} 
	mysqli_close($link);
?>