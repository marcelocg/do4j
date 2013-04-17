package com.digitalocean.do4j.main;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import com.digitalocean.do4j.droplet.Droplet;
import com.digitalocean.do4j.droplet.DropletList;
import com.digitalocean.do4j.droplet.DropletResponse;
import com.digitalocean.do4j.image.Image;
import com.digitalocean.do4j.image.ImageList;
import com.digitalocean.do4j.image.ImageResponse;
import com.digitalocean.do4j.main.api.exception.DigitalOceanAPIConfigurationErrorException;
import com.digitalocean.do4j.main.api.util.APIUtil;
import com.digitalocean.do4j.region.Region;
import com.digitalocean.do4j.region.RegionList;
import com.digitalocean.do4j.size.Size;
import com.digitalocean.do4j.size.SizeList;
import com.digitalocean.do4j.sshkey.SSHKey;
import com.digitalocean.do4j.sshkey.SSHKeyList;
import com.digitalocean.do4j.sshkey.SSHKeyResponse;
import com.google.api.client.http.HttpResponseException;

public class DigitalOceanAPI {

	private static final String DIGITAL_OCEAN_API_ENDPOINT = "https://api.digitalocean.com";

	protected static final String REGIONS_ENDPOINT  = DIGITAL_OCEAN_API_ENDPOINT + "/regions/";
	protected static final String SIZES_ENDPOINT    = DIGITAL_OCEAN_API_ENDPOINT + "/sizes/";
	protected static final String SSH_KEYS_ENDPOINT = DIGITAL_OCEAN_API_ENDPOINT + "/ssh_keys/";
	protected static final String IMAGES_ENDPOINT   = DIGITAL_OCEAN_API_ENDPOINT + "/images/";
	protected static final String DROPLETS_ENDPOINT = DIGITAL_OCEAN_API_ENDPOINT + "/droplets/";

	protected static List<Region> getAllRegions() throws IOException, DigitalOceanAPIConfigurationErrorException {
	    RegionList regionList = APIUtil.get(REGIONS_ENDPOINT).parseAs(RegionList.class);
	    return regionList.regions;
	}

	protected static List<Size> getAllSizes() throws IOException, DigitalOceanAPIConfigurationErrorException {
	    SizeList sizeList = APIUtil.get(SIZES_ENDPOINT).parseAs(SizeList.class);
	    return sizeList.sizes;
	}

	protected static List<Image> getAllImages() throws IOException, DigitalOceanAPIConfigurationErrorException {
	    ImageList imageList = APIUtil.get(IMAGES_ENDPOINT).parseAs(ImageList.class);
	    return imageList.images;
	}

	protected static List<Image> getMyImages() throws IOException, DigitalOceanAPIConfigurationErrorException {
	    ImageList imageList = APIUtil.get(IMAGES_ENDPOINT + "?filter="+DigitalOcean.FILTER_MY_IMAGES).parseAs(ImageList.class);
	    return imageList.images;
	}

	protected static List<Image> getGlobalImages() throws IOException, DigitalOceanAPIConfigurationErrorException {
	    ImageList imageList = APIUtil.get(IMAGES_ENDPOINT + "?filter="+DigitalOcean.FILTER_GLOBAL_IMAGES).parseAs(ImageList.class);
	    return imageList.images;
	}

	protected static Image getImage(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
	    ImageResponse response;
		try {
			response = APIUtil.get(IMAGES_ENDPOINT + id + "/").parseAs(ImageResponse.class);
		} catch (HttpResponseException ex) {
			if(ex.getStatusCode() == 404) {
				response = new ImageResponse();
				response.image = null;
			} else {
				throw ex;
			}
		}
	    return response.image;
	}

	protected static boolean destroyImage(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
	    ImageResponse response = APIUtil.get(IMAGES_ENDPOINT + id + "/destroy/").parseAs(ImageResponse.class);
	    return response.status.equals("OK");
	}
	
	protected static boolean destroyImage(Image image) throws IOException, DigitalOceanAPIConfigurationErrorException {
		return destroyImage(image.id);
	}

	protected static List<SSHKey> getAllSSHKeys() throws IOException, DigitalOceanAPIConfigurationErrorException {
	    SSHKeyList sshKeyList = APIUtil.get(SSH_KEYS_ENDPOINT).parseAs(SSHKeyList.class);
	    return sshKeyList.ssh_keys;
	}

	protected static SSHKey getSSHKey(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		SSHKeyResponse response;
		try {
			response = APIUtil.get(SSH_KEYS_ENDPOINT + id + "/").parseAs(SSHKeyResponse.class);
		} catch (HttpResponseException ex) {
			if(ex.getStatusCode() == 404) {
				response = new SSHKeyResponse();
				response.ssh_key = null;
			} else {
				throw ex;
			}
		}
	    return response.ssh_key;
	}

	protected static SSHKey addSSHKey(String name, String public_key) throws IOException, DigitalOceanAPIConfigurationErrorException {
		SSHKeyResponse response;
		try {
			response = APIUtil.get(SSH_KEYS_ENDPOINT + "new/?name="+ name + "&ssh_pub_key="+ URLEncoder.encode(public_key, "UTF-8")).parseAs(SSHKeyResponse.class);
		} catch (HttpResponseException ex) {
			if(ex.getStatusCode() == 404) {
				response = new SSHKeyResponse();
				response.ssh_key = null;
			} else {
				throw ex;
			}
		}
	    return response.ssh_key;
	}

