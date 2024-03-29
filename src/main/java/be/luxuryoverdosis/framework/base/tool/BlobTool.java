package be.luxuryoverdosis.framework.base.tool;

import java.sql.Blob;
import java.sql.SQLException;

import be.luxuryoverdosis.framework.web.exception.ServiceException;

public class BlobTool {
	public static byte[] convertBlobToBytes(Blob blob) {
		if(blob == null) {
			throw new ServiceException("exists.not", new String[] {"file"});
		}
		
		try {
			return blob.getBytes(1, (int)blob.length());
		} catch (SQLException e) {
			throw new ServiceException("exists.not", new String[] {"file"});
		}
	}
}
