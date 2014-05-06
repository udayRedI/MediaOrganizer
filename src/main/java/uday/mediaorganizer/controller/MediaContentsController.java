package uday.mediaorganizer.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import uday.mediaorganizer.model.domainmodel.Media;
import uday.mediaorganizer.model.domainmodel.MediaTypeDetails;
import uday.mediaorganizer.model.domainmodel.Movie;
import uday.service.MediaServiceImpl;

@Controller
public class MediaContentsController {

	@Autowired
	MediaServiceImpl mediaService;

	private HashMap<String, Object> getFailureMap(
			HashMap<String, Object> returnMap, String failureMessage) {
		returnMap.put("Result", "Failure");
		returnMap.put("Message", failureMessage);

		return returnMap;
	}

	private HashMap<String, Object> getSearchMediaSuccessMap(
			HashMap<String,Object> mediaList, HashMap<String, Object> returnMap,
			String message) {
		returnMap.put("Result", "Success");
		returnMap.put("Message", message);
		returnMap.put("Data", mediaList);

		return returnMap;
	}

	private HashMap<String, Object> getMediaCountSuccessMap(
			int mediaCount, HashMap<String, Object> returnMap,
			String message) {
		returnMap.put("Result", "Success");
		returnMap.put("Message", message);
		returnMap.put("Data", mediaCount);

		return returnMap;
	}

	private HashMap<String, Object> getUpdatedMovieDetails(String movieName) {
		MediaTypeDetails mediaTypeDetails = new MediaTypeDetails("Movie",
				"Name", movieName);
		HashMap<String, Object> movieMap = getMediaContents(mediaTypeDetails,
				1, 1);

		return movieMap;

	}

	private HashMap<String, Object> getUpdateMediaSuccessMap(
			HashMap<String, Object> returnMap, String mediaName) {
		returnMap.put("Result", "Success");
		returnMap.put("Message", mediaName + " Updates Successfully");
		returnMap.put("Data", getUpdatedMovieDetails(mediaName));

		return returnMap;
	}

	private HashMap<String, Object> getExceptionMap(Exception e,
			HashMap<String, Object> returnMap) {
		returnMap.put("Result", "Error");
		returnMap.put("Message", "Encountered error in system");

		return returnMap;
	}

	private Boolean checkIfNotValidInput(MediaTypeDetails mediaTypeDetails) {

		if(!mediaTypeDetails.isNotNull()){
			return true;
		}
		if (mediaTypeDetails.getMediaType().equals("AllMedia")) {
			if (mediaTypeDetails.getMediaTypeProperty().equals("Name")) {
				return false;
			} else if (mediaTypeDetails.getMediaTypeProperty().equals("Rating")) {
				return false;
			} else if (mediaTypeDetails.getMediaTypeProperty().equals("Year")) {
				return false;
			} else if (mediaTypeDetails.getMediaTypeProperty().equals("Genres")) {
				return false;
			}
		} else if (mediaTypeDetails.getMediaType().equals("Movie")) {
			if (mediaTypeDetails.getMediaTypeProperty().equals("Name")) {
				return false;
			} else if (mediaTypeDetails.getMediaTypeProperty().equals("Rating")) {
				return false;
			} else if (mediaTypeDetails.getMediaTypeProperty().equals("Year")) {
				return false;
			} else if (mediaTypeDetails.getMediaTypeProperty()
					.equals("Runtime")) {
				return false;
			} else if (mediaTypeDetails.getMediaTypeProperty().equals("Genres")) {
				return false;
			} else if (mediaTypeDetails.getMediaTypeProperty().equals("Actors")) {
				return false;
			} else if (mediaTypeDetails.getMediaTypeProperty().equals(
					"Director")) {
				return false;
			} else if (mediaTypeDetails.getMediaTypeProperty()
					.equals("Writers")) {
				return false;
			} else {
				return true;
			}
		}
		return true;

	}

	@RequestMapping(value = "/SearchMedia", method = RequestMethod.GET)
	public @ResponseBody
	HashMap<String, Object> searchMedia(MediaTypeDetails mediaTypeDetails) {

		HashMap<String, Object> returnMap = new HashMap<String, Object>();

		if (checkIfNotValidInput(mediaTypeDetails)) {
			returnMap = getFailureMap(returnMap, "Invalid Input");
		} else {
			HashMap<String,Object> mediaSearchMap = null;
			try {
				mediaSearchMap = mediaService.searchMedia(mediaTypeDetails);
				if (((List<String>) mediaSearchMap.get("mediaList")).size() == 0) {
					returnMap = getFailureMap(returnMap, "No Media Found");
				} else {
					returnMap = getSearchMediaSuccessMap(mediaSearchMap, returnMap,
							"Media Found");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				returnMap = getExceptionMap(e, returnMap);
			} catch (RuntimeException e) {
				e.printStackTrace();
				returnMap = getExceptionMap(e, returnMap);
			}

		}
		return returnMap;
	}

	
	@RequestMapping(value = "/MediaContents", method = RequestMethod.GET)
	public @ResponseBody
	HashMap<String, Object> getMediaContents(MediaTypeDetails mediaTypeDetails,
			int pageNo, int interval) {

		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		System.out.println("Here");
		if (checkIfNotValidInput(mediaTypeDetails)) {
			returnMap = getFailureMap(returnMap, "Invalid Input");
		} else {
			HashMap<String,Object> mediaSearchMap = null;
			try {
				int startAt = (pageNo - 1) * interval;
				mediaSearchMap = mediaService.getMediaList(mediaTypeDetails,
						startAt, interval);
				if (mediaSearchMap.size() == 0) {
					returnMap = getFailureMap(returnMap, "No Media Found");
				} else {
					returnMap = getSearchMediaSuccessMap(mediaSearchMap, returnMap,
							"Media Found");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				returnMap = getExceptionMap(e, returnMap);
			} catch (RuntimeException e) {
				e.printStackTrace();
				returnMap = getExceptionMap(e, returnMap);
			}

		}
		return returnMap;
	}

	@RequestMapping(value = "/EditMovieContents", method = RequestMethod.GET)
	public @ResponseBody
	HashMap<String, Object> editMediaContents(Movie movie) {

		HashMap<String, Object> returnMap = new HashMap<String, Object>();

		try {
			int rowsUpdated = mediaService.edit(movie);
			if (rowsUpdated == 0) {
				returnMap = getFailureMap(returnMap, "No Rows Updated");
			} else {
				returnMap = getUpdateMediaSuccessMap(returnMap, movie.getName());
			}
		} catch (SQLException e) {
			returnMap = getExceptionMap(e, returnMap);
		}
		return returnMap;
	}

//	@RequestMapping(value = "/EditMovie", method = RequestMethod.GET)
//	public String editMovie() {
//		return "EditMovie.html";
//	}

//	@RequestMapping(value = "/MovieRow", method = RequestMethod.GET)
//	public String movieRow() {
//		return "MovieRow.html";
//	}

}
