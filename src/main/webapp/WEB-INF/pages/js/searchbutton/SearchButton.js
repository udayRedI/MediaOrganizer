SearchButton = function(setSearchedValue){

	var _this = this;
	this._searchController = searchController;
	
	$("#searchButton").click(function(){
		setSearchedValue($("#search").val());
	});
}
	
