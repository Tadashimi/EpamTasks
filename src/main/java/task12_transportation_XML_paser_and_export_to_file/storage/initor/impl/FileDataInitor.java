package task12_transportation_XML_paser_and_export_to_file.storage.initor.impl;

import task12_transportation_XML_paser_and_export_to_file.application.serviceholder.ServiceHolder;
import task12_transportation_XML_paser_and_export_to_file.cargo.domain.Cargo;
import task12_transportation_XML_paser_and_export_to_file.carrier.domain.Carrier;
import task12_transportation_XML_paser_and_export_to_file.storage.initor.StorageInitor;
import task12_transportation_XML_paser_and_export_to_file.transportation.domain.Transportation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class FileDataInitor implements StorageInitor {

    protected static class ParsedTransportation {
        protected String cargoRef;
        protected String carrierRef;
        protected Transportation transportation;
    }

    protected void persistCargos(Collection<Cargo> cargos) {
        for (Cargo cargo : cargos) {
            ServiceHolder.getInstance().getCargoService().save(cargo);
        }
    }

    protected void persistCarriers(Collection<Carrier> carriers) {
        for (Carrier carrier : carriers) {
            ServiceHolder.getInstance().getCarrierService().save(carrier);
        }
    }

    protected List<Transportation> getTransportationsFromParsedObject(
            List<ParsedTransportation> transportations) {
        List<Transportation> result = new ArrayList<>();
        for (ParsedTransportation transportation : transportations) {
            result.add(transportation.transportation);
        }

        return result;
    }

    protected void persistTransportations(List<Transportation> transportations) {
        for (Transportation transportation : transportations) {
            ServiceHolder.getInstance().getTransportationService().save(transportation);
        }
    }

    protected void setReferencesBetweenEntities(Map<String, Cargo> cargoMap,
                                                Map<String, Carrier> carrierMap, List<ParsedTransportation> parsedTransportations) {

        for (ParsedTransportation parsedTransportation : parsedTransportations) {
            Cargo cargo = cargoMap.get(parsedTransportation.cargoRef);
            Carrier carrier = carrierMap.get(parsedTransportation.carrierRef);
            Transportation transportation = parsedTransportation.transportation;

            if (cargo != null) {
                List<Transportation> transportations =
                        cargo.getTransportations() == null ? new ArrayList<>() : cargo.getTransportations();
                transportations.add(transportation);
                transportation.setCargo(cargo);
                cargo.setTransportations(transportations);
            }

            if (carrier != null) {
                List<Transportation> transportations =
                        carrier.getTransportations() == null ? new ArrayList<>() : carrier.getTransportations();
                transportations.add(transportation);
                transportation.setCarrier(carrier);
                carrier.setTransportations(transportations);
            }
        }
    }
}
