package main.java.Task8_TransportationWithUpdateAndCompare.carrier.repo;

import main.java.Task8_TransportationWithUpdateAndCompare.carrier.domain.Carrier;
import main.java.Task8_TransportationWithUpdateAndCompare.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo {

    void add(Carrier carrier);

    Carrier getById(long id);

    Carrier[] getByName(String name);

    Carrier[] getAll();

    Carrier update(long id, Carrier carrier);
}
