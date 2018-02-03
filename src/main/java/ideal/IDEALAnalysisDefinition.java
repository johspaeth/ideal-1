package ideal;

import java.util.Collection;

import boomerang.BoomerangOptions;
import boomerang.cfg.IExtendedICFG;
import heros.BiDiInterproceduralCFG;
import heros.incremental.UpdatableWrapper;
import heros.solver.IPropagationController;
import ideal.debug.IDebugger;
import ideal.edgefunction.AnalysisEdgeFunctions;
import ideal.flowfunctions.StandardFlowFunctions;
import ideal.incremental.accessgraph.UpdatableAccessGraph;
import soot.SootMethod;
import soot.Unit;

public abstract class IDEALAnalysisDefinition<V> {

	/**
	 * This function generates the seed. Each (reachable) statement of the
	 * analyzed code is visited. To place a seed, a pair of access graph and an
	 * edge function must be specified. From this node the analysis starts its
	 * analysis.
	 * 
	 * @param method
	 * @param stmt
	 *            The statement over which is itearted over
	 * @param calledMethod
	 *            If stmt is a call site, this set contains the set of called
	 *            method for the call site.
	 * @return
	 */
	public abstract Collection<UpdatableAccessGraph> generate(UpdatableWrapper<SootMethod> method, UpdatableWrapper<Unit> stmt, Collection<UpdatableWrapper<SootMethod>> calledMethod, IExtendedICFG<Unit, SootMethod> icfg);

	/**
	 * This function must generate and return the AnalysisEdgeFunctions that are
	 * used for the analysis. As for standard IDE in Heros, the edge functions
	 * for normal-, call-, return- and call-to-return flows have to be
	 * specified.
	 */
	public abstract AnalysisEdgeFunctions<V> edgeFunctions();

	public abstract ResultReporter<V> resultReporter();

	public abstract IExtendedICFG<Unit, SootMethod> eIcfg();
	
	public abstract BiDiInterproceduralCFG<Unit, SootMethod> icfg();

	public abstract IDebugger<V> debugger();

	public abstract BoomerangOptions boomerangOptions();

	public abstract boolean enableAliasing();

	public abstract long analysisBudgetInSeconds();

	public abstract boolean enableNullPointOfAlias();

	public abstract boolean enableStrongUpdates();

	public abstract IDEALScheduler<V> getScheduler();

	public abstract IPropagationController<UpdatableWrapper<Unit>, UpdatableAccessGraph> propagationController();

	public String toString() {
		String str = "====== IDEal Analysis Options ======";
		str += "\nEdge Functions:\t\t" + edgeFunctions();
		str += "\nDebugger Class:\t\t" + debugger();
		str += "\nAnalysisBudget(sec):\t" + (analysisBudgetInSeconds());
		str += "\nStrong Updates:\t\t" + (enableStrongUpdates() ? "ENABLED" : "DISABLED");
		str += "\nAliasing:\t\t" + (enableAliasing() ? "ENABLED" : "DISABLED");
		str += "\nNull POAs:\t\t" + (enableNullPointOfAlias() ? "ENABLED" : "DISABLED");
		str += "\n" + boomerangOptions();
		return str;
	}

	public abstract NonIdentityEdgeFlowHandler<V> nonIdentityEdgeFlowHandler();

	public abstract void onFinishWithSeed(IFactAtStatement seed, AnalysisSolver<V> solver);

	public abstract StandardFlowFunctions<V> flowFunctions(PerSeedAnalysisContext<V> context);

	public abstract void onStartWithSeed(IFactAtStatement seed, AnalysisSolver<V> solver);

}
