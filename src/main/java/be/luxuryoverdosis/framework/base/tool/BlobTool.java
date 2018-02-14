package be.luxuryoverdosis.framework.base.tool;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Blob;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;

import be.luxuryoverdosis.framework.base.Encoding;
import be.luxuryoverdosis.framework.web.exception.ServiceException;

public class BlobTool {
	
	public static byte[] convertBlobToBytes(Blob blob) {
		if(blob == null) {
			throw new ServiceException("exists.not", new String[] {"file"});
		}
		
		try {
			StringWriter stringWriter = new StringWriter();
			IOUtils.copy(blob.getBinaryStream(), stringWriter, Encoding.UTF_8);
			return stringWriter.toString().getBytes();
		} catch (SQLException e) {
			throw new ServiceException("exists.not", new String[] {"file"});
		} catch (IOException e) {
			throw new ServiceException("filecopy.failed");
		}
	}

	
	
	
	
}
