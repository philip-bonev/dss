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

import eu.europa.esig.dss.alert.exception.AlertException;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.InMemoryDocument;
import eu.europa.esig.dss.pades.PAdESSignatureParameters;
import eu.europa.esig.dss.pades.PAdESTimestampParameters;
import eu.europa.esig.dss.pades.signature.PAdESService;
import eu.europa.esig.dss.pades.validation.PDFDocumentAnalyzer;
import eu.europa.esig.dss.signature.DocumentSignatureService;
import eu.europa.esig.dss.test.pki.crl.UnknownPkiCRLSource;
import eu.europa.esig.dss.test.pki.ocsp.UnknownPkiOCSPSource;
import eu.europa.esig.dss.spi.validation.CertificateVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PAdESSignWithRevokedCertTest extends AbstractPAdESTestSignature {

    private DocumentSignatureService<PAdESSignatureParameters, PAdESTimestampParameters> service;
    private PAdESSignatureParameters signatureParameters;
    private DSSDocument documentToSign;

    private String signingAlias;

    @BeforeEach
    void init() throws Exception {
        documentToSign = new InMemoryDocument(PAdESSignWithRevokedCertTest.class.getResourceAsStream("/sample.pdf"));
        service = new PAdESService(getCompleteCertificateVerifier());
        service.setTspSource(getGoodTsa());
    }

    private void initSignatureParameters() {
        signatureParameters = new PAdESSignatureParameters();
        signatureParameters.setSigningCertificate(getSigningCert());
        signatureParameters.setCertificateChain(getCertificateChain());
        signatureParameters.setSignatureLevel(SignatureLevel.PAdES_BASELINE_B);
    }

    @Test
    void signBRevokedAndSignBGoodUserTest() {
        signingAlias = REVOKED_USER;
        initSignatureParameters();
        documentToSign = sign();

        signingAlias = GOOD_USER;
        initSignatureParameters();

        DSSDocument doubleSigned = sign();
        assertNotNull(doubleSigned);

        PDFDocumentAnalyzer pdfDocumentAnalyzer = new PDFDocumentAnalyzer(doubleSigned);
        assertEquals(2, pdfDocumentAnalyzer.getSignatures().size());
    }

    @Test
    void signBRevokedAndSignLTGoodUserTest() {
        signingAlias = REVOKED_USER;
        initSignatureParameters();
        documentToSign = sign();

        signingAlias = GOOD_USER;
        initSignatureParameters();
        signatureParameters.setSignatureLevel(SignatureLevel.PAdES_BASELINE_LT);

        Exception exception = assertThrows(AlertException.class, () -> sign());
        assertTrue(exception.getMessage().contains("Revoked/Suspended certificate(s) detected."));
    }

    @Test
    void signBGoodUserAndSignBRevokedTest() {
        signingAlias = GOOD_USER;
        initSignatureParameters();
        documentToSign = sign();

        signingAlias = REVOKED_USER;
        initSignatureParameters();

        DSSDocument doubleSigned = sign();
        assertNotNull(doubleSigned);

        PDFDocumentAnalyzer pdfDocumentAnalyzer = new PDFDocumentAnalyzer(doubleSigned);
        assertEquals(2, pdfDocumentAnalyzer.getSignatures().size());
    }

    @Test
    void signBGoodUserAndSignLTRevokedTest() {
        signingAlias = GOOD_USER;
        initSignatureParameters();
        documentToSign = sign();

        signingAlias = REVOKED_USER;
        initSignatureParameters();
        signatureParameters.setSignatureLevel(SignatureLevel.PAdES_BASELINE_LT);

        Exception exception = assertThrows(AlertException.class, () -> sign());
        assertTrue(exception.getMessage().contains("Revoked/Suspended certificate(s) detected."));
    }

    @Test
    void signBWithRevocationCheckEnabledTest() {
        signingAlias = GOOD_USER;
        initSignatureParameters();
        signatureParameters.setCheckCertificateRevocation(true);
        documentToSign = sign();

        signingAlias = REVOKED_USER;
        initSignatureParameters();
        signatureParameters.setCheckCertificateRevocation(true);

        Exception exception = assertThrows(AlertException.class, () -> sign());
        assertTrue(exception.getMessage().contains("Revoked/Suspended certificate(s) detected."));

        signingAlias = GOOD_USER_UNKNOWN;
        initSignatureParameters();
        signatureParameters.setCheckCertificateRevocation(true);
        CertificateVerifier certificateVerifier=super.getCompleteCertificateVerifier();
        certificateVerifier.setCrlSource(new UnknownPkiCRLSource(getCertEntityRepository()));
        certificateVerifier.setOcspSource(new UnknownPkiOCSPSource(getCertEntityRepository()));
        service = new PAdESService(certificateVerifier);
        service.setTspSource(getGoodTsa());
        exception = assertThrows(AlertException.class, () -> sign());
        assertTrue(exception.getMessage().contains("Revoked/Suspended certificate(s) detected."));
    }

    @Override
    public void signAndVerify() {
        // do nothing
    }

    @Override
    protected DocumentSignatureService<PAdESSignatureParameters, PAdESTimestampParameters> getService() {
        return service;
    }

    @Override
    protected PAdESSignatureParameters getSignatureParameters() {
        return signatureParameters;
    }

    @Override
    protected DSSDocument getDocumentToSign() {
        return documentToSign;
    }

    @Override
    protected String getSigningAlias() {
        return signingAlias;
    }

}
