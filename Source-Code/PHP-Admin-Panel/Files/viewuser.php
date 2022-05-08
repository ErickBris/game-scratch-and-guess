<?php
session_start();
require_once('MysqliDb.php');
$db = new Mysqlidb ();	
if($_SESSION['myusername'] == ''){
	header("location:index.php");
}
//echo "<pre>";print_r($_SESSION);exit;
?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>View Users | Scratch Admin Panel</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="custom.css">
<link rel="stylesheet" type="text/css" href="jquery.dataTables.css">
<script type="text/javascript" language="javascript" src="js/jquery.js"></script>
<script type="text/javascript" language="javascript" src="js/jquery.dataTables.js"></script>
<script>
$(document).ready(function() {
	$('#example').dataTable();
} );
</script>
<div class="container">
	<div class="leftpart">
		<?php include 'menu.php'; ?>
	</div>
<a href="adduser.php">Add Users</a>		
	<div class="rightpart" style="margin-top:20px;">
		<table id="example" border="1" align="center" cellpadding="3" cellspacing="1">
		<thead>
			<tr>
				<th>Username</th>
				<th>Type</th>
				<?php
					if($_SESSION['usertype'] == 1){ 
				?>
				<th>Edit</th>
				<?php } ?>			
			</tr>
		</thead>
		<tbody>
			<?php 					
								
				$result = $db->get("members");				
				for($i=0;$i<count($result);$i++)
				{				
					$id=$result[$i]['id'];
			?>
			<tr>
				<td><?php echo $result[$i]['username'];?></td>
				<td>
				<?php 
					if($result[$i]['type'] == 1){
						echo "Admin";
					}
					else{
						echo "User";
					}				
				?></td>
				<?php
					if($_SESSION['usertype'] == 1){ 
				?>
				<td><a href="edituser.php?id=<?php echo $id; ?>">Edit</a></td>
				<?php
				}
			?>
			</tr>
			<?php
				}
			?>
		</tbody>
		</table>
	</div>
</div>
</body>
</html>