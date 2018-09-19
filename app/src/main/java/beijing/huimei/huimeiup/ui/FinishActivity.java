package beijing.huimei.huimeiup.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import beijing.huimei.huimeiup.R;
import beijing.huimei.huimeiup.utls.SharedPreferenceUtils;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_up:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.btn_login:
                startActivity(new Intent(this, LoginActivity.class));
                SharedPreferenceUtils.writeUserData(this, null);
                finish();
                break;
        }
    }
}
