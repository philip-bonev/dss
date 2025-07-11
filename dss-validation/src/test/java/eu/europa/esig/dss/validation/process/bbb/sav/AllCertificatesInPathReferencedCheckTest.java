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
package eu.europa.esig.dss.validation.process.bbb.sav;

import eu.europa.esig.dss.detailedreport.jaxb.XmlConstraint;
import eu.europa.esig.dss.detailedreport.jaxb.XmlSAV;
import eu.europa.esig.dss.detailedreport.jaxb.XmlStatus;
import eu.europa.esig.dss.diagnostic.SignatureWrapper;
import eu.europa.esig.dss.diagnostic.jaxb.XmlCertificate;
import eu.europa.esig.dss.diagnostic.jaxb.XmlCertificateRef;
import eu.europa.esig.dss.diagnostic.jaxb.XmlChainItem;
import eu.europa.esig.dss.diagnostic.jaxb.XmlFoundCertificates;
import eu.europa.esig.dss.diagnostic.jaxb.XmlRelatedCertificate;
import eu.europa.esig.dss.diagnostic.jaxb.XmlSignature;
import eu.europa.esig.dss.enumerations.CertificateRefOrigin;
import eu.europa.esig.dss.enumerations.Level;
import eu.europa.esig.dss.policy.LevelConstraintWrapper;
import eu.europa.esig.dss.policy.jaxb.LevelConstraint;
import eu.europa.esig.dss.validation.process.bbb.AbstractTestCheck;
import eu.europa.esig.dss.validation.process.bbb.sav.checks.AllCertificatesInPathReferencedCheck;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AllCertificatesInPathReferencedCheckTest extends AbstractTestCheck {

	@Test
	void certificatePathCheckTest() {
		XmlSignature sig = new XmlSignature();
		sig.setFoundCertificates(new XmlFoundCertificates());
		
		sig.getCertificateChain().add(getXmlChainItem("C-Id-1"));
		sig.getCertificateChain().add(getXmlChainItem("C-Id-2"));
		sig.getCertificateChain().add(getXmlChainItem("C-Id-3"));
		
		XmlRelatedCertificate xmlRelatedCertificateOne = getXmlRelatedCertificate("C-Id-1");
		xmlRelatedCertificateOne.getCertificateRefs().add(getSigningCertificateRef());
		XmlRelatedCertificate xmlRelatedCertificateTwo = getXmlRelatedCertificate("C-Id-2");
		xmlRelatedCertificateTwo.getCertificateRefs().add(getSigningCertificateRef());
		XmlRelatedCertificate xmlRelatedCertificateThree = getXmlRelatedCertificate("C-Id-3");
		xmlRelatedCertificateThree.getCertificateRefs().add(getSigningCertificateRef());
		
		sig.getFoundCertificates().getRelatedCertificates().add(xmlRelatedCertificateOne);
		sig.getFoundCertificates().getRelatedCertificates().add(xmlRelatedCertificateTwo);
		sig.getFoundCertificates().getRelatedCertificates().add(xmlRelatedCertificateThree);

		LevelConstraint constraint = new LevelConstraint();
		constraint.setLevel(Level.FAIL);

		XmlSAV result = new XmlSAV();
		AllCertificatesInPathReferencedCheck cpc = new AllCertificatesInPathReferencedCheck(i18nProvider, result, new SignatureWrapper(sig), new LevelConstraintWrapper(constraint));
		cpc.execute();

		List<XmlConstraint> constraints = result.getConstraint();
		assertEquals(1, constraints.size());
		assertEquals(XmlStatus.OK, constraints.get(0).getStatus());
	}

	@Test
	void failedCertificatePathCheckTest() {
		XmlSignature sig = new XmlSignature();
		sig.setFoundCertificates(new XmlFoundCertificates());
		
		sig.getCertificateChain().add(getXmlChainItem("C-Id-1"));
		sig.getCertificateChain().add(getXmlChainItem("C-Id-2"));
		sig.getCertificateChain().add(getXmlChainItem("C-Id-3"));
		
		XmlRelatedCertificate xmlRelatedCertificateOne = getXmlRelatedCertificate("C-Id-1");
		xmlRelatedCertificateOne.getCertificateRefs().add(getSigningCertificateRef());
		XmlRelatedCertificate xmlRelatedCertificateTwo = getXmlRelatedCertificate("C-Id-2");
		xmlRelatedCertificateTwo.getCertificateRefs().add(getSigningCertificateRef());
		XmlRelatedCertificate xmlRelatedCertificateThree = getXmlRelatedCertificate("C-Id-4");
		xmlRelatedCertificateThree.getCertificateRefs().add(getSigningCertificateRef());
		
		sig.getFoundCertificates().getRelatedCertificates().add(xmlRelatedCertificateOne);
		sig.getFoundCertificates().getRelatedCertificates().add(xmlRelatedCertificateTwo);
		sig.getFoundCertificates().getRelatedCertificates().add(xmlRelatedCertificateThree);

		LevelConstraint constraint = new LevelConstraint();
		constraint.setLevel(Level.FAIL);

		XmlSAV result = new XmlSAV();
		AllCertificatesInPathReferencedCheck cpc = new AllCertificatesInPathReferencedCheck(i18nProvider, result, new SignatureWrapper(sig), new LevelConstraintWrapper(constraint));
		cpc.execute();

		List<XmlConstraint> constraints = result.getConstraint();
		assertEquals(1, constraints.size());
		assertEquals(XmlStatus.NOT_OK, constraints.get(0).getStatus());
	}

	@Test
	void additionalReferenceCertificatePathCheckTest() {
		XmlSignature sig = new XmlSignature();
		sig.setFoundCertificates(new XmlFoundCertificates());
		
		sig.getCertificateChain().add(getXmlChainItem("C-Id-1"));
		sig.getCertificateChain().add(getXmlChainItem("C-Id-2"));
		sig.getCertificateChain().add(getXmlChainItem("C-Id-3"));
		
		XmlRelatedCertificate xmlRelatedCertificateOne = getXmlRelatedCertificate("C-Id-1");
		xmlRelatedCertificateOne.getCertificateRefs().add(getSigningCertificateRef());
		XmlRelatedCertificate xmlRelatedCertificateTwo = getXmlRelatedCertificate("C-Id-2");
		xmlRelatedCertificateTwo.getCertificateRefs().add(getSigningCertificateRef());
		XmlRelatedCertificate xmlRelatedCertificateThree = getXmlRelatedCertificate("C-Id-3");
		xmlRelatedCertificateThree.getCertificateRefs().add(getSigningCertificateRef());
		XmlRelatedCertificate xmlRelatedCertificateFour = getXmlRelatedCertificate("C-Id-4");
		xmlRelatedCertificateFour.getCertificateRefs().add(getSigningCertificateRef());
		
		sig.getFoundCertificates().getRelatedCertificates().add(xmlRelatedCertificateOne);
		sig.getFoundCertificates().getRelatedCertificates().add(xmlRelatedCertificateTwo);
		sig.getFoundCertificates().getRelatedCertificates().add(xmlRelatedCertificateThree);
		sig.getFoundCertificates().getRelatedCertificates().add(xmlRelatedCertificateFour);

		LevelConstraint constraint = new LevelConstraint();
		constraint.setLevel(Level.FAIL);

		XmlSAV result = new XmlSAV();
		AllCertificatesInPathReferencedCheck scrc = new AllCertificatesInPathReferencedCheck(i18nProvider, result, new SignatureWrapper(sig), new LevelConstraintWrapper(constraint));
		scrc.execute();

		List<XmlConstraint> constraints = result.getConstraint();
		assertEquals(1, constraints.size());
		assertEquals(XmlStatus.OK, constraints.get(0).getStatus());
	}

	@Test
	void additionalCertificateCertificatePathCheckTest() {
		XmlSignature sig = new XmlSignature();
		sig.setFoundCertificates(new XmlFoundCertificates());
		
		sig.getCertificateChain().add(getXmlChainItem("C-Id-1"));
		sig.getCertificateChain().add(getXmlChainItem("C-Id-2"));
		sig.getCertificateChain().add(getXmlChainItem("C-Id-3"));
		sig.getCertificateChain().add(getXmlChainItem("C-Id-4"));
		
		XmlRelatedCertificate xmlRelatedCertificateOne = getXmlRelatedCertificate("C-Id-1");
		xmlRelatedCertificateOne.getCertificateRefs().add(getSigningCertificateRef());
		XmlRelatedCertificate xmlRelatedCertificateTwo = getXmlRelatedCertificate("C-Id-2");
		xmlRelatedCertificateTwo.getCertificateRefs().add(getSigningCertificateRef());
		XmlRelatedCertificate xmlRelatedCertificateThree = getXmlRelatedCertificate("C-Id-3");
		xmlRelatedCertificateThree.getCertificateRefs().add(getSigningCertificateRef());
		
		sig.getFoundCertificates().getRelatedCertificates().add(xmlRelatedCertificateOne);
		sig.getFoundCertificates().getRelatedCertificates().add(xmlRelatedCertificateTwo);
		sig.getFoundCertificates().getRelatedCertificates().add(xmlRelatedCertificateThree);

		LevelConstraint constraint = new LevelConstraint();
		constraint.setLevel(Level.FAIL);

		XmlSAV result = new XmlSAV();
		AllCertificatesInPathReferencedCheck scrc = new AllCertificatesInPathReferencedCheck(i18nProvider, result, new SignatureWrapper(sig), new LevelConstraintWrapper(constraint));
		scrc.execute();

		List<XmlConstraint> constraints = result.getConstraint();
		assertEquals(1, constraints.size());
		assertEquals(XmlStatus.NOT_OK, constraints.get(0).getStatus());
	}

	@Test
	void oneReferenceTest() {
		XmlSignature sig = new XmlSignature();
		sig.setFoundCertificates(new XmlFoundCertificates());

		sig.getCertificateChain().add(getXmlChainItem("C-Id-1"));
		sig.getCertificateChain().add(getXmlChainItem("C-Id-2"));
		sig.getCertificateChain().add(getXmlChainItem("C-Id-3"));
		sig.getCertificateChain().add(getXmlChainItem("C-Id-4"));

		XmlRelatedCertificate xmlRelatedCertificateOne = getXmlRelatedCertificate("C-Id-1");
		xmlRelatedCertificateOne.getCertificateRefs().add(getSigningCertificateRef());

		sig.getFoundCertificates().getRelatedCertificates().add(xmlRelatedCertificateOne);

		LevelConstraint constraint = new LevelConstraint();
		constraint.setLevel(Level.FAIL);

		XmlSAV result = new XmlSAV();
		AllCertificatesInPathReferencedCheck scrc = new AllCertificatesInPathReferencedCheck(i18nProvider, result, new SignatureWrapper(sig), new LevelConstraintWrapper(constraint));
		scrc.execute();

		List<XmlConstraint> constraints = result.getConstraint();
		assertEquals(1, constraints.size());
		assertEquals(XmlStatus.NOT_OK, constraints.get(0).getStatus());
	}
	
	private XmlChainItem getXmlChainItem(String id) {
		XmlChainItem xmlChainItem = new XmlChainItem();
		xmlChainItem.setCertificate(getXmlCertificate(id));
		return xmlChainItem;
	}
	
	private XmlCertificate getXmlCertificate(String id) {
		XmlCertificate xmlCertificate = new XmlCertificate();
		xmlCertificate.setId(id);
		return xmlCertificate;
	}
	
	private XmlRelatedCertificate getXmlRelatedCertificate(String id) {
		XmlRelatedCertificate xmlRelatedCertificate = new XmlRelatedCertificate();
		xmlRelatedCertificate.setCertificate(getXmlCertificate(id));
		return xmlRelatedCertificate;
	}
	
	private XmlCertificateRef getSigningCertificateRef() {
		XmlCertificateRef xmlCertificateRef = new XmlCertificateRef();
		xmlCertificateRef.setOrigin(CertificateRefOrigin.SIGNING_CERTIFICATE);
		return xmlCertificateRef;
	}
	
}
