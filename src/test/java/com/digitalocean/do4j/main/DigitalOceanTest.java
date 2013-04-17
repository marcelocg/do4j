package com.digitalocean.do4j.main;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.digitalocean.do4j.image.Image;
import com.digitalocean.do4j.main.api.exception.DigitalOceanAPIConfigurationErrorException;
import com.digitalocean.do4j.main.api.exception.DigitalOceanAPIParameterErrorException;
import com.digitalocean.do4j.region.Region;
import com.digitalocean.do4j.size.Size;
import com.digitalocean.do4j.sshkey.SSHKey;

public class DigitalOceanTest extends Assert {
  
  @Test(groups = "Regions")
  public void getRegions() throws IOException, DigitalOceanAPIConfigurationErrorException {
    List<Region> regions = DigitalOcean.getRegions();
    assertNotEquals(regions.size(), 0);
  }

  @Test(groups = "Sizes")
  public void getSizes() throws IOException, DigitalOceanAPIConfigurationErrorException {
    List<Size> sizes = DigitalOcean.getSizes();
    assertNotEquals(sizes.size(), 0);
  }

  @Test(groups = "SSHKeys", expectedExceptions = DigitalOceanAPIParameterErrorException.class)
  public void addSSHKeyWithoutName() throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException{
    DigitalOcean.addSSHKey("", "ssh-dss AAAAB3NzaC1kc3MAAACBAK5uLwicCrFEpaVKBzkWxC7RQn+smg5ZQb5keh9RQKo8AszFTol5npgUAr0JWmqKIHv7nof0HndO86x9iIqNjq3vrz9CIVcFfZM7poKBJZ27Hv3v0fmSKfAc6eGdx8eM9UkZe1gzcLXK8UP2HaeY1Y4LlaHXS5tPi/dXooFVgiA7AAAAFQCQl6LZo/VYB9VgPEZzOmsmQevnswAAAIBCNKGsVP5eZ+IJklXheUyzyuL75i04OOtEGW6MO5TymKMwTZlU9r4ukuwxty+T9Ot2LqlNRnLSPQUjb0vplasZ8Ix45JOpRbuSvPovryn7rvS7//klu9hIkFAAQ/AZfGTw+696EjFBg4F5tN6MGMA6KrTQVLXeuYcZeRXwE5t5lwAAAIEAl2xYh098bozJUANQ82DiZznjHc5FW76Xm1apEqsZtVRFuh3V9nc7QNcBekhmHp5Z0sHthXCm1XqnFbkRCdFlX02NpgtNs7OcKpaJP47N8C+C/Yrf8qK/Wt3fExrL2ZLX5XD2tiotugSkwZJMW5Bv0mtjrNt0Q7P45rZjNNTag2c= user@host");
  }

  @Test(groups = "SSHKeys", expectedExceptions = DigitalOceanAPIParameterErrorException.class)
  public void addSSHKeyWithoutKey() throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException{
    DigitalOcean.addSSHKey("my_key", "");
  }
  
  @Test(groups = "SSHKeys", expectedExceptions = DigitalOceanAPIParameterErrorException.class)
  public void updateSSHKeyWithoutKey() throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException{
    DigitalOcean.addSSHKey("my_key", "");
  }
  
