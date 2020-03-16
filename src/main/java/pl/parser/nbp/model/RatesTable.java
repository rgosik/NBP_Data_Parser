package pl.parser.nbp.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.parser.nbp.repositories.RatesTableRepository;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "tabela_kursow")
@XmlAccessorType(XmlAccessType.FIELD)
public class RatesTable implements RatesTableRepository {

    @XmlElement(name = "numer_tabeli")
    @Setter private String tableNumber;
    @XmlElement(name = "data_notowania")
    @Setter private Date tradingDate;
    @XmlElement(name = "data_publikacji")
    @Setter private Date publicationDate;
    @XmlElement(name = "pozycja")
    @Setter private List<Position> position;

    @Override
    public String getTableNumber() {
        return tableNumber;
    }

    @Override
    public Date getTradingDate() {
        return tradingDate;
    }

    @Override
    public Date getPublicationDate() {
        return publicationDate;
    }

    @Override
    public List<Position> getPozycja() {
        return position;
    }

}
