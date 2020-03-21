package pl.parser.nbp.model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "tabela_kursow")
@XmlAccessorType(XmlAccessType.FIELD)
public class RatesTable implements Serializable {

    @XmlElement(name = "numer_tabeli")
    private String tableNumber;
    @XmlElement(name = "data_notowania")
    private Date tradingDate;
    @XmlElement(name = "data_publikacji")
    private Date publicationDate;
    @XmlElement(name = "pozycja")
    private List<Position> position;

}
