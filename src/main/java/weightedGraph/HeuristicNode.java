package weightedGraph;

import java.util.Objects;

abstract public class HeuristicNode<T> {

    protected T name;

    public abstract Double heuristicValue(Object other);


    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if (this == o) return true;
        if (!(o instanceof HeuristicNode)) return false;
        HeuristicNode<?> that = (HeuristicNode<?>) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String edgeString(HeuristicNode other){
        return this.name.toString().concat(" - ").concat(other.name.toString());
    }

    @Override
    public String toString(){
        return name.toString();
    }
}
