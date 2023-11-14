package com.lams.loaring.config.api;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.ContentCachingResponseWrapper;

public class CachedHttpServletResponse extends ContentCachingResponseWrapper {


	public CachedHttpServletResponse(HttpServletResponse response) {
		super(response);
	}

	public String getContentString() throws IOException {
		InputStream contentInputStream = getContentInputStream();
		byte[] content = StreamUtils.copyToByteArray(contentInputStream);
		if (content.length > 0) {
			return new String(content);
		}
		return "binary context";
	}
}
