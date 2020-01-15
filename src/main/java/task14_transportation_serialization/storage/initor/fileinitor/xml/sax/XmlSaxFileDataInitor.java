package task14_transportation_serialization.storage.initor.fileinitor.xml.sax;

import task14_transportation_serialization.cargo.domain.Cargo;
import task14_transportation_serialization.carrier.domain.Carrier;
import task14_transportation_serialization.common.business.exception.checked.InitStorageException;
import task14_transportation_serialization.common.solutions.utils.FileUtils;
import task14_transportation_serialization.common.solutions.utils.xml.sax.XmlSaxUtils;
import task14_transportation_serialization.storage.initor.fileinitor.BaseFileInitor;
import task14_transportation_serialization.transportation.domain.Transportation;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class XmlSaxFileDataInitor extends BaseFileInitor {

    private static final String FILE = "task14_transportation_serialization/init-data.xml";

    @Override
    public void initStorage() throws InitStorageException {
        File file = null;
        try {
            file = getFileWithInitData();
            SAXParser parser = XmlSaxUtils.getParser();
            SaxHandler saxHandler = new SaxHandler();
            parser.parse(file, saxHandler);
            Map<String, Cargo> cargoMap = saxHandler.getCargoMap();
            Map<String, Carrier> carrierMap = saxHandler.getCarrierMap();
            List<ParsedTransportation> transportations = saxHandler.getTransportations();
            setReferencesBetweenEntities(cargoMap, carrierMap, transportations);

            persistCargos(cargoMap.values());
            persistCarriers(carrierMap.values());
            List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
            persistTransportations(transportationList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InitStorageException(e.getMessage());
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils.createFileFromResource("init-data", "lesson14", FILE);
    }
}

