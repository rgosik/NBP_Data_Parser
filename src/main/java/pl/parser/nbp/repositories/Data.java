package pl.parser.nbp.repositories;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface Data {

    boolean unmarshalXmlFilesToObjects(List<File> xmlFiles);
    Set<RatesTableRepository> getRatesTables();
}
