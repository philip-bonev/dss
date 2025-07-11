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
package eu.europa.esig.dss.xml.common;

import eu.europa.esig.dss.alert.ExceptionOnStatusAlert;
import eu.europa.esig.dss.alert.exception.AlertException;
import org.junit.jupiter.api.Test;

import javax.xml.validation.SchemaFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SchemaFactoryBuilderTest {
	
	@Test
	void buildTest() {
		SchemaFactoryBuilder schemaBuilder = SchemaFactoryBuilder.getSecureSchemaBuilder();
		SchemaFactory schemaFactory = schemaBuilder.build();
		assertNotNull(schemaFactory);
	}
	
	@Test
	void exceptionAlertTest() {
		SchemaFactoryBuilder schemaBuilder = SchemaFactoryBuilder.getSecureSchemaBuilder();
		
		schemaBuilder.enableFeature("CUSTOM_FEATURE");
		
		schemaBuilder.setSecurityExceptionAlert(new ExceptionOnStatusAlert());
		
		Exception exception = assertThrows(AlertException.class, schemaBuilder::build);
		assertNotNull(exception);
		assertTrue(exception.getMessage().contains("SECURITY : unable to set feature(s)"));
		assertTrue(exception.getMessage().contains("CUSTOM_FEATURE"));
	}

}
