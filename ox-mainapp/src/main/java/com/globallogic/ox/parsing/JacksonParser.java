package com.globallogic.ox.parsing;

import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import com.globallogic.ox.exceptions.ParseError;

import com.google.inject.Singleton;

@Singleton
public class JacksonParser implements IJSONParser {

	private ObjectMapper mapper = new ObjectMapper();

	public JacksonParser() {
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public String toJson(Object object) throws ParseError{
		String json = null;
		try {
			json = mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			throw new ParseError(e.getMessage(),e);
		} catch (JsonMappingException e) {
			throw new ParseError(e.getMessage(),e);
		} catch (IOException e) {
			throw new ParseError(e.getMessage(),e);
		}
		return json;
	}

	public <T> T toObject(String json, Class<T> clazz) throws ParseError {
		T obj = null;
		try {
			obj = mapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			throw new ParseError(e.getMessage(),e);
		} catch (JsonMappingException e) {
			throw new ParseError(e.getMessage(),e);
		} catch (IOException e) {
			throw new ParseError(e.getMessage(),e);
		} catch (Exception e) {
			throw new ParseError("JacksonParser toObject() unexpected error",e);
		}
		return obj;
	}

	public <T> List<T> toList(String response, final Class<T> clazz) throws ParseError {
		List<T> list = null;
		try {
			list = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, clazz));			
		} catch (JsonParseException e) {
			throw new ParseError(e.getMessage(),e);
		} catch (JsonMappingException e) {
			throw new ParseError(e.getMessage(),e);
		} catch (IOException e) {
			throw new ParseError(e.getMessage(),e);
		} catch (Exception e) {
			throw new ParseError("JacksonParser toList() unexpected error",e);
		}
		
		return list;
	}

}
