package com.digitalocean.do4j.droplet;

import java.util.List;

import com.google.api.client.util.Key;

public class DropletList {
	@Key
	public String status;
	
	@Key
	public List<Droplet> droplets;

}
