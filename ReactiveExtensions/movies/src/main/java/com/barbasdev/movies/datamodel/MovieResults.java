package com.barbasdev.movies.datamodel;

import com.barbasdev.common.datalayer.model.ApiResult;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;

/**
 * Created by edu on 20/11/2016.
 */
public class MovieResults extends RealmObject implements ApiResult {

    @SerializedName("page")
    private Integer page;
    @SerializedName("results")
    private List<Movie> results = new ArrayList<>();
    @SerializedName("total_results")
    private Integer totalResults;
    @SerializedName("total_pages")
    private Integer totalPages;

    /**
     * @return The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return The results
     */
    public List<Movie> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Movie> results) {
        this.results = results;
    }

    /**
     * @return The totalResults
     */
    public Integer getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults The total_results
     */
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * @return The totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages The total_pages
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String getTitle() {
        return null;
    }
}
