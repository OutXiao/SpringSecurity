package club.wenfan.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.mockito.internal.util.io.IOUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileController {
	
	public static String filePath="D:/uploadFile/"; 
	/**
	 * 下载文件
	 * @param id
	 * @param request
	 * @param response
	 */
	@GetMapping("downloadfile/{id}")
	public void download(@PathVariable String id,HttpServletRequest request,HttpServletResponse response) {
		
		try (
				InputStream input=new FileInputStream(new File(filePath,id+".txt"));
				OutputStream out=response.getOutputStream()
 			){
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition","attachment;filename=test.txt"); //指定响应头，下载文件名
			IOUtils.copy(input, out);
			out.flush();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	/**
	 * 上传文件
	 * @param file
	 * @param request
	 * @throws IOException
	 */
	@PostMapping("/addfile")
	public void upload(@RequestParam("file") MultipartFile file ,HttpServletRequest request) throws IOException {
		String fileName=file.getOriginalFilename();
		System.out.println("fileName"+fileName);
		
		System.out.println("filePath"+filePath);
		File targetFile=new File(filePath);
		if(!targetFile.exists()) {
			targetFile.mkdir();
		}
		FileOutputStream  out=new FileOutputStream(filePath+fileName);
		out.write(file.getBytes());
		out.flush();
		out.close();
	}
	
}
