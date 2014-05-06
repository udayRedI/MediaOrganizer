SearchController = function() {

	var _this = this;

	
	this._typeAhead;
	this._pagination;
	this._resultsPerPage;
	this._createMediaRows;
	this._searchButton;
	this._searchModel;
	this._editMovie;

	this.init = function() {
		this._typeAhead = new TypeaheadComponent(this.setSearchedValue);
		this._resultsPerPage = new ResultsPerPage(this);	
		this._searchButton = new SearchButton(this.setSearchedValue);
		this._searchModel = new SearchModel(this);
	}

	//Searched Value
	this.setSearchedValue = function(searchedValue) {
		_this._searchModel.setSearchedValue(searchedValue);
		if(!_this._pagination){
			_this._pagination = new PaginationComponent(_this.addMedia, _this.setMediaCount, _this.setPageNo);
		} else {
			_this._pagination.reInitialize();
		}
	}
	this.getSearchedValue = function() {
		return this._searchModel.getSearchedValue();
	}

	//Search Model
	this.getSearchModel = function() {
		return this._searchModel;
	}

	//Paginator
	this.addMedia = function(mediaList) {
		_this._searchModel.addMedia(mediaList);
		if (_this._createMediaRows == undefined) {
			_this._createMediaRows = new createMediaRows(
					_this._loadNewRows);
		} else {
			_this._loadNewRows();
		}
	}
	
	
	//View
	this._loadNewRows = function(){
		
		$(".media-row").remove();
		
		$.each(_this.getSearchModel()._mediaList, function(mediaIndex, media){
				_this._populateRowData(media);				
		});
		_this._bindEditImage();
	}

	this._populateRowData = function(media){
		html = $.parseHTML(movieRowHtml);
		$(html).attr("movie-id", media.id);
		$(html).find(".edit_image").attr("movie-id", media.id);
		$(html).find(".rowMovieName .value").text(media.name);
		$(html).find(".rowMovieRating .value").text(media.rating);
		$(html).find(".rowMovieGenres .value").text(media.genres);
		$(html).find(".rowMovieDirector .value").text(media.director);
		$(".media_list").append(html);
	}
	
	this._bindEditImage = function() {
		$(".edit_image").on("click", function(event) {
			_this.bindEditClick(event);
		});
	}
	
	
	this.setPageNo = function(pageNo) {
		_this.getSearchModel().setPageNo(pageNo);
		_this.pageChange(pageNo);
	}

	this.setMediaCount = function(mediaCount) {
		_this.getSearchModel().setMediaCount(mediaCount);
	}
	
	this.changeResultsPerPage = function(resultsPerPage) {
		this.getSearchModel().setResultsPerPage(resultsPerPage);
		this._pagination.reInitialize(this._searchController);
	}

	//Edit module
	this.editMedia = function(mediaId, editedMedia) {
		_this._searchModel.editMedia(mediaId, editedMedia);
		_this.reloadEditedMedia(editedMedia);
	}
	
	this.pageChange = function(pageNo) {
		var mediaType = $("#media-type").val();
		var mediaTypeProperty = $("#search-by").val();
		var media = $("#search").val();
		var resultsPerPage = $("#resultsPerPage").val();

		var currentResults = resultsPerPage * pageNo;
		var currentPageFirstIndex = resultsPerPage * (pageNo - 1);
		if (this.getSearchModel()._mediaList[currentPageFirstIndex] == undefined
				|| (this.getSearchModel()._mediaList.length < this.getSearchModel()._mediaCount)) {
			$.get("MediaContents", {
				mediaType : mediaType,
				mediaTypeProperty : mediaTypeProperty,
				media : media,
				interval : resultsPerPage,
				pageNo : pageNo
			}).done(function(returnData) {
				_this.addMedia(returnData.Data.mediaList, pageNo);
			});
		} else {
			_this._loadNewRows();
		}
	}
	
	

	this.bindEditClick = function(event) {
		var movieId = event.target.getAttribute("movie-id");
		var movieDetails;

		$.each(this.getSearchModel().getMediaList(),
				function(movieIndex, movie) {
					if (movie["id"] == movieId) {
						movieDetails = movie;
						return false;
					}
				});
		if (editMovieForm == null || editMovieForm == undefined) {
			$.get("EditMovie.html", function(editMovieFormHtml) {
				editMovieForm = editMovieFormHtml;
				_this._editMovie = new EditMovie(_this.submitEditForm, _this.getMovieData, movieDetails);
				_this._editMovie.init();

			});
		} else {
			_this._editMovie.changeDetails(movieDetails);
		}

	}
	
	this.reloadEditedMedia = function(movieObject) {
		$.each($("a.media-row.list-group-item"), function(aIndex, element) {
			if (element.getAttribute("movie-id") == movieObject.id) {
				$(element).find(".rowMovieName .value").text(movieObject.name);
				$(element).find(".rowMovieRating .value").text(
						movieObject.rating);
				$(element).find(".rowMovieGenres .value").text(
						movieObject.genres);
				$(element).find(".rowMovieDirector .value").text(
						movieObject.director);
			}
		});
	}
	
	this.submitEditForm = function(editObject, event){
		event.preventDefault();
		var ladda = Ladda.create(editObject);
		ladda.start();
		var movieId = $("#editFormMovieId").val();
		var movieName = $("#editFormMovieName").val();
		var year = $("#editFormMovieYear").val();
		var rating = $("#editFormMovieRating").val();
		var length = $("#editFormMovieLength").val();
		var genres = $("#editFormMovieGenres").val();
		var director = $("#editFormMovieDirector").val();
		var writers = $("#editFormMovieWriters").val();
		var actors = $("#editFormMovieActors").val();
		var path = $("#editFormMoviePath").val();
		var plot = $("#editFormMoviePlot").val();
		$.get("EditMovieContents", {
			id : movieId,
			name : movieName,
			year : year,
			rating : rating,
			length : length,
			genres : genres,
			director : director,
			writers : writers,
			actors : actors,
			path : path,
			plot : plot
		}, function(data) {
			ladda.stop();
			_this._popupStatusMessage(data);
			var editedMovieList = data.Data.Data.mediaList[0];
			_this.editMedia(movieId, editedMovieList);
			$('#edit_modal').modal('hide');
			console.log("Success1");
		});
	}

	this._popupStatusMessage = function(data) {
		var updateStatus = data.Result;
		var selectStatus = data.Data.Result;

		if (selectStatus == "Success") {
			alert(updateStatus);
		} else {
			alert(updateStatus + ".\n" + data.Data.Message);
		}
	}
	
	this.getMovieData = function(editObject, event, movieDetails){
		event.preventDefault();
		if(navigator.onLine){
			var ladda = Ladda.create(editObject);
			ladda.start();
			var movieName = movieDetails.name;
			$.getJSON("http://www.imdbapi.com/", {
				t : movieName
			}, function(movie) {
				if(movie.Response == true){
					$("#editFormMovieName").val(movie.Title);
					$("#editFormMovieYear").val(movie.Year);
					$("#editFormMovieRating").val(movie.imdbRating);
					$("#editFormMovieLength").val(movie.Runtime);
					$("#editFormMovieGenres").val(movie.Genre);
					$("#editFormMovieDirector").val(movie.Director);
					$("#editFormMovieWriters").val(movie.Writer);
					$("#editFormMovieActors").val(movie.Actors);
					$("#editFormMoviePlot").val(movie.Plot);	
				} else {
					alert(movieName+" information Not avaliable in IMDB Please enter it manually");
				}
				
				ladda.stop();
			});	
		} else{
			alert("Please check the internet connection");
		}
	}
}
