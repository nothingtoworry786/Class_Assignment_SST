# Assignment4 — Snake and Ladder (Class Diagram)

```mermaid
classDiagram

classDef core fill:#F3E5F5,stroke:#6A1B9A,color:#4A148C
classDef interface fill:#FFF3E0,stroke:#E65100,color:#BF360C
classDef impl fill:#E3F2FD,stroke:#1565C0,color:#0D47A1
classDef enum fill:#E8F5E9,stroke:#2E7D32,color:#1B5E20
classDef rule fill:#E1F5FE,stroke:#0288D1,color:#01579B

class Main core
class SessionBuilder core
class GameSession core
class SessionContext core
class EventProcessor core
class GameBoard core
class Tile core

class Contestant interface
class ContestantImpl impl

class Die interface
class StandardDie impl

class TileAction interface
class SlideDown impl
class ClimbUp impl

class GameRule interface
class TileActionRule rule
class LenientMoveRule rule
class PreciseMoveRule rule
class UnboundedBonusRule rule
class CappedBonusRule rule

class RuleSetAssembler core
class BoardPopulator core
class TurnEvent core

classDef enumBox fill:#E8F5E9,stroke:#2E7D32,color:#1B5E20
class TurnPhase enumBox

class Main {
  +main(args)
}

class SessionBuilder {
  +createGame(gridSize, names, mode)
}

class GameSession {
  +playTurn()
  +hasEnded()
  +getPodium()
}

class SessionContext {
  +advanceTurn()
  +markAsWon(value)
  +recordRoll(value)
  +scheduleReRoll()
  +addLastContestantToPodium()
  +getRollHistory()
  +getBoard()
  +getCurrentContestant()
  +remainingContestants()
  +getPodium()
}

class EventProcessor {
  +addRule(rule)
  +dispatch(event, ctx)
}

class GameBoard {
  +getTile(position)
  +getTotalSquares()
}

class Tile {
  +trigger(ctx)
  +getIndex()
}

class Contestant {
  <<interface>>
  +getName()
  +getPosition()
  +setPosition(position)
  +throwDie(die)
}

class ContestantImpl {
  +getName()
  +getPosition()
  +setPosition(position)
  +throwDie(die)
}

class Die {
  <<interface>>
  +roll()
}

class StandardDie {
  +roll()
}

class TileAction {
  <<interface>>
  +apply(ctx)
}

class SlideDown {
  +apply(ctx)
}

class ClimbUp {
  +apply(ctx)
}

class GameRule {
  <<interface>>
  +handle(event, ctx)
}

class TileActionRule {
  +handle(event, ctx)
}

class LenientMoveRule {
  +handle(event, ctx)
}

class PreciseMoveRule {
  +handle(event, ctx)
}

class UnboundedBonusRule {
  +handle(event, ctx)
}

class CappedBonusRule {
  +handle(event, ctx)
}

class RuleSetAssembler {
  +build(mode)
}

class BoardPopulator {
  +populate(tiles, totalSquares, countPerType)
}

class TurnEvent {
  +getPhase()
  +getContestant()
  +getDiceOutcome()
  +getOriginTile()
  +getDestinationTile()
  +getTile()
}

class TurnPhase {
  <<enumeration>>
  DICE_ROLLED
  TILE_ENTERED
}

%% Relationships
ContestantImpl ..|> Contestant
StandardDie ..|> Die
SlideDown ..|> TileAction
ClimbUp ..|> TileAction

TileActionRule ..|> GameRule
LenientMoveRule ..|> GameRule
PreciseMoveRule ..|> GameRule
UnboundedBonusRule ..|> GameRule
CappedBonusRule ..|> GameRule

Main --> SessionBuilder
SessionBuilder --> GameSession
SessionBuilder --> SessionContext
SessionBuilder --> GameBoard
SessionBuilder --> ContestantImpl
SessionBuilder --> RuleSetAssembler
SessionBuilder --> StandardDie
SessionBuilder --> BoardPopulator

GameSession --> SessionContext
GameSession --> EventProcessor
GameSession --> Die
GameSession --> TurnEvent

EventProcessor --> GameRule
EventProcessor --> TurnEvent
EventProcessor --> SessionContext

GameBoard --> Tile
Tile --> TileAction

TurnEvent --> TurnPhase
TurnEvent --> Contestant
TurnEvent --> Tile

SessionContext --> GameBoard
SessionContext --> Contestant
SessionContext --> Tile
SessionContext --> TurnEvent

RuleSetAssembler --> EventProcessor
RuleSetAssembler --> TileActionRule
RuleSetAssembler --> LenientMoveRule
RuleSetAssembler --> PreciseMoveRule
RuleSetAssembler --> UnboundedBonusRule
RuleSetAssembler --> CappedBonusRule

BoardPopulator --> Tile
BoardPopulator --> TileAction
BoardPopulator --> SlideDown
BoardPopulator --> ClimbUp
```

