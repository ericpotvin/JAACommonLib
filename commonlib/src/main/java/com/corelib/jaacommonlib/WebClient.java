package com.corelib.jaacommonlib;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URI;

/**
 * WebClient.java
 *
 * WebClient manipulation.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
@SuppressWarnings("ALL")
public class WebClient extends WebViewClient
{
	/**
	 * Domain to check
	 */
	private String domain;

	/**
	 * Constructor
	 *
	 * @param domain The domain
	 */
	public WebClient(String domain)
	{
		this.domain = domain;
	}

	/**
	 * Get the domain
	 *
	 * @return String
	 */
	public String getDomain()
	{
		return this.domain;
	}

	/**
	 * shouldOverrideUrlLoading()
	 *
	 * Prevent domain injections
	 *
	 * @param view	The web view
	 * @param url	The url
	 * @return boolean
	 */
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url)
	{
		if(!URI.create(url).getHost().equals(this.domain)) {
			return false;
		}
		view.loadUrl(url);
		return true;
	}
}