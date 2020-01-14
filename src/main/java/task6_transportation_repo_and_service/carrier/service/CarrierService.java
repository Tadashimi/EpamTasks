package task6_transportation_repo_and_service.carrier.service;

import task6_transportation_repo_and_service.carrier.domain.Carrier;

public interface CarrierService {

    void addCarrier(Carrier carrier);

    Carrier getById(long id);

    Carrier[] getByName(String name);

    void deleteById(long id);
}
