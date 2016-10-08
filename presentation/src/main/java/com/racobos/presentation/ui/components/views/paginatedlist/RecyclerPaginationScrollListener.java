package com.racobos.presentation.ui.components.views.paginatedlist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by http://stackoverflow.com/questions/26543131/how-to-implement-endless-list-with-recyclerview (1st answer)
 */
public abstract class RecyclerPaginationScrollListener extends RecyclerView.OnScrollListener {

    private int firstVisibleItem, visibleItemCount, totalItemCount;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;

    private Integer startingNextPage, maxPages;

    public RecyclerPaginationScrollListener(Integer startingNextPage, Integer maxPages) {
        this.startingNextPage = startingNextPage;
        this.maxPages = maxPages;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        LinearLayoutManager mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLayoutManager.getItemCount();
        firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            if ((startingNextPage == null && maxPages == null) || (startingNextPage < maxPages)) {
                onLoadMore();
            }
            if (startingNextPage != null && maxPages != null) {
                startingNextPage++;
            }
            loading = true;
        }
    }

    public abstract void onLoadMore();
}