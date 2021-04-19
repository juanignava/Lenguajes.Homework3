#include <gtk/gtk.h>

// Constants
const int WINDOW_HEIGHT = 1000;
const int WINDOW_WIDTH = 650;
const int WIDTH_MARGIN = 50;
const int HEIGHT_MARGIN = 25;
const int BASE_HEIGHT_1 = 605;
const int BASE_HEIGHT_2 = 335;
const int BASE_HEIGHT_3 = 200;
const int BASE_WIDTH = 164;
const int ROPE_WIDTH = 20;
const int ROPE_HEIGHT = 35;
const struct DK_POSITION
{
    int x_pos;
    int y_pos;
} DK_POSITION = {50,565};


GtkWidget* window, *layout, *deedee_kong;

int main(int argc, char* argv[])
{
    gtk_init(&argc, &argv);

    GtkWidget *rope_1, *rope_2, *rope_3, *rope_4;
    GtkWidget *base_1, *base_2, *base_3, *base_4, *base_5, *base_6, *long_base;
    GtkWidget *donkey_kong;
    
    window = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    gtk_window_set_resizable(GTK_WINDOW(window), FALSE);
    gtk_widget_add_events(window, GDK_KEY_PRESS_MASK);
    g_signal_connect(window, "delete-event", G_CALLBACK(gtk_main_quit), NULL);
    gtk_widget_set_size_request(window, WINDOW_HEIGHT, WINDOW_WIDTH);

    layout = gtk_fixed_new();

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

    rope_1 = gtk_image_new_from_file("images/rope.png");
    gtk_fixed_put(GTK_FIXED(layout), rope_1, WIDTH_MARGIN + BASE_WIDTH, ROPE_HEIGHT);
    rope_2 = gtk_image_new_from_file("images/rope.png");
    gtk_fixed_put(GTK_FIXED(layout), rope_2, WIDTH_MARGIN + 2*BASE_WIDTH + ROPE_WIDTH, ROPE_HEIGHT);
    rope_3 = gtk_image_new_from_file("images/rope.png");
    gtk_fixed_put(GTK_FIXED(layout), rope_3, WIDTH_MARGIN + 3*BASE_WIDTH + 2*ROPE_WIDTH, ROPE_HEIGHT);
    rope_4 = gtk_image_new_from_file("images/rope.png");
    gtk_fixed_put(GTK_FIXED(layout), rope_4, WIDTH_MARGIN + 4*BASE_WIDTH + 3*ROPE_WIDTH, ROPE_HEIGHT);

    donkey_kong = gtk_image_new_from_file("images/donkey_kong.png");
    gtk_fixed_put(GTK_FIXED(layout), donkey_kong, 850, 120);
    deedee_kong = gtk_image_new_from_file("images/deedee_kong.png");
    gtk_fixed_put(GTK_FIXED(layout), deedee_kong, DK_POSITION.x_pos, DK_POSITION.y_pos);

    long_base = gtk_image_new_from_file("images/long_base.png");
    gtk_fixed_put(GTK_FIXED(layout), long_base, WIDTH_MARGIN, HEIGHT_MARGIN);

    gtk_container_add(GTK_CONTAINER(window), layout);
    
    gtk_window_set_title(GTK_WINDOW(window), "Button Tutorial");
    gtk_widget_show_all(window);
    gtk_main();
    return 0;
}
