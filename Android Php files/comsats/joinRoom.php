<?php
include 'db/db_connect.php';
//Check for mandatory parameters
 if($_SERVER['REQUEST_METHOD']=='POST'){
	 
	$date = date('Y-m-d H:i:s');
	$room_id = $_POST['roomid'];
	$user_id = $_POST['userid'];
	

	
	//Query to insert a movie
	$query = "INSERT INTO chat_roomuser(status,created_at,last_updated,room_id,user_id) 
				VALUES (1,'$date', '$date', '$room_id','$user_id')";
	if(mysqli_query($con,$query))
	{
		echo 'Room Joined Successfull';
	}
	else
	{
		echo 'Error while Joining Room';
	}
	}
	else{
		echo "Check Again";
		}
	 mysqli_close($con);
?>