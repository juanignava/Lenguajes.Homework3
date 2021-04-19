#include <gtk/gtk.h>

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
    gtk_widget_set_size_request(window, 1000, 650);

    layout = gtk_fixed_new();

    base_1 = gtk_image_new_from_file("images/base.png");
    gtk_fixed_put(GTK_FIXED(layout), base_1, 50, 605);
    base_2 = gtk_image_new_from_file("images/base.png");
    gtk_fixed_put(GTK_FIXED(layout), base_2, 234, 335);
    base_3 = gtk_image_new_from_file("images/base.png");
    gtk_fixed_put(GTK_FIXED(layout), base_3, 418, 605);
    base_4 = gtk_image_new_from_file("images/base.png");
    gtk_fixed_put(GTK_FIXED(layout), base_4, 602, 335);
    base_5 = gtk_image_new_from_file("images/base.png");
    gtk_fixed_put(GTK_FIXED(layout), base_5, 786, 605);
    base_6 = gtk_image_new_from_file("images/base.png");
    gtk_fixed_put(GTK_FIXED(layout), base_6, 786, 200);

    rope_1 = gtk_image_new_from_file("images/rope.png");
    gtk_fixed_put(GTK_FIXED(layout), rope_1, 214, 35);
    rope_2 = gtk_image_new_from_file("images/rope.png");
    gtk_fixed_put(GTK_FIXED(layout), rope_2, 398, 35);
    rope_3 = gtk_image_new_from_file("images/rope.png");
    gtk_fixed_put(GTK_FIXED(layout), rope_3, 582, 35);
    rope_4 = gtk_image_new_from_file("images/rope.png");
    gtk_fixed_put(GTK_FIXED(layout), rope_4, 766, 35);

    donkey_kong = gtk_image_new_from_file("images/donkey_kong.png");
    gtk_fixed_put(GTK_FIXED(layout), donkey_kong, 850, 120);
    deedee_kong = gtk_image_new_from_file("images/deedee_kong.png");
    gtk_fixed_put(GTK_FIXED(layout), deedee_kong, 50, 565);

    long_base = gtk_image_new_from_file("images/long_base.png");
    gtk_fixed_put(GTK_FIXED(layout), long_base, 50, 25);

    gtk_container_add(GTK_CONTAINER(window), layout);
    
    gtk_window_set_title(GTK_WINDOW(window), "Button Tutorial");
    gtk_widget_show_all(window);
    gtk_main();
    return 0;
}
