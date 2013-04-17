package com.digitalocean.do4j.image;

import java.util.List;

import com.google.api.client.util.Key;

public class ImageList {

	@Key
	public String status;
	
	@Key
	public List<Image> images;
	
}
