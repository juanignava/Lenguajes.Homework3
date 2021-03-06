#include "Constants.c"
#include "Socket.c"
#include "MessageHandler.c"

#include <gtk/gtk.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <stdbool.h>

// GUI variables
#define SA struct sockaddr

// Deedee kong position structure
struct DK_POSITION
{
    int x_pos;
    int y_pos;
} 
DK_POSITION = {DK_INITIALX, DK_INITIALY};

// Active player variables, the define the maximum of
// players and observers
bool isPlayer1Active = false;
bool *isPlayer1ActivePtr = &isPlayer1Active;
bool isPlayer2Active = false;
bool *isPlayer2ActivePtr = &isPlayer2Active;
int player1ObserverCont = 0;
int *player1ObserverContPtr = &player1ObserverCont;
int player2ObserverCont = 0;
int *player2ObserverContPtr = &player2ObserverCont;

// integer with the type of player
int playerType;

// Widgets Definitions for the GUI
GtkWidget* window, *layout, *deedee_kong, *lifesLabel, *scoreLabel;
GtkWidget *fruit1, *fruit2, *fruit3, *fruit4, *fruit5, *fruit6;
GtkWidget *croc1, *croc2, *croc3, *croc4, *croc5, *croc6;
GtkWidget *fruitList[6];

/*
name: key pressed
description: event used to control the first player of the game
inputs:
*/
void key_pressed1(GtkWidget *widget, GdkEventKey *event, gpointer data)
{
    // up command
    if (event->keyval == GDK_KEY_s)
    {
        strcpy(activeMessagePtr, "u1");
    }

    // left command
    else if (event->keyval == GDK_KEY_a)
    {
        strcpy(activeMessagePtr, "l1");
    }

    // down command
    else if (event->keyval == GDK_KEY_w)
    {
        strcpy(activeMessagePtr, "d1");
    }

    // right command
    else if (event->keyval == GDK_KEY_d)
    {
        strcpy(activeMessagePtr, "r1");
    }
}

/*
name: key pressed
description: event used to control the second player of the game
inputs:
*/
void key_pressed2(GtkWidget *widget, GdkEventKey *event, gpointer data)
{
    // going up
    if (event->keyval == GDK_KEY_s)
    {
        strcpy(activeMessagePtr, "u2");
    }
    // going left
    else if (event->keyval == GDK_KEY_a)
    {
        strcpy(activeMessagePtr, "l2");
    }
    // going down
    else if (event->keyval == GDK_KEY_w)
    {
        strcpy(activeMessagePtr, "d2");
    }
    // going right
    else if (event->keyval == GDK_KEY_d)
    {
        strcpy(activeMessagePtr, "r2");
    }
}

/*
name: change active variables
description: changes the variables to know if one player is already active or not
input: userID -> integer that charactarizes each role
*/
void changeActiveVariables(int userID)
{
    switch (userID)
    {
    case PLAYER1_ID:
        // update player 1 command
        strcpy(updateMessagePtr, "a1");
        strcpy(playerTypeStrPtr, "1");
        isPlayer1Active = true;

        break;
    case PLAYER2_ID:
        // update player 2 command
        strcpy(updateMessagePtr, "a2");
        strcpy(playerTypeStrPtr, "2");
        isPlayer2Active = true;

        break;
    case OBSERVER_PLAYER_1:
        // update player 1 command
        strcpy(updateMessagePtr, "a1");
        strcpy(playerTypeStrPtr, "3");
        player1ObserverCont++;
        break;
    case OBSERVER_PLAYER_2:
        // update player 2 command
        strcpy(updateMessagePtr, "a2");
        strcpy(playerTypeStrPtr, "4");
        player2ObserverCont++;
        break;
    default:
        break;
    }
}

/*
name: update components
description: thread function that keeps updating the GUI
components that move by instruction of the server
*/
void *updateComponents1(void *vargp)
{
    while (TRUE)
    {
        analizeServerMessage(1, messagePtr, window, layout, deedee_kong, fruit1,
        fruit2, fruit3, fruit4, fruit5, fruit6, croc1, croc2, croc3, croc4,
        croc5, croc6, lifesLabel, scoreLabel);
        
        usleep(300000);
    }   
}

