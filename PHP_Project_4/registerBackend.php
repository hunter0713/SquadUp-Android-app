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
$sql = "INSERT INTO usernames (username, password) VALUES ('$user', '$pass')";
if(!(mysqli_num_rows($userInfo) > 0)){


if($connect->query($sql) === true)
  {
    echo "success";
  }
}
else {
  echo "Could not insert ";
}

$connect->close();

 ?>
