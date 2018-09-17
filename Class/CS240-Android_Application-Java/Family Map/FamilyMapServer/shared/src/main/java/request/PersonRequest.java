package request;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

import result.PersonResult;

/**
 * Information for person method
 *
 * Domain:
 * authToken    : unique auth token
 * personID     : Person's unique ID
 */
public class PersonRequest {
    /**
     * Creates a PersonRequest object
     *
     * @param result -- result of the request
     * @param authToken -- the auth token for this person
     * @param personID -- the ID of this person
     */
    public PersonRequest(PersonResult result,
                         String authToken,
                         String personID){

    }
}
