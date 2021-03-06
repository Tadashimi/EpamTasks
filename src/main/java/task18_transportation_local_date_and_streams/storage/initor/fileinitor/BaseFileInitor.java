package task18_transportation_local_date_and_streams.storage.initor.fileinitor;


import task18_transportation_local_date_and_streams.application.serviceholder.ServiceHolder;
import task18_transportation_local_date_and_streams.cargo.domain.Cargo;
import task18_transportation_local_date_and_streams.carrier.domain.Carrier;
import task18_transportation_local_date_and_streams.common.business.exception.checked.InitStorageException;
import task18_transportation_local_date_and_streams.storage.initor.StorageInitor;
import task18_transportation_local_date_and_streams.transportation.domain.Transportation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class BaseFileInitor implements StorageInitor {

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

    protected void persistCargoesAndCarriers(Collection<Cargo> cargoes, Collection<Carrier> carriers) throws InitStorageException {
        Thread threadPersistCargoes = new Thread(() -> persistCargos(cargoes));

        Thread threadPersistCarriers = new Thread(() -> persistCarriers(carriers));

        threadPersistCargoes.start();
        threadPersistCarriers.start();

        try {
            threadPersistCargoes.join();
            threadPersistCarriers.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new InitStorageException("Error while threading work");
        }
    }

    protected void persistCargos(Collection<Cargo> cargos) {
        cargos.forEach(ServiceHolder.getInstance().getCargoService()::save);
    }

    protected void persistCarriers(Collection<Carrier> carriers) {
        carriers.forEach(ServiceHolder.getInstance().getCarrierService()::save);
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
        transportations.forEach(ServiceHolder.getInstance().getTransportationService()::save);
    }

    public static class ParsedTransportation {
        private String cargoRef;
        private String carrierRef;
        private Transportation transportation;

        public String getCargoRef() {
            return cargoRef;
        }

        public void setCargoRef(String cargoRef) {
            this.cargoRef = cargoRef;
        }

        public String getCarrierRef() {
            return carrierRef;
        }

        public void setCarrierRef(String carrierRef) {
            this.carrierRef = carrierRef;
        }

        public Transportation getTransportation() {
            return transportation;
        }

        public void setTransportation(
                Transportation transportation) {
            this.transportation = transportation;
        }
    }

}
