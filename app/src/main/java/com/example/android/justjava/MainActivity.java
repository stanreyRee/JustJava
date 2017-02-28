package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private int numOfCoffee = 0;
    private int priceOfCoffee = 2;
    private int priceOfWhippedCream = 1;
    private boolean hasWhippedCream = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int finalPrice = this.calculatePrice();
        this.displayPrice(finalPrice);
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
        }
        displayQuantity();
    }

    /**
     * This method is called when the reset button is clicked.
     */
    public void reset(View view) {
        resetNumOfCoffee();
        displayQuantity();
        displayPrice(-1);
    }

    /**
     * This method calculates the final price on the screen.
     */
    private int calculatePrice() {
        // check if whippedCream is checked;
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        this.hasWhippedCream = whippedCreamCheckBox.isChecked();
        int finalSinglePrice = this.priceOfCoffee
                + (this.hasWhippedCream ? this.priceOfWhippedCream : 0);
        return finalSinglePrice * this.numOfCoffee;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayPrice(int rawPrice) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);

        // This info will show welcome info after reset button is clicked
        if (rawPrice == -1) {
            priceTextView.setText("$0");
            return;
        }

        String TotalPrice = NumberFormat.getCurrencyInstance().format(rawPrice);
//        Log.v("MainActicity","The price is: " + priceInfo);
        String info = "Quantity: " + this.numOfCoffee;
        info += "\nHas whipped cream: " + Boolean.toString(this.hasWhippedCream);
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
}
