package uday.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import uday.mediaorganizer.model.domainmodel.Media;
import uday.mediaorganizer.model.domainmodel.MediaTypeDetails;
import uday.mediaorganizer.model.domainmodel.Movie;

public interface MediaService {

	HashMap<String, Object> searchMedia(MediaTypeDetails mediaTypeDetails) throws SQLException;
	
	HashMap<String, Object> getMediaList(MediaTypeDetails mediaTypeDetails,
			int limit, int startAt) throws SQLException;

	int edit(Movie movie) throws SQLException;

	int insertMedia(MediaTypeDetails mediaTypeDetails,
			MultipartFile multipartFile) throws SQLException,
			MySQLIntegrityConstraintViolationException;
}