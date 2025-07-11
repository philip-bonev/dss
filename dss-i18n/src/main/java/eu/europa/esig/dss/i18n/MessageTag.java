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
package eu.europa.esig.dss.i18n;

/**
 * Contains a list of possible message tags.
 * NOTE: all message tags shall be listed in messages.properties file
 *
 */
public enum MessageTag {
	
	/* BBB -> FC */
	BBB_FC_IEFF,
	BBB_FC_IEFF_ANS,

	BBB_FC_ICFD,
	BBB_FC_ICFD_ANS,
	
	BBB_FC_ISD,
	BBB_FC_ISD_ANS,

	BBB_FC_ISRIA,
	BBB_FC_ISRIA_ANS,
	
	BBB_FC_IOSIP,
	BBB_FC_IOSIP_ANS,

	BBB_FC_DASTHVBR,
	BBB_FC_DASTHVBR_ANS,
	BBB_FC_DBTOOST,
	BBB_FC_DBTOOST_ANS,
	BBB_FC_IBRV,
	BBB_FC_IBRV_ANS,

	BBB_FC_ISDC,
	BBB_FC_ISDC_ANS,
	
	BBB_FC_DSFREAP,
	BBB_FC_DSFREAP_ANS,
	
	BBB_FC_IAOD,
	BBB_FC_IAOD_ANS,
	
	BBB_FC_IVDBSFR,
	BBB_FC_IVDBSFR_ANS,

	BBB_FC_ISVADMDPD,
	BBB_FC_ISVADMDPD_ANS,
	BBB_FC_ISVAFMDPD,
	BBB_FC_ISVAFMDPD_ANS,
	BBB_FC_ISVASFLD,
	BBB_FC_ISVASFLD_ANS,
	BBB_FC_DSCNFFSM,
	BBB_FC_DSCNFFSM_ANS,
	BBB_FC_DSCNACMDM,
	BBB_FC_DSCNACMDM_ANS,
	BBB_FC_DSCNUOM,
	BBB_FC_DSCNUOM_ANS,

	BBB_FC_IECKSCDA,
	BBB_FC_IECKSCDA_ANS1,
	BBB_FC_IECKSCDA_ANS2,
	BBB_FC_IECKSCDA_ANS3,
	BBB_FC_IECKSCDA_ANS4,
	BBB_FC_IECKSCDA_ANS5,

	BBB_FC_DDAPDFAF,
	BBB_FC_DDAPDFAF_ANS,
	BBB_FC_IDPDFAC,
	BBB_FC_IDPDFAC_ANS,
	
	BBB_FC_IECTF,
	BBB_FC_IECTF_ANS,

	BBB_FC_ISFCS,
	BBB_FC_ISFCS_ANS,
	BBB_FC_ITFCS,
	BBB_FC_ITFCS_ANS,
	BBB_FC_IMFCS,
	BBB_FC_IMFCS_ANS,

	BBB_FC_ITZCP,
	BBB_FC_ITZCP_ANS,
	
	BBB_FC_ITEZCF,
	BBB_FC_ITEZCF_ANS,
	
	BBB_FC_ITMFP,
	BBB_FC_ITMFP_ANS,
	
	BBB_FC_IEMCF,
	BBB_FC_IEMCF_ANS,

	BBB_FC_IMFP_ASICE,
	BBB_FC_IMFP_ASICE_ANS,

	BBB_FC_ISFP_ASICE,
	BBB_FC_ISFP_ASICE_ANS,
	
	BBB_FC_ISFP_ASICS,
	BBB_FC_ISFP_ASICS_ANS,

	BBB_FC_ISFP_ASTFORAMC,
	BBB_FC_ISFP_ASTFORAMC_ANS,

	BBB_FC_IAHIV,
	BBB_FC_IAHIV_ANS,
	
	/* BBB -> CV */
	BBB_CV_IRDOF,
	BBB_CV_IRDOF_ANS,
	BBB_CV_TSP_IRDOF,
	BBB_CV_TSP_IRDOF_ANS,
	BBB_CV_CS_CSSVF,
	BBB_CV_CS_CSSVF_ANS,
	BBB_CV_IMEOF,
	BBB_CV_IMEOF_ANS,
	BBB_CV_ER_IODOF,
	BBB_CV_ER_IODOF_ANS,
	BBB_CV_ER_HASSDOC,
	BBB_CV_ER_HASSDOC_ANS,
	BBB_CV_ER_DFHVLCDOG,
	BBB_CV_ER_DFHVLCDOG_ANS,
	BBB_CV_ER_ATSRF,
	BBB_CV_ER_ATSRF_ANS,
	BBB_CV_ER_ATSSRF,
	BBB_CV_ER_ATSSRF_ANS,
	BBB_CV_IRDOI,
	BBB_CV_IRDOI_ANS,
	BBB_CV_TSP_IRDOI,
	BBB_CV_TSP_IRDOI_ANS,
	BBB_CV_CS_CSPS,
	BBB_CV_CS_CSPS_ANS,
	BBB_CV_IMEDOI,
	BBB_CV_IMEDOI_ANS,
	BBB_CV_ER_ATSRI,
	BBB_CV_ER_ATSRI_ANS,
	BBB_CV_ER_ATSSRI,
	BBB_CV_ER_ATSSRI_ANS,
	BBB_CV_ISMEC,
	BBB_CV_ISMEC_ANS,
	BBB_CV_ISMEC_ANS_2,
	BBB_CV_AAMEF,
	BBB_CV_AAMEF_ANS,
	BBB_CV_ER_TST_RN,
	BBB_CV_ER_TST_RN_ANS_1,
	BBB_CV_ER_TST_RN_ANS_2,

