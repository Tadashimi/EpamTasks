package Task6_TransportationWithRepoAndService.transportation.repo;

import Task6_TransportationWithRepoAndService.storage.Storage;
import Task6_TransportationWithRepoAndService.transportation.domain.Transportation;

public class TransportationRepoImpl implements TransportationRepo {

    @Override
    public void addTransportation(Transportation transportation) {
        Storage.addTransportation(transportation);
    }

    @Override
    public Transportation getById(long id) {
        return Storage.getTransportationById(id);
    }

    @Override
    public void deleteById(long id) {
        Storage.deleteTransportationById(id);
    }
}
