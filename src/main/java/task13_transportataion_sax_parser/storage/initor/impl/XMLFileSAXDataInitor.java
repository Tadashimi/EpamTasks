package task13_transportataion_sax_parser.storage.initor.impl;

import task13_transportataion_sax_parser.cargo.domain.Cargo;
import task13_transportataion_sax_parser.carrier.domain.Carrier;
import task13_transportataion_sax_parser.common.business.exception.checked.InitStorageException;
import task13_transportataion_sax_parser.common.solutions.utils.FileUtils;
import task13_transportataion_sax_parser.storage.initor.impl.sax.CargoHandler;
import task13_transportataion_sax_parser.storage.initor.impl.sax.CarrierHandler;
import task13_transportataion_sax_parser.storage.initor.impl.sax.TransportationHandler;
import task13_transportataion_sax_parser.transportation.domain.Transportation;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class XMLFileSAXDataInitor extends FileDataInitor {
    private static final String FILE = "task13_transportataion_sax_parser/init-data.xml";

    @Override
    public void initStorage() throws InitStorageException {
        try {
            File file = FileUtils.createFileFromResource("init-data", "lesson13", FILE);
            SAXParser saxParser = getSaxParser();

            Map<String, Cargo> cargoMap = parseCargoes(saxParser, file);
            Map<String, Carrier> carrierMap = parseCarriers(saxParser, file);
            List<ParsedTransportation> parsedTransportationList = parseTransportations(saxParser, file);

            setReferencesBetweenEntities(cargoMap, carrierMap, parsedTransportationList);

            persistCargos(cargoMap.values());
            persistCarriers(carrierMap.values());

            List<Transportation> transportationList = getTransportationsFromParsedObject(parsedTransportationList);
            persistTransportations(transportationList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InitStorageException(e.getMessage());
        }

    }

    private SAXParser getSaxParser() throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        return factory.newSAXParser();
    }

    private Map<String, Cargo> parseCargoes(SAXParser saxParser, File file) throws IOException, SAXException {
        CargoHandler cargoHandler = new CargoHandler();
        saxParser.parse(file, cargoHandler);
        return cargoHandler.getCargoMap();
    }

    private Map<String, Carrier> parseCarriers(SAXParser saxParser, File file) throws IOException, SAXException {
        CarrierHandler carrierHandler = new CarrierHandler();
        saxParser.parse(file, carrierHandler);
        return carrierHandler.getCarrierMap();
    }

    private List<ParsedTransportation> parseTransportations(SAXParser saxParser, File file) throws IOException, SAXException {
        TransportationHandler transportationHandler = new TransportationHandler();
        saxParser.parse(file, transportationHandler);
        return transportationHandler.getParsedTransportationList();
    }
}
