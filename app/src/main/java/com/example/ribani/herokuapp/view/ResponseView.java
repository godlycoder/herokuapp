package com.example.ribani.herokuapp.view;

public interface ResponseView {
    public void onSuccess(String tag, Object obj);
    public void onFailed(String tag);
}
