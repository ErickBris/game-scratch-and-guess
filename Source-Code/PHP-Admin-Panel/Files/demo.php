<?php

	/* connect to the db */	
	require_once('MysqliDb.php');
	$db = new Mysqlidb ();	
	
	/* grab the posts from the db */	
	$result = $db->get('category');

	if(isset($_GET['category']) && !isset($_GET['level']))
	{	
		$cats=$_GET['category'];	
		$db->where('cat_id',$cats);
		$result = $db->get('url');
		
		$i=0;
		foreach($result as $posts) {			
			$result[$i]['answers'] = get_answer($posts['id'],$i);
			//$result[$i]['true'] = get_true_answer($posts['id'],$i);		
			$i++;
		}		
		unset($i);
		//echo "<pre>";print_r($result);exit;
		header('Content-type: application/json');		
		echo json_encode(array('result'=>'','message'=>'','data'=>$result));
		exit;	
	}
	
	function get_answer($id,$i)
	{	
		$db = new Mysqlidb ();	
		$db->where('url_id',$id);
		$answer[$i] = $db->get('answers',null,'*');
		//echo "<pre>";print_r($answer);		
		return $answer[$i];		
	}
	/*
	function get_true_answer($id,$i)
	{	
		$db = new Mysqlidb ();	
		$db->where('question_id',$id);
		$true[$i] = $db->get('answers',null,'result');
		//echo "<pre>";print_r($true);
		return $true[$i];
	
	}
	*/
	/*
	if(isset($_GET['category']) && isset($_GET['level']))
	{	
		$cats=$_GET['category'];
		$lvl=$_GET['level'];		
		$db->where('cat_id',$cats);
		$db->where('level_id',$lvl);
		$result = $db->get('questions');				
		
		$i=0;
		foreach($result as $posts) {			
			$result[$i]['answers'] = get_answer($posts['id'],$i);
			//$result[$i]['true'] = get_true_answer($posts['id'],$i);		
			$i++;
		}		
		unset($i);
		
		//echo "<pre>";print_r($result);
		header('Content-type: application/json');		
		echo json_encode(array('result'=>'','message'=>'','data'=>$result));
		exit;	
	}	
	*/
	/* output in necessary format */
	
	header('Content-type: application/json');		
	echo json_encode(array('result'=>'','message'=>'','data'=>$result));

?>