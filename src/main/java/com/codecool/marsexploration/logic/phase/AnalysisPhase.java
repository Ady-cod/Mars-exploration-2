package com.codecool.marsexploration.logic.phase;

import com.codecool.marsexploration.data.Context;
import com.codecool.marsexploration.data.Outcome;
import com.codecool.marsexploration.logic.analyzer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnalysisPhase implements Phase{
    private List<Analyzer> analyzers;

    public AnalysisPhase() {
        analyzers = new ArrayList<>();
        analyzers.add(new LandingAnalyzer());
        analyzers.add(new TimeoutAnalyzer());
        analyzers.add(new AlienAnalyzer());
        analyzers.add(new ResourcesAnalyzer());

    }
    @Override
    public void perform(Context context) {
//        System.out.println("Entering AnalysisPhase...");

        for (Analyzer analyzer : analyzers) {
            Optional<Outcome> outcome = analyzer.analyze(context);
            if (outcome.isPresent()) {
                context.setOutcome(outcome.get());
//                System.out.println("Outcome found: " + outcome.get());
                break;
            }
        }

    }


}
