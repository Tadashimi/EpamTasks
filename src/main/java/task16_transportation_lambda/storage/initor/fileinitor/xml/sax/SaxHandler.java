package task16_transportation_lambda.storage.initor.fileinitor.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import task16_transportation_lambda.cargo.domain.Cargo;
import task16_transportation_lambda.cargo.domain.CargoType;
import task16_transportation_lambda.cargo.domain.ClothersCargo;
import task16_transportation_lambda.cargo.domain.FoodCargo;
import task16_transportation_lambda.carrier.domain.Carrier;
import task16_transportation_lambda.carrier.domain.CarrierType;
import task16_transportation_lambda.common.solutions.utils.JavaUtilDateUtils;
import task16_transportation_lambda.storage.initor.fileinitor.BaseFileInitor;
import task16_transportation_lambda.transportation.domain.Transportation;

import java.text.ParseException;
import java.util.*;

public class SaxHandler extends DefaultHandler {
    private StringBuilder content = new StringBuilder();

    private Map<String, Cargo> cargoMap = new LinkedHashMap<>();
    private Cargo currentCargo;

    private Map<String, Carrier> carrierMap = new LinkedHashMap<>();
    private Carrier currentCarrier;

    private List<BaseFileInitor.ParsedTransportation> transportations = new ArrayList<>();
    private Transportation currentTransportation;

    private Deque<String> tagStack = new ArrayDeque<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        resetStringBuilder();
        tagStack.add(qName);

        switch (stackAsStringPath()) {
            case CargoPaths.CARGO: {
                currentCargo = getCargo(attributes);
                cargoMap.put(attributes.getValue("id"), currentCargo);
                break;
            }

            case CarrierPaths.CARRIER: {
                currentCarrier = new Carrier();
                carrierMap.put(attributes.getValue("id"), currentCarrier);
                break;
            }

            case TransportationPaths.TRANSPORTATION: {
                BaseFileInitor.ParsedTransportation parsedTransportation = getParsedTransportation(attributes);
                currentTransportation = parsedTransportation.getTransportation();
                transportations.add(parsedTransportation);
                break;
            }
        }
    }

    private Cargo getCargo(Attributes attributes) {
        CargoType type = CargoType.valueOf(attributes.getValue("type"));
        switch (type) {
            case FOOD:
                return new FoodCargo();
            case CLOTHERS:
                return new ClothersCargo();
            default: {
                throw new RuntimeException("Unknown cargo type '" + type + "'");
            }
        }
    }

    private BaseFileInitor.ParsedTransportation getParsedTransportation(Attributes attributes) {
        String cargoRef = attributes.getValue("cargoref");
        String carrierRef = attributes.getValue("carrierref");

        BaseFileInitor.ParsedTransportation result = new BaseFileInitor.ParsedTransportation();
        result.setCargoRef(cargoRef);
        result.setCarrierRef(carrierRef);
        result.setTransportation(new Transportation());

        return result;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length);
        content.append(value.replaceAll("\\n", ""));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            String dataAsStr = this.content.toString();
            String path = stackAsStringPath();

            if (CargoPaths.isCargoPath(path)) {
                fillCargoWithData(currentCargo, path, dataAsStr);
            } else if (CarrierPaths.isCarrierPath(path)) {
                fillCarrierWithData(currentCarrier, path, dataAsStr);
            } else if (TransportationPaths.isTransportationPath(path)) {
                fillTransportationWithData(currentTransportation, path, dataAsStr);
            }
        } catch (Exception e) {
            throw new SAXException(e);
        }

        tagStack.removeLast();
    }

    private void fillCargoWithData(Cargo cargo, String tagPath, String content) throws ParseException {
        if (cargo.getClass().equals(ClothersCargo.class)) {
            fillClotherCargoWithData((ClothersCargo) cargo, tagPath, content);
        } else if (cargo.getClass().equals(FoodCargo.class)) {
            fillFoodCargoWithData((FoodCargo) cargo, tagPath, content);
        }

        switch (tagPath) {
            case CargoPaths.NAME: {
                cargo.setName(content);
                break;
            }
            case CargoPaths.WEIGHT: {
                cargo.setWeight(Integer.parseInt(content));
                break;
            }
        }
    }

    private void fillFoodCargoWithData(FoodCargo foodCargo, String tagPath, String content) throws ParseException {
        switch (tagPath) {
            case CargoPaths.EXPIRATION_DATE: {
                foodCargo.setExpirationDate(JavaUtilDateUtils.valueOf(content));
                break;
            }
            case CargoPaths.STORE_TEMPERATURE: {
                foodCargo.setStoreTemperature(Integer.parseInt(content));
                break;
            }
        }
    }

    private void fillClotherCargoWithData(ClothersCargo clothersCargo, String tagPath, String content) {
        switch (tagPath) {
            case CargoPaths.SIZE: {
                clothersCargo.setSize(content);
                break;
            }

            case CargoPaths.MATERIAL: {
                clothersCargo.setMaterial(content);
                break;
            }
        }
    }

    private void fillCarrierWithData(Carrier carrier, String tagPath, String content) {
        switch (tagPath) {
            case CarrierPaths.NAME: {
                carrier.setName(content);
                break;
            }
            case CarrierPaths.ADDRESS: {
                carrier.setAddress(content);
                break;
            }
            case CarrierPaths.TYPE: {
                carrier.setCarrierType(CarrierType.valueOf(content));
                break;
            }
        }
    }

    private void fillTransportationWithData(Transportation transportation, String tagPath, String content) throws ParseException {
        switch (tagPath) {
            case TransportationPaths.BILLTO: {
                transportation.setBillTo(content);
                break;
            }
            case TransportationPaths.DESCRIPTION: {
                transportation.setDescription(content);
                break;
            }
            case TransportationPaths.TRANSPORTATION_BEGIN_DATE: {
                transportation.setTransportationBeginDate(JavaUtilDateUtils.valueOf(content));
                break;
            }
        }
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        super.ignorableWhitespace(ch, start, length);
    }

    public Map<String, Cargo> getCargoMap() {
        return cargoMap;
    }

    public Map<String, Carrier> getCarrierMap() {
        return carrierMap;
    }

    public List<BaseFileInitor.ParsedTransportation> getTransportations() {
        return transportations;
    }

    private void resetStringBuilder() {
        content.setLength(0);
    }

    private String stackAsStringPath() {
        StringBuilder fullPath = new StringBuilder();

        Iterator<String> iter = tagStack.iterator();
        while (iter.hasNext()) {
            String tag = iter.next();
            fullPath.append(tag);

            if (iter.hasNext()) {
                fullPath.append("/");
            }
        }

        return fullPath.toString();
    }
}
