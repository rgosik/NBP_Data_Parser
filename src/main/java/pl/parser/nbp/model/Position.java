package pl.parser.nbp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "pozycja")
@XmlAccessorType(XmlAccessType.FIELD)
public class Position implements Serializable {

    @XmlElement(name = "nazwa_waluty")
    @Getter @Setter private String currencyName;
    @XmlElement(name = "przelicznik")
    @Getter @Setter private int conversionFactor;
    @XmlElement(name = "kod_waluty")
    @Getter @Setter private String currencyCode;
    @XmlElement(name = "kurs_kupna")
    @Getter @Setter private String buyingRate;
    @XmlElement(name = "kurs_sprzedazy")
    @Getter @Setter private String sellingRate;
    
}
