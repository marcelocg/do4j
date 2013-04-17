# DO4J

DO4J - DigitalOcean for Java - A Java wrapper for the DigitalOcean API

[DigitalOcean](http://www.digitalocean.com) is the coolest IaaS cloud provider all around. Built for developers, they offer SSD based virtual machines at initial $5.00/mo! And it's not all! They offer fantastic support, there are lots of tutorials on how to setup your droplets (machines) and they even send you some stickers now and then (I've got mine by mail here in Brazil)!

**DO4J (DigitalOcean for Java)** is a Java library that allows you to control all your resources at DigitalOcean. You can create, stop, restart and destroy your droplets, search through your own snapshots and backup images, send and update you SSHKeys and much more. All through the web API DigitalOcean has published. Take a look!

## Configuration

First you have to setup your API key and Client Id so **DO4J** can get access to your DigitalOcean account. This can be made by creating two environment variables:

* DIGITAL\_OCEAN\_API\_KEY
* DIGITAL\_OCEAN\_CLIENT\_ID

You can get your key and id right from DigitalOcean Control Panel. Just log in to your account and go to <https://www.digitalocean.com/api_access>. Then put those values into the variables and you're all set. 

## Put do4j to the Action
You can always look at the test classes to know more about how to use **DO4J**.


-- *Note: Tests are not yet complete but feel free to fork and contribute :-)*

Even so, here comes a little taste of what you can get from this library:

    List<Droplet> droplets = DigitalOcean.getDroplets();
    for (Droplet droplet : droplet) {
        System.out.println("ID: " + droplet.id);
        System.out.println("Name: " + droplet.name);
        System.out.println("Status: " + droplet.status);
        System.out.println("Backups Active? " + droplet.backups_active);
    }

    // Get a reference for an existing droplet
    Droplet webserver = DigitalOcean.getDroplet(12345);
    webserver.powerOn();

    // create a 512MB RAM droplet with Ubuntu 12.04 64b Server at New York
    Droplet database = DigitalOcean.createDroplet("Database", 66, 2676, 1);
    database.enableBackups();

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request

## License

### The MIT License

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
'Software'), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
