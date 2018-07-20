package com.example.asmaa.myapplication;


import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.editText)
    EditText  editText;
    @BindView(R.id.button)
    Button button;

    //to avoid memory leaks
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        compositeDisposable=new CompositeDisposable();

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        /*Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                textView.setText(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };*/

        //add any subscribe
       compositeDisposable.add( mainViewModel.input.subscribe(textView::setText));
      // compositeDisposable.add( mainViewModel.input.subscribe((it)->textView.setText(it)));


        button.setOnClickListener((view) -> {
            mainViewModel.input.onNext(editText.getText().toString());
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unsubscribe
        compositeDisposable.clear();
    }
}
