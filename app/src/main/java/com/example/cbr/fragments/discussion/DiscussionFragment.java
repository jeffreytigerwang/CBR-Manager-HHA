package com.example.cbr.fragments.discussion;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.adapters.questioninfoadapters.DiscussionAdapter;
import com.example.cbr.models.Messages;
import com.example.cbr.models.Users;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DiscussionFragment extends Fragment {

    // Init API
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private RecyclerView discussionRecyclerView;
    private DiscussionContract.Presenter discussionPresenter;

    private ArrayList<Messages> messagesArrayList;
    private ArrayList<Integer> imgDrawableId;
    private Messages newRefreshMessage;

    private Users user;
    private EditText discussion_sendText;
    private Button discussion_sendButton;

    private DiscussionAdapter adapter;

    private HashMap<Integer, Integer> iconCountMap;
    private int iconCounter = 0;
    private int iconIdx = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dicussion, container, false);
        discussionRecyclerView = (RecyclerView) view.findViewById(R.id.discussion_recycleReview);


        user = Users.getInstance();
        discussion_sendText = (EditText) view.findViewById(R.id.discussion_sendText);
        discussion_sendButton = (Button) view.findViewById(R.id.discussion_sendButton);
        discussion_sendButton.setEnabled(false);

        setSendText();
        setSendButton();

        // Init Retrofit & NodeJs stuff
        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        messagesArrayList = new ArrayList<>();
        imgDrawableId = new ArrayList<>();
        iconCountMap = new HashMap<>();

        setImgDrawableId();

        try {
            getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        adapter = new DiscussionAdapter(getActivity(), messagesArrayList);
        discussionRecyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(true);
        discussionRecyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    private void setSendText() {
        discussion_sendText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                discussion_sendButton.setEnabled(true);

                if (discussion_sendText.getText().toString().equals("")) {
                    discussion_sendButton.setEnabled(false);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    private void setSendButton() {
        discussion_sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createMessages();
                discussion_sendText.getText().clear();

                messagesArrayList.add(newRefreshMessage);

                adapter = new DiscussionAdapter(getActivity(), messagesArrayList);
                discussionRecyclerView.setAdapter(adapter);
            }
        });
    }

    public static DiscussionFragment newInstance() {
        return new DiscussionFragment();
    }

    public static String getFragmentTag() {
        return DiscussionFragment.class.getSimpleName();
    }


    private void getMessage() throws IOException {
        messagesArrayList = new ArrayList<>();

        Call<List<Messages>> callMessages = jsonPlaceHolderApi.getMessages();

        Response<List<Messages>> responseMessages = callMessages.execute();
        List<Messages> messagesList = responseMessages.body();

        Date currentDate = Calendar.getInstance().getTime();

        for (int i = 0; i < messagesList.size(); i++) {

            int picId = messagesList.get(i).getUserId();

            if (iconCountMap.containsKey(picId)) {
                iconIdx = iconCountMap.get(picId);
            }
            else {
                if (iconCounter <= 5) {
                    iconCountMap.put(picId, iconCounter);
                    iconIdx = iconCounter;
                    iconCounter++;
                }
                else {
                    iconIdx = 5;
                }
            }

            Messages newMessage = new Messages();

            newMessage.setImg(imgDrawableId.get(iconIdx));
            newMessage.setFirstName(messagesList.get(i).getFirstName());
            newMessage.setLastName(messagesList.get(i).getLastName());
            newMessage.setMessage(messagesList.get(i).getMessage());

            if (messagesList.get(i).getPostDate() == null) {
                newMessage.setPostDate(currentDate);
            }
            else {
                newMessage.setPostDate(messagesList.get(i).getPostDate());
            }

            messagesArrayList.add(newMessage);
        }

    }


    private void createMessages()  {

        Date currentDate = Calendar.getInstance().getTime();

        Messages message = new Messages();
        message.setUserId(user.getId());
        message.setFirstName(user.getFirstName());
        message.setLastName(user.getLastName());
        message.setMessage(discussion_sendText.getText().toString());
        message.setPostDate(currentDate);

        this.newRefreshMessage = message;

        int newIdx = iconCountMap.get(user.getId()) <= 5 ? iconCountMap.get(user.getId()) : 5;

        newRefreshMessage.setImg(imgDrawableId.get(newIdx));

        Call<Messages> call = jsonPlaceHolderApi.createMessages(message);

        call.enqueue(new Callback<Messages>() {
            @Override
            public void onResponse(Call<Messages> call, Response<Messages> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                Messages messagesResponse = response.body();
            }

            @Override
            public void onFailure(Call<Messages> call, Throwable t) {
            }
        });
    }

    private void setImgDrawableId() {
        this.imgDrawableId.add(R.drawable.discussion_sample_avatar_1);
        this.imgDrawableId.add(R.drawable.discussion_sample_avatar_2);
        this.imgDrawableId.add(R.drawable.discussion_sample_avatar_3);
        this.imgDrawableId.add(R.drawable.discussion_sample_avatar_4);
        this.imgDrawableId.add(R.drawable.discussion_sample_avatar_5);
        this.imgDrawableId.add(R.drawable.discussion_sample_avatar_6);
    }

}
