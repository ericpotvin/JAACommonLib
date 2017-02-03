package com.corelib.jaacommonlib;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.junit.Test;

/**
 * Unit test for NetworkStatus
 */
@SuppressWarnings("ALL")
public class NetworkStatusTest
{
	/**
	 * Preset data
	 */
	private static final NetworkInfo[] NO_NETWORK = {};

	@Test
	public void testConnectivityOfflineSingle()
	{
		final ConnectivityManager connectivityManager = mock(ConnectivityManager.class);
		when(connectivityManager.getAllNetworkInfo()).thenReturn(NetworkStatusTest.NO_NETWORK);

		NetworkStatus networkStatus = new NetworkStatus(connectivityManager);

		assertFalse(networkStatus.canConnectToInternet());
	}

	@Test
	public void testConnectivityOnlineSingle()
	{
		final ConnectivityManager connectivityManager = mock(ConnectivityManager.class);

		// Build an active connection
		NetworkInfo networkInfo = mock(NetworkInfo.class);
		when(networkInfo.getState()).thenReturn(NetworkInfo.State.CONNECTED);

		NetworkInfo[] networksInfo = {networkInfo};

		when(connectivityManager.getAllNetworkInfo()).thenReturn(networksInfo);

		NetworkStatus networkStatus = new NetworkStatus(connectivityManager);

		assertTrue(networkStatus.canConnectToInternet());
	}

	@Test
	public void testConnectivityOfflineMultiple()
	{
		final ConnectivityManager connectivityManager = mock(ConnectivityManager.class);

		// Build an inactive connection
		NetworkInfo networkInfo = mock(NetworkInfo.class);
		when(networkInfo.getState()).thenReturn(NetworkInfo.State.DISCONNECTED);

		NetworkInfo[] networksInfo = {networkInfo, networkInfo};

		when(connectivityManager.getAllNetworkInfo()).thenReturn(networksInfo);

		NetworkStatus networkStatus = new NetworkStatus(connectivityManager);

		assertFalse(networkStatus.canConnectToInternet());
	}

	@Test
	public void testConnectivityOnlineMultiple()
	{
		final ConnectivityManager connectivityManager = mock(ConnectivityManager.class);

		// Build an active and inactive connection
		NetworkInfo networkInfo1 = mock(NetworkInfo.class);
		when(networkInfo1.getState()).thenReturn(NetworkInfo.State.DISCONNECTED);

		NetworkInfo networkInfo2 = mock(NetworkInfo.class);
		when(networkInfo1.getState()).thenReturn(NetworkInfo.State.CONNECTED);

		NetworkInfo[] networksInfo = {networkInfo1, networkInfo2};

		when(connectivityManager.getAllNetworkInfo()).thenReturn(networksInfo);

		NetworkStatus networkStatus = new NetworkStatus(connectivityManager);

		assertTrue(networkStatus.canConnectToInternet());
	}

	@Test
	public void testNoNetwork()
	{
		final ConnectivityManager connectivityManager = mock(ConnectivityManager.class);

		// No connection available
		when(connectivityManager.getAllNetworkInfo()).thenReturn(null);

		NetworkStatus networkStatus = new NetworkStatus(connectivityManager);

		assertFalse(networkStatus.canConnectToInternet());
	}

	@Test
	public void testInvalid()
	{
		NetworkStatus networkStatus = new NetworkStatus(null);

		assertFalse(networkStatus.canConnectToInternet());
	}
}
