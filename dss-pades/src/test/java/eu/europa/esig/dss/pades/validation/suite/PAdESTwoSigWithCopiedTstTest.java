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
package eu.europa.esig.dss.pades.validation.suite;

import eu.europa.esig.dss.diagnostic.DiagnosticData;
import eu.europa.esig.dss.diagnostic.SignatureWrapper;
import eu.europa.esig.dss.diagnostic.TimestampWrapper;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.enumerations.TimestampType;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.InMemoryDocument;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PAdESTwoSigWithCopiedTstTest extends AbstractPAdESTestValidation {

    @Override
    protected DSSDocument getSignedDocument() {
        return new InMemoryDocument(getClass().getResourceAsStream("/validation/pades-two-sig-copied-tst.pdf"));
    }

    @Override
    protected void checkNumberOfSignatures(DiagnosticData diagnosticData) {
        super.checkNumberOfSignatures(diagnosticData);

        assertEquals(2, diagnosticData.getSignatures().size());
    }

    @Override
    protected void checkSignatureLevel(DiagnosticData diagnosticData) {
        for (SignatureWrapper signatureWrapper : diagnosticData.getSignatures()) {
            assertEquals(SignatureLevel.PAdES_BASELINE_T, signatureWrapper.getSignatureFormat());
        }
    }

    @Override
    protected void checkTimestamps(DiagnosticData diagnosticData) {
        List<TimestampWrapper> timestampList = diagnosticData.getTimestampList();
        assertEquals(2, timestampList.size());

        boolean sigTstValidFound = false;
        boolean sigTstInvalidFound = false;
        for (TimestampWrapper timestampWrapper : timestampList) {
            assertEquals(TimestampType.SIGNATURE_TIMESTAMP, timestampWrapper.getType());
            if (timestampWrapper.isMessageImprintDataIntact()) {
                assertTrue(timestampWrapper.isMessageImprintDataFound());
                assertTrue(timestampWrapper.isSignatureIntact());
                assertTrue(timestampWrapper.isSignatureValid());
                sigTstValidFound = true;
            } else {
                assertTrue(timestampWrapper.isMessageImprintDataFound());
                assertTrue(timestampWrapper.isSignatureIntact());
                assertFalse(timestampWrapper.isSignatureValid());
                sigTstInvalidFound = true;
            }
        }
        assertTrue(sigTstValidFound);
        assertTrue(sigTstInvalidFound);

        assertEquals(timestampList.get(0).getDigestAlgoAndValue().getDigestMethod(),
                timestampList.get(1).getDigestAlgoAndValue().getDigestMethod());
        assertArrayEquals(timestampList.get(0).getDigestAlgoAndValue().getDigestValue(),
                timestampList.get(1).getDigestAlgoAndValue().getDigestValue());
    }

}
