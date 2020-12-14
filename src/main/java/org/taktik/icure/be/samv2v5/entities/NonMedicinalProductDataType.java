//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.15 at 03:32:18 PM CEST 
//


package org.taktik.icure.be.samv2v5.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NonMedicinalProductDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NonMedicinalProductDataType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:export}DataPeriodType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:be:fgov:ehealth:samws:v2:nonmedicinal:common}NonMedicinalProductFields"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NonMedicinalProductDataType", propOrder = {
    "name",
    "category",
    "commercialStatus",
    "producer",
    "distributor"
})
public class NonMedicinalProductDataType
    extends DataPeriodType
{

    @XmlElement(name = "Name", namespace = "urn:be:fgov:ehealth:samws:v2:nonmedicinal:common", required = true)
    protected Text255Type name;
    @XmlElement(name = "Category", namespace = "urn:be:fgov:ehealth:samws:v2:nonmedicinal:common", required = true)
    protected String category;
    @XmlElement(name = "CommercialStatus", namespace = "urn:be:fgov:ehealth:samws:v2:nonmedicinal:common", required = true)
    protected String commercialStatus;
    @XmlElement(name = "Producer", namespace = "urn:be:fgov:ehealth:samws:v2:nonmedicinal:common", required = true)
    protected TextType producer;
    @XmlElement(name = "Distributor", namespace = "urn:be:fgov:ehealth:samws:v2:nonmedicinal:common")
    protected TextType distributor;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link Text255Type }
     *     
     */
    public Text255Type getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link Text255Type }
     *     
     */
    public void setName(Text255Type value) {
        this.name = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the commercialStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommercialStatus() {
        return commercialStatus;
    }

    /**
     * Sets the value of the commercialStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommercialStatus(String value) {
        this.commercialStatus = value;
    }

    /**
     * Gets the value of the producer property.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getProducer() {
        return producer;
    }

    /**
     * Sets the value of the producer property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setProducer(TextType value) {
        this.producer = value;
    }

    /**
     * Gets the value of the distributor property.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getDistributor() {
        return distributor;
    }

    /**
     * Sets the value of the distributor property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setDistributor(TextType value) {
        this.distributor = value;
    }

}
