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
package eu.europa.esig.dss.asic.cades.validation.evidencerecord;

import eu.europa.esig.dss.asic.common.validation.AbstractASiCWithEvidenceRecordTestValidation;
import eu.europa.esig.dss.diagnostic.DiagnosticData;
import eu.europa.esig.dss.diagnostic.EvidenceRecordWrapper;
import eu.europa.esig.dss.diagnostic.TimestampWrapper;
import eu.europa.esig.dss.enumerations.DigestMatcherType;
import eu.europa.esig.dss.enumerations.Indication;
import eu.europa.esig.dss.enumerations.SubIndication;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.FileDocument;
import eu.europa.esig.dss.model.ReferenceValidation;
import eu.europa.esig.dss.simplereport.SimpleReport;
import eu.europa.esig.dss.simplereport.jaxb.XmlEvidenceRecord;
import eu.europa.esig.dss.spi.x509.evidencerecord.EvidenceRecord;
import eu.europa.esig.dss.spi.x509.tsp.TimestampToken;
import eu.europa.esig.dss.spi.x509.tsp.TimestampedReference;
import eu.europa.esig.dss.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ASiCEWithCAdESEvidenceRecordNoHashtreeManifestCanonicalizedDigestValidationTest extends AbstractASiCWithEvidenceRecordTestValidation {

    @Override
    protected DSSDocument getSignedDocument() {
        return new FileDocument("src/test/resources/validation/evidencerecord/er-no-hashtree-xml-manifest-canonicalized.sce");
    }

    @Override
    protected boolean allArchiveDataObjectsProvidedToValidation() {
        return false;
    }

    @Override
    protected void checkDetachedEvidenceRecords(List<EvidenceRecord> detachedEvidenceRecords) {
        assertEquals(1, Utils.collectionSize(detachedEvidenceRecords));

        EvidenceRecord evidenceRecord = detachedEvidenceRecords.get(0);

        int archiveObjectCounter = 0;
        int orphanReferencesCounter = 0;
        List<ReferenceValidation> referenceValidationList = evidenceRecord.getReferenceValidation();
        for (ReferenceValidation referenceValidation : referenceValidationList) {
            if (DigestMatcherType.EVIDENCE_RECORD_ARCHIVE_OBJECT.equals(referenceValidation.getType())) {
                assertTrue(referenceValidation.isFound());
                assertFalse(referenceValidation.isIntact());
                ++archiveObjectCounter;
            } else if (DigestMatcherType.EVIDENCE_RECORD_ORPHAN_REFERENCE.equals(referenceValidation.getType())) {
                assertFalse(referenceValidation.isFound());
                assertFalse(referenceValidation.isIntact());
                ++orphanReferencesCounter;
            }
        }
        assertEquals(1, archiveObjectCounter);
        assertEquals(0, orphanReferencesCounter);

        List<TimestampedReference> timestampedReferences = evidenceRecord.getTimestampedReferences();
        assertFalse(Utils.isCollectionNotEmpty(timestampedReferences));

        int tstCounter = 0;
        List<TimestampToken> timestamps = evidenceRecord.getTimestamps();
        for (TimestampToken timestampToken : timestamps) {
            assertTrue(timestampToken.isProcessed());
            assertTrue(timestampToken.isMessageImprintDataFound());
            assertTrue(timestampToken.isMessageImprintDataIntact());
            ++tstCounter;
        }
        assertEquals(1, tstCounter);
    }

    @Override
    protected void checkEvidenceRecordTimestamps(DiagnosticData diagnosticData) {
        EvidenceRecordWrapper evidenceRecord = diagnosticData.getEvidenceRecords().get(0);
        assertEquals(1, evidenceRecord.getTimestampList().size());

        TimestampWrapper timestamp = evidenceRecord.getTimestampList().get(0);
        assertTrue(timestamp.isMessageImprintDataFound());
        assertTrue(timestamp.isMessageImprintDataIntact());
        assertTrue(timestamp.isSignatureIntact());
        assertTrue(timestamp.isSignatureValid());
        assertTrue(Utils.isCollectionEmpty(timestamp.getTimestampScopes()));
    }

    @Override
    protected void checkEvidenceRecordScopes(DiagnosticData diagnosticData) {
        EvidenceRecordWrapper evidenceRecord = diagnosticData.getEvidenceRecords().get(0);
        assertFalse(Utils.isCollectionNotEmpty(evidenceRecord.getEvidenceRecordScopes()));
    }

    @Override
    protected void checkEvidenceRecordTimestampedReferences(DiagnosticData diagnosticData) {
        EvidenceRecordWrapper evidenceRecord = diagnosticData.getEvidenceRecords().get(0);
        assertFalse(Utils.isCollectionNotEmpty(evidenceRecord.getCoveredObjects()));
    }

    @Override
    protected void verifySimpleReport(SimpleReport simpleReport) {
        XmlEvidenceRecord evidenceRecord = simpleReport.getEvidenceRecordById(simpleReport.getFirstEvidenceRecordId());
        assertNotNull(evidenceRecord);
        assertEquals(Indication.FAILED, evidenceRecord.getIndication());
        assertEquals(SubIndication.HASH_FAILURE, evidenceRecord.getSubIndication());
    }

}
