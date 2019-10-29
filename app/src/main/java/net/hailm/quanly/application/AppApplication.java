package net.hailm.quanly.application;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import net.hailm.quanly.model.RealmManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        Utils.init(this);
        Realm.init(this);
        RealmManager.initializeRealmConfig();
//        RealmConfiguration config = new RealmConfiguration.Builder().build();
//        Realm.setDefaultConfiguration(config);
        super.onCreate();
    }
}
