package pl.parser.nbp.model;

import pl.parser.nbp.repositories.RatesTableRepository;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;


@XmlRootElement(name = "tabela_kursow")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RatesTable implements RatesTableRepository {

    private String numer_tabeli;
    private Date data_notowania;
    private Date data_publikacji;
    private List<Position> pozycja;

    public RatesTable() {
    }

    public RatesTable(String numer_tabeli, Date data_notowania, Date data_publikacji, List<Position> pozycja) {
        this.numer_tabeli = numer_tabeli;
        this.data_notowania = data_notowania;
        this.data_publikacji = data_publikacji;
        this.pozycja = pozycja;
    }

    @Override
    public String getNumer_tabeli() {
        return numer_tabeli;
    }

    public void setNumer_tabeli(String numer_tabeli) {
        this.numer_tabeli = numer_tabeli;
    }

    @Override
    public Date getData_notowania() {
        return data_notowania;
    }

    public void setData_notowania(Date data_notowania) {
        this.data_notowania = data_notowania;
    }

    @Override
    public Date getData_publikacji() {
        return data_publikacji;
    }

    public void setData_publikacji(Date data_publikacji) {
        this.data_publikacji = data_publikacji;
    }

    @Override
    public List<Position> getPozycja() {
        return pozycja;
    }

    public void setPozycja(List<Position> pozycja) {
        this.pozycja = pozycja;
    }
}
