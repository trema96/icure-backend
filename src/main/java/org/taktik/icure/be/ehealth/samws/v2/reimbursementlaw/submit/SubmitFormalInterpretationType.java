//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.05.22 at 08:11:32 PM CEST
//


package org.taktik.icure.be.ehealth.samws.v2.reimbursementlaw.submit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.taktik.icure.be.ehealth.samws.v2.core.AllActionsType;


/**
 * <p>Java class for SubmitFormalInterpretationType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SubmitFormalInterpretationType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:reimbursementlaw:submit}FormalInterpretationType">
 *       &lt;sequence>
 *         &lt;element name="ReimbursementCondition" type="{urn:be:fgov:ehealth:samws:v2:reimbursementlaw:submit}SubmitReimbursementConditionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ReimbursementTerm" type="{urn:be:fgov:ehealth:samws:v2:reimbursementlaw:submit}SubmitReimbursementTermType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{urn:be:fgov:ehealth:samws:v2:core}allActionsMetadata"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubmitFormalInterpretationType", propOrder = {
    "reimbursementConditions",
    "reimbursementTerms"
})
public class SubmitFormalInterpretationType
    extends FormalInterpretationType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(name = "ReimbursementCondition")
    protected List<SubmitReimbursementConditionType> reimbursementConditions;
    @XmlElement(name = "ReimbursementTerm")
    protected List<SubmitReimbursementTermType> reimbursementTerms;
    @XmlAttribute(name = "action", required = true)
    protected AllActionsType action;
    @XmlAttribute(name = "from")
    protected XMLGregorianCalendar from;
    @XmlAttribute(name = "to")
    protected XMLGregorianCalendar to;

    /**
     * Gets the value of the reimbursementConditions property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reimbursementConditions property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReimbursementConditions().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubmitReimbursementConditionType }
     *
     *
     */
    public List<SubmitReimbursementConditionType> getReimbursementConditions() {
        if (reimbursementConditions == null) {
            reimbursementConditions = new ArrayList<SubmitReimbursementConditionType>();
        }
        return this.reimbursementConditions;
    }

    /**
     * Gets the value of the reimbursementTerms property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reimbursementTerms property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReimbursementTerms().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubmitReimbursementTermType }
     *
     *
     */
    public List<SubmitReimbursementTermType> getReimbursementTerms() {
        if (reimbursementTerms == null) {
            reimbursementTerms = new ArrayList<SubmitReimbursementTermType>();
        }
        return this.reimbursementTerms;
    }

    /**
     * Gets the value of the action property.
     *
     * @return
     *     possible object is
     *     {@link AllActionsType }
     *
     */
    public AllActionsType getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     *
     * @param value
     *     allowed object is
     *     {@link AllActionsType }
     *
     */
    public void setAction(AllActionsType value) {
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
