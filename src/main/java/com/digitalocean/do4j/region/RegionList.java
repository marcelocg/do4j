package com.digitalocean.do4j.region;

import java.util.List;

import com.google.api.client.util.Key;

public class RegionList {

	@Key
	public String status;
	
	@Key
	public List<Region> regions;

}
