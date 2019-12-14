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
$winner = filter_input(INPUT_POST,"winningTeam");
$loser = filter_input(INPUT_POST,"loserTeam");
$row = mysqli_query($connect,"SELECT * FROM teams WHERE teamName='$loser'");
$entry = mysqli_fetch_array($row);
$oldScore = $entry["Losses"];
if($entry["member1"] != 0 && $entry["member1"] != NULL){
	$member1 = $entry["member1"];
	$userInfo1 = mysqli_query($connect, "SELECT * FROM usernames WHERE id='$member1'");
	$user1Vector = mysqli_fetch_array($userInfo1);
	$user1Loss = $user1Vector["Losses"];
	$user1Loss = $user1Loss + 1;
	$updateUser1 = $connect->query("UPDATE usernames SET Losses='$user1Loss' WHERE id='$member1'");
}
if($entry["member2"] != 0 && $entry["member2"] != NULL){
	$member2 = $entry["member2"];
	$userInfo2 = mysqli_query($connect, "SELECT * FROM usernames WHERE id='$member2'");
	$user2Vector = mysqli_fetch_array($userInfo2);
	$user2Loss = $user2Vector["Losses"];
	$user2Loss = $user2Loss + 1;
	$updateUser2 = $connect->query("UPDATE usernames SET Losses='$user2Loss' WHERE id='$member2'");

}

if($entry["member3"] != 0 && $entry["member3"] != NULL){
	$member3 = $entry["member3"];
	$userInfo3 = mysqli_query($connect, "SELECT * FROM usernames WHERE id='$member3'");
	$user3Vector = mysqli_fetch_array($userInfo3);
	$user3Loss = $user3Vector["Losses"];
	$user3Loss = $user3Loss + 1;
	$updateUser3 = $connect->query("UPDATE usernames SET Losses='$user3Loss' WHERE id='$member3'");


}

if($entry["member4"] != 0 && $entry["member4"] != NULL){
	$member4 = $entry["member4"];
	$userInfo4 = mysqli_query($connect, "SELECT * FROM usernames WHERE id='$member4'");
	$user4Vector = mysqli_fetch_array($userInfo4);
	$user4Loss = $user4Vector["Losses"];
	$user4Loss = $user4Loss + 1;
	$updateUser4 = $connect->query("UPDATE usernames SET Losses='$user4Loss' WHERE id='$member4'");


}

if($entry["member5"] != 0 && $entry["member5"] != NULL){
	$member5 = $entry["member5"];
	$userInfo5 = mysqli_query($connect, "SELECT * FROM usernames WHERE id='$member5'");
	$user5Vector = mysqli_fetch_array($userInfo5);
	$user5Loss = $user5Vector["Losses"];
	$user5Loss = $user5Loss + 1;
	$updateUser5 = $connect->query("UPDATE usernames SET Losses='$user5Loss' WHERE id='$member5'");

	
}

$oldScore = $oldScore + 1;
$changeWins = mysqli_query($connect,"UPDATE teams SET Losses='$oldScore' WHERE teamName='$loser'");
$connect->close();

 ?>
