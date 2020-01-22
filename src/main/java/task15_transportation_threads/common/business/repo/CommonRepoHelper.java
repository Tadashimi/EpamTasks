package task15_transportation_threads.common.business.repo;

import task15_transportation_threads.common.business.domain.BaseEntity;

public final class CommonRepoHelper {

    private CommonRepoHelper() {

    }

    public static Integer findEntityIndexInArrayStorageById(BaseEntity[] data, long entityId) {
        for (int i = 0; i < data.length; i++) {
            if (Long.valueOf(entityId).equals(data[i].getId())) {
                return i;
            }
        }

        return null;
    }
}
