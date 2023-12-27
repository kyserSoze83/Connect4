module org.sarhiri_nabil.puissance_4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.sarhiri_nabil.puissance_4 to javafx.fxml;
    exports org.sarhiri_nabil.puissance_4;
}