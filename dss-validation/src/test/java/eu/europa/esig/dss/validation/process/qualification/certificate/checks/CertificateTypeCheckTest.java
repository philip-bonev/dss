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
package eu.europa.esig.dss.validation.process.qualification.certificate.checks;

import eu.europa.esig.dss.detailedreport.jaxb.XmlConstraint;
import eu.europa.esig.dss.detailedreport.jaxb.XmlStatus;
import eu.europa.esig.dss.detailedreport.jaxb.XmlValidationCertificateQualification;
import eu.europa.esig.dss.enumerations.CertificateType;
import eu.europa.esig.dss.enumerations.Level;
import eu.europa.esig.dss.enumerations.ValidationTime;
import eu.europa.esig.dss.policy.LevelConstraintWrapper;
import eu.europa.esig.dss.policy.jaxb.LevelConstraint;
import eu.europa.esig.dss.validation.process.bbb.AbstractTestCheck;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CertificateTypeCheckTest extends AbstractTestCheck {

    @Test
    void eSignTest() throws Exception {
        LevelConstraint constraint = new LevelConstraint();
        constraint.setLevel(Level.FAIL);

        XmlValidationCertificateQualification result = new XmlValidationCertificateQualification();
        CertificateTypeCheck ctc = new CertificateTypeCheck(i18nProvider, result, CertificateType.ESIGN,
                ValidationTime.CERTIFICATE_ISSUANCE_TIME, new LevelConstraintWrapper(constraint));
        ctc.execute();

        List<XmlConstraint> constraints = result.getConstraint();
        assertEquals(1, constraints.size());
        assertEquals(XmlStatus.OK, constraints.get(0).getStatus());
    }

    @Test
    void eSealTest() throws Exception {
        LevelConstraint constraint = new LevelConstraint();
        constraint.setLevel(Level.FAIL);

        XmlValidationCertificateQualification result = new XmlValidationCertificateQualification();
        CertificateTypeCheck ctc = new CertificateTypeCheck(i18nProvider, result, CertificateType.ESEAL,
                ValidationTime.VALIDATION_TIME, new LevelConstraintWrapper(constraint));
        ctc.execute();

        List<XmlConstraint> constraints = result.getConstraint();
        assertEquals(1, constraints.size());
        assertEquals(XmlStatus.OK, constraints.get(0).getStatus());
    }

    @Test
    void wsaTest() throws Exception {
        LevelConstraint constraint = new LevelConstraint();
        constraint.setLevel(Level.FAIL);

        XmlValidationCertificateQualification result = new XmlValidationCertificateQualification();
        CertificateTypeCheck ctc = new CertificateTypeCheck(i18nProvider, result, CertificateType.WSA,
                ValidationTime.BEST_SIGNATURE_TIME, new LevelConstraintWrapper(constraint));
        ctc.execute();

        List<XmlConstraint> constraints = result.getConstraint();
        assertEquals(1, constraints.size());
        assertEquals(XmlStatus.OK, constraints.get(0).getStatus());
    }

    @Test
    void unknownTypeTest() throws Exception {
        LevelConstraint constraint = new LevelConstraint();
        constraint.setLevel(Level.FAIL);

        XmlValidationCertificateQualification result = new XmlValidationCertificateQualification();
        CertificateTypeCheck ctc = new CertificateTypeCheck(i18nProvider, result, CertificateType.UNKNOWN,
                ValidationTime.BEST_SIGNATURE_TIME, new LevelConstraintWrapper(constraint));
        ctc.execute();

        List<XmlConstraint> constraints = result.getConstraint();
        assertEquals(1, constraints.size());
        assertEquals(XmlStatus.NOT_OK, constraints.get(0).getStatus());
    }

}
