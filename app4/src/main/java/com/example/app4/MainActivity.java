package com.example.app4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.app4.databinding.ActivityMainBinding;
import com.example.app4.view.ViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        ViewModel viewModel=new ViewModel(getApplication(),binding);

        binding.setViewModel(viewModel);
    }
}
