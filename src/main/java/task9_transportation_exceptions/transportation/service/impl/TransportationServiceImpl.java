package  task9_transportation_exceptions.transportation.service.impl;

import  task9_transportation_exceptions.cargo.domain.Cargo;
import  task9_transportation_exceptions.cargo.repo.CargoRepo;
import  task9_transportation_exceptions.carrier.domain.Carrier;
import  task9_transportation_exceptions.carrier.repo.CarrierRepo;
import  task9_transportation_exceptions.transportation.domain.Transportation;
import  task9_transportation_exceptions.transportation.repo.TransportationRepo;
import  task9_transportation_exceptions.transportation.service.TransportationService;

import java.util.List;

public class TransportationServiceImpl implements TransportationService {

    private TransportationRepo transportationRepo;
    private CargoRepo cargoRepo;
    private CarrierRepo carrierRepo;

    public TransportationServiceImpl(
            TransportationRepo transportationRepo, CargoRepo cargoRepo, CarrierRepo carrierRepo) {
        this.transportationRepo = transportationRepo;
        this.cargoRepo = cargoRepo;
        this.carrierRepo = carrierRepo;
    }

    @Override
    public boolean deleteById(Long id) {
        Transportation transportation = transportationRepo.getById(id);
        if (transportation != null) {
            Cargo cargo = transportation.getCargo();
            cargoRepo.removeTransportation(cargo, transportation);

            Carrier carrier = transportation.getCarrier();
            carrierRepo.removeTransportation(carrier, transportation);
        }

        return transportationRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        List<Transportation> allTransportations = transportationRepo.getAll();
        for (Transportation transportation : allTransportations) {
            System.out.println(transportation);
        }
    }

    @Override
    public void add(Transportation transportation) {
        transportationRepo.add(transportation);
    }

    @Override
    public Transportation getById(Long id) {
        return transportationRepo.getById(id);
    }

    @Override
    public List<Transportation> getAll() {
        return transportationRepo.getAll();
    }

    @Override
    public void update(Transportation transportation) {
        if (transportation != null) {
            transportationRepo.update(transportation);
        }
    }

}
