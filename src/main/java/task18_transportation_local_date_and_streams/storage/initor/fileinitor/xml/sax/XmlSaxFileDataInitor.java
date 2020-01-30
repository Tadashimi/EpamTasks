package task18_transportation_local_date_and_streams.storage.initor.fileinitor.xml.sax;

import task18_transportation_local_date_and_streams.cargo.domain.Cargo;
import task18_transportation_local_date_and_streams.carrier.domain.Carrier;
import task18_transportation_local_date_and_streams.common.business.exception.checked.InitStorageException;
import task18_transportation_local_date_and_streams.common.solutions.utils.FileUtils;
import task18_transportation_local_date_and_streams.common.solutions.utils.xml.sax.XmlSaxUtils;
import task18_transportation_local_date_and_streams.storage.initor.fileinitor.BaseFileInitor;
import task18_transportation_local_date_and_streams.transportation.domain.Transportation;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class XmlSaxFileDataInitor extends BaseFileInitor {

    private static final String FILE = "task18_transportation_zoned_date_time/init-data.xml";

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
        return FileUtils.createFileFromResource("init-data", "lesson16", FILE);
    }
}

