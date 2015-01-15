package com.chat.server;

import java.io.File;
import java.io.IOException;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;

public class ChatLocation {
	// Method to get current location of the user
	public UserLocation getLocation(String ipAddress, String filePath) {

		File file = new File(filePath);
		return getLocation(ipAddress, file);
	}
	public UserLocation getLocation(String ipAddress, File file) {
		UserLocation serverLocation = null;
		try {
			serverLocation = new UserLocation();
			LookupService lookup = new LookupService(file,LookupService.GEOIP_MEMORY_CACHE);
			Location locationServices = lookup.getLocation(ipAddress);
			serverLocation.setCountryCode(locationServices.countryCode);
			serverLocation.setCountryName(locationServices.countryName);
			serverLocation.setRegion(locationServices.region);
			serverLocation.setRegionName(regionName.regionNameByCode(locationServices.countryCode, locationServices.region));
			serverLocation.setCity(locationServices.city);
			serverLocation.setPostalCode(locationServices.postalCode);
			serverLocation.setLatitude(String.valueOf(locationServices.latitude));
			serverLocation.setLongitude(String.valueOf(locationServices.longitude));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return serverLocation;
	}
}