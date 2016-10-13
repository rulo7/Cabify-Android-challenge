package com.racobos.presentation.ui.journeyslist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.racobos.domain.R;
import com.racobos.domain.models.Journey;
import com.racobos.presentation.ui.bases.android.BaseActivity;
import com.racobos.presentation.ui.bases.android.Presenter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by rulo7 on 12/10/2016.
 */

public class JourneysListActivity extends BaseActivity implements JourneysListPresenter.JourneysListView {

    private static final String JOURNEYS_KEY = "journeys";
    @BindView(R.id.recyclerView_journeys)
    RecyclerView recyclerViewJourneys;
    @Inject
    @Presenter
    JourneysListPresenter journeysListPresenter;
    private Mara_JourneysListComposer composer;
    private RecyclerView.Adapter adapter;

    public static Intent getCallingIntent(List<Journey> journeys, Activity activity) {
        Intent intent = new Intent(activity, JourneysListActivity.class);
        intent.putExtra(JOURNEYS_KEY, new ArrayList<>(journeys));
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journeys_list);
        ButterKnife.bind(this);
        setupViews();
        setupComponents();
        journeysListPresenter.setJourneys((List<Journey>) getIntent().getSerializableExtra(JOURNEYS_KEY));
    }

    private void setupViews() {
        recyclerViewJourneys.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupComponents() {
        composer = new Mara_JourneysListComposer.Builder().setAppCompatActivity(this).build();
        composer.initialize();
        composer.enableHomeAsUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void renderJourneys(List<Journey> journeys) {
        if (adapter == null) {
            adapter = new JourneysListAdapter(journeys);
        }
        adapter.notifyDataSetChanged();
    }
}
