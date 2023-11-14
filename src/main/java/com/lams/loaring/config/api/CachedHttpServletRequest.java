package com.lams.loaring.config.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import org.springframework.util.StreamUtils;

public class CachedHttpServletRequest extends HttpServletRequestWrapper {

	private byte[] cachedPayload;

	public CachedHttpServletRequest(HttpServletRequest request) throws IOException {
		super(request);
		InputStream requestInputStream = request.getInputStream();
		this.cachedPayload = StreamUtils.copyToByteArray(requestInputStream);
	}

	@Override
	public ServletInputStream getInputStream() {
		return new CachedServletInputStream(this.cachedPayload);
	}

	@Override
	public Collection<Part> getParts() throws IOException, ServletException {
		return super.getParts();
	}

	@Override
	public BufferedReader getReader() {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.cachedPayload);
		return new BufferedReader(new InputStreamReader(byteArrayInputStream));
	}


}
