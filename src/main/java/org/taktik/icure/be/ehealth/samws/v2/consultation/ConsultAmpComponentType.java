//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.05.22 at 08:11:32 PM CEST
//


package org.taktik.icure.be.ehealth.samws.v2.consultation;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.taktik.icure.be.ehealth.samws.v2.actual.common.ContainsAlcoholType;
import org.taktik.icure.be.ehealth.samws.v2.actual.common.CrushableType;


/**
 * <p>Java class for ConsultAmpComponentType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ConsultAmpComponentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Dividable" type="{urn:be:fgov:ehealth:samws:v2:actual:common}DividableType" minOccurs="0"/>
 *         &lt;element name="Scored" type="{urn:be:fgov:ehealth:samws:v2:actual:common}ScoredType" minOccurs="0"/>
 *         &lt;element name="Crushable" type="{urn:be:fgov:ehealth:samws:v2:actual:common}CrushableType" minOccurs="0"/>
 *         &lt;element name="ContainsAlcohol" type="{urn:be:fgov:ehealth:samws:v2:actual:common}ContainsAlcoholType" minOccurs="0"/>
 *         &lt;element name="SugarFree" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ModifiedReleaseType" type="{urn:be:fgov:ehealth:samws:v2:actual:common}ModifiedReleaseTypeType" minOccurs="0"/>
 *         &lt;element name="SpecificDrugDevice" type="{urn:be:fgov:ehealth:samws:v2:actual:common}SpecificDrugDeviceType" minOccurs="0"/>
 *         &lt;element name="Dimensions" type="{urn:be:fgov:ehealth:samws:v2:core}String50Type" minOccurs="0"/>
 *         &lt;element name="Name" type="{urn:be:fgov:ehealth:samws:v2:consultation}ConsultTextType" minOccurs="0"/>
 *         &lt;element name="Note" type="{urn:be:fgov:ehealth:samws:v2:consultation}ConsultTextType" minOccurs="0"/>
 *         &lt;element name="PharmaceuticalForm" type="{urn:be:fgov:ehealth:samws:v2:consultation}PharmaceuticalFormWithStandardsType" maxOccurs="unbounded"/>
 *         &lt;element name="RouteOfAdministration" type="{urn:be:fgov:ehealth:samws:v2:consultation}RouteOfAdministrationWithStandardsType" maxOccurs="unbounded"/>
 *         &lt;element name="RealActualIngredient" type="{urn:be:fgov:ehealth:samws:v2:consultation}ConsultRealActualIngredientType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{urn:be:fgov:ehealth:samws:v2:consultation}validityPeriod"/>
 *       &lt;attribute name="sequenceNr" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="vmpComponentCode" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultAmpComponentType", propOrder = {
    "dividable",
    "scored",
    "crushable",
    "containsAlcohol",
    "sugarFree",
    "modifiedReleaseType",
    "specificDrugDevice",
    "dimensions",
    "name",
    "note",
    "pharmaceuticalForms",
    "routeOfAdministrations",
    "realActualIngredients"
})
public class ConsultAmpComponentType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(name = "Dividable")
    protected String dividable;
    @XmlElement(name = "Scored")
    protected String scored;
    @XmlElement(name = "Crushable")
    @XmlSchemaType(name = "string")
    protected CrushableType crushable;
    @XmlElement(name = "ContainsAlcohol")
    @XmlSchemaType(name = "string")
    protected ContainsAlcoholType containsAlcohol;
    @XmlElement(name = "SugarFree")
    protected Boolean sugarFree;
    @XmlElement(name = "ModifiedReleaseType")
    @XmlSchemaType(name = "integer")
    protected Integer modifiedReleaseType;
    @XmlElement(name = "SpecificDrugDevice")
    @XmlSchemaType(name = "integer")
    protected Integer specificDrugDevice;
    @XmlElement(name = "Dimensions")
    protected String dimensions;
    @XmlElement(name = "Name")
    protected ConsultTextType name;
    @XmlElement(name = "Note")
    protected ConsultTextType note;
    @XmlElement(name = "PharmaceuticalForm", required = true)
    protected List<PharmaceuticalFormWithStandardsType> pharmaceuticalForms;
    @XmlElement(name = "RouteOfAdministration", required = true)
    protected List<RouteOfAdministrationWithStandardsType> routeOfAdministrations;
    @XmlElement(name = "RealActualIngredient", required = true)
    protected List<ConsultRealActualIngredientType> realActualIngredients;
    @XmlAttribute(name = "sequenceNr", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger sequenceNr;
    @XmlAttribute(name = "vmpComponentCode")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger vmpComponentCode;
    @XmlAttribute(name = "StartDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlAttribute(name = "EndDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;

    /**
     * Gets the value of the dividable property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDividable() {
        return dividable;
    }

    /**
     * Sets the value of the dividable property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDividable(String value) {
        this.dividable = value;
    }

    /**
     * Gets the value of the scored property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getScored() {
        return scored;
    }

    /**
     * Sets the value of the scored property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setScored(String value) {
        this.scored = value;
    }

    /**
     * Gets the value of the crushable property.
     *
     * @return
     *     possible object is
     *     {@link CrushableType }
     *
     */
    public CrushableType getCrushable() {
        return crushable;
    }

    /**
     * Sets the value of the crushable property.
     *
     * @param value
     *     allowed object is
     *     {@link CrushableType }
     *
     */
    public void setCrushable(CrushableType value) {
        this.crushable = value;
    }

    /**
     * Gets the value of the containsAlcohol property.
     *
     * @return
     *     possible object is
     *     {@link ContainsAlcoholType }
     *
     */
    public ContainsAlcoholType getContainsAlcohol() {
        return containsAlcohol;
    }

    /**
     * Sets the value of the containsAlcohol property.
     *
     * @param value
     *     allowed object is
     *     {@link ContainsAlcoholType }
     *
     */
    public void setContainsAlcohol(ContainsAlcoholType value) {
        this.containsAlcohol = value;
    }

    /**
     * Gets the value of the sugarFree property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isSugarFree() {
        return sugarFree;
    }

    /**
     * Sets the value of the sugarFree property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setSugarFree(Boolean value) {
        this.sugarFree = value;
    }

    /**
     * Gets the value of the modifiedReleaseType property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getModifiedReleaseType() {
        return modifiedReleaseType;
    }

    /**
     * Sets the value of the modifiedReleaseType property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setModifiedReleaseType(Integer value) {
        this.modifiedReleaseType = value;
    }

    /**
     * Gets the value of the specificDrugDevice property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSpecificDrugDevice() {
        return specificDrugDevice;
    }

    /**
     * Sets the value of the specificDrugDevice property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSpecificDrugDevice(Integer value) {
        this.specificDrugDevice = value;
    }

    /**
     * Gets the value of the dimensions property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDimensions() {
        return dimensions;
    }

    /**
     * Sets the value of the dimensions property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDimensions(String value) {
        this.dimensions = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link ConsultTextType }
     *
     */
    public ConsultTextType getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link ConsultTextType }
     *
     */
    public void setName(ConsultTextType value) {
        this.name = value;
    }

    /**
     * Gets the value of the note property.
     *
     * @return
     *     possible object is
     *     {@link ConsultTextType }
     *
     */
    public ConsultTextType getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     *
     * @param value
     *     allowed object is
     *     {@link ConsultTextType }
     *
     */
    public void setNote(ConsultTextType value) {
        this.note = value;
    }

    /**
     * Gets the value of the pharmaceuticalForms property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pharmaceuticalForms property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPharmaceuticalForms().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PharmaceuticalFormWithStandardsType }
     *
     *
     */
    public List<PharmaceuticalFormWithStandardsType> getPharmaceuticalForms() {
        if (pharmaceuticalForms == null) {
            pharmaceuticalForms = new ArrayList<PharmaceuticalFormWithStandardsType>();
        }
        return this.pharmaceuticalForms;
    }

    /**
     * Gets the value of the routeOfAdministrations property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the routeOfAdministrations property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRouteOfAdministrations().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RouteOfAdministrationWithStandardsType }
     *
     *
     */
    public List<RouteOfAdministrationWithStandardsType> getRouteOfAdministrations() {
        if (routeOfAdministrations == null) {
            routeOfAdministrations = new ArrayList<RouteOfAdministrationWithStandardsType>();
        }
        return this.routeOfAdministrations;
    }

    /**
     * Gets the value of the realActualIngredients property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the realActualIngredients property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRealActualIngredients().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConsultRealActualIngredientType }
     *
     *
     */
    public List<ConsultRealActualIngredientType> getRealActualIngredients() {
        if (realActualIngredients == null) {
            realActualIngredients = new ArrayList<ConsultRealActualIngredientType>();
        }
        return this.realActualIngredients;
    }

    /**
     * Gets the value of the sequenceNr property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getSequenceNr() {
        return sequenceNr;
    }

    /**
     * Sets the value of the sequenceNr property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setSequenceNr(BigInteger value) {
        this.sequenceNr = value;
    }

    /**
     * Gets the value of the vmpComponentCode property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getVmpComponentCode() {
        return vmpComponentCode;
    }

    /**
     * Sets the value of the vmpComponentCode property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setVmpComponentCode(BigInteger value) {
        this.vmpComponentCode = value;
    }

    /**
     * Gets the value of the startDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

}
