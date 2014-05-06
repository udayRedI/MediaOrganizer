var mediaObject = {
		mediaList:new Array(),
		mediaCount:0
}
var editMovieForm;
movieRowHtml=null;
var languageData;
var matchError = "No Match Found";
var matchCriteriaError = "Please Select search criteria";
var totalResults = new Object();
var currentPage;



function reloadLabels() {
	$("#titleLabel").html(languageData.mediaOrganizer);
	$("#homeLabel").html(languageData.home);
	$("#importLabel").html(languageData.import);
	$("#logoutLabel").html(languageData.logout);
	$("#allMediaLabel").html(languageData.allMedia);
	$("#movieLabel").html(languageData.movie);
	$("#bookLabel").html(languageData.book);

	$("#movieName").html(languageData.movieName);
	$("#year").html(languageData.year);
	$("#movierating").html(languageData.rating);
	$("#movielength").html(languageData.length);
	$("#genre").html(languageData.genre);
	$("#director").html(languageData.director);
	$("#actors").html(languageData.actors);
	$("#writers").html(languageData.writers);

	$("#movieNameLabel").html(languageData.movieName);
	$(".rowMovieName .property-label").html(languageData.movieName);
	$(".rowMovieRating .property-label").html(languageData.rating);
	$(".rowMovieGenres .property-label").html(languageData.genres);
	$(".rowMovieDirector .property-label").html(languageData.director);
	$("#yearLabel").html(languageData.year);
	$("#ratingLabel").html(languageData.rating);
	$("#lengthLabel").html(languageData.length);
	$("#genresLabel").html(languageData.genres);
	$("#directorLabel").html(languageData.director);
	$("#actorsLabel").html(languageData.actors);
	$("#writersLabel").html(languageData.writers);
	$("#pathLabel").html(languageData.path);
	$("#plotLabel").html(languageData.plot);
	$("#getMovieData").html(languageData.getMediaData);
	$("#submitForm").html(languageData.save);
	$("#closeLabel").html(languageData.close);

	$("#changeLanguage").html(languageData.changeLanguage);
	$("#englishLabel").html(languageData.english);
	$("#frenchLabel").html(languageData.french);
}



function changeLanguage() {
	if (languageData == null) {
		var languageValue = $("#languageChange").val();
		$.get("ResourceBundle", {
			language : languageValue
		}, function(data) {
			languageData = data;
			reloadLabels();
		});
	} else {
		reloadLabels();
	}
}


$(window).load(function() {
	console.log("Binding done!!");
	changeLanguage();

});






$(document).ready(function() {
	$.get("MovieRow.html", function(html) {
		movieRowHtml = html;
	});
	$("#languageChange").click(function() {
		languageData = null;
		changeLanguage();
	});
});