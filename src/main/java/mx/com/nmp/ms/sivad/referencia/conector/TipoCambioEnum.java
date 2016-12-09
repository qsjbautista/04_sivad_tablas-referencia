/**
 * Proyecto:        NMP - Microservicio de Tablas de Referencia
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.ms.sivad.referencia.conector;

/**
 * Enum que define los tipos de tipo de cambio.
 *
 * @author ngonzalez
 */
public enum TipoCambioEnum {

    AED("AED"),
    AFA("AFA"),
    ALL("ALL"),
    ANG("ANG"),
    ARS("ARS"),
    AUD("AUD"),
    AWG("AWG"),
    BBD("BBD"),
    BDT("BDT"),
    BHD("BHD"),
    BIF("BIF"),
    BMD("BMD"),
    BND("BND"),
    BOB("BOB"),
    BRL("BRL"),
    BSD("BSD"),
    BTN("BTN"),
    BWP("BWP"),
    BZD("BZD"),
    CAD("CAD"),
    CHF("CHF"),
    CLP("CLP"),
    CNY("CNY"),
    COP("COP"),
    CRC("CRC"),
    CUP("CUP"),
    CVE("CVE"),
    CYP("CYP"),
    CZK("CZK"),
    DJF("DJF"),
    DKK("DKK"),
    DOP("DOP"),
    DZD("DZD"),
    EEK("EEK"),
    EGP("EGP"),
    ETB("ETB"),
    EUR("EUR"),
    FKP("FKP"),
    GBP("GBP"),
    GHC("GHC"),
    GIP("GIP"),
    GMD("GMD"),
    GNF("GNF"),
    GTQ("GTQ"),
    GYD("GYD"),
    HKD("HKD"),
    HNL("HNL"),
    HRK("HRK"),
    HTG("HTG"),
    HUF("HUF"),
    IDR("IDR"),
    ILS("ILS"),
    INR("INR"),
    IQD("IQD"),
    ISK("ISK"),
    JMD("JMD"),
    JOD("JOD"),
    JPY("JPY"),
    KES("KES"),
    KHR("KHR"),
    KMF("KMF"),
    KPW("KPW"),
    KRW("KRW"),
    KWD("KWD"),
    KYD("KYD"),
    KZT("KZT"),
    LAK("LAK"),
    LBP("LBP"),
    LKR("LKR"),
    LRD("LRD"),
    LSL("LSL"),
    LTL("LTL"),
    LVL("LVL"),
    LYD("LYD"),
    MAD("MAD"),
    MDL("MDL"),
    MGF("MGF"),
    MKD("MKD"),
    MMK("MMK"),
    MNT("MNT"),
    MOP("MOP"),
    MRO("MRO"),
    MTL("MTL"),
    MUR("MUR"),
    MVR("MVR"),
    MWK("MWK"),
    MXN("MXN"),
    MYR("MYR"),
    MZM("MZM"),
    NAD("NAD"),
    NGN("NGN"),
    NIO("NIO"),
    NOK("NOK"),
    NPR("NPR"),
    NZD("NZD"),
    OMR("OMR"),
    PAB("PAB"),
    PEN("PEN"),
    PGK("PGK"),
    PHP("PHP"),
    PKR("PKR"),
    PLN("PLN"),
    PYG("PYG"),
    QAR("QAR"),
    ROL("ROL"),
    RUB("RUB"),
    SAR("SAR"),
    SBD("SBD"),
    SCR("SCR"),
    SDD("SDD"),
    SEK("SEK"),
    SGD("SGD"),
    SHP("SHP"),
    SIT("SIT"),
    SKK("SKK"),
    SLL("SLL"),
    SOS("SOS"),
    SRG("SRG"),
    STD("STD"),
    SVC("SVC"),
    SYP("SYP"),
    SZL("SZL"),
    THB("THB"),
    TND("TND"),
    TOP("TOP"),
    TRL("TRL"),
    TRY("TRY"),
    TTD("TTD"),
    TWD("TWD"),
    TZS("TZS"),
    UAH("UAH"),
    UGX("UGX"),
    USD("USD"),
    UYU("UYU"),
    VEB("VEB"),
    VND("VND"),
    VUV("VUV"),
    WST("WST"),
    XAF("XAF"),
    XAG("XAG"),
    XAU("XAU"),
    XCD("XCD"),
    XOF("XOF"),
    XPD("XPD"),
    XPF("XPF"),
    XPT("XPT"),
    YER("YER"),
    YUM("YUM"),
    ZAR("ZAR"),
    ZMK("ZMK"),
    ZWD("ZWD");

    /**
     * Tipo de tipo de cambio.
     */
    private String tipo;



    // METODOS

    /**
     * Constructor.
     *
     * @param tipo El tipo de tipo de cambio.
     */
    TipoCambioEnum(String tipo) {
        this.tipo = tipo;
    }



    // GETTERS

    public String getTipo() {
        return tipo;
    }

}
