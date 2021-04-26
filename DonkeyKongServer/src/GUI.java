
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {

    // Attributes for GUI
    private JPanel panel;

    private ImageIcon image;

    private JLabel labelObject;
    private JLabel labelLiana;
    private JLabel labelPlatform;
    private JLabel labelPlayer;

    private JComboBox comboBoxObject;
    private JComboBox comboBoxLiana;
    private JComboBox comboBoxPlatform;
    private JComboBox comboBoxPlayer;

    private String[] objectsArray;
    private String[] lianasArray;
    private String[] platformsArray;
    private String[] playersArray;

    private JFrame frame;

    private ArrayList<DonkeyKongJr> players;

    private DonkeyKongJr player1;
    private DonkeyKongJr player2;

    private ArrayList<Alligator> alligators1;
    private ArrayList<Alligator> alligators2;

    private ArrayList<Fruit> fruits1;
    private ArrayList<Fruit> fruits2;

    // Constants
    private static final int PLATFORM1_X = 132;
    private static final int PLATFORM1_Y = 575;

    private static final int PLATFORM2_X = 316;
    private static final int PLATFORM2_Y = 305;

    private static final int PLATFORM3_X = 500;
    private static final int PLATFORM3_Y = 575;

    private static final int PLATFORM4_X = 684;
    private static final int PLATFORM4_Y = 305;

    private static final int PLATFORM5_X = 868;
    private static final int PLATFORM5_Y = 575;

    private static final int PLATFORM6_X = 868;
    private static final int PLATFORM6_Y = 170;

    private static final int LIANA1_X = 199;
    private static final int LIANA1_Y = 35;

    private static final int LIANA2_X = 383;
    private static final int LIANA2_Y = 35;

    private static final int LIANA3_X = 567;
    private static final int LIANA3_Y = 35;

    private static final int LIANA4_X = 752;
    private static final int LIANA4_Y = 35;

    private static final int LIANA5_X = 383;
    private static final int LIANA5_Y = 35;

    private static final int LIANA6_X = 752;
    private static final int LIANA6_Y = 35;

    private static final int SPEED = 1;

    /**
     * Description: constructor method.
     * @param players
     * @param alligators1
     * @param alligators2
     * @param fruits1
     * @param fruits2
     */
    public GUI(ArrayList<DonkeyKongJr> players, ArrayList<Alligator> alligators1,
               ArrayList<Alligator> alligators2, ArrayList<Fruit> fruits1,
               ArrayList<Fruit> fruits2) {

        this.players = players;

        this.alligators1 = alligators1;
        this.alligators2 = alligators2;

        this.fruits1 = fruits1;
        this.fruits2 = fruits2;

        panel = new JPanel();
        panel.setLayout(null);

        image = new ImageIcon(getClass().getResource("/Images/ArcadeMachine.png"));
        JLabel background = new JLabel("", image,JLabel.CENTER);
        background.setBounds(0,0, 700, 670);
        panel.add(background);

        labelObject = new JLabel("Seleccione un objeto");
        labelObject.setBounds(150, 175, 300, 25);
        labelObject.setFont(new Font("Courier New", Font.CENTER_BASELINE, 18));
        panel.add(labelObject);

        objectsArray = new String[] {"Fruta", "Cocodrilo Rojo", "Cocodrilo Azul"};
        comboBoxObject = new JComboBox(objectsArray);
        comboBoxObject.setBounds(450, 175, 100, 25);
        comboBoxObject.addActionListener(new ActionListener() {

            // Enables and disables the respective comboBoxes
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedItem = (String) comboBoxObject.getSelectedItem();

                if (selectedItem.equals("Fruta")) {

                    comboBoxLiana.setEnabled(false);
                    comboBoxPlatform.setEnabled(true);

                } else {

                    comboBoxPlatform.setEnabled(false);
                    comboBoxLiana.setEnabled(true);

                }

            }
        });
        panel.add(comboBoxObject);

        labelLiana = new JLabel("Seleccione una liana");
        labelLiana.setBounds(150, 235, 300, 25);
        labelLiana.setFont(new Font("Courier New", Font.CENTER_BASELINE, 18));
        panel.add(labelLiana);

        lianasArray = new String[] {"1", "2", "3", "4", "5", "6"};
        comboBoxLiana = new JComboBox(lianasArray);
        comboBoxLiana.setBounds(450, 235, 100, 25);
        comboBoxLiana.setEnabled(false);
        panel.add(comboBoxLiana);

        labelPlatform = new JLabel("Seleccione una plataforma");
        labelPlatform.setBounds(150, 295, 300, 25);
        labelPlatform.setFont(new Font("Courier New", Font.CENTER_BASELINE, 18));
        panel.add(labelPlatform);

        platformsArray = new String[] {"1", "2", "3", "4", "5", "6"};
        comboBoxPlatform = new JComboBox(platformsArray);
        comboBoxPlatform.setBounds(450, 295, 100, 25);
        panel.add(comboBoxPlatform);

        labelPlayer = new JLabel("Seleccione un jugador");
        labelPlayer.setBounds(150, 355, 300, 25);
        labelPlayer.setFont(new Font("Courier New", Font.CENTER_BASELINE, 18));
        panel.add(labelPlayer);

        playersArray = new String[] {"1", "2"};
        comboBoxPlayer = new JComboBox(playersArray);
        comboBoxPlayer.setBounds(450, 355, 100, 25);
        panel.add(comboBoxPlayer);

        JButton buttonApplyChanges = new JButton("Aplicar cambios");
        buttonApplyChanges.setBounds(200, 415, 300, 50);
        buttonApplyChanges.addActionListener(new ActionListener() {

            // Create the respective instances of alligators and fruits depending on user administrator selection
            @Override
            public void actionPerformed(ActionEvent e) {

                Integer comboBoxObjectValue = comboBoxObject.getSelectedIndex();
                Integer comboBoxLianaValue = comboBoxLiana.getSelectedIndex();
                Integer comboBoxPlatformValue = comboBoxPlatform.getSelectedIndex();
                Integer comboBoxPlayerValue = comboBoxPlayer.getSelectedIndex();

                Integer positionX = 0;
                Integer positionY = 0;

                // Fruit selected
                if (comboBoxObjectValue == 0) {

                    // Depending on the comboBoxObjectValue, the respective constants will be assigned to the alligator
                    switch (comboBoxPlatformValue) {

                        case 0:
                            positionX = PLATFORM1_X;
                            positionY = PLATFORM1_Y;
                            break;

                        case 1:
                            positionX = PLATFORM2_X;
                            positionY = PLATFORM2_Y;
                            break;

                        case 2:
                            positionX = PLATFORM3_X;
                            positionY = PLATFORM3_Y;
                            break;

                        case 3:
                            positionX = PLATFORM4_X;
                            positionY = PLATFORM4_Y;
                            break;

                        case 4:
                            positionX = PLATFORM5_X;
                            positionY = PLATFORM5_Y;
                            break;

                        case 5:
                            positionX = PLATFORM6_X;
                            positionY = PLATFORM6_Y;
                            break;

                    }

                    Fruit temporalFruit = new Fruit(positionX,positionY);

                    // Add fruit to player1
                    if (comboBoxPlayerValue == 0) {

                        fruits1.set(comboBoxPlatformValue, temporalFruit);

                    // Add fruit to player2
                    } else {

                        fruits1.set(comboBoxPlatformValue, temporalFruit);

                    }

                // Red alligator selected
                } else if (comboBoxObjectValue == 1 || comboBoxObjectValue == 2) {

                    // Depending on the comboBoxObjectValue, the respective constants will be assigned to the alligator
                    switch (comboBoxLianaValue) {

                        case 0:
                            positionX = LIANA1_X;
                            positionY = LIANA1_Y;
                            break;

                        case 1:
                            positionX = LIANA2_X;
                            positionY = LIANA2_Y;
                            break;

                        case 2:
                            positionX = LIANA3_X;
                            positionY = LIANA3_Y;
                            break;

                        case 3:
                            positionX = LIANA4_X;
                            positionY = LIANA4_Y;
                            break;

                        case 4:
                            positionX = LIANA5_X;
                            positionY = LIANA5_Y;
                            break;

                        case 5:
                            positionX = LIANA6_X;
                            positionY = LIANA6_Y;
                            break;

                    }

                    if(comboBoxObjectValue == 1){
                        Alligator temporalAlligator = new Alligator("r", positionX, positionY, SPEED);
                        if (comboBoxPlayerValue == 0) {

                            alligators1.set(comboBoxLianaValue, temporalAlligator);
    
                        // Add red alligator to player2
                        } else {
    
                            alligators2.set(comboBoxLianaValue, temporalAlligator);
    
                        }
                    }
                    else{
                        Alligator temporalAlligator = new Alligator("b", positionX, positionY, SPEED);
                        if (comboBoxPlayerValue == 0) {

                            alligators1.set(comboBoxLianaValue, temporalAlligator);
    
                        // Add red alligator to player2
                        } else {
    
                            alligators2.set(comboBoxLianaValue, temporalAlligator);
    
                        }
                    }

                    // Add red alligator to player1
                    

                // Blue alligator selected
                } 
                /*
                else if (comboBoxObjectValue == 2) {

                    Alligator temporalAlligator = new Alligator("b", positionX, positionY, SPEED);

                    // Add blue alligator to player1
                    if (comboBoxPlayerValue == 0) {

                        alligators1.set(comboBoxLianaValue, temporalAlligator);

                    // Add blue alligator to player2
                    } else {

                        alligators2.set(comboBoxLianaValue, temporalAlligator);

                    }

                }
                */

            }
        });
        panel.add(buttonApplyChanges);

        frame = new JFrame();
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("USUARIO ADMINISTRADOR");
        frame.setResizable(false);
        frame.add(panel);
        frame.setVisible(true);

    }

}
