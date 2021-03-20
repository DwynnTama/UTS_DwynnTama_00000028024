package umn.ac.id.dwynntama_uts_28024;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LoginPageActivity extends AppCompatActivity {
    private EditText isiUsername, isiPassword;
    private Button Login;
    private final String user = "uasmobile";
    private final String pass = "uasmobilegenap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        isiUsername = (EditText) findViewById(R.id.isiUsername);
        isiPassword = (EditText) findViewById(R.id.isiPassword);
        Login = findViewById(R.id.btnLogin);
        Toolbar toolbar = (Toolbar)findViewById(R.id.menuToolbarLogin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isiUsername.getText().toString().equals(user) && isiPassword.getText().toString().equals(pass)) {
                    Intent berhasilLogin = new Intent(LoginPageActivity.this, ListOfSongActivity.class);
                    startActivity(berhasilLogin);
                } else {
                    Toast.makeText(LoginPageActivity.this, "ID atau Password yang dimasukan salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
