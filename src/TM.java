import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class: TM
 * ---------
 *  Class representation of a Turing Machine
 *
 * @author Brennan Reed
 */

public class TM {

    /*
    Set of all states
     */
    private Set<String> Q;

    /*
    The input alphabet
     */
    private Set<String> sigma;

    /*
    The tape alphabet
     */
    private Set<String> gamma;

    /*
    The transition function
     */
    private Map<Pair, Transition> delta;

    /*
    Start state
     */
    private String s;

    /*
    Accept state
     */
    private String qAccept;

    /*
    Reject state
     */
    private String qReject;


    /**
     * Constructor for the TM class
     *
     * @param filename The name of the input file
     */

    public TM(String filename) throws FileNotFoundException{
        Q = new HashSet<>();
        sigma = new HashSet<>();
        gamma = new HashSet<>();
        delta = new HashMap<>();
        String line, s1, s2;
        String[] lineSplit;
        File file = new File(filename);
        Scanner scan = new Scanner(file);
        int count = 0;
        while (scan.hasNextLine()){
            line = scan.nextLine();
            if (line.charAt(0) == '#' || line.charAt(0) == ' ')
                continue;
            else
                count++;
            switch (count){
                case 1:
                    lineSplit = line.split("\\s+");
                    Collections.addAll(Q, lineSplit);
                    break;
                case 2:
                    lineSplit = line.split("\\s+");
                    Collections.addAll(sigma, lineSplit);
                    break;
                case 3:
                    lineSplit = line.split("\\s+");
                    Collections.addAll(gamma, lineSplit);
                    break;
                case 4:
                    s = line;
                    break;
                case 5:
                    qAccept = line;
                    break;
                case 6:
                    qReject = line;
                    break;
                default:
                    lineSplit = line.split("\\s+");
                    Direction direction;
                    Pair pair;
                    Transition transition;
                    if (lineSplit[4].equals("R"))
                        direction = Direction.RIGHT;
                    else
                        direction = Direction.LEFT;
                    pair = new Pair(lineSplit[0], lineSplit[1].charAt(0));
                    transition = new Transition(lineSplit[2], lineSplit[3].charAt(0), direction);
                    delta.put(pair, transition);
                    break;
            }
        }
        scan.close();
    }

    /**
     * Checks to see if the specified transition exists
     *
     * @param pair the key used by the transition function
     * @return whether the transition is valid
     */

    Transition validTransition(Pair pair){
            Transition transition = null;
            for (Pair p : delta.keySet()){
                if (pair.equals(p))
                     return delta.get(p);
            }
            return transition;
    }

    /**
     * Processes user-inputted strings through the TM
     *
     * @param input user-inputted string
     */

    public void process(String input){
        boolean flag = true;
        Pair pair;
        Transition transition;
        int index = 0;
        if (input.isEmpty())
            return;
        String state = s;
        List<Character> tape = new ArrayList<>();
        for (int i = 0; i < input.length(); i++)
            tape.add(i, input.charAt(i));
        System.out.println(tapeOutput(tape, index, state));

        while (flag){
            pair = new Pair(state, tape.get(index));
            transition = validTransition(pair);
            if (transition == null) {
                state = "QReject";
                flag = false;
                index++;
            } else {
                state = transition.getState();
                tape.set(index, transition.getSymbol());
                if (transition.getDirection() == Direction.RIGHT) {
                    index++;
                    if (index == tape.size())
                        tape.add(index, 'u');
                } else {
                    if (index > 0)
                        index--;
                }
            }
            System.out.println(tapeOutput(tape, index, state));
            if (state.equals(qAccept) || state.equals(qReject))
                flag = false;
        }
    }

    /**
     * Creates and returns the String representation of the TM tape
     *
     * @param tape the TM tape
     * @param index index of TM head
     * @param state current state of TM
     * @return String representation of the tape
     */

    public String tapeOutput(List<Character> tape, int index, String state){
        String output = "";
        for (int i = 0; i < index; i++)
            output += tape.get(i);
        output += state;
        for (int i = index; i < tape.size(); i++)
            output += tape.get(i);
        return output;
    }

    /**
     * Handles input and creates the specified TM object
     *
     * @param args commandline arguments
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Turing machine specification file name: ");
        String filename = scanner.nextLine();
        try {
            TM tm = new TM(filename);
            boolean flag = true;
            while (flag) {
                System.out.print("--> ");
                String input = scanner.nextLine();
                if (input.equals(""))
                    flag = false;
                else
                    tm.process(input);
            }
            System.out.println("Goodbye");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
