package pl.parser.nbp.repositories;

import java.io.Serializable;

public interface PositionRepository extends Serializable {

    String getKurs_sprzedazy();
    String getKurs_kupna();
    String getKod_waluty();
    int getPrzelicznik();
    String getNazwa_waluty();
}
