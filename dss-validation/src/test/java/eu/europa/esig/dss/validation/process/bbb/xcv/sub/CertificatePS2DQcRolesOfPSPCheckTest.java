/**
 * DSS - Digital Signature Services
 * Copyright (C) 2015 European Commission, provided under the CEF programme
 * <p>
 * This file is part of the "DSS - Digital Signature Services" project.
 * <p>
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * <p>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package eu.europa.esig.dss.validation.process.bbb.xcv.sub;

import eu.europa.esig.dss.detailedreport.jaxb.XmlConstraint;
import eu.europa.esig.dss.detailedreport.jaxb.XmlStatus;
import eu.europa.esig.dss.detailedreport.jaxb.XmlSubXCV;
import eu.europa.esig.dss.diagnostic.CertificateWrapper;
import eu.europa.esig.dss.diagnostic.jaxb.XmlCertificate;
import eu.europa.esig.dss.diagnostic.jaxb.XmlOID;
import eu.europa.esig.dss.diagnostic.jaxb.XmlPSD2QcInfo;
import eu.europa.esig.dss.diagnostic.jaxb.XmlQcStatements;
import eu.europa.esig.dss.diagnostic.jaxb.XmlRoleOfPSP;
import eu.europa.esig.dss.enumerations.CertificateExtensionEnum;
import eu.europa.esig.dss.enumerations.Level;
import eu.europa.esig.dss.policy.MultiValuesConstraintWrapper;
import eu.europa.esig.dss.policy.jaxb.MultiValuesConstraint;
import eu.europa.esig.dss.validation.process.bbb.AbstractTestCheck;
import eu.europa.esig.dss.validation.process.bbb.xcv.sub.checks.CertificatePS2DQcRolesOfPSPCheck;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CertificatePS2DQcRolesOfPSPCheckTest extends AbstractTestCheck {

    @Test
    void validTest() {
        XmlQcStatements xmlQcStatements = new XmlQcStatements();
        xmlQcStatements.setOID(CertificateExtensionEnum.QC_STATEMENTS.getOid());

        XmlRoleOfPSP xmlRoleOfPSP = new XmlRoleOfPSP();
        xmlRoleOfPSP.setName("Payment Initiation Service Provider");
        XmlOID xmlOID = new XmlOID();
        xmlOID.setDescription("psp-pi");
        xmlOID.setValue("0.4.0.19495.1.2");
        xmlRoleOfPSP.setOid(xmlOID);

        XmlPSD2QcInfo xmlPSD2QcInfo = new XmlPSD2QcInfo();
        xmlPSD2QcInfo.getRolesOfPSP().add(xmlRoleOfPSP);
        xmlQcStatements.setPSD2QcInfo(xmlPSD2QcInfo);

        MultiValuesConstraint constraint = new MultiValuesConstraint();
        constraint.setLevel(Level.FAIL);
        constraint.getId().add("psp-pi");

        XmlCertificate xc = new XmlCertificate();
        xc.getCertificateExtensions().add(xmlQcStatements);

        XmlSubXCV result = new XmlSubXCV();
        CertificatePS2DQcRolesOfPSPCheck cqcps2drc = new CertificatePS2DQcRolesOfPSPCheck(
                i18nProvider, result, new CertificateWrapper(xc), new MultiValuesConstraintWrapper(constraint));
        cqcps2drc.execute();

        List<XmlConstraint> constraints = result.getConstraint();
        assertEquals(1, constraints.size());
        assertEquals(XmlStatus.OK, constraints.get(0).getStatus());
    }
    @Test
    void multipleValuesTest() {
        XmlQcStatements xmlQcStatements = new XmlQcStatements();
        xmlQcStatements.setOID(CertificateExtensionEnum.QC_STATEMENTS.getOid());

        XmlRoleOfPSP xmlRoleOfPSP = new XmlRoleOfPSP();
        xmlRoleOfPSP.setName("Payment Initiation Service Provider");
        XmlOID xmlOID = new XmlOID();
        xmlOID.setDescription("psp-pi");
        xmlOID.setValue("0.4.0.19495.1.2");
        xmlRoleOfPSP.setOid(xmlOID);

        XmlPSD2QcInfo xmlPSD2QcInfo = new XmlPSD2QcInfo();
        xmlPSD2QcInfo.getRolesOfPSP().add(xmlRoleOfPSP);
        xmlQcStatements.setPSD2QcInfo(xmlPSD2QcInfo);

        MultiValuesConstraint constraint = new MultiValuesConstraint();
        constraint.setLevel(Level.FAIL);
        constraint.getId().add("psp-as");
        constraint.getId().add("psp-pi");

        XmlCertificate xc = new XmlCertificate();
        xc.getCertificateExtensions().add(xmlQcStatements);

        XmlSubXCV result = new XmlSubXCV();
        CertificatePS2DQcRolesOfPSPCheck cqcps2drc = new CertificatePS2DQcRolesOfPSPCheck(
                i18nProvider, result, new CertificateWrapper(xc), new MultiValuesConstraintWrapper(constraint));
        cqcps2drc.execute();

        List<XmlConstraint> constraints = result.getConstraint();
        assertEquals(1, constraints.size());
        assertEquals(XmlStatus.OK, constraints.get(0).getStatus());
    }

    @Test
    void oidTest() {
        XmlQcStatements xmlQcStatements = new XmlQcStatements();
        xmlQcStatements.setOID(CertificateExtensionEnum.QC_STATEMENTS.getOid());

        XmlRoleOfPSP xmlRoleOfPSP = new XmlRoleOfPSP();
        xmlRoleOfPSP.setName("Payment Initiation Service Provider");
        XmlOID xmlOID = new XmlOID();
        xmlOID.setDescription("psp-pi");
        xmlOID.setValue("0.4.0.19495.1.2");
        xmlRoleOfPSP.setOid(xmlOID);

        XmlPSD2QcInfo xmlPSD2QcInfo = new XmlPSD2QcInfo();
        xmlPSD2QcInfo.getRolesOfPSP().add(xmlRoleOfPSP);
        xmlQcStatements.setPSD2QcInfo(xmlPSD2QcInfo);

        MultiValuesConstraint constraint = new MultiValuesConstraint();
        constraint.setLevel(Level.FAIL);
        constraint.getId().add("0.4.0.19495.1.2");

        XmlCertificate xc = new XmlCertificate();
        xc.getCertificateExtensions().add(xmlQcStatements);

        XmlSubXCV result = new XmlSubXCV();
        CertificatePS2DQcRolesOfPSPCheck cqcps2drc = new CertificatePS2DQcRolesOfPSPCheck(
                i18nProvider, result, new CertificateWrapper(xc), new MultiValuesConstraintWrapper(constraint));
        cqcps2drc.execute();

        List<XmlConstraint> constraints = result.getConstraint();
        assertEquals(1, constraints.size());
        assertEquals(XmlStatus.OK, constraints.get(0).getStatus());
    }

    @Test
    void invalidTest() {
        XmlQcStatements xmlQcStatements = new XmlQcStatements();
        xmlQcStatements.setOID(CertificateExtensionEnum.QC_STATEMENTS.getOid());

        XmlRoleOfPSP xmlRoleOfPSP = new XmlRoleOfPSP();
        xmlRoleOfPSP.setName("Payment Initiation Service Provider");
        XmlOID xmlOID = new XmlOID();
        xmlOID.setDescription("psp-as");
        xmlOID.setValue("0.4.0.19495.1.1");
        xmlRoleOfPSP.setOid(xmlOID);

        XmlPSD2QcInfo xmlPSD2QcInfo = new XmlPSD2QcInfo();
        xmlPSD2QcInfo.getRolesOfPSP().add(xmlRoleOfPSP);
        xmlQcStatements.setPSD2QcInfo(xmlPSD2QcInfo);

        MultiValuesConstraint constraint = new MultiValuesConstraint();
        constraint.setLevel(Level.FAIL);
        constraint.getId().add("psp-pi");

        XmlCertificate xc = new XmlCertificate();
        xc.getCertificateExtensions().add(xmlQcStatements);

        XmlSubXCV result = new XmlSubXCV();
        CertificatePS2DQcRolesOfPSPCheck cqcps2drc = new CertificatePS2DQcRolesOfPSPCheck(
                i18nProvider, result, new CertificateWrapper(xc), new MultiValuesConstraintWrapper(constraint));
        cqcps2drc.execute();

        List<XmlConstraint> constraints = result.getConstraint();
        assertEquals(1, constraints.size());
        assertEquals(XmlStatus.NOT_OK, constraints.get(0).getStatus());
    }

    @Test
    void qcPS2DNotPresentTest() {
        XmlQcStatements xmlQcStatements = new XmlQcStatements();
        xmlQcStatements.setOID(CertificateExtensionEnum.QC_STATEMENTS.getOid());

        MultiValuesConstraint constraint = new MultiValuesConstraint();
        constraint.setLevel(Level.FAIL);
        constraint.getId().add("psp-pi");

        XmlCertificate xc = new XmlCertificate();
        xc.getCertificateExtensions().add(xmlQcStatements);

        XmlSubXCV result = new XmlSubXCV();
        CertificatePS2DQcRolesOfPSPCheck cqcps2drc = new CertificatePS2DQcRolesOfPSPCheck(
                i18nProvider, result, new CertificateWrapper(xc), new MultiValuesConstraintWrapper(constraint));
        cqcps2drc.execute();

        List<XmlConstraint> constraints = result.getConstraint();
        assertEquals(1, constraints.size());
        assertEquals(XmlStatus.NOT_OK, constraints.get(0).getStatus());
    }

    @Test
    void qcStatementsNotPresentTest() {
        MultiValuesConstraint constraint = new MultiValuesConstraint();
        constraint.setLevel(Level.FAIL);
        constraint.getId().add("psp-pi");

        XmlCertificate xc = new XmlCertificate();

        XmlSubXCV result = new XmlSubXCV();
        CertificatePS2DQcRolesOfPSPCheck cqcps2drc = new CertificatePS2DQcRolesOfPSPCheck(
                i18nProvider, result, new CertificateWrapper(xc), new MultiValuesConstraintWrapper(constraint));
        cqcps2drc.execute();

        List<XmlConstraint> constraints = result.getConstraint();
        assertEquals(1, constraints.size());
        assertEquals(XmlStatus.NOT_OK, constraints.get(0).getStatus());
    }

}
