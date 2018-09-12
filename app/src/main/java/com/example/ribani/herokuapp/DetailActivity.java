package com.example.ribani.herokuapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ribani.herokuapp.base.MvpActivity;
import com.example.ribani.herokuapp.presenter.MainPresenter;
import com.example.ribani.herokuapp.view.ResponseView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends MvpActivity<MainPresenter> implements ResponseView{

    @BindView(R.id.imageActorDetail)
    ImageView imageActorDetail;
    @BindView(R.id.textNameDetail)
    TextView textNameDetail;
    @BindView(R.id.textDobDetail)
    TextView textDobDetail;
    @BindView(R.id.textDescriptionDetail)
    TextView textDescriptionDetail;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        presenter.getBundle(bundle);

    }

    @Override
    public void onSuccess(String tag, Object obj) {
        switch (tag) {
            case MainPresenter.BUNDLE_EXIST :
                Bundle bundle = (Bundle) obj;
                Picasso.get().load(bundle.getString(MainPresenter.IMAGE_LINK)).into(imageActorDetail);
                textNameDetail.setText(bundle.getString(MainPresenter.NAME));
                textDobDetail.setText(bundle.getString(MainPresenter.DOB));
                textDescriptionDetail.setText(bundle.getString(MainPresenter.DESCRIPTION));
                break;
        }
    }

    @Override
    public void onFailed(String tag) {
        switch (tag) {
            case MainPresenter.BUNDLE_EXIST :
                Toast.makeText(this, getString(R.string.bundle_null), Toast.LENGTH_LONG)
                        .show();
                break;
        }
    }
}
