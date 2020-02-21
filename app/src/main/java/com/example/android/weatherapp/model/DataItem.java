package com.example.android.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataItem{
	@SerializedName("summary")
	private String summary;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@SerializedName("sunriseTime")
	private long sunriseTime;

	@SerializedName("sunsetTime")
	private long sunsetTime;

	@SerializedName("moonPhase")
	private double moonPhase;

	@SerializedName("precipIntensityMax")
	private double precipIntensityMax;

	@SerializedName("precipIntensityMaxTime")
	private long precipIntensityMaxTime;

	@SerializedName("precipType")
	private String precipType;

	@SerializedName("temperatureHigh")
	private double temperatureHigh;

	@SerializedName("temperatureHighTime")
	private long temperatureHighTime;

	@SerializedName("temperatureLow")
	private double temperatureLow;

	@SerializedName("temperatureLowTime")
	private long temperatureLowTime;

	@SerializedName("apparentTemperatureHigh")
	private double apparentTemperatureHigh;

	@SerializedName("apparentTemperatureHighTime")
	private double apparentTemperatureHighTime;

	@SerializedName("apparentTemperatureLow")
	private double apparentTemperatureLow;

	@SerializedName("apparentTemperatureLowTime")
	private long apparentTemperatureLowTime;

	@SerializedName("temperatureMin")
	private double temperatureMin;

	@SerializedName("temperatureMinTime")
	private long temperatureMinTime;

	@SerializedName("temperatureMax")
	private double temperatureMax;

	@SerializedName("temperatureMaxTime")
	private long temperatureMaxTime;

	@SerializedName("apparentTemperatureMin")
	private double apparentTemperatureMin;

	@SerializedName("apparentTemperatureMinTime")
	private long apparentTemperatureMinTime;

	@SerializedName("apparentTemperatureMax")
	private double apparentTemperatureMax;

	@SerializedName("apparentTemperatureMaxTime")
	private double apparentTemperatureMaxTime;

	@SerializedName("precipProbability")
	private double precipProbability;

	@SerializedName("visibility")
	private double visibility;

	@SerializedName("windGust")
	private double windGust;

	@SerializedName("precipIntensity")
	private double precipIntensity;

	@SerializedName("icon")
	private String icon;

	@SerializedName("cloudCover")
	private double cloudCover;

	@SerializedName("windBearing")
	private int windBearing;

	@SerializedName("apparentTemperature")
	private double apparentTemperature;

	@SerializedName("pressure")
	private double pressure;

	@SerializedName("dewPoint")
	private double dewPoint;

	@SerializedName("ozone")
	private double ozone;

	@SerializedName("nearestStormBearing")
	private int nearestStormBearing;

	@SerializedName("nearestStormDistance")
	private double nearestStormDistance;

	@SerializedName("temperature")
	private double temperature;

	@SerializedName("humidity")
	private double humidity;

	@SerializedName("time")
	private long time;

	@SerializedName("windSpeed")
	private double windSpeed;

	@SerializedName("uvIndex")
	private int uvIndex;

	public void setPrecipProbability(double precipProbability){
		this.precipProbability = precipProbability;
	}

	public double getPrecipProbability(){
		return precipProbability;
	}

	public void setVisibility(int visibility){
		this.visibility = visibility;
	}

	public double getVisibility(){
		return visibility;
	}

	public void setWindGust(double windGust){
		this.windGust = windGust;
	}

	public double getWindGust(){
		return windGust;
	}

	public void setPrecipIntensity(double precipIntensity){
		this.precipIntensity = precipIntensity;
	}

	public double getPrecipIntensity(){
		return precipIntensity;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setCloudCover(double cloudCover){
		this.cloudCover = cloudCover;
	}

	public double getCloudCover(){
		return cloudCover;
	}

	public void setWindBearing(int windBearing){
		this.windBearing = windBearing;
	}

	public int getWindBearing(){
		return windBearing;
	}

	public void setApparentTemperature(double apparentTemperature){
		this.apparentTemperature = apparentTemperature;
	}

	public double getApparentTemperature(){
		return apparentTemperature;
	}

	public void setPressure(double pressure){
		this.pressure = pressure;
	}

	public double getPressure(){
		return pressure;
	}

	public void setDewPoint(double dewPoint){
		this.dewPoint = dewPoint;
	}

	public double getDewPoint(){
		return dewPoint;
	}

	public void setOzone(double ozone){
		this.ozone = ozone;
	}

	public double getOzone(){
		return ozone;
	}

	public void setNearestStormBearing(int nearestStormBearing){
		this.nearestStormBearing = nearestStormBearing;
	}

	public int getNearestStormBearing(){
		return nearestStormBearing;
	}

	public void setNearestStormDistance(int nearestStormDistance){
		this.nearestStormDistance = nearestStormDistance;
	}

	public double getNearestStormDistance(){
		return nearestStormDistance;
	}

	public void setTemperature(double temperature){
		this.temperature = temperature;
	}

	public double getTemperature(){
		return temperature;
	}

	public void setHumidity(double humidity){
		this.humidity = humidity;
	}

	public double getHumidity(){
		return humidity;
	}

	public void setTime(long time){
		this.time = time;
	}

	public long getTime(){
		return time;
	}

	public void setWindSpeed(double windSpeed){
		this.windSpeed = windSpeed;
	}

	public double getWindSpeed(){
		return windSpeed;
	}

	public void setUvIndex(int uvIndex){
		this.uvIndex = uvIndex;
	}

	public int getUvIndex(){
		return uvIndex;
	}

	public String formatDate(long date, String itemType){
		Date da = new Date(date*1000);
		String format="";
		if (itemType.equals("hourly")){
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY hh:mm a", Locale.getDefault());
			format = dateFormat.format(da);
		}
		else if (itemType.equals("daily")){
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY", Locale.getDefault());
			format = dateFormat.format(da);
		}
		return format;
	}

	public long getSunriseTime() {
		return sunriseTime;
	}

	public void setSunriseTime(long sunriseTime) {
		this.sunriseTime = sunriseTime;
	}

	public long getSunsetTime() {
		return sunsetTime;
	}

	public void setSunsetTime(long sunsetTime) {
		this.sunsetTime = sunsetTime;
	}

	public double getMoonPhase() {
		return moonPhase;
	}

	public void setMoonPhase(double moonPhase) {
		this.moonPhase = moonPhase;
	}

	public double getPrecipIntensityMax() {
		return precipIntensityMax;
	}

	public void setPrecipIntensityMax(double precipIntensityMax) {
		this.precipIntensityMax = precipIntensityMax;
	}

	public long getPrecipIntensityMaxTime() {
		return precipIntensityMaxTime;
	}

	public void setPrecipIntensityMaxTime(long precipIntensityMaxTime) {
		this.precipIntensityMaxTime = precipIntensityMaxTime;
	}

	public String getPrecipType() {
		return precipType;
	}

	public void setPrecipType(String precipType) {
		this.precipType = precipType;
	}

	public double getTemperatureHigh() {
		return temperatureHigh;
	}

	public void setTemperatureHigh(double temperatureHigh) {
		this.temperatureHigh = temperatureHigh;
	}

	public long getTemperatureHighTime() {
		return temperatureHighTime;
	}

	public void setTemperatureHighTime(long temperatureHighTime) {
		this.temperatureHighTime = temperatureHighTime;
	}

	public double getTemperatureLow() {
		return temperatureLow;
	}

	public void setTemperatureLow(double temperatureLow) {
		this.temperatureLow = temperatureLow;
	}

	public long getTemperatureLowTime() {
		return temperatureLowTime;
	}

	public void setTemperatureLowTime(long temperatureLowTime) {
		this.temperatureLowTime = temperatureLowTime;
	}

	public double getApparentTemperatureHigh() {
		return apparentTemperatureHigh;
	}

	public void setApparentTemperatureHigh(double apparentTemperatureHigh) {
		this.apparentTemperatureHigh = apparentTemperatureHigh;
	}

	public double getApparentTemperatureHighTime() {
		return apparentTemperatureHighTime;
	}

	public void setApparentTemperatureHighTime(double apparentTemperatureHighTime) {
		this.apparentTemperatureHighTime = apparentTemperatureHighTime;
	}

	public double getApparentTemperatureLow() {
		return apparentTemperatureLow;
	}

	public void setApparentTemperatureLow(double apparentTemperatureLow) {
		this.apparentTemperatureLow = apparentTemperatureLow;
	}

	public long getApparentTemperatureLowTime() {
		return apparentTemperatureLowTime;
	}

	public void setApparentTemperatureLowTime(long apparentTemperatureLowTime) {
		this.apparentTemperatureLowTime = apparentTemperatureLowTime;
	}

	public double getTemperatureMin() {
		return temperatureMin;
	}

	public void setTemperatureMin(double temperatureMin) {
		this.temperatureMin = temperatureMin;
	}

	public long getTemperatureMinTime() {
		return temperatureMinTime;
	}

	public void setTemperatureMinTime(long temperatureMinTime) {
		this.temperatureMinTime = temperatureMinTime;
	}

	public double getTemperatureMax() {
		return temperatureMax;
	}

	public void setTemperatureMax(double temperatureMax) {
		this.temperatureMax = temperatureMax;
	}

	public long getTemperatureMaxTime() {
		return temperatureMaxTime;
	}

	public void setTemperatureMaxTime(long temperatureMaxTime) {
		this.temperatureMaxTime = temperatureMaxTime;
	}

	public double getApparentTemperatureMin() {
		return apparentTemperatureMin;
	}

	public void setApparentTemperatureMin(double apparentTemperatureMin) {
		this.apparentTemperatureMin = apparentTemperatureMin;
	}

	public long getApparentTemperatureMinTime() {
		return apparentTemperatureMinTime;
	}

	public void setApparentTemperatureMinTime(long apparentTemperatureMinTime) {
		this.apparentTemperatureMinTime = apparentTemperatureMinTime;
	}

	public double getApparentTemperatureMax() {
		return apparentTemperatureMax;
	}

	public void setApparentTemperatureMax(double apparentTemperatureMax) {
		this.apparentTemperatureMax = apparentTemperatureMax;
	}

	public double getApparentTemperatureMaxTime() {
		return apparentTemperatureMaxTime;
	}

	public void setApparentTemperatureMaxTime(double apparentTemperatureMaxTime) {
		this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
	}

	public void setVisibility(double visibility) {
		this.visibility = visibility;
	}

	public void setNearestStormDistance(double nearestStormDistance) {
		this.nearestStormDistance = nearestStormDistance;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"precipProbability = '" + precipProbability + '\'' + 
			",precipIntensity = '" + precipIntensity + '\'' + 
			",time = '" + time + '\'' + 
			"}";
		}
}