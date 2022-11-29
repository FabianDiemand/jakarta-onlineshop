package io.github.fd_education.jakartaonlineshop.listeners;

import jakarta.faces.model.DataModelEvent;
import jakarta.faces.model.DataModelListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductListener implements DataModelListener {
    private final Logger log = Logger.getLogger(getClass().getName());

    @Override
    public void rowSelected(DataModelEvent event) {
        log.log(Level.INFO, event.getRowIndex() + ": " + event.getRowData());
    }
}
