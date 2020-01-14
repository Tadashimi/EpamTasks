package  task9_transportation_exceptions.common.business.service;

import  task9_transportation_exceptions.common.solutions.exception.TransportationException;

public interface CommonService {

    boolean deleteById(Long id) throws TransportationException;

    void printAll();

}
