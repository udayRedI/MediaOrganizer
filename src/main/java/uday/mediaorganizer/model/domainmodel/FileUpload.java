package uday.mediaorganizer.model.domainmodel;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload{
 
	private List<MultipartFile> files;

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
 
}