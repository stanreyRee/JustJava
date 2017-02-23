package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private int numOfCoffee = 0;
    private int priceOfCoffee = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayPrice(this.numOfCoffee * this.priceOfCoffee);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        modifyNumOfCoffee(1);
        displayQuantity();
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (this.numOfCoffee != 0) {
            modifyNumOfCoffee(-1);
            displayQuantity();
        }
    }

    /**
     * This method is called when the reset button is clicked.
     */
    public void reset(View view) {
        resetNumOfCoffee();
        displayQuantity();
        displayPrice(0);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
        displayPrice(this.priceOfCoffee * number);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        String priceInfo = NumberFormat.getCurrencyInstance().format(number);
        String info = "Total: " + priceInfo + "\nThank you!";
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
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + this.numOfCoffee);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}