  @Test(groups = "SSHKeys")
  public void addSSHKey() throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
    SSHKey key = DigitalOcean.addSSHKey("my_key", "ssh-dss AAAAB3NzaC1kc3MAAACBAK5uLwicCrFEpaVKBzkWxC7RQn+smg5ZQb5keh9RQKo8AszFTol5npgUAr0JWmqKIHv7nof0HndO86x9iIqNjq3vrz9CIVcFfZM7poKBJZ27Hv3v0fmSKfAc6eGdx8eM9UkZe1gzcLXK8UP2HaeY1Y4LlaHXS5tPi/dXooFVgiA7AAAAFQCQl6LZo/VYB9VgPEZzOmsmQevnswAAAIBCNKGsVP5eZ+IJklXheUyzyuL75i04OOtEGW6MO5TymKMwTZlU9r4ukuwxty+T9Ot2LqlNRnLSPQUjb0vplasZ8Ix45JOpRbuSvPovryn7rvS7//klu9hIkFAAQ/AZfGTw+696EjFBg4F5tN6MGMA6KrTQVLXeuYcZeRXwE5t5lwAAAIEAl2xYh098bozJUANQ82DiZznjHc5FW76Xm1apEqsZtVRFuh3V9nc7QNcBekhmHp5Z0sHthXCm1XqnFbkRCdFlX02NpgtNs7OcKpaJP47N8C+C/Yrf8qK/Wt3fExrL2ZLX5XD2tiotugSkwZJMW5Bv0mtjrNt0Q7P45rZjNNTag2c= user@host");
    assertNotNull(key);
    DigitalOcean.destroySSHKey(key.id);
  }

  @Test(groups = "SSHKeys", dependsOnMethods={"addSSHKey"})
  public void getSSHKeys() throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
    SSHKey key = DigitalOcean.addSSHKey("my_key", "ssh-dss AAAAB3NzaC1kc3MAAACBAK5uLwicCrFEpaVKBzkWxC7RQn+smg5ZQb5keh9RQKo8AszFTol5npgUAr0JWmqKIHv7nof0HndO86x9iIqNjq3vrz9CIVcFfZM7poKBJZ27Hv3v0fmSKfAc6eGdx8eM9UkZe1gzcLXK8UP2HaeY1Y4LlaHXS5tPi/dXooFVgiA7AAAAFQCQl6LZo/VYB9VgPEZzOmsmQevnswAAAIBCNKGsVP5eZ+IJklXheUyzyuL75i04OOtEGW6MO5TymKMwTZlU9r4ukuwxty+T9Ot2LqlNRnLSPQUjb0vplasZ8Ix45JOpRbuSvPovryn7rvS7//klu9hIkFAAQ/AZfGTw+696EjFBg4F5tN6MGMA6KrTQVLXeuYcZeRXwE5t5lwAAAIEAl2xYh098bozJUANQ82DiZznjHc5FW76Xm1apEqsZtVRFuh3V9nc7QNcBekhmHp5Z0sHthXCm1XqnFbkRCdFlX02NpgtNs7OcKpaJP47N8C+C/Yrf8qK/Wt3fExrL2ZLX5XD2tiotugSkwZJMW5Bv0mtjrNt0Q7P45rZjNNTag2c= user@host");
    List<SSHKey> keys = DigitalOcean.getSSHKeys();
    assertNotEquals(keys.size(), 0);
    DigitalOcean.destroySSHKey(key.id);
  }

  @Test(groups = "SSHKeys", dependsOnMethods={"addSSHKey"})
  public void getSSHKey() throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
    SSHKey sshkey = DigitalOcean.addSSHKey("my_key", "ssh-dss AAAAB3NzaC1kc3MAAACBAK5uLwicCrFEpaVKBzkWxC7RQn+smg5ZQb5keh9RQKo8AszFTol5npgUAr0JWmqKIHv7nof0HndO86x9iIqNjq3vrz9CIVcFfZM7poKBJZ27Hv3v0fmSKfAc6eGdx8eM9UkZe1gzcLXK8UP2HaeY1Y4LlaHXS5tPi/dXooFVgiA7AAAAFQCQl6LZo/VYB9VgPEZzOmsmQevnswAAAIBCNKGsVP5eZ+IJklXheUyzyuL75i04OOtEGW6MO5TymKMwTZlU9r4ukuwxty+T9Ot2LqlNRnLSPQUjb0vplasZ8Ix45JOpRbuSvPovryn7rvS7//klu9hIkFAAQ/AZfGTw+696EjFBg4F5tN6MGMA6KrTQVLXeuYcZeRXwE5t5lwAAAIEAl2xYh098bozJUANQ82DiZznjHc5FW76Xm1apEqsZtVRFuh3V9nc7QNcBekhmHp5Z0sHthXCm1XqnFbkRCdFlX02NpgtNs7OcKpaJP47N8C+C/Yrf8qK/Wt3fExrL2ZLX5XD2tiotugSkwZJMW5Bv0mtjrNt0Q7P45rZjNNTag2c= user@host");
    SSHKey key = DigitalOcean.getSSHKey(sshkey.id);
    assertNotNull(key);
    DigitalOcean.destroySSHKey(key.id);
  }

  @Test(groups = "SSHKeys", dependsOnMethods={"addSSHKey"})
  public void updateSSHKey() throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
    SSHKey key = DigitalOcean.addSSHKey("my_key", "ssh-dss AAAAB3NzaC1kc3MAAACBAK5uLwicCrFEpaVKBzkWxC7RQn+smg5ZQb5keh9RQKo8AszFTol5npgUAr0JWmqKIHv7nof0HndO86x9iIqNjq3vrz9CIVcFfZM7poKBJZ27Hv3v0fmSKfAc6eGdx8eM9UkZe1gzcLXK8UP2HaeY1Y4LlaHXS5tPi/dXooFVgiA7AAAAFQCQl6LZo/VYB9VgPEZzOmsmQevnswAAAIBCNKGsVP5eZ+IJklXheUyzyuL75i04OOtEGW6MO5TymKMwTZlU9r4ukuwxty+T9Ot2LqlNRnLSPQUjb0vplasZ8Ix45JOpRbuSvPovryn7rvS7//klu9hIkFAAQ/AZfGTw+696EjFBg4F5tN6MGMA6KrTQVLXeuYcZeRXwE5t5lwAAAIEAl2xYh098bozJUANQ82DiZznjHc5FW76Xm1apEqsZtVRFuh3V9nc7QNcBekhmHp5Z0sHthXCm1XqnFbkRCdFlX02NpgtNs7OcKpaJP47N8C+C/Yrf8qK/Wt3fExrL2ZLX5XD2tiotugSkwZJMW5Bv0mtjrNt0Q7P45rZjNNTag2c= user@host");
    assertNotNull(DigitalOcean.updateSSHKey(key.id, "ssh-dss 123456789"));
    DigitalOcean.destroySSHKey(key.id);
  }

  @Test(groups = "SSHKeys", dependsOnMethods={"addSSHKey"})
  public void destroySSHKey() throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
    SSHKey key = DigitalOcean.addSSHKey("my_key", "ssh-dss AAAAB3NzaC1kc3MAAACBAK5uLwicCrFEpaVKBzkWxC7RQn+smg5ZQb5keh9RQKo8AszFTol5npgUAr0JWmqKIHv7nof0HndO86x9iIqNjq3vrz9CIVcFfZM7poKBJZ27Hv3v0fmSKfAc6eGdx8eM9UkZe1gzcLXK8UP2HaeY1Y4LlaHXS5tPi/dXooFVgiA7AAAAFQCQl6LZo/VYB9VgPEZzOmsmQevnswAAAIBCNKGsVP5eZ+IJklXheUyzyuL75i04OOtEGW6MO5TymKMwTZlU9r4ukuwxty+T9Ot2LqlNRnLSPQUjb0vplasZ8Ix45JOpRbuSvPovryn7rvS7//klu9hIkFAAQ/AZfGTw+696EjFBg4F5tN6MGMA6KrTQVLXeuYcZeRXwE5t5lwAAAIEAl2xYh098bozJUANQ82DiZznjHc5FW76Xm1apEqsZtVRFuh3V9nc7QNcBekhmHp5Z0sHthXCm1XqnFbkRCdFlX02NpgtNs7OcKpaJP47N8C+C/Yrf8qK/Wt3fExrL2ZLX5XD2tiotugSkwZJMW5Bv0mtjrNt0Q7P45rZjNNTag2c= user@host");
    assertTrue(DigitalOcean.destroySSHKey(key.id));
  }

  @Test(groups = "Images")
  public void getAllImages() throws IOException, DigitalOceanAPIConfigurationErrorException {
    List<Image> images = DigitalOcean.getImages();
    assertNotEquals(images.size(), 0);
  }

  @Test(groups = "Images")
  public void getImage() throws IOException, DigitalOceanAPIConfigurationErrorException {
    Image image = DigitalOcean.getImage(2676); // Ubuntu 12.04 64bits
    assertNotNull(image);
    assertEquals(image.id, 2676);
    assertNotNull(image.name);
    assertNotEquals(image.name, "");
  }

  @Test(groups = "Images")
  public void getMyImages() throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
    List<Image> images = DigitalOcean.getImages(DigitalOcean.FILTER_MY_IMAGES);
    assertNotNull(images);
  }

  @Test(groups = "Images")
  public void getGlobalImages() throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
    List<Image> images = DigitalOcean.getImages(DigitalOcean.FILTER_GLOBAL_IMAGES);
    assertNotEquals(images.size(), 0);
  }
  
  @Test(groups = "Images", expectedExceptions = DigitalOceanAPIParameterErrorException.class)
  public void getImagesWrongParameter() throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
    DigitalOcean.getImages("WRONG_PARAMETER");
  }

  @Test(groups = "Images", expectedExceptions = DigitalOceanAPIParameterErrorException.class)
  public void getImagesEmptyParameter() throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
    DigitalOcean.getImages("");
  }

  @Test(groups = "Images", expectedExceptions = DigitalOceanAPIParameterErrorException.class)
  public void getImagesNullParameter() throws IOException, DigitalOceanAPIParameterErrorException, DigitalOceanAPIConfigurationErrorException {
    DigitalOcean.getImages(null);
  }

  @Test(groups = "Images")
  public void destroyImage() {
    throw new RuntimeException("Test not implemented");
  }


  @Test(groups = "Droplets")
  public void createDropletStringintintint() {
    throw new RuntimeException("Test not implemented");
  }

  @Test(groups = "Droplets")
  public void createDropletStringintintintListInteger() {
    throw new RuntimeException("Test not implemented");
  }

  @Test(groups = "Droplets")
  public void destroyDropletint() {
    throw new RuntimeException("Test not implemented");
  }

  @Test(groups = "Droplets")
  public void disableBackupsForDropletint() {
    throw new RuntimeException("Test not implemented");
  }

  @Test(groups = "Droplets")
  public void enableBackupsForDropletint() {
    throw new RuntimeException("Test not implemented");
  }

  @Test(groups = "Droplets")
  public void getDroplet() {
    throw new RuntimeException("Test not implemented");
  }

  @Test(groups = "Droplets")
  public void getDroplets() {
    throw new RuntimeException("Test not implemented");
  }

  @Test(groups = "Droplets")
  public void powerCycleDropletint() {
    throw new RuntimeException("Test not implemented");
  }

  @Test(groups = "Droplets")
  public void powerOffDropletint() {
    throw new RuntimeException("Test not implemented");
  }

  @Test(groups = "Droplets")
  public void powerOnDropletint() {
    throw new RuntimeException("Test not implemented");
  }

  @Test(groups = "Droplets")
  public void rebootDropletint() {
    throw new RuntimeException("Test not implemented");
  }

  @Test(groups = "Droplets")
  public void rebuildDropletFromImageintint() {
    throw new RuntimeException("Test not implemented");
  }

  @Test(groups = "Droplets")
  public void resetRootPasswordForDropletint() {
    throw new RuntimeException("Test not implemented");
  }

  @Test(groups = "Droplets")
  public void resizeDropletintint() {
    throw new RuntimeException("Test not implemented");
  }

  @Test(groups = "Droplets")
  public void restoreDropletFromImageintint() {
    throw new RuntimeException("Test not implemented");
  }

  @Test(groups = "Droplets")
  public void shutdownDropletint() {
    throw new RuntimeException("Test not implemented");
  }

  @Test(groups = "Droplets")
  public void takeSnapshotForDroplet() {
    throw new RuntimeException("Test not implemented");
  }

}
