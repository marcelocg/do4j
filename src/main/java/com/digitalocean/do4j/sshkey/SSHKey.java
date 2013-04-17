package com.digitalocean.do4j.sshkey;

import java.io.IOException;

import com.digitalocean.do4j.main.DigitalOcean;
import com.digitalocean.do4j.main.api.exception.DigitalOceanAPIConfigurationErrorException;
import com.google.api.client.util.Key;

public class SSHKey {

	@Key
	public int id;

	@Key
	public String name;

	@Key
	public String ssh_pub_key;

	public boolean destroy() throws DigitalOceanAPIConfigurationErrorException {
		boolean result = false;
		try {
			result = DigitalOcean.destroySSHKey(this.id);
		} catch (IOException e) {}
		return result;
	}
}
