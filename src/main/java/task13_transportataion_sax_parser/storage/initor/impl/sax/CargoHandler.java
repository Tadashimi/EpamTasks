package task13_transportataion_sax_parser.storage.initor.impl.sax;

import task13_transportataion_sax_parser.cargo.domain.Cargo;
import task13_transportataion_sax_parser.cargo.domain.CargoType;
import task13_transportataion_sax_parser.cargo.domain.ClothersCargo;
import task13_transportataion_sax_parser.cargo.domain.FoodCargo;
import task13_transportataion_sax_parser.common.solutions.utils.JavaUtilDataUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class CargoHandler extends DefaultHandler {
    private static final String CARGOES = "cargoes";
    private static final String CARGO = "cargo";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String WEIGHT = "weight";
    private static final String EXPIRATION_DATE = "expirationDate";
    private static final String STORE_TEMPERATURE = "storeTemperature";
    private static final String SIZE = "size";
    private static final String MATERIAL = "material";

    private String elementValue;
    private Map<String, Cargo> cargoMap;
    private Cargo cargo;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case CARGOES:
                cargoMap = new HashMap<>();
                break;
            case CARGO:
                CargoType type = CargoType.valueOf(attributes.getValue("type"));
                cargo = createCargoByType(type);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case ID:
                if (cargo != null) {
                    cargoMap.put(elementValue, cargo);
                }
                break;
            case NAME:
                if (cargo != null) {
                    cargo.setName(elementValue);
                }
                break;
            case WEIGHT:
                cargo.setWeight(Integer.valueOf(elementValue));
                break;
            case EXPIRATION_DATE:
                try {
                    ((FoodCargo) cargo).setExpirationDate(JavaUtilDataUtils.valueOf(elementValue));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case STORE_TEMPERATURE:
                ((FoodCargo) cargo).setStoreTemperature(Integer.valueOf(elementValue));
                break;
            case SIZE:
                ((ClothersCargo) cargo).setSize(elementValue);
                break;
            case MATERIAL:
                ((ClothersCargo) cargo).setMaterial(elementValue);
                break;
            case CARGO:
                cargo = null;
                break;
        }
    }

    private Cargo createCargoByType(CargoType type) {
        switch (type) {
            case FOOD:
                return new FoodCargo();
            default:
                return new ClothersCargo();
        }
    }

    public Map<String, Cargo> getCargoMap() {
        return cargoMap;
    }
}
