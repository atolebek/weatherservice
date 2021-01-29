<%--
  Created by IntelliJ IDEA.
  User: AliTo
  Date: 28.01.2021
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weather and Geo service</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <input type="text" name="cityCode" id="cityCode">
    <button type="button" id="submitCode">Get weather</button>
    <br>
    <p id="weatherInfo"></p>
    <br>
    <input type="text" name="zipcode" id="zipCode">
    <button type="button" id="submitZip">Get weather</button>
    <p id="timeZoneInfo"></p>
<br>


</p>
<script>
    $("#submitCode").click(function (e){
        e.preventDefault();
        const cityCode = $("#cityCode").val();
        const xhr = new XMLHttpRequest();
        xhr.overrideMimeType("application/json");
        xhr.open("GET", "get_weather?cityCode=" + cityCode, true);
        xhr.onreadystatechange = function (){
            $("#weatherInfo").text(xhr.responseText);
        }
        xhr.send(null);
    })



    $("#submitZip").click(function (){
        const zipCode = $("#zipCode").val();
        const code = JSON.stringify({zipCode: zipCode});
        $.ajax({
            url:'/get_timezone',
            type: 'POST',
            data: 'json=' + code,
            dataType: 'json',
            success: function (result){
                $("#timeZoneInfo").text(JSON.stringify(result));
            }
        })
    })
</script>

</script>
</body>
</html>