import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** This program is designed to calculate the total cost of college dormitory, 
 *  meal, and add-on options extending the javafx Application class
 * 
 *  @author Taline Mkrtschjan
 *  Due 10/25/2019
 */

public class CollegeDormCost extends Application {

    private Menu dormitoryMenu;                //to use for dormitory menu
    private Menu mealMenu;                     //to use for meal plan menu
    
    private Slider slider;					   //to use for the slider
    
    private RadioMenuItem allenHall;           //holds allenHall dorm info
    private RadioMenuItem pikeHall;            //holds pikeHall dorm info
    private RadioMenuItem farthingHall;        //holds farthingHall dorm info
    private RadioMenuItem universitySuites;    //holds universtySuites dorm info
    private RadioMenuItem sevenDayMeals;       //holds value of 7-day meal plan
    private RadioMenuItem fourteenDayMeals;    //holds value of 14-day meal plan
    private RadioMenuItem unlimitedMeals;      //holds value of unlimited meal 
    										   //plan
    
    private CheckBox grocery;                   //holds option of grocery add-on
    private CheckBox gym;                       //holds option of gym add-on
    private CheckBox cleaning;                  //holds option of room-cleaning
    private CheckBox arts;                      //holds option of arts add-on
    private CheckBox laundry;                   //holds option of weekly laundry
    private CheckBox parking;                   //holds option of on-campus 
                                                //parking
    
    private ToggleGroup dormitoryToggleGroup;   //to combine dorm menu items
    private ToggleGroup mealToggleGroup;        //to combine meal menu items
    
    private Label dormitoryLabel1;              //to display dormitory cost
    private Label mealPlanLabel1;               //to display meal plan cost
    private Label totalCostLabel;		        //to display total cost
    private Label sliderCostLabel1;             //to display slider amount
    
    private Label sliderCostLabel2;
    private Label addOnCostLabel1;				//to display total add-on cost
    