	BBB_CV_DRNMND,
	BBB_CV_DRNMND_ANS,
	BBB_CV_DMENMND,
	BBB_CV_DMENMND_ANS,
	
	BBB_CV_ISI,
	BBB_CV_ISIC,
	BBB_CV_ISIR,
	BBB_CV_ISIT,
	BBB_CV_ISI_ANS,
	
	BBB_CV_IAFS,
	BBB_CV_IAFS_ANS,
	
	/* BBB -> ICS */
	BBB_ICS_ISCI,
	BBB_ICS_ISCI_ANS,
	BBB_ICS_ISASCP,
	BBB_ICS_ISASCP_ANS,
	BBB_ICS_ISASCPU,
	BBB_ICS_ISASCPU_ANS,
	BBB_ICS_ISACDP,
	BBB_ICS_ISACDP_ANS,
	BBB_ICS_ICDVV,
	BBB_ICS_ICDVV_ANS,
	BBB_ICS_ICDVVS,
	BBB_ICS_ICDVVS_ANS,
	BBB_ICS_AIDNASNE,
	BBB_ICS_AIDNASNE_ANS,
	BBB_ICS_ISAKIDP,
	BBB_ICS_ISAKIDP_ANS,
	BBB_ICS_DKIDVM,
	BBB_ICS_DKIDVM_ANS,

	/* BBB -> RFC */
	BBB_RFC_NUP,
	BBB_RFC_NUP_ANS,
	BBB_RFC_IRIF,
	BBB_RFC_IRIF_TUNU,
	BBB_RFC_IRIF_ANS,
	
	ADEST_ROBVPIIC,
	ADEST_ROBVPIIC_ANS,
	ADEST_ROTVPIIC,
	ADEST_ROTVPIIC_ANS,
	ADEST_RORPIIC,
	ADEST_RORPIIC_ANS,

	BSV_IFCRC,
	BSV_IFCRC_ANS,
	BSV_IISCRC,
	BSV_IISCRC_ANS,
	BSV_IVCIRC,
	BSV_IVCIRC_ANS,
	BSV_IXCVRC,
	BSV_IXCVRC_ANS,
	BSV_ISCRAVTC,
	BSV_ISCRAVTC_ANS,
	BSV_IVTAVRSC,
	BSV_IVTAVRSC_ANS,
	BSV_ISCCTC,
	BSV_ISCCTC_ANS,
	BSV_ICTGTNASCRT,
	BSV_ICTGTNASCRT_ANS,
	BSV_ICTGTNASCET,
	BSV_ICTGTNASCET_ANS,
	BSV_ICVRC,
	BSV_ICVRC_ANS,
	BSV_ISAVRC,
	BSV_ISAVRC_ANS,
	BSV_ICTGTNACCET,
	BSV_ICTGTNACCET_ANS,

	LTV_ABSV,
	LTV_ABSV_ANS,
	LTV_ISCKNR,
	LTV_ISCKNR_ANS0,
	LTV_ISCKNR_ANS1,
	
	ARCH_LTVV,
	ARCH_LTVV_ANS,
	ARCH_LTAIVMP,
	ARCH_LTAIVMP_ANS,
	ARCH_IRTVBBA,
	ARCH_IRTVBBA_ANS,
	ARCH_ICHFCRLPOET,
	ARCH_ICHFCRLPOET_ANS,

	ACCM,
	ASCCM_EAA,
	ASCCM_EAA_ANS,
	ASCCM_DAA,
	ASCCM_DAA_ANS,
	ASCCM_DAA_ANS_2,
	ASCCM_APKSA,
	ASCCM_APKSA_ANS,
	ASCCM_AR,
	ASCCM_AR_ANS_ANR,
	ASCCM_AR_ANS_AKSNR,
	ASCCM_PKSK,
	ASCCM_PKSK_ANS,

	ACCM_DESC_WITH_ID,
	ACCM_DESC_WITH_ID_RESULT,
	ACCM_DESC_WITH_NAME,
	
	ACCM_POS_SIG_SIG,
	ACCM_POS_TST_SIG,
	ACCM_POS_REVOC_SIG,

