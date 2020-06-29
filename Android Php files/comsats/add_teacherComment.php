<?php
include 'db/db_connect.php';
//Check for mandatory parameters
 if($_SERVER['REQUEST_METHOD']=='POST'){
	$journalId = $_POST['journalId'];
	$body = $_POST['body'];
	$date = date('Y-m-d H:i:s');
	$user_id = $_POST['user_id'];
	

	
	//Query to insert a movie
	$query = "INSERT INTO journals_comments(journal_id,body,created_on,user_id) 
				VALUES ('$journalId','$body','$date','$user_id')";
	if(mysqli_query($con,$query))
	{
		echo 'Journal Comment Added Successfull';
	}
	else
	{
		echo 'Error while Adding Journal Comment ';
	}
	}
	else{
		echo "Check Again";
		}
	 mysqli_close($con);
?>