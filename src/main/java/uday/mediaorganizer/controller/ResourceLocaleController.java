package uday.mediaorganizer.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResourceLocaleController {

	@RequestMapping(value = "/ResourceBundle", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, String> getMediaContents(String language) {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("Application", new Locale(language));
		Map<String, String> map = convertResourceBundleToMap(resourceBundle);
		
		return map;
	}

	 private Map<String, String> convertResourceBundleToMap(ResourceBundle resource) {
		    Map<String, String> map = new HashMap<String, String>();

		    Enumeration<String> keys = resource.getKeys();
		    while (keys.hasMoreElements()) {
		      String key = keys.nextElement();
		      map.put(key, resource.getString(key));
		    }

		    return map;
		  }
}