	ACCM_POS_CNTR_SIG,
	ACCM_POS_CNTR_SIG_PL,
	ACCM_POS_CON_DIG,
	ACCM_POS_ER_ADO,
	ACCM_POS_ER_ADO_PL,
	ACCM_POS_ER_OR,
	ACCM_POS_ER_OR_PL,
	ACCM_POS_ER_TST,
	ACCM_POS_ER_TST_SEQ,
	ACCM_POS_ER_MST_SIG,
	ACCM_POS_JWS,
	ACCM_POS_KEY,
	ACCM_POS_KEY_PL,
	ACCM_POS_MAN,
	ACCM_POS_MAN_PL,
	ACCM_POS_MAN_ENT,
	ACCM_POS_MAN_ENT_PL,
	ACCM_POS_MES_DIG,
	ACCM_POS_MESS_IMP,
	ACCM_POS_REF,
	ACCM_POS_REF_PL,
	ACCM_POS_SIG_D_ENT,
	ACCM_POS_SIG_D_ENT_PL,
	ACCM_POS_SIGND_PRT,
	ACCM_POS_SIGNTR_PRT,
	
	ACCM_POS_CERT_CHAIN_SIG,
	ACCM_POS_CERT_CHAIN_TST,
	ACCM_POS_CERT_CHAIN_REVOC,
	ACCM_POS_CERT_CHAIN,
	ACCM_POS_SIG_CERT_REF,

	BBB_SAV_DSCACRCC,
	BBB_SAV_DSCACRCC_ANS,
	BBB_SAV_ACPCCRSCA,
	BBB_SAV_ACPCCRSCA_ANS,

	BBB_SAV_ISVA,
	BBB_SAV_ISVA_ANS,
	BBB_SAV_ISSV,
	BBB_SAV_ISSV_ANS,
	BBB_SAV_ICERRM,
	BBB_SAV_ICERRM_ANS,
	BBB_SAV_ICRM,
	BBB_SAV_ICRM_ANS,
	BBB_SAV_ISQPCTP,
	BBB_SAV_ISQPCTP_ANS,
	BBB_SAV_ISQPCHP,
	BBB_SAV_ISQPCHP_ANS,
	BBB_SAV_ISQPCIP,
	BBB_SAV_ISQPCIP_ANS,
	BBB_SAV_ISQPCTSIP,
	BBB_SAV_ISQPCTSIP_ANS,
	BBB_SAV_ISQPSLP,
	BBB_SAV_ISQPSLP_ANS,
	BBB_SAV_ISQPSTP,
	BBB_SAV_ISQPSTP_ANS,
	BBB_SAV_ISQPXTIP,
	BBB_SAV_ISQPXTIP_ANS,
	BBB_SAV_IUQPCSP,
	BBB_SAV_IUQPCSP_ANS,
	BBB_SAV_IUQPSTSP,
	BBB_SAV_IUQPSTSP_ANS,
	BBB_SAV_IUQPVDTSP,
	BBB_SAV_IUQPVDTSP_ANS,
	BBB_SAV_IUQPVDROTSP,
	BBB_SAV_IUQPVDROTSP_ANS,
	BBB_SAV_IUQPATSP,
	BBB_SAV_IUQPATSP_ANS,
	BBB_SAV_ICTVS,
	BBB_SAV_ICTVS_ANS,
	BBB_SAV_IDTSP,
	BBB_SAV_IDTSP_ANS,
	BBB_SAV_ITVS,
	BBB_SAV_ITVS_ANS,
	BBB_SAV_IVTTSTP,
	BBB_SAV_IVTTSTP_ANS,
	BBB_SAV_IVLTATSTP,
	BBB_SAV_IVLTATSTP_ANS,
	BBB_SAV_ISQPMDOSPP,
	BBB_SAV_ISQPMDOSPP_ANS,
	BBB_SAV_DMICTSTMCMI,
	BBB_SAV_DMICTSTMCMI_ANS,

	BBB_TAV_ISVA,
	BBB_TAV_ISVA_ANS,
	BBB_TAV_ITSAP,
	BBB_TAV_ITSAP_ANS,
	BBB_TAV_DTSAVM,
	BBB_TAV_DTSAVM_ANS,
	BBB_TAV_DTSAOM,
	BBB_TAV_DTSAOM_ANS,

	BBB_VCI_ISPK,
	BBB_VCI_ISPK_ANS,

	BBB_VCI_ISPA,
	BBB_VCI_ISPA_ANS,
	BBB_VCI_ISPSUPP,
	BBB_VCI_ISPSUPP_ANS,
	BBB_VCI_ISPM,
	BBB_VCI_ISPM_ANS,
	BBB_VCI_IZHSP,
	BBB_VCI_IZHSP_ANS,

