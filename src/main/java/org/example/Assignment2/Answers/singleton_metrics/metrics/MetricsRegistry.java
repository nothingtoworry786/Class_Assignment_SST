package org.example.Assignment2.Answers.singleton_metrics.metrics;

import java.io.ObjectStreamException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static volatile MetricsRegistry INSTANCE;
    private static boolean constructed = false;

    private final Map<String, Long> counters = new HashMap<>();

    private MetricsRegistry() {
        if (constructed) {
            throw new IllegalStateException("MetricsRegistry instance already created");
        }
        constructed = true;
    }

    public static MetricsRegistry getInstance() {
        MetricsRegistry result = INSTANCE;
        if (result == null) {
            synchronized (MetricsRegistry.class) {
                result = INSTANCE;
                if (result == null) {
                    result = INSTANCE = new MetricsRegistry();
                }
            }
        }
        return result;
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

    @Serial
    private Object readResolve() throws ObjectStreamException {
        return getInstance();
    }
}
