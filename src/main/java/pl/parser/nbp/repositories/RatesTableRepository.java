package pl.parser.nbp.repositories;

import pl.parser.nbp.model.Position;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface RatesTableRepository extends Serializable {

    String getNumer_tabeli();
    Date getData_notowania();
    Date getData_publikacji();
    List<Position> getPozycja();

}
