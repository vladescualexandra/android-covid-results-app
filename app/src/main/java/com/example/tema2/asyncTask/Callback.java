package com.example.tema2.asyncTask;

public interface Callback<R> {

    void runResultOnUiThread(R result);
}