/*
name: update components
description: thread function that keeps updating the GUI
components that move by instruction of the server
*/
void *updateComponents2(void *vargp)
{
    while (TRUE)
    {
        analizeServerMessage(2 ,messagePtr, window, layout, deedee_kong, fruit1,
        fruit2, fruit3, fruit4, fruit5, fruit6, croc1, croc2, croc3, croc4,
        croc5, croc6, lifesLabel, scoreLabel);
        usleep(300000);
    }   
}

/*
name: update data
description: asks the server to update the GUI with the data given
about the score and player lives
*/
void *updateData1(void *vargp)
{
    while (TRUE)
    {
        socketSleepTime = socketTime;

        if(actualLives == 0){
            gtk_label_set_text(GTK_LABEL(lifesLabel), "GAME OVER");
            strcpy(activeMessagePtr, "p");
            sleep(2);
            gtk_window_close(GTK_WINDOW(window));
        }
        strcpy(activeMessagePtr, "sc");
        usleep(700000);
    }   
}

/*
name: update data 2
description: asks the server to update the GUI with the data given
about the score and player lives
*/
void *updateData2(void *vargp)
{
    while (TRUE)
    {
        socketSleepTime = socketTime;
        
        if(actualLives == 0){
            gtk_label_set_text(GTK_LABEL(lifesLabel), "GAME OVER");
            strcpy(activeMessagePtr, "o");
            sleep(2);
            gtk_window_close(GTK_WINDOW(window));
        }
        strcpy(activeMessagePtr, "kc");
        usleep(700000);
    }   
}

/*
name: exit button clicked
description: click event function of the exit button
*/
static void exitButtonClicked(GtkWidget *widget, gpointer data)
{
    gtk_window_close(GTK_WINDOW(window));
}

