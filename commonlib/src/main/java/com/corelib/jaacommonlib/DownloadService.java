package com.corelib.jaacommonlib;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.lang.String;
import java.net.HttpURLConnection;

/**
 * DownloadService.java
 *
 * Download Service.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
@SuppressWarnings("ALL")
public class DownloadService extends IntentService
{
	/**
	 * The class tag name
	 */
	final static private String TAG = "DownloadService";

	/**
	 * The URL tag name
	 */
	public static final String URL = "url";
	/**
	 * The http code tag name
	 */
	public static final String HTTP_CODE = "httpcode";
	/**
	 * The result tag name
	 */
	public static final String RESPONSE = "result";
	/**
	 * The length tag name
	 */
	public static final String LENGTH = "length";
	/**
	 * The cacheFile tag name
	 */
	public static final String CACHE_FILE = "cacheFile";
	/**
	 * The IntentService tag name
	 */
	public static final String INTENT = "IntentService";

	/**
	 * The callback content
	 */
	private String content;
	/**
	 * The HTTP Code
	 */
	private int httpCode;
	/**
	 * The document length
	 */
	private int length;

	/**
	 * Constructor
	 *
	 * Requires:
 	 *   <uses-permission android:name="android.permission.INTERNET"></uses-permission>
	 *   <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
	 *
	 */
	public DownloadService()
	{
		super("DownloadService");
		this.content = "";
		this.httpCode = 0;
		this.length = 0;

		this.setStrickMode();
	}

	/**
	 * Set the strick mode
	 * Make sure Strict mode is enabled
	 */
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	private void setStrickMode()
	{
		try {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
		}
		catch (NullPointerException ex) {
		}
	}

	/**
	 * Get the page content
	 *
	 * @return String
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Get the HTTP Code
	 *
	 * @return int
	 */
	public int getHttpCode() {
		return httpCode;
	}

	/**
	 * Get the document lenght
	 *
	 * @return int
	 */
	public long getLength() {
		return length;
	}

	/**
	 * Override: onHandleIntent
	 *
	 * @param intent The intent
	 */
	@Override
	protected void onHandleIntent(Intent intent)
	{
		String urlLink;
		//int method = DownloadService.GET;

		try {
			// Get the intent passed params
			urlLink = intent.getStringExtra(DownloadService.URL);

			URL url = new URL(urlLink);

			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(3000);
			InputStream is = httpURLConnection.getInputStream();
			this.httpCode = httpURLConnection.getResponseCode();
			if(this.httpCode != HttpURLConnection.HTTP_OK) {
				return;
			}

			this.length = httpURLConnection.getContentLength();
			this.content = httpURLConnection.getContent().toString();
		}
		catch (MalformedURLException eMal) {
			return;
		}
		catch (IOException eIO) {
			return;
		}

		this.publishResults();
	}

	/**
	 * publishResults
	 *
	 */
	private void publishResults()
	{
		Intent intent = new Intent(DownloadService.INTENT);
		intent.putExtra(DownloadService.RESPONSE, this.content);
		intent.putExtra(DownloadService.HTTP_CODE, this.httpCode);
		intent.putExtra(DownloadService.LENGTH, this.length);
		sendBroadcast(intent);
	}
}