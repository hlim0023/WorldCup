package com.example.worldcup.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ResultViewModel extends AndroidViewModel {
    private ResultRepository mRepository;
    private LiveData<List<Result>> mAllResults;//fleet

    public ResultViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ResultRepository(application);
        mAllResults = mRepository.getAllResults();
    }

        public LiveData<List<Result>> getAllResults() {
        return mAllResults;
    }

    public void insert(Result result) {
        mRepository.insert(result);
    }
    public void deleteAll(){
        mRepository.deleteAll();
    }

}


