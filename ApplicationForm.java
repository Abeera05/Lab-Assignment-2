package com.example.applicationform;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ApplicationForm extends Application {

    private final List<Person> person = new ArrayList<>();
    private final ImageView imageView = new ImageView();

    @Override
    public void start(Stage stage) {
        // Banner Image
        Image bannerImage = new Image("file:banner.jpeg");
        ImageView bannerImageView = new ImageView(bannerImage);
        bannerImageView.setFitHeight(100);
        bannerImageView.setFitWidth(800);

        HBox bannerBox = new HBox(bannerImageView);
        bannerBox.setAlignment(Pos.CENTER);
        bannerBox.setPadding(new Insets(10));

        //ab different elements set krenge
        TextField nameTextField = new TextField();
        TextField fatherNameTextField = new TextField();
        TextField cnicTextField = new TextField();
        DatePicker datePicker = new DatePicker();
        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.getItems().addAll("Lahore", "Islamabad", "Karachi");

        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadioButton = new RadioButton("Male");
        RadioButton femaleRadioButton = new RadioButton("Female");
        maleRadioButton.setToggleGroup(genderGroup);
        femaleRadioButton.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, maleRadioButton, femaleRadioButton);
        genderBox.setAlignment(Pos.CENTER_LEFT);

        // Image
        imageView.setFitWidth(250);
        imageView.setFitHeight(250);
        Button chooseImageButton = new Button("Choose Image");
        chooseImageButton.setOnAction(e -> chooseImage(stage));

        VBox imageBox = new VBox(10, imageView, chooseImageButton);
        imageBox.setAlignment(Pos.CENTER_RIGHT);
        imageBox.setPadding(new Insets(10));

        // Save Button
        Button saveButton = new Button("Save");
        Label messageLabel = new Label();
        messageLabel.setFont(new Font(14));
        HBox saveButtonBox = new HBox(saveButton);
        saveButtonBox.setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);
        // directly object bna dia label ka
        grid.add(new Label("Name"), 0, 0);
        grid.add(nameTextField, 1, 0);
        grid.add(new Label("Father Name"), 0, 1);
        grid.add(fatherNameTextField, 1, 1);
        grid.add(new Label("CNIC"), 0, 2);
        grid.add(cnicTextField, 1, 2);
        grid.add(new Label("Date"), 0, 3);
        grid.add(datePicker, 1, 3);
        grid.add(new Label("Gender"), 0, 4);
        grid.add(genderBox, 1, 4);
        grid.add(new Label("City"), 0, 5);
        grid.add(cityComboBox, 1, 5);

        grid.add(saveButtonBox, 0, 6, 2, 1); // Add the save button to grid


        // Main Layout with bold form title
        Text formTitle = new Text("UNIVERSITY APPLICATION FORM");
        formTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        HBox mainLayout = new HBox(20, grid, imageBox);
        mainLayout.setPadding(new Insets(10, 50, 10, 50));

        VBox vbox = new VBox(20, bannerBox, formTitle, mainLayout, messageLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        // Save Button Action
        saveButton.setOnAction(e -> {
            String name = nameTextField.getText();
            String fatherName = fatherNameTextField.getText();
            String cnic = cnicTextField.getText().trim();
            String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "";
            String gender;
            if (maleRadioButton.isSelected()) {
                gender = "Male";
            } else if(femaleRadioButton.isSelected()) {
                gender = "Female";
            }else{
                gender = "Unknown";
            }
            String city = cityComboBox.getValue();
            // condition set krdi k koi bhi field empty nai honi chahiye
            if (name.isEmpty() || fatherName.isEmpty() || cnic.isEmpty() || date.isEmpty() || gender.isEmpty() || city == null) {
                messageLabel.setText("Please fill all fields.");
                messageLabel.setStyle("-fx-text-fill: red;");
            } else {
                person.add(new Person(name, fatherName, cnic, date, gender, city));
                messageLabel.setText("Form data saved successfully!");
                messageLabel.setStyle("-fx-text-fill: green;");
                System.out.println("Saved Person:");
                System.out.println(person); //console mai person ka data print

                //empty all fields to enter next data
                nameTextField.clear();
                fatherNameTextField.clear();
                cnicTextField.clear();
                datePicker.setValue(null);
                genderGroup.selectToggle(null);
                cityComboBox.setValue(null);
            }
        });

        stage.setScene(new Scene(vbox, 800, 600));
        stage.setTitle("University Application Form");
        stage.show();
    }

    private void chooseImage(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) imageView.setImage(new Image(file.toURI().toString()));
    }

    public static void main(String[] args) {
        launch();
    }
}