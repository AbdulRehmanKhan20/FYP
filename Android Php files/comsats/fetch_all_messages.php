<?php
include 'db/db_connect.php';

	 $roomid = $_GET['roomId'];

//Query to select Hospital id and Hospital name
$query = "SELECT chat_chat.user_id,user_management_user.username,chat_chat.message FROM chat_chat,user_management_user where user_management_user.id = chat_chat.user_id and room_id ='$roomid' ORDER BY chat_chat.created_at";
$result = array();
$roomArray = array();
$room_name ="" ;
$response = array();
//Prepare the query
if($stmt = $con->prepare($query)){
	$stmt->execute();
	//Bind the fetched data to $name
	$stmt->bind_result($userId,$username,$message);
	//Fetch 1 row at a time					
	while($stmt->fetch()){
		
		//Populate the movie array
		$roomArray["userId"] = $userId;
		$roomArray["username"] = $username;
		$roomArray["message"] = $message;
		$result[]=$roomArray;
	}
	$stmt->close();
	$response["success"] = 1;
	$response["data"] = $result;
}else{
	//Some error while fetching data
	$response["success"] = 0;
	$response["message"] = mysqli_error($con);
}
//Display JSON response
echo json_encode($response);
 
?>