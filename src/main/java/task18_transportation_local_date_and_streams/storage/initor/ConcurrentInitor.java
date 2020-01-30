package task18_transportation_local_date_and_streams.storage.initor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import task18_transportation_local_date_and_streams.cargo.domain.Cargo;
import task18_transportation_local_date_and_streams.cargo.domain.CargoType;
import task18_transportation_local_date_and_streams.cargo.domain.ClothersCargo;
import task18_transportation_local_date_and_streams.cargo.domain.FoodCargo;
import task18_transportation_local_date_and_streams.carrier.domain.Carrier;
import task18_transportation_local_date_and_streams.carrier.domain.CarrierType;
import task18_transportation_local_date_and_streams.common.business.exception.checked.InitStorageException;
import task18_transportation_local_date_and_streams.common.solutions.utils.FileUtils;
import task18_transportation_local_date_and_streams.common.solutions.utils.JavaUtilDateUtils;
import task18_transportation_local_date_and_streams.common.solutions.utils.xml.dom.XmlDomUtils;
import task18_transportation_local_date_and_streams.storage.initor.fileinitor.BaseFileInitor;
import task18_transportation_local_date_and_streams.transportation.domain.Transportation;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

import static task18_transportation_local_date_and_streams.common.solutions.utils.xml.dom.XmlDomUtils.getOnlyElement;
import static task18_transportation_local_date_and_streams.common.solutions.utils.xml.dom.XmlDomUtils.getOnlyElementTextContent;

public class ConcurrentInitor extends BaseFileInitor {

    private static final String FILE = "task18_transportation_zoned_date_time/init-data.xml";
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
                    Map.Entry<String, Cargo> parsedData;
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
                LocalDate expirationDate = JavaUtilDateUtils
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
                    BaseFileInitor.ParsedTransportation parsedData;
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

        threadsList.forEach(Thread::start);

        threadsList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                isError = true;
                e.printStackTrace();
            }
        });

        if (isError) {
            throw new InitStorageException("Concurrent initialization failed");
        }

        setReferencesBetweenEntities(cargoMap, carrierMap, transportations);

        persistCargoesAndCarriers(cargoMap.values(), carrierMap.values());
        List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
        persistTransportations(transportationList);
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils.createFileFromResource("init-data", "lesson16", FILE);
    }
}
