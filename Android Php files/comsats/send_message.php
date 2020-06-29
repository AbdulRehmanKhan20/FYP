<?php
include 'db/db_connect.php';
//Check for mandatory parameters
 if($_SERVER['REQUEST_METHOD']=='POST'){
	 
	$message = $_POST['message'];
	$date = date('Y-m-d H:i:s');
	$room_id = $_POST['room_id'];
	$user_id = $_POST['user_id'];
	

	
	//Query to insert a movie
	$query = "INSERT INTO chat_chat(message,created_at,last_updated,room_id,user_id) 
				VALUES ('$message','$date', '$date', '$room_id','$user_id')";
	if(mysqli_query($con,$query))
	{
		echo 'Message Sent Successfull';
	}
	else
	{
		echo 'Error while Message Sent';
	}
	}
	else{
		echo "Check Again";
		}
	 mysqli_close($con);
?>