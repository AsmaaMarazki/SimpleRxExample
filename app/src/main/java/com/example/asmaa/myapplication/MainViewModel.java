package com.example.asmaa.myapplication;

import android.arch.lifecycle.ViewModel;
import io.reactivex.subjects.BehaviorSubject;

public class MainViewModel extends ViewModel {

    //subject can work as subscriber and observable
    BehaviorSubject<String> input=BehaviorSubject.create();


}