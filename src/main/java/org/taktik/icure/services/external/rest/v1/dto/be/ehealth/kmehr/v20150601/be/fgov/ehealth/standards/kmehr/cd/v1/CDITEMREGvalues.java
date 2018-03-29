/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.11.10 à 11:53:43 AM CET 
//


package org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20150601.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-ITEM-REGvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ITEM-REGvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="isinitialintervention"/>
 *     &lt;enumeration value="dominance"/>
 *     &lt;enumeration value="existingbridge"/>
 *     &lt;enumeration value="observedlesion"/>
 *     &lt;enumeration value="segment"/>
 *     &lt;enumeration value="totaltreatedlength"/>
 *     &lt;enumeration value="bloodvesseldiameter"/>
 *     &lt;enumeration value="procedure-device"/>
 *     &lt;enumeration value="contrastproductused"/>
 *     &lt;enumeration value="reimbursementnomenclaturetype"/>
 *     &lt;enumeration value="intervention-type"/>
 *     &lt;enumeration value="intervention-site"/>
 *     &lt;enumeration value="approach"/>
 *     &lt;enumeration value="navigationcomputer"/>
 *     &lt;enumeration value="intervention-device"/>
 *     &lt;enumeration value="bearingsurface"/>
 *     &lt;enumeration value="graftused"/>
 *     &lt;enumeration value="acetabularinterface"/>
 *     &lt;enumeration value="femoralinterface"/>
 *     &lt;enumeration value="stem"/>
 *     &lt;enumeration value="cup"/>
 *     &lt;enumeration value="head"/>
 *     &lt;enumeration value="neck"/>
 *     &lt;enumeration value="insert"/>
 *     &lt;enumeration value="prosthesis-other"/>
 *     &lt;enumeration value="brand"/>
 *     &lt;enumeration value="producer"/>
 *     &lt;enumeration value="size"/>
 *     &lt;enumeration value="revisiontechnic"/>
 *     &lt;enumeration value="kneeimplanttype"/>
 *     &lt;enumeration value="kneeinsert"/>
 *     &lt;enumeration value="tibialinterface"/>
 *     &lt;enumeration value="patellarinterface"/>
 *     &lt;enumeration value="patella"/>
 *     &lt;enumeration value="femur"/>
 *     &lt;enumeration value="tibia"/>
 *     &lt;enumeration value="tickness"/>
 *     &lt;enumeration value="kneeprosthesis"/>
 *     &lt;enumeration value="revisioncomponent"/>
 *     &lt;enumeration value="doctorsremark"/>
 *     &lt;enumeration value="resynchronisation"/>
 *     &lt;enumeration value="pacingindication"/>
 *     &lt;enumeration value="issecondoperator"/>
 *     &lt;enumeration value="postpcitimi"/>
 *     &lt;enumeration value="poststenosis"/>
 *     &lt;enumeration value="infiltration"/>
 *     &lt;enumeration value="operation-type"/>
 *     &lt;enumeration value="alignment"/>
 *     &lt;enumeration value="approach"/>
 *     &lt;enumeration value="custominstrumentation"/>
 *     &lt;enumeration value="substitute"/>
 *     &lt;enumeration value="modularneck"/>
 *     &lt;enumeration value="materialsremark"/>
 *     &lt;enumeration value="ortho-device"/>
 *     &lt;enumeration value="devicename"/>
 *     &lt;enumeration value="distributor"/>
 *     &lt;enumeration value="manufacturer"/>
 *     &lt;enumeration value="femoralreplacement"/>
 *     &lt;enumeration value="acetabularreplacement"/>
 *     &lt;enumeration value="revisedcomponent"/>
 *     &lt;enumeration value="cardioversion"/>
 *     &lt;enumeration value="motivation"/>
 *     &lt;enumeration value="criteriacomments"/>
 *     &lt;enumeration value="resynchronisation"/>
 *     &lt;enumeration value="comorbiditiescomments"/>
 *     &lt;enumeration value="informationprimo"/>
 *     &lt;enumeration value="lvef"/>
 *     &lt;enumeration value="qrs"/>
 *     &lt;enumeration value="diameter"/>
 *     &lt;enumeration value="cardiacasynchrony"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-ITEM-REGvalues")
@XmlEnum
public enum CDITEMREGvalues {

