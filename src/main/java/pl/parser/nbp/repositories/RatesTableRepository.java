package pl.parser.nbp.repositories;

import pl.parser.nbp.model.Position;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface RatesTableRepository extends Serializable {

    String getTableNumber();
    Date getTradingDate();
    Date getPublicationDate();
    List<Position> getPozycja();

}
