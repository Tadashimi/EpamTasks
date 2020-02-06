package task20_transportation_data_base.carrier.repo;

import task20_transportation_data_base.carrier.domain.Carrier;
import task20_transportation_data_base.common.business.repo.CommonRepo;

import java.util.Optional;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

    Optional<Carrier> getByIdFetchingTransportations(long id);

    Carrier[] findByName(String name);

}
