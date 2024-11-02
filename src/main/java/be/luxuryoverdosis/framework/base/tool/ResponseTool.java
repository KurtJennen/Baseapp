package be.luxuryoverdosis.framework.base.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import be.luxuryoverdosis.baseapp.Constants;
import be.luxuryoverdosis.framework.base.Encoding;
import be.luxuryoverdosis.framework.base.FileContentType;
import be.luxuryoverdosis.framework.web.exception.ServiceException;

public final class ResponseTool {
	private ResponseTool() {
	}
	
	public static void initializeResponseForDownload(final HttpServletResponse response, final String attachmentName, final String contentType, final int contentLength) {
		response.setHeader("Content-Disposition", "attachment;filename=\"" + attachmentName + "\"");
	    response.setContentType(contentType);
		response.setContentLength(contentLength);
	}
	
	public static void writeResponseForDownload(final HttpServletResponse response, final String attachmentName, final String contentType, final byte[] bytes) throws Exception {
		initializeResponseForDownload(response, attachmentName, contentType, bytes.length);
		
		ServletOutputStream out = response.getOutputStream();
		out.write(bytes);
		out.flush();
		out.close();
		
		response.flushBuffer();
	}
	
	public static void initializeResponseForAuthentication(final HttpServletResponse response) {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(FileContentType.APPLICATION_JSON);
		response.setCharacterEncoding(Encoding.UTF_8);
	}
	
	public static void writeResponseForAuthentication(final HttpServletResponse response, final String message) throws Exception {
		initializeResponseForAuthentication(response);
		
		ServletOutputStream out = response.getOutputStream();
		out.write(message.getBytes());
		out.flush();
		out.close();
		
		response.flushBuffer();
	}
	
    public static void writeResponseForDownloadDocument(final HttpServletResponse response, final File file) {
        initializeResponseForDownload(response, file.getName(), FileContentType.APPLICATION_VND_OASIS_OPENDOCUMENT_TEXT, Long.valueOf(file.length()).intValue());
        
        try {
            ServletOutputStream out = response.getOutputStream();
            
            InputStream inputStream = new FileInputStream(file);
            byte[] buffer = new byte[Constants.DUIZENDVIERENTWINTIG];
            int lengte;
            while ((lengte = inputStream.read(buffer)) > 0) {
                out.write(buffer, 0, lengte);
            }
            inputStream.close();
        } catch (Exception e) {
            throw new ServiceException("errors.exception.type", new String[]{e.getClass().getName().toLowerCase()});
        }
    }
	
}
