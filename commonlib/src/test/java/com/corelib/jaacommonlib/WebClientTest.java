package com.corelib.jaacommonlib;

import android.webkit.WebView;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Unit test for WebClient
 */
@SuppressWarnings("ALL")
public class WebClientTest
{
	@Test
	public void testWebClient()
	{
		WebClient webClient = new WebClient("domain.com");
		assertTrue(webClient.getDomain().equals("domain.com"));
	}

	@Test
	public void testLoad()
	{
		WebView view = mock(WebView.class);

		WebClient webClient = new WebClient("domain.com");
		boolean result = webClient.shouldOverrideUrlLoading(view, "http://domain.com/page1.html");

		assertTrue(result);
	}

	@Test
	public void testLoadInjection()
	{
		WebView view = mock(WebView.class);

		WebClient webClient = new WebClient("domain.com");
		boolean result = webClient.shouldOverrideUrlLoading(view, "http://another-domain.com/page1.html");

		assertFalse(result);
	}
}