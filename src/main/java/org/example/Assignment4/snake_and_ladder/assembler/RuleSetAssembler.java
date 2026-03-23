package org.example.Assignment4.snake_and_ladder.assembler;

import org.example.Assignment4.snake_and_ladder.service.EventProcessor;
import org.example.Assignment4.snake_and_ladder.strategy.CappedBonusRule;
import org.example.Assignment4.snake_and_ladder.strategy.GameRule;
import org.example.Assignment4.snake_and_ladder.strategy.LenientMoveRule;
import org.example.Assignment4.snake_and_ladder.strategy.PreciseMoveRule;
import org.example.Assignment4.snake_and_ladder.strategy.TileActionRule;
import org.example.Assignment4.snake_and_ladder.strategy.UnboundedBonusRule;

public class RuleSetAssembler {
    public EventProcessor build(String mode) {
        EventProcessor processor = new EventProcessor();

        String m = mode == null ? "" : mode.trim().toLowerCase();

        boolean precise = m.contains("precise");
        boolean capped = m.contains("cap");

        GameRule moveRule = precise ? new PreciseMoveRule() : new LenientMoveRule();
        GameRule bonusRule = capped ? new CappedBonusRule() : new UnboundedBonusRule();
        GameRule tileActionRule = new TileActionRule();

        processor.addRule(moveRule);
        processor.addRule(bonusRule);
        processor.addRule(tileActionRule);

        return processor;
    }
}

