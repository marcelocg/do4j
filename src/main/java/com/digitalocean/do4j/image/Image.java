package com.digitalocean.do4j.image;

import java.io.IOException;

import com.digitalocean.do4j.main.DigitalOcean;
import com.digitalocean.do4j.main.api.exception.DigitalOceanAPIConfigurationErrorException;
import com.google.api.client.util.Key;

public class Image {

	@Key
	public int id;

	@Key
	public String name;

	@Key
	public String distribution;
	
	public boolean destroy() throws DigitalOceanAPIConfigurationErrorException {
		boolean result = false;
		try {
			result = DigitalOcean.destroyImage(this.id);
		} catch (IOException e) {}
		return result;
	}
}
