module com.example.snakenladderpro {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.snakenladderpro to javafx.fxml;
    exports com.example.snakenladderpro;
}