	BBB_XCV_SUB,
	BBB_XCV_SUB_ANS,
	BBB_XCV_SUB_ANS_2,
	BBB_XCV_RFC,
	BBB_XCV_RFC_ANS,
	BBB_XCV_RAC,
	BBB_XCV_RAC_ANS,
	BBB_XCV_CCCBB,
	BBB_XCV_CCCBB_ANS,
	BBB_XCV_CCCBB_SIG_ANS,
	BBB_XCV_CCCBB_TSP_ANS,
	BBB_XCV_CCCBB_REV_ANS,
	BBB_XCV_CMDCIPI,
	BBB_XCV_CMDCIPI_ANS,
	BBB_XCV_CMDCIQC,
	BBB_XCV_CMDCIQC_ANS,
	BBB_XCV_CMDCIQSCD,
	BBB_XCV_CMDCIQSCD_ANS,
	BBB_XCV_CMDCIITLP,
	BBB_XCV_CMDCIITLP_ANS,
	BBB_XCV_CMDCIITNP,
	BBB_XCV_CMDCIITNP_ANS,
	BBB_XCV_CMDCICQCC,
	BBB_XCV_CMDCICQCC_ANS,
	BBB_XCV_CMDCICQCLVA,
	BBB_XCV_CMDCICQCLVA_ANS,
	BBB_XCV_CMDCICQCLVHAC,
	BBB_XCV_CMDCICQCLVHAC_ANS,
	BBB_XCV_CMDCICQCERPA,
	BBB_XCV_CMDCICQCERPA_ANS,
	BBB_XCV_CMDCICSQCSSCD,
	BBB_XCV_CMDCICSQCSSCD_ANS,
	BBB_XCV_CMDCICQCPDSLA,
	BBB_XCV_CMDCICQCPDSLA_ANS,
	BBB_XCV_CMDCICQCTA,
	BBB_XCV_CMDCICQCTA_ANS,
	BBB_XCV_CMDCDCQCCLCEC,
	BBB_XCV_CMDCDCQCCLCEC_ANS,
	BBB_XCV_CMDCDCQCCLCEC_ANS_EU,
	BBB_XCV_CMDCSCSIA,
	BBB_XCV_CMDCSCSIA_ANS,
	BBB_XCV_CMDCICQCRA,
	BBB_XCV_CMDCICQCRA_ANS,
	BBB_XCV_CMDCICQCNA,
	BBB_XCV_CMDCICQCNA_ANS,
	BBB_XCV_CMDCICQCIA,
	BBB_XCV_CMDCICQCIA_ANS,
	BBB_XCV_DCCUCE,
	BBB_XCV_DCCUCE_ANS,
	BBB_XCV_DCCFCE,
	BBB_XCV_DCCFCE_ANS,
	BBB_XCV_DCSBSINC,
	BBB_XCV_DCSBSINC_ANS,
	BBB_XCV_ICAC,
	BBB_XCV_ICAC_ANS,
	BBB_XCV_ICPDV,
	BBB_XCV_ICPDV_ANS,
	BBB_XCV_ICPTV,
	BBB_XCV_ICPTV_ANS,
	BBB_XCV_ICNRAEV,
	BBB_XCV_ICNRAEV_ANS,
	BBB_XCV_IVTBCTSD,
	BBB_XCV_IVTBCTSD_ANS,
	BBB_XCV_ICTIVRSC,
	BBB_XCV_ICTIVRSC_ANS,
	BBB_XCV_ICTIVRCIRI,
	BBB_XCV_ICTIVRCIRI_ANS,
	BBB_XCV_IRDCSFC,
	BBB_XCV_IRDCSFC_ANS,
	BBB_XCV_IRDPFC,
	BBB_XCV_IRDPFC_ANS,
	BBB_XCV_IRDPFRC, 
	BBB_XCV_IRDPFRC_ANS,
	BBB_XCV_IARDPFC,
	BBB_XCV_IARDPFC_ANS,
	BBB_VTS_IRDPFC,
	BBB_VTS_IRDPFC_ANS,
	BBB_XCV_ISCOH,
	BBB_XCV_ISCOH_ANS,
	BBB_XCV_ISCUKN,
	BBB_XCV_ISCUKN_ANS,
	BBB_XCV_ISCR,
	BBB_XCV_ISCR_ANS,
	BBB_XCV_ISCGKU,
	BBB_XCV_ISCGKU_ANS,
	BBB_XCV_ISCGKU_ANS_CERT,
	BBB_XCV_ISCGEKU,
	BBB_XCV_ISCGEKU_ANS,
	BBB_XCV_ISCGEKU_ANS_CERT,
	BBB_XCV_ICSI,
	BBB_XCV_ICSI_ANS,
	BBB_XCV_IOTAA,
	BBB_XCV_IOTAA_ANS,
	BBB_XCV_HPCCVVT,
	BBB_XCV_HPCCVVT_ANS,
	BBB_XCV_PSEUDO_USE,
	BBB_XCV_PSEUDO_USE_ANS,
	BBB_XCV_AIA_PRES,
	BBB_XCV_AIA_PRES_ANS,
	BBB_XCV_REVOC_PRES,
	BBB_XCV_REVOC_PRES_ANS,
	BBB_XCV_REVOC_THIS_UPDATE_PRESENT,
	BBB_XCV_REVOC_THIS_UPDATE_PRESENT_ANS,
	BBB_XCV_REVOC_ISSUER_KNOWN,
	BBB_XCV_REVOC_ISSUER_KNOWN_ANS,
	BBB_XCV_REVOC_ISSUER_VALID_AT_PROD,
	BBB_XCV_REVOC_ISSUER_VALID_AT_PROD_ANS,
	BBB_XCV_REVOC_AFTER_CERT_NOT_BEFORE,
	BBB_XCV_REVOC_AFTER_CERT_NOT_BEFORE_ANS,
	BBB_XCV_REVOC_HAS_CERT_INFO,
	BBB_XCV_REVOC_HAS_CERT_INFO_ANS,
	BBB_XCV_REVOC_RESPID_MATCH,
	BBB_XCV_REVOC_RESPID_MATCH_ANS,
	BBB_XCV_REVOC_CERT_HASH_PRESENT,
	BBB_XCV_REVOC_CERT_HASH_PRESENT_ANS,
	BBB_XCV_REVOC_CERT_HASH_MATCH,
	BBB_XCV_REVOC_CERT_HASH_MATCH_ANS,
	BBB_XCV_REVOC_SELF_ISSUED_OCSP,
	BBB_XCV_REVOC_SELF_ISSUED_OCSP_ANS,

