//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.05.22 at 08:11:32 PM CEST
//


package org.taktik.icure.be.ehealth.samws.v2.company.submit;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChangeCompanyRequestType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ChangeCompanyRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Company" type="{urn:be:fgov:ehealth:samws:v2:company:submit}ChangeCompanyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChangeCompanyRequestType", propOrder = {
    "company"
})
@XmlRootElement(name = "ChangeCompanyRequest")
public class ChangeCompanyRequest
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(name = "Company", required = true)
    protected ChangeCompanyType company;

    /**
     * Gets the value of the company property.
     *
     * @return
     *     possible object is
     *     {@link ChangeCompanyType }
     *
     */
    public ChangeCompanyType getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     *
     * @param value
     *     allowed object is
     *     {@link ChangeCompanyType }
     *
     */
    public void setCompany(ChangeCompanyType value) {
        this.company = value;
    }

}