	private double dormitoryCost = 0;			//to hold dormitory choice cost
	private double mealPlanCost = 0;            //to hold meal plan choice cost    
    
    
	public static void main(String[] args)
    {
       //to launch the application
       launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//to use for window measurements
		final double WIDTH = 700.0, HEIGHT = 900.0; 
		final double MIN = 100, MAX = 1000.0, INITIAL = 100.0;
		
		//to use for slider measurements
		final double MAJOR_TICK_UNIT = 50;
		final int MINOR_TICK_UNIT = 0;
		final int SLIDER_LENGTH = 500;
		
		Label dormitoryLabel;                 //for dormitory cost menu label
		Label mealPlanLabel;                  //for meal plan cost label
		Label sliderLabel;                    //for use with slider label
		Label sliderCostLabel;                //for slider cost label
		Label addOnOptionsLabel;              //for add-on options label
		Label addOnCostLabel;                 //for add-on cost label
		
		//to create MenuBar, Button, HBox, VBo, and DropShadow objects
		MenuBar menuBar = new MenuBar();;					  
		Button totalCostButton;    
	    HBox costHBox, addOnOptionsHBox, dormitoryHBox, mealPlanHBox, sliderHBox;
	    VBox vbox, vbox1, vbox2, vbox3; 
		DropShadow shadow, shadow1, shadow2, shadow3;
		
		//to create shadow objects and add shadows around labels and cost button
		
		shadow = new DropShadow();
		shadow.setHeight(10.0);
		shadow.setWidth(10.0);
		shadow.setColor(Color.INDIANRED);
		
		shadow1 = new DropShadow();
		shadow1.setHeight(25.0);
		shadow1.setWidth(25.0);
		shadow1.setColor(Color.DARKSLATEBLUE);
		
		shadow2 = new DropShadow();
		shadow2.setHeight(25.0);
		shadow2.setWidth(25.0);
		shadow2.setColor(Color.GREEN);
		
		shadow3 = new DropShadow();
		shadow3.setHeight(25.0);
		shadow3.setWidth(25.0);
		shadow3.setColor(Color.INDIANRED);		  
		
		// Create the menus using the specific methods
		createdormitoryMenu(primaryStage);
		createMealMenu(primaryStage);
		
		//to add dormitory and meal menus to menu bar
		menuBar.getMenus().add(dormitoryMenu);
		menuBar.getMenus().add(mealMenu);
		
		//to create slider using constant measurements, setting snap to tick to 
		//true, and adding increments, setting default value of the slider
		slider = new Slider(MIN, MAX, INITIAL);
		slider.setOrientation(Orientation.VERTICAL);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(MAJOR_TICK_UNIT);
		slider.setMinorTickCount(MINOR_TICK_UNIT);
		slider.setShowTickLabels(true);
		slider.setSnapToTicks(true);
		slider.setBlockIncrement(MAJOR_TICK_UNIT);
		slider.setValue(INITIAL);
		slider.setPrefHeight(SLIDER_LENGTH);
		
		//setting and formatting slider label
		sliderLabel = new Label("Add money to charge account");
		sliderLabel.getStyleClass().add("label-green");
		sliderLabel.setAlignment(Pos.CENTER);
		sliderLabel.setEffect(shadow2);
		
		//to display information and value of added money in a formatted output
		sliderCostLabel = new Label("Total money added: ");
		sliderCostLabel.setStyle("-fx-text-fill: crimson;" 
							   + "-fx-font-weight: bolder");
		sliderCostLabel1 = new Label("$ 100");
		sliderCostLabel1.getStyleClass().add("label-bold");
		sliderCostLabel1.setEffect(shadow3);
	
		//event handler when slider is used
		slider.valueProperty().addListener((observable, oldValue, newValue) -> {
			double sliderCost = slider.getValue();
			sliderCostLabel1.setText(String.format("$ %,.2f", sliderCost));
			sliderCostLabel2.setText(String.format("$ %,.2f", sliderCost));
		});
		
		//placing slider and label in a vbox
		vbox = new VBox(40, sliderLabel, slider, sliderCostLabel, 
						sliderCostLabel1);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(50));
		
		//formatted label for add-on-options
		addOnOptionsLabel = new Label("Add-on Options:");
		addOnOptionsLabel.getStyleClass().add("label-blue");
		addOnOptionsLabel.setEffect(shadow1);
		
	    //creating checkboxes, setting displays and values of add-on options
		grocery = new CheckBox("Grocery delivery (per week): $ 50");
	    gym = new CheckBox("Gym membership (per semester): $ 200");
	    arts = new CheckBox("On campus art events (per semester): $ 450");
	    cleaning = new CheckBox("Dorm room cleaning (per week): $ 75");
	    laundry = new CheckBox("Laundry service (per week): $ 50");
	    parking = new CheckBox("Parking on campus (per semester): $ 150");

		//event handlers for each checkbox calling the calculateAddOnOptions
		grocery.setOnAction(event -> {
	        calculateAddOnOptions(); 
	      });
		
		gym.setOnAction(event -> {
	        calculateAddOnOptions(); 			
	      });
		
		arts.setOnAction(event -> {
	        calculateAddOnOptions(); 			
	      });
		
		cleaning.setOnAction(event -> {
	        calculateAddOnOptions(); 			
	      });
		
		laundry.setOnAction(event -> {
	        calculateAddOnOptions(); 			
	      });
		
		parking.setOnAction(event -> {
	        calculateAddOnOptions(); 			
	      });
	    
		//formatted label to identify add-on options total cost
		addOnCostLabel = new Label("Total Add-On Cost:");
		addOnCostLabel.setStyle("-fx-text-fill: crimson; "
				              + "-fx-font-weight: bold");
		
		//formatted label to display add-on options total cost
		addOnCostLabel1 = new Label("$ 0.00");
		addOnCostLabel1.getStyleClass().add("label-bold");
		addOnCostLabel1.setEffect(shadow);
		
