package main.java.Task6_TransportationWithRepoAndService.carrier.repo;

import main.java.Task6_TransportationWithRepoAndService.carrier.domain.Carrier;
import main.java.Task6_TransportationWithRepoAndService.common.repo.BaseRepo;

public interface CarrierRepo extends BaseRepo {

    void add(Carrier carrier);

    Carrier getById(long id);

    Carrier[] getByName(String name);
}
