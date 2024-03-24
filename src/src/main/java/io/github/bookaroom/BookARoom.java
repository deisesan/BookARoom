package io.github.bookaroom;

import io.github.controllers.CampusController;
import io.github.controllers.ConsoleController;
import io.github.data.DataSource;

public class BookARoom {

    public static void main(String[] args) {
        
        DataSource.initializeDataSource();
        CampusController campusController = new CampusController();
        campusController.setCampusLista(DataSource.getCampusList());
        ConsoleController consoleController = new ConsoleController(campusController);
        consoleController.iniciar();
    }
}
