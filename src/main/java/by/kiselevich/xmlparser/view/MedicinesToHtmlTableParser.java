package by.kiselevich.xmlparser.view;

import by.kiselevich.xmlparser.entity.medicins.*;
import by.kiselevich.xmlparser.entity.medicins.Package;

import javax.xml.bind.JAXBElement;

public class MedicinesToHtmlTableParser {

    private static final String LINE_DELIMITER_TAG = "<br>";
    private static final String TABLE_START_TAG = "<table BORDER=2 BORDERCOLOR=RED >";
    private static final String TABLE_END_TAG = "</table>";
    private static final String ROW_START_TAG = "<tr>";
    private static final String ROW_END_TAG = "</tr>";
    private static final String HEADER_START_TAG = "<th>";
    private static final String HEADER_END_TAG = "</th>";
    private static final String DATA_START_TAG = "<td>";
    private static final String DATA_END_TAG = "</td>";

    public String parse(Medicines medicines) {
        StringBuilder result = new StringBuilder();

        result.append(TABLE_START_TAG);
        addMedicinesTableHeaders(result);

        Certificate certificate;
        Package aPackage;
        Dosage dosage;
        for (Medicine medicine : medicines.getMedicine()) {
            result.append(ROW_START_TAG);

            result.append(DATA_START_TAG);
            result.append(medicine.getId());
            result.append(DATA_END_TAG);

            result.append(DATA_START_TAG);
            result.append(medicine.getNickname());
            result.append(DATA_END_TAG);

            result.append(DATA_START_TAG);
            result.append(medicine.getName());
            result.append(DATA_END_TAG);

            result.append(DATA_START_TAG);
            result.append(medicine.getPharm());
            result.append(DATA_END_TAG);

            result.append(DATA_START_TAG);
            result.append(medicine.getGroup());
            result.append(DATA_END_TAG);

            result.append(DATA_START_TAG);
            for (JAXBElement<String> analogId : medicine.getAnalogs().getAnalogId()) {
                result.append(analogId.getValue());
                result.append(LINE_DELIMITER_TAG);
            }
            result.append(DATA_END_TAG);

            result.append(DATA_START_TAG);
            result.append(TABLE_START_TAG);
            addAnalogsTableHeaders(result);
            for (Version version : medicine.getVersions().getVersion()) {
                result.append(ROW_START_TAG);

                result.append(DATA_START_TAG);
                result.append(version.getForm());
                result.append(DATA_END_TAG);

                result.append(DATA_START_TAG);
                result.append(version.getManufacturer());
                result.append(DATA_END_TAG);

                //certificate start
                result.append(DATA_START_TAG);
                result.append(TABLE_START_TAG);
                addCertificateTableHeaders(result);
                result.append(ROW_START_TAG);

                certificate = version.getCertificate();

                result.append(DATA_START_TAG);
                result.append(certificate.getNumber());
                result.append(DATA_END_TAG);

                result.append(DATA_START_TAG);
                result.append(certificate.getStartDate().toString());
                result.append(DATA_END_TAG);

                result.append(DATA_START_TAG);
                result.append(certificate.getEndDate().toString());
                result.append(DATA_END_TAG);

                result.append(DATA_START_TAG);
                result.append(certificate.getOrganization());
                result.append(DATA_END_TAG);

                result.append(ROW_END_TAG);
                result.append(TABLE_END_TAG);
                result.append(DATA_END_TAG);
                //certificate end

                //package start
                result.append(DATA_START_TAG);
                result.append(TABLE_START_TAG);
                addPackageTableHeaders(result);
                result.append(ROW_START_TAG);

                aPackage = version.getPackage();

                result.append(DATA_START_TAG);
                result.append(aPackage.getType());
                result.append(DATA_END_TAG);

                result.append(DATA_START_TAG);
                result.append(aPackage.getAmount());
                result.append(DATA_END_TAG);

                result.append(DATA_START_TAG);
                result.append(aPackage.getPrice());
                result.append(DATA_END_TAG);

                result.append(ROW_END_TAG);
                result.append(TABLE_END_TAG);
                result.append(DATA_END_TAG);
                //package end

                //dosage start
                result.append(DATA_START_TAG);
                result.append(TABLE_START_TAG);
                addDosageTableHeaders(result);
                result.append(ROW_START_TAG);

                dosage = version.getDosage();

                result.append(DATA_START_TAG);
                result.append(dosage.getCount());
                result.append(DATA_END_TAG);

                result.append(DATA_START_TAG);
                result.append(dosage.getPeriodicity());
                result.append(DATA_END_TAG);

                result.append(ROW_END_TAG);
                result.append(TABLE_END_TAG);
                result.append(DATA_END_TAG);
                //dosage end

                result.append(ROW_END_TAG);
            }
            result.append(TABLE_END_TAG);
            result.append(DATA_END_TAG);

            result.append(ROW_END_TAG);
        }
        result.append(TABLE_END_TAG);

        return result.toString();
    }