	BBB_XCV_DCIDNMSDNIC,
	BBB_XCV_DCIDNMSDNIC_ANS,
	BBB_XCV_ISCGCOUN,
	BBB_XCV_ISCGCOUN_ANS,
	BBB_XCV_ISCGLOC,
	BBB_XCV_ISCGLOC_ANS,
	BBB_XCV_ISCGST,
	BBB_XCV_ISCGST_ANS,
	BBB_XCV_ISCGORGAN,
	BBB_XCV_ISCGORGAN_ANS,
	BBB_XCV_ISCGORGAU,
	BBB_XCV_ISCGORGAU_ANS,
	BBB_XCV_ISCGORGAI,
	BBB_XCV_ISCGORGAI_ANS,
	BBB_XCV_ISCGSURN,
	BBB_XCV_ISCGSURN_ANS,
	BBB_XCV_ISCGGIVEN,
	BBB_XCV_ISCGGIVEN_ANS,
	BBB_XCV_ISCGPSEUDO,
	BBB_XCV_ISCGPSEUDO_ANS,
	BBB_XCV_ISCGCOMMONN,
	BBB_XCV_ISCGCOMMONN_ANS,
	BBB_XCV_ISCGTITLE,
	BBB_XCV_ISCGTITLE_ANS,
	BBB_XCV_ISCGEMAIL,
	BBB_XCV_ISCGEMAIL_ANS,

	BBB_XCV_ISSSC,
	BBB_XCV_ISSSC_ANS,
	
	BBB_XCV_ISNSSC,
	BBB_XCV_ISNSSC_ANS,
	
	BBB_XCV_IRDC,
	BBB_XCV_IRDC_ANS,

	XCV_TSL_ESP,
	XCV_TSL_ESP_ANS,
	XCV_TSL_ESP_SIG_ANS,
	XCV_TSL_ESP_TSP_ANS,
	XCV_TSL_ESP_REV_ANS,
	XCV_TSL_ETIP,
	XCV_TSL_ETIP_ANS,
	XCV_TSL_ETIP_SIG_ANS,
	XCV_TSL_ETIP_TSP_ANS,
	XCV_TSL_ETIP_REV_ANS,

	PCV_IVTSC,
	PCV_IVTSC_ANS,
	PCV_ICCSVTSF,
	PCV_ICCSVTSF_ANS,

	PSV_IPCVA,
	PSV_IPCVA_ANS,
	PSV_IPCVC,
	PSV_IPCVC_ANS,
	PSV_IPSVC,
	PSV_IPSVC_ANS,
	PSV_IPTVC,
	PSV_IPTVC_ANS,
	PSV_ITPOCOBCT,
	PSV_ITPOSVAOBCT,
	PSV_ITPOSVAOBCT_ANS,
	PSV_ITPORDAOBCT,
	PSV_ITPOOBCT_ANS,
	PSV_ITPRISCNARTCAC,
	PSV_ITPRISCNARTCAC_ANS,
	PSV_ICRDIT,
	PSV_ICRDIT_ANS,
	PSV_IPCRIAIDBEDC,
	PSV_IPCRIAIDBEDC_ANS,
	PSV_ICTD,
	PSV_ICTD_ANS,
	PSV_ISDDTA,
	PSV_ISDDTA_ANS,
	PSV_HRDBIBCT,
	PSV_HRDBIBCT_ANS,
	PSV_DIURDSCHPVR,
	PSV_DIURDSCHPVR_ANS,

	TSV_ASTPTCT,
	TSV_ASTPTCT_ANS,
	TSV_IBSTAIDOSC,
	TSV_IBSTAIDOSC_ANS,
	TSV_IBSTBCEC,
	TSV_IBSTBCEC_ANS,
	TSV_ISCNVABST,
	TSV_ISCNVABST_ANS,

