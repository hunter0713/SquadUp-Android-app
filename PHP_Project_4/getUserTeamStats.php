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
$teamName = filter_input(INPUT_POST, "teamName");
$teamInfo = $connect->query("SELECT * FROM teams WHERE teamName = '$teamName'");
$entry = mysqli_fetch_array($teamInfo);
$saveData = "";
echo $entry['teamName'] . "." . $entry['teamID'] . "." . $entry['Wins'] . "." . $entry['Losses'];
$connect->close();
?>
