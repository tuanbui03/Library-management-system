/* doesn't work with source level 1.8:
module com.mycompany.mavenproject1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.mavenproject1 to javafx.fxml;
    exports com.mycompany.mavenproject1;
}
*/
