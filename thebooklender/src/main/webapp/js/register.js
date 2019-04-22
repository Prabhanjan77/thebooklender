$(document).ready(function() {
  $("#registerButton").click(function(event) {
    event.preventDefault();
	var v1=document.getElementById("email2").value;
	var v2=document.getElementById("name1").value;
	var v3=document.getElementById("pass").value;
	var v4=document.getElementById("addr").value;
		//console.log(v1+ " "  +v2+" "+v3);
	

		var formData = {}
	    formData["user_email"] = v1;
		formData["user_name"] = v2;
	    formData["password"] = v3;
	    formData["address"]=v4;
	    var formJson = JSON.stringify(formData);
	    //console.log(formJson);


	var url="http://localhost:"+location.port+"/thebooklender/api/users/add";


    jQuery.ajax({
        url: url,
        type: "POST",
        data: formJson,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function() {
        	window.location.reload(true);
     
			},
        error: function() {
        
        	window.location.reload(true);
        		//alert("Account is created successfully");
					console.log("error");
					
        }
      });
		});
	});
