<?php 
	$con = mysqli_connect('localhost', 'kevhnmay94', '', 'simpleblog');
	
	$judul = mysqli_real_escape_string($con, $_POST['Judul']);
	$tanggal = mysqli_real_escape_string($con, $_POST['Tanggal']);
	$konten = mysqli_real_escape_string($con, $_POST['Konten']);
	$update = FALSE;
	if(isset($_POST['id']))
	{
		$id = mysqli_real_escape_string($con, $_POST['id']);
		$update = TRUE;
	}
	
	//Check if Judul, Tanggal, and Konten are retrieved correctly
	echo "Judul: ",$judul, "<br>";
	echo "Tanggal : ",$tanggal, "<br>";
	echo "Konten: ",$konten, "<br>";
	
	// Check connection
	if (mysqli_connect_errno())
	{
		echo "Failed to connect to MySQL <br>";
	}
	else 
	{
		//Insert form values into database
		if(!$update)
		{
			$query = "INSERT INTO `posts` (`id`, `title`, `date`, `content`) 
					  VALUES (NULL, '$judul', '$tanggal', '$konten');";
		}
		else 
		{
			$query = "UPDATE `posts` 
			          SET `title`='$judul',`date`='$tanggal',`content`='$konten' 
				      WHERE id = $id";
		}
					
		if (!mysqli_query($con,$query)) 
		{
			die('Error: ' . mysqli_error($con));
		}
		
		echo "1 record added";

	}
	
	mysqli_close($con);
	
	//Redirect to index.php
	header('Location: index.php');
 ?>