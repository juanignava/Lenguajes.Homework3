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
char activeMessage[10] = "a"; // requests message
char* activeMessagePtr = activeMessage;

/*
name: send message to server
description: function that sends requests to the server
and receives the answer
*/
void *sendMessageToServer(void *vargp)
{
    char message[MAXCHAR]="";
    int messageLength;
    int messageLengthAux;

    while (true)
    {
        // send request to server
        bzero(message, sizeof(message));
        messageLength = 0;
        while ((message[messageLength++]) != '\0');
        messageLength++;
        messageLengthAux = htonl(messageLength);
        write(socketFileDescriptor, (char *)&messageLengthAux, sizeof(messageLength));
        write(socketFileDescriptor, activeMessage, messageLength); // sends the activeMessage to the server

        // read server answer
        read(socketFileDescriptor, (char *)&messageLengthAux, sizeof(int));
        messageLength = ntohl(messageLengthAux);
        read(socketFileDescriptor, message, messageLength);
        printf("From Server: %s\n", message); //The client saves the server's answer in message
        sleep(1);
        strcpy(activeMessagePtr, "a"); // Restores the activeMessage variable into an "a" for updating
    }
}