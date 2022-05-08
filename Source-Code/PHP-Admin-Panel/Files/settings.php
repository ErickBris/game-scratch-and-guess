<?php
	require_once('MysqliDb.php');
	$db = new Mysqlidb ();
	session_start();
	if(!isset($_SESSION['myusername'])){
		header("location:index.php");
	}
	//echo "<pre>";print_r($_SESSION);exit;	
						
	if(isset($_POST['powered']) && $_POST != null && $_SESSION['usertype'] == 1)
	{
		global $db;
		$data = Array(									
			'poweredby' => $_POST['powered']		
		);
				
		$db->where ("id", 1);
		$db->update ('poweredby', $data);	
		$_SESSION['msg4'] = "Settings Updated Successfully";			
	}
	$db->where ("id", 1);						
	$r = $db->get("poweredby",1);	
?>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Settings | Scratch Admin Panel</title>
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
			<form name="addcategory" method="post" action="">
			<td>
			<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#FFFFFF">
			<tr>
			<td colspan="3" align="center"><strong></strong></td>
			</tr>
			<tr>
			<td >Powered By</td>
			<td width="6">:</td>
			<td><input name="powered" type="text" id="powered" value="<?php echo $r[0]['poweredby'];?>" required></td>
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
			<td colspan="3"  align="center" style="color:red;"><?php if(isset($_SESSION['msg4'])) { echo $_SESSION['msg4'];}?>
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

