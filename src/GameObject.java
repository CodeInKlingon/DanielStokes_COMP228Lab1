import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameObject {

    //properties
    private Vector2 center;
    private Vector2 velocity;
    private ObjectState state;


    //method called by main game loop to update game objects position
    public void Update(){

        double newPosX = center.x + velocity.x;
        if(newPosX > 480)
            newPosX = 0;
        double newPosY = center.y + velocity.y;
        if(newPosY > 480)
            newPosY = 0;
        center = new Vector2(newPosX, newPosY);
    }

    //constructor for cerating new game object instances
    public GameObject(double cX,double cY,double vX,double vY){
        center = new Vector2(cX, cY);
        velocity = new Vector2(vX,vY);
        state = ObjectState.Alive;
    }

    //string override method to output gameobject to string;
    public String getGameObject(){
        String stateString = (state == ObjectState.Alive ? "Alive": "Dead");
        return "Center: " + center + "| velocity: " + velocity + "| State: " + stateString;
    }



    //getters and setters
    public Vector2 getCenter() {
        return center;
    }

    public void setCenter(Vector2 center) {
        this.center = center;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public ObjectState getState() {
        return state;
    }

    public void setState(ObjectState state) {
        this.state = state;
    }
}

//vector 2 class representing the x and y coordinate values
class Vector2{

    public double x;
    public double y;

    //constructor for creating vector2 data types
    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    //string output of this vector2 data type
    public String toString(){
        return String.format("%.4f" , x) + String.format("%.4f" , y);
    }
}

//object state enum
enum ObjectState{
    Alive,
    Dead
}

//driver class to creat window and update window every 16ms
class Driver3{

    public static void main(String[] args) throws InterruptedException {

        //array of gameobjects
        GameObject[] objects = new GameObject[10];

        //array of Jpanel for each gameobject
        JPanel[] panels = new JPanel[10];

        //counting variable for tracking howmany gameobjects we have initialized
        final int[] objectCount = {0};

        //main frame window that the game takes place inside.
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(500,500);

        //quit button
        JButton btnQuit = new JButton();
        btnQuit.setBounds(380,20,100,40);
        btnQuit.setText("Quit");
        btnQuit.addActionListener(new ActionListener() {
            //quit button logic
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //add quit button to main frame
        frame.add(btnQuit);

        //add game object button
        JButton btnAddObject = new JButton();
        btnAddObject.setBounds(20,20,100,40);
        btnAddObject.setText("Add GameObject");
        btnAddObject.addActionListener(new ActionListener() {
            //add game object button logic
            @Override
            public void actionPerformed(ActionEvent e) {

                if(objectCount[0] < 10) {

                    //create game object with user inputted values
                    double startX = Double.parseDouble(JOptionPane.showInputDialog("Enter start postion X"));
                    double startY = Double.parseDouble(JOptionPane.showInputDialog("Enter start postion Y"));

                    double velX = Double.parseDouble(JOptionPane.showInputDialog("Enter velocity in the X direction"));
                    double velY = Double.parseDouble(JOptionPane.showInputDialog("Enter velocity in the Y direction"));

                    GameObject newObject = new GameObject(startX,startY,velX,velY);
                    objects[objectCount[0]] = newObject;

                    //create a panel for this object
                    JPanel panel = new JPanel();
                    panel.setLayout(null);

                    //set position of panel to gameobjects start position
                    panel.setBounds((int)startX,(int)startY, 20, 20);
                    panels[objectCount[0]] = panel;
                    objectCount[0] += 1;

                    JOptionPane.showMessageDialog(null,"Object created: " + newObject.getGameObject());
                }
                else{
                    JOptionPane.showMessageDialog(null,"Maximum game objects have been created");
                }
            }
        });

        //add button to main frame
        frame.add(btnAddObject);

        //show the frame
        frame.show();


        //game loop
        while(true){

            //for all of the objects check if they have been created by the user
            for(int g = 0; g < objectCount[0]; g ++){
                if(objects[g] != null){
                    //the object at index g is created. do logic for updating the object and updating its panels position
                    objects[g].Update();

                    panels[g].setBounds((int)objects[g].getCenter().x, (int)objects[g].getCenter().y, 20, 20);
                    panels[g].setBackground(Color.BLACK);
                    frame.add(panels[g]);
                }
            }

            //sleap main thread for 16 milliseconds
            Thread.sleep(16);
        }

    }
}
