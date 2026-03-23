# Assignment4 — Pen (Class Diagram)

```mermaid
classDiagram

classDef interface fill:#FFF3E0,stroke:#E65100,color:#BF360C
classDef impl fill:#E3F2FD,stroke:#1565C0,color:#0D47A1
classDef core fill:#F3E5F5,stroke:#6A1B9A,color:#4A148C

class App core
class Marker core
class MarkerFactory core

class Drawable interface
class Tip interface
class Reload interface

class BrushDrawable impl
class FeltDrawable impl
class PermanentDrawable impl
class SnapTip impl
class TwistTip impl
class TubeReload impl
class ReservoirReload impl

class App {
  +main(args: String[]): void
}

class MarkerFactory {
  +createMarker(drawableType: String, tipType: String, reloadType: String, inkColor: String): Marker
}

class Marker {
  +Marker(inkColor: String, drawable: Drawable, tip: Tip, reloader: Reload)
  +draw(text: String): void
  +uncap(): void
  +cap(): void
  +reload(color: String): void
}

class Drawable {
  <<interface>>
  +draw(color: String, text: String): void
}

class BrushDrawable {
  +draw(color: String, text: String): void
}

class FeltDrawable {
  +draw(color: String, text: String): void
}

class PermanentDrawable {
  +draw(color: String, text: String): void
}

class Tip {
  <<interface>>
  +open(): void
  +seal(): void
}

class SnapTip {
  +open(): void
  +seal(): void
}

class TwistTip {
  +open(): void
  +seal(): void
}

class Reload {
  <<interface>>
  +reload(color: String): void
}

class TubeReload {
  +reload(color: String): void
}

class ReservoirReload {
  +reload(color: String): void
}

%% Relationships
App --> MarkerFactory
App --> Marker

MarkerFactory ..> Marker
MarkerFactory ..> Drawable
MarkerFactory ..> Tip
MarkerFactory ..> Reload

Marker --> Drawable : has
Marker --> Tip : has
Marker --> Reload : has

Drawable <|.. BrushDrawable
Drawable <|.. FeltDrawable
Drawable <|.. PermanentDrawable

Tip <|.. SnapTip
Tip <|.. TwistTip

Reload <|.. TubeReload
Reload <|.. ReservoirReload
```