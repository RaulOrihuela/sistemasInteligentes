package weightedGraph;

public class GeoNode<T> extends HeuristicNode<T> {

    Double latitude;
    Double longitude;

    public GeoNode(T name, Double latitude, Double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public Double heuristicValue(Object other) {
        if (!(other instanceof GeoNode)) return null;
        else {
            GeoNode otherGeo = (GeoNode) other;
            Double deltaLat = Math.toRadians(this.latitude - otherGeo.latitude);
            Double deltaLong = Math.toRadians(this.longitude - otherGeo.longitude);
            // 12756.274 is two times the radius of earth in km(6378.137)
            return 12756.274 * Math.asin(Math.sqrt(
                    Math.pow(Math.sin(deltaLat/2),2) +
                    Math.pow(Math.sin(deltaLong/2),2) *
                    Math.cos(Math.toRadians(this.latitude)) *
                    Math.cos(Math.toRadians(otherGeo.latitude))
            ));
        }
    }
}
