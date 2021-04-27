// Internal Files Imports
#include "GUI.c"
//#include "Socket.c"

// External Libraries Imports
#include <gtk/gtk.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#include <arpa/inet.h>


// Global Variables Definition
#define SA struct sockaddr
int socketFileDescriptor;

// UI components
GtkWidget *mainWindow, *mainLabel, *mainLayout,
    *player1Button, 
    *player2Button,
    *observerPl1,
    *observerPl2;

/*
Name: player 1 button clicked.
Description: process that begins when an user clicks the first
player option.
*/
static void player1Button_clicked(GtkWidget* widget, gpointer data)
{
    if (!*isPlayer1ActivePtr)
    {
        //gtk_window_close(GTK_WINDOW(mainWindow));
        gtk_widget_hide(mainWindow);
        printf("In player 1\n");
        strcpy(activeMessagePtr, "1");
        pthread_t sender_thread;
        pthread_create(&sender_thread, NULL, sendMessageToServer, NULL);
        gameWindow(PLAYER1_ID);
        
    }
    else{
        printf("Player 1 is currently active");
    }

}

/*
Name: player 2 button clicked.
Description: process that begins when an user clicks the second
player option.
*/
static void player2Button_clicked(GtkWidget* widget, gpointer data)
{
    if (!*isPlayer2ActivePtr)
    {
        gtk_widget_hide(mainWindow);
        strcpy(activeMessagePtr, "2");
        pthread_t sender_thread;
        pthread_create(&sender_thread, NULL, sendMessageToServer, NULL);
        gameWindow(PLAYER2_ID);
    }
    else{
        printf("Player 2 is currently active");
    }
    
}

/*
Name: observer 1 button clicked.
Description: process that begins when an user clicks the observe
first player option
*/
static void Observer1Button_clicked(GtkWidget* widget, gpointer data)
{
    if (true)
    {
        //gtk_window_close(GTK_WINDOW(mainWindow));
        gtk_widget_hide(mainWindow);
        strcpy(activeMessagePtr, "3");
        pthread_t sender_thread;
        pthread_create(&sender_thread, NULL, sendMessageToServer, NULL);
        gameWindow(OBSERVER_PLAYER_1);
    }
    else{
        gtk_widget_set_sensitive (widget, FALSE);
        printf("Reached maximum player 1 observers");
    }
    
}

/*
Name: observer 2 button clicked.
Description: process that begins when an user clicks the observe
second player option
*/
static void Observer2Button_clicked(GtkWidget* widget, gpointer data)
{
    if (true)
    {
        gtk_widget_hide(mainWindow);
        strcpy(activeMessagePtr, "4");
        pthread_t sender_thread;
        pthread_create(&sender_thread, NULL, sendMessageToServer, NULL);
        gameWindow(OBSERVER_PLAYER_2);
    }
    else{
        printf("Reached maximum player 2 observers");
    }
    
}

/*
Name: begin main window.
Description: displays the menu window of the game in here
the user can decide to be a player or an observer.
*/
int beginMainWindow(){
    mainWindow = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    gtk_window_set_resizable(GTK_WINDOW(mainWindow), FALSE);
    gtk_widget_add_events(mainWindow, GDK_KEY_PRESS_MASK);

    player1Button = gtk_button_new_with_label("Player 1");
    player2Button = gtk_button_new_with_label("Player 2");
    observerPl1 = gtk_button_new_with_label("ObservePlayer 1");
    observerPl2 = gtk_button_new_with_label("Observe Player 2");
    mainLabel = gtk_label_new("Please Select a Player");

    //gtk_widget_set_sensitive (player2Button, FALSE);
    //gtk_widget_set_sensitive (observerPl2, FALSE);

    mainLayout = gtk_box_new(TRUE, 0);
    
    g_signal_connect(mainWindow, "delete-event", G_CALLBACK(gtk_main_quit), NULL);
    g_signal_connect(player1Button, "clicked", G_CALLBACK(player1Button_clicked), NULL);
    g_signal_connect(player2Button, "clicked", G_CALLBACK(player2Button_clicked), NULL);
    g_signal_connect(observerPl1, "clicked", G_CALLBACK(Observer1Button_clicked), NULL);
    g_signal_connect(observerPl2, "clicked", G_CALLBACK(Observer2Button_clicked), NULL);

    gtk_box_pack_start(GTK_BOX(mainLayout), mainLabel, TRUE, TRUE, 0);
    gtk_box_pack_start(GTK_BOX(mainLayout), player1Button, TRUE, TRUE, 0);
    gtk_box_pack_start(GTK_BOX(mainLayout), player2Button, TRUE, TRUE, 0);
    gtk_box_pack_start(GTK_BOX(mainLayout), observerPl1, TRUE, TRUE, 0);
    gtk_box_pack_start(GTK_BOX(mainLayout), observerPl2, TRUE, TRUE, 0);

    gtk_container_add(GTK_CONTAINER(mainWindow), mainLayout);
    gtk_widget_set_size_request(mainWindow, 300, 300);
    
    gtk_window_set_title(GTK_WINDOW(mainWindow), "DonCE Kong menu");
    gtk_widget_show_all(mainWindow);
    gtk_main();
}

/*
Name: main
Description: Starts the program, the client and the menu GUI
*/
int main(int argc, char* argv[])
{
    // Connects with the server
    struct sockaddr_in serverAddress;
    socketFileDescriptor = socket(AF_INET, SOCK_STREAM, 0);

    if (socketFileDescriptor == -1) 
    {
        printf("socket creation failed...\n");
        exit(0);
        return 0;
    }

    printf("Socket successfully created..\n");
    bzero(&serverAddress, sizeof(serverAddress));
    serverAddress.sin_family = AF_INET;
    serverAddress.sin_addr.s_addr = inet_addr("127.0.0.1");
    serverAddress.sin_port = htons(PORT);

    if (connect(socketFileDescriptor, (SA*)&serverAddress, sizeof(serverAddress)) != 0) {
        printf("connection with the server failed...\n");
        exit(0);
        return 0;
    }

    printf("connected to the server..\n");
    gtk_init(&argc, &argv);
    beginMainWindow(); // Loads menu
    return 0;
}