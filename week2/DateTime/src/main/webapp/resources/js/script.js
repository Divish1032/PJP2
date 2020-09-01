var operation = 0;
var weekday = new Array(7);
var bulk = 1;
weekday[0] = "Sunday";
weekday[1] = "Monday";
weekday[2] = "Tuesday";
weekday[3] = "Wednesday";
weekday[4] = "Thursday";
weekday[5] = "Friday";
weekday[6] = "Saturday";

$('#type_app').on('change', function() {
    $('.in, .operation').hide();
    checkType(this.value);
});

$('#method').on('change', function() {
    $('.in, .operation').hide();
    checkOperation(this.value);
});

$('#bulk_opt').on('change', function() {
    bulk = this.value;
    if(this.value == 1){
        $('.submit_bulk').hide();
    }
    else{
        $('.submit_bulk').show();
    }
});

init = () =>  {
    $('#method').val(1);
    bulk = 1;
    $('#type_app').val(1);
    $('.in, .operation, .operation_date, .bulk_operations, .submit_bulk').hide();
    checkType($('#type_app').val());
}

checkType = (ch) => {
    $('.in, .operation, .operation_date, .bulk_operations').hide();
    if(ch == 2){
        $('.bulk_operations').show();
        checkOperation($('#method').val());
    }
    else if(ch == 3){
        $('.operation_date').show();
        checkOperation($('#method').val());
    }
}

$(".submit_bulk").click(function(){
    $(".bulk_result").html("");
    if(bulk == 2){
        $(".bulk_result").append('<p class = "resu">Query result file generated at resources folders with name bulk_query_result.csv</p>');
    }
    else if(bulk == 3){
        $(".bulk_result").append('<p class = "resu">File generated at resources folders with name java_bulk_query_output.csv</p>');
    }
    else if(bulk == 4){
        $(".bulk_result").append('<p class = "resu">Session data dashboard is empty due to some error.</p>');
    }
    else{
        $(".bulk_result").append('<p class = "resu">Null</p>');
    }
});




checkOperation = (choice) => {
    $('.result').html("");

    operation = choice;
    if(choice == 2){
        $('.operation, #date1, #date2, .radio').show();
        $( "#datepicker-13, #datepicker-14" ).datepicker({ dateFormat: 'yy-mm-dd' });
       // $( "#datepicker-13,#datepicker-14" ).datepicker("show");
    }
    if(choice == 3){
        $('.operation, #date1, #n, .radio').show();
        $( "#datepicker-13, #datepicker-14" ).datepicker({ dateFormat: 'yy-mm-dd' });
    }
    if(choice == 4){
        $('.operation, #date1').show();
        $( "#datepicker-13, #datepicker-14" ).datepicker({ dateFormat: 'yy-mm-dd' });
    }
    if(choice == 5){
        $('.operation, #date1').show();
        $( "#datepicker-13, #datepicker-14" ).datepicker({ dateFormat: 'yy-mm-dd' });
    }
    if(choice == 6){
        $('.operation, #phrase').show();
    }
}

function loadDoc() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        console.log(this.responseText);
      }
    };
    xhttp.open("POST", "testing-api", true);
    xhttp.send();
}

$(".submit").click(function(){

    loadDoc();

    $('.result').html("");
    if(operation == 2){
        var date1 = $('#datepicker-13').val();
        var date2 = $('#datepicker-14').val();
        var radio = $("input[name='opt']:checked").val();
        var mydate1 = new Date(date1);
        var mydate2 = new Date(date2);
        if(radio == 1){
            mydate1.addDays(mydate2.getDate());
            mydate1.addMonths(mydate2.getMonth());
            mydate1.addYears(mydate2.getFullYear());
            $(".result").append('<p class = "resu">Addition of two dates - ' + mydate1.toUTCString() + ' .</p>')
        }
        else{
            const diffTime = Math.abs(mydate1 - mydate2);
            const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); 
            const diffWeeks =  Math.floor(diffTime / (1000 * 60 * 60 * 24 * 7)); 
            const diffMonths =  Math.floor(diffTime / (1000 * 60 * 60 * 24 * 7 * 30)); 
            const diffYears =  Math.floor(diffTime / (1000 * 60 * 60 * 24 * 7 * 365)); 

            $(".result").append('<p class = "resu">Subraction of two dates - ' + diffDays + ' days.</p>');
            $(".result").append('<p class = "resu">Subraction of two dates - ' + diffWeeks + ' weeks.</p>');
            $(".result").append('<p class = "resu">Subraction of two dates - ' + diffMonths + ' months.</p>');
            $(".result").append('<p class = "resu">Subraction of two dates - ' + diffYears + ' years.</p>')

        }
    }
    else if(operation == 3){
        var date1 = $('#datepicker-13').val();
        var n = $('#nday').val();
        var type = $('#ntype').val();
        var mydate1 = new Date(date1);
        var radio = $("input[name='opt']:checked").val();
        console.log("11111111111e" + radio + " " + type + " " + n);
        if(type == 1){
            (radio == 1) ? 
            mydate1.addDays(n)
            : mydate1.minusDays(n);
        }
        else if(type == 2){
            (radio == 1) ? 
            mydate1.addDays(7 * n)
            : mydate1.minusDays( 7 * n);
        }
        else if(type == 3){
            (radio == 1) ? 
            mydate1.addMonths(n)
            : mydate1.minusMonths(n);
        }
        else if(type == 4){
            (radio == 1) ? 
            mydate1.addYears(n)
            : mydate1.minusYears(n);
        }   
        
        $(".result").append('<p class = "resu">Operation gave following result: ' + mydate1.toUTCString() + ' .</p>')
    }
    else if(operation == 4){
        var date1 = $('#datepicker-13').val();
        var mydate1 = new Date(date1);
        var ans = mydate1.getDay();
        $(".result").append('<p class = "resu">Day of the week of given date is: ' + weekday[ans] + ' .</p>')
    }
    else if(operation == 5){
        var date1 = $('#datepicker-13').val();
        var mydate1 = new Date(date1);
        $(".result").append('<p class = "resu">Week number of given date is: ' + mydate1.getWeek() + ' .</p>')
    }
    else{
        var phrase = $('#phraset').val();
        var result = checkNLP(phrase);
        $(".result").append('<p class = "resu">' + result + ' .</p>')
    }
}); 


