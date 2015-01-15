package com.chat.server;

public class UserLocation {

	private String countryCode;
	private String countryName;
	private String region;
	private String postalCode;
	private float latitude;
	private float longitude;
	private String city;
	private String regionName;

	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = Float.parseFloat(latitude);
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = Float.parseFloat(longitude);
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}	
}