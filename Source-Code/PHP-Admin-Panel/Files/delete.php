<?php 
	session_start();
	require_once('MysqliDb.php');
	$db = new Mysqlidb ();		
	if(!isset($_SESSION['myusername'])){
		header("location:index.php");
	}    
     
    if($_SESSION['usertype'] != 1)
    {
        header('location:error-pages.html');
        exit;
    }   
    
    if($_REQUEST['option'] == 'imgs' && isset($_REQUEST['id']))
    {
		$db->where ("id", $_REQUEST['id']);	
		$row = $db->get("url",1);	
		
		$fname = explode('/',$row[0]['url']);
		$imgname = "uploads/".end($fname);		
		unlink($imgname);
		
		$db->where ("url_id", $_REQUEST['id']);	
		$db->delete('answers', $data);
		
		$db->where ("id", $_REQUEST['id']);	
        $res = $db->delete('url', $data);		
		
        if($res == 1)
        {
            header('location:home.php?msg=success');
            exit;
        }
        else
        {
            header('location:home.php?msg=failed');
            exit;
        }
    }
	
	if($_REQUEST['option'] == 'cats' && isset($_REQUEST['id']))
    {
		$db->where ("cat_id", $_REQUEST['id']);	
		$row = $db->get("url");	
		
		foreach($row as $rows)
		{
			$fname = explode('/',$rows['url']);
			$imgname = "uploads/".end($fname);		
			unlink($imgname);
		}
				
		$db->where ("cat_id", $_REQUEST['id']);	
		$db->delete('url', $data);
		
		$db->where ("id", $_REQUEST['id']);	
        $res = $db->delete('category', $data);		
		
        if($res == 1)
        {
            header('location:viewcategory.php?msg=success');
            exit;
        }
        else
        {
            header('location:viewcategory.php?msg=failed');
            exit;
        }
    }
	
    
?>
