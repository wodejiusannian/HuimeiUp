package beijing.huimei.huimeiup.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beijing.huimei.huimeiup.R;
import beijing.huimei.huimeiup.utls.MUtilsInternet;
import beijing.huimei.huimeiup.utls.SharedPreferenceUtils;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;


    private MUtilsInternet net = MUtilsInternet.getInstance();

    private String str = null;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activiy);
        username = (EditText) this.findViewById(R.id.et_username);
        password = (EditText) this.findViewById(R.id.er_password);
        final Spinner spinner = (Spinner) this.findViewById(R.id.spinner);
        final List<String> mData = new ArrayList<>();
        mData.add("请选择操作人");
        mData.add("韩旭");
        mData.add("李博");
        mData.add("王媛");
        mData.add("吴佳美");
        mData.add("江昌琼");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mData);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str = mData.get(position);
                SharedPreferenceUtils.writeUploader(LoginActivity.this, str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        this.findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str != null && !str.isEmpty() && !str.equals("请选择操作人")) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("account", username.getText().toString());
                    map.put("pwd", password.getText().toString());
                    net.post(LoginActivity.this, map, new MUtilsInternet.XCallBack() {
                        @Override
                        public void onResponse(String result) {
                            try {
                                JSONObject obj = new JSONObject(result);
                                JSONObject body = obj.getJSONObject("body");
                                SharedPreferenceUtils.writeUserData(LoginActivity.this, body.getString("userId"));
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "请选择操作人", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

}
