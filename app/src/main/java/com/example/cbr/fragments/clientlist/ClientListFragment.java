package com.example.cbr.fragments.clientlist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.adapters.ClientListAdapter;
import com.example.cbr.databinding.FragmentClientlistBinding;
import com.example.cbr.fragments.base.BaseFragment;

import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientInfoManager;

import java.util.ArrayList;
import java.util.Collections;

public class ClientListFragment extends BaseFragment implements ClientListContract.View {

    private ArrayList<ClientInfo> clientInfoArrayList;
    private ClientInfoManager clientInfoManager;

    private FragmentClientlistBinding binding;
    private ClientListFragmentInterface clientListFragmentInterface;
    private ClientListContract.Presenter clientListPresenter;
    private ClientListAdapter adapter;
    private SharedPreferences pref;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private static final String KEY = "Sort";

    // static finals instead of enum as switch statement requires const variables.
    private static final String ASCENDING_BY_NAME = "ascending by name";
    private static final String DESCENDING_BY_NAME = "descending by name";
    private static final String ASCENDING_BY_ID = "ascending by id";
    private static final String DESCENDING_BY_ID = "descending by id";



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

        clientInfoManager = ClientInfoManager.getInstance();
        clientInfoArrayList = clientInfoManager.getClientInfoArrayList();

        //Attempt to make the fragment remember the sort settings
        pref = this.getActivity().getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);

        binding = FragmentClientlistBinding.inflate(inflater, container, false);

        recyclerView = binding.recyclerViewClientlist;
        linearLayoutManager = new LinearLayoutManager(getActivity());
        showData();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        binding.textViewPatientList.setText(R.string.patient_list);
        setHasOptionsMenu(true);


        View view = binding.getRoot();
        return view;
    }

    private void showData(){

        String mSortSettings = pref.getString(KEY, ASCENDING_BY_NAME);

        switch (mSortSettings) {
            case ASCENDING_BY_NAME:
                Collections.sort(clientInfoArrayList, ClientInfo.BY_NAME_ASCENDING);
                break;
            case DESCENDING_BY_NAME:
                Collections.sort(clientInfoArrayList, ClientInfo.BY_NAME_DESCENDING);
                break;
            case ASCENDING_BY_ID:
                Collections.sort(clientInfoArrayList, ClientInfo.BY_ID_ASCENDING);
                break;
            case DESCENDING_BY_ID:
                Collections.sort(clientInfoArrayList, ClientInfo.BY_ID_DESCENDING);
                break;
            default:
                break;
        }

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ClientListAdapter(getActivity(), clientInfoArrayList, clientListFragmentInterface);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu_clientlist, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem searchItem = menu.findItem(R.id.clientListSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.clientListSort){
            showSortDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showSortDialog() {
        String[] options = {"Ascending by Name", "Descending by Name", "Ascending by ID",
                "Descending by ID"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sort By");
        builder.setIcon(R.drawable.ic_action_sort);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences.Editor editor = pref.edit();

                switch (i) {
                    case 0:
                        editor.putString(KEY, ASCENDING_BY_NAME);
                        editor.apply();
                        showData();
                        break;
                    case 1:
                        editor.putString(KEY, DESCENDING_BY_NAME);
                        editor.apply();
                        showData();
                        break;
                    case 2:
                        editor.putString(KEY, ASCENDING_BY_ID);
                        editor.apply();
                        showData();
                        break;
                    case 3:
                        editor.putString(KEY, DESCENDING_BY_ID);
                        editor.apply();
                        showData();
                        break;
                    default:
                        break;
                }
            }
        });
        builder.create().show();
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
