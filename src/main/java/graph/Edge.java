package graph;

/**
 * 
 * @author sanket.bharaswadkar
 *
 */
public class Edge {

    private Integer length;
    private Node from;
    private Node to;

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Node getFrom() {
        return from;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public Node getTo() {
        return to;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        StringBuilder builder = new StringBuilder();
        builder.append("[ ");
        builder.append(this.getFrom().getName() + this.getTo().getName() +this.getLength());

        builder.append(" ]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return 31 * (this.from == null ? 0 : this.from.hashCode()) * (this.to == null ? 0 : this.to.hashCode());
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Edge)) {
            return false;
        }
        Edge objEdge = (Edge) obj;
        if (this.to.equals(objEdge.getTo()) && this.from.equals(objEdge.getFrom())) {
            return true;
        }
        return false;
    }
}
