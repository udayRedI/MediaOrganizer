SearchModel = function(searchController) {

	var _this = this;

	this._searchedValue;
	this._mediaList = new Array(),
	this._mediaCount = 0,
	this._pageNo = 1,
	this._resultsPerPage = 5;
	this._searchController = searchController;

	this.setSearchedValue = function(searchedValue) {
		this._searchedValue = searchedValue;
	}

	this.getSearchedValue = function() {
		return this._searchedValue;
	}

	this.getMediaList = function() {
		return this._mediaList;
	}

	this._setMediaList = function(mediaList) {
		this._mediaList.length = 0;
		$.each(mediaList, function(mediaIndex, media) {
				_this._mediaList.push(media);
		});
	}

	this.addMedia = function(mediaList) {
		this._setMediaList(mediaList);
	}

	this.getMediaCount = function() {
		return this._mediaCount;
	}

	this.setMediaCount = function(mediaCount) {
		this._mediaCount = mediaCount;
	}

	this.getPageNo = function() {
		return this._pageNo;
	}

	this.setPageNo = function(pageNo) {
		this._pageNo = pageNo;
	}


	this.setResultsPerPage = function(resultsPerPage) {
		this._resultsPerPage = resultsPerPage;
	}

	this.editMedia = function(mediaId, editedMedia) {
		$.each(this._mediaList, function(mediaIndex, media) {
			if (media.id == mediaId) {
				_this._mediaList[mediaIndex] = editedMedia;
				return false;
			}
		});
	}

	this.getResultsPerPage = function() {
		return this._resultsPerPage;
	}
}