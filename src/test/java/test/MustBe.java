package test;

import heros.incremental.UpdatableWrapper;
import ideal.incremental.accessgraph.UpdatableAccessGraph;
import soot.Unit;
import typestate.TypestateDomainValue;

public class MustBe extends ExpectedResults<ConcreteState> {

	MustBe(UpdatableWrapper<Unit> unit, UpdatableAccessGraph accessGraph, InternalState state) {
		super(unit, accessGraph, state);
	}

	public String toString(){
		return "MustBe " + super.toString();
	}

	@Override
	public void computedResults(TypestateDomainValue<ConcreteState> results) {
		for(ConcreteState s : results.getStates()){
			System.out.println("ConcreteState in MustBe " + s);
			if(state == InternalState.ACCEPTING){
				satisfied |= !s.isErrorState();
				imprecise = results.getStates().size() > 1;
			} else if(state == InternalState.ERROR){
				satisfied |= s.isErrorState();
				imprecise = results.getStates().size() > 1;
			}
		}
		
	}
}	
