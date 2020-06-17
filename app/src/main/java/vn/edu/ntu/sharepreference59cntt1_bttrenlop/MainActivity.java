package vn.edu.ntu.sharepreference59cntt1_bttrenlop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public  static final  String tenSharePref = "my_share_pref";
    public  static final  String keyTen = "ten";
    public  static final  String keyNgaySinh = "ngaysinh";
    public  static final  String keyNam = "Nam";
    public  static final  String keyNu = "Nu";
    public  static final  String keySDT = "SDT";
    EditText Ten, Ngaysinh,Sdt;
    RadioButton rdbNam, rdbNu;
    Button luu, doc, xoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adViews();
    }

    private void adViews() {
        Ten = findViewById(R.id.edtNames);
        Ngaysinh = findViewById(R.id.edtBirths);
        Sdt = findViewById(R.id.edtNumber);

        rdbNam = findViewById(R.id.rbMan);
        rdbNu = findViewById(R.id.rbWoman);

        luu = findViewById(R.id.btnSave);
        doc = findViewById(R.id.btnRead);
        xoa = findViewById(R.id.btnDelete);

        luu.setOnClickListener(this);
        doc.setOnClickListener(this);
        xoa.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        docPref();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.btnSave: luuPref(); break;
            case R.id.btnRead: docPref(); break;
            case R.id.btnDelete: xoaThongTin(); break;

        }
    }

    private void xoaThongTin() {
        Ten.setText("");
        Ngaysinh.setText("");
        Sdt.setText("");
        rdbNam.setChecked(true);
        rdbNu.setChecked(false);
    }

    private void docPref() {
        SharedPreferences sharedPreferences = getSharedPreferences(tenSharePref, MODE_PRIVATE);
        if (sharedPreferences!=null)
        {
            String ten = sharedPreferences.getString(keyTen, "nhap ten");
            String ngaySinh = sharedPreferences.getString(keyNgaySinh, "was't born");
            String sdt = sharedPreferences.getString(keySDT,"not phone");
            boolean nam = sharedPreferences.getBoolean(keyNam,true);
            boolean nu = sharedPreferences.getBoolean(keyNu,false);

            Ten.setText(ten);
            Ngaysinh.setText(ngaySinh);
            Sdt.setText(sdt);
            rdbNam.setChecked(nam);
            rdbNu.setChecked(nu);
        }
    }

    private void luuPref() {
        SharedPreferences sharedPreferences = getSharedPreferences(tenSharePref, MODE_PRIVATE);
        if (sharedPreferences!=null)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(keyTen,Ten.getText().toString());
            editor.putString(keyNgaySinh, Ngaysinh.getText().toString());
            editor.putBoolean(keyNam, rdbNam.isChecked());
            editor.putBoolean(keyNu, rdbNu.isChecked());
            editor.putString(keySDT,Sdt.getText().toString());
        }
    }
}
