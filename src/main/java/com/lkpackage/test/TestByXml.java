package com.lkpackage.test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/5/31 8:40
 */
public class TestByXml {
    public static void main(String[] args) {
//        System.out.println(TestByXml.class.getResource("/").getPath());
        String path = TestByXml.class.getResource("/").getPath();
        String file = "web.xml";
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(path + file);
            Element root = document.getRootElement();
            List<Element> servlets = root.elements("servlet");
            List<Element> mappings = root.elements("servlet-mapping");
            for (Element servlet : servlets) {
                Element name = servlet.element("servlet-name");
                Element servletClass = servlet.element("servlet-class");
                System.out.println(name.getText()+"==="+servletClass.getText());

            }
            for (Element mapping : mappings) {
                Element name = mapping.element("servlet-name");
                Element url = mapping.element("url-pattern");
                System.out.println(name.getText()+"==="+url.getText());
            }
            System.out.println(servlets.size());
            System.out.println(mappings.size());
            System.out.println(document);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
