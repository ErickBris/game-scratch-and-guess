<?php		
session_start();
require_once('MysqliDb.php');
error_reporting(E_ALL);
$data = array();
$db = new Mysqlidb ();

?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Scratch Admin Panel</title>
</head>
<body>
	<table width="300" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
	<tr>
		<form name="form1" method="post" action="checklogin.php">
		<td>
		<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#FFFFFF">
		<tr>
		<td colspan="3" align="center"><strong >Login </strong></td>
		</tr>
		<tr>
		<td width="78">Username</td>
		<td width="6">:</td>
		<td width="294"><input name="myusername" type="text" id="myusername" required></td>
		</tr>
		<tr>
		<td>Password</td>
		<td>:</td>
		<td><input name="mypassword" type="password" id="mypassword" required></td>
		</tr>
		<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><input type="submit" name="Submit" value="Login"></td>
		</tr>
		<tr>
		<td colspan="3"  align="center" style="color:red;"><?php if(isset($_SESSION['error'])) { echo $_SESSION['error'];}?>
		</td>
		</tr>
		</table>
		</td>
		</form>
	</tr>
	</table>
	</body>
</html>