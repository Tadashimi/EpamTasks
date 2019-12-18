package Task7_TransportationWithCollections.carrier.repo;

import Task7_TransportationWithCollections.carrier.domain.Carrier;
import Task7_TransportationWithCollections.common.business.repo.CommonRepo;

import java.util.List;

public interface CarrierRepo extends CommonRepo {

    void add(Carrier carrier);

    Carrier getById(long id);

    List<Carrier> getByName(String name);

}
