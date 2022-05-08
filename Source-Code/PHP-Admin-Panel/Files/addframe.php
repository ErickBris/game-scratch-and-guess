<?php
session_start();
	require_once('MysqliDb.php');
	$db = new Mysqlidb ();		
	if(!isset($_SESSION['myusername'])){
		header("location:index.php");
	}
	
	if(isset($_POST['category']) && $_POST['category'] != null && $_SESSION['usertype'] == 1)
	{			
		$target_dir = "uploads/";
		$target_file = $target_dir . basename($_FILES["photo"]["name"]);
		
		$uploadOk = 1;
		$imageFileType = pathinfo($target_file,PATHINFO_EXTENSION);
		// Check if image file is a actual image or fake image
		if(isset($_POST["submit"])) {
		
			$check = getimagesize($_FILES["photo"]["tmp_name"]);
			if($check !== false) {
				//echo "File is an image - " . $check["mime"] . ".";
				$uploadOk = 1;
			} else {
				$_SESSION['err'] = "File is not an image.";
				$uploadOk = 0;
			}
		}
		
		// Check if file already exists
		if (file_exists($target_file)) {
			$_SESSION['err'] = "Sorry, file already exists.";
			$uploadOk = 0;
		}
		
		 $maxsize  = 5097152; //5mb
		 
		// Check file size
		if ($_FILES["photo"]["size"] >= $maxsize) {			
			$_SESSION['err'] = "Sorry, your file is too large.";
			$uploadOk = 0;
		}

		// Allow certain file formats
		if($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg"
		&& $imageFileType != "gif" ) {
			$_SESSION['err'] = "Sorry, only JPG, JPEG, PNG & GIF files are allowed.";
			$uploadOk = 0;
		}
		// Check if $uploadOk is set to 0 by an error
		if ($uploadOk == 0) {
			$_SESSION['err'] = "Sorry, your file was not uploaded.";
		// if everything is ok, try to upload file
		} else {
			if (move_uploaded_file($_FILES["photo"]["tmp_name"], $target_file)) {
				//echo "The file ". basename( $_FILES["photo"]["name"]). " has been uploaded.";
			} else {
				$_SESSION['err'] = "Sorry, there was an error uploading your file.";
			}
		}

		if(!isset($_SESSION['err']) && $_SESSION['err'] == "")
		{
			//echo "<pre>";print_r($_POST);exit;
			global $db;
			$fullpath = BASE_URL.$target_file;
			$data = Array(
				'cat_id' => $_POST['category'],
				'url' => $fullpath	
			);
			$id = $db->insert('url', $data);
			
			$lastid = $db->getInsertId("url");
			if($_POST['correct'] == 1)
			{
				$ans1 = Array(
					'url_id' => $lastid,
					'answer' => $_POST['answer1'],
					'result' => '1'
				);
			}
			else
			{
				$ans1 = Array(
					'url_id' => $lastid,
					'answer' => $_POST['answer1'],
					'result' => '0'					
				);			
			}
			$db->insert('answers', $ans1);
			
			if($_POST['correct'] == 2)
			{
				$ans2 = Array(
					'url_id' => $lastid,
					'answer' => $_POST['answer2'],
					'result' => '1'
				);
			}
			else
			{
				$ans2 = Array(
					'url_id' => $lastid,
					'answer' => $_POST['answer2'],
					'result' => '0'						
				);
			}
			$db->insert('answers', $ans2);
			
			if($_POST['correct'] == 3)
			{
				$ans3 = Array(
					'url_id' => $lastid,
					'answer' => $_POST['answer3'],
					'result' => '1'
				);
			}
			else
			{
				$ans3 = Array(
					'url_id' => $lastid,
					'answer' => $_POST['answer3'],
					'result' => '0'					
				);
			}
			$db->insert('answers', $ans3);
			
			if($_POST['correct'] == 4)
			{
				$ans4 = Array(
					'url_id' => $lastid,
					'answer' => $_POST['answer4'],
					'result' => '1'
				);
			}
			else
			{
				$ans4 = Array(
					'url_id' => $lastid,
					'answer' => $_POST['answer4'],
					'result' => '0'						
				);
			}
			$db->insert('answers', $ans4);
					
			if($id != ''){
				$_SESSION['msg1'] = "Image Uploaded Successfully";			
			} else {
				$_SESSION['msg1'] = "Image Not Uploaded";
			}
		}
	}
?>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Add Photos | Scratch Admin Panel</title>
	  <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
	<script language="javascript">
	
	
	
	$(document).ready(function(){	
	$("#correct").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A
            (e.keyCode == 65 && e.ctrlKey === true) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
		$('.alert').fadeIn(500);
		$('.alert').delay(3000);	
		$('.alert').fadeOut(500);
		
		$('.err').fadeIn(500);
		$('.err').delay(3000);	
		$('.err').fadeOut(500);
	});
	</script>
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
		<table width="550" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">

			<tr>
			<form name="addframe" method="post" action="addframe.php" enctype="multipart/form-data">
			<td>
			<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#FFFFFF">
			<tr>
			<td colspan="3" align="center"><strong>Add Image</strong></td>
			</tr>
			<tr>
			<td>Category</td>
			<td width="6">:</td>
			<td>
				<select name="category" id="category">
				<?php 					
					$result = $db->get("category");				
					for($i=0;$i<count($result);$i++)
					{	
				?>
					<option value="<?php echo $result[$i]['id']?>"><?php echo $result[$i]['category']?></option>
				<?php
					}
				?>
				</select>
			</td>
			</tr>
			<tr>
			<!--
			<tr>
			<td>Frame Url</td>
			<td width="6">:</td>
			<td><input size="65" name="frameurl" type="text" id="frameurl" required></td>
			</tr>
			-->
			<tr>
			<td>Upload Image</td>
			<td width="6">:</td>
			<td><input type="file" name="photo" id="photo" required></td>
			</tr>
			<tr>
			<td>Answer 1 </td>
			<td width="6">:</td>
			<td><input name="answer1" type="text" id="answer1" required></td>
			</tr>
			<tr>
			<td>Answer 2 </td>
			<td width="6">:</td>
			<td><input name="answer2" type="text" id="answer2" required></td>
			</tr>
			<tr>
			<td>Answer 3 </td>
			<td width="6">:</td>
			<td><input name="answer3" type="text" id="answer3" required></td>
			</tr>
			<tr>
			<td>Answer 4 </td>
			<td width="6">:</td>
			<td><input name="answer4" type="text" id="answer4" required></td>
			</tr>
			<tr>
			<td>Enter Correct Answer No.</td>
			<td width="6">:</td>
			<td><input name="correct" type="text" id="correct" placeholder="E.g. 1" required></td>
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
			<td class="err" colspan="3"  align="center" style="color:red;"><?php if(isset($_SESSION['err'])) { echo $_SESSION['err'];}?>						
			</td>
			</tr>
			<tr>
			<td class="alert" colspan="3"  align="center" style="color:green;"><?php if(isset($_SESSION['msg1'])) { echo $_SESSION['msg1'];}?>
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

