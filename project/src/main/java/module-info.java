module xml.xmleditor {
    requires javafx.controls;
    requires javafx.fxml;


    opens xml.xmleditor to javafx.fxml;
    exports xml.xmleditor;
}