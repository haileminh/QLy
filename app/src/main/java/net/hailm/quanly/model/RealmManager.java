package net.hailm.quanly.model;

import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;

public class RealmManager {
    private static final String TAG = "RealmManager";

    private static Realm realm;

    private static RealmConfiguration realmConfiguration;

    public static void initializeRealmConfig() {
        if (realmConfiguration == null) {
            LogUtils.d(TAG, "Initializing Realm configuration.");
            setRealmConfiguration(new RealmConfiguration.Builder()
                    .schemaVersion(1)
                    .assetFile("default.realm")
                    .build());
        }
    }

    private static void setRealmConfiguration(RealmConfiguration realmConfiguration) {
        RealmManager.realmConfiguration = realmConfiguration;
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private static int activityCount = 0;

    public static Realm getRealm() {
        return realm;
    }

    public static void incrementCount() {
        if (activityCount == 0) {
            if (realm != null) {
                if (!realm.isClosed()) {
                    Log.w(TAG, "Unexpected open Realm found.");
                    realm.close();
                }
            }
        }

        try {
            realm = Realm.getInstance(realmConfiguration);
        } catch (RealmMigrationNeededException e) {
            realm = Realm.getDefaultInstance();
            e.printStackTrace();
        }
        activityCount++;
    }

    public static void decrementCount() {
        activityCount--;
        if (activityCount <= 0) {
            activityCount = 0;
            if (realm != null) {
                realm.close();
            }

            if (Realm.compactRealm(realmConfiguration)) {
                Log.d(TAG, "Realm compacted successfully.");
            }
            realm = null;
        }
    }
}
