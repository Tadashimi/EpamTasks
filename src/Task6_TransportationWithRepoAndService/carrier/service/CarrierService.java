package Task6_TransportationWithRepoAndService.carrier.service;

import Task6_TransportationWithRepoAndService.carrier.domain.Carrier;

public interface CarrierService {

    void addCarrier(Carrier carrier);

    Carrier getById(long id);

    Carrier[] getByName(String name);

    void deleteById(long id);
}
