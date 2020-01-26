package task16_transportation_lambda.storage;

import java.util.concurrent.atomic.AtomicLong;

public final class IdGenerator {

    private IdGenerator() {
    }

    private static AtomicLong id = new AtomicLong(0);

    public static Long generateId() {
        return id.incrementAndGet();
    }
}
