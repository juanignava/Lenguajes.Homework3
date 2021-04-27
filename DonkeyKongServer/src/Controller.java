

import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    private static ArrayList<DonkeyKongJr> players = new ArrayList<>();

    private static ArrayList<Alligator> alligators1 = new ArrayList<>();
    private static ArrayList<Alligator> alligators2 = new ArrayList<>();

    private static ArrayList<Fruit> fruits1 = new ArrayList<>();
    private static ArrayList<Fruit> fruits2 = new ArrayList<>();

    private DonkeyKongJr player1;
    private DonkeyKongJr player2;

    /**
     * Description: constructor method.
     */
    public Controller() {

        createNullInstances();

        players.set(0, new DonkeyKongJr(Constants.DK_INITIAL_X, Constants.DK_INITIAL_Y,1,0));
        players.set(1, new DonkeyKongJr(Constants.DK_INITIAL_X, Constants.DK_INITIAL_Y,1,0));

    }

    /**
     * Description: creates null instances of players, alligators and fruits to set the
     *              correct size to the respective arrayLists.
     */
    private void createNullInstances() {

        // Loop to add two null instances to players
        
        for (Integer i = 0; i < 2; i++) {

            players.add(null);

        }
        

        // Loop to add six null instances to alligators1
        for (Integer i = 0; i < 6; i++) {

            alligators1.add(null);

        }

        // Loop to add six null instances to alligators2
        for (Integer i = 0; i < 6; i++) {

            alligators2.add(null);

        }

        // Loop to add six null instances to fruits1
        for (Integer i = 0; i < 6; i++) {

            fruits1.add(null);

        }

        // Loop to add six null instances to fruits2
        for (Integer i = 0; i < 6; i++) {

            fruits2.add(null);

        }

    }

    /**
     * Description: Analyzes the string given by the client and calls the given functions.
     * @param instruction String with the indication given by the client
     */
    public Integer followInstruction(String instruction) throws IOException {

        // Instructions for player1
        if (instruction.contains("1")) {

            // Checks if the player1 is already instantiated or not
            if (players.get(0) == null){//players.size() == 0) {

                player1 = new DonkeyKongJr(Constants.DK_INITIAL_X, Constants.DK_INITIAL_Y, 1, 0);
                players.add(player1);

            }

            // Moves player1 to the right
            if (instruction.equals("r1")) {

                players.get(0).setPositionX(Constants.PIXELS_RIGHT_LEFT);

            // Moves player1 to the left
            } else if (instruction.equals("l1")) {

                players.get(0).setPositionX(Constants.PIXELS_RIGHT_LEFT * -1);

            // Moves player1 upwards
            } else if (instruction.equals("u1")) {

                players.get(0).setPositionY(Constants.PIXELS_UP_DOWN);

            // Moves player1 downwards
            } else if (instruction.equals("d1")) {

                players.get(0).setPositionY(Constants.PIXELS_UP_DOWN * -1);

            }

        // Instructions for player2
        } else if (instruction.contains("2")) {

            // Checks if the player2 is already instantiated or not
            if (players.get(1) == null) {

                player2 = new DonkeyKongJr(0,0, 1, 0);
                players.add(player2);

            }

            // Moves player2 to the right
            if (instruction.equals("r2")) {

                players.get(1).setPositionX(Constants.PIXELS_RIGHT_LEFT);

                // Moves player2 to the left
            } else if (instruction.equals("l2")) {

                players.get(1).setPositionX(Constants.PIXELS_RIGHT_LEFT * -1);

                // Moves player2 upwards
            } else if (instruction.equals("u2")) {

                players.get(1).setPositionY(Constants.PIXELS_UP_DOWN);

                // Moves player2 upwards
            } else if (instruction.equals("d2")) {

                players.get(1).setPositionY(Constants.PIXELS_UP_DOWN * -1);

            }

        }

        Integer option;

        if (instruction.contains("s")) {

            option = 3;

        } else if (instruction.contains("1")){

            option = 1;

        } else {
            option = 2;
        }

        return option;

    }

    /**
     * Description: return the full data of players, fruits and alligators in one simple string.
     */
    public String getData(int player) {

        String data = "";
        ArrayList<Fruit> fruitsTemporal;
        ArrayList<Alligator> alligatorsTemporal;

        //for (int i = 0; i < players.size()-1; i++) {

            // Player does not exist
        if (players.get(player) == null) {

            if(player == 0){
                data += "dk,_,_;" +
                "f1,_,_;f2,_,_;f3,_,_;f4,_,_f5,_,_f6,_,_;" +
                "c1,_,_,_;c2,_,_,_;c3,_,_,_;c4,_,_,_;c5,_,_,_;c6,_,_,_" +
                "/";
            } else {
                data += "2dk,_,_;" +
                    "f1,_,_;f2,_,_;f3,_,_;f4,_,_f5,_,_f6,_,_;" +
                    "c1,_,_,_;c2,_,_,_;c3,_,_,_;c4,_,_,_;c5,_,_,_;c6,_,_,_" +
                    "/";
            }

        } else {

            if(player == 0){

                players.get(player).colisions(fruits1, alligators1);

                data += "dk," + players.get(player).getPositionX() + "," + players.get(player).getPositionY() + ";";
                // + players.get(i).getLifes() + "," + players.get(i).getScore() + ";";

                if (player == 0) {

                    fruitsTemporal = fruits1;
                    alligatorsTemporal = alligators1;

                } else {

                    fruitsTemporal = fruits2;
                    alligatorsTemporal = alligators2;

                }

                for (int j = 0; j < fruitsTemporal.size(); j++) {

                    // Model.Fruit does not exist
                    if (fruitsTemporal.get(j) == null) {

                        data += "f" + (j + 1) + ",_,_;";

                    } else {

                        data += "f" + (j + 1) + "," + fruitsTemporal.get(j).getPositionX() + "," +
                                fruitsTemporal.get(j).getPositionY()+ ";";

                    }

                }

                for (int k = 0; k < alligatorsTemporal.size(); k++) {

                    // Model.Alligator does not exist
                    if (alligatorsTemporal.get(k) == null) {

                        data += "c" + (k + 1) + ",_,_,_;";

                    } else {

                        alligatorsTemporal.get(k).setPositionY(Constants.PIXELS_UP_DOWN);

                        data += "c" + (k + 1) + "," + alligatorsTemporal.get(k).getColor() + "," +
                                alligatorsTemporal.get(k).getPositionX() + "," + alligatorsTemporal.get(k).getPositionY() + ";";

                    }

                }

                data += "/";
                //data += "/" + players.get(i).getLifes() + "/" + players.get(i).getScore();
                //players.get(i).colisions(fruits1, alligators1);;
            }
            else{

                players.get(player).colisions(fruits2, alligators2);

                data += "2dk," + players.get(player).getPositionX() + "," + players.get(player).getPositionY() + ";";
                // + players.get(i).getLifes() + "," + players.get(i).getScore() + ";";

                if (player == 0) {

                    fruitsTemporal = fruits1;
                    alligatorsTemporal = alligators1;

                } else {

                    fruitsTemporal = fruits2;
                    alligatorsTemporal = alligators2;

                }

                for (int j = 0; j < fruitsTemporal.size(); j++) {

                    // Model.Fruit does not exist
                    if (fruitsTemporal.get(j) == null) {

                        data += "f" + (j + 1) + ",_,_;";

                    } else {

                        data += "f" + (j + 1) + "," + fruitsTemporal.get(j).getPositionX() + "," +
                                fruitsTemporal.get(j).getPositionY()+ ";";

                    }

                }

                for (int k = 0; k < alligatorsTemporal.size(); k++) {

                    // Model.Alligator does not exist
                    if (alligatorsTemporal.get(k) == null) {

                        data += "c" + (k + 1) + ",_,_,_;";

                    } else {

                        alligatorsTemporal.get(k).setPositionY(Constants.PIXELS_UP_DOWN);

                        data += "c" + (k + 1) + "," + alligatorsTemporal.get(k).getColor() + "," +
                                alligatorsTemporal.get(k).getPositionX() + "," + alligatorsTemporal.get(k).getPositionY() + ";";

                    }

                }

                data += "/";
                //data += "/" + players.get(i).getLifes() + "/" + players.get(i).getScore();
                //players.get(i).colisions(fruits1, alligators1);;

            }

        }

        //}

        return data;

    }

    public String getMessage(Integer option) {

        String message = "";

        if(option == 1) {

            message = getData(0);

        } else if(option == 2) {

            message = getData(1);

        } else {

            message = "l," + players.get(0).getLifes() + "," + players.get(0).getScore();

        }

        return message;

    }

    /**
     * Description: returns which player instances are not null
     */
    public String[] getDynamicPlayer() {

        ArrayList<String> temporal = new ArrayList<>();

        // Loop to check every player
        for (Integer i = 0; i < players.size(); i++) {

            if (players.get(i) != null) {

                temporal.add(String.valueOf(i + 1));

            }

        }

        String[] selections = new String[temporal.size()];

        // Loop to add final player instances
        for (Integer j = 0; j < temporal.size(); j++) {

            selections[j] = temporal.get(j);

        }

        return selections;

    }

    /**
     * Description: returns which alligator instances are not null
     * @param player
     */
    public String[] getDynamicLiana(Integer player) {

        ArrayList< ArrayList<Alligator> > alligatorsTemporal = new ArrayList<>();
        alligatorsTemporal.add(alligators1);
        alligatorsTemporal.add(alligators2);

        ArrayList<String> temporal = new ArrayList<>();

        // Loop to check every alligator instance depending on the player received
        for (Integer i = 0; i < alligatorsTemporal.get(player).size(); i++) {

            if (alligatorsTemporal.get(player).get(i) == null) {

                temporal.add(String.valueOf(i + 1));

            }

        }

        String[] selections = new String[temporal.size()];

        // Loop to add final alligator instances
        for (Integer j = 0; j < temporal.size(); j++) {

            selections[j] = temporal.get(j);

        }

        return selections;

    }

    /**
     * Description: returns which fruit instances are or not null depending on the action received
     * @param player
     * @param action
     */
    public String[] getDynamicPlatform(Integer player, String action) {

        ArrayList< ArrayList<Fruit> > fruitsTemporal = new ArrayList<>();
        fruitsTemporal.add(fruits1);
        fruitsTemporal.add(fruits2);

        ArrayList<String> temporal = new ArrayList<>();

        // Loop to check every fruit instance depending on the player and action received
        for (Integer i = 0; i < fruitsTemporal.get(player).size(); i++) {


            if (fruitsTemporal.get(player).get(i) == null && action.equals("ADD")) {

                temporal.add(String.valueOf(i + 1));

            } else if(fruitsTemporal.get(player).get(i) != null && action.equals("DELETE")) {

                temporal.add(String.valueOf(i + 1));

            }

        }

        // Loop to add final fruit instances
        String[] selections = new String[temporal.size()];

        for (Integer j = 0; j < temporal.size(); j++) {

            selections[j] = temporal.get(j);

        }

        return selections;

    }

    /**
     * Description: returns the dynamic information of the comboBoxes of the GUI.
     * @param strComboBoxObjectValue
     * @param strComboBoxPlatformValue
     * @param strComboBoxPlayerValue
     * @param intComboBoxLianaValue
     * @param intComboBoxPlatformValue
     */
    public void alligatorsFruitsCreation(String strComboBoxObjectValue, String strComboBoxPlatformValue,
                                         String strComboBoxPlayerValue, Integer intComboBoxLianaValue,
                                         Integer intComboBoxPlatformValue) {

        Integer positionX;
        Integer positionY;
        Integer[] constantsXY;
        /*
        Integer[] constantsX = {Constants.PLATFORM1_X, Constants.PLATFORM2_X, Constants.PLATFORM3_X,
                                Constants.PLATFORM4_X, Constants.PLATFORM5_X, Constants.PLATFORM6_X};
        Integer[] constantsY = {Constants.PLATFORM1_Y, Constants.PLATFORM2_Y, Constants.PLATFORM3_Y,
                                Constants.PLATFORM4_Y, Constants.PLATFORM5_Y, Constants.PLATFORM6_Y};
        */

        // Pick the selected information
        switch (strComboBoxObjectValue) {

            // Add fruit
            case "Anadir Fruta":

                constantsXY = getPlatformConstants(strComboBoxPlatformValue);

                positionX = constantsXY[0];
                positionY = constantsXY[1];

                //positionX = constantsX[Integer.valueOf(strComboBoxPlayerValue)];
                //positionY = constantsY[Integer.valueOf(strComboBoxPlayerValue)];

                Fruit temporalFruit = new Fruit(positionX, positionY);

                // Add fruit to player1
                if (strComboBoxPlayerValue.equals("1")) {

                    fruits1.set(intComboBoxPlatformValue, temporalFruit);

                // Add fruit to player2
                } else {

                    fruits2.set(intComboBoxPlatformValue, temporalFruit);

                }

                break;

            // Delete fruit
            case "Eliminar Fruta":

                // Delete fruit from player1
                if (strComboBoxPlayerValue.equals("1")) {

                    fruits1.set(intComboBoxPlatformValue, null);

                // Delete fruit from player2
                } else {

                    fruits2.set(intComboBoxPlatformValue, null);

                }

                break;

            // Add red alligatior
            case "Anadir Cocodrilo Rojo":

                constantsXY = getLianaConstants(Integer.toString(intComboBoxLianaValue+1));

                positionX = constantsXY[0];
                positionY = constantsXY[1];

                Alligator temporalAlligatorR = new Alligator("r", positionX,positionY, Constants.SPEED);

                // Add red alligator to player1
                if (strComboBoxPlayerValue.equals("1")) {

                    alligators1.set(intComboBoxLianaValue, temporalAlligatorR);

                    // Add red alligator to player2
                } else {

                    alligators2.set(intComboBoxLianaValue, temporalAlligatorR);

                }

                break;

            // Add blue alligator
            case "Anadir Cocodrilo Azul":

                constantsXY = getLianaConstants(Integer.toString(intComboBoxLianaValue+1));

                positionX = constantsXY[0];
                positionY = constantsXY[1];

                Alligator temporalAlligatorB = new Alligator("b", positionX,positionY, Constants.SPEED);

                // Add blue alligator to player1
                if (strComboBoxPlayerValue.equals("1")) {

                    alligators1.set(intComboBoxLianaValue, temporalAlligatorB);

                    // Add blue alligator to player2
                } else {

                    alligators2.set(intComboBoxLianaValue, temporalAlligatorB);

                }

                break;

        }

    }

    /**
     * Description: returns the correct X and Y positions of the respective platform.
     * @param strComboBoxPlatformValue
     */
    private Integer[] getPlatformConstants(String strComboBoxPlatformValue) {

        Integer[] positions = new Integer[2];

        Integer positionX = 0;
        Integer positionY = 0;

        // Depending on the comboBoxObjectValue, the respective constants will be assigned to the fruit
        switch (strComboBoxPlatformValue) {

            case "1":
                positionX = Constants.PLATFORM1_X;
                positionY = Constants.PLATFORM1_Y;
                break;

            case "2":
                positionX = Constants.PLATFORM2_X;
                positionY = Constants.PLATFORM2_Y;
                break;

            case "3":
                positionX = Constants.PLATFORM3_X;
                positionY = Constants.PLATFORM3_Y;
                break;

            case "4":
                positionX = Constants.PLATFORM4_X;
                positionY = Constants.PLATFORM4_Y;
                break;

            case "5":
                positionX = Constants.PLATFORM5_X;
                positionY = Constants.PLATFORM5_Y;
                break;

            case "6":
                positionX = Constants.PLATFORM6_X;
                positionY = Constants.PLATFORM6_Y;
                break;

        }

        positions[0] = positionX;
        positions[1] = positionY;

        return positions;

    }

    /**
     * Description: returns the correct X and Y positions of the respective liana.
     * @param strComboBoxPlatformValue
     */
    private Integer[] getLianaConstants(String strComboBoxPlatformValue) {

        Integer[] positions = new Integer[2];

        Integer positionX = 0;
        Integer positionY = 0;

        // Depending on the comboBoxObjectValue, the respective constants will be assigned to the alligator
        switch (strComboBoxPlatformValue) {

            case "1":
                positionX = Constants.LIANA1_X;
                positionY = Constants.LIANA1_Y;
                break;

            case "2":
                positionX = Constants.LIANA2_X;
                positionY = Constants.LIANA2_Y;
                break;

            case "3":
                positionX = Constants.LIANA3_X;
                positionY = Constants.LIANA3_Y;
                break;

            case "4":
                positionX = Constants.LIANA4_X;
                positionY = Constants.LIANA4_Y;
                break;

            case "5":
                positionX = Constants.LIANA5_X;
                positionY = Constants.LIANA5_Y;
                break;

            case "6":
                positionX = Constants.LIANA6_X;
                positionY = Constants.LIANA6_Y;
                break;

        }

        positions[0] = positionX;
        positions[1] = positionY;

        return positions;

    }

}
