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
package eu.europa.esig.dss.pades.validation.suite;

import eu.europa.esig.dss.diagnostic.DiagnosticData;
import eu.europa.esig.dss.diagnostic.SignatureWrapper;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.InMemoryDocument;
import eu.europa.esig.dss.spi.DSSUtils;
import eu.europa.esig.dss.spi.x509.CertificateSource;
import eu.europa.esig.dss.spi.x509.CommonTrustedCertificateSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

// abde test
class DSS3111Test extends AbstractPAdESTestValidation {

    @Override
    protected DSSDocument getSignedDocument() {
        return new InMemoryDocument(getClass().getResourceAsStream("/validation/pades-lt-extended-abde.pdf"));
    }

    @Override
    protected CertificateSource getTrustedCertificateSource() {
        CommonTrustedCertificateSource trustedCertificateSource = new CommonTrustedCertificateSource();
        trustedCertificateSource.addCertificate(DSSUtils.loadCertificateFromBase64EncodedString("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"));
        trustedCertificateSource.addCertificate(DSSUtils.loadCertificateFromBase64EncodedString("MIIEVzCCAr+gAwIBAgIBATANBgkqhkiG9w0BAQ0FADBNMRAwDgYDVQQDDAdyb290LWNhMRkwFwYDVQQKDBBOb3dpbmEgU29sdXRpb25zMREwDwYDVQQLDAhQS0ktVEVTVDELMAkGA1UEBhMCTFUwHhcNMjMwNDExMDkzMDEyWhcNMjUwNDExMDkzMDEyWjBNMRAwDgYDVQQDDAdyb290LWNhMRkwFwYDVQQKDBBOb3dpbmEgU29sdXRpb25zMREwDwYDVQQLDAhQS0ktVEVTVDELMAkGA1UEBhMCTFUwggGiMA0GCSqGSIb3DQEBAQUAA4IBjwAwggGKAoIBgQChAl5g1wWKs3If1lebUeFVWxNO60uYMtUV60Sat2shEsRmnmHD1cWTyK1blEQpxKFbjxqNIoVabmBzMT3pdS8oaAuQXCx5zaVGU83NXuAELg1V3GbjmbEj1sMeE1cVEceb1GJJgCw2s0P6++opalJHC+8loihRUi2hZE6NoEHYPcjqBSXWfOzsiuCA0aEoanRwZ7UG/uqdZYShAh59Juan56fhy78cvBQn0N0njij8dk+0e7JFf+LBRORLTAGSlyepVx6MST8ZoQ51HusZnfdG/LO1DWoqsdS26y+PasiNee5xNa1FOEViWh5V9AH8kFmE3DO0TOG640ure5nA7BkQ/1+eF1BqRuaZq8HERZbm7OVViO06gTJRpxtGuZigUtUTPc03On38c0xTMO4GhvsgnTHs2T7ABJY0hKkoygtVBwhH2JUexdjfqqOL5YYaR0kHXmSXq/O2aqwPRbeD8DzOIsB2UiYeX4374iXGTj7CqRHH0+gK5g5z5z/P80Abu1sCAwEAAaNCMEAwDgYDVR0PAQH/BAQDAgEGMB0GA1UdDgQWBBRTxpLeSvXLPe4DdyS/cuVe/jbJjTAPBgNVHRMBAf8EBTADAQH/MA0GCSqGSIb3DQEBDQUAA4IBgQBuMzdr4TUo6Kezeds1fmespxtWxkUTWQzSlFCnG3cpg5TLb/2aOx/2/9oiL4/CRW/sIS5jRAqd4+6cN+o7YuYYEPVMnLM2LLyyZjR7xH64PMzmlIOvxqIbXom8i2SY+7GHGnB00pDkJCDHVfZwLshCGOql4QgPbW1hz5Q81B95BJbC3AVyH6wWl5JUJ+SX16UEm9WE2Eoyqv4JNov3ZrU9tYjU3sqwu4CzE92RiBIYuaZ875pifemU97EZnWEZxOeF1jgHN5llWMeLt6ZwW/lWWFHaTjzy0HiijmAa984G5Iir3yMgyTqeDniZYGn3YjrM6vQTL0/B682BX6pADV4vLIrwcyWHUVqHUOXdvVipOkKFTrATgY2oW5VSsEJrGafDqb6MXfSjiv7ouTlY8eEslcV14Tt7KeDrSjOjzFfr5xSF+Amay9lUaDFWRGeAvMeJ1tqe1dwp+7G8Uh40bvO2KdduklD5LT671/pRbOWr5XItyu4rMfLhIM4DuHGnqck="));
        trustedCertificateSource.addCertificate(DSSUtils.loadCertificateFromBase64EncodedString("MIIEfjCCAuagAwIBAgIBZTANBgkqhkiG9w0BAQsFADBdMSAwHgYDVQQDDBdzZWxmLXNpZ25lZC10cnVzdGVkLXRzYTEZMBcGA1UECgwQTm93aW5hIFNvbHV0aW9uczERMA8GA1UECwwIUEtJLVRFU1QxCzAJBgNVBAYTAkxVMB4XDTIzMDUxMTA5MzAzMFoXDTI1MDMxMTA5MzAzMFowXTEgMB4GA1UEAwwXc2VsZi1zaWduZWQtdHJ1c3RlZC10c2ExGTAXBgNVBAoMEE5vd2luYSBTb2x1dGlvbnMxETAPBgNVBAsMCFBLSS1URVNUMQswCQYDVQQGEwJMVTCCAaIwDQYJKoZIhvcNAQEBBQADggGPADCCAYoCggGBALsPfnlgAIVomwJ6Y+4WuJcFMJH/jjS29FLmmykg7vzvVYatWhuppknhOQ006A2B7IMSTD855UdYZIIwW3NNXhU8tqo8/Vbjeb6PA6o70I+ZOglVm5sdG0z+6tTS9I0AuBVbsPfKex/0L6lLyFORStI3vTOSB9pP+uvrs7GUBRAaotFMj9n1RsHziMxzAnrtLTqgx+YqnsBQwP1K0OIZWB+cGziv+TUJ6PDaGnKrgnqQ0+wisLIK/IRUIhOLX9daFY4HjwKskjBF8Lrpl+T1+UbaPFqyhNsK9UadTzk0UiY76qnLar6Z8QfVtF4VIDjwbv7WWRmJACK8A+B6dhvPuAdTkp01yK3HJlVrnQkj/y+ups1mRfZOpruyD6hQM6mX6CbfuyNWwl5cF7L/+9glgiQ0NRPSWS8SWZNp0Lu1qcxK9qYjbJ0zNOXB0hgJdBmqPFV4tNoQE+mBDKrpiU5Bh979xclokVsSyCSALonOlakxvMqzq/mXfv0Q8jzMtmKtNQIDAQABo0kwRzAOBgNVHQ8BAf8EBAMCB4AwFgYDVR0lAQH/BAwwCgYIKwYBBQUHAwgwHQYDVR0OBBYEFK8xxt4qDzgdPtoShukF+lzPhW51MA0GCSqGSIb3DQEBCwUAA4IBgQA3XooPj3KKfV5oLFf+suJ4UsepGdflG8gGXQrohO8jwU5GE1N1d/KBGubsGACrORmrWBQRSdzwlPWFvDhBXOq4HAjDf1FKAU9fGi00fOKgFZxI23EgLjTOv7Om1uKOZsJ+Zm5SzzS7f8ECWinXxPyHL98C3Lzp6ln8oUTwu/3q2V7Sn5enTJ3kZjkHP7FLxoStVUzsGK7ZnwGnNHjgtTT7fAUKQLoUs3okks93g8u63Vwoj6SqLP4pAg295W5qlCejT6dceAMfTj3OIJVP5KvgdA2duU5z0N52qpneAw1y5d9hRuq1W8PbHyfuICM92b5BvwYobEOvl/GvB+VS9F2QRZbEsjRqJce8/h0Sj/047js3SGAzylbpHqBRs4mhyicVZkWlKuky2EQQOQHPCV4tW4nx+niQ7vQPdhxX0/ziBL41+H6MkkeBNIF0vgT8pJ4f0f+7pSWvlZjCDuS+IJMv+2CouIwFAJKSictMTJzimCl0yZROcV7ljzT2yRHPy78="));
        trustedCertificateSource.addCertificate(DSSUtils.loadCertificateFromBase64EncodedString("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"));
        return trustedCertificateSource;
    }

    @Override
    protected void checkSignatureLevel(DiagnosticData diagnosticData) {
        boolean ltLevelSigFound = false;
        boolean tLevelSigFound = false;
        for (SignatureWrapper signatureWrapper : diagnosticData.getSignatures()) {
            if (SignatureLevel.PAdES_BASELINE_LT.equals(signatureWrapper.getSignatureFormat())) {
                ltLevelSigFound = true;
            } else if (SignatureLevel.PAdES_BASELINE_T.equals(signatureWrapper.getSignatureFormat())) {
                tLevelSigFound = true;
            }
        }
        assertTrue(ltLevelSigFound);
        assertTrue(tLevelSigFound);
    }

}
