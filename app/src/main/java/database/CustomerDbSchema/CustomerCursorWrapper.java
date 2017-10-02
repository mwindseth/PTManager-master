package database.CustomerDbSchema;

import android.database.CursorWrapper;

import java.util.UUID;

import database.CustomerDbSchema.CustomerDbSchema.CustomerTable;


/**
 * Created by Matt on 10/1/2017.
 */

public class CustomerCursorWrapper extends CursorWrapper {
    public CustomerCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Customer getCustomer() {
        String uuidString = getString(getColumnIndex(CustomerTable.Cols.UUID));
        String name = getString(getColumnIndex(CustomerTable.Cols.NAME));

        Customer customer = new Customer(UUID.fromString(uuidString));
        crime.SetNAME(name);

        return customer;
    }

}


