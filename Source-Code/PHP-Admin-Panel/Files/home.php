<?php
session_start();
require_once('MysqliDb.php');
$db = new Mysqlidb ();
if($_SESSION['myusername'] == ''){
	header("location:index.php");
}


?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Home | Scratch Admin Panel</title>
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
	
	<?php
	if(isset($_GET['msg']) && $_GET['msg'] == 'success')
	{            
	?>
		<div class="alert alert-success">			
			<strong>Success!</strong> Record Deleted.
		</div>
	<?php
	}
	else if(isset($_GET['msg']) && $_GET['msg'] == 'success')
	{
	?>
		<div class="alert alert-success">				
				<strong>Failed!</strong> To Delete Record.
		</div>
    <?php
    }	                
	?>
	<script>
		$(document).ready(function(){
			$('.alert').fadeIn(500);
			$('.alert').delay(3000);	
			$('.alert').fadeOut(500);
		});
	</script>
		
	<div class="rightpart" style="margin-top:20px;">
		<table id="example" border="1" align="center" cellpadding="3" cellspacing="1">
		<thead>
			<tr>
				<th>Category</th>
				<th>Image Url</th>
				<?php
					if($_SESSION['usertype'] == 1){ 
				?>
				<th>Action</th>
				<?php } ?>
			</tr>
		</thead>
		<tbody>
			<?php 			
	
				$result = $db->get("url");				
				for($i=0;$i<count($result);$i++)
				{
			?>
			<tr>
				<td>
					<?php
						$db->where ("id", $result[$i]['cat_id']);						
						$r = $db->get("category",1);
						echo $r[0]['category'];					
					?>
				</td>
				<td><?php echo $result[$i]['url'];?></td>
				<?php
					if($_SESSION['usertype'] == 1){ 
				?>
				<td><a href="delete.php?option=imgs&id=<?php echo $result[$i]['id']; ?>" onclick="return confirm('Are you Sure you want to delete Image')">Delete</a></td>
				<?php } ?>
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