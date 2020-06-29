<?php 

 // if($_SERVER['REQUEST_METHOD']=='POST')
	 if(true)
 {

 include 'db/db_connect.php';
 
 // $email = $_POST['email'];
 // $password = $_POST['password'];
 $email = "Khan";
 $password = "abc";
 
 $Sql_Query = "select user_management_user.id,user_management_role.title,user_management_user.password from user_management_user,user_management_userrole,user_management_role where user_management_user.username='$email'  and user_management_user.id = user_management_userrole.user_id and user_management_userrole.role_id = user_management_role.id and user_management_user.is_active=1";
 $check = mysqli_fetch_array(mysqli_query($con,$Sql_Query));
 
 echo "aaaa ".print_r($check);
if($check)
{
  echo "Data Matched".'.'. $check[0].','.$check[1];
}
else
{
	echo "Invalid Username or Password Please Try Again";
}
 }else{
 echo "Check Again";
 }
mysqli_close($con);

?>?>