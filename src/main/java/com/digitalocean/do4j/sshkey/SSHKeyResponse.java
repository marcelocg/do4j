package com.digitalocean.do4j.sshkey;

import com.google.api.client.util.Key;

public class SSHKeyResponse {

	@Key
	public String status;

	@Key
	public SSHKey ssh_key;

}
