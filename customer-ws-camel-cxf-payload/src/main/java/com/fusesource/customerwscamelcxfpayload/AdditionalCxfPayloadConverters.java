
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

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.camel.Converter;
import org.apache.camel.component.cxf.CxfPayload;
import org.apache.cxf.binding.soap.SoapHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

@Converter
public class AdditionalCxfPayloadConverters {

    private static final Logger log = LoggerFactory.getLogger(AdditionalCxfPayloadConverters.class);
    private static javax.xml.parsers.DocumentBuilderFactory b = javax.xml.parsers.DocumentBuilderFactory.newInstance();

    @Converter
    public static CxfPayload<SoapHeader> toCxfPayload(String xml) {
        // System.out.println("To CxfPayload " + xml);
        List<Element> elements = new ArrayList<Element>();
        try {
            Document doc = b.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));
            elements.add(doc.getDocumentElement());
        } catch (Exception ex) {
            log.warn("Exception while converting String payload to CxfPayload; resulting payload will be empty.");
        }
        // The CxfPayload is changed to use Source object under layer, the elements API only work if we already setup the list before creating the CxfPayload
        CxfPayload<SoapHeader> ret = new CxfPayload<SoapHeader>(null, elements);
        return ret;
    }

    private static TransformerFactory transFactory = TransformerFactory.newInstance();

    @Converter
    public static String toString(CxfPayload<SoapHeader> payload) {
        StringBuilder sb = new StringBuilder();

        List<Element> elements = payload.getBody();
        for (Element e : elements) {
            try {
                Transformer transformer = transFactory.newTransformer();
                StringWriter buffer = new StringWriter();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                transformer.transform(new DOMSource(e),
                        new StreamResult(buffer));
                sb.append(buffer.toString());
            } catch (Exception ex) {
                log.warn("Warning: problems producing XML String content : " + ex.getMessage());
            }
        }

        return sb.toString();
    }

    @Converter
    public static Node toNode(CxfPayload<SoapHeader> payload) {
        return payload.getBody().get(0);
    }

}
