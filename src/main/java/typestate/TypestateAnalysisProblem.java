package typestate;

import java.util.Collection;

import boomerang.accessgraph.AccessGraph;
import boomerang.incremental.UpdatableWrapper;
import ideal.DefaultIDEALAnalysisDefinition;
import ideal.edgefunction.AnalysisEdgeFunctions;
import soot.SootMethod;
import soot.Unit;

public abstract class TypestateAnalysisProblem<State> extends DefaultIDEALAnalysisDefinition<TypestateDomainValue<State>> {
	private TypestateChangeFunction<State> func;

	@Override
	public AnalysisEdgeFunctions<TypestateDomainValue<State>> edgeFunctions() {
		return new TypestateEdgeFunctions<State>(getOrCreateTransitionFunctions());
	}

	private TypestateChangeFunction<State> getOrCreateTransitionFunctions() {
		if(func == null)
			func = createTypestateChangeFunction();
		return func;
	}

	public abstract TypestateChangeFunction<State> createTypestateChangeFunction();

	@Override
	public Collection<AccessGraph> generate(UpdatableWrapper<SootMethod> method, UpdatableWrapper<Unit> stmt, Collection<UpdatableWrapper<SootMethod>> optional) {
		return getOrCreateTransitionFunctions().generate(method, stmt, optional);
	}

}
