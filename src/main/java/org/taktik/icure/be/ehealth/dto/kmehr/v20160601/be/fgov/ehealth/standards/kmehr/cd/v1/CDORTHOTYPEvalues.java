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
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.09 at 09:24:47 AM CET 
//


package org.taktik.icure.be.ehealth.dto.kmehr.v20160601.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CD-ORTHO-TYPEvalues.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ORTHO-TYPEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="primaryprocedure"/>
 *     &lt;enumeration value="revisionwithprosthesis"/>
 *     &lt;enumeration value="osteosynthesis"/>
 *     &lt;enumeration value="resection"/>
 *     &lt;enumeration value="arthrodesis"/>
 *     &lt;enumeration value="amputation"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-ORTHO-TYPEvalues")
@XmlEnum
public enum CDORTHOTYPEvalues {

    @XmlEnumValue("primaryprocedure")
    PRIMARYPROCEDURE("primaryprocedure"),
    @XmlEnumValue("revisionwithprosthesis")
    REVISIONWITHPROSTHESIS("revisionwithprosthesis"),
    @XmlEnumValue("osteosynthesis")
    OSTEOSYNTHESIS("osteosynthesis"),
    @XmlEnumValue("resection")
    RESECTION("resection"),
    @XmlEnumValue("arthrodesis")
    ARTHRODESIS("arthrodesis"),
    @XmlEnumValue("amputation")
    AMPUTATION("amputation");
    private final String value;

    CDORTHOTYPEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDORTHOTYPEvalues fromValue(String v) {
        for (CDORTHOTYPEvalues c: CDORTHOTYPEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
