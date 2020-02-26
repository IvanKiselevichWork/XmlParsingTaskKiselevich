package by.kiselevich.xmlparser.service.xmlparser.dom;

import by.kiselevich.xmlparser.entity.medicins.*;
import by.kiselevich.xmlparser.entity.medicins.Package;
import by.kiselevich.xmlparser.service.xmlparser.TagType;
import by.kiselevich.xmlparser.service.xmlparser.XmlParser;
import by.kiselevich.xmlparser.service.xmlparser.XmlParserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class XmlDomParser implements XmlParser {

    private static final Logger LOG = LogManager.getLogger(XmlDomParser.class);

    // to prevent XXE attacks
    // see https://help.semmle.com/wiki/display/JAVA/Resolving+XML+external+entity+in+user-controlled+data
    private static final String SECURITY_FEATURE_URL = "http://apache.org/xml/features/disallow-doctype-decl";

    private DocumentBuilder documentBuilder;
    private DatatypeFactory datatypeFactory;

    public XmlDomParser() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setFeature(SECURITY_FEATURE_URL, true);
            documentBuilder = documentBuilderFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            LOG.warn(e);
        }
    }

    @Override
    public Medicines parse(String xmlFilePath) {

        Medicines medicines = new Medicines();

        try {
            datatypeFactory = DatatypeFactory.newInstance();
            List<Medicine> medicineList = medicines.getMedicine();
            Document document = documentBuilder.parse(xmlFilePath);
            Element root = document.getDocumentElement();

            NodeList medicineNodes = root.getChildNodes();
            Medicine medicine;
            Node medicineNode;
            NamedNodeMap attributes;
            NodeList medicineParametersNode;
            Node medicineParameter;
            for (int i = 0; i < medicineNodes.getLength(); i++) {
                medicineNode = medicineNodes.item(i);
                if (!medicineNode.getNodeName().equals(TagType.MEDICINE.getValue())) {
                    continue;
                }
                medicine = new Medicine();
                attributes = medicineNode.getAttributes();
                for (int j = 0; j < attributes.getLength(); j++) {
                    if (attributes.item(j).getNodeName().equals(TagType.ID.getValue())) {
                        medicine.setId(attributes.item(j).getNodeValue());
                    } else if (attributes.item(j).getNodeName().equals(TagType.NICKNAME.getValue())) {
                        medicine.setNickname(attributes.item(j).getNodeValue());
                    }
                }

                medicineParametersNode = medicineNode.getChildNodes();

                for (int j = 0; j < medicineParametersNode.getLength(); j++) {
                    medicineParameter = medicineParametersNode.item(j);
                    setMedicineParameter(medicine, medicineParameter);
                }

                medicineList.add(medicine);
            }

        } catch (IOException | SAXException | DatatypeConfigurationException e) {
            LOG.warn(e);
        }

        return medicines;
    }

    private void setMedicineParameter(Medicine medicine, Node medicineParameter) {
        TagType tagType = getTagType(medicineParameter);
        switch (tagType) {
            case NAME:
                medicine.setName((medicineParameter.getTextContent()));
                break;
            case PHARM:
                medicine.setPharm((medicineParameter.getTextContent()));
                break;
            case GROUP:
                medicine.setGroup(getMedicineGroup(medicineParameter));
                break;
            case ANALOGS:
                medicine.setAnalogs(getMedicineAnalogs(medicineParameter));
                break;
            case VERSIONS:
                medicine.setVersions(getMedicineVersions(medicineParameter));
                break;
            default:
                break;
        }
    }

    @Override
    public XmlParserType getType() {
        return XmlParserType.DOM;
    }

    private Group getMedicineGroup(Node groupNode) {
        return Group.fromValue(groupNode.getTextContent());
    }

    private Analogs getMedicineAnalogs(Node analogsNode) {
        Analogs analogs = new Analogs();
        List<JAXBElement<String>> analogList = analogs.getAnalogId();
        NodeList analogIdList = analogsNode.getChildNodes();
        JAXBElement<String> analogId;
        ObjectFactory objectFactory = new ObjectFactory();
        Node analogIdNode;
        for (int i = 0; i < analogIdList.getLength(); i++) {
            analogIdNode = analogIdList.item(i);
            if (!analogIdNode.getNodeName().equals(TagType.ANALOG_ID.getValue())) {
                continue;
            }
            analogId = objectFactory.createAnalogsAnalogId(analogIdNode.getTextContent());
            analogList.add(analogId);
        }
        return analogs;
    }

    private Versions getMedicineVersions(Node versionsNode) {
        Versions versions = new Versions();
        List<Version> versionList = versions.getVersion();
        NodeList versionsNodeList = versionsNode.getChildNodes();
        NodeList versionNodeList;
        Version version;
        for (int i = 0; i < versionsNodeList.getLength(); i++) {
            versionNodeList = versionsNodeList.item(i).getChildNodes();
            if (versionNodeList.getLength() == 0) {
                continue;
            }
            version = new Version();
            Node versionParameter;
            for (int j = 0; j < versionNodeList.getLength(); j++) {
                versionParameter = versionNodeList.item(j);

                setVersionParameter(version, versionParameter);
            }
            versionList.add(version);
        }
        return versions;
    }

    private void setVersionParameter(Version version, Node versionParameter) {
        TagType tagType = getTagType(versionParameter);

        switch (tagType) {
            case FORM:
                version.setForm(getVersionForm(versionParameter));
                break;
            case MANUFACTURER:
                version.setManufacturer((versionParameter.getTextContent()));
                break;
            case CERTIFICATE:
                version.setCertificate(getVersionCertificate(versionParameter));
                break;
            case PACKAGE:
                version.setPackage(getVersionPackage(versionParameter));
                break;
            case DOSAGE:
                version.setDosage(getVersionDosage(versionParameter));
                break;
            default:
                break;
        }
    }

    private TagType getTagType(Node parameter) {
        TagType tagType;
        try {
            tagType = TagType.valueOf(parameter.getNodeName());
        } catch (IllegalArgumentException ignored) {
            tagType = TagType.INVALID_TAG;
        }
        return tagType;
    }

    private Form getVersionForm(Node formNode) {
        return Form.fromValue(formNode.getTextContent());
    }

    private Certificate getVersionCertificate(Node certificateNode) {
        Certificate certificate = new Certificate();
        NodeList certificateNodeList = certificateNode.getChildNodes();
        Node certificateParameter;

        for (int i = 0; i < certificateNodeList.getLength(); i++) {
            certificateParameter = certificateNodeList.item(i);
            setCertificateParameter(certificate, certificateParameter);
        }
        return certificate;
    }

    private void setCertificateParameter(Certificate certificate, Node certificateParameter) {
        TagType tagType = getTagType(certificateParameter);

        switch (tagType) {
            case NUMBER:
                certificate.setNumber(Integer.parseInt(certificateParameter.getTextContent()));
                break;
            case START_DATE:
                certificate.setStartDate(datatypeFactory.newXMLGregorianCalendar(certificateParameter.getTextContent()));
                break;
            case END_DATE:
                certificate.setEndDate(datatypeFactory.newXMLGregorianCalendar(certificateParameter.getTextContent()));
                break;
            case ORGANIZATION:
                certificate.setOrganization(certificateParameter.getTextContent());
                break;
            default:
                break;
        }
    }

    private Package getVersionPackage(Node packageNode) {
        Package aPackage = new Package();
        NodeList packageNodeList = packageNode.getChildNodes();
        Node packageParameter;
        for (int i = 0; i < packageNodeList.getLength(); i++) {
            packageParameter = packageNodeList.item(i);
            setPackageParameter(aPackage, packageParameter);
        }
        return aPackage;
    }

    private void setPackageParameter(Package aPackage, Node packageParameter) {
        TagType tagType = getTagType(packageParameter);

        switch (tagType) {
            case TYPE:
                aPackage.setType(packageParameter.getTextContent());
                break;
            case AMOUNT:
                aPackage.setAmount(Integer.parseInt(packageParameter.getTextContent()));
                break;
            case PRICE:
                aPackage.setPrice(BigDecimal.valueOf(Double.parseDouble(packageParameter.getTextContent())));
                break;
            default:
                break;
        }
    }

    private Dosage getVersionDosage(Node dosageNode) {
        Dosage dosage = new Dosage();
        NodeList dosageNodeList = dosageNode.getChildNodes();
        Node dosageParameter;
        for (int i = 0; i < dosageNodeList.getLength(); i++) {
            dosageParameter = dosageNodeList.item(i);
            setDosageParameter(dosage, dosageParameter);
        }
        return dosage;
    }

    private void setDosageParameter(Dosage dosage, Node dosageParameter) {
        TagType tagType = getTagType(dosageParameter);

        switch (tagType) {
            case COUNT:
                dosage.setCount(Integer.parseInt(dosageParameter.getTextContent()));
                break;
            case PERIODICITY:
                dosage.setPeriodicity(getDosagePeriodicity(dosageParameter));
                break;
            default:
                break;
        }
    }

    private Periodicity getDosagePeriodicity(Node periodicityNode) {
        return Periodicity.fromValue(periodicityNode.getTextContent());
    }
}
