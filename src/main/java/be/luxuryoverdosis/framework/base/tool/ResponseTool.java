package be.luxuryoverdosis.framework.base.tool;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import be.luxuryoverdosis.framework.base.Encoding;
import be.luxuryoverdosis.framework.base.FileContentType;

public class ResponseTool {
	
	public static void initializeResponseForDownload(HttpServletResponse response, final String attachmentName, final String contentType, final int contentLength) {
		response.setHeader("Content-Disposition", "attachment;filename=\"" + attachmentName + "\"");
	    response.setContentType(contentType);
		response.setContentLength(contentLength);
	}
	
	public static void writeResponseForDownload(HttpServletResponse response, final String attachmentName, final String contentType, final byte[] bytes) throws Exception {
		initializeResponseForDownload(response, attachmentName, contentType, bytes.length);
		
		ServletOutputStream out = response.getOutputStream();
		out.write(bytes);
		out.flush();
		out.close();
		
		response.flushBuffer();
	}
	
	public static void initializeResponseForAuthentication(HttpServletResponse response) {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(FileContentType.APPLICATION_JSON);
		response.setCharacterEncoding(Encoding.UTF_8);
	}
	
	public static void writeResponseForAuthentication(HttpServletResponse response, String message) throws Exception {
		initializeResponseForAuthentication(response);
		
		ServletOutputStream out = response.getOutputStream();
		out.write(message.getBytes());
		out.flush();
		out.close();
		
		response.flushBuffer();
	}
	
}
