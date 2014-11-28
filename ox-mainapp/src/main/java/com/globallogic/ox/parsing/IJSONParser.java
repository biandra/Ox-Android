package com.globallogic.ox.parsing;

import java.util.List;
import com.globallogic.ox.exceptions.ParseError;

public interface IJSONParser {

	String toJson(Object object) throws ParseError;

	<T> T toObject(String json, Class<T> clazz) throws ParseError;

	<T> List<T> toList(String response, final Class<T> clazz) throws ParseError;
}