	ADEST_IRTPTBST,
	ADEST_IRTPTBST_ANS,
	ADEST_ISTPTBST,
	ADEST_ISTPTBST_ANS,
	ADEST_VFDTAOCST_ANS,
	ADEST_ISTPTDABST,
	ADEST_ISTPTDABST_ANS,
	ADEST_IBSVPTC,
	ADEST_IBSVPTC_ANS,
	ADEST_IBSVPTADC,
	ADEST_IBSVPTADC_ANS,
	ADEST_IRERVPC,
	ADEST_IRERVPC_ANS,

	QUAL_TL_EXP,
	QUAL_TL_EXP_ANS,
	QUAL_TL_FRESH,
	QUAL_TL_FRESH_ANS,
	QUAL_TL_VERSION,
	QUAL_TL_VERSION_ANS,
	QUAL_TL_WS,
	QUAL_TL_WS_ANS,
	QUAL_TL_SV,
	QUAL_TL_SV_ANS,
	QUAL_TL_IMRA,
	QUAL_TL_IMRA_ANS,
	QUAL_TL_IMRA_ANS_V1,
	QUAL_TL_IMRA_ANS_V2,
	
	QUAL_TL_SERV_CONS,
	QUAL_TL_SERV_CONS_ANS0,
	QUAL_TL_SERV_CONS_ANS1,
	QUAL_TL_SERV_CONS_ANS2,
	QUAL_TL_SERV_CONS_ANS3,
	QUAL_TL_SERV_CONS_ANS3A,
	QUAL_TL_SERV_CONS_ANS3B,
	QUAL_TL_SERV_CONS_ANS3C,
	QUAL_TL_SERV_CONS_ANS4,
	QUAL_TL_SERV_CONS_ANS5,
	QUAL_TL_SERV_CONS_ANS6,
	QUAL_TL_SERV_CONS_ANS7,

	QUAL_CERT_TRUSTED_LIST_REACHED,
	QUAL_CERT_TRUSTED_LIST_REACHED_ANS,

	QUAL_TRUSTED_LIST_ACCEPT,
	QUAL_TRUSTED_LIST_ACCEPT_ANS,
	QUAL_LIST_OF_TRUSTED_LISTS_ACCEPT,
	QUAL_LIST_OF_TRUSTED_LISTS_ACCEPT_ANS,
	QUAL_VALID_TRUSTED_LIST_PRESENT,
	QUAL_VALID_TRUSTED_LIST_PRESENT_ANS,

	QUAL_CERT_TYPE_AT_ST,
	QUAL_CERT_TYPE_AT_ST_ANS,
	QUAL_CERT_TYPE_AT_CC,
	QUAL_CERT_TYPE_AT_CC_ANS,
	QUAL_CERT_TYPE_AT_VT,
	QUAL_CERT_TYPE_AT_VT_ANS,

	QUAL_QC_AT_ST,
	QUAL_QC_AT_ST_ANS,
	QUAL_QC_AT_CC,
	QUAL_QC_AT_CC_ANS,
	QUAL_QC_AT_VT,
	QUAL_QC_AT_VT_ANS,

	QUAL_QSCD_AT_ST,
	QUAL_QSCD_AT_ST_ANS,
	QUAL_QSCD_AT_CC,
	QUAL_QSCD_AT_CC_ANS,
	QUAL_QSCD_AT_VT,
	QUAL_QSCD_AT_VT_ANS,

	QUAL_UNIQUE_CERT,
	QUAL_UNIQUE_CERT_ANS,
	QUAL_IS_ADES,
	QUAL_IS_ADES_IND,
	QUAL_IS_ADES_INV,

	QUAL_HAS_METS,
	QUAL_HAS_METS_ANS,

	QUAL_HAS_METS_ATTIME,
	QUAL_HAS_METS_ATTIME_ANS,

	QUAL_HAS_METS_HCCECBA,
	QUAL_HAS_METS_HCCECBA_ANS,
	QUAL_HAS_METS_HCCECBA_ANS_2,
	QUAL_HAS_METS_HCCECBA_ANS_3,
	
	QUAL_HAS_CAQC,
	QUAL_HAS_CAQC_ANS,
	QUAL_HAS_CAQC_ANS_2,

	QUAL_HAS_ATTIME,
	QUAL_HAS_ATTIME_ANS,

	QUAL_HAS_TS_CERT_TYPE,
	QUAL_HAS_TS_CERT_TYPE_ANS,

	QUAL_HAS_CONF,
	QUAL_HAS_CONF_ANS,

	QUAL_HAS_QTST,
	QUAL_HAS_QTST_ANS,
	
	QUAL_IS_TRUST_CERT_MATCH_SERVICE,
	QUAL_IS_TRUST_CERT_MATCH_SERVICE_ANS0,
	QUAL_IS_TRUST_CERT_MATCH_SERVICE_ANS1,
	QUAL_IS_TRUST_CERT_MATCH_SERVICE_ANS2,

