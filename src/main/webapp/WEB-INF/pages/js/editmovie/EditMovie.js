EditMovie = function(submitForm, getMovieData, movieDetails){
	
	var _this = this;
	this._movieDetails = movieDetails;

	this.init = function(){
		$("#edit_modal").html(editMovieForm);
		this._loadEditForm();
		this._bindEditForm();			
	}
	this.changeDetails = function(movieDetails){
		this._movieDetails = movieDetails;
		this._loadEditForm();
	}
	
	this._loadEditForm = function() {
		$("#headerMovieName").html(this._movieDetails.name);
		$("#editFormMovieId").val(this._movieDetails.id);
		$("#editFormMovieName").val(this._movieDetails.name);
		$("#editFormMovieYear").val(this._movieDetails.year);
		$("#editFormMovieRating").val(this._movieDetails.rating);
		$("#editFormMovieLength").val(this._movieDetails.length);
		$("#editFormMovieGenres").val(this._movieDetails.genres);
		$("#editFormMovieDirector").val(this._movieDetails.director);
		$("#editFormMovieWriters").val(this._movieDetails.writers);
		$("#editFormMovieActors").val(this._movieDetails.actors);
		$("#editFormMoviePath").val(this._movieDetails.path);
		$("#editFormMoviePlot").val(this._movieDetails.plot);
	}

	this._bindEditForm = function() {
		console.log("Binding Form");
		
		$("#getMovieData").bind("click", function(event){
			getMovieData(this, event, _this._movieDetails);
		});
		
		$("#submitForm").bind("click", function(event){
			submitForm(this, event);
		})
	}

}
