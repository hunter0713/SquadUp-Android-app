<?php
session_start();
$servername = "mysql.eecs.ku.edu";
$username = "zack_khaz";
$password = "jo9aiyaa";
$dbName = "zack_khaz";

$connect = new mysqli($servername, $username, $password, $dbName);
if($connect->connect_error)
  {
    die("Connection failed: " . $connect->connect_error);
  }

$user = filter_input(INPUT_POST, "username");
$pass = filter_input(INPUT_POST, "password");
$userInfo = mysqli_query($connect,"SELECT * FROM usernames WHERE username = '$user'");
$row = mysqli_fetch_array($userInfo);
if($user == $row['username']){
  if($pass == $row['password']){
      echo "Success";
  }
else{
echo "failure";
}
}
else if($user != $row['username'] || $pass != $row['password']) {
  echo "failure";
}
$connect->close();
?>
