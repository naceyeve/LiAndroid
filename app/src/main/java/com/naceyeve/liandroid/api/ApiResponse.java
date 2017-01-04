package com.naceyeve.liandroid.api;

/**
 * Created by Administrator on 2016/12/29.
 */

public class ApiResponse<T> {
    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
