package Task9_TransportationWithException.carrier.repo;

import Task9_TransportationWithException.carrier.domain.Carrier;
import Task9_TransportationWithException.common.business.repo.CommonRepo;
import Task9_TransportationWithException.transportation.domain.Transportation;

import java.util.List;

public interface CarrierRepo extends CommonRepo {

    void add(Carrier carrier);

    Carrier getById(long id);

    Carrier[] findByName(String name);

    List<Carrier> getAll();

    void update(Carrier carrier);

    default void removeTransportation(Carrier carrier, Transportation transportation) {
        if (carrier !=  null) {
            carrier.removeTransportation(transportation);
        }
    }
}
