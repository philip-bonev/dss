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
package eu.europa.esig.dss.asic.cades.validation;

import eu.europa.esig.dss.asic.cades.ASiCWithCAdESFormatDetector;
import eu.europa.esig.dss.asic.cades.extract.ASiCWithCAdESContainerExtractor;
import eu.europa.esig.dss.asic.cades.validation.timestamp.ASiCWithCAdESTimestampAnalyzer;
import eu.europa.esig.dss.asic.common.ASiCContent;
import eu.europa.esig.dss.asic.common.ASiCUtils;
import eu.europa.esig.dss.asic.common.extract.DefaultASiCContainerExtractor;
import eu.europa.esig.dss.asic.common.validation.ASiCManifestParser;
import eu.europa.esig.dss.asic.common.validation.ASiCManifestValidator;
import eu.europa.esig.dss.asic.common.validation.AbstractASiCContainerAnalyzer;
import eu.europa.esig.dss.cades.validation.CAdESSignature;
import eu.europa.esig.dss.cades.validation.CMSDocumentAnalyzer;
import eu.europa.esig.dss.enumerations.ASiCContainerType;
import eu.europa.esig.dss.enumerations.ASiCManifestTypeEnum;
import eu.europa.esig.dss.enumerations.ArchiveTimestampType;
import eu.europa.esig.dss.enumerations.TimestampType;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.ManifestEntry;
import eu.europa.esig.dss.model.ManifestFile;
import eu.europa.esig.dss.spi.signature.AdvancedSignature;
import eu.europa.esig.dss.spi.validation.analyzer.DocumentAnalyzer;
import eu.europa.esig.dss.spi.validation.analyzer.timestamp.TimestampAnalyzer;
import eu.europa.esig.dss.spi.validation.analyzer.timestamp.TimestampAnalyzerComparator;
import eu.europa.esig.dss.spi.validation.timestamp.DetachedTimestampSource;
import eu.europa.esig.dss.spi.x509.tsp.TimestampToken;
import eu.europa.esig.dss.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is an implementation to validate ASiC containers with CAdES signature(s)
 * 
 */
public class ASiCContainerWithCAdESAnalyzer extends AbstractASiCContainerAnalyzer {

	private static final Logger LOG = LoggerFactory.getLogger(ASiCContainerWithCAdESAnalyzer.class);

	/**
	 * The empty constructor
	 */
	ASiCContainerWithCAdESAnalyzer() {
		super();
	}

	/**
	 * The default constructor
	 * 
	 * @param asicContainer {@link DSSDocument} to be validated
	 */
	public ASiCContainerWithCAdESAnalyzer(final DSSDocument asicContainer) {
		super(asicContainer);
	}

	/**
	 * The constructor with {@link ASiCContent}
	 *
	 * @param asicContent {@link ASiCContent} to be validated
	 */
	public ASiCContainerWithCAdESAnalyzer(final ASiCContent asicContent) {
		super(asicContent);
	}

	@Override
	public boolean isSupported(DSSDocument dssDocument) {
		return new ASiCWithCAdESFormatDetector().isSupportedASiC(dssDocument);
	}

	@Override
	public boolean isSupported(ASiCContent asicContent) {
		return new ASiCWithCAdESFormatDetector().isSupportedASiC(asicContent);
	}

	@Override
	protected DefaultASiCContainerExtractor getContainerExtractor() {
		return new ASiCWithCAdESContainerExtractor(document);
	}
	
	@Override
	protected List<DocumentAnalyzer> getSignatureAnalyzers() {
		if (signatureValidators == null) {
			signatureValidators = new ArrayList<>();
			for (final DSSDocument signature : getSignatureDocuments()) {
				CMSDocumentAnalyzer cadesValidator = new CMSDocumentAnalyzer(signature);
				cadesValidator.setCertificateVerifier(certificateVerifier);
				cadesValidator.setSignaturePolicyProvider(getSignaturePolicyProvider());
				cadesValidator.setContainerContents(getArchiveDocuments());
				
				DSSDocument signedDocument = ASiCWithCAdESUtils.getSignedDocument(asicContent, signature.getName());
				if (signedDocument != null) {
					cadesValidator.setDetachedContents(Collections.singletonList(signedDocument));
				}
				
				DSSDocument signatureManifest = ASiCManifestParser.getLinkedManifest(getAllManifestDocuments(), signature.getName());
				if (signatureManifest != null) {
					ManifestFile manifestFile = getValidatedManifestFile(signatureManifest);
					cadesValidator.setManifestFile(manifestFile);
				}
				
				signatureValidators.add(cadesValidator);
			}
		}
		return signatureValidators;
	}

