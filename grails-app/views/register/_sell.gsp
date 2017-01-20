<% if (it != null) { %>
    <td>${it.sell?.dateCreated.toString(org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd"))}</td>
    <td>${it.sell?.price}</td>
    <td>${it.sell?.volume}</td>
<%} else {%>
    <td></td>
    <td></td>
    <td></td>
<%}%>