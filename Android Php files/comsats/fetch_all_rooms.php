<?php
include 'db/db_connect.php';
//Query to select Hospital id and Hospital name
$query = "SELECT DISTINCT chat_room.id,chat_room.name, count(chat_roomuser.id) FROM chat_roomuser  RIGHT JOIN chat_room ON  chat_room.id = chat_roomuser.room_id GROUP BY chat_room.name";
$result = array();
$roomArray = array();
$room_name ="" ;
$response = array();
//Prepare the query
if($stmt = $con->prepare($query)){
	$stmt->execute();
	//Bind the fetched data to $name
	$stmt->bind_result($id,$name,$count);
	//Fetch 1 row at a time					
	while($stmt->fetch()){
		
		//Populate the movie array
		$roomArray["id"] = $id;
		$roomArray["name"] = $name;
		$roomArray["count"] = $count;
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