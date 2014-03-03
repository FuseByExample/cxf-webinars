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
package com.fusesource.customerwscamelcxfprovider;

import java.io.ByteArrayInputStream;
import javax.xml.transform.sax.SAXSource;
import org.apache.camel.Converter;
import org.xml.sax.InputSource;

@Converter
public class AdditionalConverters {
  
  @Converter
  public static SAXSource toSAXSource(String xml) { 
      return new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
  }
}
