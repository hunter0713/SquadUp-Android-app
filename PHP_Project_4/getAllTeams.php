<?php
$servername = "mysql.eecs.ku.edu";
$username = "zack_khaz";
$password = "jo9aiyaa";
$dbName = "zack_khaz";

$connect = new mysqli($servername, $username, $password, $dbName);
$rows = mysqli_query($connect,"SELECT * FROM teams");
while($entry = mysqli_fetch_array($rows))
{
echo $entry["teamName"] . "." . $entry["Wins"] . "." . $entry["Losses"] . "|";
}
$connect->close();
?>
