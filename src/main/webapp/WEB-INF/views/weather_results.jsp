<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="weatherinfo">
<table class="results-data">
<c:forEach items="${results}" var="element"> 
  <tr>
    <td>${element.cityName}</td>
    <td>${element.weatherData}</td>
    <td></td>
    <td></td>
  </tr>
</c:forEach> 
</table>
</div>