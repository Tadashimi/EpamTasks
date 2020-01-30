package task18_transportation_local_date_and_streams.storage;

import java.util.concurrent.atomic.AtomicLong;

public final class IdGenerator {

    private IdGenerator() {
    }

    private static AtomicLong id = new AtomicLong(0);

    public static Long generateId() {
        return id.incrementAndGet();
    }
}