		//to display the add-on option label and cost horizontally in an hbox
		addOnOptionsHBox = new HBox(30, addOnCostLabel, addOnCostLabel1);
		addOnOptionsHBox.setAlignment(Pos.BOTTOM_LEFT);
		addOnOptionsHBox.setPadding(new Insets(8));
		
		//formatted label to identify dorm cost option
	    dormitoryLabel = new Label("Dormitory cost: ");
		dormitoryLabel.setStyle("-fx-text-fill: crimson; "
	                          + "-fx-font-weight: bold");
		
		//formatted label to display dormitory choice cost set initially at 
		//lowest cost
		dormitoryLabel1 = new Label("$ 1,200.00");
		dormitoryLabel1.getStyleClass().add("label-bold");  
		dormitoryLabel1.setEffect(shadow);
		
		//to display dorm cost label and dorm cost horizontally in an hbox
		dormitoryHBox = new HBox(35, dormitoryLabel, dormitoryLabel1);
		dormitoryHBox.setAlignment(Pos.BOTTOM_LEFT);
		dormitoryHBox.setPadding(new Insets(8));
		
		//to create formatted label for meal plan cost
		mealPlanLabel = new Label("Meal plan cost: ");
		mealPlanLabel.setStyle("-fx-text-fill: crimson; "
				             + "-fx-font-weight: bold"); 
		
		//to display meal plan choice cost. Set initially at lowest cost
		mealPlanLabel1 = new Label("$ 560.00");				
		mealPlanLabel1.getStyleClass().add("label-bold");
		mealPlanLabel1.setEffect(shadow);
		
		//to display meal plan label and cost horizontally in an hbox
		mealPlanHBox = new HBox(35, mealPlanLabel, mealPlanLabel1);
		mealPlanHBox.setAlignment(Pos.BOTTOM_LEFT);
		mealPlanHBox.setPadding(new Insets(8));
		
		//to create formatted label for slider
		sliderCostLabel = new Label("Total charge added:");
		sliderCostLabel.setStyle("-fx-text-fill: crimson; "
				               + "-fx-font-weight: bold");
		
		//to display added amount using slider. Set initially at $100
		sliderCostLabel2 = new Label("$ 100");
		sliderCostLabel2.getStyleClass().add("label-bold");
		sliderCostLabel2.setEffect(shadow);

		
		sliderHBox = new HBox(40, sliderCostLabel, sliderCostLabel2);
		sliderHBox.setAlignment(Pos.BOTTOM_LEFT);
		sliderHBox.setPadding(new Insets(8));
		
		//to place add-on options in a formatted vbox
		vbox1 = new VBox(20, addOnOptionsLabel, grocery, gym, cleaning, arts, 
				 		 laundry, parking);  
		vbox1.setAlignment(Pos.CENTER_LEFT);
		vbox1.setPadding(new Insets(20));
		vbox1.setStyle("-fx-background-color: white");
		vbox1.setEffect(shadow1);
		
		//to place hboxes of add-on option, dormitory cost, and meal plan cost, 
		//and slider cost in a vbox
		vbox2 = new VBox(20, addOnOptionsHBox, dormitoryHBox, mealPlanHBox,
						 sliderHBox);
		vbox2.setAlignment(Pos.CENTER_LEFT);
		vbox2.setPadding(new Insets(40));
		
		//combining all vboxes in an other vbox to use with left borderPane
		vbox3 = new VBox(30, vbox1, vbox2);
		vbox3.setAlignment(Pos.CENTER_LEFT);
		vbox3.setPadding(new Insets(30));

		//formatted button to get total cost of all choices
		totalCostButton = new Button("Calculate Total Cost per semester");
		totalCostButton.setOnAction(new TotalCostButtonHandler());
		totalCostButton.setEffect(shadow3);
		
