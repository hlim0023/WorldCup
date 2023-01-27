package com.example.worldcup.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ResultContentProvider extends ContentProvider {
    public static final String CONTENT_AUTHORITY = "NOV.app.lin";
    public static final Uri CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    private static final int MULTIPLE_ROWS_TASKS = 1;
    private static final int SINGLE_ROW_TASKS = 2;


    //private  static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    ResultDatabase db;

    private static UriMatcher createUriMatcher() {

        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CONTENT_AUTHORITY;

        //sUriMatcher will return code 1 if uri like authority/tasks
        uriMatcher.addURI(authority, Result.TABLE_NAME, MULTIPLE_ROWS_TASKS);

        //sUriMatcher will return code 2 if uri like e.g. authority/tasks/7 (where 7 is id of row in tasks table)
        uriMatcher.addURI(authority, Result.TABLE_NAME + "/#", SINGLE_ROW_TASKS);

        return uriMatcher;
    }


//    static{
//        sURIMatcher.addURI(CONTENT_AUTHORITY, "result", Team1);
//        //...
//        //...
//
//    }


    public ResultContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deletionCount;

        deletionCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .delete(Result.TABLE_NAME, selection, selectionArgs);

        return deletionCount;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

//        int match = sURIMatcher.match(uri);
//
//        switch (match){
//            case Team1:
//                //serve with Team1
//                //break;
//                // other case ..
//
//
//        }
        long newId= db
                .getOpenHelper()
                .getWritableDatabase()
                .insert(Result.TABLE_NAME,0,values);
        // "content://monash.fit2081.week8"
        return ContentUris.withAppendedId(CONTENT_URI,newId);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        db = ResultDatabase.getDatabase(getContext());

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        //throw new UnsupportedOperationException("Not yet implemented");

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        builder.setTables(Result.TABLE_NAME);
        String query = builder.buildQuery(projection,selection,null,null,sortOrder,null);

        final Cursor cursor = db
                .getOpenHelper()
                .getReadableDatabase()
                .query(query, selectionArgs);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int updateCount;
        updateCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .update(Result.TABLE_NAME, 0, values, selection, selectionArgs);

        return updateCount;
    }
}