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
package eu.europa.esig.dss.pades.signature.suite;

import eu.europa.esig.dss.enumerations.DigestAlgorithm;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.DigestDocument;
import eu.europa.esig.dss.model.InMemoryDocument;
import eu.europa.esig.dss.model.SignatureValue;
import eu.europa.esig.dss.pades.PAdESSignatureParameters;
import eu.europa.esig.dss.pades.signature.PAdESService;
import eu.europa.esig.dss.test.PKIFactoryAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PAdESLevelBDigestDocumentTest extends PKIFactoryAccess {

    private PAdESService service;
    private PAdESSignatureParameters signatureParameters;
    private DSSDocument documentToSign;

    private static final DSSDocument ORIGINAL_DOCUMENT = new InMemoryDocument("Hello World !".getBytes(), "test.text");

    @BeforeEach
    void init() throws Exception {
        documentToSign = new DigestDocument(DigestAlgorithm.SHA256, ORIGINAL_DOCUMENT.getDigestValue(DigestAlgorithm.SHA256));

        signatureParameters = new PAdESSignatureParameters();
        signatureParameters.bLevel().setSigningDate(new Date());
        signatureParameters.setSigningCertificate(getSigningCert());
        signatureParameters.setCertificateChain(getCertificateChain());
        signatureParameters.setSignatureLevel(SignatureLevel.PAdES_BASELINE_B);

        service = new PAdESService(getCompleteCertificateVerifier());
    }

    @Test
    void test() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> service.getDataToSign(documentToSign, signatureParameters));
        assertEquals("DigestDocument cannot be used! PDF document is expected!", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> service.signDocument(documentToSign, signatureParameters, new SignatureValue()));
        assertEquals("DigestDocument cannot be used! PDF document is expected!", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> service.getContentTimestamp(documentToSign, signatureParameters));
        assertEquals("DigestDocument cannot be used! PDF document is expected!", exception.getMessage());
    }

    @Override
    protected String getSigningAlias() {
        return GOOD_USER;
    }

}
