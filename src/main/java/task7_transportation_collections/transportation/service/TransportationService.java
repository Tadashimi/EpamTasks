package task7_transportation_collections.transportation.service;

import task7_transportation_collections.common.business.service.CommonService;
import task7_transportation_collections.transportation.domain.Transportation;

public interface TransportationService extends CommonService {

    void add(Transportation transportation);

    Transportation getById(Long id);

}
