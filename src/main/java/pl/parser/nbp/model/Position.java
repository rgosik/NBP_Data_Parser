package pl.parser.nbp.model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "pozycja")
@XmlAccessorType(XmlAccessType.FIELD)
public class Position implements Serializable {

    @XmlElement(name = "nazwa_waluty")
    private String currencyName;
    @XmlElement(name = "przelicznik")
    private int conversionFactor;
    @XmlElement(name = "kod_waluty")
    private String currencyCode;
    @XmlElement(name = "kurs_kupna")
    private String buyingRate;
    @XmlElement(name = "kurs_sprzedazy")
    private String sellingRate;

}
