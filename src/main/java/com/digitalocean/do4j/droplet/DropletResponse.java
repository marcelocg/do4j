package com.digitalocean.do4j.droplet;

import com.google.api.client.util.Key;

public class DropletResponse {
	@Key
	public String status;

	@Key
	public Droplet droplet;

}
