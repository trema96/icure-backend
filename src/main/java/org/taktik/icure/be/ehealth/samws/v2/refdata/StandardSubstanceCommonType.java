//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.05.22 at 08:11:32 PM CEST
//


package org.taktik.icure.be.ehealth.samws.v2.refdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.taktik.icure.be.ehealth.samws.v2.core.Text255Type;
import org.taktik.icure.be.ehealth.samws.v2.core.TextType;


/**
 * <p>Java class for StandardSubstanceCommonType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="StandardSubstanceCommonType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:refdata}StandardSubstanceKeyCommonType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:be:fgov:ehealth:samws:v2:refdata}StandardSubstanceFields"/>
 *         &lt;element name="Substance" type="{urn:be:fgov:ehealth:samws:v2:refdata}SubstanceKeyType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StandardSubstanceCommonType", propOrder = {
    "name",
    "definition",
    "url",
    "substances"
})
public class StandardSubstanceCommonType
    extends StandardSubstanceKeyCommonType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(name = "Name")
    protected Text255Type name;
    @XmlElement(name = "Definition")
    protected TextType definition;
    @XmlElement(name = "URL")
    protected String url;
    @XmlElement(name = "Substance", required = true)
    protected List<SubstanceKeyType> substances;

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
     * Gets the value of the definition property.
     *
     * @return
     *     possible object is
     *     {@link TextType }
     *
     */
    public TextType getDefinition() {
        return definition;
    }

    /**
     * Sets the value of the definition property.
     *
     * @param value
     *     allowed object is
     *     {@link TextType }
     *
     */
    public void setDefinition(TextType value) {
        this.definition = value;
    }

    /**
     * Gets the value of the url property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getURL() {
        return url;
    }

    /**
     * Sets the value of the url property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setURL(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the substances property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the substances property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubstances().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubstanceKeyType }
     *
     *
     */
    public List<SubstanceKeyType> getSubstances() {
        if (substances == null) {
            substances = new ArrayList<SubstanceKeyType>();
        }
        return this.substances;
    }

}
