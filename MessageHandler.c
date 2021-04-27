#include <gtk/gtk.h>

/*
name: getList
description: function that separates a message into a list for an specific sepration character
inputs: message -> message to analize.
        sep -> separator character.
        list -> resultant list.
*/

char playerTypeStr[10];
char *playerTypeStrPtr = playerTypeStr;

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
void updateMonkey(char *monkeyInformation, GtkWidget* deedee_kong, GtkWidget *lifesLabel, GtkWidget *scoreLabel,GtkWidget* layout){
    char sepInfo[2] = ",";
    char listInfo[10][14];
    getList(monkeyInformation, sepInfo, listInfo);

    int xPos = atoi(listInfo[1]);
    int yPos = atoi(listInfo[2]);

    if (xPos != 0 && yPos != 0){
        
        gtk_widget_show(deedee_kong);

        gtk_fixed_move(GTK_FIXED(layout), deedee_kong, xPos, yPos);
    }
    else{
        gtk_widget_hide(deedee_kong);
    }
}

/*
name: update fruit
description: function that describes moves the fruit positions.
inputs:
fruitInfo-> infromation given by the server about the fruit position
*/
void updateFruit(char *fruit1Info, GtkWidget* fruit, GtkWidget* layout){

    //printf("The fruit information: %s\n", fruit1Info);
    char sepInfo[2] = ",";
    char listInfo[10][14];
    getList(fruit1Info, sepInfo, listInfo);
    int xPos = atoi(listInfo[1]);
    int yPos = atoi(listInfo[2]);

    if (xPos != 0 && yPos != 0){
        
        gtk_widget_show(fruit);

        gtk_fixed_move(GTK_FIXED(layout), fruit, xPos, yPos);
    }
    else{
        gtk_widget_hide(fruit);
    }
    
}

void updateCroc(char *crocInfo, GtkWidget* croc, GtkWidget* layout){
    //printf("The Croc information: %s\n", crocInfo);
    char sepInfo[2] = ",";
    char listInfo[10][14];
    getList(crocInfo, sepInfo, listInfo);


    int xPos = atoi(listInfo[2]);
    int yPos = atoi(listInfo[3]);

    if (xPos != 0 && yPos != 0){

        if (!gtk_widget_is_visible(croc)){
            if (*listInfo[1] == 'r')
            {
            gtk_image_set_from_file(GTK_IMAGE(croc), "images/green-croc.png");
            }
            else if (*listInfo[1] == 'b') {
            gtk_image_set_from_file(GTK_IMAGE(croc), "images/blue-croc.png");
            }
            //gtk_image_set_from_file(GTK_IMAGE(croc), "images/blue-croc.png");

            gtk_widget_show(croc);
        }
       

        gtk_fixed_move(GTK_FIXED(layout), croc, xPos, yPos);
    }
    else{
        gtk_widget_hide(croc);
    }
    
}

int actualLives = 1;
int socketTime = 300000;
//int *isLiveIncreasedPtr = &isLiveIncreased;

void updateData(char *data, GtkWidget* lifesLabel, GtkWidget* scoreLabel, GtkWidget* window){
    char sepInfo[2] = ",";
    char listInfo[10][14];
    getList(data, sepInfo, listInfo);
    int livesNum = atoi(listInfo[1]);
    int scoreNum = atoi(listInfo[2]);

    printf("Actual Lives: %d\n", actualLives);
    printf("Lives Num: %d\n", livesNum);
    //printf("Is live increased? : %d\n", isLiveIncreased);
    if (actualLives < livesNum){
        
        socketTime -= 50000;
    }


    if(actualLives!= livesNum){
        actualLives = livesNum;
    }

    

    gtk_label_set_text(GTK_LABEL(lifesLabel), listInfo[1]);
    gtk_label_set_text(GTK_LABEL(scoreLabel), listInfo[2]);
    
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
void analizeServerMessage(int player, char* message, GtkWidget* window, GtkWidget* layout, 
GtkWidget* deedee_kong, GtkWidget *fruit1, GtkWidget *fruit2, GtkWidget *fruit3,
GtkWidget *fruit4, GtkWidget *fruit5, GtkWidget *fruit6, GtkWidget *croc1,
GtkWidget *croc2, GtkWidget *croc3, GtkWidget *croc4, GtkWidget *croc5, GtkWidget *croc6,
GtkWidget *lifesLabel, GtkWidget *scoreLabel){

    //printf("The message before: %s\n", message);
    // works only if the message sent has the minimum lenght
    if (  (*message == 'd' && *playerTypeStr == '1') ||
          (*message == '2' && *playerTypeStr == '2') ||
          (*message == 'd' && *playerTypeStr == '3') ||
          (*message == '2' && *playerTypeStr == '4') )
    {
        // separates the players, the message was contructed with a '/' separator within players

        //printf("The message: %s\n", message);
        char sepPlayer[2] = "/";
        char listPlayers[100][14];
        getList(message, sepPlayer, listPlayers);

        char sepComponent[2]=";";
        char listComponent[10][14];
        if (player == 1 || player ==2)
        {
            // Separates the components, the message was contructed with a ';' within components.
            getList(listPlayers[0], sepComponent, listComponent);
            //printf("The monkey information: %s\n", listComponent[0]);
            updateMonkey(listComponent[0], deedee_kong, lifesLabel, scoreLabel, layout);
            updateFruit(listComponent[1], fruit1, layout);
            updateFruit(listComponent[2], fruit2, layout);
            updateFruit(listComponent[3], fruit3, layout);
            updateFruit(listComponent[4], fruit4, layout);
            updateFruit(listComponent[5], fruit5, layout);
            updateFruit(listComponent[6], fruit6, layout);
            updateCroc(listComponent[7], croc1, layout);
            updateCroc(listComponent[8], croc2, layout);
            updateCroc(listComponent[9], croc3, layout);
            updateCroc(listComponent[10], croc4, layout);
            updateCroc(listComponent[11], croc5, layout);
            updateCroc(listComponent[12], croc6, layout);
        }
        if (player == 2)
        {
            
        }
    
    }
    else if (*message == 'l')
    {
        updateData(message, lifesLabel, scoreLabel, window);
    }
    else if (*message == 's')
    {
        updateData(message, lifesLabel, scoreLabel, window);
    }
    
}