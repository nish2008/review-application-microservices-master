<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stag" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to Review Application!!</title>
</head>
<body>
<script language="Javascript" type="text/javascript">


    function createRequestObject() {
        var tmpXmlHttpObject;

        //depending on what the browser supports,
        //use the right way to create the XMLHttpRequest object
        if (window.XMLHttpRequest) {
            // Mozilla, Safari would use this method ?
                tmpXmlHttpObject = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            // IE would use this method ?
                tmpXmlHttpObject = new ActiveXObject("Microsoft.XMLHTTP");
        }

        return tmpXmlHttpObject;
    }

//call the above function to create the XMLHttpRequest object
var http = createRequestObject();

    function makeGetRequest() {
        //make a connection to the server ?
        //specifying that you intend to make a GET request
        //to the server. Specifiy the page name and the URL parameters to send
            http.open('get', 'readfromcache');

        //assign a handler for the response
            http.onreadystatechange = processResponse;

        //actually send the request to the server
            http.send(null);
    }

    function processResponse() {
        //check if the response has been received from the server
            if(http.readyState == 4){

        //read and assign the response from the server
            var response = http.responseText;

        //do additional parsing of the response, if needed

        //in this case simply assign the response
        //to the contents of the <div> on the page.
            const update_desc = document.getElementById('description');
                update_desc.innerHTML = update_desc.innerHTML + response  ;

        //If the server returned an error message like a 404 error,
        //that message would be shown within the div tag!!.
        //So it may be worth doing some basic error before
        //setting the contents of the <div>
    }

}

    setInterval(makeGetRequest, 1000);

</script>
    <h2>Kafka Logs from multiple services : </h2>

    <div id="description"></div>
</body>
</html>