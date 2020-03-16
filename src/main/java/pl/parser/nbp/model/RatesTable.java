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
    @Setter private String numer_tabeli;
    @XmlElement(name = "data_notowania")
    @Setter private Date data_notowania;
    @XmlElement(name = "data_publikacji")
    @Setter private Date data_publikacji;
    @XmlElement(name = "pozycja")
    @Setter private List<Position> pozycja;

    @Override
    public String getNumer_tabeli() {
        return numer_tabeli;
    }

    @Override
    public Date getData_notowania() {
        return data_notowania;
    }

    @Override
    public Date getData_publikacji() {
        return data_publikacji;
    }

    @Override
    public List<Position> getPozycja() {
        return pozycja;
    }

}
