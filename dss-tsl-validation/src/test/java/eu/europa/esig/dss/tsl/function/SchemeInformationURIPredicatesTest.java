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
package eu.europa.esig.dss.tsl.function;

import eu.europa.esig.dss.model.DSSException;
import eu.europa.esig.trustedlist.TrustedListFacade;
import eu.europa.esig.trustedlist.jaxb.tsl.NonEmptyMultiLangURIListType;
import eu.europa.esig.trustedlist.jaxb.tsl.NonEmptyMultiLangURIType;
import eu.europa.esig.trustedlist.jaxb.tsl.TrustStatusListType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SchemeInformationURIPredicatesTest {

	private static NonEmptyMultiLangURIListType schemeInformationUriListType;

	@BeforeAll
	static void init() throws Exception {
		try (FileInputStream fis = new FileInputStream("src/test/resources/eu-lotl-pivot.xml")) {
			TrustStatusListType lotlPivot = TrustedListFacade.newFacade().unmarshall(fis);
			assertNotNull(lotlPivot);
			schemeInformationUriListType = lotlPivot.getSchemeInformation().getSchemeInformationURI();
			assertNotNull(schemeInformationUriListType);
		}
	}

	@Test
	void pivotLOTL() {
		List<String> pivotUrls = schemeInformationUriListType.getURI().stream().filter(new PivotSchemeInformationURI()).map(NonEmptyMultiLangURIType::getValue)
				.collect(Collectors.toList());
		assertEquals(4, pivotUrls.size());

		// Ensure original ordering
		assertEquals("https://ec.europa.eu/information_society/policy/esignature/trusted-list/tl-pivot-247-mp.xml", pivotUrls.get(0));
		assertEquals("https://ec.europa.eu/information_society/policy/esignature/trusted-list/tl-pivot-226-mp.xml", pivotUrls.get(1));
		assertEquals("https://ec.europa.eu/information_society/policy/esignature/trusted-list/tl-pivot-191-mp.xml", pivotUrls.get(2));
		assertEquals("https://ec.europa.eu/information_society/policy/esignature/trusted-list/tl-pivot-172-mp.xml", pivotUrls.get(3));
	}

	@Test
	void byLang() {
		assertEquals(1, schemeInformationUriListType.getURI().stream().filter(new SchemeInformationURIByLang("fr")).count());
		assertEquals(0, schemeInformationUriListType.getURI().stream().filter(new SchemeInformationURIByLang("xx")).count());
		assertThrows(NullPointerException.class, () -> new SchemeInformationURIByLang(null));
	}

	@Test
	void oj() {
		String currentPivotOjUrl = "http://eur-lex.europa.eu/legal-content/EN/TXT/?uri=uriserv:OJ.C_.2016.233.01.0001.01.ENG";

		List<String> list = schemeInformationUriListType.getURI().stream().filter(new OfficialJournalSchemeInformationURI(currentPivotOjUrl))
				.map(NonEmptyMultiLangURIType::getValue).collect(Collectors.toList());
		assertEquals(1, list.size());
		assertEquals(currentPivotOjUrl, list.get(0));

		String otherOJUrl = "https://eur-lex.europa.eu/legal-content/EN/TXT/?uri=uriserv:OJ.C_.2019.276.01.0001.01.ENG";
		list = schemeInformationUriListType.getURI().stream().filter(new OfficialJournalSchemeInformationURI(otherOJUrl)).map(NonEmptyMultiLangURIType::getValue)
				.collect(Collectors.toList());
		assertEquals(1, list.size());
		assertEquals(currentPivotOjUrl, list.get(0));

		Exception exception = assertThrows(DSSException.class, () -> schemeInformationUriListType.getURI().stream()
				.filter(new OfficialJournalSchemeInformationURI("blabla")).collect(Collectors.toList()));
		assertEquals("Incorrect format of Official Journal URL [blabla] is provided", exception.getMessage());

		exception = assertThrows(NullPointerException.class, () -> new OfficialJournalSchemeInformationURI(null));
		assertEquals("Official Journal URL cannot be null!", exception.getMessage());
	}

}
