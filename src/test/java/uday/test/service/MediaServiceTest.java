package uday.test.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import uday.mediaorganizer.model.domainmodel.Media;
import uday.mediaorganizer.model.domainmodel.MediaTypeDetails;
import uday.mediaorganizer.model.domainmodel.Movie;
import uday.service.MediaService;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
//@Transactional
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class MediaServiceTest {

	@Autowired
	private MediaService mediaService;

	@Resource(name="FileTypeMap")
	Map<String, String> fileTypeMap;


	@SuppressWarnings("unchecked")
	private String getMovieName(HashMap<String,Object> mediaSearchMap){
		return ((Movie) ((List<Media>) mediaSearchMap.get("mediaList")).get(0)).getName();
	}

	
	//Search tests
	@Test
	@DatabaseSetup("SampleData.xml")
	@DatabaseTearDown("RestoreData.xml")
	public void testSearchNotNullInput() {
		MediaTypeDetails mediaTypeDetails = new MediaTypeDetails("Movie",
				"Name", "Iron Man");
		HashMap<String,Object> mediaSearchMap = new HashMap<String,Object>();
		try {
			mediaSearchMap = mediaService.searchMedia(mediaTypeDetails);
		} catch (SQLException e) {
			assert (false);
		}
		assertEquals(1, ((List<String>)mediaSearchMap.get("mediaList")).size());
	}

	//@Test
	@DatabaseSetup("SampleData.xml")
	@DatabaseTearDown("RestoreData.xml")
	public void testSearchInvalidMovie() {
		MediaTypeDetails mediaTypeDetails = new MediaTypeDetails("Movie",
				"Name", "I am the legend");
		HashMap<String,Object> mediaSearchMap = new HashMap<String,Object>();
		try {
			mediaSearchMap = mediaService.getMediaList(mediaTypeDetails, 0, 2);
		} catch (SQLException e) {
			assert (false);
		}
		assertEquals(0, ((List<Media>) mediaSearchMap.get("mediaList")).size());
	}

	//Media Contents
	//@Test
	@DatabaseSetup("SampleData.xml")
	@DatabaseTearDown("RestoreData.xml")
	public void testSelectNotNullInput() {
		MediaTypeDetails mediaTypeDetails = new MediaTypeDetails("Movie",
				"Name", "Iron Man");
		HashMap<String,Object> mediaSearchMap = new HashMap<String,Object>();
		try {
			mediaSearchMap = mediaService.getMediaList(mediaTypeDetails, 0, 2);
		} catch (SQLException e) {
			assert (false);
		}
		String movieName = getMovieName(mediaSearchMap);
		assertEquals(1, mediaSearchMap.get("mediaCount"));
		assertEquals("Iron Man", (movieName));
	}

	//@Test
	@DatabaseSetup("SampleData.xml")
	@DatabaseTearDown("RestoreData.xml")
	public void testSelectNullInput() {
		MediaTypeDetails mediaTypeDetails = null;
		HashMap<String,Object> mediaSearchMap = new HashMap<String,Object>();
		try {
			mediaSearchMap = mediaService.getMediaList(mediaTypeDetails, 0, 2);
		} catch (SQLException e) {
			assert (false);
		}
		assertEquals(null, mediaSearchMap.get("mediaCount"));
	}
	
	//@Test
	@DatabaseSetup("SampleData.xml")
	@DatabaseTearDown("RestoreData.xml")
	public void testSelectInvalidMovie() {
		MediaTypeDetails mediaTypeDetails = new MediaTypeDetails("Movie",
				"Name", "I am the legend");
		HashMap<String,Object> mediaSearchMap = new HashMap<String,Object>();
		try {
			mediaSearchMap = mediaService.getMediaList(mediaTypeDetails, 0, 2);
		} catch (SQLException e) {
			assert (false);
		}
		assertEquals(0, mediaSearchMap.get("mediaCount"));
	}

	
	@SuppressWarnings("unchecked")
	private int getMediaListSize(HashMap<String,Object> mediaSearchMap){
		return ((List<Media>)mediaSearchMap.get("mediaList")).size();
	}
	
	//@Test
	public void testOverFlowPagination(){
		MediaTypeDetails mediaTypeDetails = new MediaTypeDetails("Movie",
				"Genres", "Action");
		HashMap<String,Object> mediaSearchMap = new HashMap<String,Object>();
		try {
			mediaSearchMap = mediaService.getMediaList(mediaTypeDetails, 0, 10);
			assertEquals(10, getMediaListSize(mediaSearchMap));
			mediaSearchMap = mediaService.getMediaList(mediaTypeDetails, 10, 10);
			assertEquals(3, getMediaListSize(mediaSearchMap));
		} catch (SQLException e) {
			assert (false);
		}
	}

	//@Test
	public void testExactPagination(){
		MediaTypeDetails mediaTypeDetails = new MediaTypeDetails("Movie",
				"Genres", "Sci-Fi");
		HashMap<String,Object> mediaSearchMap = new HashMap<String,Object>();
		try {
			mediaSearchMap = mediaService.getMediaList(mediaTypeDetails, 0, 10);
			assertEquals(10, mediaSearchMap.get("mediaCount"));
		} catch (SQLException e) {
			assert (false);
		}
		
	}
	
	//@Test
	public void testUnderFlowPagination(){
		MediaTypeDetails mediaTypeDetails = new MediaTypeDetails("Movie",
				"Actors", "Robert");
		HashMap<String,Object> mediaSearchMap = new HashMap<String,Object>();
		try {
			mediaSearchMap = mediaService.getMediaList(mediaTypeDetails, 0, 10);
			assertEquals(9, mediaSearchMap.get("mediaCount"));
		} catch (SQLException e) {
			assert (false);
		}
		
	}

	//@Test
	@DatabaseSetup("SampleData.xml")
	@DatabaseTearDown("RestoreData.xml")
	public void testEditNotNullInput() {
		Movie movie = new Movie(72, "Iron Man", "2012", "", 0.0, "", "", "",
				"", "", "", "/home/uday/MediaOrganizer/Movies/Iron Man");
		int rowsUpdated = 0;
		try {
			rowsUpdated = mediaService.edit(movie);
		} catch (SQLException e) {
			assert (false);
		}
		assertEquals(1, rowsUpdated);
	}
	
	//@Test
	@DatabaseSetup("SampleData.xml")
	@DatabaseTearDown("RestoreData.xml")
	public void testEditNullInput() {
		Movie movie = null;
		int rowsUpdated = 0;
		try {
			rowsUpdated = mediaService.edit(movie);
		} catch (SQLException e) {
			assert (false);
		}
		assertEquals(0, rowsUpdated);
	}
	
	//@Test
	@DatabaseSetup("SampleData.xml")
	@ExpectedDatabase(value="SampleData.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	@DatabaseTearDown("RestoreData.xml")
	public void testEditInvalidMovie() {
		Movie movie = new Movie(73, "Batman", "2012", "", 0.0, "", "", "",
				"", "", "", "/home/uday/MediaOrganizer/Movies/Iron Man");
		int rowsUpdated = 0;
		try {
			rowsUpdated = mediaService.edit(movie);
		} catch (SQLException e) {
			assert (false);
		}
		assertEquals(0,rowsUpdated);
	}

	//@Test
	@DatabaseSetup("SampleData.xml")
	@ExpectedDatabase(value="ExpectedData.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	@DatabaseTearDown("RestoreData.xml")
	public void testInsert() throws InterruptedException {
		MediaTypeDetails mediaTypeDetails = new MediaTypeDetails("Movie",
				"Name", "Aadavariki");
		File videoFile = new File("/home/uday_reddy/Downloads/Aadavariki.mp4");
		MockMultipartFile multipartFile = null;
		try {
			multipartFile = new MockMultipartFile("Aadavariki.mp4", "Aadavariki.mp4", "video/mp4", new FileInputStream(videoFile));
		} catch (FileNotFoundException e1) {
			assert(false);
			e1.printStackTrace();
		} catch (IOException e1) {
			assert(false);
			e1.printStackTrace();
		}
		
		int insertedStatus = 0;
		try {
			insertedStatus = mediaService.insertMedia(mediaTypeDetails, multipartFile);
		} catch (SQLException e) {
			assert (false);
		} 
		assertEquals(1, insertedStatus);
	}

}