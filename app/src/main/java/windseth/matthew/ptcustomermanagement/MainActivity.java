package windseth.matthew.ptcustomermanagement;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CustomerFragment();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.log_off){
            Intent logout = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(logout);
            Toast.makeText(MainActivity.this,R.string.logging_off,Toast.LENGTH_SHORT).show();
            this.finish();
            return true;
        }
        if (id==R.id.take_photo) {
            Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            boolean canTakePhoto = mPhotoFile != null &&
                    captureImage.resolveActivity(packageManager) != null;






        }
        return true;
    }


    }

}
