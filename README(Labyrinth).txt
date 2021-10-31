In the program the mazes should not be generated automatically but should be built from
the data that will be provided for the development of the practice. They give us 4 text files that
they contain the information for the construction of 4 different labyrinths (maze1.txt, maze2.txt, ...). The structure
of the data is similar for the 4 files, so the maze1.txt, which corresponds to the maze in fig. 1, contains:
FIG. 1 Game interface
15
10
1011101010101010100010101010101010101100
1001110010011010011010011100110110010110
0101010101011001101001100011011000111000
0101010101010101100110101100100110100110
0101001101100101001111000101010110111100
0101110110010000111001010011011010010100
0101001101100001101001101001110001010101
0011101011000011100010100110001101100101
1001101001101101010110011100100111100101
0001110010110000011001010101000110100110
0101001111000111100101100101010110011110
0011110000111010000011100101010100111100
1011011010011110011110010110001111000101
1001101001101001110000111010110000110100
0011101010100110001110101010011010110110
2
10
The first two lines are the rows and columns respectively, of the grid where the maze is defined,
that is, 15 rows and 10 columns. The two end lines indicate where the exit of the maze is
i.e. in row 2 and column 10 (they start to be listed from 0).
The lines in between correspond to the information on the walls. Each line corresponds to one
row of the maze grid, the row has as many squares as columns the grid. For each box there are 4
values ​​0 or 1 that correspond to the walls of the box in the North, East, South and West directions
respectively. So the box (0,0) has the values ​​1011 that describe the walls it has, 1 means that there are
wall 0 that does not exist, that is, has a wall in the North, has no wall in the East and has a wall in the South and West, therefore the box
(0,0) can be represented as a box of the style:
The following box la (0,1) with values ​​1010 indicates that it only has walls in the North and the South, will be represented
with a box like the one below, and so on for all the squares on the maze grid.
To place the tile in the maze, two random numbers corresponding to one row and one will be generated
grid column and the tab will be placed there. The image shows the tab in position (4.0).
To move the tile the keyboard will be used so that with the 'w' key we move to the North, with 'a' to the East,
with the 's' to the south and with the 'd' to the west. Movement will only be possible if there is no wall that does
prevent. When the exit is reached the game ends and a message indicating that it has been won automatically comes out

Design and classes used
To carry out this practice we will use 5 classes (Labyrinth, Box, Record, Exit and
PracticeFinal2 (with main method)).
Tab and Output: This class has two attributes, a string with the name of the
file with the png image and a BufferedImage. The constructor initializes the
bufferedimage with an imageio.read of a new file with the name of the string of the
file (File: "bolapng.png" and Output: "Salidapng.png"). Finally they have a
paintComponent method in which one graphic object and two
float x and y coordinates, a drawimage is made with the following parameters
bufferedimage, x and y coordinates, size coordinates (47 and 47), and null.
Box: -The box objects are the main elements that make up the
maze based on a matrix structure.
The attributes are as follows:
5 rectangles (4 are the barriers), variable color, Booleans to know if the object
is occupied by a token or an output, a token object and an output, Booleans that
indicate if there is a barrier according to what the file says and an initialized constant
in color.BLACK that will indicate the color of the barriers that is always the same.
Methods:
Constructor: In this you have to pass 9 parameters, 5 rectangle objects, variable
of the color, two Booleans to know the occupancy status and a String. Are
variables are assigned directly to attributes except the string. This String has
length 4 and represents the reading of the file that will help us assign the
Booleans of the different barriers depending on whether they are '0' or '1'. Of the next
way:
this.nort = (s.charAt (0) == '1');
this.este = (s.charAt (1) == '1');
this.sur = (s.charAt (2) == '1');
this.west = (s.charAt (3) == '1');
Another paintComponent method that checks the booleans "busy" and
"Barrier directions" and paint the triangles (attributes) with a parameter
graphics.
Finally setters and getters methods to check and assign states.
Labyrinth: Class that extends a JPanel. Attributes: integers to determine the
dimensions (number of boxes), position of the exit, position of the card, size
total and a constant that represents the size of the cells.
Color, two-dimensional array of bufferedreader box objects and a String to indicate
the file.
-Constructor with IOException: initialization of the string with "maze1.txt", of the color with
the YELLOW and the buferred reader with the string. the first two lines are read and
assign to the integer components of the dimension and the maximums as
DIMENSION * SIDE. Finally the array is initialized with the dimensions.
Double loop to initialize the cells of the array. Integers x and y are created
that contain the size that has been traveled on its respective axis. With every row
a line is read from the file that is stored in a string. In each iteration
initialize a rectangle at position x and y with the same size on the axes = SIDE and
four rectangles of the barriers that are placed in different ways (below or at
the right) and of a narrower size in one of its coordinates. I also know
must divide the string of the row and take the four components that correspond to the
box with the substring method whose parameters are used (j * 4, (j * 4) +4), where j is the
column index in row i. After this, the box of the array is initialized with the
above parameters. Finally, the value is assigned to the integer coordinates of the
Exit. Catch except filenotfound.
-Paintcomponent that iterates over the components of the array and paints them.
-getPreferedSize with the integers of the maximum dimensions
-Getters and setters methods for class attributes.
-Methods that put the output and or the token in its coordinates with the methods
setExit or setFicha.
-empty tab that changes the occupied tab of the array in the coordinates of the tab
-4 boolean methods to know if the position is north, south
… Of the file is available with the negation of the method isNorte () ...
-four methods to move the tile to the corresponding coordinate with
methods seoccupada (false), increase or decrease corresponding tab coordinate
and set tab in new coordinates of the array.
-setFile: everything is initialized again but the string of the file is changed to a string
passed by parameter.
-changecolor: it is built again by changing the color with that of the parameter and the
same with the string of the file.
Class with main: This class extends JFrame and implements KeyListener and
ActionListener.
Labyrinth attributes menubar, menu, menuitem and variable color.

Constructor: we initialize the maze and mark all the parameters of the
JFrame, we add the maze get Size ... We set the coordinates of
the token with a Random initialized with nextDouble up to the dimensions of the
respective coordinate of the maze. We put titles and the tab and the output. We started
the menu and its items. with "resume, change maze and close" options.
KeyRealeased: with the keyboard window per parameter, five
conditionals. Four of them check which key has been pressed and if the zone to which it is
that you want to go is available (.nortedavailable () ...), and the other conditional
check if the coordinates of the token and the output are the same, which in case
affirmative opens a JOptionPane with a victory message.
ActionPerformed: methods for menu items. To change the labyrinth it
open a JFilechooser that saves the string of the file path and calls the method
setfile with the parameter is said string, the token is placed randomly and
repaint.To retry, reposition the tile at other random coordinates
previously emptying the previous position and repainting. To exit, System.exit (0).
Finally, to change the color, conditionals are made to check the color
selected that in positive case for any of them the color variable is changed to
the one selected by the user we call the changecolor method with the parameters
color and getFile (), put the token and output and repaint.