//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.14 at 03:48:33 PM CEST 
//


package org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20141001.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20141001.be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESS;
import org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20141001.be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20141001.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;


/**
 * <p>Java class for addressTypeBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addressTypeBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.ehealth.fgov.be/standards/kmehr/id/v1}ID-KMEHR" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-ADDRESS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="text" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;sequence>
 *             &lt;element name="country" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}countryType" minOccurs="0"/>
 *             &lt;element name="zip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *             &lt;element name="nis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *             &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *             &lt;element name="district" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *             &lt;element name="street" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *             &lt;element name="housenumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *             &lt;element name="postboxnumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *             &lt;element name="text" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addressTypeBase", propOrder = {
    "ids",
    "cds",
    "country",
    "zip",
    "nis",
    "city",
    "district",
    "street",
    "housenumber",
    "postboxnumber",
    "texts"
})
@XmlSeeAlso({
    AddressType.class
})
public class AddressTypeBase
    implements Serializable
{

    private final static long serialVersionUID = 20141001L;
    @XmlElement(name = "id")
    protected List<IDKMEHR> ids;
    @XmlElement(name = "cd")
    protected List<CDADDRESS> cds;
    protected CountryType country;
    protected String zip;
    protected String nis;
    protected String city;
    protected String district;
    protected String street;
    protected String housenumber;
    protected String postboxnumber;
    @XmlElement(name = "text")
    protected List<TextType> texts;

    /**
     * Gets the value of the ids property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ids property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IDKMEHR }
     * 
     * 
     */
    public List<IDKMEHR> getIds() {
        if (ids == null) {
            ids = new ArrayList<IDKMEHR>();
        }
        return this.ids;
    }

    /**
     * Gets the value of the cds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CDADDRESS }
     * 
     * 
     */
    public List<CDADDRESS> getCds() {
        if (cds == null) {
            cds = new ArrayList<CDADDRESS>();
        }
        return this.cds;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link CountryType }
     *     
     */
    public CountryType getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryType }
     *     
     */
    public void setCountry(CountryType value) {
        this.country = value;
    }

    /**
     * Gets the value of the zip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the value of the zip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZip(String value) {
        this.zip = value;
    }

    /**
     * Gets the value of the nis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNis() {
        return nis;
    }

    /**
     * Sets the value of the nis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNis(String value) {
        this.nis = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the district property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets the value of the district property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrict(String value) {
        this.district = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the housenumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHousenumber() {
        return housenumber;
    }

    /**
     * Sets the value of the housenumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHousenumber(String value) {
        this.housenumber = value;
    }

    /**
     * Gets the value of the postboxnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostboxnumber() {
        return postboxnumber;
    }

    /**
     * Sets the value of the postboxnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostboxnumber(String value) {
        this.postboxnumber = value;
    }

    /**
     * Gets the value of the texts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the texts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTexts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     * 
     * 
     */
    public List<TextType> getTexts() {
        if (texts == null) {
            texts = new ArrayList<TextType>();
        }
        return this.texts;
    }

}
