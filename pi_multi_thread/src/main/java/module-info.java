module rzelonek.pi {
    requires javafx.controls;
    requires javafx.fxml;

    opens rzelonek.pi to javafx.fxml;
    exports rzelonek.pi;
}
