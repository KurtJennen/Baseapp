package be.luxuryoverdosis.framework.base.tool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.lang.StringUtils;
import org.xml.sax.SAXException;

import be.luxuryoverdosis.framework.base.Encoding;

public class JaxbTool {
	public static Marshaller getMarshaller(Class<Object> clazz) throws JAXBException {
		return getMarshaller(clazz, Encoding.UTF_8);
	}
	
	public static Marshaller getMarshaller(Class<Object> clazz, String encoding) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(clazz);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.setProperty(Marshaller.JAXB_ENCODING, encoding);
        
        return m;
	}
	
	public static Unmarshaller getUnmarshaller(Class<Object> clazz) throws JAXBException {
        return getUnmarshaller(clazz, null);
    }
	
	public static Unmarshaller getUnmarshaller(Class<Object> clazz, String schemaLocation) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        if (StringUtils.isNotEmpty(schemaLocation)) {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = null;
            try {
                schema = sf.newSchema(JaxbTool.class.getClassLoader().getResource(schemaLocation));
            } catch (SAXException e) {
                throw new JAXBException("Cannot create schema", e);
            }

            unmarshaller.setSchema(schema);
        }

        return unmarshaller;
    }
	
	public static InputStream getInputStream(Class<Object> clazz, Object data) throws JAXBException {
		return getInputStream(clazz, data, Encoding.UTF_8);
	}
	
	public static InputStream getInputStream(Class<Object> clazz, Object data, String encoding) throws JAXBException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		getMarshaller(clazz, encoding).marshal(data, out);
		InputStream in = new ByteArrayInputStream(out.toByteArray());
		
		return in;
	}
	
	public static OutputStream getOutputStream(Class<Object> clazz, Object data, OutputStream out) throws JAXBException {
		getMarshaller(clazz).marshal(data, out);
		return out;
	}
	
	public static String getXml(Class<Object> clazz, Object data) throws JAXBException {
		return getXml(clazz, data, Encoding.UTF_8);
	}
	
	public static String getXml(Class<Object> clazz, Object data, String encoding) throws JAXBException {
		StringWriter stringWriter = new StringWriter();
		getMarshaller(clazz, encoding).marshal(data, stringWriter);
		return stringWriter.toString();
	}
	
	public static Object getObject(String xmlString, Class<Object> clazz) throws JAXBException {
        return getObject(xmlString, clazz, null);
    }
	
	public static Object getObject(String xmlString, Class<Object> clazz, String schemaLocation) throws JAXBException {
		return getUnmarshaller(clazz, schemaLocation).unmarshal(new StringReader(xmlString));
	}
	
	public static Object getObject(InputStream inputStream, Class<Object> clazz) throws JAXBException, IOException {
		return getObject(inputStream, clazz, null);
	}
	
	public static Object getObject(InputStream inputStream, Class<Object> clazz, String schemaLocation) throws JAXBException, IOException {
        return getUnmarshaller(clazz, schemaLocation).unmarshal(inputStream);
    }
}
