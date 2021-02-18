package com.example.cbr.fragments.clientlist;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.adapters.ClientListAdapter;
import com.example.cbr.databinding.FragmentClientlistBinding;
import com.example.cbr.fragments.TempHomeFragment;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.model.ClientInfo;

import java.util.ArrayList;

public class ClientListFragment extends BaseFragment implements ClientListContract.View {

    private FragmentClientlistBinding binding;
    private ClientListFragmentInterface clientListFragmentInterface;
    private ClientListContract.Presenter clientListPresenter;
    ListView listView;
    String mTitle[] = {"Facebook", "Whatsapp", "Twitter", "Instagram", "Youtube"};
    String mDescription[] = {"Facebook Description", "Whatsapp Description", "Twitter Description", "Instagram Description", "Youtube Description"};
    int images[] = {R.drawable.ic_baseline_home_24, R.drawable.ic_baseline_notifications_24, R.drawable.ic_baseline_list_24, R.drawable.ic_baseline_home_24, R.drawable.ic_baseline_home_24};

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            clientListFragmentInterface = (ClientListFragmentInterface) context;
        } catch (ClassCastException e) {
            Log.e(getFragmentTag(), "Activity should implement ClientListFragmentInterface");
        }
    }

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new ClientListPresenter(this));

        // View binding so that findViewById() doesn't have to be used
        binding = FragmentClientlistBinding.inflate(inflater, container, false);
        displayString("Patient List");

        RecyclerView recyclerView = binding.recyclerViewClientlist;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ClientInfo john = new ClientInfo(true, "Sample text0", "sample text0", "sample text0",
                "sample text0", "sample text0", "sample text0", 40, "sample text0",
                true, "sample text0", true, true, true, true, true, true, true, true, true, true, "sample text0", "sample text0", "sample text0", "sample text0", "sample text0", "sample text0", "sample text0", "sample text0", "sample text0");

        ClientInfo john1 = new ClientInfo(true, "sample text1", "sample text1", "sample text1",
                "sample text1", "sample text1", "sample text1", 40, "sample text1",
                true, "sample text1", true, true, true, true, true, true, true, true, true, true, "sample text1", "sample text1", "sample text1", "sample text1", "sample text1", "sample text1", "sample text1", "sample text1", "sample text1");
        ClientInfo john2 = new ClientInfo(true, "sample text2", "sample text2", "sample text2",
                "sample text2", "sample text2", "sample text2", 40, "sample text2",
                true, "sample text2", true, true, true, true, true, true, true, true, false, true, "sample text2", "sample text2", "sample text2", "sample text2", "sample text2", "sample text2", "sample text2", "sample text2", "sample text2");

        ArrayList<ClientInfo> peopleList = new ArrayList<>();
        peopleList.add(john);
        peopleList.add(john1);
        peopleList.add(john2);


        ClientListAdapter adapter = new ClientListAdapter(getActivity(), peopleList, clientListFragmentInterface);
        recyclerView.setAdapter(adapter);

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

    public interface ClientListFragmentInterface {
        void swapToClientPage(ClientInfo clientInfo);
    }
}