	protected static SSHKey updateSSHKey(int id, String public_key) throws IOException, DigitalOceanAPIConfigurationErrorException {
		SSHKeyResponse response;
		try {
			response = APIUtil.get(SSH_KEYS_ENDPOINT + id + "/edit/" + "?ssh_pub_key="+ URLEncoder.encode(public_key, "UTF-8")).parseAs(SSHKeyResponse.class);
		} catch (HttpResponseException ex) {
			if(ex.getStatusCode() == 404) {
				response = new SSHKeyResponse();
				response.ssh_key = null;
			} else {
				throw ex;
			}
		}
	    return response.ssh_key;
	}

	protected static boolean destroySSHKey(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
	    SSHKeyResponse response = APIUtil.get(SSH_KEYS_ENDPOINT + id + "/destroy/").parseAs(SSHKeyResponse.class);
	    return response.status.equals("OK");
	}

	protected static List<Droplet> getDroplets() throws IOException, DigitalOceanAPIConfigurationErrorException {
	    DropletList dropletList = APIUtil.get(DROPLETS_ENDPOINT).parseAs(DropletList.class);
	    return dropletList.droplets;
	}

	protected static Droplet getDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
		DropletResponse response;
		try {
			response = APIUtil.get(DROPLETS_ENDPOINT + id + "/").parseAs(DropletResponse.class);
		} catch (HttpResponseException ex) {
			if(ex.getStatusCode() == 404) {
				response = new DropletResponse();
				response.droplet = null;
			} else {
				throw ex;
			}
		}
	    return response.droplet;
	}

	protected static Droplet createDroplet(String name, int size, int image, int region, int[] keys) throws IOException, DigitalOceanAPIConfigurationErrorException {
		DropletResponse response;
		String strKeys = "";
		if(keys != null) {
			strKeys = "&ssh_key_ids="+String.valueOf(keys[0]);
			for (int i = 1; i < keys.length; i++) {
				strKeys.concat("," + keys[i]);
			}
		}
		response = APIUtil.get(DROPLETS_ENDPOINT + "new/?name=" + name + "&size_id=" + size + "&image_id=" + image + "&region_id=" + region + strKeys).parseAs(DropletResponse.class);
		
		return response.droplet;
	}

	protected static boolean destroyDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
	    DropletResponse response = APIUtil.get(DROPLETS_ENDPOINT + id + "/destroy/").parseAs(DropletResponse.class);
	    return response.status.equals("OK");
	}

	protected static boolean rebootDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
	    DropletResponse response = APIUtil.get(DROPLETS_ENDPOINT + id + "/reboot/").parseAs(DropletResponse.class);
	    return response.status.equals("OK");
	}

	protected static boolean powerCycleDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
	    DropletResponse response = APIUtil.get(DROPLETS_ENDPOINT + id + "/power_cycle/").parseAs(DropletResponse.class);
	    return response.status.equals("OK");
	}

	protected static boolean shutdownDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
	    DropletResponse response = APIUtil.get(DROPLETS_ENDPOINT + id + "/shutdown/").parseAs(DropletResponse.class);
	    return response.status.equals("OK");
	}

	protected static boolean powerOffDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
	    DropletResponse response = APIUtil.get(DROPLETS_ENDPOINT + id + "/power_off/").parseAs(DropletResponse.class);
	    return response.status.equals("OK");
	}

	protected static boolean powerOnDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
	    DropletResponse response = APIUtil.get(DROPLETS_ENDPOINT + id + "/power_on/").parseAs(DropletResponse.class);
	    return response.status.equals("OK");
	}

	protected static boolean resetRootPasswordForDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
	    DropletResponse response = APIUtil.get(DROPLETS_ENDPOINT + id + "/password_reset/").parseAs(DropletResponse.class);
	    return response.status.equals("OK");
	}

	protected static boolean resizeDroplet(int droplet_id, int size_id) throws IOException, DigitalOceanAPIConfigurationErrorException {
	    DropletResponse response = APIUtil.get(DROPLETS_ENDPOINT + droplet_id + "/resize/?size_id=" + size_id).parseAs(DropletResponse.class);
	    return response.status.equals("OK");
	}

	protected static boolean takeSnapshotForDroplet(int id, String name) throws IOException, DigitalOceanAPIConfigurationErrorException {
	    DropletResponse response;
	    if(name == null || name.equals(""))
	    	response = APIUtil.get(DROPLETS_ENDPOINT + id + "/snapshot/").parseAs(DropletResponse.class);
	    else
	    	response = APIUtil.get(DROPLETS_ENDPOINT + id + "/snapshot/?name="+URLEncoder.encode(name, "UTF-8")).parseAs(DropletResponse.class);
	    return response.status.equals("OK");
	}

	protected static boolean restoreDropletFromImage(int droplet_id, int image_id) throws IOException, DigitalOceanAPIConfigurationErrorException {
	    DropletResponse response = APIUtil.get(DROPLETS_ENDPOINT + droplet_id + "/restore/?image_id=" + image_id).parseAs(DropletResponse.class);
	    return response.status.equals("OK");
	}

	protected static boolean rebuildDropletFromImage(int droplet_id, int image_id) throws IOException, DigitalOceanAPIConfigurationErrorException {
	    DropletResponse response = APIUtil.get(DROPLETS_ENDPOINT + droplet_id + "/rebuild/?image_id=" + image_id).parseAs(DropletResponse.class);
	    return response.status.equals("OK");
	}

	protected static boolean enableBackupsForDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
	    DropletResponse response = APIUtil.get(DROPLETS_ENDPOINT + id + "/enable_backups/").parseAs(DropletResponse.class);
	    return response.status.equals("OK");
	}

	protected static boolean disableBackupsForDroplet(int id) throws IOException, DigitalOceanAPIConfigurationErrorException {
	    DropletResponse response = APIUtil.get(DROPLETS_ENDPOINT + id + "/disable_backups/").parseAs(DropletResponse.class);
	    return response.status.equals("OK");
	}

}
