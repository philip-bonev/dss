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
package eu.europa.esig.dss.pdf;

import eu.europa.esig.dss.enumerations.PdfLockAction;
import eu.europa.esig.dss.enumerations.CertificationPermission;

import java.io.Serializable;
import java.util.List;

/**
 * This class defines a list of restrictions imposed to a PDF document modifications
 * by the current signature/field
 *
 */
public class SigFieldPermissions implements Serializable {

    private static final long serialVersionUID = -9025990647233753969L;

    /** Indicates the set of fields that should be locked */
    private PdfLockAction action;

    /** Contains a set of fields */
    private List<String> fields;

    /** The access permissions (optional) */
    private CertificationPermission certificationPermission;

    /**
     * Default constructor instantiating object with null values
     */
    public SigFieldPermissions() {
        // empty
    }

    /**
     * Gets the defined action
     *
     * @return {@link PdfLockAction}
     */
    public PdfLockAction getAction() {
        return action;
    }

    /**
     * Sets the action
     *
     * @param action {@link PdfLockAction}
     */
    public void setAction(PdfLockAction action) {
        this.action = action;
    }

    /**
     * Gets a list of field names
     *
     * @return a list of {@link String}s
     */
    public List<String> getFields() {
        return fields;
    }

    /**
     * Sets a list of field names
     *
     * @param fields a list of {@link String}s
     */
    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    /**
     * Gets the {@code CertificationPermission}
     *
     * @return {@link CertificationPermission}
     */
    public CertificationPermission getCertificationPermission() {
        return certificationPermission;
    }

    /**
     * Sets the {@code CertificationPermission}
     *
     * @param certificationPermission {@link CertificationPermission}
     */
    public void setCertificationPermission(CertificationPermission certificationPermission) {
        this.certificationPermission = certificationPermission;
    }

}
