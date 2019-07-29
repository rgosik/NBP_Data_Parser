package pl.parser.nbp.xmlmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "tabela_kursow")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RatesTable implements Serializable {

    private String numer_tabeli;
    private Date data_notowania;
    private Date data_publikacji;
    private List<Position> pozycja;
}
