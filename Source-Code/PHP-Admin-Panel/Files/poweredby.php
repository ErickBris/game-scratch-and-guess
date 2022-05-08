<?php

	/* connect to the db */
	$link = mysql_connect('localhost','gurutech_photofr','JNCBI&(aH,,I') or die('Cannot connect to the DB');
	mysql_select_db('gurutech_photoframe',$link) or die('Cannot select the DB');

	/* grab the posts from the db */
	$query = "SELECT poweredby FROM poweredby where id='1'";
	$result = mysql_query($query,$link) or die('Invalid query:  '.$query);
	
	/* create one master array of the records */
	$posts = array();
	if(mysql_num_rows($result)) {
		while($post = mysql_fetch_assoc($result)) {
			$posts[] =$post;
			//$cat[] = $post['category'];			
		}
	}
	//echo "<pre>";print_r($posts);exit;
	//$category = implode(",",$cat);
	
	/* output in necessary format */
	
	header('Content-type: application/json');		
	echo json_encode(array('result'=>'','message'=>'','data'=>$posts));

	/* disconnect from the db */
	@mysql_close($link);

?>