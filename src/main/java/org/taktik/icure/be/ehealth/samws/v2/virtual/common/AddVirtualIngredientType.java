//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.05.22 at 08:11:32 PM CEST
//


package org.taktik.icure.be.ehealth.samws.v2.virtual.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddVirtualIngredientType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AddVirtualIngredientType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:virtual:common}VirtualIngredientType">
 *       &lt;sequence>
 *         &lt;element name="RealVirtualIngredient" type="{urn:be:fgov:ehealth:samws:v2:virtual:common}AddRealVirtualIngredientType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddVirtualIngredientType", propOrder = {
    "realVirtualIngredients"
})
public class AddVirtualIngredientType
    extends VirtualIngredientType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(name = "RealVirtualIngredient")
    protected List<AddRealVirtualIngredientType> realVirtualIngredients;

    /**
     * Gets the value of the realVirtualIngredients property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the realVirtualIngredients property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRealVirtualIngredients().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddRealVirtualIngredientType }
     *
     *
     */
    public List<AddRealVirtualIngredientType> getRealVirtualIngredients() {
        if (realVirtualIngredients == null) {
            realVirtualIngredients = new ArrayList<AddRealVirtualIngredientType>();
        }
        return this.realVirtualIngredients;
    }

}
