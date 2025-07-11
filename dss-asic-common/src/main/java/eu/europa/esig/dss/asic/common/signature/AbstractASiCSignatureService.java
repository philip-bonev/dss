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
package eu.europa.esig.dss.asic.common.signature;

import eu.europa.esig.dss.asic.common.ASiCContainerEvidenceRecordParameters;
import eu.europa.esig.dss.asic.common.ASiCContent;
import eu.europa.esig.dss.asic.common.ASiCUtils;
import eu.europa.esig.dss.asic.common.ZipUtils;
import eu.europa.esig.dss.asic.common.extract.DefaultASiCContainerExtractor;
import eu.europa.esig.dss.enumerations.MimeType;
import eu.europa.esig.dss.evidencerecord.EvidenceRecordIncorporationService;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.DigestDocument;
import eu.europa.esig.dss.model.SerializableCounterSignatureParameters;
import eu.europa.esig.dss.model.SerializableEvidenceRecordIncorporationParameters;
import eu.europa.esig.dss.model.SerializableSignatureParameters;
import eu.europa.esig.dss.model.SerializableTimestampParameters;
import eu.europa.esig.dss.model.SignatureValue;
import eu.europa.esig.dss.model.ToBeSigned;
import eu.europa.esig.dss.signature.AbstractSignatureService;
import eu.europa.esig.dss.signature.CounterSignatureService;
import eu.europa.esig.dss.signature.MultipleDocumentsSignatureService;
import eu.europa.esig.dss.enumerations.SigningOperation;
import eu.europa.esig.dss.spi.validation.CertificateVerifier;
import eu.europa.esig.dss.spi.x509.tsp.TimestampToken;
import eu.europa.esig.dss.utils.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The abstract class containing the main methods for ASiC signature creation/extension
 *
 * @param <SP> implementation of signature parameters corresponding to the supported signature format
 * @param <TP> implementation of timestamp parameters corresponding to the supported document format
 * @param <CSP> implementation of counter-signature parameters corresponding to the supported signature format
 * @param <ERP> implementation of parameters used for evidince record embedding
 */
