package typestate.tests;

import org.junit.Test;

import boomerang.cfg.IExtendedICFG;
import soot.SootMethod;
import soot.Unit;
import test.ConcreteState;
import test.IDEALTestingFramework;
import typestate.TypestateChangeFunction;
import typestate.impl.statemachines.FileMustBeClosedStateMachine;
import typestate.test.helper.File;

@SuppressWarnings("deprecation")
public class TypeStateEvaluationTest extends IDEALTestingFramework {
	
	@Test
	public void beforeStatementAdditionTest()
	{
		File file = new File();
		file.open();
		writeDataToFile(file);
		mustBeInErrorState(file);
	}
	
	@Test
	public void afterStatementAdditionTest()
	{
		File file = new File();
		file.open();
		writeDataToFile(file);
		file.close();
		mustBeInAcceptingState(file);
	}

	private void writeDataToFile(File file) {
		System.out.println("written to file");
	}

	@Override
	protected TypestateChangeFunction<ConcreteState> createTypestateChangeFunction(IExtendedICFG<Unit, SootMethod> icfg) {
		return new FileMustBeClosedStateMachine(icfg);
	}

}
