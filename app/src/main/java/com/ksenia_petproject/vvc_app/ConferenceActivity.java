package com.ksenia_petproject.vvc_app;

import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ksenia_petproject.vvc_app.databinding.ActivityConferenceBinding;
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceConfig;
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceFragment;

import java.util.Random;

public class ConferenceActivity extends AppCompatActivity {

    private ActivityConferenceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConferenceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addFragment();
    }

    public void addFragment() {
        long appID = 402374185;
        String appSign = "ba600978ab9ad052eadf6df9bfecc232a90f476617390ef73ee986d6d92175e6";

        String userID = Build.MANUFACTURER + "_" + generateUserID();
        String userName = userID + "_name";
        String conferenceID = "test_conference_id";

        ZegoUIKitPrebuiltVideoConferenceConfig config = new ZegoUIKitPrebuiltVideoConferenceConfig();
        ZegoUIKitPrebuiltVideoConferenceFragment fragment = ZegoUIKitPrebuiltVideoConferenceFragment.newInstance(
                appID, appSign, userID, userName, conferenceID, config);

        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), fragment).commitNow();
    }

    private String generateUserID() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        while (builder.length() < 5) {
            int nextInt = random.nextInt(10);
            if (builder.length() == 0 && nextInt == 0) {
                continue;
            }
            builder.append(nextInt);
        }
        return builder.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
