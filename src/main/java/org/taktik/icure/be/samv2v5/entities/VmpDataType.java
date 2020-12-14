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
 * <p>Java class for VmpDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VmpDataType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:export}DataPeriodType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:be:fgov:ehealth:samws:v2:virtual:common}VmpFields"/>
 *         &lt;group ref="{urn:be:fgov:ehealth:samws:v2:export}VmpReferenceFields"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VmpDataType", propOrder = {
    "name",
    "abbreviation",
    "singleAdministrationDose",
    "commentedClassification",
    "vmpGroup",
    "vtm",
    "wada"
})
public class VmpDataType
    extends DataPeriodType
{

    @XmlElement(name = "Name", namespace = "urn:be:fgov:ehealth:samws:v2:virtual:common", required = true)
    protected Text255Type name;
    @XmlElement(name = "Abbreviation", namespace = "urn:be:fgov:ehealth:samws:v2:virtual:common", required = true)
    protected Text255Type abbreviation;
    @XmlElement(name = "SingleAdministrationDose", namespace = "urn:be:fgov:ehealth:samws:v2:virtual:common")
    protected QuantityType singleAdministrationDose;
    @XmlElement(name = "CommentedClassification")
    protected List<CommentedClassificationFullDataType> commentedClassification;
    @XmlElement(name = "VmpGroup", required = true)
    protected VmpGroupFullDataType vmpGroup;
    @XmlElement(name = "Vtm")
    protected VtmFullDataType vtm;
    @XmlElement(name = "Wada")
    protected List<WadaType> wada;

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
     * Gets the value of the abbreviation property.
     * 
     * @return
     *     possible object is
     *     {@link Text255Type }
     *     
     */
    public Text255Type getAbbreviation() {
        return abbreviation;
    }

    /**
     * Sets the value of the abbreviation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Text255Type }
     *     
     */
    public void setAbbreviation(Text255Type value) {
        this.abbreviation = value;
    }

    /**
     * Gets the value of the singleAdministrationDose property.
     * 
     * @return
     *     possible object is
     *     {@link QuantityType }
     *     
     */
    public QuantityType getSingleAdministrationDose() {
        return singleAdministrationDose;
    }

    /**
     * Sets the value of the singleAdministrationDose property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityType }
     *     
     */
    public void setSingleAdministrationDose(QuantityType value) {
        this.singleAdministrationDose = value;
    }

    /**
     * Gets the value of the commentedClassification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commentedClassification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommentedClassification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommentedClassificationFullDataType }
     * 
     * 
     */
    public List<CommentedClassificationFullDataType> getCommentedClassification() {
        if (commentedClassification == null) {
            commentedClassification = new ArrayList<CommentedClassificationFullDataType>();
        }
        return this.commentedClassification;
    }

    /**
     * Gets the value of the vmpGroup property.
     * 
     * @return
     *     possible object is
     *     {@link VmpGroupFullDataType }
     *     
     */
    public VmpGroupFullDataType getVmpGroup() {
        return vmpGroup;
    }

    /**
     * Sets the value of the vmpGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link VmpGroupFullDataType }
     *     
     */
    public void setVmpGroup(VmpGroupFullDataType value) {
        this.vmpGroup = value;
    }

    /**
     * Gets the value of the vtm property.
     * 
     * @return
     *     possible object is
     *     {@link VtmFullDataType }
     *     
     */
    public VtmFullDataType getVtm() {
        return vtm;
    }

    /**
     * Sets the value of the vtm property.
     * 
     * @param value
     *     allowed object is
     *     {@link VtmFullDataType }
     *     
     */
    public void setVtm(VtmFullDataType value) {
        this.vtm = value;
    }

    /**
     * Gets the value of the wada property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wada property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWada().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WadaType }
     * 
     * 
     */
    public List<WadaType> getWada() {
        if (wada == null) {
            wada = new ArrayList<WadaType>();
        }
        return this.wada;
    }

}
