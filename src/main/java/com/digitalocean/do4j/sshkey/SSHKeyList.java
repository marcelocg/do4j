package com.digitalocean.do4j.sshkey;

import java.util.List;

import com.google.api.client.util.Key;

public class SSHKeyList {

	@Key
	public String status;
	
	@Key
	public List<SSHKey> ssh_keys;
	
}
