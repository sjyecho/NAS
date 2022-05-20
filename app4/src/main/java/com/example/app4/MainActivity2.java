package com.example.app4;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.app4.databinding.ActivityMainBinding;
import com.example.app4.databinding.Mvvm2Binding;
import com.example.app4.view.ViewModel;
import com.example.app4.view.ViewModel2;

public class MainActivity2 extends AppCompatActivity {

    Mvvm2Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.mvvm2);

        ViewModel2 viewModel=new ViewModel2(getApplication(),binding);
        binding.setViewModel(viewModel);
    }
}
