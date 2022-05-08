<?php
	session_start();
	require_once('MysqliDb.php');
	$db = new Mysqlidb ();		
	if(!isset($_SESSION['myusername'])){
		header("location:index.php");
	}
	
	if(isset($_GET['id']) && $_GET['id'] != '' )
	{
		$id=$_GET['id'];
		$db->where ("id", $id);						
		$r = $db->get("members");
		$row = $r[0];		
	}	
	
	if(isset($_POST['user']) && $_POST['user'] != null )
	{
		//echo "<pre>";print_r($_POST);exit;
		global $db;
	
		if($_POST['pass'] != '')
		{
			$data = Array(
				'username' => $_POST['user'],	
				'password' => md5($_POST['pass']),
				'type' => $_POST['type']
			);
			$id = (int)$_POST['id'];			
			$db->where ("id", $id);
			$db->update ('members', $data);	
			$_SESSION['msg'] = "User Updated Successfully";						
		}
		else
		{
			$data = Array(
				'username' => $_POST['user'],					
				'type' => $_POST['type']
			);
			$id = (int)$_POST['id'];			
			$db->where ("id", $id);
			$db->update ('members', $data);			
			$_SESSION['msg'] = "User Updated Successfully";			
		}
		
	}
?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Edit User | Phtoframeeditor Admin Panel</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="custom.css">
<?php
	if($_SESSION['usertype'] != 1){ 
?>
<div class="head" align="center" style="background: red; margin: 0 auto;  padding: 5px;  width: 350px;">Only authenticated user can add content.</div>
<?php } ?>
<div class="container">
	<div class="leftpart">
		<a href="home.php">Home</a>
		<a href="viewuser.php">View User</a>
		<a href="adduser.php">Add User</a>
	
		<a href="addcategory.php">Add Category</a>
		<a href="addframe.php">Add Frames</a>
		<a href="logout.php">Logout</a>
	</div>
	<div class="rightpart">
		<table width="500" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">

			<tr>
			<form name="addframe" method="post" action="">
			<input  name="id" type="hidden" value="<?php echo $row['id']; ?>"  ></td>
			<td>
			<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#FFFFFF">
			<tr>
			<td colspan="3" align="center"><strong>Edit User</strong></td>
			</tr>
			<tr>
			<td>Username</td>
			<td width="6">:</td>
			<td><input size="50" name="user" type="text" id="user" value="<?php echo $row['username']; ?>" required></td>
			</tr>			
			<tr>
			<td>Password</td>
			<td width="6">:</td>
			<td><input size="50" name="pass" type="password" value="" id="pass" ></td>
			</tr>
			<tr>
			<td>User Type</td>
			<td width="6">:</td>
			<td>
			<select name="type" id="type">
				<option value="1" <?php if($row['type'] == 1){echo "selected";} ?>>Admin</option>
				<option value="2" <?php if($row['type'] == 2){echo "selected";} ?>>User</option>
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

