
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

package com.fusesource.customerwscamelcxfpayload;

import java.util.ArrayList;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfPayload;
import org.apache.cxf.binding.soap.SoapHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CxfPayloadProcessor implements Processor {
    private static final Logger log = LoggerFactory.getLogger(CxfPayloadProcessor.class);

    public void process(Exchange exchng) throws Exception {
        Document xml = exchng.getIn().getBody(Document.class);
        List<Element> elements = new ArrayList<Element>();
        elements.add(xml.getDocumentElement());
        exchng.getOut().setBody(new CxfPayload<SoapHeader>(null, elements));
    }

}
