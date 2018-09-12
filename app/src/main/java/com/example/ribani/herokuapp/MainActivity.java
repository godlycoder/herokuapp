package com.example.ribani.herokuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ribani.herokuapp.adapter.ActorsAdapter;
import com.example.ribani.herokuapp.base.MvpActivity;
import com.example.ribani.herokuapp.listener.CustomItemClickListener;
import com.example.ribani.herokuapp.model.Actors;
import com.example.ribani.herokuapp.model.ResponseItem;
import com.example.ribani.herokuapp.presenter.MainPresenter;
import com.example.ribani.herokuapp.view.ResponseView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpActivity<MainPresenter> implements ResponseView
        , CustomItemClickListener {

    @BindView(R.id.progress)
    RelativeLayout progress;
    private ActorsAdapter adapter;

    @BindView(R.id.recyclerViewMain)
    RecyclerView recyclerViewMain;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerViewMain.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        presenter.loadData();

    }

    @Override
    public void onSuccess(String tag, Object obj) {
        recyclerViewMain.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);

        switch (tag) {
            case MainPresenter.LOAD_DATA:
                ResponseItem responseItem = (ResponseItem) obj;
                List<Actors> actorsList = responseItem.getActors();
                Log.d("Coba", String.valueOf(actorsList.get(1).getName()));

                adapter = new ActorsAdapter(MainActivity.this, actorsList
                        , this);

                recyclerViewMain.setAdapter(adapter);
                recyclerViewMain.setLayoutManager(new LinearLayoutManager(this));
                break;
            case MainPresenter.GO_TO_DETAIL:
                Bundle bundle = (Bundle) obj;
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onFailed(String tag) {
        recyclerViewMain.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);

        switch (tag) {
            case MainPresenter.LOAD_DATA:
                Toast.makeText(MainActivity.this, R.string.empty_data, Toast.LENGTH_SHORT)
                        .show();
                break;
            case MainPresenter.LOAD_FAILED:
                Toast.makeText(MainActivity.this, R.string.faled_load_data, Toast.LENGTH_SHORT)
                        .show();
                break;

        }
    }


    @Override
    public void onItemClickListener(View view, Actors actors) {
        presenter.goToDetail(actors);
    }
}
