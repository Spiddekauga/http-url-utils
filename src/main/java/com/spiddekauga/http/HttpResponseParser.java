package com.spiddekauga.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Parses HTTP responses and converts them into common types
 */
public class HttpResponseParser {
private static final String NEWLINE = System.getProperty("line.separator");

/**
 * Convert the server response to a string. Uses UTF-8 charset encoding.
 * @param connection the established connection
 * @return get the string response from a connection
 * @throws IOException
 */
public static String getStringResponse(HttpURLConnection connection) throws IOException {
	return getStringResponse(connection, "UTF-8");
}

/**
 * Convert the server response to a string.
 * @param connection the established connection
 * @param charset character encoding to use
 * @return get the string response from a connection
 * @throws IOException
 */
public static String getStringResponse(HttpURLConnection connection, String charset) throws IOException {

	InputStream inputStream = connection.getInputStream();
	BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));
	StringBuilder stringBuilder = new StringBuilder();
	String line;
	while ((line = reader.readLine()) != null) {
		stringBuilder.append(line).append(NEWLINE);
	}

	// Remove last newline
	int lastNewline = stringBuilder.lastIndexOf(NEWLINE);
	if (lastNewline != -1) {
		stringBuilder.delete(lastNewline, stringBuilder.length());
	}

	return stringBuilder.toString();
}
}
