<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
<form:form method="post" modelAttribute="todo">
<form:hidden path="id"/>
<fieldset class="form-group">
<form:label path="desc">Description </form:label>
<form:input type="text" name="desc" class="form-controler" required="required" path="desc"/>
<form:label path="targetDate">TargetDate </form:label>
<form:input type="text" name="targetDate" class="form-controler" required="required" path="targetDate"  />
<button type="submit" class="btn btn-success">Add </button>

<form:errors path="*" cssClass="text-warning"></form:errors>
</fieldset>
</form:form>
</div>
<script src="webjars/bootstrap-datepicker/1.0.1/js/bootstrap-datepicker.js"></script>
<script>
		$('#targetDate').datepicker({
			format : 'dd-MM-yyyy'
		});
	</script>
<%@ include file="common/footer.jspf" %>
