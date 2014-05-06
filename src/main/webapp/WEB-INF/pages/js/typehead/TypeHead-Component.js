TypeaheadComponent = function(setSearchedValue){
    $("#search").typeahead({
        remote:{
        	url:'SearchMedia?mediaType=%MEDIATYPE&mediaTypeProperty=%SEARCHBY&media=%MEDIAVALUE',
        	replace: function(url, mediaValue) {
        	      var mediaType = encodeURIComponent($("#media-type").val());
        	      var searchBy = encodeURIComponent($("#search-by").val());

        	      return url.replace('%MEDIATYPE', mediaType).replace('%SEARCHBY', searchBy).replace("%MEDIAVALUE",mediaValue);
        	    },
        	filter: function(data) {
                mediaDetailsList = [];
                mediaList = data.Data.mediaList;
                for( i=0; i< mediaList.length ; i++){
                    mediaDetailsList.push(mediaList[i]);
                }
                return mediaDetailsList;
            }
        }
    }).bind('typeahead:selected', function(event, item){
        setSearchedValue(item.value);
    });
}
