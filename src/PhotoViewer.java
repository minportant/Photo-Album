/*
 * Homework 5 Min Woo Kim, mk4ed 
 * Sources: Big Java Book, Java Website
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import java.io.*;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PhotoViewer extends JFrame{
    /*
     * Components needed for the Panel is declared
     */
    private PhotographContainer imageAlbum;
    private JLabel mainPic = new JLabel();
    private JLabel thumb1 = new JLabel();
    private JLabel thumb2 = new JLabel();
    private JLabel thumb3 = new JLabel();
    private JLabel thumb4 = new JLabel();
    private JLabel thumb5 = new JLabel();
    private JLabel info1 = new JLabel();
    private JLabel info2 = new JLabel();
    private JLabel info3 = new JLabel();
    private JLabel info4 = new JLabel();
    private JLabel info5 = new JLabel(); 
    private ButtonGroup rbGroup = new ButtonGroup();
    private int index = 0;

    /*
     * Gets the height and width of the users computer screen
     */
    int height = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    int width = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth());

    /*
     * Main method sets up the album for it to be displayed in GUI
     */
    public static void main(String[] args) {
        PhotoViewer myViewer = new PhotoViewer();
        String imageDirectory = "images\\";

        /*
         * Photographs are created.
         */
        Photograph p1 = new Photograph(imageDirectory + "BackAngled.jpg","Back Angled", "2016-03-27", 4);
        Photograph p2 = new Photograph(imageDirectory + "Top.jpg","Top", "2018-03-28", 3);
        Photograph p3 = new Photograph(imageDirectory + "Interior.jpg","Interior", "2018-03-29", 2);
        Photograph p4 = new Photograph(imageDirectory + "FrontAngled.jpg","Front Angle", "2015-03-30", 4);
        Photograph p5 = new Photograph(imageDirectory + "Side.jpg", "Side", "2017-03-21", 5);
        myViewer.imageAlbum = new PhotoLibrary("Koenigsegg Agera RS", 1);

        /*
         * Photos are added to imageAlbum
         */
        myViewer.imageAlbum.addPhoto(p1);
        myViewer.imageAlbum.addPhoto(p2);
        myViewer.imageAlbum.addPhoto(p3);
        myViewer.imageAlbum.addPhoto(p4);
        myViewer.imageAlbum.addPhoto(p5);

        /*
         * Sorts the photos by default sort, which is by date.
         */
        Collections.sort(myViewer.imageAlbum.photos);

        /*
         * lambda declaration of createAndShowGUI
         */
        javax.swing.SwingUtilities.invokeLater(() -> myViewer.createAndShowGUI() );
    }


    /*
     * Creates the GUI with the corresponding size with BorderLayout and shows it to user.
     */
    private void createAndShowGUI() {
        // TODO Auto-generated method stub
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addComponentsToPane(this.getContentPane());
        this.pack();
        this.setVisible(true);
        this.setSize(width, height);
        this.setLayout(new BorderLayout());
    }


    /*
     * Method to add components to pane
     */
    public void addComponentsToPane(Container contentPane) {
        // TODO Auto-generated method stub 

        /*
         *  JPanel for thumbnails are created using the GridBagLayout
         */
        JPanel thumb = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        /*
         *  Panel for the buttons are created
         */
        Panel buttons = new Panel();

        /*
         *  Panel for the radioButtons are created
         */
        Panel radioButton = new Panel();

        /*
         *  Sets mainPic's text (description of the photo) to the first picture in the album.
         */
        mainPic.setText("<html>Caption: " + imageAlbum.photos.get(0).getCaption() + "<br>Date: " + imageAlbum.photos.get(0).getDateTaken() + "<br>Rating: " + imageAlbum.photos.get(0).getRating() + "</html>");

        /*
         *  Calls the updateLabel and updateFiles helper method
         */
        updateLabel();
        updateFiles();

        /*
         *  The setup of the GridbagLayout, adding thumbnails and the description of it underneath each thumbnails.
         */
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        thumb.add(thumb1,c);
        c.gridy = 200;
        thumb.add(info1,c);
        c.gridy = 400;
        thumb.add(thumb2,c);
        c.gridy = 500;
        thumb.add(info2,c);
        c.gridy = 700;
        thumb.add(thumb3,c);
        c.gridy = 800;
        thumb.add(info3,c);
        c.gridy = 1000;
        thumb.add(thumb4,c);
        c.gridy = 1100;
        thumb.add(info4,c);
        c.gridy = 1300;
        thumb.add(thumb5,c);
        c.gridy = 1400;
        thumb.add(info5,c);

        /*
         *  Creates JButton for the Next, Previous, Sort by Date, Sort by Caption, and Sort by Rating.
         */
        JButton nextButton = new JButton("Next");
        JButton previousButton = new JButton("Previous");
        JButton sortDateButton = new JButton("Sort By Date");
        JButton sortCaptionButton = new JButton("Sort By Caption");
        JButton sortRatingButton = new JButton("Sort By Rating");

        /*
         *  Implements ButtonListener on the created JButtons
         */
        nextButton.addActionListener(new ButtonListener());
        previousButton.addActionListener(new ButtonListener());
        sortDateButton.addActionListener(new ButtonListener());
        sortCaptionButton.addActionListener(new ButtonListener());
        sortRatingButton.addActionListener(new ButtonListener());

        /*
         *  Sets Action Command for each of the created JButtons
         */
        nextButton.setActionCommand("next");
        previousButton.setActionCommand("previous");
        sortDateButton.setActionCommand("by date");
        sortCaptionButton.setActionCommand("by caption");
        sortRatingButton.setActionCommand("by rating");

        /*
         *  Adds each of the created JButtons on the panel called buttons
         */
        buttons.add(previousButton);
        buttons.add(nextButton);
        buttons.add(sortDateButton);
        buttons.add(sortCaptionButton);
        buttons.add(sortRatingButton);

        /*
         *  Creates JRadioButtons with the text 1, 2, 3, 4, and 5
         */
        JRadioButton rb1 = new JRadioButton("1");
        JRadioButton rb2 = new JRadioButton("2");
        JRadioButton rb3 = new JRadioButton("3");
        JRadioButton rb4 = new JRadioButton("4");
        JRadioButton rb5 = new JRadioButton("5");  

        /*
         *  Implements ButtonListener on the created JRadioButtons
         */
        rb1.addActionListener(new ButtonListener());
        rb2.addActionListener(new ButtonListener());
        rb3.addActionListener(new ButtonListener());
        rb4.addActionListener(new ButtonListener());
        rb5.addActionListener(new ButtonListener());

        /*
         *  Sets Action Command for each of the created JRadioButtons
         */
        rb1.setActionCommand("one");
        rb2.setActionCommand("two");
        rb3.setActionCommand("three");
        rb4.setActionCommand("four");
        rb5.setActionCommand("five");

        /*
         *  Groups the five JRadioButtons so that it automatically toggles off when the selection is changed.
         */
        rbGroup.add(rb1);
        rbGroup.add(rb2);
        rbGroup.add(rb3);
        rbGroup.add(rb4);
        rbGroup.add(rb5);

        /*
         *  Adds the five JRadioButtons on the panel named radioButton
         */
        radioButton.add(rb1);
        radioButton.add(rb2);
        radioButton.add(rb3);
        radioButton.add(rb4);
        radioButton.add(rb5);

        /*
         *  Sets where the mainPic's description goes, it is currently set to be centered under the current picture.
         */
        mainPic.setHorizontalTextPosition(JLabel.CENTER);
        mainPic.setVerticalTextPosition(JLabel.BOTTOM);

        /*
         *  Finally adds each of the panels to the contentPane, putting thumbnails on the leftside, 
         *  main picture centered, buttons on the top, and radio buttons on the bottom.
         */
        contentPane.add(thumb, BorderLayout.WEST);
        contentPane.add(mainPic, BorderLayout.CENTER);
        contentPane.add(buttons, BorderLayout.NORTH);
        contentPane.add(radioButton, BorderLayout.SOUTH);

        /*
         *  Calls the addMouseClick method that allows the thumbnails to listen to mouse clicks
         */
        addMouseClick();


    }

    /*
     * changeMainImage helper method that takes in a parameter index. 
     * When this method is called, the mainPic's image and description is changed to 
     * the image and description of the pic in the called index of imageAlbum.
     */
    public void changeMainImage(int index) {
        try {
            File currentFile = this.imageAlbum.photos.get(index).getFile();
            BufferedImage buffMain = ImageIO.read(currentFile);
            mainPic.setIcon(new ImageIcon(buffMain.getScaledInstance(3*width/4, 3*height/4,  Image.SCALE_SMOOTH)));
            mainPic.setText("<html>Caption: " + imageAlbum.photos.get(index).getCaption() + "<br>Date: " + imageAlbum.photos.get(index).getDateTaken() + "<br>Rating: " + imageAlbum.photos.get(index).getRating() + "</html>");
        }catch (IOException e){
            System.out.println("IOException: " + e.getLocalizedMessage());
        }
    }

    /*
     * updateLAbel helper method that updates each of the thumbnails detail text to match the photos that it is describing.
     */
    public void updateLabel() {
        info1.setText("<html>Caption: " + imageAlbum.photos.get(0).getCaption() + "<br>Date: " + imageAlbum.photos.get(0).getDateTaken() + "<br>Rating: " + imageAlbum.photos.get(0).getRating() + "</html>");
        info2.setText("<html>Caption: " + imageAlbum.photos.get(1).getCaption() + "<br>Date: " + imageAlbum.photos.get(1).getDateTaken() + "<br>Rating: " + imageAlbum.photos.get(1).getRating() + "</html>");
        info3.setText("<html>Caption: " + imageAlbum.photos.get(2).getCaption() + "<br>Date: " + imageAlbum.photos.get(2).getDateTaken() + "<br>Rating: " + imageAlbum.photos.get(2).getRating() + "</html>");
        info4.setText("<html>Caption: " + imageAlbum.photos.get(3).getCaption() + "<br>Date: " + imageAlbum.photos.get(3).getDateTaken() + "<br>Rating: " + imageAlbum.photos.get(3).getRating() + "</html>");
        info5.setText("<html>Caption: " + imageAlbum.photos.get(4).getCaption() + "<br>Date: " + imageAlbum.photos.get(4).getDateTaken() + "<br>Rating: " + imageAlbum.photos.get(4).getRating() + "</html>");
    }
    
    /*
     *  Helper method that updates the thumbnail images when called.
     */
    public void updateFiles() {
        try {
            /*
             *  First gets the file name in string for each photo from imageAlbum
             */
            File currentFile = imageAlbum.photos.get(0).getFile();
            File thumbp1 = imageAlbum.photos.get(0).getFile();
            File thumbp2 = imageAlbum.photos.get(1).getFile();
            File thumbp3 = imageAlbum.photos.get(2).getFile();
            File thumbp4 = imageAlbum.photos.get(3).getFile();
            File thumbp5 = imageAlbum.photos.get(4).getFile();
            
            /*
             *  creates buffered images by reading each filename
             */
            BufferedImage buffMain = ImageIO.read(currentFile);
            BufferedImage buffImage1 = ImageIO.read(thumbp1);
            BufferedImage buffImage2 = ImageIO.read(thumbp2);
            BufferedImage buffImage3 = ImageIO.read(thumbp3);
            BufferedImage buffImage4 = ImageIO.read(thumbp4);
            BufferedImage buffImage5 = ImageIO.read(thumbp5);
            
            /*
             *  Sets default mainpic to the first image, setting its size bigger than the thumbnails.
             *  Sets thumbnail pictures to each thumb JLabels setting the width and heigh tenth of the screen size.
             */
            mainPic.setIcon(new ImageIcon(buffMain.getScaledInstance(3*width/4, 3*height/4,  Image.SCALE_SMOOTH)));
            thumb1.setIcon(new ImageIcon(buffImage1.getScaledInstance(width/10, height/10,  Image.SCALE_SMOOTH)));
            thumb2.setIcon(new ImageIcon(buffImage2.getScaledInstance(width/10, height/10,  Image.SCALE_SMOOTH)));
            thumb3.setIcon(new ImageIcon(buffImage3.getScaledInstance(width/10, height/10,  Image.SCALE_SMOOTH)));
            thumb4.setIcon(new ImageIcon(buffImage4.getScaledInstance(width/10, height/10,  Image.SCALE_SMOOTH)));
            thumb5.setIcon(new ImageIcon(buffImage5.getScaledInstance(width/10, height/10,  Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            /*
             *  If file isn't read, throw exception
             */
            e.printStackTrace();
        }
    }
    
    /*
     * ButtonListener class for the buttons and the radioButtons.
     */
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            
            /*
             *  Action when the Next button is pressed.
             */
            if (e.getActionCommand().equals("next")) {
                /*
                 *  if index is at the last photo, index is reset to 0, else increment index by 1.
                 */
                if(index == 4) {
                    index = 0;
                }else {
                    index++;
                }
                /*
                 * calls the changeMainImage helper method.
                 */
                changeMainImage(index);
            }

            /*
             *  Action when Previous button is pressed.
             */
            if (e.getActionCommand().equals("previous")) {
                /*
                 *  if index is at the first photo, it sets the index to 4, else decrements index by 1.
                 */
                if(index == 0) {
                    index = 4;
                }else {
                    index--;
                }
                /*
                 *  calls the changeMainImage helper method
                 */
                changeMainImage(index);
            }
            
            /*
             * Each of the Sort Buttons sets the Text by the corresponding sort method,
             * sets the text of the main picture to the first picture,
             * calls updateLabel and updateFiles method,
             * resets the int index to 0,
             * and clears radio button selection so that nothing is already shown pressed
             */
            /*
             *  Action when the Sort by Date button is pressed.
             */
            if (e.getActionCommand().equals("by date")) {
                Collections.sort(imageAlbum.photos);
                mainPic.setText("<html>Caption: " + imageAlbum.photos.get(0).getCaption() + "<br>Date: " + imageAlbum.photos.get(0).getDateTaken() + "<br>Rating: " + imageAlbum.photos.get(0).getRating() + "</html>");
                updateLabel();
                updateFiles();
                index = 0;
                rbGroup.clearSelection();
            }

            /*
             *  Action when the Sort by Caption button is pressed.
             */
            if (e.getActionCommand().equals("by caption")) {
                Collections.sort(imageAlbum.photos, new CompareByCaption());
                mainPic.setText("<html>Caption: " + imageAlbum.photos.get(0).getCaption() + "<br>Date: " + imageAlbum.photos.get(0).getDateTaken() + "<br>Rating: " + imageAlbum.photos.get(0).getRating() + "</html>");
                updateLabel();
                updateFiles();
                index = 0;
                rbGroup.clearSelection();
            }

            /*
             *  Action when the Sort by Rating button is pressed.
             */
            if (e.getActionCommand().equals("by rating")) {
                Collections.sort(imageAlbum.photos, new CompareByRating());
                mainPic.setText("<html>Caption: " + imageAlbum.photos.get(0).getCaption() + "<br>Date: " + imageAlbum.photos.get(0).getDateTaken() + "<br>Rating: " + imageAlbum.photos.get(0).getRating() + "</html>");
                updateLabel();
                updateFiles();
                index = 0;
                rbGroup.clearSelection();
            }

            /*
             * Each of the radio button sets the current photo's rating to the corresponding rating,
             * sets the mainPic's text to the text of the first photo,
             * and calls the updateLabel method
             */
            /*
             *  Action when radio button 1 is pressed.
             */
            if (e.getActionCommand().equals("one")) {
                imageAlbum.photos.get(index).setRating(1);
                mainPic.setText("<html>Caption: " + imageAlbum.photos.get(index).getCaption() + "<br>Date: " + imageAlbum.photos.get(index).getDateTaken() + "<br>Rating: " + imageAlbum.photos.get(index).getRating() + "</html>");
                updateLabel();
            }

            /*
             *  Action when radio button 2 is pressed.
             */
            if (e.getActionCommand().equals("two")) {
                imageAlbum.photos.get(index).setRating(2);
                mainPic.setText("<html>Caption: " + imageAlbum.photos.get(index).getCaption() + "<br>Date: " + imageAlbum.photos.get(index).getDateTaken() + "<br>Rating: " + imageAlbum.photos.get(index).getRating() + "</html>");
                updateLabel();
            }

            /*
             *  Action when radio button 3 is pressed.
             */
            if (e.getActionCommand().equals("three")) {

                imageAlbum.photos.get(index).setRating(3);
                mainPic.setText("<html>Caption: " + imageAlbum.photos.get(index).getCaption() + "<br>Date: " + imageAlbum.photos.get(index).getDateTaken() + "<br>Rating: " + imageAlbum.photos.get(index).getRating() + "</html>");
                updateLabel();
            }

            /*
             *  Action when radio button 4 is pressed.
             */
            if (e.getActionCommand().equals("four")) {
                imageAlbum.photos.get(index).setRating(4);
                mainPic.setText("<html>Caption: " + imageAlbum.photos.get(index).getCaption() + "<br>Date: " + imageAlbum.photos.get(index).getDateTaken() + "<br>Rating: " + imageAlbum.photos.get(index).getRating() + "</html>");
                updateLabel();
            }

            /*
             *  Action when radio button 5 is pressed.
             */
            if (e.getActionCommand().equals("five")) {
                imageAlbum.photos.get(index).setRating(5);
                mainPic.setText("<html>Caption: " + imageAlbum.photos.get(index).getCaption() + "<br>Date: " + imageAlbum.photos.get(index).getDateTaken() + "<br>Rating: " + imageAlbum.photos.get(index).getRating() + "</html>");
                updateLabel();
            }
        }
    }

    /*
     * addMouseClick method that sets what the mouse click on the thumbnails do
     */
    public void addMouseClick() {
        
        /*
         *  When thumb1 is clicked, the mainImage is changed to the picture in the imageAlbum with the index of 0 and sets int index to 0.
         */
        thumb1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                changeMainImage(0);
                rbGroup.clearSelection();
                index = 0;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });
        
        /*
         *  When thumb2 is clicked, the mainImage is changed to the picture in the imageAlbum with the index of 1 and sets int index to 1.
         */
        thumb2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                changeMainImage(1);
                rbGroup.clearSelection();
                index = 1;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });
        
        /*
         *  When thumb3 is clicked, the mainImage is changed to the picture in the imageAlbum with the index of 2 and sets int index to 2.
         */
        thumb3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                changeMainImage(2);
                rbGroup.clearSelection();
                index = 2;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });
        
        /*
         *  When thumb4 is clicked, the mainImage is changed to the picture in the imageAlbum with the index of 3 and sets int index to 3.
         */
        thumb4.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                changeMainImage(3);
                rbGroup.clearSelection();
                index = 3;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });
        
        /*
         *  When thumb5 is clicked, the mainImage is changed to the picture in the imageAlbum with the index of 4 and sets int index to 4.
         */
        thumb5.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                changeMainImage(4);
                rbGroup.clearSelection();
                index = 4;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });
    }
}
