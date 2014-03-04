These demos have been built and tested using JBoss Fuse 6.1 (beta) build 337

Building the examples
---------------------
The examples are built using Apache Maven. To build all of the examples (and install the
corresponding artifacts in your local Maven repository) enter the following commands from
the top-level directory of the examples:

    $ cd parent
    $ mvn install
    $ cd ..
    $ mvn install


Installing the customer-ws-osgi-bundle
--------------------------------------
The 'customer-ws-osgi-bundle' deploys a simple web service listening on ServiceMix's
HTTP port (by default, this is port 8181). To install and start the bundle, just do 

    karaf@root> install -s mvn:com.fusesource.byexample.cxf-webinars/customer-ws-osgi-bundle

You will now find that the server is listening on 'http://localhost:8181/cxf/Customer' 
- you can verify this quickly by pointing your browser at 'http://localhost:8181/cxf/Customer?wsdl'.
You can test the service by using a tool such as SoapUI from http://www.soapui.org. 

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

For example, to install the camel-cxf POJO route, do 

    karaf@root> features:install camel-cxf
    karaf@root> install -s mvn:com.fusesource.byexample.cxf-webinars/customer-ws-camel-cxf-pojo

To install the camel-cxf Payload route, do 

    karaf@root> features:install camel-cxf
    karaf@root> features:install camel-velocity
    karaf@root> install -s mvn:com.fusesource.byexample.cxf-webinars/customer-ws-camel-cxf-payload

Finally, to install the camel-cxf Provider route, do 

    karaf@root> features:install camel-cxf
    karaf@root> features:install camel-velocity
    karaf@root> install -s mvn:com.fusesource.byexample.cxf-webinars/customer-ws-camel-cxf-provider


