package  task8_transportation_update_and_compare.transportation.service;

import  task8_transportation_update_and_compare.common.business.service.CommonService;
import  task8_transportation_update_and_compare.transportation.domain.Transportation;

public interface TransportationService extends CommonService {

    void add(Transportation transportation);

    Transportation getById(Long id);

    Transportation update(Long id, Transportation transportation);

}