		//formatted label to display total cost value in dollar amount
		totalCostLabel = new Label("$ 0.00");
		totalCostLabel.setStyle("-fx-border-width: 1px");
		totalCostLabel.getStyleClass().add("label-bold");
		totalCostLabel.setEffect(shadow3);
		
		//to place the total cost button and label in an hbox
		costHBox = new HBox(70, totalCostButton, totalCostLabel);
		costHBox.setAlignment(Pos.BOTTOM_LEFT);
		costHBox.setPadding(new Insets(45));
		
		//to create a borderpane and place the menubar, hbox and vboxes in each 
		//side of the borderpane
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(menuBar);
		borderPane.setBottom(costHBox); 
		borderPane.setRight(vbox);
		borderPane.setLeft(vbox3);
		
		//add scrollbar to the boderPane
		ScrollPane scrollPane = new ScrollPane(borderPane);
		scrollPane.setContent(borderPane);
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		//to create, display, and show a Scene
		Scene scene = new Scene(scrollPane, WIDTH, HEIGHT);
		scene.getStylesheets().add("CollegeDormCostStyle.css");
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("College Housing and Meal Cost");


		primaryStage.show();
		
	} //primary stage method
		
	
	//to create dormitory menu
	public void createdormitoryMenu(Stage primaryStage) {
		
		//to set dormitory value to least expensive if no other option is picked
		dormitoryCost = 1200;             
		
		//to add dormitory menu and set mnemonics
		dormitoryMenu = new Menu("_Dormitory");
		dormitoryMenu.getStyleClass().add("menu-bold");
		dormitoryMenu.setMnemonicParsing(true);
		
		//to add radio menu items to the dormitory menu and set mnemonics
		allenHall = new RadioMenuItem("_Allen Hall: $1500 per semester");
		pikeHall = new RadioMenuItem("_Pike Hall: $1600 per semester");
		farthingHall = new RadioMenuItem("_FarthingHall: $1200 per semester");
		universitySuites = new RadioMenuItem("_University Suites: "
										   + "$1800 per semester");
		
		//combining all dormitory radio menu items in a toggle group to allow 
		//one pick only
		dormitoryToggleGroup = new ToggleGroup();
		allenHall.setToggleGroup(dormitoryToggleGroup);
		pikeHall.setToggleGroup(dormitoryToggleGroup);
		farthingHall.setToggleGroup(dormitoryToggleGroup);
		universitySuites.setToggleGroup(dormitoryToggleGroup);
		
		//setting farthinghall as default choice
		farthingHall.setSelected(true);
		
		//to list all dormitory options under dormitory menu
		dormitoryMenu.getItems().addAll(allenHall, pikeHall, farthingHall,
									universitySuites);
		
		//creating event handlers for each radio menu item option. Setting the 
		//price of each dormitory and setting the dormitory cost label 
		//accordingly
		allenHall.setOnAction(event -> {
			dormitoryCost = 1500;
			dormitoryLabel1.setText(String.format("$ %,.2f", dormitoryCost));
		});
		
		pikeHall.setOnAction(event -> {
			dormitoryCost = 1600;
			dormitoryLabel1.setText(String.format("$ %,.2f", dormitoryCost));
		});
		
		farthingHall.setOnAction(event -> {
			 dormitoryCost = 1200;
			 dormitoryLabel1.setText(String.format("$ %,.2f", dormitoryCost));
		});
		
		universitySuites.setOnAction(event -> {
			dormitoryCost = 1800;
			dormitoryLabel1.setText(String.format("$ %,.2f", dormitoryCost));
		});
		
	}// creating dormitory method

	//to create meal plan menu
	public void createMealMenu(Stage primaryStage) {
		
		//to set mealPlan value to least expensive option if no other option is
		//picked from the menu
		mealPlanCost = 560;
		
		//to add meal plan menu and set mnemonics
		mealMenu = new Menu("_Meal Options");
		mealMenu.getStyleClass().add("menu-bold");
		mealMenu.setMnemonicParsing(true);
		
		//to create meal plan options as radio menu items and set mnemonics
		sevenDayMeals = new RadioMenuItem("_7 meals per week: $560 per "
										+ "semester");
		fourteenDayMeals = new RadioMenuItem("14 _meals per semester: $1095 "
										   + "per semester");
		unlimitedMeals = new RadioMenuItem("_Unlimited meals: $1500 per "
										 + "semester");
		
		//combining meal plan radio menu items in a toggle group to force allow
		//one pick only
		mealToggleGroup = new ToggleGroup();
		sevenDayMeals.setToggleGroup(mealToggleGroup);
		fourteenDayMeals.setToggleGroup(mealToggleGroup);
		unlimitedMeals.setToggleGroup(mealToggleGroup);
		
		//setting 7 day meal plan option as the selected default option
		sevenDayMeals.setSelected(true);	
		 
		//to add all meal plan options under the meal menu
		mealMenu.getItems().addAll(sevenDayMeals, fourteenDayMeals, 
								   unlimitedMeals);
		
		
		//event handlers for each meal plan option choice. Setting meal plan
		//values for each option and setting the cost label of meal plan
		//options accordingly
		sevenDayMeals.setOnAction(event -> {
			 mealPlanCost = 560;
			 mealPlanLabel1.setText(String.format("$ %,.2f", mealPlanCost));
		});
		
		fourteenDayMeals.setOnAction(event -> {
			mealPlanCost =1095;
			mealPlanLabel1.setText(String.format("$ %,.2f", mealPlanCost));
		});
		
		unlimitedMeals.setOnAction(event -> {
			mealPlanCost = 1500;
			mealPlanLabel1.setText(String.format("$ %,.2f", mealPlanCost));
		});
	}//meal plan create method
	
   
	//event handler for button click
	class TotalCostButtonHandler implements EventHandler<ActionEvent> {
      @Override
      public void handle(ActionEvent event) {
         
    	 double totalCost = 0;                   //to hold value of total cost
         
         //adding dormitory choice values to the total cost
    	 if (allenHall.isSelected())
    		 
             totalCost += dormitoryCost;
         	
         if (pikeHall.isSelected())
             totalCost += dormitoryCost;
         
         if (farthingHall.isSelected())
	         totalCost += dormitoryCost;
         
         if (universitySuites.isSelected())
	         totalCost += dormitoryCost;
         
         
         //adding meal plan choice values to the total cost
         if (sevenDayMeals.isSelected())
        	  totalCost += mealPlanCost;
         
         if (fourteenDayMeals.isSelected())
        	 totalCost += mealPlanCost;
         
         if (unlimitedMeals.isSelected())
        	 totalCost += mealPlanCost;
         

         //adding slider value to the total cost
         totalCost += slider.getValue();

 
         // Display the results of total cost adding add-on options values by
         //calling the calculateAddOnOptions() method
         totalCostLabel.setText(String.format("$ %,.2f", totalCost + 
        		 							  calculateAddOnOptions()));
         totalCostLabel.getStyleClass().add("label-bold");
      }
   } //cost button handler
	   
	
   //method to calculate add-on options and set the label accordingly
	private double calculateAddOnOptions() {
		
		double addOnTotal = 0;              //to hold total of add-on options  
	    
		//setting the value of each option if clicked
		if (grocery.isSelected())
		   addOnTotal += 15*50;
         
        if (gym.isSelected())
    	   addOnTotal += 200;
         
        if (arts.isSelected())
    	   addOnTotal += 450;
     
        if (cleaning.isSelected())
    	   addOnTotal += 15*75;
     
        if (laundry.isSelected())
    	   addOnTotal += 15*50;
     
        if (parking.isSelected())
    	   addOnTotal +=150;
     
        addOnCostLabel1.setText(String.format("$ %,.2f", addOnTotal));
       
        //to return add-on options value
        return addOnTotal; 
       
   } //add-on options method
} //main
