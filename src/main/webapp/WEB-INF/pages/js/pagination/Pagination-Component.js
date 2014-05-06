PaginationComponent = function(addMedia, setMediaCount, setPageNo) {

	var _this = this;
	this._searchedValue;
	this._searchedValue;
	
	
	this._paginatorSetup = function(currentPage, pagesRequired, data) {
		

		$('#pagination').bootstrapPaginator({
			currentPage : currentPage,
			totalPages : pagesRequired,
			itemContainerClass : function(type, page, current) {
				return (page === current) ? "active" : "pointer-cursor";
			},
			onPageClicked : function(e, originalEvent, type, pageNo) {
				setPageNo(pageNo);	            
	        }
	    });
		addMedia(data.Data.mediaList);
	}
	
	this._initialize = function(searchController){
		this._searchedValue = $("#search").val();

		var mediaType = $("#media-type").val();
		var mediaTypeProperty = $("#search-by").val();
		var media = this._searchedValue;
		$.get("MediaContents", {
			mediaType : mediaType,
			mediaTypeProperty : mediaTypeProperty,
			media : media,
			interval : $("#resultsPerPage").val(),
			pageNo : 1
		}, function(data) {
			var mediaCount = data.Data.mediaCount;
			setMediaCount(mediaCount);
			var resultsPerPage = $("#resultsPerPage").val();
			var pagesRequired = Math.ceil(mediaCount / resultsPerPage);
			currentPage = 1;
			_this._paginatorSetup(currentPage, pagesRequired, data);
		});
	}
		
	
	this.reInitialize = function(){
	    this._initialize();
	}
	
    this._initialize();
}