/**
 * Copyright 2011 FuseSource
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.fusesource.customer.client;

import com.fusesource.demo.wsdl.customerservice.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientInvoker implements Runnable {

  private static final Logger log
          = LoggerFactory.getLogger(ClientInvoker.class);

  private boolean shutdownInProgress = false;
  private Thread t;

  private CustomerService customerService;

  public void run() {
    while (! shutdownInProgress) {
      try {
        Thread.sleep(2000);

	/*
        com.fusesource.demo.customer.Customer response 
        	= customerService.lookupCustomer("1234");

        log.info("Got back " + response.getFirstName() + " "
                + response.getLastName()
                + ", ph:" + response.getPhoneNumber() );
        */

        javax.xml.ws.Holder<String> status = new javax.xml.ws.Holder<String>();
        javax.xml.ws.Holder<String> statusMessage = new javax.xml.ws.Holder<String>();

        customerService.getCustomerStatus("1234", status, statusMessage);

	log.info("Got back: status = " + status.value
            + ", statusMessage = " + statusMessage.value );
        
      } catch (InterruptedException ex) {
        log.warn("Unexpected InterruptedException. " + ex);
      } 
    }
  }

  public void init() {
    log.info("Starting  client invoker thread.");
    t = new Thread(this, "Customer web service invoker thread.");
    t.start();
  }

  public void destroy() {
    log.info("Signaling client invoker thread to shutdown...");
    shutdownInProgress = true;

    try {
      t.join();
    } catch (InterruptedException ex) {
      // Do nothing.
    }

    log.info("Thread stopped!");
  }

  public void setCustomerService(CustomerService customerService) {
    this.customerService = customerService;
  }

}
