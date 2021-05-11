/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.adobe.aem.guides.john.core.models;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.settings.SlingSettingsService;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import java.util.Optional;

//
// import javax.script.ScriptEngineManager;
// import javax.script.ScriptEngine;
import javax.script.*;

import java.io.FileReader;
import java.io.InputStream;
import javax.script.ScriptException;
import javax.script.Invocable;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.commons.osgi.PropertiesUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.Repository;

import org.apache.commons.io.IOUtils;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import java.util.Scanner;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;




import org.apache.sling.models.annotations.DefaultInjectionStrategy;

@Model(adaptables = Resource.class)
public class HelloWorldModel {

    @ValueMapValue(name=PROPERTY_RESOURCE_TYPE, injectionStrategy=InjectionStrategy.OPTIONAL)
    @Default(values="No resourceType")
    protected String resourceType;

    @OSGiService
    private SlingSettingsService settings;
    @SlingObject
    private Resource currentResource;
    @SlingObject
    private ResourceResolver resourceResolver;

    private String message;
    private String strHtml;

    public String getText() {
        return StringUtils.isNotBlank(this.strHtml) ? this.strHtml.toUpperCase() : null;
    }

    @PostConstruct
    protected void init() {

      //  Resource dataResource = resourceResolver.getResource("/etc/designs/todo/clientlib/js.txt"); 
        Resource dataResource = resourceResolver.getResource("/apps/john/clientlibs/clientlib-site/js.txt"); 

        InputStream stream = (InputStream)dataResource.adaptTo(InputStream.class);

        int[] av;
        String str = "";

        try {
            int length = stream.available();
            av = new int[length];
            for (int i = 0; i < length; i++) {
                av[i] = stream.read();
                str =  str + Character.toString(av[i]);
            }
        } catch(Exception e) {
            str = String.valueOf(e);
        }         
        message = str + "\n";
    }

    public String getMessage() {
        return message;
    }

}
