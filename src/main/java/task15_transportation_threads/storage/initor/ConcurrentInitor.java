package task15_transportation_threads.storage.initor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import task15_transportation_threads.cargo.domain.Cargo;
import task15_transportation_threads.cargo.domain.CargoType;
import task15_transportation_threads.cargo.domain.ClothersCargo;
import task15_transportation_threads.cargo.domain.FoodCargo;
import task15_transportation_threads.carrier.domain.Carrier;
import task15_transportation_threads.carrier.domain.CarrierType;
import task15_transportation_threads.common.business.exception.checked.InitStorageException;
import task15_transportation_threads.common.solutions.utils.FileUtils;
import task15_transportation_threads.common.solutions.utils.JavaUtilDateUtils;
import task15_transportation_threads.common.solutions.utils.xml.dom.XmlDomUtils;
import task15_transportation_threads.storage.initor.fileinitor.BaseFileInitor;
import task15_transportation_threads.transportation.domain.Transportation;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static task15_transportation_threads.common.solutions.utils.xml.dom.XmlDomUtils.getOnlyElement;
import static task15_transportation_threads.common.solutions.utils.xml.dom.XmlDomUtils.getOnlyElementTextContent;

public class ConcurrentInitor extends BaseFileInitor {

    private static final String FILE = "task15_transportation_threads/init-data.xml";
    private static Map<String, Cargo> cargoMap;
    private static Map<String, Carrier> carrierMap;
    private static List<BaseFileInitor.ParsedTransportation> transportations;
    private boolean isError = false;

    public class CargoesParser implements Runnable {

        @Override
        public void run() {
            File file = null;
            try {
                file = getFileWithInitData();
                Document document = XmlDomUtils.getDocument(file);

                Element root = getOnlyElement(document, "cargoes");
                NodeList xmlCargoes = root.getElementsByTagName("cargo");

                cargoMap = new LinkedHashMap<>();

                for (int i = 0; i < xmlCargoes.getLength(); i++) {
                    Map.Entry<String, Cargo> parsedData = null;
                    try {
                        parsedData = parseCargo(xmlCargoes.item(i));
                        cargoMap.put(parsedData.getKey(), parsedData.getValue());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                isError = true;
                e.printStackTrace();
            } finally {
                if (file != null) {
                    file.delete();
                }
            }
        }

        private Map.Entry<String, Cargo> parseCargo(Node cargoXml) throws ParseException {
            Element cargoElem = ((Element) cargoXml);

            String id = cargoElem.getAttribute("id");
            CargoType cargoType = CargoType.valueOf(cargoElem.getAttribute("type"));

            Cargo cargo;
            if (CargoType.FOOD.equals(cargoType)) {
                FoodCargo foodCargo = new FoodCargo();
                Date expirationDate = JavaUtilDateUtils
                        .valueOf(getOnlyElementTextContent(cargoElem, "expirationDate"));
                foodCargo.setExpirationDate(expirationDate);
                foodCargo.setStoreTemperature(
                        Integer.parseInt(getOnlyElementTextContent(cargoElem, "storeTemperature")));
                cargo = foodCargo;
            } else {
                ClothersCargo clothersCargo = new ClothersCargo();
                clothersCargo.setMaterial(getOnlyElementTextContent(cargoElem, "material"));
                clothersCargo.setSize(getOnlyElementTextContent(cargoElem, "size"));
                cargo = clothersCargo;
            }

            cargo.setName(getOnlyElementTextContent(cargoElem, "name"));
            cargo.setWeight(Integer.parseInt(getOnlyElementTextContent(cargoElem, "weight")));

            return new AbstractMap.SimpleEntry<>(id, cargo);
        }
    }

    public class CarriersParser implements Runnable {

        @Override
        public void run() {
            File file = null;
            try {
                file = getFileWithInitData();
                Document document = XmlDomUtils.getDocument(file);

                carrierMap = new LinkedHashMap<>();

                Element root = getOnlyElement(document, "carriers");
                NodeList xmlCarriers = root.getElementsByTagName("carrier");

                for (int i = 0; i < xmlCarriers.getLength(); i++) {
                    Map.Entry<String, Carrier> parsedData = parseCarrier(xmlCarriers.item(i));
                    carrierMap.put(parsedData.getKey(), parsedData.getValue());
                }
            } catch (Exception e) {
                isError = true;
                e.printStackTrace();
            } finally {
                if (file != null) {
                    file.delete();
                }
            }
        }

        private Map.Entry<String, Carrier> parseCarrier(Node cargoXml) {
            Element carrierElement = ((Element) cargoXml);

            String id = carrierElement.getAttribute("id");
            Carrier carrier = new Carrier();

            carrier.setName(getOnlyElementTextContent(carrierElement, "name"));
            carrier.setAddress(getOnlyElementTextContent(carrierElement, "address"));
            String carrierTypeStr = getOnlyElementTextContent(carrierElement, "type");
            carrier.setCarrierType(CarrierType.valueOf(carrierTypeStr));

            return new AbstractMap.SimpleEntry<>(id, carrier);
        }
    }

    public class TransportationsParser implements Runnable {

        @Override
        public void run() {
            File file = null;
            try {
                file = getFileWithInitData();
                Document document = XmlDomUtils.getDocument(file);
                transportations = new ArrayList<>();

                Element root = getOnlyElement(document, "transportations");
                NodeList xmlTransportations = root.getElementsByTagName("transportation");

                for (int i = 0; i < xmlTransportations.getLength(); i++) {
                    BaseFileInitor.ParsedTransportation parsedData = null;
                    parsedData = parseTransportationData(xmlTransportations.item(i));
                    transportations.add(parsedData);
                }
            } catch (Exception e) {
                isError = true;
                e.printStackTrace();
            } finally {
                if (file != null) {
                    file.delete();
                }
            }
        }

        private BaseFileInitor.ParsedTransportation parseTransportationData(Node transportationXml)
                throws ParseException {
            Element transportationElement = ((Element) transportationXml);

            BaseFileInitor.ParsedTransportation result = new BaseFileInitor.ParsedTransportation();
            String cargoRef = transportationElement.getAttribute("cargoref");
            result.setCargoRef(cargoRef);
            String carrierRef = transportationElement.getAttribute("carrierref");
            result.setCarrierRef(carrierRef);

            Transportation transportation = new Transportation();
            transportation.setBillTo(getOnlyElementTextContent(transportationElement, "billto"));
            transportation.setDescription(getOnlyElementTextContent(transportationElement, "description"));
            String beginDataStr = getOnlyElementTextContent(transportationElement, "transportationBeginDate");
            transportation.setTransportationBeginDate(JavaUtilDateUtils.valueOf(beginDataStr));
            result.setTransportation(transportation);

            return result;
        }
    }

    @Override
    public void initStorage() throws InitStorageException {
        List<Thread> threadsList = new ArrayList<>();

        threadsList.add(new Thread(new CargoesParser()));
        threadsList.add(new Thread(new CarriersParser()));
        threadsList.add(new Thread(new TransportationsParser()));

        for (Thread thread : threadsList) {
            thread.start();
        }

        for (Thread thread : threadsList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                isError = true;
                e.printStackTrace();
            }
        }

        if (isError) {
            throw new InitStorageException("Concurrent initialization failed");
        }

        setReferencesBetweenEntities(cargoMap, carrierMap, transportations);

        persistCargoesAndCarriers(cargoMap.values(), carrierMap.values());
        List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
        persistTransportations(transportationList);
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils.createFileFromResource("init-data", "lesson15", FILE);
    }
}
