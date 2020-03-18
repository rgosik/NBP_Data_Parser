package pl.parser.nbp.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "tabela_kursow")
@XmlAccessorType(XmlAccessType.FIELD)
public class RatesTable implements Serializable {

    @XmlElement(name = "numer_tabeli")
    @Setter private String tableNumber;
    @XmlElement(name = "data_notowania")
    @Setter private Date tradingDate;
    @XmlElement(name = "data_publikacji")
    @Setter private Date publicationDate;
    @XmlElement(name = "pozycja")
    @Setter private List<Position> position;

    public String getTableNumber() {
        return tableNumber;
    }

    public Date getTradingDate() {
        return tradingDate;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public List<Position> getPozycja() {
        return position;
    }

}
