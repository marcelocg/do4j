package com.digitalocean.do4j.size;

import java.util.List;

import com.google.api.client.util.Key;

public class SizeList {

	@Key
	public String status;
	
	@Key
	public List<Size> sizes;
	
}
