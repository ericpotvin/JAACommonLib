package com.corelib.jaacommonlib;


import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unit test for URLHandler
 */
@SuppressWarnings("ALL")
public class URLHandlerTest
{
	/**
	 * Setup default test strings
	 */
	private static final String DOMAIN = "http://bookofzeus.com";
	private static final String url1 = URLHandlerTest.DOMAIN + "/articles/create-an-android-virtual-device";
	private static final String url2 = URLHandlerTest.DOMAIN + "/page1.html";
	private static final String url3 = URLHandlerTest.DOMAIN + "/page2.php?awesome=1";
	private static final String url4 = URLHandlerTest.DOMAIN + "/folder1/";
	private static final String url5 = URLHandlerTest.DOMAIN + "/folder2/another_test";
	private static final String url6 = URLHandlerTest.DOMAIN + "/articles/create-an-android-virtual-device/";
	private static final String url7 = URLHandlerTest.DOMAIN + "";
	private static final String url8 = URLHandlerTest.DOMAIN + "?";

	@Test
	public void testURL_1_6() throws Exception
	{
		URLHandler u1 = new URLHandler(URLHandlerTest.url1);
		String test1 = "/articles/create-an-android-virtual-device";
		String path1 = u1.getPath();
		assertTrue(path1.equals(test1));

		u1 = new URLHandler(url6);
		test1 = "/articles/create-an-android-virtual-device/";
		path1 = u1.getPath();
		assertTrue(path1.equals(test1));
	}

	@Test
	public void testURL_2() throws Exception
	{
		String name = "page1";
		String urlName = URLHandler.getNameFromUrl(url2);
		assertTrue(urlName.equals(name));
	}

	@Test
	public void testUrl_7() throws Exception
	{
		String urlName = URLHandler.getNameFromUrl(url7);
		assertTrue(urlName.equals(""));
	}

	@Test
	public void testUrl_8() throws Exception
	{
		String urlName = URLHandler.getNameFromUrl(url8);
		assertTrue(urlName.equals(""));
	}

	@Test
	public void testURL_3() throws Exception
	{
		String name = "page2";

		String urlName = URLHandler.getNameFromUrl(url3);
		assertTrue(urlName.equals(name));
	}

	@Test
	public void testURL_4() throws Exception
	{
		String name = "folder1";

		String urlName = URLHandler.getNameFromUrl(url4);
		assertTrue(urlName.equals(name));
	}

	@Test
	public void testURL_5() throws Exception
	{
		String name = "another_test";

		String urlName = URLHandler.getNameFromUrl(url5);
		assertTrue(urlName.equals(name));
	}
}