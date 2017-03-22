package com.sahajarora.socialnetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable{
    private EditText etTags, etIntProds, etIntCons, etSimProds, etSimCons, etValueK, etIterations;
    private RadioButton rbDocumentPopularity, rbPopularityUsers, rbFollowSimilarity, rbDistanceUsers, rbLikeSimilarity,
            rbStrategyA, rbStrategyB;
    private Button btnCompleteSimulation;
    private RadioGroup rgRankingStrategy, rgProducerStrategy;
    private static final String STRATEGY_A = "A";
    private static final String STRATEGY_B = "B";

    public static final SocialNetwork sn = new SocialNetwork();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTags = (EditText) findViewById(R.id.etTags);
        etIntProds = (EditText) findViewById(R.id.etIntProds);
        etIntCons = (EditText) findViewById(R.id.etIntCons);
        etSimProds = (EditText) findViewById(R.id.etSimProds);
        etSimCons = (EditText) findViewById(R.id.etSimCons);
        etValueK = (EditText) findViewById(R.id.etValueK);
        etIterations = (EditText) findViewById(R.id.etIterations);

        //Default strategy
        sn.setStrategy(new DocumentPopularity());

        rbDocumentPopularity = (RadioButton) findViewById(R.id.rbDocumentPopularity);
        rbDocumentPopularity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sn.setStrategy(new DocumentPopularity());
            }
        });

        rbPopularityUsers = (RadioButton) findViewById(R.id.rbPopularityUsers);
        rbPopularityUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sn.setStrategy(new DocumentUserPopularity());
            }
        });


        rbFollowSimilarity = (RadioButton) findViewById(R.id.rbFollowSimilarity);
        rbFollowSimilarity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sn.setStrategy(new DocumentFollowPopularity());
            }
        });

        rbDistanceUsers = (RadioButton) findViewById(R.id.rbDistanceUsers);
        rbDistanceUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sn.setStrategy(new DocumentFriendPopularity());
            }
        });

        rbLikeSimilarity = (RadioButton) findViewById(R.id.rbLikeSimilarity);
        rbLikeSimilarity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sn.setStrategy(new DocumentLikeSimilarityPopularity());
            }
        });

        rbStrategyA = (RadioButton) findViewById(R.id.rbStrategyA);

        rbStrategyB = (RadioButton) findViewById(R.id.rbStrategyB);

        btnCompleteSimulation = (Button) findViewById(R.id.btnCompleteSimulation);

        rgRankingStrategy = (RadioGroup) findViewById(R.id.rgRankingStrategy);
        rgProducerStrategy = (RadioGroup) findViewById(R.id.rgProducerStrategy);

        btnCompleteSimulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sn.setTotalTags(Integer.parseInt(etTags.getText().toString()));
                switch (rgProducerStrategy.getCheckedRadioButtonId()){
                    case (R.id.rbStrategyA):
                        sn.setStratProducers(STRATEGY_A);
                        break;
                    case (R.id.rbStrategyB):
                        sn.setStratProducers(STRATEGY_B);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
                        break;
                }


                sn.setTopK(Integer.parseInt(etValueK.getText().toString()));
                sn.setInitialPopulation(Integer.parseInt(etIntProds.getText().toString()), Integer.parseInt(etIntCons.getText().toString()));

                sn.setInitialConfiguration();
                sn.setSimilutionPopulation(Integer.parseInt(etSimProds.getText().toString()), Integer.parseInt(etSimCons.getText().toString()));

                sn.setSimulationConfiguration();
                sn.setNumSimulations(Integer.parseInt(etIterations.getText().toString()));

                sn.simulate(Integer.parseInt(etIterations.getText().toString()), false);


                Intent i = new Intent(MainActivity.this, ChosenUsersActivity.class);
                startActivity(i);

            }
        });


    }


}
