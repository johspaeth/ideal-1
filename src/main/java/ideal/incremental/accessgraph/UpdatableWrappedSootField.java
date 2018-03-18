package ideal.incremental.accessgraph;

import boomerang.accessgraph.WrappedSootField;
import heros.incremental.UpdatableWrapper;

import soot.SootField;
import soot.Unit;

/**
 * A wrapped SootField. It can have a more precise type information than the original SootField.
 * 
 * @author spaeth
 *
 */
public class UpdatableWrappedSootField {
  private UpdatableWrapper<SootField> field;
  private UpdatableWrapper<Unit> stmt;
  public static boolean TRACK_STMT = true;

  public UpdatableWrappedSootField(UpdatableWrapper<SootField> f, UpdatableWrapper<Unit> s) {
    this.field = f;
    this.stmt = (TRACK_STMT ? s : null);
  }

public UpdatableWrapper<SootField> getField() {
    return field;
  }
  
  public WrappedSootField getWrappedSootField() {
	  if(stmt != null)
		  return new WrappedSootField(this.field.getContents(), this.stmt.getContents());
	  else
		  return new WrappedSootField(this.field.getContents(), null);
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((field == null) ? 0 : field.hashCode());
    result = prime * result + ((stmt == null) ? 0 : stmt.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    /*if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UpdatableWrappedSootField other = (UpdatableWrappedSootField) obj;
    if (field == null) {
      if (other.field != null)
        return false;
    } else if (!field.equals(other.field))
      return false;
    if (stmt == null) {
      if (other.stmt != null)
        return false;
    } else if (!stmt.equals(other.stmt))
      return false;
    return true;*/
	return ((UpdatableWrappedSootField) obj).getWrappedSootField().equals(this.getWrappedSootField());
  }

  public String toString() {
    return field.getContents().getName().toString();
  }

}