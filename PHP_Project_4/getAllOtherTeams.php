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
$username = filter_input(INPUT_POST, "username");
$userInfo = $connect->query("SELECT * FROM usernames WHERE username = '$username'");
$entry = mysqli_fetch_array($userInfo);
$userId = (int)$entry['id'];
$saveTeams = "";
$rows = mysqli_query($connect,"SELECT * FROM teams");
while($entry = mysqli_fetch_array($rows))
{
	if(!($entry["member1"] == $userId || $entry["member2"] == $userId || $entry["member3"] == $userId || $entry["member4"] == $userId || $entry["member5"] == $userId))
	{
		echo $entry["teamName"] . ".";
	}
}
$connect->close();
?>
