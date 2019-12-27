package Task11_TransportationWithFileReader.transportation.service;

import Task11_TransportationWithFileReader.transportation.domain.Transportation;
import Task11_TransportationWithFileReader.transportation.repo.TransportationRepo;

import java.util.List;

public class TransportationServiceImpl implements TransportationService {

    private TransportationRepo transportationRepo;

    public TransportationServiceImpl(
            TransportationRepo transportationRepo) {
        this.transportationRepo = transportationRepo;
    }

    @Override
    public boolean deleteById(Long id) {
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

    @Override
    public void addAll(List<Transportation> transportations) {
        if (transportations != null) {
            transportationRepo.addAll(transportations);
        }
    }
}
