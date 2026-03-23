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

**Before (question code)** — two ways to reach `MetricsRegistry`, so “singleton” is not guaranteed.

```mermaid
%%{init: {"theme": "base", "themeVariables": {"fontFamily": "system-ui, Segoe UI, sans-serif", "primaryColor": "#E3F2FD", "primaryBorderColor": "#1565C0", "primaryTextColor": "#0D47A1", "lineColor": "#78909C"}}}%%
flowchart TB
    subgraph before["Before — broken"]
        direction LR
        ML[MetricsLoader] -->|"new MetricsRegistry()"| MR[(MetricsRegistry)]
        AP[App] -->|"getInstance()"| MR
    end

    subgraph problems["Why it breaks"]
        direction TB
        P1[Public constructor]
        P2[Racy lazy getInstance]
        P3[Reflection / deserialization]
    end

    MR -.-> P1
    MR -.-> P2
    MR -.-> P3

    classDef caller fill:#E3F2FD,stroke:#1565C0,stroke-width:2px,color:#0D47A1
    classDef registry fill:#FFF3E0,stroke:#E65100,stroke-width:2px,color:#BF360C
    classDef risk fill:#FFEBEE,stroke:#C62828,stroke-width:1px,color:#B71C1C
    class ML,AP caller
    class MR registry
    class P1,P2,P3 risk
```



**After (answer)** — one shared instance; techniques listed once, tied to the same `MetricsRegistry`.

```mermaid
%%{init: {"theme": "base", "themeVariables": {"fontFamily": "system-ui, Segoe UI, sans-serif", "primaryColor": "#E8F5E9", "primaryBorderColor": "#2E7D32", "primaryTextColor": "#1B5E20", "lineColor": "#689F38"}}}%%
flowchart TB
    subgraph access["After — single global registry"]
        direction LR
        ML2[MetricsLoader]
        AP2[App]
        ML2 -->|"getInstance()"| MR2[(MetricsRegistry)]
        AP2 -->|"getInstance()"| MR2
    end

    subgraph impl["Singleton implementation"]
        DET["volatile INSTANCE + double-checked locking<br/>private ctor + reflection guard<br/>readResolve() → getInstance()"]
    end

    MR2 --> DET

    classDef caller fill:#E8F5E9,stroke:#2E7D32,stroke-width:2px,color:#1B5E20
    classDef registry fill:#C8E6C9,stroke:#1B5E20,stroke-width:2px,color:#0D3D0D
    classDef detail fill:#F1F8E9,stroke:#558B2F,stroke-width:1px,color:#33691E
    class ML2,AP2 caller
    class MR2 registry
    class DET detail
```



> **Tip:** Mermaid *flowcharts* don’t support `note right of …` (that’s for sequence diagrams). Use `subgraph` or styled nodes instead.

Now:

- All callers inside the JVM share **one** `MetricsRegistry` instance.
- Reflection cannot create a second instance after the first is constructed.
- Serialization/deserialization returns the existing singleton.
- `MetricsLoader` and `App` both work with the same global counters as intended.

