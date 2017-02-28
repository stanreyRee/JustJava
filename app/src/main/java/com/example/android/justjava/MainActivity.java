package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private int numOfCoffee = 0;

    // prices
    private float coffeePrice = 2.00f;
    private float whippedCreamPrice = 0.50f;
    private float chocolatePrice = 0.50f;

    private boolean hasWhippedCream = false;
    private boolean hasChocolate = false;

    // name
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        this.getUserName();
        float finalPrice = this.calculatePrice();
        this.displayPrice(finalPrice);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (this.numOfCoffee == 100) {
            Toast.makeText(this,"You cannot order more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        modifyNumOfCoffee(1);
        displayQuantity();
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (this.numOfCoffee == 0) {
            Toast.makeText(this,"You cannot order less than 0 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        modifyNumOfCoffee(-1);
        displayQuantity();
    }

    /**
     * This method is called when the reset button is clicked.
     */
    public void reset(View view) {
        // reset userName
        this.resetUserName();

        // reset Coffee
        resetNumOfCoffee();
        displayQuantity();
        displayPrice(-1.0f);

        // reset the checkboxes
        this.resetChocolateCheckBox();
        this.resetWhippedCreamCheckBox();

    }

    /**
     * This method calculates the final price on the screen.
     */
    private float calculatePrice() {
        // check if whippedCream is checked;
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        this.hasWhippedCream = whippedCreamCheckBox.isChecked();

        // check if chocolate is checked;
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        this.hasChocolate = chocolateCheckBox.isChecked();

        float finalSinglePrice = this.coffeePrice
                + (this.hasWhippedCream ? this.whippedCreamPrice : 0)
                + (this.hasChocolate ? this.chocolatePrice : 0);
        return finalSinglePrice * this.numOfCoffee;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayPrice(float rawPrice) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);

        // This info will show initial price information when total price is 0.
        if (Float.compare(rawPrice, -1.0f) == 0 || Float.compare(rawPrice, 0.0f) == 0) {
            priceTextView.setText("$0");
            return;
        }

        String TotalPrice = NumberFormat.getCurrencyInstance().format(rawPrice);
//        Log.v("MainActicity","The price is: " + priceInfo);
        String info = "Name: " + this.userName;
        info += "\nQuantity: " + this.numOfCoffee;
        info += "\nAdd whipped cream: " + this.hasWhippedCream;
        info += "\nAdd chocolate: " + this.hasChocolate;
        info += "\nTotal: " + TotalPrice;
        info += "\nThank you!";
        priceTextView.setText(info);
    }

    /**
     * This method modifies the numOfCoffee value by the given num.
     */
    private void modifyNumOfCoffee(int num) {
        this.numOfCoffee += num;
    }

    /**
     * This method resets the numOfCoffee value to 0.
     */
    private void resetNumOfCoffee() {
        this.numOfCoffee = 0;
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
    }

    /**
     * This method displays the numOfCoffee on the screen.
     */
    private void displayQuantity() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + this.numOfCoffee);
    }

    /**
     * This method displays the given text on the Total price section.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }


    /**
     * This method resets whippedCreamCheckBox.
     */
    private void resetWhippedCreamCheckBox() {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        if (this.hasWhippedCream) {
            whippedCreamCheckBox.toggle();
        }
    }

    /**
     * This method resets whippedCreamCheckBox.
     */
    private void resetChocolateCheckBox() {
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        if (this.hasChocolate) {
            chocolateCheckBox.toggle();
        }
    }


    /**
     * This method gets user input of name and store it.
     */
    private void getUserName() {
        EditText userNameInput = (EditText) findViewById(R.id.name_field);
        this.userName = userNameInput.getText().toString();
    }

    /**
     * This method resets name field to null.
     */
    private void resetUserName() {
        EditText userNameInput = (EditText) findViewById(R.id.name_field);
        userNameInput.setText(null);
    }
}
