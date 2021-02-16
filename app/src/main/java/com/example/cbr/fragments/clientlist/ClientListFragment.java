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
        //setupButton();

        ListView listView = binding.listViewClientlist;
        ClientListClientInfo john = new ClientListClientInfo("John","1234567890","Detailed location");
        ClientListClientInfo john1 = new ClientListClientInfo("John","1234567890","Detailed location");
        ClientListClientInfo john2 = new ClientListClientInfo("John","1234567890","Detailed location");
        ClientListClientInfo john3 = new ClientListClientInfo("John","1234567890","Detailed location");
        ClientListClientInfo john4 = new ClientListClientInfo("John","1234567890","Detailed location");
        ClientListClientInfo john5 = new ClientListClientInfo("John","1234567890","Detailed location");
        ClientListClientInfo john6 = new ClientListClientInfo("John","1234567890","Detailed location");
        ClientListClientInfo john7 = new ClientListClientInfo("John","1234567890","Detailed location");
        ClientListClientInfo john8 = new ClientListClientInfo("John","1234567890","Detailed location");
        ClientListClientInfo john9 = new ClientListClientInfo("John","1234567890","Detailed location");
        ClientListClientInfo john0 = new ClientListClientInfo("John","1234567890","Detailed location");


        ArrayList<ClientListClientInfo> peopleList = new ArrayList<>();
        peopleList.add(john);
        peopleList.add(john1);
        peopleList.add(john2);
        peopleList.add(john3);
        peopleList.add(john4);
        peopleList.add(john5);
        peopleList.add(john6);
        peopleList.add(john7);
        peopleList.add(john8);
        peopleList.add(john9);
        peopleList.add(john0);

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

//    private void setupButton() {
//        binding.sampleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clientListPresenter.onButtonClicked();
//            }
//        });
//    }

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
