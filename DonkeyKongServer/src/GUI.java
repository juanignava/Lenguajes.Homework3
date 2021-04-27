

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private Controller controller;

    /**
     * Description: constructor method.
     * @param controller
     */
     public GUI(Controller controller) {

         this.controller = controller;

         panel = new JPanel();
         panel.setLayout(null);

         // Background
         image = new ImageIcon(getClass().getResource("/Images/ArcadeMachine.png"));
         JLabel background = new JLabel("", image,JLabel.CENTER);
         background.setBounds(0,0, 700, 670);
         panel.add(background);

         // Liana
         labelLiana = new JLabel("Seleccione una liana");
         labelLiana.setBounds(150, 295, 300, 25);
         labelLiana.setFont(new Font("Courier New", Font.CENTER_BASELINE, 18));
         panel.add(labelLiana);

         lianasArray = new String[] {"1", "2", "3", "4", "5", "6"};
         comboBoxLiana = new JComboBox(lianasArray);
         comboBoxLiana.setBounds(450, 295, 100, 25);
         comboBoxLiana.setEnabled(false);
         panel.add(comboBoxLiana);

         // Platform
         labelPlatform = new JLabel("Seleccione una plataforma");
         labelPlatform.setBounds(150, 355, 300, 25);
         labelPlatform.setFont(new Font("Courier New", Font.CENTER_BASELINE, 18));
         panel.add(labelPlatform);

         platformsArray = new String[] {"1", "2", "3", "4", "5", "6"};
         comboBoxPlatform = new JComboBox(platformsArray);
         comboBoxPlatform.setBounds(450, 355, 100, 25);
         comboBoxPlatform.setEnabled(false);
         panel.add(comboBoxPlatform);

         // Object
         labelObject = new JLabel("Seleccione una accion");
         labelObject.setBounds(150, 175, 300, 25);
         labelObject.setFont(new Font("Courier New", Font.CENTER_BASELINE, 18));
         panel.add(labelObject);

         objectsArray = new String[] {"Anadir Fruta", "Eliminar Fruta", "Anadir Cocodrilo Rojo",
                 "Anadir Cocodrilo Azul"};
         comboBoxObject = new JComboBox(objectsArray);
         comboBoxObject.setBounds(400, 175, 150, 25);
         comboBoxObject.addActionListener(new ActionListener() {

             // Enables, disables and add the respective items to the comboBoxes
             @Override
             public void actionPerformed(ActionEvent e) {

                 comboBoxPlayer.setEnabled(true);

                 DefaultComboBoxModel<String> playerModel = new DefaultComboBoxModel<>
                                                           (controller.getDynamicPlayer());
                 comboBoxPlayer.setModel(playerModel);

                 Integer player = comboBoxPlayer.getSelectedIndex();

                 String strComboBoxObjectValue = comboBoxObject.getSelectedItem().toString();

                 // Add alligator case
                 if (strComboBoxObjectValue.contains("Cocodrilo")) {

                     DefaultComboBoxModel<String> lianaModel = new DefaultComboBoxModel<>
                                                               (controller.getDynamicLiana(player));
                     comboBoxLiana.setModel(lianaModel);

                     comboBoxLiana.setEnabled(true);
                     comboBoxPlatform.setEnabled(false);

                     // Add fruit case
                 } else if (strComboBoxObjectValue.equals("Anadir Fruta")) {

                     DefaultComboBoxModel<String> platformModel = new DefaultComboBoxModel<>
                                                                  (controller.getDynamicPlatform(player, "ADD"));
                     comboBoxPlatform.setModel(platformModel);

                     comboBoxLiana.setEnabled(false);
                     comboBoxPlatform.setEnabled(true);

                     // Delete fruit case
                 } else if (strComboBoxObjectValue.equals("Eliminar Fruta")) {

                     DefaultComboBoxModel<String> platformModel = new DefaultComboBoxModel<>
                                                                  (controller.getDynamicPlatform(player, "DELETE"));
                     comboBoxPlatform.setModel(platformModel);

                     comboBoxLiana.setEnabled(false);
                     comboBoxPlatform.setEnabled(true);

                 }

             }

         });
         panel.add(comboBoxObject);


         // Player
         labelPlayer = new JLabel("Seleccione un jugador");
         labelPlayer.setBounds(150, 235, 300, 25);
         labelPlayer.setFont(new Font("Courier New", Font.CENTER_BASELINE, 18));
         panel.add(labelPlayer);

         playersArray = new String[] {"1", "2"};
         comboBoxPlayer = new JComboBox(playersArray);
         comboBoxPlayer.setBounds(450, 235, 100, 25);
         comboBoxPlayer.setEnabled(false);
         comboBoxPlayer.addActionListener(new ActionListener() {

             // Enables, disables and add the respective items to the comboBoxes
             @Override
             public void actionPerformed(ActionEvent e) {

                 Integer player = comboBoxPlayer.getSelectedIndex();

                 DefaultComboBoxModel<String> lianaModel = new DefaultComboBoxModel<>
                                                           (controller.getDynamicLiana(player));
                 comboBoxLiana.setModel(lianaModel);

                 String strComboBoxObjectValue = comboBoxObject.getSelectedItem().toString();

                 // Add fruit case
                 if (strComboBoxObjectValue.equals("Anadir Fruta")) {

                     DefaultComboBoxModel<String> platformModel = new DefaultComboBoxModel<>
                                                                  (controller.getDynamicPlatform(player, "ADD"));
                    comboBoxPlatform.setModel(platformModel);

                     // Delete fruit case
                 } else if (strComboBoxObjectValue.equals("Eliminar Fruta")) {

                     DefaultComboBoxModel<String> platformModel = new DefaultComboBoxModel<>
                                                                  (controller.getDynamicPlatform(player, "DELETE"));
                    comboBoxPlatform.setModel(platformModel);

                 }

             }

         });
         panel.add(comboBoxPlayer);

         JButton buttonApplyChanges = new JButton("Aplicar cambios");
         buttonApplyChanges.setBounds(200, 415, 300, 50);
         buttonApplyChanges.addActionListener(new ActionListener() {

             // Create the respective instances of alligators and fruits depending on user administrator selection
             @Override
             public void actionPerformed(ActionEvent e) {

                 // Dynamic manage information of the comboBoxes
                 String strComboBoxObjectValue = comboBoxObject.getSelectedItem().toString();
                 String strComboBoxPlatformValue = comboBoxPlatform.getSelectedItem().toString();
                 String strComboBoxPlayerValue = comboBoxPlayer.getSelectedItem().toString();

                 Integer intComboBoxLianaValue = Integer.parseInt(comboBoxLiana.getSelectedItem().toString()) - 1;
                 Integer intComboBoxPlatformValue = Integer.parseInt(comboBoxPlatform.getSelectedItem().toString()) - 1;

                 controller.alligatorsFruitsCreation(strComboBoxObjectValue, strComboBoxPlatformValue,
                                                     strComboBoxPlayerValue, intComboBoxLianaValue,
                                                     intComboBoxPlatformValue);

                 // Disables the respective comboBoxes
                 comboBoxPlatform.setEnabled(false);
                 comboBoxLiana.setEnabled(false);
                 comboBoxPlayer.setEnabled(false);

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
