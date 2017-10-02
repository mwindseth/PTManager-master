package windseth.matthew.ptcustomermanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CustomerListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CustomerFragment();
    }
}
