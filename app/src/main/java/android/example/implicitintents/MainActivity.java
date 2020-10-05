package android.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = (EditText) findViewById(R.id.website_edittext);
        mLocationEditText = (EditText) findViewById(R.id.location_edittext);
        mShareTextEditText = (EditText) findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view){
        //get url text
        String url = mWebsiteEditText.getText().toString();

        //parse the URI and create the intent
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        //cari activity untuk handle intent nya dan mulai activity-nya
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void openLocation(View view){
        //mendapatkan string indicating lokasi
        //passed to the location hadler intact
        String loc = mLocationEditText.getText().toString();

        //parse the location and create the intent(parse lokasi dan buat yg dimaksud)
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        //cari activity untuk handle intent dan memulai activity -nya
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void shareText(View view){
        String txt = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";
        //untuk share kemana saja
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                //judul saat ingin mengirim
                .setChooserTitle("Share this text with: ")
                //data apa yang mau di share
                .setText(txt)
                //untuk memilih mau share kemana
                .startChooser();
    }
}