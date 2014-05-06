ResultsPerPage = function(searchController){

	var _this = this;
	this._searchController = searchController;
	
	$("#resultsPerPage").change(function(){
		_this._searchController.changeResultsPerPage(this.value);
	});
}
	
