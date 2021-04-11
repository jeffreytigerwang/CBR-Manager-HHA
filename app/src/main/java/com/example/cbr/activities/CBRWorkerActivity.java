package com.example.cbr.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cbr.R;
import com.example.cbr.models.Users;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CBRWorkerActivity extends AppCompatActivity {

    // Init API
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    ImageView iconImageView;
    TextView name, location, postDate, userType, message, phoneNumber;

    private int userId = 0;
    private int iconIdx = 0;
    private String messageStr;
    private String postDateStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cbr_worker);

        // Init Retrofit & NodeJs stuff
        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        name = findViewById(R.id.cbrWorker_name);
        phoneNumber = findViewById(R.id.cbrWorker_phoneNumer);
        location = findViewById(R.id.cbrWorker_location);
        postDate = findViewById(R.id.cbrWorker_postDate);
        userType = findViewById(R.id.cbrWorker_userType);
        iconImageView = findViewById(R.id.cbrWorker_icon);
        message = findViewById(R.id.cbrWorker_message);

        getData();
        try {
            setData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getData() {
        if (getIntent().hasExtra(getString(R.string.cbrWorkerIntent_userId)) &&
                getIntent().hasExtra(getString(R.string.cbrWorkerIntent_iconIdx)) &&
                getIntent().hasExtra(getString(R.string.cbrWorkerIntent_message)) &&
                getIntent().hasExtra(getString(R.string.cbrWorkerIntent_postDate))) {

            userId = getIntent().getIntExtra(getString(R.string.cbrWorkerIntent_userId), 1);
            iconIdx = getIntent().getIntExtra(getString(R.string.cbrWorkerIntent_iconIdx), 0);
            messageStr = getIntent().getStringExtra(getString(R.string.cbrWorkerIntent_message));
            postDateStr = getIntent().getStringExtra(getString(R.string.cbrWorkerIntent_postDate));

        }
        else {
            Toast.makeText(this, R.string.cbrWorkerIntent_noData, Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() throws IOException {

        Call<List<Users>> call = jsonPlaceHolderApi.getUsers();

        Response<List<Users>> response = call.execute();
        List<Users> usersList = response.body();

        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getId() == userId) {
                String nameStr = usersList.get(i).getFirstName() + " " + usersList.get(i).getLastName();
                name.setText(nameStr);

                phoneNumber.setText(String.format("Phone Number: %s", usersList.get(i).getPhoneNumber()));
                location.setText(usersList.get(i).getZones());

                postDate.setText(String.format("Post Date: %s", postDateStr));
                userType.setText(String.format("User Type: %s", usersList.get(i).getUserType()));
                iconImageView.setImageResource(iconIdx);
                message.setText(String.format("Message: %s", messageStr));
            }
        }

    }
}