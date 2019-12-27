package main.java.Task7_TransportationWithCollections.carrier.repo;

import main.java.Task7_TransportationWithCollections.carrier.domain.Carrier;
import main.java.Task7_TransportationWithCollections.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo {

    void add(Carrier carrier);

    Carrier getById(long id);

    Carrier[] getByName(String name);

}
