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
package com.fusesource.customerwscamelcxfpojo;

import com.fusesource.demo.customer.Customer;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LookupCustomerProcessor implements Processor {

    public static final Logger log = LoggerFactory.getLogger(LookupCustomerProcessor.class);

    public void process(Exchange exchng) throws Exception {

        Object[] args = exchng.getIn().getBody(Object[].class);
        String customerId = (String) args[0];

        Customer c = new Customer();
        c.setFirstName("Ade");
        c.setLastName("Trenaman");
        c.setId(customerId);
        c.setPhoneNumber("+353-1-01234567");

        exchng.getOut().setBody(new Object[] {c});
    }
}
