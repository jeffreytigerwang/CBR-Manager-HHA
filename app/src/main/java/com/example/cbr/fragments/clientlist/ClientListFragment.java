package com.example.cbr.fragments.clientlist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.adapters.ClientListAdapter;
import com.example.cbr.databinding.FragmentClientlistBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.models.ClientDisability;
import com.example.cbr.models.ClientEducationAspect;
import com.example.cbr.models.ClientHealthAspect;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientSocialAspect;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ClientListFragment extends BaseFragment implements ClientListContract.View {

    // Init API
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private ArrayList<ClientInfo> clientInfoArrayList;

    private FragmentClientlistBinding binding;
    private ClientListFragmentInterface clientListFragmentInterface;
    private ClientListContract.Presenter clientListPresenter;
    private ClientListAdapter adapter;
    private SharedPreferences pref;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

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

        //Attempt to make the fragment remember the sort settings
        pref = this.getActivity().getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Init Retrofit & NodeJs stuff
        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        clientInfoArrayList = new ArrayList<>();

        // View binding so that findViewById() doesn't have to be used
        binding = FragmentClientlistBinding.inflate(inflater, container, false);

        try {
            getClientsInfo();
            getClientGeneralAspect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        recyclerView = binding.recyclerViewClientlist;
        linearLayoutManager = new LinearLayoutManager(getActivity());
        showData();


        binding.textViewPatientList.setText(R.string.patient_list);
        setHasOptionsMenu(true);


        View view = binding.getRoot();
        return view;
    }

    private void showData(){

        String mSortSettings = pref.getString("Sort", "descending");
        
        if (mSortSettings.equals("ascending")){
            Collections.sort(clientInfoArrayList, ClientInfo.BY_TITLE_ASCENDING);
        }
        else if (mSortSettings.equals("descending")){
            Collections.sort(clientInfoArrayList, ClientInfo.BY_TITLE_DESCENDING);
        }

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ClientListAdapter(getActivity(), clientInfoArrayList, clientListFragmentInterface);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
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
            // Toast.makeText(getActivity(), "Sort", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showSortDialog() {
        String[] options = {"Ascending", "Descending"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sort By");
        builder.setIcon(R.drawable.ic_action_sort);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Sort", "ascending");
                    editor.apply();
                    showData();
                }

                if (i == 1){
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Sort", "descending");
                    editor.apply();
                    showData();
                }
            }
        });
        builder.create().show();
    }

    private void getClientsInfo() throws IOException {
        Call<List<ClientInfo>> call = jsonPlaceHolderApi.getClientsInfo();

        Response<List<ClientInfo>> response = call.execute();
        List<ClientInfo> clientInfoList = response.body();

        clientInfoArrayList.addAll(clientInfoList);
    }

    private void getClientGeneralAspect() throws IOException {
        Call<List<ClientDisability>> callDisable = jsonPlaceHolderApi.getClientDisability();
        Call<List<ClientHealthAspect>> callHealth = jsonPlaceHolderApi.getClientHealthAspect();
        Call<List<ClientEducationAspect>> callEducation = jsonPlaceHolderApi.getClientEducationAspect();
        Call<List<ClientSocialAspect>> callSocial = jsonPlaceHolderApi.getClientSocialAspect();

        Response<List<ClientDisability>> responseDisable = callDisable.execute();
        List<ClientDisability> clientDisabilityList = responseDisable.body();

        Response<List<ClientHealthAspect>> responseHealth = callHealth.execute();
        List<ClientHealthAspect> clientHealthAspectList = responseHealth.body();

        Response<List<ClientEducationAspect>> responseEducation = callEducation.execute();
        List<ClientEducationAspect> clientEducationAspectList = responseEducation.body();

        Response<List<ClientSocialAspect>> responseSocial = callSocial.execute();
        List<ClientSocialAspect> clientSocialAspectList = responseSocial.body();


        for (int i = 0; i < clientInfoArrayList.size(); i++) {
            ClientInfo clientInfo = clientInfoArrayList.get(i);

            clientInfo.setAmputeeDisability(clientDisabilityList.get(i).isAmputeeDisability());
            clientInfo.setPolioDisability(clientDisabilityList.get(i).isPolioDisability());
            clientInfo.setSpinalCordInjuryDisability(clientDisabilityList.get(i).isSpinalCordInjuryDisability());
            clientInfo.setCerebralPalsyDisability(clientDisabilityList.get(i).isCerebralPalsyDisability());
            clientInfo.setSpinaBifidaDisability(clientDisabilityList.get(i).isSpinaBifidaDisability());
            clientInfo.setHydrocephalusDisability(clientDisabilityList.get(i).isHydrocephalusDisability());
            clientInfo.setVisualImpairmentDisability(clientDisabilityList.get(i).isVisualImpairmentDisability());
            clientInfo.setHearingImpairmentDisability(clientDisabilityList.get(i).isHearingImpairmentDisability());
            clientInfo.setDoNotKnowDisability(clientDisabilityList.get(i).isDoNotKnowDisability());
            clientInfo.setOtherDisability(clientDisabilityList.get(i).isOtherDisability());

            clientInfo.setRateHealth(clientHealthAspectList.get(i).getRateHealth());
            clientInfo.setDescribeHealth(clientHealthAspectList.get(i).getDescribeHealth());
            clientInfo.setSetGoalForHealth(clientHealthAspectList.get(i).getSetGoalForHealth());

            clientInfo.setRateEducation(clientEducationAspectList.get(i).getRateEducation());
            clientInfo.setDescribeEducation(clientEducationAspectList.get(i).getDescribeEducation());
            clientInfo.setSetGoalForEducation(clientEducationAspectList.get(i).getSetGoalForEducation());

            clientInfo.setRateSocialStatus(clientSocialAspectList.get(i).getRateSocialStatus());
            clientInfo.setDescribeSocialStatus(clientSocialAspectList.get(i).getDescribeSocialStatus());
            clientInfo.setSetGoalForSocialStatus(clientSocialAspectList.get(i).getSetGoalForSocialStatus());

            clientInfoArrayList.set(i, clientInfo);
        }

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
