<g:form name="filter" action="filter" class="form-inline">
    <div class="form-group">
        <label for="fromDate">From</label>
        <g:datePicker name="fromDate" value="${filter?.fromDate}" precision="year" years="${Calendar.instance.get(Calendar.YEAR)..2000}"></g:datePicker>
    </div>
    <div class="form-group">
        <label for="toDate">To</label>
        <g:datePicker name="toDate" value="${filter?.toDate}" precision="year" years="${Calendar.instance.get(Calendar.YEAR)..2000}"></g:datePicker>
    </div>
    <g:submitButton name="Filter" class="btn btn-default"/>
    <g:link controller="register" action="clearFilter">
        <input type="button" value="Clear" class="btn"/>
    </g:link>
    <g:link controller="register" action="fullDataSet">
        <input type="button" value="Full data" class="btn"/>
    </g:link>
</g:form>
