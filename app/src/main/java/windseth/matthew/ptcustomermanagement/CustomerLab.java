package windseth.matthew.ptcustomermanagement;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.CustomerDbSchema.CustomerBaseHelper;
import database.CustomerDbSchema.CustomerCursorWrapper;
import database.CustomerDbSchema.CustomerDbSchema.CustomerTable;



public class CustomerLab {
    private static CustomerLab sCustomerLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CustomerLab get(Context context) {
        if (sCustomerLab == null) {
            sCustomerLab = new CustomerLab(context);
        }
        return sCustomerLab;
    }

    private CustomerLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CustomerBaseHelper(mContext)
                .getWritableDatabase();
        mCustomers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Customer customer = new Customer();
            customer.setName("Customer #" + i);
            mCustomers.add(customer);

        }
    }

    public List<Customer> getCustomers() {

        List<Customer> customers = new ArrayList<>();

        CustomerCursorWrapper cursor = queryCustomers(null, null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                customers.add(cursor.getCustomer());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return customers;
    }

    public void  addCustomer(Customer c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(CustomerTable.NAME, null, values);
    }

    public Customer getCustomer(UUID id) {
        CustomerCursorWrapper cursor = queryCustomers(
                CustomerTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
        } finally {
            cursor.close();
        }
        return null;
    }

    public void updateCustomer(Customer customer){
        String uuidString = customer.getId().toString();
        ContentValues values = getContentValues(customer);

        mDatabase.update(CustomerTable.NAME, values,
                CustomerTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private CustomerCursorWrapper queryCustomers(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CustomerTable.CUSTOMERS,
                null, //columns - "null" selects ALL columns
                whereClause,
                whereArgs,
                null, //groupBy
                null, //having
                null //orderBy
        );
        return new CustomerCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Customer customer) {
        ContentValues values = new ContentValues();
        values.put(CustomerTable.Cols.UUID, customer.getId().toString());
        values.put(CustomerTable.Cols.NAME, customer.getName());

        return values;
    }

    public File getPhotoFile(Customer customer) {
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, customer.getPhotoFilename());
    }

}
