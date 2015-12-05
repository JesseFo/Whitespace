<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>New Post</title>
		<link rel="stylesheet" type="text/css" href="main.css">
	</head>
	<body>
		<nav>
			Message Board
			<a href="index.html">Home</a>
			<a href="posts.jsp">Posts</a>
			<a href="adminview.jsp">Admin</a>
		</nav>
		
		<div id="content">
			<h1>New Post</h1>
			
			<form method="post" action="makepost">
				Title:<br />
				<input type="text" name="posttitle" /> <br />
				Body:<br />
				<textarea name="postbody" rows="10" cols="50"></textarea> <br />
				<input type="submit" value="Submit">
				<input type="reset" value="Reset">
			</form>
		</div>
	</body>
</html>