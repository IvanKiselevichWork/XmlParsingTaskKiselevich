package by.kiselevich.xmlparser.service.xmlparser.sax;

import by.kiselevich.xmlparser.entity.medicins.*;
import by.kiselevich.xmlparser.entity.medicins.Package;
import by.kiselevich.xmlparser.service.xmlparser.TagType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigDecimal;
import java.util.List;

public class SaxHandler extends DefaultHandler {

    private ObjectFactory objectFactory;
    private DatatypeFactory datatypeFactory;
    private List<Medicine> medicineList;
    private Medicine currentMedicine;
    private TagType currentTag;
    private Analogs currentAnalogs;
    private Versions currentVersions;
    private Version currentVersion;
    private Certificate currentCertificate;
    private Package currentPackage;
    private Dosage currentDosage;

    public SaxHandler(Medicines medicines) throws DatatypeConfigurationException {
        this.medicineList = medicines.getMedicine();
        objectFactory = new ObjectFactory();
        datatypeFactory = DatatypeFactory.newInstance();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        int dotsIndex = qName.indexOf(':');
        if (dotsIndex != -1) {
            qName = qName.substring(dotsIndex + 1);
        }
        currentTag = TagType.fromString(qName);
        switch (currentTag) {
            case MEDICINE:
                currentMedicine = new Medicine();
                currentMedicine.setId(attributes.getValue(TagType.ID.getValue()));
                currentMedicine.setNickname(attributes.getValue(TagType.NICKNAME.getValue()));
                medicineList.add(currentMedicine);
                break;
            case ANALOGS:
                currentAnalogs = new Analogs();
                currentMedicine.setAnalogs(currentAnalogs);
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
                currentVersion.setPackage(currentPackage);
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
    public void characters(char[] ch, int start, int length) throws SAXException {
        switch (currentTag) {
            case NAME:
                currentMedicine.setName(new String(ch, start, length));
                break;
            case PHARM:
                currentMedicine.setPharm(new String(ch, start, length));
                break;
            case GROUP:
                currentMedicine.setGroup(Group.fromValue(new String(ch, start, length)));
                break;
            case ANALOG_ID:
                currentAnalogs.getAnalogId().add(objectFactory.createAnalogsAnalogId(
                        new String(ch, start, length)
                ));
                break;
            case FORM:
                currentVersion.setForm(Form.fromValue(new String(ch, start, length)));
                break;
            case MANUFACTURER:
                currentVersion.setManufacturer(new String(ch, start, length));
                break;
            case NUMBER:
                currentCertificate.setNumber(Integer.parseInt(new String(ch, start, length)));
                break;
            case START_DATE:
                currentCertificate.setStartDate(datatypeFactory.newXMLGregorianCalendar(
                        new String(ch, start, length)
                ));
                break;
            case END_DATE:
                currentCertificate.setEndDate(datatypeFactory.newXMLGregorianCalendar(
                        new String(ch, start, length)
                ));
                break;
            case ORGANIZATION:
                currentCertificate.setOrganization(new String(ch, start, length));
                break;
            case TYPE:
                currentPackage.setType(new String(ch, start, length));
                break;
            case AMOUNT:
                currentPackage.setAmount(Integer.parseInt(new String(ch, start, length)));
                break;
            case PRICE:
                currentPackage.setPrice(BigDecimal.valueOf(Double.parseDouble(
                        new String(ch, start, length)
                )));
                break;
            case COUNT:
                currentDosage.setCount(Integer.parseInt(new String(ch, start, length)));
                break;
            case PERIODICITY:
                currentDosage.setPeriodicity(Periodicity.fromValue(new String(ch, start, length)));
                break;
            default:
                break;
        }
        currentTag = TagType.INVALID_TAG;
    }

}
