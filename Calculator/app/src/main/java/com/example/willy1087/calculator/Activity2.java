package com.example.willy1087.calculator;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;


public class Activity2 extends ActionBarActivity{


    private Button sin,cos,tan,ln,log,percentage,pi,square_root,factorial,e,power;
    private Double number;
    private String ope;

    //optional first number
    private Double first;

    //this output will not be display in this activity but will be use to send back
    //the selected for the first activity
    private EditText output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);


        //getting the number from MainActivity
        Intent i = getIntent();
        number = i.getDoubleExtra("number",0);

        //if operand
        ope = i.getStringExtra("ope");

        //if first number followed by an operand
        if(ope != null){
            first = i.getDoubleExtra("first_number",0);
        }

        //extra operands

        sin = (Button)findViewById(R.id.sin);
        cos = (Button)findViewById(R.id.cos);
        tan = (Button)findViewById(R.id.tan);

        ln = (Button)findViewById(R.id.ln);
        log = (Button)findViewById(R.id.log);
        pi = (Button)findViewById(R.id.pi);

        percentage = (Button)findViewById(R.id.percentage);
        factorial = (Button)findViewById(R.id.factorial);
        square_root = (Button)findViewById(R.id.radical);

        e = (Button)findViewById(R.id.e);
        power = (Button)findViewById(R.id.caret);

        //extra operands

        //the output
        output = (EditText)findViewById(R.id.output);
        String str_number = "" + number;
        output.setText(output.getText().append(str_number));
        output.setSelection(output.length());
        disableSoftInputFromAppearing(output);
        //the output

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendExtra("sin");
            }
        });

        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendExtra("cos");
            }
        });

        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendExtra("tan");
            }
        });

        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendExtra("ln");
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendExtra("log");
            }
        });

        pi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendExtra("pi");
            }
        });

        percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendExtra("%");
            }
        });

        factorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendExtra("!");
            }
        });

        square_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendExtra("square_root");
            }
        });

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendExtra("e");
            }
        });

        power.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                appendExtra("^");
            }
        });


    }

    public void appendExtra(String extra){

        //continue sin, cos, tan
        //select the trigonometry

        switch (extra){
            case "sin":
                //do sine
                number = Math.sin(number);
                break;
            case "cos":
                //do cosine
                number = Math.cos(number);
                break;
            case "tan":
                //do tan
                number = Math.tan(number);
                break;
            case "ln":
                //do ln
                number = Math.log(number);
                break;
            case "log":
                //do log
                number = Math.log10(number);
                break;
            case "pi":
                //do pi
                if(number == 0.0){

                    //if you only pass pi without a number
                    number = 1.0;
                }
                number = number * Math.PI;
                break;
            case "%":
                //do percentage
                number = number / 100.0;
                break;
            case "!":
                //do factorial
                int fact = number.intValue();
                number = (double)fact(fact);
                break;
            case "square_root":
                //square root
                number = Math.sqrt(number);
                break;
            case "e":
                //e
                if(number == 0.0){

                    //if you only pass e without a number
                    number = 1.0;
                }
                number = number*Math.E;
                break;

        }

        if(extra.equals("^")){
            //leave this activity with the caret
            Intent intent = new Intent(Activity2.this,MainActivity.class);
            intent.putExtra("power","^");

            DecimalFormat twoDForm = new DecimalFormat("#.####");
            number = Double.valueOf(twoDForm.format(number));
            intent.putExtra("output", number);

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            //leave this activity
        }else {
            DecimalFormat twoDForm = new DecimalFormat("#.####");
            number = Double.valueOf(twoDForm.format(number));

            Intent intent = new Intent(Activity2.this, MainActivity.class);
            intent.putExtra("output", number);

           //send operation even if it is null
            intent.putExtra("ope",ope);

            if(ope != null){
                intent.putExtra("first",first);
            }

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }


    }

    //this is the factorial recursive algorithm
    int fact(int n)
    {
        int result;

        if(n==1)
            return 1;

        result = fact(n-1) * n;
        return result;
    }

    /**
     * Disable soft keyboard from appearing, use in conjunction with adding the android:windowSoftInputMode="stateAlwaysHidden|adjustNothing" attribute to your activity tag in your manifest
     file.
     * @param editText
     */
    public static void disableSoftInputFromAppearing(EditText editText) {
        if (Build.VERSION.SDK_INT >= 11) {
            editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
            editText.setTextIsSelectable(true);
        } else {
            editText.setRawInputType(InputType.TYPE_NULL);
            editText.setFocusable(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        //if other functions tab, go to the next activity
        if(id == R.id.Swap){


            //Toast.makeText(this,"the",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Activity2.this,MainActivity.class);

            if(!output.getText().toString().isEmpty()){
                intent.putExtra("output",Double.valueOf(output.getText().toString()));
            }

            //if user decides to change his mind and the number is the second operand
            // like 5+3 but before he presses something like sin or cos, etc
            // decides to return to the activity

            if(ope != null){
                intent.putExtra("ope",ope);
                intent.putExtra("first",first);
            }

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;


        }else {

            return super.onOptionsItemSelected(item);
        }

        //return super.onOptionsItemSelected(item);
    }
}
