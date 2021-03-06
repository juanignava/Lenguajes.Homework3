#include "Constants.c"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <stdbool.h>

#define SA struct sockaddr


// Variables
int socketFileDescriptor;
char updateMessage[10];
char *updateMessagePtr = updateMessage;
char activeMessage[10] = "a1"; // requests message
char* activeMessagePtr = activeMessage;

char message[MAXCHAR]="";
char* messagePtr = message;

int socketSleepTime = 300000;

/*
name: send message to server
description: function that sends requests to the server
and receives the answer
*/
void *sendMessageToServer(void *vargp)
{
    //char message[MAXCHAR]="";
    int messageLength;
    int messageLengthAux;

    while (true)
    {
        // send request to server
        bzero(message, sizeof(message));
        messageLength = 0;
        while ((message[messageLength++]) != '\0');
        messageLength++;
        messageLength++;
        messageLengthAux = htonl(messageLength);
        write(socketFileDescriptor, (char *)&messageLengthAux, sizeof(messageLength));
        write(socketFileDescriptor, activeMessage, messageLength); // sends the activeMessage to the server

        // read server answer
        read(socketFileDescriptor, (char *)&messageLengthAux, sizeof(int));
        messageLength = ntohl(messageLengthAux);
        read(socketFileDescriptor, message, messageLength);
        
        // Restores the activeMessage variable into an "a1" or "a2" for updating
        strcpy(activeMessagePtr, updateMessage); 
        usleep(socketSleepTime);
        
    }
}