<html>
<head><title> Fruit picker</title></head>
<body>
	<form action="/favorite_fruit" method="POST">
		<p> What is oyur favorite fruit?</p>
		
		<#list fruits as fruit>
			<p>
				<input type="radio" name="fruit" value="${fruit}">${fruit}</input>
			</p>
		</#list>
		<input type="submit" value="Submit" />
	</form>
</body>
</html>