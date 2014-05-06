package uday.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import uday.mediaorganizer.model.domainmodel.Media;
import uday.mediaorganizer.model.domainmodel.MediaTypeDetails;
import uday.mediaorganizer.model.domainmodel.Movie;
import uday.service.mapper.mediacount.MediaCountResultSetExtractor;
import uday.service.mapper.medialist.MediaListRowMapper;
import uday.service.mapper.searchmedia.SearchMediaRowMapper;

@Service
@Transactional
public class MediaServiceImpl implements MediaService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Resource(name = "ApplicationVariables")
	Map<String, String> applicationVariables;

	@Resource(name = "SelectQueryFactory")
	Map<String, String> selectQueryFactory;

	@Resource(name = "MediaListQueryFactory")
	Map<String, String> mediaListQueryFactory;

	@Override
	public HashMap<String, Object> searchMedia(MediaTypeDetails mediaTypeDetails)
			throws SQLException {
		HashMap<String, Object> mediaSearchMap = new HashMap<String, Object>();
		if (mediaTypeDetails != null
				&& (mediaTypeDetails.getMediaType().equals(
						applicationVariables.get("allMedia")) || mediaTypeDetails
						.getMediaType().equals(
								applicationVariables.get("movie")))) {
			String query = "select " + mediaTypeDetails.getMediaTypeProperty()
					+ " from Movie  where ";
			mediaSearchMap = searchMovie(mediaTypeDetails, query, 0, 5);
		}

		return mediaSearchMap;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	private HashMap<String, Object> searchMovie(
			MediaTypeDetails mediaTypeDetails, String query, int startAt,
			int interval) throws SQLException {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		List<String> mediaList = null;
		if (mediaTypeDetails != null) {
			mediaList = jdbcTemplate.query(selectQueryFactory
					.get(mediaTypeDetails.getMediaTypeProperty()),
					new Object[] { mediaTypeDetails.getMedia() + "%", startAt,
							interval }, new SearchMediaRowMapper());
			returnMap.put("mediaList", mediaList);
		}

		return returnMap;
	}

	@Override
	public HashMap<String, Object> getMediaList(
			MediaTypeDetails mediaTypeDetails, int startAt, int interval)
			throws SQLException {

		HashMap<String, Object> mediaSearchMap = new HashMap<String, Object>();
		if (mediaTypeDetails != null
				&& (mediaTypeDetails.getMediaType().equals("AllMedia") || mediaTypeDetails
						.getMediaType().equals("Movie"))) {
			mediaSearchMap = getMovieList(mediaTypeDetails, startAt, interval);
		}

		return mediaSearchMap;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	private HashMap<String, Object> getMovieList(
			MediaTypeDetails mediaTypeDetails, int startAt, int interval)
			throws SQLException {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		List<Media> mediaList = null;
		int mediaCount;
		if (mediaTypeDetails != null) {
			mediaList = jdbcTemplate.query(
					mediaListQueryFactory.get("ListQuery")
							+ mediaListQueryFactory.get(mediaTypeDetails
									.getMediaTypeProperty()), new Object[] {
							mediaTypeDetails.getMedia(), startAt, interval },
					new MediaListRowMapper());
			returnMap.put("mediaList", mediaList);
			mediaCount = getMovieCount(mediaTypeDetails);
			returnMap.put("mediaCount", mediaCount);
		}

		return returnMap;
	}

	@Transactional(readOnly = true)
	private int getMovieCount(MediaTypeDetails mediaTypeDetails)
			throws SQLException {
		int mediaCount = 0;
		if (mediaTypeDetails != null) {
			mediaCount = (Integer) jdbcTemplate.query(
					"select count(*) from Movie where "
							+ mediaTypeDetails.getMediaTypeProperty()
							+ " like ?",
					new Object[] { "%" + mediaTypeDetails.getMedia() + "%" },
					new MediaCountResultSetExtractor());

		}
		return mediaCount;
	}

	@Override
	@Transactional(readOnly = false)
	public int edit(Movie movie) throws SQLException {
		int rowsUpdated = 0;
		if (movie != null) {

			rowsUpdated = jdbcTemplate
					.update("Update Movie set Name = ?, Year = ?, Genres = ?, Rating = ?, Runtime = ?, Director = ?, Writers = ?, Actors = ?, Plot = ?, MediaOrganizerFilePath = ?  where id = ? ",
							new Object[] { movie.getName(), movie.getYear(),
									movie.getGenres(), movie.getRating(),
									movie.getLength(), movie.getDirector(),
									movie.getWriters(), movie.getActors(),
									movie.getPlot(), movie.getPath(),
									movie.getId() });

		}
		return rowsUpdated;

	}

	private void createDestinationFolder(String mediaType, String mediaName) {
		String folderName = applicationVariables.get(mediaType + "Path")
				+ mediaName;
		new File(folderName).mkdir();
	}

	private File getDestinationFile(String mediaType, String mediaName,
			String mediaFileName) {
		createDestinationFolder(mediaType, mediaName);
		String destinationFile = applicationVariables.get(mediaType + "Path")
				+ mediaName + "/" + mediaFileName;
		return new File(destinationFile);
	}

	private void copyMediaContents(String mediaType, String mediaName,
			String mediaFileName, MultipartFile multipartFile)
			throws IllegalStateException, IOException {
		multipartFile.transferTo(getDestinationFile(mediaType, mediaName,
				mediaFileName));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int insertMedia(MediaTypeDetails mediaTypeDetails,
			MultipartFile multipartFile) throws SQLException,
			MySQLIntegrityConstraintViolationException {

		String mediaType = mediaTypeDetails.getMediaType();
		String mediaName = mediaTypeDetails.getMedia();
		String mediaFileName = multipartFile.getOriginalFilename();
		int insertStatus = 0;
		if (mediaType.equals(Movie.class.getSimpleName())) {
			try {
				insertStatus = insertMovie(mediaTypeDetails, mediaFileName);
				copyMediaContents(mediaType, mediaName, mediaFileName,
						multipartFile);
			} catch (SQLException e) {
				throw e;
			} catch (IllegalStateException e) {
				throw new SQLException(e);
			} catch (IOException e) {
				throw new SQLException(e);
			}
		}
		return insertStatus;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	private int insertMovie(MediaTypeDetails mediaTypeDetails,
			String mediaFileName) throws SQLException,
			MySQLIntegrityConstraintViolationException {
		String mediaType = mediaTypeDetails.getMediaType();
		String mediaName = mediaTypeDetails.getMedia();
		return jdbcTemplate
				.update("insert into Movie (Name,Year ,Genres ,Rating ,Runtime ,Director ,Writers ,Actors ,Plot ,Poster ,Type ,MediaOrganizerFilePath ) "
						+ "values(?,'','',0.0,'','','','','','','Movie','"
						+ applicationVariables.get(mediaType + "Path")
						+ mediaName + "')",
						new Object[] { mediaTypeDetails.getMedia() });
	}

}