	/**
	 * Returns a list of timestamp validators for timestamps embedded into the container
	 *
	 * @return a list of {@link TimestampAnalyzer}s
	 */
	protected List<TimestampAnalyzer> getTimestampAnalyzers() {
		if (timestampAnalyzers == null) {
			timestampAnalyzers = new ArrayList<>();
			for (final DSSDocument timestamp : getTimestampDocuments()) {
				ASiCWithCAdESTimestampAnalyzer timestampValidator = getTimestampValidator(timestamp);
				if (timestampValidator != null) {
					timestampAnalyzers.add(timestampValidator);
				}
			}
			timestampAnalyzers.sort(new TimestampAnalyzerComparator());
		}
		return timestampAnalyzers;
	}

	private ASiCWithCAdESTimestampAnalyzer getTimestampValidator(DSSDocument timestampDocument) {
		DSSDocument timestampedDocument;
		ManifestFile manifestFile = null;
		ArchiveTimestampType archiveTimestampType = null;

		DSSDocument archiveManifest = ASiCManifestParser.getLinkedManifest(
				getAllManifestDocuments(), timestampDocument.getName());
		if (archiveManifest != null) {
			timestampedDocument = archiveManifest;
			manifestFile = getValidatedManifestFile(archiveManifest);
			if (manifestFile != null) {
				archiveTimestampType = isArchiveTimeStamp(manifestFile) ? ArchiveTimestampType.CAdES_DETACHED : null;
			} else {
				LOG.warn("A linked manifest is not found for a timestamp with name [{}]!",
						archiveManifest.getName());
			}

		} else {
			List<DSSDocument> rootLevelSignedDocuments = ASiCUtils.getRootLevelSignedDocuments(asicContent);
			if (Utils.collectionSize(rootLevelSignedDocuments) == 1) {
				timestampedDocument = rootLevelSignedDocuments.get(0);
			} else {
				LOG.warn("Timestamp {} is skipped (no linked archive manifest found / unique file)",
						timestampDocument.getName());
				return null;
			}
		}

		final ASiCWithCAdESTimestampAnalyzer timestampValidator = new ASiCWithCAdESTimestampAnalyzer(
				timestampDocument, TimestampType.CONTAINER_TIMESTAMP);
		timestampValidator.setTimestampedData(timestampedDocument);
		timestampValidator.setManifestFile(manifestFile);
		timestampValidator.setArchiveTimestampType(archiveTimestampType);
		timestampValidator.setOriginalDocuments(getAllDocuments());
		timestampValidator.setArchiveDocuments(getArchiveDocuments());
		timestampValidator.setDetachedEvidenceRecords(getDetachedEvidenceRecords());
		timestampValidator.setCertificateVerifier(certificateVerifier);
		return timestampValidator;
	}
	
	@Override
	protected List<TimestampToken> buildDetachedTimestamps() {
		DetachedTimestampSource detachedTimestampSource = new DetachedTimestampSource();
		for (TimestampAnalyzer timestampAnalyzer : getTimestampAnalyzers()) {
			detachedTimestampSource.addExternalTimestamp(timestampAnalyzer.getTimestamp());
		}
		return detachedTimestampSource.getDetachedTimestamps();
	}

	@Override
	public List<DSSDocument> getArchiveDocuments() {
		List<DSSDocument> archiveContents = super.getArchiveDocuments();
		// in case of Manifest file (ASiC-E CAdES signature) add signed documents
		if (Utils.isCollectionNotEmpty(getManifestDocuments())) {
			for (DSSDocument document : getAllDocuments()) {
				if (!archiveContents.contains(document)) {
					archiveContents.add(document);
				}
			}
		}
		return archiveContents;
	}

	@Override
	protected List<TimestampToken> attachExternalTimestamps(List<AdvancedSignature> allSignatures) {
		List<TimestampToken> externalTimestamps = new ArrayList<>();
		
		List<TimestampAnalyzer> currentTimestampAnalyzers = getTimestampAnalyzers();
		for (TimestampAnalyzer tstAnalyzer : currentTimestampAnalyzers) {
			TimestampToken timestamp = getExternalTimestamp(tstAnalyzer, allSignatures);
			if (timestamp != null) {
				externalTimestamps.add(timestamp);
			}
		}

		return externalTimestamps;
	}
	
