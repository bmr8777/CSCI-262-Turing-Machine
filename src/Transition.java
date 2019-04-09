/**
 * Class representation of a single transition made by the TM
 *
 * @author Brennan Reed
 */

public class Transition {

    /*
    The next state of the TM
     */
    private String state;

    /*
    The symbol being written to the tape
     */
    private Character symbol;

    /*
    The direction the TM is moving
     */
    private Direction direction;

    /**
     * Constructor for the transition class
     *
     * @param state The next state of the TM
     * @param symbol The symbol being written to the tape
     * @param direction The direction the TM is moving
     */

    public Transition(String state, Character symbol, Direction direction){
        this.state = state;
        this.symbol = symbol;
        this.direction = direction;
    }

    /**
     * Getter for state
     *
     * @return state
     */

    public String getState(){ return state; }

    /**
     * Getter for symbol
     *
     * @return symbol
     */

    public Character getSymbol(){ return symbol; }

    /**
     * Getter for direction
     *
     * @return direction
     */

    public Direction getDirection(){ return direction; }
}
