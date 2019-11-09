<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Document Upload</title>
</head>
<body>
	<div align="center">
		<h1>Document Upload</h1>

		<form action="upload" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>Id:</td>
					<td><input type="text" name="id" /></td>
				</tr>
				<tr>
					<td>Document:</td>
					<td><input type="file" name="document" /></td>
				</tr>
				<tr>
					<td><input type="submit" name="submit" value="Upload" /></td>
				</tr>
			</table>
		</form>
	</div>
	<hr />
	<div align="center">
	<h1>View and Download Documents</h1>
		<table>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Link</th>
			</tr>
			<c:forEach items="${documents }" var="document">
				<tr>
					<td>${document.id }</td>
					<td>${document.name }</td>
					<td><a href="download?id=${document.id }">Download</a></td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>