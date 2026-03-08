### Exercise A — Singleton Metrics Registry (Answer)

**Problem in the question code**

- `MetricsRegistry` was **not** a real singleton:
  - public constructor,
  - lazy `getInstance()` without proper synchronization,
  - no protection against reflection or serialization creating extra instances.
- `MetricsLoader` used `new MetricsRegistry()` directly, so multiple registries could exist.
- Under concurrency, different threads could see different registry instances; global counters were not truly global.

**How the answer solves it**

- Implement a proper, **thread-safe, lazy-initialized singleton**:
  - `private static volatile MetricsRegistry INSTANCE`,
  - `private MetricsRegistry()` that throws if called more than once (blocks reflection-based second construction),
  - `getInstance()` uses double-checked locking to create the instance safely and lazily.
- Ensure **serialization safety**:
  - implement `readResolve()` and delegate to `getInstance()` so deserialization returns the singleton.
- Keep metrics API the same:
  - `setCount`, `increment`, `getCount`, `getAll` remain synchronized, acting on the single `counters` map.
- Fix `MetricsLoader`:
  - obtains the instance via `MetricsRegistry.getInstance()` instead of `new MetricsRegistry()`,
  - populates the global registry from `metrics.properties`.
- `App` compares the identity of `loaded` vs `global` to demonstrate they are the same object, then prints counters.

---

### Design – before vs after

```mermaid
flowchart TD
    MetricsLoader -->|"new MetricsRegistry()"| MetricsRegistry
    App -->|"MetricsRegistry.getInstance()"| MetricsRegistry

    note right of MetricsRegistry
      Public ctor
      Racy lazy getInstance()
      Reflection + serialization
      can create extra instances
    end note
```

```mermaid
flowchart TD
    MetricsLoader -->|"MetricsRegistry.getInstance()"| MetricsRegistrySingleton
    App -->|"MetricsRegistry.getInstance()"| MetricsRegistrySingleton

    subgraph MetricsRegistrySingleton[MetricsRegistry (Singleton)]
      direction TB
      INSTANCE[(static volatile INSTANCE)]
      Ctors[private ctor + reflection guard]
      ReadResolve[readResolve() -> getInstance()]
    end
```

Now:

- All callers inside the JVM share **one** `MetricsRegistry` instance.
- Reflection cannot create a second instance after the first is constructed.
- Serialization/deserialization returns the existing singleton.
- `MetricsLoader` and `App` both work with the same global counters as intended.

