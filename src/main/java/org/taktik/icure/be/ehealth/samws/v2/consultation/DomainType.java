//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.05.22 at 08:11:32 PM CEST
//


package org.taktik.icure.be.ehealth.samws.v2.consultation;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.taktik.icure.be.ehealth.samws.v2.core.QuantityType;
import org.taktik.icure.be.ehealth.samws.v2.core.RangeType;
import org.taktik.icure.be.ehealth.samws.v2.core.StrengthRangeType;
import org.taktik.icure.be.ehealth.samws.v2.core.StrengthType;


/**
 * <p>Java class for DomainType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DomainType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="Quantity" type="{urn:be:fgov:ehealth:samws:v2:core}QuantityType"/>
 *         &lt;element name="QuantityRange" type="{urn:be:fgov:ehealth:samws:v2:core}RangeType"/>
 *         &lt;element name="Strength" type="{urn:be:fgov:ehealth:samws:v2:core}StrengthType"/>
 *         &lt;element name="StrengthRange" type="{urn:be:fgov:ehealth:samws:v2:core}StrengthRangeType"/>
 *         &lt;element name="Code" type="{urn:be:fgov:ehealth:samws:v2:core}String20Type"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DomainType", propOrder = {
    "code",
    "strengthRange",
    "strength",
    "quantityRange",
    "quantity"
})
public class DomainType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(name = "Code")
    protected String code;
    @XmlElement(name = "StrengthRange")
    protected StrengthRangeType strengthRange;
    @XmlElement(name = "Strength")
    protected StrengthType strength;
    @XmlElement(name = "QuantityRange")
    protected RangeType quantityRange;
    @XmlElement(name = "Quantity")
    protected QuantityType quantity;

    /**
     * Gets the value of the code property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the strengthRange property.
     *
     * @return
     *     possible object is
     *     {@link StrengthRangeType }
     *
     */
    public StrengthRangeType getStrengthRange() {
        return strengthRange;
    }

    /**
     * Sets the value of the strengthRange property.
     *
     * @param value
     *     allowed object is
     *     {@link StrengthRangeType }
     *
     */
    public void setStrengthRange(StrengthRangeType value) {
        this.strengthRange = value;
    }

    /**
     * Gets the value of the strength property.
     *
     * @return
     *     possible object is
     *     {@link StrengthType }
     *
     */
    public StrengthType getStrength() {
        return strength;
    }

    /**
     * Sets the value of the strength property.
     *
     * @param value
     *     allowed object is
     *     {@link StrengthType }
     *
     */
    public void setStrength(StrengthType value) {
        this.strength = value;
    }

    /**
     * Gets the value of the quantityRange property.
     *
     * @return
     *     possible object is
     *     {@link RangeType }
     *
     */
    public RangeType getQuantityRange() {
        return quantityRange;
    }

    /**
     * Sets the value of the quantityRange property.
     *
     * @param value
     *     allowed object is
     *     {@link RangeType }
     *
     */
    public void setQuantityRange(RangeType value) {
        this.quantityRange = value;
    }

    /**
     * Gets the value of the quantity property.
     *
     * @return
     *     possible object is
     *     {@link QuantityType }
     *
     */
    public QuantityType getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     *
     * @param value
     *     allowed object is
     *     {@link QuantityType }
     *
     */
    public void setQuantity(QuantityType value) {
        this.quantity = value;
    }

}
