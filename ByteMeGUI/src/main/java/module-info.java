module com.rachitbhandari.bytemegui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires junit;

    opens com.rachitbhandari.bytemegui to javafx.fxml;
    exports com.rachitbhandari.bytemegui;
}