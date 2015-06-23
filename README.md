These demos have been built and tested using Red Hat JBoss Fuse 6.2.0 build 133

Prerequisites
-------------
Before building and running this example you need:

* Maven 3.0.4 or higher
* JDK 1.6
* JBoss Fuse 6.2.0

Building the examples
---------------------
The examples are built using Apache Maven. To build all of the examples (and install the
corresponding artifacts in your local Maven repository) enter the following command from
the top-level directory of the examples:

    $ mvn install

Configuring additional users
----------------------------
Edit the `$FUSE_HOME/etc/users.properties` and add a user called `admin`:

    admin=admin,admin

This will enable connecting to the embedded message broker.


Installing the customer-ws-osgi-bundle
--------------------------------------
The `customer-ws-osgi-bundle` deploys a simple web service listening on ServiceMix's
HTTP port (by default, this is port 8181). To install and start the bundle, just do 

    karaf@root> install -s mvn:com.fusesource.byexample.cxf-webinars/customer-ws-osgi-bundle

You will now find that the server is listening on `http://localhost:8181/cxf/Customer` 
- you can verify this quickly by pointing your browser at `http://localhost:8181/cxf/Customer?wsdl`.
You can test the service by using a tool such as [SoapUI](http://www.soapui.org)

Alternatively, you can install a bundle that creates a CXF client to this web 
service. 

    karaf@root> install -s mvn:com.fusesource.byexample.cxf-webinars/customer-ws-client

The bundle creates a thread that invokes on the web service once a second. It logs 
the response it gets; you can view the log using 

    karaf@root> log:display -n 10



Installing the camel-cxf routes
-------------------------------
To install the camel-cxf demos into JBoss Fuse, you need to install the relevant OSGi bundle
and any prerequisite features.

Note: You must stop the `customer-ws-osgi-bundle`, if you have alread installed and started it,
otherwise you will get a port conflict when a camel-cxf demo is started.

For example, to install the camel-cxf POJO route, do 

    karaf@root> features:install camel-cxf
    karaf@root> install -s mvn:com.fusesource.byexample.cxf-webinars/customer-ws-camel-cxf-pojo

To install the camel-cxf Payload route, do 

    karaf@root> features:install camel-cxf camel-velocity
    karaf@root> install -s mvn:com.fusesource.byexample.cxf-webinars/customer-ws-camel-cxf-payload

Finally, to install the camel-cxf Provider route, do 

    karaf@root> features:install camel-cxf camel-velocity
    karaf@root> install -s mvn:com.fusesource.byexample.cxf-webinars/customer-ws-camel-cxf-provider

