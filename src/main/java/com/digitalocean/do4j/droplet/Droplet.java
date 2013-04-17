package com.digitalocean.do4j.droplet;

import java.io.IOException;

import com.digitalocean.do4j.image.Image;
import com.digitalocean.do4j.main.DigitalOcean;
import com.digitalocean.do4j.main.api.exception.DigitalOceanAPIConfigurationErrorException;
import com.digitalocean.do4j.size.Size;
import com.google.api.client.util.Key;

public class Droplet {

	@Key
	public int id;

	@Key
	public String name;

	@Key
	public int image_id;

	@Key
	public int region_id;

	@Key
	public int size_id;

	@Key
	public String status;

	@Key
	public String backups_active;

	@Key
	public int event_id;

	public boolean destroy() throws DigitalOceanAPIConfigurationErrorException {
		boolean result = false;
		try {
			result = DigitalOcean.destroyDroplet(this.id);
		} catch (IOException e) {}
		return result;
	}

	public boolean reboot() throws DigitalOceanAPIConfigurationErrorException {
		boolean result = false;
		try {
			result = DigitalOcean.rebootDroplet(this.id);
		} catch (IOException e) {}
		return result;
	}

	public boolean powerCycle() throws DigitalOceanAPIConfigurationErrorException {
		boolean result = false;
		try {
			result = DigitalOcean.powerCycleDroplet(this.id);
		} catch (IOException e) {}
		return result;
	}

	public boolean shutdown() throws DigitalOceanAPIConfigurationErrorException {
		boolean result = false;
		try {
			result = DigitalOcean.shutdownDroplet(this.id);
		} catch (IOException e) {}
		return result;
	}

	public boolean powerOff() throws DigitalOceanAPIConfigurationErrorException {
		boolean result = false;
		try {
			result = DigitalOcean.powerOffDroplet(this.id);
		} catch (IOException e) {}
		return result;
	}

	public boolean powerOn() throws DigitalOceanAPIConfigurationErrorException {
		boolean result = false;
		try {
			result = DigitalOcean.powerOnDroplet(this.id);
		} catch (IOException e) {}
		return result;
	}

	public boolean resetRootPassword() throws DigitalOceanAPIConfigurationErrorException {
		boolean result = false;
		try {
			result = DigitalOcean.resetRootPasswordForDroplet(this.id);
		} catch (IOException e) {}
		return result;
	}

	public boolean resize(Size size) throws DigitalOceanAPIConfigurationErrorException {
		return resize(size.id);
	}

	public boolean resize(int size_id) throws DigitalOceanAPIConfigurationErrorException {
		boolean result = false;
		try {
			result = DigitalOcean.resizeDroplet(this.id, size_id);
		} catch (IOException e) {}
		return result;
	}

	public boolean takeSnaphot() throws DigitalOceanAPIConfigurationErrorException {
		return takeSnaphot(null);
	}
	
	public boolean takeSnaphot(String name) throws DigitalOceanAPIConfigurationErrorException {
		boolean result = false;
		try {
			result = DigitalOcean.takeSnapshotForDroplet(this, name);
		} catch (IOException e) {}
		return result;
	}

	public boolean restoreFromImage(Image image) throws DigitalOceanAPIConfigurationErrorException {
		return restoreFromImage(image.id);
	}

	public boolean restoreFromImage(int image_id) throws DigitalOceanAPIConfigurationErrorException {
		boolean result = false;
		try {
			result = DigitalOcean.restoreDropletFromImage(this.id, image_id);
		} catch (IOException e) {}
		return result;
	}

	public boolean rebuildFromImage(Image image) throws DigitalOceanAPIConfigurationErrorException {
		return rebuildFromImage(image.id);
	}

	public boolean rebuildFromImage(int image_id) throws DigitalOceanAPIConfigurationErrorException {
		boolean result = false;
		try {
			result = DigitalOcean.rebuildDropletFromImage(this.id, image_id);
		} catch (IOException e) {}
		return result;
	}

	public boolean enableBackups() throws DigitalOceanAPIConfigurationErrorException {
		boolean result = false;
		try {
			result = DigitalOcean.enableBackupsForDroplet(this.id);
		} catch (IOException e) {}
		return result;
	}

	public boolean disableBackups() throws DigitalOceanAPIConfigurationErrorException {
		boolean result = false;
		try {
			result = DigitalOcean.disableBackupsForDroplet(this.id);
		} catch (IOException e) {}
		return result;
	}

}
