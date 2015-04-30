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
import android.widget.Toast;


public class MainActivity extends ActionBarActivity{


    private Button zero,one,two,three,four,five,six,seven,eight,nine,operator_plus,operator_minus,
    operator_multiply,operator_divide,operator_equal,delete,dot;

    private EditText output;

    private String firstOperand = "";
    private double firstNumber = 0;

    private String secondOperand = "";
    private double secondNumber = 0;

    private String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dot
        dot = (Button)findViewById(R.id.dot);
        //dot

        //clear
        delete =(Button)findViewById(R.id.delete);
        //clear

        //the numbers
        zero = (Button)findViewById(R.id.zero);
        one = (Button)findViewById(R.id.one);
        two = (Button)findViewById(R.id.two);
        three = (Button)findViewById(R.id.three);
        four = (Button)findViewById(R.id.four);
        five = (Button)findViewById(R.id.five);
        six = (Button)findViewById(R.id.six);
        seven = (Button)findViewById(R.id.seven);
        eight = (Button)findViewById(R.id.eight);
        nine = (Button)findViewById(R.id.nine);
        //the numbers

        //the output
        output = (EditText)findViewById(R.id.output);


        //display if it comes from the other activity
        Intent i = getIntent();

        if(i.hasExtra("output")) {

            //place it in either first or second operand

            if (i.getStringExtra("ope") != null){
                operation = i.getStringExtra("ope");
                firstNumber = i.getDoubleExtra("first",0);
            }

            if(operation.isEmpty()) {

                firstOperand = "" + i.getDoubleExtra("output", 0);
                output.setText("");
                output.setText(output.getText().append(firstOperand));

                //get the numerical value of the number
                firstNumber = Double.valueOf(output.getText().toString());
            }else{

                secondOperand = "" + i.getDoubleExtra("output", 0);
                output.setText("");
                output.setText(output.getText().append(secondOperand));
                //operation = "";

                //get the numerical value of the number
                secondNumber = Double.valueOf(output.getText().toString());
            }
        }

        Bundle x = i.getExtras();
        //i.hasExtra("power")
        //this is for the power caret
        if(x != null) {
            if (x.containsKey("power")) {
                doOperation(i.getStringExtra("power"));
            }
        }

        //display if comes from the other activity
        output.setSelection(output.length());
        disableSoftInputFromAppearing(output);
        //the output

        //operators
        operator_plus = (Button)findViewById(R.id.plus);
        operator_minus = (Button)findViewById(R.id.minus);
        operator_multiply = (Button)findViewById(R.id.multiply);
        operator_divide = (Button)findViewById(R.id.divide);
        operator_equal = (Button)findViewById(R.id.equals);
        //operators


        dot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                appendDot(".");
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_digit();
            }
        });


        operator_divide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                doOperation("/");
            }
        });

        operator_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOperation("*");
            }
        });

        operator_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOperation("-");
            }
        });

        operator_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOperation("+");
            }
        });

        operator_equal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                doOperation("=");
            }
        });



        zero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                appendNumber(0);
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(1);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(2);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(3);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(4);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(5);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(6);
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(7);
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(8);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(9);
            }
        });


    }

    public void delete_digit(){
        //delete the digit

        String text = (output.getText().toString());
        if(!(text.equals(""))) {
            output.setText(text.substring(0, text.length() - 1));
            output.setSelection(output.length());

            if(operation.isEmpty() && output.length() != 0){
                if(!output.getText().toString().equals("-")) {
                    firstNumber = Double.valueOf(output.getText().toString());
                }
            }else if(!operation.isEmpty()&& output.length() != 0){
                if(!output.getText().toString().equals("-")) {
                    secondNumber = Double.valueOf(output.getText().toString());
                }
            }else if (output.length() == 0){
                //reset firstNumber and second Number and operation

                if(operation.isEmpty()){
                    firstNumber = 0;
                }else{
                    secondNumber = 0;
                }
                //continue here if output length and operation is not empty case
            }

        }
    }

    public void appendDot(String dot){

        output.setText(output.getText().append(dot));
        output.setSelection(output.length());

    }

    public void appendNumber(int i){

        //if first operator is empty then do this
        if(operation.isEmpty()) {
            firstOperand = "" + i;
            output.setText(output.getText().append(firstOperand));
            output.setSelection(output.length());

            //get the numerical value of the number
            firstNumber = Double.valueOf(output.getText().toString());
        }else{
            //second number
            secondOperand = ""+i;
            output.setText(output.getText().append(secondOperand));
            output.setSelection(output.length());

            //get the numerical value of the number
            secondNumber = Double.valueOf(output.getText().toString());
        }
    }

    public void doOperation(String ope){


        if(ope.equals("=")){

            if(!output.getText().toString().equals("")){
                Calculation_Result();
            }

        }else{

            //this is the operation selected
            operation = ope;
            output.setText("");
            output.setSelection(output.length());
        }

    }
    public void  Calculation_Result(){


        //output.setText(output.getText().append(test));

        //delete the expression text
        output.setText("");



       if(operation.equals("+")){
           //calculate the sum
           firstNumber = firstNumber+secondNumber;
       }else if (operation.equals("-")){
           firstNumber = firstNumber-secondNumber;
       }else if (operation.equals("*")){
           firstNumber = firstNumber * secondNumber;
       }else if (operation.equals("/")){

           //check zero condition
           if(secondNumber == 0) {
               //display error and break from this.................ojo

               output.setText("");

                Toast.makeText(this, "Invalid value, try again ", Toast.LENGTH_LONG).show();

           }
           else{
               firstNumber = firstNumber / secondNumber;
           }
       }else if (operation.equals("^")){

           firstNumber = Math.pow(firstNumber,secondNumber);
       }

            DecimalFormat twoDForm = new DecimalFormat("#.####");
            firstNumber = Double.valueOf(twoDForm.format(firstNumber));

           //set it to the phone
           //clear the second number
           output.setText("");
           operation = "";
           String result = ""+firstNumber;
           output.setText(output.getText().append(result));
           output.setSelection(output.length());


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

            Intent intent = new Intent(MainActivity.this,Activity2.class);

            if(!output.getText().toString().isEmpty()){
                intent.putExtra("number",Double.valueOf(output.getText().toString()));
            }
            //pass operation if any e.i 5+5sin , i need to pass the + sign so
            //when i get it back it remember it has sign already

            //check if operation is selected and send it to activity2 along with the first input
            if(!operation.isEmpty()){
                intent.putExtra("first_number",firstNumber);
                intent.putExtra("ope",operation);
            }

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;


        }else {

            return super.onOptionsItemSelected(item);
        }

    }
}
