<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="css/fileupload/jquery.fileupload.css">
<link href="css/sticky-footer-navbar.css" rel="stylesheet"
	media="screen">
<link href="css/typeahead.css" rel="stylesheet"
	media="screen">
<link rel="stylesheet" href="css/ladda/ladda-themeless.min.css">
<link href="css/MainStyle.css" rel="stylesheet" media="screen">
</head>
<body>
	<div id="wrap">
		<!-- Fixed navbar -->
		<div class="navbar">
			<div class="container">

				<div class="nav-collapse collapse navbar-responsive-collapse">
					<div class="nav-collapse collapse">
						<ul class="nav navbar-nav">
							<li><a class="navbar-brand" id="titleLabel" href="#"></a></li>
							<li><a href="#pane1" id="homeLabel" data-toggle="tab"></a></li>
							<li><a href="#pane2" id="importLabel" data-toggle="tab"></a></li>
						</ul>
					</div>
					<!--/.nav-collapse -->
					<ul class="nav navbar-nav pull-right">
						<li><a href="#">${username}</a></li>
						<li><a href="logout" id="logoutLabel"></a></li>
					</ul>
				</div>
			</div>
		</div>

		<!-- Begin page content -->
		<div class="container">
			<div class="content_wrapper">
				<div class="movable-pane">
					<div id="home" class="movable-element">
						<div class="media_list list-group">
							<div href="#" class="list-group-item">
								<div>
									<div class="row">
										<div class="col-4">
											<input type="text" autocomplete="off" class="typeahead form-control"
												placeholder="Enter the media details" id="search"
												data-provide="typeahead">
										</div>
										<div class="col-2">
											<select id="search-by" class="form-control">
												<option value="Name">Search by</option>
												<option value="Name">Movie Name</option>
												<option value="Actors">Actors</option>
												<option value="Genres">Genre</option>
												<option value="Director">Director</option>
												<option value="Rating">Movie Rating</option>
												<option value="Writers">Writers</option>
												<option value="year">Year</option>
												<option value="Runtime">Movie Length</option>
											</select>
										</div>
										<div class="col-2 ">
											<select id="resultsPerPage" class="form-control">
												<option value="5">Results(5)</option>
												<option value="10">10</option>
												<option value="15">15</option>
												<option value="20">20</option>
												<option value="25">25</option>
												<option value="30">30</option>
												<option value="35">35</option>
												<option value="40">40</option>
												<option value="45">45</option>
												<option value="50">50</option>
											</select>
										</div>
										<div class="col-2 ">
											<select id="media-type" class="form-control">
												<option value="AllMedia" id="allMediaLabel"></option>
												<option value="Movie" id="movieLabel"></option>
												<option value="Book" id="bookLabel"></option>
											</select>
										</div>
										<div class="col-2"></div>
										<div class="pull-right">
											<input type="button" value="search" class="typeahead tt-query form-control" id="searchButton">
										</div>
									</div>
								</div>
							</div>

						</div>
						<div id="pagination"></div>
					</div>
					<div id="import"  class="movable-element">
					</div>
				</div>
			</div>
		</div>
	</div>




	<div id="footer">
		<div id="wrapper" class="container-fluid">
			<div class="row-fluid">
				<form id="frmOptions" method="post" class="form-inline">
					<div class="row-fluid">
						<div id="formLeft" class=" span4">
							<div class="pull-right">
								<div class="control-group">
									<div class="controls">
										<label for="select1" id="changeLanguage" class="control-label"></label>
										<select id="languageChange">
											<option id="englishLabel" value="en"></option>
											<option id="frenchLabel" value="fr"></option>
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal" id="edit_modal"></div>

	<script src="js/jquery-1.10.2.js"></script>
	<script
		src="js/bootstrapmodal/bootstrap-modal.js"></script>
	<script src="js/fileupload/jquery.ui.widget.js"></script>
	<script src="js/fileupload/jquery.iframe-transport.js"></script>
	<script src="js/fileupload/jquery.fileupload.js"></script>
	<script src="js/typeahead.js"></script>
	<script src="js/ladda/spin.min.js"></script>
	<script src="js/ladda/ladda.min.js"></script>
	<script src="js/pagination/bootstrap-paginator.js"></script>
	<script src="js/Global.js"></script>

	<script src="js/typehead/TypeHead-Component.js"></script>
	<script src="js/resultsperpage/ResultsPerPage-Component.js"></script>
	<script src="js/pagination/Pagination-Component.js"></script>
	<script src="js/createmediarows/CreateMediaRows-Component.js"></script>
	<script src="js/searchbutton/SearchButton.js"></script>
	
	<script src="js/searchcomponent/SearchController.js"></script>
	<script src="js/searchcomponent/SearchModel.js"></script>
	<script src="js/editmovie/EditMovie.js"></script>
	<script src="js/ControllerInstantiate.js"></script>
	
	<script src="js/Animations.js"></script>
	<script src="js/Navigation.js"></script>
	<script type="text/javascript">
		var userName = "${username}";
		//console.log(userName);
	</script>
</body>
</html>