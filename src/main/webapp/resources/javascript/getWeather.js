$(document).ready(function(){

	$("#submitButton").click(function(){
	  
		var cityName  = $("#cityname").val();
		var cities;
		
		
	if (validateInput(cityName) !== false){
		
		if (cityName !== undefined || cityName !== "") {
			cities = cityName.split(",")	
		}

		// ajax call to server to send city data
		$.ajax({
				url: "getWeather?city="+cities,
				type: 'GET',
				beforeSend: function() { $('#loadingModal').show(); },
		        complete: function() { $('#loadingModal').hide(); },
				success: function(result){
						$("#weatherinfo").html(result);
	    }});
	 }
	});
	
	
	function validateInput(cityName){
		// check city names are alpha characters
		if(!cityName.match(/^[a-zA-Z, ]*$/)){	
			
			$("#errormessage.error").html("Please enter only alpha characters");
			$("#cityname").addClass("input-error");
			
			return false;
			
		}else{
			
			$("#errormessage.error").html("");
			$("#cityname").removeClass("input-error");
			
		}
		if (cityName === ""){
			
			$("#errormessage.error").html("Please enter a city name");
			$("#cityname").addClass("input-error");
			
			return false;
			
		}else if (cityName !== "" && cityName.match(/^[a-zA-Z, ]*$/)){
			
			$("#errormessage.error").html("");
			$("#cityname").removeClass("input-error");

		}

	}
	
	
	$('#cityname').keypress(function(event) {
	    if (event.keyCode == 13 || event.which == 13) {
	        $('#submitButton').focus().click();
	    }
	});
	
});