/*
Name: game window.
Description: creates a game window for a player or observer.
Param: option -> determines which kind of window has to load
1-> player 1
2-> player 2
3-> player 1 observer
4-> player 2 observer
*/
int gameWindow(int option)
{
    // Define the active players and observers
    changeActiveVariables(option);

    // Define the GUI variables
    GtkWidget *rope_1, *rope_2, *rope_3, *rope_4;
    GtkWidget *base_1, *base_2, *base_3, *base_4, *base_5, *base_6, *long_base;
    GtkWidget *donkey_kong, *livesLabelAux, *scoreLabelAux, *exitButton;
    
    // window definition
    window = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    gtk_window_set_resizable(GTK_WINDOW(window), FALSE);
    gtk_widget_add_events(window, GDK_KEY_PRESS_MASK);
    

    // Add the respective kay pressed event only if the user is a player
    if (option == 1)
    {
        g_signal_connect(window, "key_press_event", G_CALLBACK(key_pressed1), NULL);

    }
    else if (option == 2)
    {
        g_signal_connect(window, "key_press_event", G_CALLBACK(key_pressed2), NULL);
    }
    
    gtk_widget_set_size_request(window, WINDOW_HEIGHT, WINDOW_WIDTH);

    // layout definition
    layout = gtk_fixed_new();

    // labels
    livesLabelAux = gtk_label_new("Lifes left: ");
    lifesLabel = gtk_label_new("");
    gtk_fixed_put(GTK_FIXED(layout), livesLabelAux, 800, 50);
    gtk_fixed_put(GTK_FIXED(layout), lifesLabel, 890, 50);

    scoreLabelAux = gtk_label_new("Score: ");
    scoreLabel = gtk_label_new("");
    gtk_fixed_put(GTK_FIXED(layout), scoreLabelAux, 800, 100);
    gtk_fixed_put(GTK_FIXED(layout), scoreLabel, 890, 100);

    // exit button
    exitButton = gtk_button_new_with_label("Exit Game");
    g_signal_connect(exitButton, "clicked", G_CALLBACK(exitButtonClicked), NULL);
    gtk_fixed_put(GTK_FIXED(layout), exitButton, 850, 500);


    // bases definitions
    base_1 = gtk_image_new_from_file("images/base.png");
    gtk_fixed_put(GTK_FIXED(layout), base_1, WIDTH_MARGIN, BASE_HEIGHT_1);
    base_2 = gtk_image_new_from_file("images/base.png");
    gtk_fixed_put(GTK_FIXED(layout), base_2, WIDTH_MARGIN + BASE_WIDTH + ROPE_WIDTH, BASE_HEIGHT_2);
    base_3 = gtk_image_new_from_file("images/base.png");
    gtk_fixed_put(GTK_FIXED(layout), base_3, WIDTH_MARGIN + 2*BASE_WIDTH + 2*ROPE_WIDTH, BASE_HEIGHT_1);
    base_4 = gtk_image_new_from_file("images/base.png");
    gtk_fixed_put(GTK_FIXED(layout), base_4, WIDTH_MARGIN + 3*BASE_WIDTH + 3*ROPE_WIDTH, BASE_HEIGHT_2);
    base_5 = gtk_image_new_from_file("images/base.png");
    gtk_fixed_put(GTK_FIXED(layout), base_5, WIDTH_MARGIN + 4*BASE_WIDTH + 4*ROPE_WIDTH, BASE_HEIGHT_1);
    base_6 = gtk_image_new_from_file("images/base.png");
    gtk_fixed_put(GTK_FIXED(layout), base_6, WIDTH_MARGIN + 4*BASE_WIDTH + 4*ROPE_WIDTH, BASE_HEIGHT_3);
    long_base = gtk_image_new_from_file("images/long_base.png");
    gtk_fixed_put(GTK_FIXED(layout), long_base, WIDTH_MARGIN, HEIGHT_MARGIN);

    // ropes definitions
    rope_1 = gtk_image_new_from_file("images/rope.png");
    gtk_fixed_put(GTK_FIXED(layout), rope_1, WIDTH_MARGIN + BASE_WIDTH, ROPE_HEIGHT);
    rope_2 = gtk_image_new_from_file("images/rope.png");
    gtk_fixed_put(GTK_FIXED(layout), rope_2, WIDTH_MARGIN + 2*BASE_WIDTH + ROPE_WIDTH, ROPE_HEIGHT);
    rope_3 = gtk_image_new_from_file("images/rope.png");
    gtk_fixed_put(GTK_FIXED(layout), rope_3, WIDTH_MARGIN + 3*BASE_WIDTH + 2*ROPE_WIDTH, ROPE_HEIGHT);
    rope_4 = gtk_image_new_from_file("images/rope.png");
    gtk_fixed_put(GTK_FIXED(layout), rope_4, WIDTH_MARGIN + 4*BASE_WIDTH + 3*ROPE_WIDTH, ROPE_HEIGHT);

    // friuts definitions
    fruit1 = gtk_image_new_from_file("images/apple.png");
    gtk_fixed_put(GTK_FIXED(layout), fruit1, WIDTH_MARGIN + BASE_WIDTH, ROPE_HEIGHT);
    fruit2 = gtk_image_new_from_file("images/pineapple.png");
    gtk_fixed_put(GTK_FIXED(layout), fruit2, WIDTH_MARGIN + BASE_WIDTH, ROPE_HEIGHT);
    fruit3 = gtk_image_new_from_file("images/banana.png");
    gtk_fixed_put(GTK_FIXED(layout), fruit3, WIDTH_MARGIN + BASE_WIDTH, ROPE_HEIGHT);
    fruit4 = gtk_image_new_from_file("images/apple.png");
    gtk_fixed_put(GTK_FIXED(layout), fruit4, WIDTH_MARGIN + BASE_WIDTH, ROPE_HEIGHT);
    fruit5 = gtk_image_new_from_file("images/banana.png");
    gtk_fixed_put(GTK_FIXED(layout), fruit5, WIDTH_MARGIN + BASE_WIDTH, ROPE_HEIGHT);
    fruit6 = gtk_image_new_from_file("images/pineapple.png");
    gtk_fixed_put(GTK_FIXED(layout), fruit6, WIDTH_MARGIN + BASE_WIDTH, ROPE_HEIGHT);
    fruitList[0] = fruit1;
    fruitList[1] = fruit2;
    fruitList[2] = fruit3;
    fruitList[3] = fruit4;
    fruitList[4] = fruit5;
    fruitList[5] = fruit6;

    // Crocodile deifinitions
    croc1 = gtk_image_new_from_file("images/green-croc.png");
    gtk_fixed_put(GTK_FIXED(layout), croc1, WIDTH_MARGIN + BASE_WIDTH, ROPE_HEIGHT);
    croc2 = gtk_image_new_from_file("images/green-croc.png");
    gtk_fixed_put(GTK_FIXED(layout), croc2, WIDTH_MARGIN + BASE_WIDTH, ROPE_HEIGHT);
    croc3 = gtk_image_new_from_file("images/green-croc.png");
    gtk_fixed_put(GTK_FIXED(layout), croc3, WIDTH_MARGIN + BASE_WIDTH, ROPE_HEIGHT);
    croc4 = gtk_image_new_from_file("images/green-croc.png");
    gtk_fixed_put(GTK_FIXED(layout), croc4, WIDTH_MARGIN + BASE_WIDTH, ROPE_HEIGHT);
    croc5 = gtk_image_new_from_file("images/green-croc.png");
    gtk_fixed_put(GTK_FIXED(layout), croc5, WIDTH_MARGIN + BASE_WIDTH, ROPE_HEIGHT);
    croc6 = gtk_image_new_from_file("images/green-croc.png");
    gtk_fixed_put(GTK_FIXED(layout), croc6, WIDTH_MARGIN + BASE_WIDTH, ROPE_HEIGHT);


    // dondekey kong and deedee kong image definition
    donkey_kong = gtk_image_new_from_file("images/donkey_kong.png");
    gtk_fixed_put(GTK_FIXED(layout), donkey_kong, 850, 120);
    deedee_kong = gtk_image_new_from_file("images/deedee_kong.png");
    gtk_fixed_put(GTK_FIXED(layout), deedee_kong, DK_POSITION.x_pos, DK_POSITION.y_pos);

    // show images in the window
    gtk_container_add(GTK_CONTAINER(window), layout);
    if (option == 1) gtk_window_set_title(GTK_WINDOW(window), "Player 1");
    if (option == 2) gtk_window_set_title(GTK_WINDOW(window), "Player 2");
    if (option == 3) gtk_window_set_title(GTK_WINDOW(window), "Oobserver Player 1");
    if (option == 4) gtk_window_set_title(GTK_WINDOW(window), "Observer Player 2");
    gtk_widget_show_all(window);
    gtk_widget_hide(deedee_kong);
    gtk_widget_hide(fruit1);
    gtk_widget_hide(fruit2);
    gtk_widget_hide(fruit3);
    gtk_widget_hide(fruit4);
    gtk_widget_hide(fruit5);
    gtk_widget_hide(fruit6);
    gtk_widget_hide(croc1);
    gtk_widget_hide(croc2);
    gtk_widget_hide(croc3);
    gtk_widget_hide(croc4);
    gtk_widget_hide(croc5);
    gtk_widget_hide(croc6);


    // Create the respective message request thread depending on the game
    // the user wnats to see
    if (option == 1)
    {
        pthread_t updateGUI_thread;
        pthread_create(&updateGUI_thread, NULL, updateComponents1, NULL);
        pthread_t updateData_thread;
        pthread_create(&updateData_thread, NULL, updateData1, NULL);

    }
    else if (option == 3){
        pthread_t updateGUI_thread;
        pthread_create(&updateGUI_thread, NULL, updateComponents1, NULL);
        pthread_t updateData_thread;
        pthread_create(&updateData_thread, NULL, updateData1, NULL);
    }
    else if (option == 2 || option == 4)
    {
        pthread_t updateGUI_thread;
        pthread_create(&updateGUI_thread, NULL, updateComponents2, NULL);
        pthread_t updateData_thread;
        pthread_create(&updateData_thread, NULL, updateData2, NULL);
    }
    
    gtk_main();

    return 0;
}
