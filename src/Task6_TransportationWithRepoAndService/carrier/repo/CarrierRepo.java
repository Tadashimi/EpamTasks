package Task6_TransportationWithRepoAndService.carrier.repo;

import Task6_TransportationWithRepoAndService.carrier.domain.Carrier;

public interface CarrierRepo {

    void addCarrier(Carrier carrier);

    Carrier getById(long id);

    Carrier[] getByName(String name);

    void deleteById(long id);
}
