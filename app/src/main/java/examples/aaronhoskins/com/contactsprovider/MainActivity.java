package examples.aaronhoskins.com.contactsprovider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements PermissionsManager.IPermissionManager {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionsManager permissionsManager = new PermissionsManager(this);
        permissionsManager.requestPermission();
        permissionsManager.checkPermission();
    }

    @Override
    public void onPermissionResult(boolean isGranted) {
        if(isGranted) {
            ContactsManager contactsManager = new ContactsManager(this);
            contactsManager.getContacts();
        } else {
            Log.d("TAG", "onPermissionResult: " + "PERMISSIONS NOT GRANTED");
        }

    }
}
