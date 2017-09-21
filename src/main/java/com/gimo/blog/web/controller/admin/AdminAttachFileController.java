package com.gimo.blog.web.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gimo.blog.core.util.PropertiesUtils;


@Controller
@RequestMapping("admin/attach")
public class AdminAttachFileController {

	private final static Logger logger = LoggerFactory.getLogger(AdminAttachFileController.class);
	
	@RequestMapping(value="/uploadimage")
	public @ResponseBody Object uploadImage (MultipartFile upfile, HttpServletRequest request, HttpServletResponse response){
		String basePath ;
		String visitUrl ;
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String os = System.getProperty("os.name");  
			if(os.toLowerCase().startsWith("win")){  
				basePath = PropertiesUtils.getProperties("base.path.win");
				visitUrl = PropertiesUtils.getProperties("visit.url");  
			}else{
				basePath = PropertiesUtils.getProperties("base.path.linux");
				visitUrl = PropertiesUtils.getProperties("visit.url");
			}
			// 文件拓展名
			String ext = upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".")+1);
			String pre =  String.valueOf(System.currentTimeMillis());
			// 文件完整名称
			String fileName = pre.concat(".").concat(ext);
			StringBuilder filePath = new StringBuilder();
			//拼接保存路径
			filePath.append(basePath).append("/").append(fileName);
			visitUrl = visitUrl.concat("readimage/").concat(fileName).concat(".aspx");
			File file = new File(filePath.toString());
			if(!file.exists()){
				file.getParentFile().mkdirs();
			}
			OutputStream out = new FileOutputStream(file);
			FileCopyUtils.copy(upfile.getInputStream(), out);
			result.put("state", "SUCCESS");
			result.put("url", visitUrl);
			result.put("size", upfile.getSize());
			result.put("original", fileName);
			result.put("type", upfile.getContentType());
		} catch (Exception e){
			logger.error("uploadImage have some problems :" + e.toString());
			result.put("state", "ERROR");
		}
		return result;
	}
	
	@RequestMapping("/readimage/{fileName}")
	public void readImage(@PathVariable("fileName") String fileName,  HttpServletResponse response) throws IOException {
		String basePath ;
		String os = System.getProperty("os.name");  
		if(os.toLowerCase().startsWith("win")){  
			basePath = PropertiesUtils.getProperties("base.path.win");
		}else{
			basePath = PropertiesUtils.getProperties("base.path.linux");
		}
		String filePath = basePath.concat("/").concat(fileName);
		File image = new File(filePath);
		if (!image.exists()) {  
			return;  
		}
		response.setHeader("Pragma", "No-cache"); 
		response.setHeader("Cache-Control", "no-cache"); 
		response.setDateHeader("Expires", 0); 
		response.setContentType("image/*");
		response.getOutputStream().write(FileUtils.readFileToByteArray(image));  

        //java在使用流时,都会有一个缓冲区,按一种它认为比较高效的方法来发数据:

        //把要发的数据先放到缓冲区,缓冲区放满以后再一次性发过去,而不是分开一次一次地发.

        //而flush()表示强制将缓冲区中的数据发送出去,不必等到缓冲区满.

        response.getOutputStream().flush();  

        response.getOutputStream().close();  
	}

}
