package by.kiselevich.xmlparser.service.xmlparser.dom;

import by.kiselevich.xmlparser.entity.medicins.*;
import by.kiselevich.xmlparser.entity.medicins.Package;
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

    private DocumentBuilder documentBuilder;

    public XmlDomParser() {
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOG.warn(e);
        }
    }

    @Override
    public Medicines parse(String xmlFilePath) {

        Medicines medicines = new Medicines();
        List<Medicine> medicineList = medicines.getMedicine();
        Document document = null;

        try {
            document = documentBuilder.parse(xmlFilePath);
            Element root = document.getDocumentElement();

            NodeList medicineNodes = root.getChildNodes();
            Medicine medicine;
            Node medicineNode;
            NamedNodeMap attributes;
            NodeList medicineParamsNode;
            for (int i = 0; i < medicineNodes.getLength(); i++) {
                medicineNode = medicineNodes.item(i);
                if (!medicineNode.getNodeName().equals("Medicine")) {
                    continue;
                }
                medicine = new Medicine();
                attributes = medicineNode.getAttributes();
                for (int j = 0; j < attributes.getLength(); j++) {
                    if (attributes.item(j).getNodeName().equals("id")) {
                        medicine.setId(attributes.item(j).getNodeValue());
                    } else {
                        medicine.setNickname(attributes.item(j).getNodeValue());
                    }
                }

                medicineParamsNode = medicineNode.getChildNodes();

                for (int j = 0; j < medicineParamsNode.getLength(); j++) {
                    switch (medicineParamsNode.item(j).getNodeName()) {
                        case "Name":
                            medicine.setName((medicineParamsNode.item(j).getNodeValue()));
                            break;
                        case "Pharm":
                            medicine.setPharm((medicineParamsNode.item(j).getNodeValue()));
                            break;
                        case "Group":
                            medicine.setGroup(getMedicineGroup(medicineParamsNode.item(j)));
                            break;
                        case "Analogs":
                            medicine.setAnalogs(getMedicineAnalogs(medicineParamsNode.item(j)));
                            break;
                        case "Versions":
                            medicine.setVersions(getMedicineVersions(medicineParamsNode.item(j)));
                            break;
                    }
                }

                medicineList.add(medicine);
            }

        } catch (IOException | SAXException | DatatypeConfigurationException e) {
            LOG.warn(e);
        }

        return medicines;
    }

    @Override
    public XmlParserType getType() {
        return XmlParserType.DOM;
    }

    private Group getMedicineGroup(Node groupNode) {
        return Group.fromValue(groupNode.getNodeValue());
    }

    private Analogs getMedicineAnalogs(Node analogsNode) {
        Analogs analogs = new Analogs();
        List<JAXBElement<String>> analogList = analogs.getAnalogId();
        NodeList analogIdList = analogsNode.getChildNodes();
        JAXBElement<String> analogId;
        ObjectFactory objectFactory = new ObjectFactory();
        for (int i = 0; i < analogIdList.getLength(); i++) {
            analogId = objectFactory.createAnalogsAnalogId(analogIdList.item(i).getNodeValue());
            analogList.add(analogId);
        }
        return analogs;
    }

    private Versions getMedicineVersions(Node versionsNode) throws DatatypeConfigurationException {
        Versions versions = new Versions();
        List<Version> versionList = versions.getVersion();
        NodeList versionsNodeList = versionsNode.getChildNodes();
        NodeList versionNodeList;
        Version version;
        for (int i = 0; i < versionsNodeList.getLength(); i++) {
            versionNodeList = versionsNodeList.item(i).getChildNodes();
            version = new Version();
            for (int j = 0; j < versionNodeList.getLength(); j++) {
                switch (versionNodeList.item(j).getNodeName()) {
                    case "Form":
                        version.setForm(getVersionForm(versionNodeList.item(j)));
                        break;
                    case "Manufacturer":
                        version.setManufacturer((versionNodeList.item(j).getNodeValue()));
                        break;
                    case "Certificate":
                        version.setCertificate(getVersionCertificate(versionNodeList.item(j)));
                        break;
                    case "Package":
                        version.setPackage(getVersionPackage(versionNodeList.item(j)));
                        break;
                    case "Dosage":
                        version.setDosage(getVersionDosage(versionNodeList.item(j)));
                        break;
                }
            }
            versionList.add(version);
        }
        return versions;
    }

    private Form getVersionForm(Node formNode) {
        return Form.fromValue(formNode.getNodeValue());
    }

    private Certificate getVersionCertificate(Node certificateNode) throws DatatypeConfigurationException {
        Certificate certificate = new Certificate();
        NodeList certificateNodeList = certificateNode.getChildNodes();
        Node certificateParameter;
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        for (int i = 0; i < certificateNodeList.getLength(); i++) {
            certificateParameter = certificateNodeList.item(i);
            switch (certificateParameter.getNodeName()) {
                case "Number":
                    certificate.setNumber(Integer.parseInt(certificateParameter.getNodeValue()));
                    break;
                case "StartDate":
                    certificate.setStartDate(datatypeFactory.newXMLGregorianCalendar(certificateParameter.getNodeValue()));
                    break;
                case "EndDate":
                    certificate.setEndDate(datatypeFactory.newXMLGregorianCalendar(certificateParameter.getNodeValue()));
                    break;
                case "Organization":
                    certificate.setOrganization(certificateParameter.getNodeValue());
                    break;
            }
        }
        return certificate;
    }

    private Package getVersionPackage(Node packageNode) {
        Package aPackage = new Package();
        NodeList packageNodeList = packageNode.getChildNodes();
        Node packageParameter;
        for (int i = 0; i < packageNodeList.getLength(); i++) {
            packageParameter = packageNodeList.item(i);
            switch (packageParameter.getNodeName()) {
                case "Type":
                    aPackage.setType(packageParameter.getNodeValue());
                    break;
                case "Amount":
                    aPackage.setAmount(Integer.parseInt(packageParameter.getNodeValue()));
                    break;
                case "Price":
                    aPackage.setPrice(BigDecimal.valueOf(Double.parseDouble(packageParameter.getNodeValue())));
                    break;
            }
        }
        return aPackage;
    }

    private Dosage getVersionDosage(Node dosageNode) {
        Dosage dosage = new Dosage();
        NodeList dosageNodeList = dosageNode.getChildNodes();
        Node dosageParameter;
        for (int i = 0; i < dosageNodeList.getLength(); i++) {
            dosageParameter = dosageNodeList.item(i);
            switch (dosageParameter.getNodeName()) {
                case "Count":
                    dosage.setCount(Integer.parseInt(dosageParameter.getNodeValue()));
                    break;
                case "Periodicity":
                    dosage.setPeriodicity(getDosagePeriodicity(dosageParameter));
                    break;
            }
        }
        return dosage;
    }

    private Periodicity getDosagePeriodicity(Node periodicityNode) {
        return Periodicity.fromValue(periodicityNode.getNodeValue());
    }
}
