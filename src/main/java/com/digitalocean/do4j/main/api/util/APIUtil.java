package com.digitalocean.do4j.main.api.util;

import java.io.IOException;

import com.digitalocean.do4j.main.api.exception.DigitalOceanAPIConfigurationErrorException;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Key;

public class APIUtil {

  static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  static final JsonFactory JSON_FACTORY = new JacksonFactory();

  private static final String CLIENT_ID = System.getenv("DIGITAL_OCEAN_CLIENT_ID");
  private static final String API_KEY = System.getenv("DIGITAL_OCEAN_API_KEY");

  public static boolean isConnected() {
    return (CLIENT_ID != null && API_KEY != null);
  }

  private static HttpRequestFactory getRequestFactory() {
    HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
      public void initialize(HttpRequest request) throws IOException {
        request.setParser(new JsonObjectParser(JSON_FACTORY));
      }
    });

    return requestFactory;
  }

  public static HttpResponse get(String endpoint) throws IOException, DigitalOceanAPIConfigurationErrorException {
    if(!isConnected()) {
      throw new DigitalOceanAPIConfigurationErrorException("Configuration error: please setup the CLIENT_ID and API_KEY environment variables correctly.");
    }
    DigitalOceanAPIEndpointUrl url = new DigitalOceanAPIEndpointUrl(endpoint);
    url.client_id = CLIENT_ID;
    url.api_key = API_KEY;
    System.out.println("Calling endpoint: " + url.build());

    HttpRequest request = getRequestFactory().buildGetRequest(url);
    return request.execute();
  }

  public static class DigitalOceanAPIEndpointUrl extends GenericUrl {

    public DigitalOceanAPIEndpointUrl(String encodedUrl) {
      super(encodedUrl);
    }

    @Key
    public String client_id;

    @Key
    public String api_key;
  }

}
