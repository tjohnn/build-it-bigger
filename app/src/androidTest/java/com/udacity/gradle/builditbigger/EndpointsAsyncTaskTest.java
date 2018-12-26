package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {


    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule(MainActivity.class);

    @Test
    public void apiResponseNotNull() throws ExecutionException, InterruptedException {
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(activityTestRule.getActivity());
        endpointsAsyncTask.execute(activityTestRule.getActivity());

        assertNotNull(endpointsAsyncTask.get());
    }
}