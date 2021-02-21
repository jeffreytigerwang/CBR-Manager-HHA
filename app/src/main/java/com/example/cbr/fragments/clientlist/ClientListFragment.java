package com.example.cbr.fragments.clientlist;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.adapters.ClientListAdapter;
import com.example.cbr.databinding.FragmentClientlistBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.models.ClientInfo;

import java.util.ArrayList;

public class ClientListFragment extends BaseFragment implements ClientListContract.View {

    private FragmentClientlistBinding binding;
    private ClientListFragmentInterface clientListFragmentInterface;
    private ClientListContract.Presenter clientListPresenter;

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

        ClientInfo john = new ClientInfo(true, "Sample text0", "sample text0", "sample text0",
                "sample text0", "sample text0", "sample text0", 40, "sample text0",
                true, "sample text0", true, true, true, true, true, true, true, true, true, true, "sample text0", "sample text0", "sample text0", "sample text0", "sample text0", "sample text0", "sample text0", "sample text0", "sample text0");

        ClientInfo john1 = new ClientInfo(true, "sample text1", "sample text1", "sample text1",
                "sample text1", "sample text1", "sample text1", 40, "sample text1",
                true, "sample text1", true, true, true, true, true, true, true, true, true, true, "sample text1", "sample text1", "sample text1", "sample text1", "sample text1", "sample text1", "sample text1", "sample text1", "sample text1");

        ClientInfo john2 = new ClientInfo(true, "sample text2", "sample text2", "sample text2",
                "sample text2", "sample text2", "sample text2", 40, "sample text2",
                true, "sample text2", true, true, true, true, true, true, true, true, false, true, "sample text2", "sample text2", "sample text2", "sample text2", "sample text2", "sample text2", "sample text2", "sample text2", "sample text2");

        ClientInfo empty = new ClientInfo();
        ClientInfo empty1 = new ClientInfo();
        ClientInfo empty2 = new ClientInfo();
        ClientInfo empty3 = new ClientInfo();

        ArrayList<ClientInfo> peopleList = new ArrayList<>();
        peopleList.add(john);
        peopleList.add(john1);
        peopleList.add(john2);
        peopleList.add(empty);
        peopleList.add(empty1);
        peopleList.add(empty2);
        peopleList.add(empty3);

        RecyclerView recyclerView = binding.recyclerViewClientlist;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        ClientListAdapter adapter = new ClientListAdapter(getActivity(), peopleList, clientListFragmentInterface);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        binding.sampleText.setText(R.string.patient_list);

        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void setPresenter(ClientListContract.Presenter presenter) {
        clientListPresenter = presenter;
    }

    public static ClientListFragment newInstance() {
        return new ClientListFragment();
    }

    public static String getFragmentTag() {
        return ClientListFragment.class.getSimpleName();
    }

    public interface ClientListFragmentInterface {
        void swapToClientPage(ClientInfo clientInfo);
    }
}
