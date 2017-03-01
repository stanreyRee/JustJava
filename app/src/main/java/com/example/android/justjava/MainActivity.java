package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
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
        String submitOrder = this.createOrderSummary(finalPrice);
        displayMessage(submitOrder);
        if (this.numOfCoffee > 0) {
            sendMessageAsEmail(submitOrder);
        }
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (this.numOfCoffee == 100) {
            Toast.makeText(this, "You cannot order more than 100 coffees", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "You cannot order less than 0 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        modifyNumOfCoffee(-1);
        displayQuantity();
    }

    /**
     * This method is called when the reset button is clicked.
     */
    public void reset(View view) {
        // reset userName to null
        this.resetUserName();

        // reset the checkboxes
        this.resetChocolateCheckBox();
        this.resetWhippedCreamCheckBox();

        // reset Coffee quantity
        resetNumOfCoffee();
        displayQuantity();

        // reset Order summary
        resetOrderSummary();
    }

    /**
     * This method calculates the final price on the screen.
     *
     * @return is the total price of coffee including the toppings.
     */
    private float calculatePrice() {
        // check if customer wants whipped cream topping;
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        this.hasWhippedCream = whippedCreamCheckBox.isChecked();

        // check if customer wants chocolate topping;
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        this.hasChocolate = chocolateCheckBox.isChecked();

        // calculate the final price for coffee
        float finalSinglePrice = this.coffeePrice
                + (this.hasWhippedCream ? this.whippedCreamPrice : 0)
                + (this.hasChocolate ? this.chocolatePrice : 0);
        return finalSinglePrice * this.numOfCoffee;
    }

    /**
     * This method returns the order summary according to the given total price.
     *
     * @param rawPrice is the total price, including coffee and toppings
     * @return order summary
     */
    private String createOrderSummary(float rawPrice) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);

        // This info will show initial price information when total price is 0.
        if (Float.compare(rawPrice, -1.0f) == 0 || Float.compare(rawPrice, 0.0f) == 0) {
            return "";
        }

        String TotalPrice = NumberFormat.getCurrencyInstance().format(rawPrice);
//        Log.v("MainActicity","The price is: " + priceInfo);
        String info = getResources().getString(R.string.order_summary_name, this.userName);
        info += "\n" + getResources().getString(R.string.order_summary_quantity, this.numOfCoffee);
        info += "\n" + getResources().getString(R.string.order_summary_whipped_cream, this.hasWhippedCream);
        info += "\n" + getResources().getString(R.string.order_summary_chocolate, this.hasChocolate);
        info += "\n" + getResources().getString(R.string.order_summary_total, TotalPrice);
        info += "\n" + getResources().getString(R.string.thank_you);
        return info;
    }

    /**
     * This method modifies the numOfCoffee value by the given num.
     *
     * @param num is the number of coffee to be added/substracted
     */
    private void modifyNumOfCoffee(int num) {
        this.numOfCoffee += num;
    }

    /**
     * This method resets the numOfCoffee value to 0.
     */
    private void resetNumOfCoffee() {
        this.numOfCoffee = 0;
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
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
     *
     * @param message is the message to be shown on total price section
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
            this.hasWhippedCream = false;
        }
    }

    /**
     * This method resets whippedCreamCheckBox.
     */
    private void resetChocolateCheckBox() {
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        if (this.hasChocolate) {
            chocolateCheckBox.toggle();
            this.hasChocolate = false;
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

    /**
     * This method resets Order Summary info.
     */
    private void resetOrderSummary() {
        TextView quantityTextView = (TextView) findViewById(R.id.price_text_view);
        quantityTextView.setText("");
    }

    /**
     * This method sends the given message as an email.
     *
     * @param message is the message to be sent
     */
    public void sendMessageAsEmail(String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.order_summary_name, this.userName));
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