	QUAL_HAS_GRANTED,
	QUAL_HAS_GRANTED_ANS,
	QUAL_HAS_GRANTED_ANS_2,
	
	QUAL_HAS_GRANTED_AT,
	QUAL_HAS_GRANTED_AT_ANS,

	QUAL_HAS_CONSISTENT_BY_QC,
	QUAL_HAS_CONSISTENT_BY_QC_ANS,
	QUAL_HAS_CONSISTENT_BY_QSCD,
	QUAL_HAS_CONSISTENT_BY_QSCD_ANS,

	QUAL_HAS_CERT_TYPE_COVERAGE,
	QUAL_HAS_CERT_TYPE_COVERAGE_ANS,

	QUAL_HAS_VALID_CAQC,
	QUAL_HAS_VALID_CAQC_ANS,

	QUAL_HAS_ONLY_ONE,
	QUAL_HAS_ONLY_ONE_ANS,
	
	BBB_ACCEPT,
	BBB_ACCEPT_ANS,

	TST_TYPE_CONTENT_TST,
	TST_TYPE_SIGNATURE_TST,
	TST_TYPE_VD_TST,
	TST_TYPE_DOC_TST,
	TST_TYPE_CONTAINER_TST,
	TST_TYPE_ARCHIVE_TST,
	TST_TYPE_ER_TST,

	TST_TYPE_REF_ER_ATST,
	TST_TYPE_REF_ER_ATST_SEQ,
	
	EMPTY,

	/* Tokens */

	CA_CERTIFICATE,

	SIGNING_CERTIFICATE,

	REVOCATION,

	SIGNATURE,

	TIMESTAMP,
	
	/* Additional Info */

	ACCEPTABLE_REVOCATION,

	BASIC_SIGNATURE_VALIDATION_RESULT,

	BEST_SIGNATURE_TIME_CERT_NOT_AFTER,

	BEST_SIGNATURE_TIME_CERT_NOT_BEFORE,

	BEST_SIGNATURE_TIME_CERT_REVOCATION,

	BEST_SIGNATURE_TIME_CERT_SUSPENSION,
	
	CERTIFICATE_ID,

	CERTIFICATE_REVOCATION_FOUND,

	CERTIFICATE_REVOCATION_NOT_FOUND,

	CERTIFICATE_SUNSET_DATE,

	CERTIFICATE_SUNSET_DATE_TRUST_ANCHOR,

	CERTIFICATE_SUNSET_DATE_VALID,

	CERTIFICATE_TYPE,

	CERTIFICATE_VALIDITY,

	CONTROL_TIME,

	CONTROL_TIME_ALONE,

	CONTROL_TIME_WITH_POE,

	CONTROL_TIME_WITH_TRUST_ANCHOR,
	
	CRYPTOGRAPHIC_CHECK_FAILURE,

	CRYPTOGRAPHIC_CHECK_FAILURE_WITH_ID,

	CRYPTOGRAPHIC_CHECK_FAILURE_WITH_REF,
	
	CRYPTOGRAPHIC_CHECK_FAILURE_WITH_REF_WITH_NAME,

	CRYPTOGRAPHIC_CHECK_FAILURE_WITH_REF_WITH_NAMES,

	CRYPTOGRAPHIC_CHECK_SUCCESS,

	CRYPTOGRAPHIC_CHECK_SUCCESS_KEY_SIZE,

	CRYPTOGRAPHIC_CHECK_SUCCESS_DM,

	CRYPTOGRAPHIC_CHECK_SUCCESS_DM_WITH_ID,

	CRYPTOGRAPHIC_CHECK_SUCCESS_DM_WITH_NAME,

	CRYPTOGRAPHIC_CHECK_SUCCESS_DM_WITH_NAMES,

	EVIDENCE_RECORD_VALIDATION,

	EXTENDED_KEY_USAGE,

	KEY_USAGE,

	LAST_ACCEPTABLE_REVOCATION,
	
	REFERENCE,

	REFERENCE_NAME_CHECK,

	REFERENCES_WITH_NAMES,
	
	REVOCATION_ACCEPTANCE_CHECK,

	REVOCATION_CERT_HASH_OK,

	REVOCATION_CERT_HASH_OK_ID,

	REVOCATION_CERT_VALIDITY,

	REVOCATION_CHECK,
	
	REVOCATION_CONSISTENT,

	REVOCATION_CONSISTENT_CRL,

	REVOCATION_CONSISTENT_OCSP,

	REVOCATION_CONSISTENT_TL,

	REVOCATION_INFO,
	
	REVOCATION_NOT_AFTER_AFTER,

	REVOCATION_NOT_AFTER_AFTER_ID,

	REVOCATION_PRODUCED_AT_CERT_VALIDITY,

	REVOCATION_PRODUCED_AT_OUT_OF_BOUNDS,

	REVOCATION_PRODUCED_AT_OUT_OF_BOUNDS_ID,

