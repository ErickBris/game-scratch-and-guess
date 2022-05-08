<?php
session_start();
require_once('MysqliDb.php');
$db = new Mysqlidb ();
// Define $myusername and $mypassword
$myusername=$_POST['myusername'];
$mypassword=$_POST['mypassword'];

// To protect MySQL injection (more detail about MySQL injection)
$myusername = stripslashes($myusername);
$mypassword = stripslashes($mypassword);
//$myusername = mysql_real_escape_string($myusername);
$mypassword = md5($mypassword);

$db->where("username = ? AND password = ?", Array($myusername,$mypassword));
$res = $db->get("members",1);

if(count($res)==1){
	$_SESSION['myusername'] = $res[0]['username'];
	$_SESSION['usertype'] = $res[0]['type'];	
	header("location:home.php");
}
else {
	$_SESSION['error'] = "Wrong Username or Password";
	header("location:index.php");
}
?>