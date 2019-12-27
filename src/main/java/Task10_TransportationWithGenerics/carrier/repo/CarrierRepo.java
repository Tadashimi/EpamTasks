package main.java.Task10_TransportationWithGenerics.carrier.repo;

import main.java.Task10_TransportationWithGenerics.carrier.domain.Carrier;
import main.java.Task10_TransportationWithGenerics.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier> {

    Carrier getByIdFetchingTransportations(long id);

    Carrier[] findByName(String name);

}
