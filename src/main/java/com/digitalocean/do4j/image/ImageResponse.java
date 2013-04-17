package com.digitalocean.do4j.image;

import com.google.api.client.util.Key;

public class ImageResponse {

	@Key
	public String status;

	@Key
	public Image image;

}
