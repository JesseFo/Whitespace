<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Posts</title>
		<link rel="stylesheet" type="text/css" href="main.css">
	</head>
	<body>
		<div id="wrapper">
			<nav>
				Message Board
				<a href="index.html">Home</a>
				<a href="posts.jsp">Posts</a>
				<a href="adminview.jsp">Admin</a>
			</nav>
			
			<div id="content">
				<h1>Posts</h1>
				
				<p><a href="newpost.jsp">Write a post</a></p>
				<hr />
				
				<div class="post">
					<h2>Post Title</h2> <% //show every post in REVERSE date order - newest first %>
					<h4>Author: FirstName Lastname</h4>
					<hr />
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin porta nulla nulla, vitae efficitur est posuere vitae. Morbi pretium neque sit amet purus ultricies, ut fringilla ex tempor. Vestibulum sit amet tortor fermentum, ultrices metus ut, ullamcorper velit. Vivamus sed imperdiet ipsum, quis tempor est. In a mattis massa. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Duis pellentesque viverra dignissim. Proin molestie consequat volutpat.</p>
					<a href="comment.jsp?p=postid">Comment...</a> || <a href="removepost.jsp?p=postid">Delete</a>
				</div>
				
				<div class="comment"> <% //for every post, show each comment in date order %>
					<p>Firstname Lastname says...</p>
					<p>I really wish we were using literally anything other than JSP for this garbage.</p>
					<a href="removecomment.jsp?c=commentid">Remove</a>
				</div>
				
			</div>
		</div>
	</body>
</html>