	private TimestampToken getExternalTimestamp(TimestampAnalyzer tstAnalyzer, List<AdvancedSignature> allSignatures) {
		if (tstAnalyzer instanceof ASiCWithCAdESTimestampAnalyzer) {
			ASiCWithCAdESTimestampAnalyzer timestampValidator = (ASiCWithCAdESTimestampAnalyzer) tstAnalyzer;
			final TimestampToken timestamp = timestampValidator.getTimestamp();
			if (timestamp.getTimeStampType().isContainerTimestamp()) {
				ManifestFile coveredManifest = timestampValidator.getCoveredManifest();
				if (coveredManifest != null) {
					for (ManifestEntry entry : coveredManifest.getEntries()) {
						CAdESSignature cadesSignature = getCAdESSignatureFromFileName(allSignatures, entry.getUri());
						if (cadesSignature != null) {
							cadesSignature.addExternalTimestamp(timestamp);
						}
					}
				}
			}

			return timestamp;
		}
		return null;
	}

	private CAdESSignature getCAdESSignatureFromFileName(List<AdvancedSignature> signatures, String fileName) {
		for (AdvancedSignature advancedSignature : signatures) {
			if (Utils.areStringsEqual(fileName, advancedSignature.getFilename()) &&
					!advancedSignature.isCounterSignature()) {
				return (CAdESSignature) advancedSignature;
			}
		}
		return null;
	}
	
	private boolean isArchiveTimeStamp(ManifestFile manifestFile) {
		return ASiCUtils.coversSignature(manifestFile);
	}

	@Override
	protected List<ManifestFile> getManifestFilesDescriptions() {
		final List<ManifestFile> descriptions = new ArrayList<>();

		List<DSSDocument> manifestDocuments = getManifestDocuments();
		for (DSSDocument manifestDocument : manifestDocuments) {
			ManifestFile manifestFile = ASiCManifestParser.getManifestFile(manifestDocument);
			if (manifestFile != null) {
				ASiCManifestValidator asiceWithCAdESManifestValidator = new ASiCManifestValidator(manifestFile, getAllDocuments());
				asiceWithCAdESManifestValidator.validateEntries();
				descriptions.add(manifestFile);
			}
		}

		List<DSSDocument> archiveManifestDocuments = getArchiveManifestDocuments();
		for (DSSDocument manifestDocument : archiveManifestDocuments) {
			ManifestFile manifestFile = ASiCManifestParser.getManifestFile(manifestDocument);
			if (manifestFile != null) {
				manifestFile.setManifestType(ASiCManifestTypeEnum.ARCHIVE_MANIFEST);
				ASiCManifestValidator asiceWithCAdESManifestValidator = new ASiCManifestValidator(manifestFile, getAllDocuments());
				asiceWithCAdESManifestValidator.validateEntries();
				descriptions.add(manifestFile);
			}
		}

		List<DSSDocument> evidenceRecordManifestDocuments = getEvidenceRecordManifestDocuments();
		for (DSSDocument manifestDocument : evidenceRecordManifestDocuments) {
			ManifestFile manifestFile = ASiCManifestParser.getManifestFile(manifestDocument);
			if (manifestFile != null) {
				manifestFile.setManifestType(ASiCManifestTypeEnum.EVIDENCE_RECORD);
				ASiCManifestValidator asiceWithCAdESManifestValidator = new ASiCManifestValidator(manifestFile, getAllDocuments());
				asiceWithCAdESManifestValidator.validateEntries();
				descriptions.add(manifestFile);
			}
		}

		return descriptions;
	}
	
	@Override
	public List<DSSDocument> getOriginalDocuments(AdvancedSignature advancedSignature) {
		if (advancedSignature.isCounterSignature()) {
			CAdESSignature cadesSignature = (CAdESSignature) advancedSignature;
			return Collections.singletonList(cadesSignature.getOriginalDocument());
		}
		List<DSSDocument> retrievedDocs = advancedSignature.getDetachedContents();
		if (ASiCContainerType.ASiC_S.equals(getContainerType())) {
			return getSignedDocumentsASiCS(retrievedDocs);
		} else {
			DSSDocument linkedManifest = ASiCManifestParser.getLinkedManifest(getManifestDocuments(), advancedSignature.getFilename());
			if (linkedManifest == null) {
				return Collections.emptyList();
			}
			ManifestFile manifestFile = ASiCManifestParser.getManifestFile(linkedManifest);
			if (manifestFile == null) {
				return Collections.emptyList();
			}
			return getManifestedDocuments(manifestFile);
		}
	}
	
	private List<DSSDocument> getManifestedDocuments(ManifestFile manifestFile) {
		List<ManifestEntry> entries = manifestFile.getEntries();
		List<DSSDocument> signedDocuments = getAllDocuments();
		
		List<DSSDocument> result = new ArrayList<>();
		for (ManifestEntry entry : entries) {
			for (DSSDocument signedDocument : signedDocuments) {
				if (Utils.areStringsEqual(entry.getUri(), signedDocument.getName())) {
					result.add(signedDocument);
				}
			}
		}
		return result;
	}

}
