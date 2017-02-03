package com.corelib.jaacommonlib;

import android.content.Intent;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for DownloadService
 */
@SuppressWarnings("ALL")
public class DownloadServiceTest
{
	@Test
	public void testConstructor() throws Exception
	{
		DownloadService downloadService = new DownloadService();

		assertTrue(downloadService.getContent().equals(""));
		assertTrue(downloadService.getHttpCode() == 0);
		assertTrue(downloadService.getLength() == 0);
	}

	@Test
	public void testDownloadServiceBadUrl()
	{
		Intent intent = mock(Intent.class);
		when(intent.getStringExtra(DownloadService.URL)).thenReturn("blah");

		DownloadService downloadService = new DownloadService();
		downloadService.onHandleIntent(intent);

		assertTrue(downloadService.getContent().equals(""));
		assertTrue(downloadService.getHttpCode() == 0);
		assertTrue(downloadService.getLength() == 0);
	}

	@Test
	public void testDownloadServiceBadDomain()
	{
		Intent intent = mock(Intent.class);
		when(intent.getStringExtra(DownloadService.URL)).thenReturn("http://unkonow.domain/");

		DownloadService downloadService = new DownloadService();
		downloadService.onHandleIntent(intent);

		assertTrue(downloadService.getContent().equals(""));
		assertTrue(downloadService.getHttpCode() == 0);
		assertTrue(downloadService.getLength() == 0);
	}

	@Test
	public void testDownloadService301()
	{
		Intent intent = mock(Intent.class);
		when(intent.getStringExtra(DownloadService.URL)).thenReturn("http://github.com/");

		DownloadService downloadService = new DownloadService();
		downloadService.onHandleIntent(intent);

		assertTrue(downloadService.getHttpCode() == 301);
	}


	@Test
	public void testDownloadService200()
	{
		Intent intent = mock(Intent.class);
		when(intent.getStringExtra(DownloadService.URL)).thenReturn("http://google.com/");

		DownloadService downloadService = new DownloadService();
		downloadService.onHandleIntent(intent);

		assertTrue(downloadService.getHttpCode() == 200);
	}
}
