//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.15 at 03:32:18 PM CEST 
//


package org.taktik.icure.be.samv2v5.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddAmppFamhpToExistingAmpType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddAmppFamhpToExistingAmpType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:actual:common}AmpKeyType">
 *       &lt;sequence>
 *         &lt;element name="Ampp" type="{urn:be:fgov:ehealth:samws:v2:actual:common}AddAmppFamhpType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddAmppFamhpToExistingAmpType", namespace = "urn:be:fgov:ehealth:samws:v2:actual:common", propOrder = {
    "ampp"
})
public class AddAmppFamhpToExistingAmpType
    extends AmpKeyType
{

    @XmlElement(name = "Ampp", required = true)
    protected List<AddAmppFamhpType> ampp;

    /**
     * Gets the value of the ampp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ampp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmpp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddAmppFamhpType }
     * 
     * 
     */
    public List<AddAmppFamhpType> getAmpp() {
        if (ampp == null) {
            ampp = new ArrayList<AddAmppFamhpType>();
        }
        return this.ampp;
    }

}
