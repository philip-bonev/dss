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
package eu.europa.esig.dss.cms;

import eu.europa.esig.dss.enumerations.MimeTypeEnum;
import eu.europa.esig.dss.model.CommonDocument;
import eu.europa.esig.dss.model.DSSException;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.cms.CMSSignedData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 * A document composed by a CMSSignedData
 */
public class CMSSignedDocument extends CommonDocument {

	private static final long serialVersionUID = 1413370170096318058L;

	/**
	 * The CMSSignedData representing the document
	 */
	protected CMSSignedData signedData;

	/**
	 * The default constructor for CMSSignedDocument.
	 *
	 * @param data
	 *            the CMSSignedData
	 */
	public CMSSignedDocument(final CMSSignedData data) {
		this(data, null);
	}

	/**
	 * The constructor for CMSSignedDocument with a custom document name
	 *
	 * @param data
	 *            the CMSSignedData
	 * @param name
	 * 			  {@link String} document name
	 */
	public CMSSignedDocument(final CMSSignedData data, String name) {
		Objects.requireNonNull(data, "The CMSSignedData cannot be null");
		this.signedData = data;
		this.name = name;
		this.mimeType = MimeTypeEnum.PKCS7;
	}

	@Override
	public InputStream openStream() {
		return new ByteArrayInputStream(getBytes());
	}

	/**
	 * Gets {@code CMSSignedData}
	 *
	 * @return {@link CMSSignedData} the signedData
	 */
	public CMSSignedData getCMSSignedData() {
		return signedData;
	}

	/**
	 * Returns the encoded binaries of the CMSSignedData
	 *
	 * @return binaries
	 */
	public byte[] getBytes() {
		try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
			writeTo(output);
			return output.toByteArray();
		} catch (IOException e) {
			throw new DSSException(String.format("An error occurred while reading CMSSignedData binaries : %s", e.getMessage()), e);
		}
	}

	/**
	 * This method writes a content of the CMS into an {@code OutputStream} using the DL coding
	 *
	 * @param stream
	 *            the output stream where to write
	 * @throws IOException if an error occurs on writing
	 */
	@Override
	public void writeTo(OutputStream stream) throws IOException {
		// keep DL to ensure the original order of elements
		final ASN1OutputStream asn1OutputStream = ASN1OutputStream.create(stream, ASN1Encoding.DL);
		asn1OutputStream.writeObject(signedData.toASN1Structure());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		CMSSignedDocument that = (CMSSignedDocument) o;
		if ((signedData == null || that.signedData == null) && signedData != that.signedData) {
			return false;
		}
        return signedData == null || Objects.equals(signedData.toASN1Structure(), that.signedData.toASN1Structure());
    }

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + Objects.hashCode(signedData.toASN1Structure());
		return result;
	}

}
