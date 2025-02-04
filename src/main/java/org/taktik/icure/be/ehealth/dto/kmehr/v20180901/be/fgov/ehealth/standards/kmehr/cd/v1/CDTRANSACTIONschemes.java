//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.14 at 03:49:33 PM CEST 
//


package org.taktik.icure.be.ehealth.dto.kmehr.v20180901.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CD-TRANSACTIONschemes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-TRANSACTIONschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CD-TRANSACTION"/>
 *     &lt;enumeration value="CD-TRANSACTION-CARENET"/>
 *     &lt;enumeration value="CD-TRANSACTION-MAA"/>
 *     &lt;enumeration value="CD-CHAPTER4APPENDIX"/>
 *     &lt;enumeration value="CD-TRANSACTION-REG"/>
 *     &lt;enumeration value="CD-TRANSACTION-MYCARENET"/>
 *     &lt;enumeration value="CD-TRANSACTION-TYPE"/>
 *     &lt;enumeration value="CD-HUBSERVICE"/>
 *     &lt;enumeration value="CD-DIARY"/>
 *     &lt;enumeration value="LOCAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-TRANSACTIONschemes")
@XmlEnum
public enum CDTRANSACTIONschemes {

    @XmlEnumValue("CD-TRANSACTION")
    CD_TRANSACTION("CD-TRANSACTION", "1.13"),
    @XmlEnumValue("CD-TRANSACTION-CARENET")
    CD_TRANSACTION_CARENET("CD-TRANSACTION-CARENET", "1.0"),
    @XmlEnumValue("CD-TRANSACTION-MAA")
    CD_TRANSACTION_MAA("CD-TRANSACTION-MAA", "*1.0"),
    @XmlEnumValue("CD-CHAPTER4APPENDIX")
    CD_CHAPTER_4_APPENDIX("CD-CHAPTER4APPENDIX", "1.0"),
    @XmlEnumValue("CD-TRANSACTION-REG")
    CD_TRANSACTION_REG("CD-TRANSACTION-REG", "1.3"),
    @XmlEnumValue("CD-TRANSACTION-MYCARENET")
    CD_TRANSACTION_MYCARENET("CD-TRANSACTION-MYCARENET", "1.5"),
    @XmlEnumValue("CD-TRANSACTION-TYPE")
    CD_TRANSACTION_TYPE("CD-TRANSACTION-TYPE", "1.3"),
    @XmlEnumValue("CD-HUBSERVICE")
    CD_HUBSERVICE("CD-HUBSERVICE", "1.0"),
    @XmlEnumValue("CD-DIARY")
    CD_DIARY("CD-DIARY", "1.0"),
    LOCAL("LOCAL", "1.0");
    private final String value; //
    private final String version;
    CDTRANSACTIONschemes(String v, String vs) {
        value = v;
        version = vs;
    }

    public String value() {
        return value;
    } //

    public String version() {
        return version;
    }

    public static CDTRANSACTIONschemes fromValue(String v) {
        for (CDTRANSACTIONschemes c: CDTRANSACTIONschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
