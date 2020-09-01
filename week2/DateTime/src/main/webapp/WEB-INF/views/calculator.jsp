<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.sapient.calculator.web.*" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DateTimeCalculator</title>
    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
         rel = "stylesheet">
    <script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
      <!-- Javascript -->
      <script>
         
      </script>
</head>
<body>


  <div class="jumbotron">
    <h1 class="display-4">DateTime Calculator</h1>
    <p class="lead">Pick an option to start with the application.</p>
    <hr class="my-4">


    <div class="form-group">
      <label for="type_app">Select type</label>
      <select class="form-control" aria-selected="0" name="type_app" id="type_app">
        <option value="1">Select</option>
        <option value="2">Select to perform various operation on date using DateTime calculator.</option>
        <option value="3">Select to perform automation, bulk processing and visit history of the sessions.</option>
      </select>
    </div>

    <div class="bulk_operations">
      
      <div class="form-group">
        <label for="bulk_opt">Select bulk operation</label>
        <select class="form-control" aria-selected="0" name="bulk_opt" id="bulk_opt">
          <option value="1">Select</option>
          <option value="2">Select for bulk operation on DateTime calculator.</option>
          <option value="3">Select for automating creation of bulk operation file</option>
          <option value="4">Select to view the dashboard for the history of last 100 session done users.</option>
        </select>

        <button type="button" style="margin-top: 20px;" class="btn btn-dark submit_bulk">Submit</button>

        <div class="bulk_result" style="margin-top: 30px;">
          <%
            BulkController bc = new BulkController();
            bc.userSelection(2);
          %>

          <%
            BulkController bc2 = new BulkController();
            bc2.userSelection(1);
          %>

          <%
            BulkController bc3 = new BulkController();
            bc3.userSelection(3);
          %>
        </div>
      </div>

    </div>

    <div class="operation_date">
      <div class="form-group">
        <label for="method">Select Operation</label>
        <select class="form-control" aria-selected="0" name="method" id="method">
          <option value="1">Select</option>
          <option value="2">Select for addition/subtraction of two dates.</option>
          <option value="3">Select for addition/subtraction of n days/weeks/months to a given date.</option>
          <option value="4">Select to determine day of the week of a given date.</option>
          <option value="5">Select to determine week number of a given date.</option>
          <option value="6">Select to translate natural language phrases</option>
        </select>
      </div>
  
      <div class="operation" style="margin: 30px auto;">
        <h5 style="text-align: center;">Input Values</h5>
        <div id="date1" class="form-group in">
          <label for="datepicker-13">Enter Date 1: </label>
          <input class="form-control" type = "text" name="date1" id = "datepicker-13">
        </div>
        <div  id="date2" class="form-group in">
          <label for="datepicker-14">Enter Date 2: </label>
          <input class="form-control" type = "text" name="date2" id = "datepicker-14">
        </div>
  
        <div class="row in" id="n">
          <div class="col-sm-6">
            <div>
              <label for="n">N</label>
              <input type="number" class="form-control" name="nday" id="nday" placeholder="5">
            </div>
          </div>
          <div class="col-sm-6">
            <div class="form-group">
              <label for="ntype">Select type</label>
              <select class="form-control" id="ntype" name="ntype">
                <option value="1">Day</option>
                <option value="2">Week</option>
                <option value="3">Month</option>
                <option value="4">Year</option>
              </select>
            </div>
          </div>
        </div>
  
        <div class="radio in row" style="margin: 0;">
          <div class="form-check col-sm-6">
            <input style="margin-top: 2px;" class="form-check-input" type="radio" name="opt" id="exampleRadios1" value="1" checked>
            <label class="form-check-label" for="exampleRadios1">
              Addition
            </label>
          </div>
          <div class="form-check col-sm-6">
            <input style="margin-top: 2px;" class="form-check-input" type="radio" name="opt" id="exampleRadios2" value="2">
            <label class="form-check-label" for="exampleRadios2">
              Substraction
            </label>
          </div>
        </div>
        <div class="form-group in" id="phrase">
          <label for="phraset">Phrase</label>
          <input type="text" class="form-control" name="phrase" id="phraset" placeholder="Tomorrow">
        </div>
        <button type="button" style="margin-top: 20px;" class="btn btn-dark submit">Submit</button>
      </div>
  
      <div class="result">
      </div>

    </div> 

  </div>
  <script>
    $( "#datepicker-13" ).removeAttr("autofocus");
  </script>

<div id="google_translate_element"></div>

<script type="text/javascript">
function googleTranslateElementInit() {
  new google.translate.TranslateElement({pageLanguage: 'en', layout: google.translate.TranslateElement.InlineLayout.HORIZONTAL}, 'google_translate_element');
}
</script>

<script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>


  <script src="${contextPath}/resources/js/script.js"></script>
</body>
</html>



















<!-- 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>DateTime Calculator</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">

    <h2>Hello ${name}!</h2>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
 -->