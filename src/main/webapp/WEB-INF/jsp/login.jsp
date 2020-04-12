<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<font color="red">${errorMessage}</font></br>
<form method="POST">
   Name : <input type="text" name="name"></input><br/>
   Password :<input type="password" name="password"></input><br/>
   <input type="submit"></input>
</form>
<%@ include file="common/footer.jspf" %>