checkNLP = (phrase) => {
    var now = new Date();
    phrase = phrase.toLowerCase();
    var thenum = (phrase.match(/\d+/) != null) ? phrase.match(/\d+/)[0] : 0;
    
    if(phrase.includes("tomorrow")){
        return "Translated date: " +  now.addDays(1).toUTCString().substring(5,16);
    }
    else if(phrase.includes("today")){
        return "Translated date: " +  now.toUTCString().substring(5,16);
    }
    else if(phrase.includes("days earlier")){
        return "Translated date: " +  now.minusDays(thenum).toUTCString().substring(5,16);
    }
    else if(phrase.includes("month") && phrase.includes("earlier")){
        return "Translated date: " +  now.minusMonths(thenum).toUTCString().substring(5,16);
    }
    else if(phrase.includes("next month")){
        return "Translated date: " +  now.addMonths(1).toUTCString().substring(5,16);
    }
    else if(phrase.includes("yesterday")){
        return "Translated date: " +  now.minusDays(1).toUTCString().substring(5,16);
    }
    else if(phrase.includes("day before yesterday")){
        return "Translated date: " +  now.minusDays(2).toUTCString().substring(5,16);
    }
    else if(phrase.includes("next week")){
        return "Translated date: " +  now.addDays(7).toUTCString().substring(5,16);
    }
    else if(phrase.includes("month") && phrase.includes("before")){
        return "Translated date: " +  now.minusMonths(thenum).toUTCString().substring(5,16);
    }
    else if(phrase.includes("weeks from now")){
        return "Translated date: " +  now.addDays(thenum * 7).toUTCString().substring(5,16);
    }
    else if(phrase.includes("previous week")){
        return "Translated date: " +  now.minusDays(7).toUTCString().substring(5,16);
    }
    else if(phrase.includes("last month")){
        return "Translated date: " +  now.minusMonths(1).toUTCString().substring(5,16);
    }
    else if(phrase.includes("last week")){
        return "Translated date: " +  now.minusDays(7).toUTCString().substring(5,16);
    }
    else if(phrase.includes("months earlier")){
        return "Translated date: " +  now.minusMonths(thenum).toUTCString().substring(5,16);
    }
    else if(phrase.includes("last year")){
        return "Translated date: " +  now.minusYears(1).toUTCString().substring(5,16);
    }
    else if(phrase.includes("next year")){
        return "Translated date: " +  now.addYears(1).toUTCString().substring(5,16);
    }
    else if(phrase.includes("days from now")){
        return "Translated date: " +  now.addDays(thenum).toUTCString().substring(5,16);
    }
    else if(phrase.includes("months from now")){
        return "Translated date: " +  now.addMonths(thenum).toUTCString().substring(5,16);
    }
    else if(phrase.includes("years from now")){
        return "Translated date: " +  now.addYears(thenum).toUTCString().substring(5,16);
    }
    else if(phrase.includes("weeks") && phrase.includes("earlier")){
        return "Translated date: " +  now.minusDays(thenum * 7).toUTCString().substring(5,16);
    }
    else{
        return "Our translator is improving day by day. Don't worry we will convert this text in future."
    }
}



Date.prototype.addDays = function(days) {
    this.setDate(this.getDate() + parseInt(days));
    return this;
};

Date.prototype.addMonths = function(months) {
    console.log(this.getMonth());
    this.setMonth(this.getMonth()  + parseInt(months));
    return this;
};

Date.prototype.addYears = function(years) {
    console.log(this.getMonth());
    this.setFullYear(this.getFullYear() + parseInt(years));
    return this;
};

Date.prototype.minusDays = function(days) {
    this.setDate(this.getDate() - parseInt(days));
    return this;
};

Date.prototype.minusMonths = function(months) {
    console.log("Jjjjjjjj" + this.getMonth + " " + months);
    this.setMonth(this.getMonth() - parseInt(months) );
    return this;
};

Date.prototype.minusYears = function(years) {
    console.log(this.getMonth());
    this.setFullYear(this.getFullYear() - parseInt(years));
    return this;
};

Date.prototype.getWeek = function() {
    var date = new Date(this.getTime());
    date.setHours(0, 0, 0, 0);
    // Thursday in current week decides the year.
    date.setDate(date.getDate() + 3 - (date.getDay() + 6) % 7);
    // January 4 is always in week 1.
    var week1 = new Date(date.getFullYear(), 0, 4);
    // Adjust to Thursday in week 1 and count number of weeks from date to week1.
    return 1 + Math.round(((date.getTime() - week1.getTime()) / 86400000
                          - 3 + (week1.getDay() + 6) % 7) / 7);
  }

  init();

