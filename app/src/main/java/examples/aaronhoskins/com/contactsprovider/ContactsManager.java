package examples.aaronhoskins.com.contactsprovider;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ContactsManager {
    private Context context;

    public ContactsManager(Context context) {
        this.context = context;
    }

    public void getContacts() {
        Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Cursor contactsCursor = context.getContentResolver().query(
                contactsUri, null, null, null, null
        );

        while (contactsCursor.moveToNext()) {

            String contactName = contactsCursor.getString(contactsCursor.getColumnIndex(DISPLAY_NAME));

            //retrieve phone numbers from contacts
            int hasNumberColumnIndex = contactsCursor.getColumnIndex(HAS_PHONE_NUMBER);
            int has_phone = contactsCursor.getInt(hasNumberColumnIndex);

            if (has_phone > 0) {
                List<String> numbers = new ArrayList<>();

                Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

                Cursor phoneCursor = context.getContentResolver().query(
                        phoneUri,
                        new String[]{NUMBER},//projection
                        DISPLAY_NAME + "=?"
                        , new String[]{contactName}
                        , NUMBER + " ASC"
                );

                //Select PROJECTION from PHONEURI where SELECTION{SELECTION ARG) SORTORDER

                while (phoneCursor.moveToNext()) {

                    String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));

                    Log.d(TAG, "getContacts: " + phoneNumber);

                }

            }
        }


    }
}
