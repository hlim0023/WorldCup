package com.example.movielibrary.provider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;


public class ResultRepository {

    private ResultDao mResultDao;
    private LiveData<List<Result>> mAllResults;

    ResultRepository(Application application) {
        ResultDatabase db = ResultDatabase.getDatabase(application);
        mResultDao =  db.resultDao();
        mAllResults = mResultDao.getAllResult();

    }
    LiveData<List<Result>> getAllResults() {
        return mAllResults;
    }

    void insert(Result result) {
        ResultDatabase.databaseWriteExecutor.execute(() -> mResultDao.addResult(result));
    }

    void deleteAll(){
        ResultDatabase.databaseWriteExecutor.execute(()->{
            mResultDao.deleteAllResults();
        });
    }
}
