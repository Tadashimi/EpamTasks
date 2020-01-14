package task11_transportation_txt_parser.common.business.repo;

import task11_transportation_txt_parser.common.business.domain.BaseEntity;

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
