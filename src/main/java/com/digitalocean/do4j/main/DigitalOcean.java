package com.digitalocean.do4j.main;

import java.io.IOException;
import java.util.List;

import com.digitalocean.do4j.droplet.Droplet;
import com.digitalocean.do4j.image.Image;
import com.digitalocean.do4j.main.api.exception.DigitalOceanAPIConfigurationErrorException;
import com.digitalocean.do4j.main.api.exception.DigitalOceanAPIParameterErrorException;
import com.digitalocean.do4j.region.Region;
import com.digitalocean.do4j.size.Size;
import com.digitalocean.do4j.sshkey.SSHKey;

public class DigitalOcean {

	public static final String FILTER_MY_IMAGES = "my_images";
	public static final String FILTER_GLOBAL_IMAGES = "global";
	
	public static List<Region> getRegions() throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.getAllRegions();
	}
	
	public static List<Size> getSizes() throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.getAllSizes();
	}

	public static List<Image> getImages() throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.getAllImages();
	}

	public static List<Image> getImages(String filter) throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
		List<Image> result = null;
		if(filter == FILTER_MY_IMAGES)
			result = DigitalOceanAPI.getMyImages();
		else if(filter == FILTER_GLOBAL_IMAGES)
			result = DigitalOceanAPI.getGlobalImages();
		else
			throw new DigitalOceanAPIParameterErrorException("Filter should be one of DigitalOcean.FILTER_MY_IMAGES or DigitalOcean.FILTER_GLOBAL_IMAGES");
		
		return result;
	}

	public static Image getImage(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.getImage(id);
	}

	public static boolean destroyImage(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.destroyImage(id);
	}

	public static List<SSHKey> getSSHKeys() throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.getAllSSHKeys();
	}
	
	public static SSHKey getSSHKey(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.getSSHKey(id);
	}

	public static SSHKey addSSHKey(String name, String public_key) throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
		if (name == null || name.equals("") || public_key == null || public_key.equals(""))
			throw new DigitalOceanAPIParameterErrorException("Parameters name and public_key should not be null or empty.");
		else
			return DigitalOceanAPI.addSSHKey(name, public_key);
	}

	public static SSHKey updateSSHKey(int id, String public_key) throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
		if (public_key == null || public_key.equals(""))
			throw new DigitalOceanAPIParameterErrorException("Parameters name and public_key should not be null or empty.");
		else
			return DigitalOceanAPI.updateSSHKey(id, public_key);
	}

	public static boolean destroySSHKey(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.destroySSHKey(id);
	}

	public static boolean destroySSHKey(SSHKey key) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return destroySSHKey(key.id);
	}

	public static List<Droplet> getDroplets() throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.getDroplets();
	}

	public static Droplet getDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.getDroplet(id);
	}

	public static Droplet createDroplet(String name, Size size, Image image, Region region) throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
		if(name == null || name.equals("") || size == null || image == null || region == null)
			throw new DigitalOceanAPIParameterErrorException("Parameters name, size, image and region should not be null or empty.");
		return createDroplet(name, size.id, image.id, region.id);
	}
	
	public static Droplet createDroplet(String name, Size size, Image image, Region region, List<SSHKey> sshKeys) throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
		if(name == null || name.equals("") || size == null || image == null || region == null)
			throw new DigitalOceanAPIParameterErrorException("Parameters name, size, image and region should not be null or empty.");

		Droplet droplet = null;
		if(sshKeys == null || sshKeys.size() == 0)
			droplet = createDroplet(name, size.id, image.id, region.id);
		else {
			int[] keys = new int[sshKeys.size()];
			for (int i = 0; i < sshKeys.size(); i++) {
				keys[i] = sshKeys.get(i).id;
			}
			droplet = DigitalOceanAPI.createDroplet(name, size.id, image.id, region.id, keys);
		}
		return droplet;
	}

	public static Droplet createDroplet(String name, int size, int image, int region) throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
		if(name == null || name.equals("") || size <= 0 || image <= 0 || region <= 0)
			throw new DigitalOceanAPIParameterErrorException("Parameter name should not be null or empty. Parameters size, image and region should be greater than 0 (zero).");
		
		return DigitalOceanAPI.createDroplet(name, size, image, region, null);
	}

	public static Droplet createDroplet(String name, int size, int image, int region, List<Integer> sshKeys) throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
		if(name == null || name.equals("") || size <= 0 || image <= 0 || region <= 0 || sshKeys == null || sshKeys.size() == 0)
			throw new DigitalOceanAPIParameterErrorException("Parameter name should not be null or empty. Parameters size, image and region should be greater than 0 (zero). Parameter keys should not be null or empty.");

		int[] keys = new int[sshKeys.size()];
		for (int i = 0; i < sshKeys.size(); i++) {
			keys[i] = sshKeys.get(i);
		}

		return DigitalOceanAPI.createDroplet(name, size, image, region, keys);
	}

	public static boolean destroyDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.destroyDroplet(id);
	}

	public static boolean destroyDroplet(Droplet droplet) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return destroyDroplet(droplet.id);
	}

	public static boolean rebootDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.rebootDroplet(id);
	}
	
	public static boolean rebootDroplet(Droplet droplet) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return rebootDroplet(droplet.id);
	}

	public static boolean powerCycleDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.powerCycleDroplet(id);
	}
	
	public static boolean powerCycleDroplet(Droplet droplet) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return powerCycleDroplet(droplet.id);
	}

	public static boolean shutdownDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.shutdownDroplet(id);
	}
	
	public static boolean shutdownDroplet(Droplet droplet) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return shutdownDroplet(droplet.id);
	}

	public static boolean powerOffDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.powerOffDroplet(id);
	}
	
	public static boolean powerOffDroplet(Droplet droplet) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return powerOffDroplet(droplet.id);
	}

	public static boolean powerOnDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.powerOnDroplet(id);
	}
	
	public static boolean powerOnDroplet(Droplet droplet) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return powerOnDroplet(droplet.id);
	}

	public static boolean resetRootPasswordForDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.resetRootPasswordForDroplet(id);
	}
	
	public static boolean resetRootPasswordForDroplet(Droplet droplet) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return resetRootPasswordForDroplet(droplet.id);
	}

	public static boolean resizeDroplet(int droplet_id, int size_id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.resizeDroplet(droplet_id, size_id);
	}
	
	public static boolean resizeDroplet(Droplet droplet, Size size) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return resizeDroplet(droplet.id, size.id);
	}

	public static boolean takeSnapshotForDroplet(int id, String name) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.takeSnapshotForDroplet(id, name);
	}
	
	public static boolean takeSnapshotForDroplet(Droplet droplet, String name) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return takeSnapshotForDroplet(droplet.id, name);
	}

	public static boolean restoreDropletFromImage(int droplet_id, int image_id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.restoreDropletFromImage(droplet_id, image_id);
	}
	
	public static boolean restoreDropletFromImage(Droplet droplet, Image image) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return restoreDropletFromImage(droplet.id, image.id);
	}

	public static boolean rebuildDropletFromImage(int droplet_id, int image_id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.rebuildDropletFromImage(droplet_id, image_id);
	}
	
	public static boolean rebuildDropletFromImage(Droplet droplet, Image image) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return rebuildDropletFromImage(droplet.id, image.id);
	}

	public static boolean enableBackupsForDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.enableBackupsForDroplet(id);
	}
	
	public static boolean enableBackupsForDroplet(Droplet droplet) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return enableBackupsForDroplet(droplet.id);
	}

	public static boolean disableBackupsForDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return DigitalOceanAPI.disableBackupsForDroplet(id);
	}
	
	public static boolean disableBackupsForDroplet(Droplet droplet) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return disableBackupsForDroplet(droplet.id);
	}

}
