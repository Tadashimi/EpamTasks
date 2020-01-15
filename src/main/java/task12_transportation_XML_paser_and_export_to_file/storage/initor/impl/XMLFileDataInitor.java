package task12_transportation_XML_paser_and_export_to_file.storage.initor.impl;

import task12_transportation_XML_paser_and_export_to_file.cargo.domain.Cargo;
import task12_transportation_XML_paser_and_export_to_file.cargo.domain.CargoType;
import task12_transportation_XML_paser_and_export_to_file.cargo.domain.ClothersCargo;
import task12_transportation_XML_paser_and_export_to_file.cargo.domain.FoodCargo;
import task12_transportation_XML_paser_and_export_to_file.carrier.domain.Carrier;
import task12_transportation_XML_paser_and_export_to_file.carrier.domain.CarrierType;
import task12_transportation_XML_paser_and_export_to_file.common.business.exception.checked.InitStorageException;
import task12_transportation_XML_paser_and_export_to_file.common.solutions.utils.FileUtils;
import task12_transportation_XML_paser_and_export_to_file.common.solutions.utils.JavaUtilDataUtils;
import task12_transportation_XML_paser_and_export_to_file.transportation.domain.Transportation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;

public class XMLFileDataInitor extends FileDataInitor {
    private static final String FILE = "task12_transportation_XML_paser_and_export_to_file/init-data.xml";

    @Override
    public void initStorage() throws InitStorageException {
        Document document = null;
        try {
            document = getDocumentFromFIleWIthInitData();

            Map<String, Cargo> cargoMap = parseCargosFromDocument(document);
            Map<String, Carrier> carrierMap = parseCarriersFromDocument(document);
            List<ParsedTransportation> parsedTransportationList = parseTransportationsDataFromDocument(document);
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

    private Document getDocumentFromFIleWIthInitData() throws IOException, ParserConfigurationException, SAXException {
        File file = FileUtils.createFileFromResource("init-data", "lesson12", FILE);
        Document document = null;
        if (file != null) {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            documentBuilderFactory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
        }
        return document;
    }

    private Map<String, Cargo> parseCargosFromDocument(Document document) throws ParseException {
        Map<String, Cargo> cargoMap = new HashMap<>();
        NodeList cargoes = document.getElementsByTagName("cargoes").item(0).getChildNodes();
        for (int i = 0; i < cargoes.getLength(); i++) {
            Node cargoNode = cargoes.item(i);
            if (cargoNode.getNodeType() == Node.ELEMENT_NODE) {
                SimpleEntry<String, Cargo> cargoData = parseCargoFromElemet((Element) cargoNode);
                if (cargoData != null) {
                    cargoMap.put(cargoData.getKey(), cargoData.getValue());
                }
            }
        }
        return cargoMap;
    }

    private SimpleEntry<String, Cargo> parseCargoFromElemet(Element cargoElement) throws ParseException {
        CargoType cargoType = CargoType.valueOf(cargoElement.getAttribute("type"));
        String id = cargoElement.getElementsByTagName("id").item(0).getTextContent();
        String name = cargoElement.getElementsByTagName("name").item(0).getTextContent();
        int weight = Integer.valueOf(cargoElement.getElementsByTagName("weight").item(0).getTextContent());
        Cargo cargo;
        switch (cargoType) {
            case CLOTHERS:
                String size = cargoElement.getElementsByTagName("size").item(0).getTextContent();
                String material = cargoElement.getElementsByTagName("material").item(0).getTextContent();
                cargo = new ClothersCargo();
                ((ClothersCargo) cargo).setSize(size);
                ((ClothersCargo) cargo).setMaterial(material);
                break;
            default:
                String expirationDateString = cargoElement.getElementsByTagName("expirationDate").item(0).getTextContent();
                Date expirationDate = JavaUtilDataUtils.valueOf(expirationDateString);
                int temperature = Integer.valueOf(cargoElement.getElementsByTagName("storeTemperature").item(0).getTextContent());
                cargo = new FoodCargo();
                ((FoodCargo) cargo).setExpirationDate(expirationDate);
                ((FoodCargo) cargo).setStoreTemperature(temperature);
        }
        cargo.setName(name);
        cargo.setWeight(weight);
        return new SimpleEntry<>(id, cargo);
    }

    private Map<String, Carrier> parseCarriersFromDocument(Document document) {
        Map<String, Carrier> carrierMap = new HashMap<>();
        NodeList carriers = document.getElementsByTagName("carriers").item(0).getChildNodes();
        for (int i = 0; i < carriers.getLength(); i++) {
            Node carrierNode = carriers.item(i);
            if (carrierNode.getNodeType() == Node.ELEMENT_NODE) {
                SimpleEntry<String, Carrier> carrierData = parseCarrierFromElement((Element) carrierNode);
                if (carrierData != null) {
                    carrierMap.put(carrierData.getKey(), carrierData.getValue());
                }
            }
        }
        return carrierMap;
    }

    private SimpleEntry<String, Carrier> parseCarrierFromElement(Element carrierElement) {
        CarrierType carrierType = CarrierType.valueOf(carrierElement.getAttribute("type"));
        String id = carrierElement.getElementsByTagName("id").item(0).getTextContent();
        String name = carrierElement.getElementsByTagName("name").item(0).getTextContent();
        String address = carrierElement.getElementsByTagName("address").item(0).getTextContent();
        Carrier carrier = new Carrier();
        carrier.setName(name);
        carrier.setAddress(address);
        carrier.setCarrierType(carrierType);
        return new SimpleEntry<>(id, carrier);
    }

    private List<ParsedTransportation> parseTransportationsDataFromDocument(Document document) throws ParseException {
        List<ParsedTransportation> result = new ArrayList<>();

        NodeList transportations = document.getElementsByTagName("transportations").item(0).getChildNodes();
        for (int i = 0; i < transportations.getLength(); i++) {
            Node transportationNode = transportations.item(i);
            if (transportationNode.getNodeType() == Node.ELEMENT_NODE) {
                result.add(parseTransportationFromElement((Element) transportationNode));
            }
        }
        return result;

    }

    private ParsedTransportation parseTransportationFromElement(Element transportationElement) throws ParseException {
        String cargoId = transportationElement.getElementsByTagName("cargo_id").item(0).getTextContent();
        String carrierId = transportationElement.getElementsByTagName("carrier_id").item(0).getTextContent();
        String description = transportationElement.getElementsByTagName("description").item(0).getTextContent();
        String billTo = transportationElement.getElementsByTagName("billTo").item(0).getTextContent();
        String dateStr = transportationElement.getElementsByTagName("date").item(0).getTextContent();
        Date transportationDate = JavaUtilDataUtils.valueOf(dateStr);

        Transportation transportation = new Transportation();
        transportation.setDescription(description);
        transportation.setBillTo(billTo);
        transportation.setTransportationBeginDate(transportationDate);

        ParsedTransportation parsedTransportation = new ParsedTransportation();
        parsedTransportation.cargoRef = cargoId;
        parsedTransportation.carrierRef = carrierId;
        parsedTransportation.transportation = transportation;

        return parsedTransportation;
    }
}
