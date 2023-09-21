package co.edu.uniminuto.permisos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_CAMERA = 100;
    public static final int REQUEST_CODE_BIOMETRIC = 101;
    public static final int REQUEST_CODE_LOCATION = 102;
    public static final int REQUEST_CODE_STORAGE = 103;
    public static final int REQUEST_CODE_INTERNET = 104;
    private Button checkPermissions;
    private Button requestPermissions;
    private TextView tvCamera;
    private TextView tvDactilar;

    private TextView tvLocation;

    private TextView tvAlmacenamientoInterno;
    private TextView tvInternet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        begin();
        checkPermissions.setOnClickListener(this::verificarPermisos);
        requestPermissions.setOnClickListener(this::solicitarPermisos);

        findViewById(R.id.tvLocation).setOnClickListener(this::solicitarPermisoUbicacion);
        findViewById(R.id.tvAlmacenamientoInterno).setOnClickListener(this::solicitarPermisoAlmacenamiento);

    }

    private void verificarPermisos(View view){
        // 0 otorgado -1 no otorgado
        int statusPermisoCam = ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA);
        int statusPermisoBio = ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.USE_BIOMETRIC);
        int statusPermisoLot = ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION);
        int statusPermisoAlmIn = ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int statusPermisoInt= ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.INTERNET);
       
        tvCamera.setText("Estatus ver permisos de la camara:"+statusPermisoCam);
        tvDactilar.setText("Estatus ver permisos de la huella:"+statusPermisoBio);
        tvLocation.setText("Estatus ver permisos de la localizacion:" +statusPermisoLot);
        tvAlmacenamientoInterno.setText("Estatus ver permisos de almacenamient" + statusPermisoAlmIn);
        tvInternet.setText("Estatus ver permisos de internet: " + statusPermisoInt );
        
        requestPermissions.setEnabled(true);
    }

    
    public void solicitarPermisos(View view){
        int requestCode = -1;

        int viewId = view.getId();
        if (viewId == R.id.tvCamera) {
            requestCode = REQUEST_CODE_CAMERA;
        } else if (viewId == R.id.tvBiometric) {
            requestCode = REQUEST_CODE_BIOMETRIC;
        } else if (viewId == R.id.tvLocation) {
            requestCode = REQUEST_CODE_LOCATION;
        } else if (viewId == R.id.tvAlmacenamientoInterno) {
            requestCode = REQUEST_CODE_STORAGE;
        } else if (viewId == R.id.tvInternet) {
            requestCode = REQUEST_CODE_INTERNET;
        }

        if (requestCode != -1) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, requestCode);
            }
        }
    }

    public void solicitarPermisoUbicacion(View view) {
        solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION, REQUEST_CODE_LOCATION);
    }

    public void solicitarPermisoAlmacenamiento(View view) {
        solicitarPermiso(Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_CODE_STORAGE);
    }

    private void solicitarPermiso(String permission, int requestCode) {
        if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
        }
    }

    private void begin(){
        tvCamera = findViewById(R.id.tvCamera);
        tvDactilar = findViewById(R.id.tvBiometric);
        tvLocation = findViewById(R.id.tvLocation);
        tvAlmacenamientoInterno = findViewById(R.id.tvAlmacenamientoInterno);
        tvInternet = findViewById(R.id.tvInternet);
        checkPermissions = findViewById(R.id.btnCheckPermissions);
        requestPermissions =findViewById(R.id.btnRequestPermissions);
        //Deshabilitar el botonrequest
        requestPermissions.setEnabled(false);
    }

}