package api.petstore.helpers;

import org.apache.http.client.utils.URIBuilder;

import lombok.SneakyThrows;

public class PathSegments {

    /**
     * This function returns an entity ID as a path segment
     *
     * @param entityID entity ID in a String format (entity could be: pet)
     * @return String with a space ID
     */
    @SneakyThrows
    public static String pathWithEntityID(String entityID) {
        return new URIBuilder().setPathSegments(entityID).build().toString();
    }

}
