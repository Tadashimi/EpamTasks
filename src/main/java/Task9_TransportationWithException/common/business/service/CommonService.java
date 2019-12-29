package  Task9_TransportationWithException.common.business.service;

import  Task9_TransportationWithException.common.solutions.exception.TransportationException;

public interface CommonService {

    boolean deleteById(Long id) throws TransportationException;

    void printAll();

}
