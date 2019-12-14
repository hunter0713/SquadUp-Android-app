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


$user = filter_input(INPUT_POST, "member1");
$teamName = filter_input(INPUT_POST,"teamName");
$userInfo = $connect->query("SELECT * FROM usernames WHERE username = '$user'");
$entry = mysqli_fetch_array($userInfo);
$userId = $entry['id'];
$row = mysqli_query($connect,"SELECT * FROM teams WHERE teamName='$teamName'");
$numOfRows = mysqli_num_rows($row);
$sql = "INSERT INTO teams (teamName, member1) VALUES ('$teamName', '$userId')";
if(!($numOfRows > 0)){
if($connect->query($sql) === true)
  {
    $sql = "SELECT teamID from teams WHERE teamName='$teamName'";
    $teamId = mysqli_query($connect,$sql);
    $teamId = mysqli_fetch_array($teamId);
    $teamId = $teamId['teamID'];
    echo $teamId;
  }
}
else {
  echo "Failed";
}
$connect->close();

 ?>
