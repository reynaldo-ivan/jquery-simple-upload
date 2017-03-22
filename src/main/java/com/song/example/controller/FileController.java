package com.song.example.controller;

import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileController {

	@RequestMapping(value = "/echofile", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody HashMap<String, Object> echoFile(MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("multi "+request);

		MultipartFile multipartFile = request.getFile("file");
		Long size = multipartFile.getSize();
		String contentType = multipartFile.getContentType();
        InputStream stream = multipartFile.getInputStream();
        
        byte[] bytes = IOUtils.toByteArray(stream);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("fileoriginalsize", size);
        map.put("contenttype", contentType);
        map.put("base64", new String(Base64Utils.encode(bytes)));

        return map;
	}
}
