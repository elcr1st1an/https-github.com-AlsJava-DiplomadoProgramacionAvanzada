package edu.aluismarte.diplomado.week7;

import org.apache.xerces.parsers.SAXParser;
import org.jdom2.Document;
import org.jdom2.JDOMConstants;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderSAX2Factory;
import org.owasp.encoder.Encode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * @author Aluis Marte on 11/5/2021.
 * @see <a href="https://cheatsheetseries.owasp.org/cheatsheets/XML_External_Entity_Prevention_Cheat_Sheet.html#saxbuilder">XML External Entity Prevention</a>
 */
public class SecurityCase {

    private static final Logger logger = LoggerFactory.getLogger(SecurityCase.class);

    public Document getXML(String file) {
        return getXML(file, getSaxBuilder());
    }

    public Document getXML(String file, SAXBuilder saxBuilder) {
        try {
            String xml = readXML(file);
            logger.debug("Working with log secure {}", Encode.forXml(xml));
            if (xml == null) {
                return null;
            }
            return saxBuilder.build(new StringReader(xml));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private SAXBuilder getSaxBuilder() {
        SAXBuilder saxBuilder = new SAXBuilder(new XMLReaderSAX2Factory(false, SAXParser.class.getName()));
        saxBuilder.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        saxBuilder.setFeature(JDOMConstants.SAX_FEATURE_EXTERNAL_ENT, false);
        saxBuilder.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        saxBuilder.setExpandEntities(false);
        return saxBuilder;
    }

    private String readXML(String file) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(file);
            if (inputStream == null) {
                return null;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            inputStream.close();
            return stringBuilder.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
