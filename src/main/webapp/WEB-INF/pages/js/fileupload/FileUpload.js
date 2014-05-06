$(function() {
	'use strict';

	var url = 'fileUpload';

//	$('#fileupload')
//			.fileupload(
//					{
//						url : url,
//						cache : false,
//						contentType : false,
//						processData : false,
//			            data: JSON.stringify({'Content-Disposition': 'form-data', 'name': "files[0]", 'filename': "Aadavariki.mp4",
//			            	'Content-Type': "video/mp4"}),
//						done : function(e, data) {
//							console.log("Done is here");
//							console.log(data);
//							$.each(data.result.files, function(index, file) {
//								$('<li/>').text(file.name).appendTo('#files');
//							});
//
//						},
//						progressall : function(e, data) {
//							console.log("Updating");
//							var progress = parseInt(data.loaded / data.total
//									* 100, 10);
//							$('#progress .bar').css('width', progress + '%');
//						}
//					}).prop('disabled', !$.support.fileInput).parent()
//			.addClass($.support.fileInput ? undefined : 'disabled');
//});
//$(document).ready(function() {
//    $('#addFile').click(function() {
//        var fileIndex = $('#fileTable tr').children().length - 1;
//        $('#fileTable').append(
//                '<tr><td>'+
//                '   <input type="file" name="files['+ fileIndex +']" />'+
//                '</td></tr>');
//    });
//    $("#uploadForm").submit(function(event){
//    	$.ajax({
//            type: 'POST',
//            url: 'save.html',
//            contentType: 'application/json; charset=utf-8',
//            dataType: 'json',
//            data: JSON.stringify({'Content-Disposition': 'form-data', 'name': "files[0]", 'filename': "Aadavariki.mp4",
//        },function(data){
//        	alert(data);
//        	console.log(data);
//        });
//        console.log("Here");
//        event.preventDefault();
//    });
    
});
