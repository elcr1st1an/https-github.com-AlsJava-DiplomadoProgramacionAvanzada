package edu.aluismarte.diplomado.week7;

import edu.aluismarte.diplomado.utils.TimeExtension;
import org.apache.xerces.parsers.SAXParser;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderSAX2Factory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Aluis Marte on 11/5/2021.
 */
@ExtendWith(TimeExtension.class)
class SecurityCaseTest {

    private final SecurityCase securityCase = new SecurityCase();

    @Test
    void secureParserAndSecureXMLTest() {
        Document xml = securityCase.getXML("/SecuredXML.xml");
        assertNotNull(xml);
        printDocument(xml);
    }

    @Test
    void secureParserAndInsecureXMLTest() {
        Document xml = securityCase.getXML("/UnsecuredXML.xml");
        assertNull(xml); // Have to be null
    }

    @Test
    void insecureParserAndInsecureXMLTest() {
        SAXBuilder saxBuilder = new SAXBuilder(new XMLReaderSAX2Factory(false, SAXParser.class.getName()));
        Document xml = securityCase.getXML("/UnsecuredXML.xml", saxBuilder);
        assertNotNull(xml);
        printDocument(xml);
    }

    @Test
    void insecureParserAndSecureXMLTest() {
        SAXBuilder saxBuilder = new SAXBuilder(new XMLReaderSAX2Factory(false, SAXParser.class.getName()));
        Document xml = securityCase.getXML("/SecuredXML.xml", saxBuilder);
        assertNotNull(xml);
        printDocument(xml);
    }

    private void printDocument(Document xml) {
        Element rootElement = xml.getRootElement();
        System.out.println("Root element :" + rootElement.getName());
        for (Content content : rootElement.getContent()) {
            System.out.println("Content:" + content.getValue());
        }
    }

}