	REVOCATION_REASON,

	REVOCATION_THIS_UPDATE_CONTROL_TIME,

	PSEUDO,

	SIGNATURE_ALGORITHM_WITH_KEY_SIZE,
	
	STRUCTURAL_VALIDATION_FAILURE,

	TIMESTAMP_AND_CERTIFICATE_NOT_AFTER,

	TIMESTAMP_AND_CRYPTO_CONSTRAINTS_EXPIRATION,

	TIMESTAMP_AND_REVOCATION_TIME,

	TIMESTAMP_VALIDATION,
	
	TOKEN_ID,

	TRUST_SERVICE_NAME,

	TRUSTED_SERVICE_STATUS,

	TRUSTED_SERVICE_TYPE,

	TRUSTED_LIST,

	VALIDATION_TIME,
	
	/* BasicBuildingBlocks titles */

	CRYPTOGRAPHIC_VERIFICATION,
	
	FORMAT_CHECKING,

	IDENTIFICATION_OF_THE_SIGNING_CERTIFICATE,

	PAST_SIGNATURE_VALIDATION,

	PAST_CERTIFICATE_VALIDATION,

	REVOCATION_FRESHNESS_CHECKER,

	SIGNATURE_ACCEPTANCE_VALIDATION,

	VALIDATION_CONTEXT_INITIALIZATION,

	VALIDATION_TIME_SLIDING,

	X509_CERTIFICATE_VALIDATION,
	
	RESULTS,

	/* Validation Process Definitions */

	CERT_QUALIFICATION,

	CERT_QUALIFICATION_AT_TIME,
	
	DAAV,
	
	CC,

	CRS,

	LOTL,

	PSV_CRS,
	
	RAC,

	SIG_QUALIFICATION,

	SUB_XCV,

	TL,

	TST_QUALIFICATION,

	TST_QUALIFICATION_AT_TIME,

	VPBS,

	VPER,

	VPFLTVD,

	VPFRVC,

	VPFSWATSP,

	VPFTSP,

	VPFTSPWATSP,

	VTS_CRS,
	
	/* Custom variables */

	/* Validation time */
	VT_BEST_SIGNATURE_TIME,
	VT_CERTIFICATE_ISSUANCE_TIME,
	VT_VALIDATION_TIME,
	VT_TST_GENERATION_TIME,
	VT_TST_POE_TIME,

	/* Semantics */
	SEMANTICS_TOTAL_PASSED,
	SEMANTICS_PASSED,
	SEMANTICS_TOTAL_FAILED,
	SEMANTICS_FAILED,
	SEMANTICS_INDETERMINATE,

	SEMANTICS_FORMAT_FAILURE,
	SEMANTICS_HASH_FAILURE,
	SEMANTICS_SIG_CRYPTO_FAILURE,
	SEMANTICS_REVOKED,
	SEMANTICS_EXPIRED,
	SEMANTICS_NOT_YET_VALID, 

	SEMANTICS_SIG_CONSTRAINTS_FAILURE, 
	SEMANTICS_CHAIN_CONSTRAINTS_FAILURE, 
	SEMANTICS_CERTIFICATE_CHAIN_GENERAL_FAILURE, 
	SEMANTICS_CRYPTO_CONSTRAINTS_FAILURE,
	SEMANTICS_POLICY_PROCESSING_ERROR,
	SEMANTICS_SIGNATURE_POLICY_NOT_AVAILABLE, 
	SEMANTICS_TIMESTAMP_ORDER_FAILURE, 
	SEMANTICS_NO_SIGNING_CERTIFICATE_FOUND, 
	SEMANTICS_NO_CERTIFICATE_CHAIN_FOUND, 
	SEMANTICS_REVOKED_NO_POE, 
	SEMANTICS_REVOKED_CA_NO_POE, 
	SEMANTICS_OUT_OF_BOUNDS_NOT_REVOKED, 
	SEMANTICS_OUT_OF_BOUNDS_NO_POE,
	SEMANTICS_REVOCATION_OUT_OF_BOUNDS_NO_POE,
	SEMANTICS_CRYPTO_CONSTRAINTS_FAILURE_NO_POE, 
	SEMANTICS_NO_POE,
	SEMANTICS_TRY_LATER, 
	SEMANTICS_SIGNED_DATA_NOT_FOUND;
	
	/**
	 * This method returns the id code of the referred message.
	 *
	 * @return {@code String} message.
	 */
	public String getId() {
		return name();
	}

	/**
	 * This method returns a semantics {@code MessageTag} to be used by ETSI Indication/SubIndication
	 *
	 * @param etsiCode {@link String} Indication/SubIndication
	 * @return {@link MessageTag}
	 */
	public static final MessageTag getSemantic(String etsiCode) {
		String expectedEnumValue = "SEMANTICS_" + etsiCode;
		for (MessageTag messageTag : values()) {
			if (expectedEnumValue.equals(messageTag.name())) {
				return messageTag;
			}
		}
		return null;
	}

}
