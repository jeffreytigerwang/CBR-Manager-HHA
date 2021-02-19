package com.example.cbr.fragments.clientlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.cbr.R;
import com.example.cbr.databinding.FragmentClientlistBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.model.ClientInfo;

import java.util.ArrayList;

public class ClientListFragment extends BaseFragment implements ClientListContract.View {

    private FragmentClientlistBinding binding;
    private ClientListContract.Presenter clientListPresenter;
    ListView listView;
    String mTitle[] = {"Facebook", "Whatsapp", "Twitter", "Instagram", "Youtube"};
    String mDescription[] = {"Facebook Description", "Whatsapp Description", "Twitter Description", "Instagram Description", "Youtube Description"};
    int images[] = {R.drawable.ic_baseline_home_24, R.drawable.ic_baseline_notifications_24, R.drawable.ic_baseline_list_24, R.drawable.ic_baseline_home_24, R.drawable.ic_baseline_home_24};

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new ClientListPresenter(this));


        // View binding so that findViewById() doesn't have to be used
        binding = FragmentClientlistBinding.inflate(inflater, container, false);
        displayString("Patient List");

        ListView listView = binding.listViewClientlist;
        ClientInfo john = new ClientInfo(true, "Sample text", "Sample text", "Sample text",
                "Sample text", "Sample text", "Sample text", 40, "Sample text",
                true, "Sample text", true, true, true, true, true, true, true, true, true, true, "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text");

        ClientInfo john1 = new ClientInfo(true, "Sample text", "Sample text", "Sample text",
                "Sample text", "Sample text", "Sample text", 40, "Sample text",
                true, "Sample text", true, true, true, true, true, true, true, true, true, true, "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text");
        ClientInfo john2 = new ClientInfo(true, "Sample text", "Sample text", "Sample text",
                "Sample text", "Sample text", "Sample text", 40, "Sample text",
                true, "Sample text", true, true, true, true, true, true, true, true, true, true, "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text");

        ArrayList<ClientInfo> peopleList = new ArrayList<>();
        peopleList.add(john);
        peopleList.add(john1);
        peopleList.add(john2);


        ClientListAdapter adapter = new ClientListAdapter(getActivity(), R.layout.item_clientlist, peopleList);
        listView.setAdapter(adapter);

        View view = binding.getRoot();
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // destroy references to view
        binding = null;
    }

    @Override
    public void displayString(String string) {
        binding.sampleText.setText(string);
    }


    @Override
    public void setPresenter(ClientListContract.Presenter presenter) {
        // set the presenter so the view can communicate with the presenter
        clientListPresenter = presenter;
    }

    public static ClientListFragment newInstance() {
        return new ClientListFragment();
    }

    public static String getFragmentTag() {
        // return the name of the fragment as a tag, primarily for switching between fragments
        return ClientListFragment.class.getSimpleName();
    }
}
