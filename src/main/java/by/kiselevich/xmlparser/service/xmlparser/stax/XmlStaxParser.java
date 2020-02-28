package by.kiselevich.xmlparser.service.xmlparser.stax;

import by.kiselevich.xmlparser.entity.medicins.*;
import by.kiselevich.xmlparser.entity.medicins.Package;
import by.kiselevich.xmlparser.service.xmlparser.TagType;
import by.kiselevich.xmlparser.service.xmlparser.XmlParser;
import by.kiselevich.xmlparser.service.xmlparser.XmlParserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

public class XmlStaxParser implements XmlParser {

    private static final Logger LOG = LogManager.getLogger(XmlStaxParser.class);

    private static final int MEDICINE_ID_INDEX = 0;
    private static final int MEDICINE_NICKNAME_INDEX = 1;

    private ObjectFactory objectFactory;
    private DatatypeFactory datatypeFactory;

    private XMLInputFactory xmlInputFactory;
    private XMLStreamReader reader;

    private Medicines medicines;
    private Medicine currentMedicine;
    private TagType currentTag;
    private Versions currentVersions;
    private Version currentVersion;
    private Certificate currentCertificate;
    private Package currentPackage;
    private Dosage currentDosage;

    public XmlStaxParser() {
        xmlInputFactory = XMLInputFactory.newInstance();

        // disable resolving of external DTD entities to prevent XXE attacks
        xmlInputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);

        objectFactory = new ObjectFactory();
    }

    @Override
    public Medicines parse(String xmlFilePath) {
        medicines = new Medicines();

        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            LOG.warn(e);
            return medicines;
        }

        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(new File(xmlFilePath));
            reader = xmlInputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        currentTag = TagType.fromString(reader.getLocalName());
                        handleElement();
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        handleCharacters();
                        break;
                    default:
                        break;
                }
            }

        } catch (IOException | XMLStreamException e) {
            LOG.warn(e);
        }

        return medicines;
    }

    private void handleCharacters() {
        switch (currentTag) {
            case NAME:
                currentMedicine.setName(reader.getText());
                break;
            case PHARM:
                currentMedicine.setPharm(reader.getText());
                break;
            case GROUP:
                currentMedicine.setGroup(Group.fromValue(reader.getText()));
                break;
            case ANALOG:
                currentMedicine.getAnalogs().getAnalogId().add(objectFactory.createAnalogsAnalogId(
                        reader.getText()
                ));
                break;
            case FORM:
                currentVersion.setForm(Form.fromValue(reader.getText()));
                break;
            case MANUFACTURER:
                currentVersion.setManufacturer(reader.getText());
                break;
            case NUMBER:
                currentCertificate.setNumber(Integer.parseInt(reader.getText()));
                break;
            case START_DATE:
                currentCertificate.setStartDate(datatypeFactory.newXMLGregorianCalendar(
                        reader.getText()
                ));
                break;
            case END_DATE:
                currentCertificate.setEndDate(datatypeFactory.newXMLGregorianCalendar(
                        reader.getText()
                ));
                break;
            case ORGANIZATION:
                currentCertificate.setOrganization(reader.getText());
                break;
            case TYPE:
                currentPackage.setType(reader.getText());
                break;
            case AMOUNT:
                currentPackage.setAmount(Integer.parseInt(reader.getText()));
                break;
            case PRICE:
                currentPackage.setPrice(BigDecimal.valueOf(Double.parseDouble(
                        reader.getText()
                )));
                break;
            case COUNT:
                currentDosage.setCount(Integer.parseInt(reader.getText()));
                break;
            case PERIODICITY:
                currentDosage.setPeriodicity(Periodicity.fromValue(reader.getText()));
                break;
            default:
                break;
        }
        currentTag = TagType.INVALID_TAG;
    }

    private void handleElement() {
        switch (currentTag) {
            case MEDICINE:
                currentMedicine = new Medicine();
                currentMedicine.setId(reader.getAttributeValue(MEDICINE_ID_INDEX));
                currentMedicine.setNickname(reader.getAttributeValue(MEDICINE_NICKNAME_INDEX));
                medicines.getMedicine().add(currentMedicine);
                break;
            case ANALOGS:
                currentMedicine.setAnalogs(new Analogs());
                break;
            case VERSIONS:
                currentVersions = new Versions();
                currentMedicine.setVersions(currentVersions);
                break;
            case VERSION:
                currentVersion = new Version();
                currentVersions.getVersion().add(currentVersion);
                break;
            case CERTIFICATE:
                currentCertificate = new Certificate();
                currentVersion.setCertificate(currentCertificate);
                break;
            case PACKAGE:
                currentPackage = new Package();
                currentVersion.setPackageType(currentPackage);
                break;
            case DOSAGE:
                currentDosage = new Dosage();
                currentVersion.setDosage(currentDosage);
                break;
            default:
                break;
        }
    }

    @Override
    public XmlParserType getType() {
        return XmlParserType.STAX;
    }
}
