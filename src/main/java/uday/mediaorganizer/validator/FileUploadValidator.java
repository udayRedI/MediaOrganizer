package uday.mediaorganizer.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import uday.mediaorganizer.model.domainmodel.FileUpload;

 
public class FileUploadValidator implements Validator{
 
	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		//just validate the FileUpload instances
		return FileUpload.class.isAssignableFrom(clazz);
	}
 
	@Override
	public void validate(Object target, Errors errors) {
 
		FileUpload file = (FileUpload)target;
 
		if(file.getFiles().size()==0){
			errors.rejectValue("file", "required.fileUpload");
		}
	}
}