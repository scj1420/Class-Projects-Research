package request;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

import result.FillResult;

/**
 * Informations for fill method
 *
 * Domain:
 * userName     : Non-empty String
 * generations  : Optional int value
 */
public class FillRequest {

    /**
     * Creates FillRequest object with just the username
     *
     * @param userName -- the user's username
     */
    public FillRequest(String userName){}

    /**
     * Creates FillRequest object with the username and number of
     * generations to be generated
     *
     * @param userName - the user's username
     * @param generations - number of generations to be generated
     */
    public FillRequest(String userName,
                       int generations){}
}