    @XmlEnumValue("isinitialintervention")
    ISINITIALINTERVENTION("isinitialintervention"),
    @XmlEnumValue("dominance")
    DOMINANCE("dominance"),
    @XmlEnumValue("existingbridge")
    EXISTINGBRIDGE("existingbridge"),
    @XmlEnumValue("observedlesion")
    OBSERVEDLESION("observedlesion"),
    @XmlEnumValue("segment")
    SEGMENT("segment"),
    @XmlEnumValue("totaltreatedlength")
    TOTALTREATEDLENGTH("totaltreatedlength"),
    @XmlEnumValue("bloodvesseldiameter")
    BLOODVESSELDIAMETER("bloodvesseldiameter"),
    @XmlEnumValue("procedure-device")
    PROCEDURE_DEVICE("procedure-device"),
    @XmlEnumValue("contrastproductused")
    CONTRASTPRODUCTUSED("contrastproductused"),
    @XmlEnumValue("reimbursementnomenclaturetype")
    REIMBURSEMENTNOMENCLATURETYPE("reimbursementnomenclaturetype"),
    @XmlEnumValue("intervention-type")
    INTERVENTION_TYPE("intervention-type"),
    @XmlEnumValue("intervention-site")
    INTERVENTION_SITE("intervention-site"),
    @XmlEnumValue("approach")
    APPROACH("approach"),
    @XmlEnumValue("navigationcomputer")
    NAVIGATIONCOMPUTER("navigationcomputer"),
    @XmlEnumValue("intervention-device")
    INTERVENTION_DEVICE("intervention-device"),
    @XmlEnumValue("bearingsurface")
    BEARINGSURFACE("bearingsurface"),
    @XmlEnumValue("graftused")
    GRAFTUSED("graftused"),
    @XmlEnumValue("acetabularinterface")
    ACETABULARINTERFACE("acetabularinterface"),
    @XmlEnumValue("femoralinterface")
    FEMORALINTERFACE("femoralinterface"),
    @XmlEnumValue("stem")
    STEM("stem"),
    @XmlEnumValue("cup")
    CUP("cup"),
    @XmlEnumValue("head")
    HEAD("head"),
    @XmlEnumValue("neck")
    NECK("neck"),
    @XmlEnumValue("insert")
    INSERT("insert"),
    @XmlEnumValue("prosthesis-other")
    PROSTHESIS_OTHER("prosthesis-other"),
    @XmlEnumValue("brand")
    BRAND("brand"),
    @XmlEnumValue("producer")
    PRODUCER("producer"),
    @XmlEnumValue("size")
    SIZE("size"),
    @XmlEnumValue("revisiontechnic")
    REVISIONTECHNIC("revisiontechnic"),
    @XmlEnumValue("kneeimplanttype")
    KNEEIMPLANTTYPE("kneeimplanttype"),
    @XmlEnumValue("kneeinsert")
    KNEEINSERT("kneeinsert"),
    @XmlEnumValue("tibialinterface")
    TIBIALINTERFACE("tibialinterface"),
    @XmlEnumValue("patellarinterface")
    PATELLARINTERFACE("patellarinterface"),
    @XmlEnumValue("patella")
    PATELLA("patella"),
    @XmlEnumValue("femur")
    FEMUR("femur"),
    @XmlEnumValue("tibia")
    TIBIA("tibia"),
    @XmlEnumValue("tickness")
    TICKNESS("tickness"),
    @XmlEnumValue("kneeprosthesis")
    KNEEPROSTHESIS("kneeprosthesis"),
    @XmlEnumValue("revisioncomponent")
    REVISIONCOMPONENT("revisioncomponent"),
    @XmlEnumValue("doctorsremark")
    DOCTORSREMARK("doctorsremark"),
    @XmlEnumValue("resynchronisation")
    RESYNCHRONISATION("resynchronisation"),
    @XmlEnumValue("pacingindication")
    PACINGINDICATION("pacingindication"),
    @XmlEnumValue("issecondoperator")
    ISSECONDOPERATOR("issecondoperator"),
    @XmlEnumValue("postpcitimi")
    POSTPCITIMI("postpcitimi"),
    @XmlEnumValue("poststenosis")
    POSTSTENOSIS("poststenosis"),
    @XmlEnumValue("infiltration")
    INFILTRATION("infiltration"),
    @XmlEnumValue("operation-type")
    OPERATION_TYPE("operation-type"),
    @XmlEnumValue("alignment")
    ALIGNMENT("alignment"),
    @XmlEnumValue("custominstrumentation")
    CUSTOMINSTRUMENTATION("custominstrumentation"),
    @XmlEnumValue("substitute")
    SUBSTITUTE("substitute"),
    @XmlEnumValue("modularneck")
    MODULARNECK("modularneck"),
    @XmlEnumValue("materialsremark")
    MATERIALSREMARK("materialsremark"),
    @XmlEnumValue("ortho-device")
    ORTHO_DEVICE("ortho-device"),
    @XmlEnumValue("devicename")
    DEVICENAME("devicename"),
    @XmlEnumValue("distributor")
    DISTRIBUTOR("distributor"),
    @XmlEnumValue("manufacturer")
    MANUFACTURER("manufacturer"),
    @XmlEnumValue("femoralreplacement")
    FEMORALREPLACEMENT("femoralreplacement"),
    @XmlEnumValue("acetabularreplacement")
    ACETABULARREPLACEMENT("acetabularreplacement"),
    @XmlEnumValue("revisedcomponent")
    REVISEDCOMPONENT("revisedcomponent"),
    @XmlEnumValue("cardioversion")
    CARDIOVERSION("cardioversion"),
    @XmlEnumValue("motivation")
    MOTIVATION("motivation"),
    @XmlEnumValue("criteriacomments")
    CRITERIACOMMENTS("criteriacomments"),
    @XmlEnumValue("comorbiditiescomments")
    COMORBIDITIESCOMMENTS("comorbiditiescomments"),
    @XmlEnumValue("informationprimo")
    INFORMATIONPRIMO("informationprimo"),
    @XmlEnumValue("lvef")
    LVEF("lvef"),
    @XmlEnumValue("qrs")
    QRS("qrs"),
    @XmlEnumValue("diameter")
    DIAMETER("diameter"),
    @XmlEnumValue("cardiacasynchrony")
    CARDIACASYNCHRONY("cardiacasynchrony");
    private final String value;

    CDITEMREGvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDITEMREGvalues fromValue(String v) {
        for (CDITEMREGvalues c: CDITEMREGvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
