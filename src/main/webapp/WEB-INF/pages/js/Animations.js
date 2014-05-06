var uploadForm;
function move(length) {
	$(".movable-pane").stop().animate({
		marginLeft : length
	}, 1600);
}

function moveImport() {
	move("-" + screen.width + "px");
}

$(document).ready(function() {

	$("#homeLabel").click(function() {
		move("0px");
	});

	/* Move about */
	$("#importLabel").click(function() {
		if (uploadForm == null) {
			$.get("MediaImport.html", function(uploadForm) {
				$("#import").html(uploadForm);
			}).done(function() {
				moveImport();
			});
		} else {
			moveImport();
		}

	});

});
