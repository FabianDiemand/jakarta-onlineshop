package io.github.fd_education.jakartaonlineshop.api.listeners;

import jakarta.faces.model.DataModelEvent;
import jakarta.faces.model.DataModelListener;

/**
 * Implementation of DataModelListener for Order list.
 *
 * @author Fabian Diemand
 */
public class OrderListener implements DataModelListener {

    /**
     * Implement rowSelected method from the DataModelListener interface to listen for changes in selected row.
     *
     * @param event The {@link DataModelEvent} we are processing
     */
    @Override
    public void rowSelected(DataModelEvent event) {
    }
}
