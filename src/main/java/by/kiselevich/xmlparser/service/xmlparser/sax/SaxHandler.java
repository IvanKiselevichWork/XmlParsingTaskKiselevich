package by.kiselevich.xmlparser.service.xmlparser.sax;

import by.kiselevich.xmlparser.entity.medicins.*;
import by.kiselevich.xmlparser.entity.medicins.Package;
import by.kiselevich.xmlparser.service.xmlparser.TagType;
import org.xml.sax.Attributes;
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
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
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
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length);
        switch (currentTag) {
            case NAME:
                currentMedicine.setName(value);
                break;
            case PHARM:
                currentMedicine.setPharm(value);
                break;
            case GROUP:
                currentMedicine.setGroup(Group.fromValue(value));
                break;
            case ANALOG:
                currentMedicine.getAnalogs().getAnalogId().add(objectFactory.createAnalogsAnalogId(
                        value
                ));
                break;
            case FORM:
                currentVersion.setForm(Form.fromValue(value));
                break;
            case MANUFACTURER:
                currentVersion.setManufacturer(value);
                break;
            case NUMBER:
                currentCertificate.setNumber(Integer.parseInt(value));
                break;
            case START_DATE:
                currentCertificate.setStartDate(datatypeFactory.newXMLGregorianCalendar(
                        value
                ));
                break;
            case END_DATE:
                currentCertificate.setEndDate(datatypeFactory.newXMLGregorianCalendar(
                        value
                ));
                break;
            case ORGANIZATION:
                currentCertificate.setOrganization(value);
                break;
            case TYPE:
                currentPackage.setType(value);
                break;
            case AMOUNT:
                currentPackage.setAmount(Integer.parseInt(value));
                break;
            case PRICE:
                currentPackage.setPrice(BigDecimal.valueOf(Double.parseDouble(
                        value
                )));
                break;
            case COUNT:
                currentDosage.setCount(Integer.parseInt(value));
                break;
            case PERIODICITY:
                currentDosage.setPeriodicity(Periodicity.fromValue(value));
                break;
            default:
                break;
        }
        currentTag = TagType.INVALID_TAG;
    }

}
