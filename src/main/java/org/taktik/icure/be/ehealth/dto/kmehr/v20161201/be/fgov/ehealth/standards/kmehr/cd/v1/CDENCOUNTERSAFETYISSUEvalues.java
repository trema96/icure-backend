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
// Generated on: 2017.03.09 at 09:24:52 AM CET 
//


package org.taktik.icure.be.ehealth.dto.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CD-ENCOUNTERSAFETYISSUEvalues.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ENCOUNTERSAFETYISSUEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="verbal"/>
 *     &lt;enumeration value="fysical"/>
 *     &lt;enumeration value="material"/>
 *     &lt;enumeration value="notificationtopolice"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-ENCOUNTERSAFETYISSUEvalues")
@XmlEnum
public enum CDENCOUNTERSAFETYISSUEvalues {

    @XmlEnumValue("verbal")
    VERBAL("verbal"),
    @XmlEnumValue("fysical")
    FYSICAL("fysical"),
    @XmlEnumValue("material")
    MATERIAL("material"),
    @XmlEnumValue("notificationtopolice")
    NOTIFICATIONTOPOLICE("notificationtopolice");
    private final String value;

    CDENCOUNTERSAFETYISSUEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDENCOUNTERSAFETYISSUEvalues fromValue(String v) {
        for (CDENCOUNTERSAFETYISSUEvalues c: CDENCOUNTERSAFETYISSUEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
