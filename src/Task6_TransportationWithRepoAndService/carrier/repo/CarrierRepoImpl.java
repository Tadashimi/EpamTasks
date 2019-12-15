package Task6_TransportationWithRepoAndService.carrier.repo;

import Task6_TransportationWithRepoAndService.carrier.domain.Carrier;
import Task6_TransportationWithRepoAndService.common.utils.ArrayUtils;
import Task6_TransportationWithRepoAndService.storage.Storage;

public class CarrierRepoImpl implements CarrierRepo {

    @Override
    public void addCarrier(Carrier carrier) {
        Storage.addCarrier(carrier);
    }

    @Override
    public Carrier getById(long id) {
        return Storage.getCarrierById(id);
    }

    @Override
    public Carrier[] getByName(String name) {
        Carrier[] result = Storage.getCarriersByName(name);
        ArrayUtils.trimArray(result);
        return result;
    }

    @Override
    public void deleteById(long id) {
        Storage.deleteCarrierById(id);
    }
}
