package vn.edu.ntu.sharepreference59cntt1_bttrenlop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //định nghĩa hàm
    public  static final String tenSharePref = "my_share_pref";
    public  static final String keyTen = "ten";
    public  static final String keyngaysinh = "ngay_sinh";
    public  static final String keynam = "nam";
    public  static final String keynu = "nu";
    public  static final String keysdt = "sdt";


    EditText edtName, edtDate, edtPhone;
    RadioButton rbNam, rbNu;
    Button btnLuu, btnDoc, btnXoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        docPref();
    }

    private  void  addView(){
        edtName = findViewById(R.id.edtNames);
        edtDate = findViewById(R.id.edtBirths);
        edtPhone = findViewById(R.id.edtNumber);
        rbNam = findViewById(R.id.rbMan);
        rbNu = findViewById(R.id.rbWoman);
        btnLuu = findViewById(R.id.btnSave);
        btnDoc = findViewById(R.id.btnRead);
        btnXoa = findViewById(R.id.btnDelete);
        btnLuu.setOnClickListener(this);
        btnDoc.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnSave: luuPref(); break;
            case R.id.btnRead: docPref(); break;
            case R.id.btnDelete: xoaThongTin(); break;
        }
    }

    private  void luuPref(){
        SharedPreferences sharedPreferences = getSharedPreferences(tenSharePref, MODE_PRIVATE);
        if(sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //lưu
            //kiểu textview
            editor.putString(keyTen, edtName.getText().toString());
            editor.putString(keyngaysinh, edtDate.getText().toString());

            //kiểu radiobutton
            editor.putBoolean(keynam, rbNam.isChecked());
            editor.putBoolean(keynu, rbNu.isChecked());

            editor.putString(keysdt, edtPhone.getText().toString());

            editor.commit(); // dữ liệu ít nên lưu bằng commit
        }
    }


    private  void docPref(){
        SharedPreferences sharedPreferences = getSharedPreferences(tenSharePref, MODE_PRIVATE);
        if(sharedPreferences != null){
            String ten = sharedPreferences.getString(keyTen, "Chưa có tên");
            String ngaySinh = sharedPreferences.getString(keyngaysinh, "Chưa sinh ra");

            boolean nam = sharedPreferences.getBoolean(keynam, true);
            boolean nu = sharedPreferences.getBoolean(keynu, false);

            String sdt = sharedPreferences.getString(keysdt, "Chưa có điện thoại");

            edtName.setText(ten);
            edtDate.setText(ngaySinh);
            rbNam.setChecked(nam);
            rbNu.setChecked(nu);
            edtPhone.setText(sdt);
        }
    }

    private void xoaThongTin(){
        edtName.setText("");
        edtDate.setText("");
        rbNam.setChecked(true);
        rbNu.setChecked(false);
        edtPhone.setText("");
    }
}