    private void addMedicinesTableHeaders(StringBuilder result) {

        result.append(ROW_START_TAG);

        result.append(HEADER_START_TAG);
        result.append("id");
        result.append(HEADER_END_TAG);

        result.append(HEADER_START_TAG);
        result.append("nickname");
        result.append(HEADER_END_TAG);

        result.append(HEADER_START_TAG);
        result.append("Name");
        result.append(HEADER_END_TAG);

        result.append(HEADER_START_TAG);
        result.append("Pharm");
        result.append(HEADER_END_TAG);

        result.append(HEADER_START_TAG);
        result.append("Group");
        result.append(HEADER_END_TAG);

        result.append(HEADER_START_TAG);
        result.append("Analogs");
        result.append(HEADER_END_TAG);

        result.append(HEADER_START_TAG);
        result.append("Versions");
        result.append(HEADER_END_TAG);

        result.append(ROW_END_TAG);
    }

    private void addAnalogsTableHeaders(StringBuilder result) {

        result.append(ROW_START_TAG);

        result.append(HEADER_START_TAG);
        result.append("Form");
        result.append(HEADER_END_TAG);

        result.append(HEADER_START_TAG);
        result.append("Manufacturer");
        result.append(HEADER_END_TAG);

        result.append(HEADER_START_TAG);
        result.append("Certificate");
        result.append(HEADER_END_TAG);

        result.append(HEADER_START_TAG);
        result.append("Package");
        result.append(HEADER_END_TAG);

        result.append(HEADER_START_TAG);
        result.append("Dosage");
        result.append(HEADER_END_TAG);

        result.append(ROW_END_TAG);
    }

    private void addCertificateTableHeaders(StringBuilder result) {
        result.append(ROW_START_TAG);

        result.append(HEADER_START_TAG);
        result.append("Number");
        result.append(HEADER_END_TAG);

        result.append(HEADER_START_TAG);
        result.append("StartDate");
        result.append(HEADER_END_TAG);

        result.append(HEADER_START_TAG);
        result.append("EndDate");
        result.append(HEADER_END_TAG);

        result.append(HEADER_START_TAG);
        result.append("Organization");
        result.append(HEADER_END_TAG);

        result.append(ROW_END_TAG);
    }


    private void addPackageTableHeaders(StringBuilder result) {
        result.append(ROW_START_TAG);

        result.append(HEADER_START_TAG);
        result.append("Type");
        result.append(HEADER_END_TAG);

        result.append(HEADER_START_TAG);
        result.append("Amount");
        result.append(HEADER_END_TAG);

        result.append(HEADER_START_TAG);
        result.append("Price");
        result.append(HEADER_END_TAG);

        result.append(ROW_END_TAG);
    }

    private void addDosageTableHeaders(StringBuilder result) {
        result.append(ROW_START_TAG);

        result.append(HEADER_START_TAG);
        result.append("Count");
        result.append(HEADER_END_TAG);

        result.append(HEADER_START_TAG);
        result.append("Periodicity");
        result.append(HEADER_END_TAG);

        result.append(ROW_END_TAG);
    }
}
