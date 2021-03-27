package com.example.cbr.fragments.discussion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.adapters.questioninfoadapters.DiscussionAdapter;
import com.example.cbr.databinding.FragmentClientlistBinding;
import com.example.cbr.models.ClientDisability;
import com.example.cbr.models.Messages;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DiscussionFragment extends Fragment {

    // Init API
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private RecyclerView discussionRecyclerView;

    private ArrayList<String> firstName;
    private ArrayList<String> lastName;
    private ArrayList<String> message;

    DiscussionAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dicussion, container, false);

        // Init Retrofit & NodeJs stuff
        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        firstName = new ArrayList<>();
        lastName = new ArrayList<>();
        message = new ArrayList<>();

        try {
            getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        adapter = new DiscussionAdapter(getActivity(), firstName, lastName, message);

        discussionRecyclerView = (RecyclerView) view.findViewById(R.id.discussion_recycleReview);

        return view;
    }

    public static DiscussionFragment newInstance() {
        return new DiscussionFragment();
    }

    public static String getFragmentTag() {
        return DiscussionFragment.class.getSimpleName();
    }


    private void getMessage() throws IOException {
        Call<List<Messages>> callMessages = jsonPlaceHolderApi.getMessages();

        Response<List<Messages>> responseMessages = callMessages.execute();
        List<Messages> messagesList = responseMessages.body();

        for (int i = 0; i < messagesList.size(); i++) {
            firstName.add(messagesList.get(i).getFirstName());
            lastName.add(messagesList.get(i).getLastName());
            message.add(messagesList.get(i).getMessage());
        }

        for (int i = 0; i < messagesList.size(); i++) {
            System.out.println("firstName" + firstName.get(i));
            System.out.println("lastName" + lastName.get(i));
            System.out.println("message" + message.get(i));
        }
    }
}
