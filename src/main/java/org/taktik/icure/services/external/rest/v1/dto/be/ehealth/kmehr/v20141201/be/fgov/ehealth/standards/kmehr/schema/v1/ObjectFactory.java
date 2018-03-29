/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.03.05 à 11:48:22 AM CET 
//


package org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20141201.be.fgov.ehealth.standards.kmehr.schema.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20141201.be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20141201.org.w3.xmldsig.Signature;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr._20141201.be.fgov.ehealth.standards.kmehr.schema.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _KmehrmessageSignature_QNAME = new QName("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "Signature");
    private final static QName _CompoundprescriptionTypeMagistraltext_QNAME = new QName("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "magistraltext");
    private final static QName _CompoundprescriptionTypeGalenicform_QNAME = new QName("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "galenicform");
    private final static QName _CompoundprescriptionTypeQuantity_QNAME = new QName("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "quantity");
    private final static QName _CompoundprescriptionTypeCompound_QNAME = new QName("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "compound");
    private final static QName _CompoundprescriptionTypeFormularyreference_QNAME = new QName("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "formularyreference");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr._20141201.be.fgov.ehealth.standards.kmehr.schema.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FrequencyType }
     * 
     */
    public FrequencyType createFrequencyType() {
        return new FrequencyType();
    }

    /**
     * Create an instance of {@link RefscopeType }
     * 
     */
    public RefscopeType createRefscopeType() {
        return new RefscopeType();
    }

    /**
     * Create an instance of {@link PersonType }
     * 
     */
    public PersonType createPersonType() {
        return new PersonType();
    }

    /**
     * Create an instance of {@link CompoundType }
     * 
     */
    public CompoundType createCompoundType() {
        return new CompoundType();
    }

    /**
     * Create an instance of {@link ItemType }
     * 
     */
    public ItemType createItemType() {
        return new ItemType();
    }

    /**
     * Create an instance of {@link ItemType.Regimen }
     * 
     */
    public ItemType.Regimen createItemTypeRegimen() {
        return new ItemType.Regimen();
    }

    /**
     * Create an instance of {@link ItemType.Posology }
     * 
     */
    public ItemType.Posology createItemTypePosology() {
        return new ItemType.Posology();
    }

    /**
     * Create an instance of {@link ContentType }
     * 
     */
    public ContentType createContentType() {
        return new ContentType();
    }

    /**
     * Create an instance of {@link StandardType }
     * 
     */
    public StandardType createStandardType() {
        return new StandardType();
    }

    /**
     * Create an instance of {@link InsuranceType }
     * 
     */
    public InsuranceType createInsuranceType() {
        return new InsuranceType();
    }

    /**
     * Create an instance of {@link Kmehrmessage }
     * 
     */
    public Kmehrmessage createKmehrmessage() {
        return new Kmehrmessage();
    }

    /**
     * Create an instance of {@link ConfidentialityType }
     * 
     */
    public ConfidentialityType createConfidentialityType() {
        return new ConfidentialityType();
    }

    /**
     * Create an instance of {@link HeaderType }
     * 
     */
    public HeaderType createHeaderType() {
        return new HeaderType();
    }

    /**
     * Create an instance of {@link Base64EncryptedDataType }
     * 
     */
    public Base64EncryptedDataType createBase64EncryptedDataType() {
        return new Base64EncryptedDataType();
    }

    /**
     * Create an instance of {@link FolderType }
     * 
     */
    public FolderType createFolderType() {
        return new FolderType();
    }

    /**
     * Create an instance of {@link QuantityType }
     * 
     */
    public QuantityType createQuantityType() {
        return new QuantityType();
    }

    /**
     * Create an instance of {@link AcknowledgmentType }
     * 
     */
    public AcknowledgmentType createAcknowledgmentType() {
        return new AcknowledgmentType();
    }

    /**
     * Create an instance of {@link MemberinsuranceType }
     * 
     */
    public MemberinsuranceType createMemberinsuranceType() {
        return new MemberinsuranceType();
    }

    /**
     * Create an instance of {@link LocationBirthPlaceType }
     * 
     */
    public LocationBirthPlaceType createLocationBirthPlaceType() {
        return new LocationBirthPlaceType();
    }

    /**
     * Create an instance of {@link CountryType }
     * 
     */
    public CountryType createCountryType() {
        return new CountryType();
    }

    /**
     * Create an instance of {@link HolterType }
     * 
     */
    public HolterType createHolterType() {
        return new HolterType();
    }

    /**
     * Create an instance of {@link PackageType }
     * 
     */
    public PackageType createPackageType() {
        return new PackageType();
    }

    /**
     * Create an instance of {@link SeverityType }
     * 
     */
    public SeverityType createSeverityType() {
        return new SeverityType();
    }

    /**
     * Create an instance of {@link FCMAXType }
     * 
     */
    public FCMAXType createFCMAXType() {
        return new FCMAXType();
    }

    /**
     * Create an instance of {@link RecipientType }
     * 
     */
    public RecipientType createRecipientType() {
        return new RecipientType();
    }

    /**
     * Create an instance of {@link MedicationType }
     * 
     */
    public MedicationType createMedicationType() {
        return new MedicationType();
    }

    /**
     * Create an instance of {@link CostType }
     * 
     */
    public CostType createCostType() {
        return new CostType();
    }

    /**
     * Create an instance of {@link TelecomType }
     * 
     */
    public TelecomType createTelecomType() {
        return new TelecomType();
    }

    /**
     * Create an instance of {@link RRMINType }
     * 
     */
    public RRMINType createRRMINType() {
        return new RRMINType();
    }

    /**
     * Create an instance of {@link Base64EncryptedValueType }
     * 
     */
    public Base64EncryptedValueType createBase64EncryptedValueType() {
        return new Base64EncryptedValueType();
    }

    /**
     * Create an instance of {@link SiteType }
     * 
     */
    public SiteType createSiteType() {
        return new SiteType();
    }

    /**
     * Create an instance of {@link ProfessionType }
     * 
     */
    public ProfessionType createProfessionType() {
        return new ProfessionType();
    }

    /**
     * Create an instance of {@link AddressType }
     * 
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link ContentlocalitemattributeType }
     * 
     */
    public ContentlocalitemattributeType createContentlocalitemattributeType() {
        return new ContentlocalitemattributeType();
    }

    /**
     * Create an instance of {@link TimequantityType }
     * 
     */
    public TimequantityType createTimequantityType() {
        return new TimequantityType();
    }

    /**
     * Create an instance of {@link WeekdayType }
     * 
     */
    public WeekdayType createWeekdayType() {
        return new WeekdayType();
    }

    /**
     * Create an instance of {@link TimeunitType }
     * 
     */
    public TimeunitType createTimeunitType() {
        return new TimeunitType();
    }

    /**
     * Create an instance of {@link SubstanceType }
     * 
     */
    public SubstanceType createSubstanceType() {
        return new SubstanceType();
    }

    /**
     * Create an instance of {@link GalenicformType }
     * 
     */
    public GalenicformType createGalenicformType() {
        return new GalenicformType();
    }

    /**
     * Create an instance of {@link AddressTypeBase }
     * 
     */
    public AddressTypeBase createAddressTypeBase() {
        return new AddressTypeBase();
    }

    /**
     * Create an instance of {@link AdministrationunitType }
     * 
     */
    public AdministrationunitType createAdministrationunitType() {
        return new AdministrationunitType();
    }

    /**
     * Create an instance of {@link MomentType }
     * 
     */
    public MomentType createMomentType() {
        return new MomentType();
    }

    /**
     * Create an instance of {@link CompoundprescriptionType }
     * 
     */
    public CompoundprescriptionType createCompoundprescriptionType() {
        return new CompoundprescriptionType();
    }

    /**
     * Create an instance of {@link IncapacityreasonType }
     * 
     */
    public IncapacityreasonType createIncapacityreasonType() {
        return new IncapacityreasonType();
    }

    /**
     * Create an instance of {@link StrengthType }
     * 
     */
    public StrengthType createStrengthType() {
        return new StrengthType();
    }

    /**
     * Create an instance of {@link ErrorType }
     * 
     */
    public ErrorType createErrorType() {
        return new ErrorType();
    }

    /**
     * Create an instance of {@link FCMINType }
     * 
     */
    public FCMINType createFCMINType() {
        return new FCMINType();
    }

    /**
     * Create an instance of {@link DayperiodType }
     * 
     */
    public DayperiodType createDayperiodType() {
        return new DayperiodType();
    }

    /**
     * Create an instance of {@link SexType }
     * 
     */
    public SexType createSexType() {
        return new SexType();
    }

    /**
     * Create an instance of {@link DurationType }
     * 
     */
    public DurationType createDurationType() {
        return new DurationType();
    }

    /**
     * Create an instance of {@link IncapacityType }
     * 
     */
    public IncapacityType createIncapacityType() {
        return new IncapacityType();
    }

    /**
     * Create an instance of {@link TemporalityType }
     * 
     */
    public TemporalityType createTemporalityType() {
        return new TemporalityType();
    }

    /**
     * Create an instance of {@link UnitType }
     * 
     */
    public UnitType createUnitType() {
        return new UnitType();
    }

    /**
     * Create an instance of {@link FormularyreferenceType }
     * 
     */
    public FormularyreferenceType createFormularyreferenceType() {
        return new FormularyreferenceType();
    }

    /**
     * Create an instance of {@link DateType }
     * 
     */
    public DateType createDateType() {
        return new DateType();
    }

    /**
     * Create an instance of {@link AdministrationquantityType }
     * 
     */
    public AdministrationquantityType createAdministrationquantityType() {
        return new AdministrationquantityType();
    }

    /**
     * Create an instance of {@link CertaintyType }
     * 
     */
    public CertaintyType createCertaintyType() {
        return new CertaintyType();
    }

    /**
     * Create an instance of {@link RouteType }
     * 
     */
    public RouteType createRouteType() {
        return new RouteType();
    }

    /**
     * Create an instance of {@link HeadingType }
     * 
     */
    public HeadingType createHeadingType() {
        return new HeadingType();
    }

    /**
     * Create an instance of {@link UrgencyType }
     * 
     */
    public UrgencyType createUrgencyType() {
        return new UrgencyType();
    }

    /**
     * Create an instance of {@link AuthorType }
     * 
     */
    public AuthorType createAuthorType() {
        return new AuthorType();
    }

    /**
     * Create an instance of {@link MinrefType }
     * 
     */
    public MinrefType createMinrefType() {
        return new MinrefType();
    }

    /**
     * Create an instance of {@link RenewalType }
     * 
     */
    public RenewalType createRenewalType() {
        return new RenewalType();
    }

    /**
     * Create an instance of {@link PeriodicityType }
     * 
     */
    public PeriodicityType createPeriodicityType() {
        return new PeriodicityType();
    }

    /**
     * Create an instance of {@link RRMAXType }
     * 
     */
    public RRMAXType createRRMAXType() {
        return new RRMAXType();
    }

    /**
     * Create an instance of {@link TextWithLayoutType }
     * 
     */
    public TextWithLayoutType createTextWithLayoutType() {
        return new TextWithLayoutType();
    }

    /**
     * Create an instance of {@link LifecycleType }
     * 
     */
    public LifecycleType createLifecycleType() {
        return new LifecycleType();
    }

    /**
     * Create an instance of {@link LocalitemattributeType }
     * 
     */
    public LocalitemattributeType createLocalitemattributeType() {
        return new LocalitemattributeType();
    }

    /**
     * Create an instance of {@link MaxrefType }
     * 
     */
    public MaxrefType createMaxrefType() {
        return new MaxrefType();
    }

    /**
     * Create an instance of {@link ErrorMyCarenetType }
     * 
     */
    public ErrorMyCarenetType createErrorMyCarenetType() {
        return new ErrorMyCarenetType();
    }

    /**
     * Create an instance of {@link TransactionType }
     * 
     */
    public TransactionType createTransactionType() {
        return new TransactionType();
    }

    /**
     * Create an instance of {@link SenderType }
     * 
     */
    public SenderType createSenderType() {
        return new SenderType();
    }

    /**
     * Create an instance of {@link PresentationType }
     * 
     */
    public PresentationType createPresentationType() {
        return new PresentationType();
    }

    /**
     * Create an instance of {@link HcpartyType }
     * 
     */
    public HcpartyType createHcpartyType() {
        return new HcpartyType();
    }

    /**
     * Create an instance of {@link FrequencyType.Nominator }
     * 
     */
    public FrequencyType.Nominator createFrequencyTypeNominator() {
        return new FrequencyType.Nominator();
    }

    /**
     * Create an instance of {@link FrequencyType.Denominator }
     * 
     */
    public FrequencyType.Denominator createFrequencyTypeDenominator() {
        return new FrequencyType.Denominator();
    }

    /**
     * Create an instance of {@link RefscopeType.Refvalue }
     * 
     */
    public RefscopeType.Refvalue createRefscopeTypeRefvalue() {
        return new RefscopeType.Refvalue();
    }

    /**
     * Create an instance of {@link PersonType.Nationality }
     * 
     */
    public PersonType.Nationality createPersonTypeNationality() {
        return new PersonType.Nationality();
    }

    /**
     * Create an instance of {@link CompoundType.Medicinalproduct }
     * 
     */
    public CompoundType.Medicinalproduct createCompoundTypeMedicinalproduct() {
        return new CompoundType.Medicinalproduct();
    }

    /**
     * Create an instance of {@link CompoundType.Quantityprefix }
     * 
     */
    public CompoundType.Quantityprefix createCompoundTypeQuantityprefix() {
        return new CompoundType.Quantityprefix();
    }

    /**
     * Create an instance of {@link ItemType.Regimen.Daytime }
     * 
     */
    public ItemType.Regimen.Daytime createItemTypeRegimenDaytime() {
        return new ItemType.Regimen.Daytime();
    }

    /**
     * Create an instance of {@link ItemType.Regimen.Weekday }
     * 
     */
    public ItemType.Regimen.Weekday createItemTypeRegimenWeekday() {
        return new ItemType.Regimen.Weekday();
    }

    /**
     * Create an instance of {@link ItemType.Posology.Takes }
     * 
     */
    public ItemType.Posology.Takes createItemTypePosologyTakes() {
        return new ItemType.Posology.Takes();
    }

    /**
     * Create an instance of {@link ContentType.Substanceproduct }
     * 
     */
    public ContentType.Substanceproduct createContentTypeSubstanceproduct() {
        return new ContentType.Substanceproduct();
    }

    /**
     * Create an instance of {@link ContentType.Medicinalproduct }
     * 
     */
    public ContentType.Medicinalproduct createContentTypeMedicinalproduct() {
        return new ContentType.Medicinalproduct();
    }

    /**
     * Create an instance of {@link StandardType.Specialisation }
     * 
     */
    public StandardType.Specialisation createStandardTypeSpecialisation() {
        return new StandardType.Specialisation();
    }

    /**
     * Create an instance of {@link InsuranceType.Personalpart }
     * 
     */
    public InsuranceType.Personalpart createInsuranceTypePersonalpart() {
        return new InsuranceType.Personalpart();
    }

    /**
     * Create an instance of {@link InsuranceType.Thirdpayercontract }
     * 
     */
    public InsuranceType.Thirdpayercontract createInsuranceTypeThirdpayercontract() {
        return new InsuranceType.Thirdpayercontract();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Signature }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1", name = "Signature", scope = Kmehrmessage.class)
    public JAXBElement<Signature> createKmehrmessageSignature(Signature value) {
        return new JAXBElement<Signature>(_KmehrmessageSignature_QNAME, Signature.class, Kmehrmessage.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TextType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1", name = "magistraltext", scope = CompoundprescriptionType.class)
    public JAXBElement<TextType> createCompoundprescriptionTypeMagistraltext(TextType value) {
        return new JAXBElement<TextType>(_CompoundprescriptionTypeMagistraltext_QNAME, TextType.class, CompoundprescriptionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GalenicformType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1", name = "galenicform", scope = CompoundprescriptionType.class)
    public JAXBElement<GalenicformType> createCompoundprescriptionTypeGalenicform(GalenicformType value) {
        return new JAXBElement<GalenicformType>(_CompoundprescriptionTypeGalenicform_QNAME, GalenicformType.class, CompoundprescriptionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuantityType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1", name = "quantity", scope = CompoundprescriptionType.class)
    public JAXBElement<QuantityType> createCompoundprescriptionTypeQuantity(QuantityType value) {
        return new JAXBElement<QuantityType>(_CompoundprescriptionTypeQuantity_QNAME, QuantityType.class, CompoundprescriptionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompoundType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1", name = "compound", scope = CompoundprescriptionType.class)
    public JAXBElement<CompoundType> createCompoundprescriptionTypeCompound(CompoundType value) {
        return new JAXBElement<CompoundType>(_CompoundprescriptionTypeCompound_QNAME, CompoundType.class, CompoundprescriptionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FormularyreferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1", name = "formularyreference", scope = CompoundprescriptionType.class)
    public JAXBElement<FormularyreferenceType> createCompoundprescriptionTypeFormularyreference(FormularyreferenceType value) {
        return new JAXBElement<FormularyreferenceType>(_CompoundprescriptionTypeFormularyreference_QNAME, FormularyreferenceType.class, CompoundprescriptionType.class, value);
    }

}
