package com.example.ribani.herokuapp.presenter;

import android.os.Bundle;
import android.util.Log;

import com.example.ribani.herokuapp.base.BasePresenter;
import com.example.ribani.herokuapp.model.Actors;
import com.example.ribani.herokuapp.model.ResponseItem;
import com.example.ribani.herokuapp.rest.ApiClient;
import com.example.ribani.herokuapp.rest.ApiInterface;
import com.example.ribani.herokuapp.view.ResponseView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter extends BasePresenter<ResponseView> {

    public static final String LOAD_DATA = "LOAD_DATA";
    public static final String LOAD_FAILED = "LOAD_FAILED";
    public static final String NAME = "NAME";
    public static final String IMAGE_LINK = "IMAGE_LINK";
    public static final String BUNDLE_EXIST = "BUNDLE_EXIST";
    public static final String DOB = "DOB";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String GO_TO_DETAIL = "GO_TO_DETAIL";

    public MainPresenter(ResponseView view) {
        super.attachView(view);
    }

    public void loadData() {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseItem> call = service.getActors();

        call.enqueue(new Callback<ResponseItem>() {
            @Override
            public void onResponse(Call<ResponseItem> call, Response<ResponseItem> response) {
                if (response.isSuccessful()) {
                    ResponseItem responseItem = response.body();

                    view.onSuccess(LOAD_DATA, responseItem);
                } else {
                    view.onFailed(LOAD_DATA);
                }
            }

            @Override
            public void onFailure(Call<ResponseItem> call, Throwable t) {
                view.onFailed(LOAD_FAILED);
            }
        });
    }

    public void goToDetail(Actors actors) {
        Bundle bundle = new Bundle();
        bundle.putString(NAME, actors.getName());
        bundle.putString(DOB, actors.getNewFormatDob());
        bundle.putString(DESCRIPTION, actors.getDescription());
        bundle.putString(IMAGE_LINK, actors.getImage());

        view.onSuccess(GO_TO_DETAIL, bundle);
    }

    public void getBundle(Bundle bundle) {
        if (bundle != null) {
            view.onSuccess(BUNDLE_EXIST, bundle);
        } else {
            view.onFailed(BUNDLE_EXIST);
        }
    }
}
