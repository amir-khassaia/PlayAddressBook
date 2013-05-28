$(document).ready(function() {
	// Prepare a basic jQuery tooltip for each cell in the contact table
	$("tr:visible, .contact").each(function() {
		var sText = "";
		$(this).children("td").each(function(){
			if (sText != "")
				// comma separated values
				sText += ", "; 
			sText += $(this).text().trim();
		});
		$(this).attr("title", sText);
	});
	// Color cells on hovering, should use toggleClass() and CSS ideally
	$("td, .contact").on("mouseenter", function(){
		$(this).css("background-color", "lightblue");
	})
	$("td, .contact").on("mouseleave", function(){
		$(this).css("background-color", "");
	})
});

