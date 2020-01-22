package task15_transportation_threads.storage.initor.fileinitor.xml.sax;

import task15_transportation_threads.cargo.domain.Cargo;
import task15_transportation_threads.carrier.domain.Carrier;
import task15_transportation_threads.common.business.exception.checked.InitStorageException;
import task15_transportation_threads.common.solutions.utils.FileUtils;
import task15_transportation_threads.common.solutions.utils.xml.sax.XmlSaxUtils;
import task15_transportation_threads.storage.initor.fileinitor.BaseFileInitor;
import task15_transportation_threads.transportation.domain.Transportation;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class XmlSaxFileDataInitor extends BaseFileInitor {

    private static final String FILE = "task15_transportation_threads/init-data.xml";

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

            persistCargoesAndCarriers(cargoMap.values(), carrierMap.values());

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
        return FileUtils.createFileFromResource("init-data", "lesson15", FILE);
    }
}

