<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
<h2>Here are your to do list</h2><br/>

<table class="table table-striped">
<caption>Your Todos List</caption>
<thead><tr><th>Description</th><th>TargetDate</th><th>Is it Done</th><th></th><th></th></tr></thead>
<c:if test="${fn:length(TodosList)> 0}">
<c:forEach items="${TodosList}" var="todo">
<tbody><tr><td>${todo.desc}</td><td><fmt:formatDate pattern="dd-MM-yyyy" value="${todo.targetDate}"/></td><td>${todo.done}</td>
<td><a type="button" class="btn btn-success" href="/updatetodo?id=${todo.id}">Update</a></td>
<td><a type="button" class="btn btn-warning" href="/deleteTodo?id=${todo.id}">Delete</a></td>
</tr></tbody>
</c:forEach>
</c:if>
</table>
<div class="button>"><a href="/addTodo"> add To-do</a></div>
${TodosList}
</div>
<%@ include file="common/footer.jspf" %>
