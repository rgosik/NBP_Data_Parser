package pl.parser.nbp.services;

import pl.parser.nbp.model.RatesTable;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface RatesTableService {

    boolean unmarshalXmlFilesToObjects(List<File> xmlFiles);
    Set<RatesTable> getRatesTables();
}
