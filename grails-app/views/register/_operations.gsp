<h3>Operations:</h3>

<table class="table">
    <thead>
        <th>Buy date</th>
        <th>Price</th>
        <th>Volume</th>
        <th>Sell date</th>
        <th>Price</th>
        <th>Volume</th>
        <th>Calculation volume</th>
        <th>Income</th>
    </thead>
    <tbody>
        <g:each in="${calculations}" var="calculation">
            <tr>
                <g:render template="buy" bean="${calculation}"/>
                <g:render template="sell" bean="${calculation}"/>
                <g:render template="calculation" bean="${calculation}"/>
            </tr>
        </g:each>
    </tbody>

</table>
