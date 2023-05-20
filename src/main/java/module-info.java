module com.example.babynameclass {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.babynameclass to javafx.fxml;
    exports com.example.babynameclass;
}