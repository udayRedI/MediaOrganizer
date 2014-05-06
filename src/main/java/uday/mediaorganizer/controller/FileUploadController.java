package uday.mediaorganizer.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import uday.mediaorganizer.model.domainmodel.FileUpload;
import uday.mediaorganizer.model.domainmodel.MediaTypeDetails;
import uday.mediaorganizer.validator.FileUploadValidator;
import uday.service.MediaService;

@SuppressWarnings("deprecation")
@Controller
public class FileUploadController {

	@Autowired
	private FileUploadValidator fileValidator;
	
	
	
	private static boolean stopUpload=false;
	
	@Autowired
	private MediaService mediaService;

	@Resource(name="FileTypeMap")
	Map<String, String> fileTypeMap;
	
	@Resource(name="ReturnStatusVariables")
	Map<String, String> returnStatusMap;
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
    public String displayForm() {
        return "FileUploadForm.jsp";
    }
     
	private String getFileType(String mediaType){
		return fileTypeMap.get(mediaType.split("/")[0]);
	}

	private String removeFileExtension(String mediaName){
		int dotIndex = mediaName.lastIndexOf(".");
		
		return mediaName.substring(0, dotIndex);
	}
	
	private HashMap<String, Object> getExceptionMap(
			HashMap<String, Object> returnMap, String failureMessage, String mediaName) {
		returnMap.put("Result", "Failure");
		returnMap.put("Message", failureMessage);
		returnMap.put("MediaName", mediaName);

		return returnMap;
	}
	
	private HashMap<String, Object> getStatusMap(
			HashMap<String, Object> returnMap, int status, String mediaName) {
		returnMap.put("Status", returnStatusMap.get(Integer.toString(status)));
		returnMap.put("Message", returnStatusMap.get((status+"Message").toString()));
		returnMap.put("MediaName", removeFileExtension(mediaName));
		
		return returnMap;
	}

	
	
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody HashMap<String, Object>  save(
             FileUpload uploadedFile,
                    Model map) {
         
        List<MultipartFile> files = uploadedFile.getFiles();
 
        HashMap<String, Object> returnMap = new HashMap<String, Object>();
        int returnStatus;
        if(files.size() > 0) {
            for (MultipartFile multipartFile : files) {
 
                String mediaName = multipartFile.getOriginalFilename();
                String mediaType = getFileType(multipartFile.getContentType());
                MediaTypeDetails mediaTypeDetails = new MediaTypeDetails(mediaType, "Name", removeFileExtension(mediaName));
					try {
						returnStatus = mediaService.insertMedia(mediaTypeDetails, multipartFile);
						returnMap = getStatusMap(returnMap, returnStatus, mediaName);
					} catch (DataIntegrityViolationException e) {
						returnMap=getExceptionMap(returnMap, returnStatusMap.get("duplicateMediaMessage"), mediaName);
					} catch (SQLException e) {
						returnMap=getExceptionMap(returnMap, returnStatusMap.get("errorMessage"), mediaName);
					}
            }
        } else {
        	returnMap=getExceptionMap(returnMap, returnStatusMap.get("errorMessage"), null);
        }
         
        return returnMap;
    }
	
//	@RequestMapping("/stopUpload")
//	public @ResponseBody void stopFileUpload() {
//		stopUpload = true;
//	}
}