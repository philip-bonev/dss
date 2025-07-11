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

import eu.europa.esig.dss.detailedreport.jaxb.XmlValidationCertificateQualification;
import eu.europa.esig.dss.enumerations.CertificateQualifiedStatus;
import eu.europa.esig.dss.enumerations.Indication;
import eu.europa.esig.dss.enumerations.SubIndication;
import eu.europa.esig.dss.enumerations.ValidationTime;
import eu.europa.esig.dss.i18n.I18nProvider;
import eu.europa.esig.dss.i18n.MessageTag;
import eu.europa.esig.dss.model.policy.LevelRule;
import eu.europa.esig.dss.validation.process.ChainItem;

/**
 * Checks whether the certificate is qualified at validation time
 *
 */
public class QualifiedCheck extends ChainItem<XmlValidationCertificateQualification> {

	/** The certificate qualification status */
	private final CertificateQualifiedStatus qualifiedStatus;

	/** Validation time type */
	private final ValidationTime validationTime;

	/**
	 * Default constructor
	 *
	 * @param i18nProvider {@link I18nProvider}
	 * @param result {@link XmlValidationCertificateQualification}
	 * @param qualifiedStatus {@link CertificateQualifiedStatus}
	 * @param validationTime {@link ValidationTime}
	 * @param constraint {@link LevelRule}
	 */
	public QualifiedCheck(I18nProvider i18nProvider, XmlValidationCertificateQualification result,
						  CertificateQualifiedStatus qualifiedStatus, ValidationTime validationTime,
						  LevelRule constraint) {
		super(i18nProvider, result, constraint);

		this.qualifiedStatus = qualifiedStatus;
		this.validationTime = validationTime;
	}

	@Override
	protected boolean process() {
		return CertificateQualifiedStatus.isQC(qualifiedStatus);
	}

	@Override
	protected MessageTag getMessageTag() {
		switch (validationTime) {
		case BEST_SIGNATURE_TIME:
			return MessageTag.QUAL_QC_AT_ST;
		case CERTIFICATE_ISSUANCE_TIME:
			return MessageTag.QUAL_QC_AT_CC;
		case VALIDATION_TIME:
			return MessageTag.QUAL_QC_AT_VT;
		default:
			throw new IllegalArgumentException("Unsupported time " + validationTime);
		}
	}

	@Override
	protected MessageTag getErrorMessageTag() {
		switch (validationTime) {
		case BEST_SIGNATURE_TIME:
			return MessageTag.QUAL_QC_AT_ST_ANS;
		case CERTIFICATE_ISSUANCE_TIME:
			return MessageTag.QUAL_QC_AT_CC_ANS;
		case VALIDATION_TIME:
			return MessageTag.QUAL_QC_AT_VT_ANS;
		default:
			throw new IllegalArgumentException("Unsupported time " + validationTime);
		}
	}

	@Override
	protected Indication getFailedIndicationForConclusion() {
		return Indication.FAILED;
	}

	@Override
	protected SubIndication getFailedSubIndicationForConclusion() {
		return null;
	}

}