public abstract class AbstractASiCSignatureService<SP extends SerializableSignatureParameters, TP extends SerializableTimestampParameters, 
					CSP extends SerializableCounterSignatureParameters, ERP extends SerializableEvidenceRecordIncorporationParameters>
		extends AbstractSignatureService<SP, TP> implements MultipleDocumentsSignatureService<SP, TP>, CounterSignatureService<CSP>, EvidenceRecordIncorporationService<ERP> {

	private static final long serialVersionUID = 243114076381526665L;

	/**
	 * The default constructor
	 *
	 * @param certificateVerifier {@link CertificateVerifier}
	 */
	protected AbstractASiCSignatureService(CertificateVerifier certificateVerifier) {
		super(certificateVerifier);
	}

	@Override
	public TimestampToken getContentTimestamp(DSSDocument toSignDocument, SP parameters) {
		return getContentTimestamp(Arrays.asList(toSignDocument), parameters);
	}

	@Override
	public ToBeSigned getDataToSign(DSSDocument toSignDocument, SP parameters) {
		Objects.requireNonNull(toSignDocument, "toSignDocument cannot be null!");
		return getDataToSign(Arrays.asList(toSignDocument), parameters);
	}

	@Override
	public DSSDocument signDocument(DSSDocument toSignDocument, SP parameters, SignatureValue signatureValue) {
		Objects.requireNonNull(toSignDocument, "toSignDocument cannot be null!");
		return signDocument(Arrays.asList(toSignDocument), parameters, signatureValue);
	}

	@Override
	public DSSDocument timestamp(DSSDocument toTimestampDocument, TP parameters) {
		Objects.requireNonNull(toTimestampDocument, "toTimestampDocument cannot be null!");
		return timestamp(Arrays.asList(toTimestampDocument), parameters);
	}

	/**
	 * Creates a new ASiC container with the {@code evidenceRecordDocument} applied to the {@code document}.
	 * <p>
	 * If the provided original document is an existing ASiC container, then the {@code evidenceRecordDocument}
	 * will be evaluated against the container files and places within the container.
	 *
	 * @param document               a list of {@link DSSDocument}s preserved by an evidence record
	 * @param evidenceRecordDocument {@link DSSDocument} to add
	 * @param parameters             {@link ASiCContainerEvidenceRecordParameters} providing configuration for
	 *                               the evidence record incorporation
	 * @return {@link DSSDocument} ASiC container containing the evidence record file document
	 */
	public DSSDocument addContainerEvidenceRecord(DSSDocument document, DSSDocument evidenceRecordDocument,
												  ASiCContainerEvidenceRecordParameters parameters) {
		Objects.requireNonNull(document, "Document cannot be null!");
		return addContainerEvidenceRecord(Collections.singletonList(document), evidenceRecordDocument, parameters);
	}

	/**
	 * Creates a new ASiC container with the {@code evidenceRecordDocument} applied to the {@code documents}.
	 * <p>
	 * If the provided original document is an existing ASiC container, then the {@code evidenceRecordDocument}
	 * will be evaluated against the container files and places within the container.
	 *
	 * @param documents              a list of {@link DSSDocument}s preserved by an evidence record
	 * @param evidenceRecordDocument {@link DSSDocument} to add
	 * @param parameters             {@link ASiCContainerEvidenceRecordParameters} providing configuration for
	 *                               the evidence record incorporation
	 * @return {@link DSSDocument} ASiC container containing the evidence record file document
	 */
	public abstract DSSDocument addContainerEvidenceRecord(List<DSSDocument> documents, DSSDocument evidenceRecordDocument,
												  ASiCContainerEvidenceRecordParameters parameters);

	/**
	 * Extracts the content (documents) of the ASiC container
	 *
	 * @param archive {@link DSSDocument} representing an ASiC container
	 * @return {@link ASiCContent}
	 */
	protected ASiCContent extractCurrentArchive(DSSDocument archive) {
		DefaultASiCContainerExtractor extractor = getArchiveExtractor(archive);
		return extractor.extract();
	}

	/**
	 * Returns a relevant ASiC container extractor for the given format
	 *
	 * @param archive {@link DSSDocument} to get an extractor for
	 * @return an instance of {@link DefaultASiCContainerExtractor}
	 */
	protected abstract DefaultASiCContainerExtractor getArchiveExtractor(DSSDocument archive);

	/**
	 * Creates a ZIP-Archive by copying the provided documents to the new container using
	 * the current time as ZIP creation time
	 *
	 * @param asicContent            {@link ASiCContent} to create a new ZIP archive from
	 * @return {@link DSSDocument} the created ASiC Container
	 */
	protected DSSDocument buildASiCContainer(ASiCContent asicContent) {
		return buildASiCContainer(asicContent, new Date());
	}

	/**
	 * Creates a ZIP-Archive by copying the provided documents to the new container
	 * 
	 * @param asicContent            {@link ASiCContent} to create a new ZIP archive from
	 * @param creationTime           {@link Date} of the archive creation (optional)
	 * @return {@link DSSDocument} the created ASiC Container
	 */
	protected DSSDocument buildASiCContainer(ASiCContent asicContent, Date creationTime) {
		DSSDocument zipArchive = ZipUtils.getInstance().createZipArchive(asicContent, creationTime);
		zipArchive.setMimeType(ASiCUtils.getMimeType(asicContent.getMimeTypeDocument()));
		return zipArchive;
	}

	/**
	 * This method verifies whether the signature creation is possible with the provided documents
	 *
	 * @param documentsToSign a list of {@link DSSDocument}s
	 */
	protected void assertSignaturePossible(List<DSSDocument> documentsToSign) {
		if (Utils.isCollectionEmpty(documentsToSign)) {
			throw new IllegalArgumentException("List of documents to sign cannot be empty!");
		}
		for (DSSDocument document : documentsToSign) {
			if (document instanceof DigestDocument) {
				throw new IllegalArgumentException("ASiC container creation is not possible with DigestDocument!");
			}
		}
	}

	/**
	 * Verifies a validity of counter signature parameters
	 *
	 * @param parameters counter signature parameters to verify
	 */
	protected void assertCounterSignatureParametersValid(CSP parameters) {
		Objects.requireNonNull(parameters.getSignatureIdToCounterSign(), "The Id of a signature to be counter signed shall be defined! "
					+ "Please use SerializableCounterSignatureParameters.setSignatureIdToCounterSign(signatureId) method.");
	}

	/**
	 * Verifies if incorporation of a SignaturePolicyStore is possible
	 *
	 * @param asicContent {@link ASiCContent}
	 */
	protected void assertAddSignaturePolicyStorePossible(ASiCContent asicContent) {
		if (Utils.isCollectionEmpty(asicContent.getSignatureDocuments())) {
			throw new UnsupportedOperationException(
					"Signature documents of the expected format are not found in the provided ASiC Container! "
					+ "Add a SignaturePolicyStore is not possible!");
		}
	}

	/**
	 * Generates and returns a final name for the archive to create
	 *
	 * @param originalFile {@link DSSDocument} original signed/extended document container
	 * @param operation {@link SigningOperation} the performed signing operation
	 * @param containerMimeType {@link MimeType} the expected mimeType
	 * @return {@link String} the archive filename
	 */
	protected String getFinalArchiveName(DSSDocument originalFile, SigningOperation operation, MimeType containerMimeType) {
		return getFinalDocumentName(originalFile, operation, null, containerMimeType);
	}

}
