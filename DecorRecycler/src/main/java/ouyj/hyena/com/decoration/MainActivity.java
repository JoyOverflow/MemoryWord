package ouyj.hyena.com.decoration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLinear:
                startActivity(
                        new Intent(MainActivity.this, LinearLayoutActivity.class)
                );
                break;
            case R.id.btnGrid:
                startActivity(
                        new Intent(MainActivity.this, GridLayoutActivity.class)
                );
                break;
            case R.id.btnMiddle:
                startActivity(
                        new Intent(MainActivity.this, MiddleLayoutActivity.class)
                );
                break;
            case R.id.btnSpan:
                startActivity(
                        new Intent(MainActivity.this, SpanLayoutActivity.class)
                );
                break;
            default:
                break;
        }
    }
}
