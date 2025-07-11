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
package eu.europa.esig.dss.asic.xades.extract;

import eu.europa.esig.dss.asic.common.ASiCUtils;
import eu.europa.esig.dss.asic.common.ZipUtils;
import eu.europa.esig.dss.asic.common.extract.DefaultASiCContainerExtractor;
import eu.europa.esig.dss.model.DSSDocument;

import java.util.List;

/**
 * The class is used to extract the content (documents) embedded into an ASiC with XAdES container
 */
public class ASiCWithXAdESContainerExtractor extends DefaultASiCContainerExtractor {

	/** The manifest filename */
	private static final String METAINF_MANIFEST_FILENAME = ASiCUtils.META_INF_FOLDER + "manifest.xml";

	/**
	 * The default constructor
	 *
	 * @param archive {@link DSSDocument} representing the container
	 */
	public ASiCWithXAdESContainerExtractor(DSSDocument archive) {
		super(archive);
	}

	@Override
	public boolean isSupportedContainerFormat() {
		List<String> filenames = ZipUtils.getInstance().extractEntryNames(asicContainer);
		return ASiCUtils.isAsicFileContent(filenames) ||
				(ASiCUtils.areFilesContainMimetype(filenames) && ASiCUtils.isContainerOpenDocument(asicContainer));
	}

	@Override
	protected boolean isAllowedManifest(String entryName) {
		return entryName.equals(METAINF_MANIFEST_FILENAME);
	}

	@Override
	protected boolean isAllowedArchiveManifest(String entryName) {
		// No archive manifest in ASiC with XAdES
		return false;
	}

	@Override
	protected boolean isAllowedEvidenceRecordManifest(String entryName) {
		return ASiCUtils.isEvidenceRecordManifest(entryName);
	}

	@Override
	protected boolean isAllowedSignature(String entryName) {
		return ASiCUtils.isXAdES(entryName);
	}

	@Override
	protected boolean isAllowedTimestamp(String entryName) {
		// No timestamp file in ASiC with XAdES
		return false;
	}

	@Override
	protected boolean isAllowedEvidenceRecord(String entryName) {
		return ASiCUtils.EVIDENCE_RECORD_ERS.equals(entryName) || ASiCUtils.EVIDENCE_RECORD_XML.equals(entryName);
	}

}
