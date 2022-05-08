<?php
session_start();
	require_once('MysqliDb.php');
	$db = new Mysqlidb ();		
	if(!isset($_SESSION['myusername'])){
		header("location:index.php");
	}
	
	
	if(isset($_POST['user']) && $_POST['user'] != null && $_POST['pass'] != null && $_SESSION['usertype'] == 1)
	{
		global $db;
		$data = Array(
			'username' => $_POST['user'],			
			'password' => md5($_POST['pass']),
			'type' => $_POST['type']
		);
		$id = $db->insert('members', $data);
		if($id != ''){
			$_SESSION['msg'] = "User Added Successfully";			
		}
		else {
			$_SESSION['msg'] = "User Not Added";
		}
		
	}
?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Add Users | Scratch Admin Panel</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="custom.css">
<?php
	if($_SESSION['usertype'] != 1){ 
?>
<div class="head" align="center" style="background: red; margin: 0 auto;  padding: 5px;  width: 350px;margin-bottom:10px;">Only authenticated user can add content.</div>
<?php } ?>
<div class="container">
	<div class="leftpart">
		<?php include 'menu.php'; ?>
	</div>
	<div class="rightpart">
		<table width="500" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">

			<tr>
			<form name="addframe" method="post" action="">
			<td>
			<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#FFFFFF">
			<tr>
			<td colspan="3" align="center"><strong>Add User</strong></td>
			</tr>
			<tr>
			<td>Username</td>
			<td width="6">:</td>
			<td><input size="50" name="user" type="text" id="user" required></td>
			</tr>			
			<tr>
			<td>Password</td>
			<td width="6">:</td>
			<td><input size="50" name="pass" type="password" id="pass" required></td>
			</tr>
			<tr>
			<td >User Type</td>
			<td width="6">:</td>
			<td>
			<select name="type" id="type">
				<option value="1">Admin</option>
				<option value="2">User</option>
			</select>
			</td>			
			</tr>

			<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<?php
				if($_SESSION['usertype'] == 1){ 
			?>
			<td><input type="submit" name="submit" value="Submit"></td>
			<?php } ?>
			</tr>
			<tr>
			<td colspan="3"  align="center" style="color:red;"><?php if(isset($_SESSION['msg'])) { echo $_SESSION['msg'];}?>
			</td>
			</tr>
			</table>
			</td>
			</form>
			</tr>

		</table>
	</div>
</div>
</body>
</html>

