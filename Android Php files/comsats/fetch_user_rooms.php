<?php
if($_SERVER['REQUEST_METHOD']=='POST')
{

include 'db/db_connect.php';


 $roomid = $_POST['roomid'];
 $userid = $_POST['userid'];
 
  $CheckSQL = "SELECT * FROM chat_roomuser WHERE chat_roomuser.status=1  and chat_roomuser.user_id = '$userid' and chat_roomuser.room_id = '$roomid'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));
 
 if(isset($check)){
	 echo "true";
				}
	else{ 
	 echo "false";
	}
}
 
?>