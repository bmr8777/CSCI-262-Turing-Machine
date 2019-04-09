/**
 * Class representation of the input pair for the transition function
 *
 * @author Brennan Reed
 */

public class Pair {

    /*
    The current state
     */
    private String state;

    /*
    Symbol currently under the TM head
     */
    private Character symbol;

    /**
     * Constructor for the Pair class
     *
     * @param state The current state
     * @param symbol The symbol under the TM head
     */

    public Pair(String state, Character symbol){
        this.state = state;
        this.symbol = symbol;
    }

    /**
     * Overrides the default equals method to allow accruate comparisons
     *
     * @param pair pair to compare with
     * @return whether the pairs are equal
     */

    public boolean equals(Pair pair){ return (state.equals(pair.state) && symbol.equals(pair.symbol)); }
}
