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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.taktik.icure.be.ehealth.samws.v2.core.ChangeNoChangeActionType;
import org.taktik.icure.be.ehealth.samws.v2.core.Text255Type;
import org.taktik.icure.be.ehealth.samws.v2.core.VmpComponentKeyType;


/**
 * <p>Java class for ChangeVmpComponentType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ChangeVmpComponentType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:core}VmpComponentKeyType">
 *       &lt;sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;group ref="{urn:be:fgov:ehealth:samws:v2:virtual:common}VmpComponentFields"/>
 *           &lt;group ref="{urn:be:fgov:ehealth:samws:v2:virtual:common}VmpComponentReferences"/>
 *         &lt;/sequence>
 *         &lt;element name="VirtualIngredient" type="{urn:be:fgov:ehealth:samws:v2:virtual:common}ChangeVirtualIngredientType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{urn:be:fgov:ehealth:samws:v2:core}changeNoChangeMetadata"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChangeVmpComponentType", propOrder = {
    "phaseNumber",
    "name",
    "virtualFormCode",
    "routeOfAdministrationCodes",
    "virtualIngredients"
})
public class ChangeVmpComponentType
    extends VmpComponentKeyType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(name = "PhaseNumber")
    protected Short phaseNumber;
    @XmlElement(name = "Name")
    protected Text255Type name;
    @XmlElement(name = "VirtualFormCode")
    protected String virtualFormCode;
    @XmlElement(name = "RouteOfAdministrationCode")
    protected List<String> routeOfAdministrationCodes;
    @XmlElement(name = "VirtualIngredient")
    protected List<ChangeVirtualIngredientType> virtualIngredients;
    @XmlAttribute(name = "action", required = true)
    protected ChangeNoChangeActionType action;
    @XmlAttribute(name = "from")
    protected XMLGregorianCalendar from;
    @XmlAttribute(name = "to")
    protected XMLGregorianCalendar to;

    /**
     * Gets the value of the phaseNumber property.
     *
     * @return
     *     possible object is
     *     {@link Short }
     *
     */
    public Short getPhaseNumber() {
        return phaseNumber;
    }

    /**
     * Sets the value of the phaseNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Short }
     *
     */
    public void setPhaseNumber(Short value) {
        this.phaseNumber = value;
    }

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
     * Gets the value of the virtualFormCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVirtualFormCode() {
        return virtualFormCode;
    }

    /**
     * Sets the value of the virtualFormCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVirtualFormCode(String value) {
        this.virtualFormCode = value;
    }

    /**
     * Gets the value of the routeOfAdministrationCodes property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the routeOfAdministrationCodes property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRouteOfAdministrationCodes().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     *
     *
     */
    public List<String> getRouteOfAdministrationCodes() {
        if (routeOfAdministrationCodes == null) {
            routeOfAdministrationCodes = new ArrayList<String>();
        }
        return this.routeOfAdministrationCodes;
    }

    /**
     * Gets the value of the virtualIngredients property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the virtualIngredients property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVirtualIngredients().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChangeVirtualIngredientType }
     *
     *
     */
    public List<ChangeVirtualIngredientType> getVirtualIngredients() {
        if (virtualIngredients == null) {
            virtualIngredients = new ArrayList<ChangeVirtualIngredientType>();
        }
        return this.virtualIngredients;
    }

    /**
     * Gets the value of the action property.
     *
     * @return
     *     possible object is
     *     {@link ChangeNoChangeActionType }
     *
     */
    public ChangeNoChangeActionType getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     *
     * @param value
     *     allowed object is
     *     {@link ChangeNoChangeActionType }
     *
     */
    public void setAction(ChangeNoChangeActionType value) {
        this.action = value;
    }

    /**
     * Gets the value of the from property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setFrom(XMLGregorianCalendar value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setTo(XMLGregorianCalendar value) {
        this.to = value;
    }

}
