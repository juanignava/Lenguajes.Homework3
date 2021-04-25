#include <gtk/gtk.h>

/*
name: getList
description: function that separates a message into a list for an specific sepration character
inputs: message -> message to analize.
        sep -> separator character.
        list -> resultant list.
*/
void getList(char message[200], char sep[2], char list[10][14]){
  char *token;
  token = strtok(message, sep);
  int i = 0;
  while (token != NULL)
  {
     char copia[200];
     strcpy(copia, token);
     

    strcpy(list[i], token);
    i++;

     token = strtok(NULL, sep);
  }
  
}

/*
name: update monkey.
description: updates the monkey position with the one given in the message.
inputs: monkeyInformation -> splitted part of the message that contains only the monkey information.
        layout -> the layout of the respective window
        deedee_kong -> pointer to the definition of the game monkey
*/
void updateMonkey(char *monkeyInformation, GtkWidget* deedee_kong, GtkWidget* layout){
    char sepInfo[2] = ",";
    char listInfo[10][14];
    getList(monkeyInformation, sepInfo, listInfo);

    if (!(listInfo[1] == "_" || listInfo[2] == "_")){
        int xPos = atoi(listInfo[1]);
        int yPos = atoi(listInfo[2]);

        gtk_fixed_move(GTK_FIXED(layout), deedee_kong, xPos, yPos);
    }
}

/*
name: analize server message
description: splits the gicen message into the respective variables of the game
inputs: player -> number of player the user needs to update.
        message -> the message given by the server.
        window -> the game window of the user request
        layout -> the layout of the respective window
        deedee_kong -> pointer to the definition of the game monkey
*/
void analizeServerMessage(int player, char* message, GtkWidget* window, GtkWidget* layout, GtkWidget* deedee_kong){

    // works only if the message sent has the minimum lenght
    if (strlen(message)> 103)
    {
        // separates the players, the message was contructed with a '/' separator within players
        char sepPlayer[2] = "/";
        char listPlayers[100][14];
        getList(message, sepPlayer, listPlayers);

        char sepComponent[2]=";";
        char listComponent[10][14];
        if (player == 1)
        {
            // Separates the components, the message was contructed with a ';' within components.
            getList(listPlayers[0], sepComponent, listComponent);
            printf("Monkey information: %s\n", listComponent[0]);
            updateMonkey(listComponent[0], deedee_kong, layout);
        }
        if (player == 2)
        {
            
        }
    
    }
    
}