package by.kiselevich.xmlparser.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class XmlMedicinesValidator {

    private static final Logger LOG = LogManager.getLogger(XmlMedicinesValidator.class);

    private static final String SCHEMA_FILE = "medications.xsd";

    private Validator validator;

    public XmlMedicinesValidator() {
        URL schemaUrl = getClass().getClassLoader().getResource(SCHEMA_FILE);

        if (schemaUrl != null) {
            File schemaFile = new File(schemaUrl.getFile());
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            try {
                // https://owasp.org/www-project-cheat-sheets/cheatsheets/XML_External_Entity_Prevention_Cheat_Sheet
                factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
                factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

                Schema schema = factory.newSchema(schemaFile);
                validator = schema.newValidator();

                // https://owasp.org/www-project-cheat-sheets/cheatsheets/XML_External_Entity_Prevention_Cheat_Sheet
                validator.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
                validator.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            } catch (SAXException e) {
                LOG.error(e);
            }
        } else {
            LOG.warn("XML schema not found");
        }
    }

    public boolean isValid(String xmlFilePath) {
        try {
            Source source = new StreamSource(xmlFilePath);
            validator.validate(source);
            return true;
        } catch (SAXException | IOException e) {
            LOG.warn(e);
            return false;
        }
    }
}
