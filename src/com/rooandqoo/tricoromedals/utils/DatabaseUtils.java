
package com.rooandqoo.tricoromedals.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;

import com.rooandqoo.tricoromedals.database.DatabaseHelper;
import com.rooandqoo.tricoromedals.database.MedalsDao;
import com.rooandqoo.tricoromedals.models.Medal;

public class DatabaseUtils {

    public static void updateDataBase(Context context, int version) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        MedalsDao medalsDao = new MedalsDao(db);

        final SharedPreferences pref = context.getSharedPreferences(Constants.PREFERENCE_FILE,
                context.MODE_PRIVATE);
        Editor e = pref.edit();

        switch (version) {
            case 1:
                int numberOfMedals = 38;
                Medal[] newMedals = new Medal[numberOfMedals];

                newMedals[0] = new Medal(14, 1, "岩手県行脚");
                newMedals[1] = new Medal(14, 1, "富山県行脚");
                newMedals[2] = new Medal(14, 1, "滋賀県行脚");

                newMedals[3] = new Medal(16, 0, "DJPOINT5500");
                newMedals[4] = new Medal(16, 0, "DJPOINT6000");
                newMedals[5] = new Medal(16, 0, "DJPOINT6500");
                newMedals[6] = new Medal(16, 0, "DJPOINT7000");
                newMedals[7] = new Medal(16, 0, "DJPOINT7500");
                newMedals[8] = new Medal(16, 0, "DJPOINT8000");
                newMedals[9] = new Medal(16, 0, "DJPOINT8500");
                newMedals[10] = new Medal(16, 0, "DJPOINT9000");
                newMedals[11] = new Medal(16, 0, "DJPOINT9500");
                newMedals[12] = new Medal(16, 0, "DJPOINT10000");
                newMedals[13] = new Medal(16, 0, "DJPOINT10500");
                newMedals[14] = new Medal(16, 1, "DJPOINT11000");
                newMedals[15] = new Medal(16, 1, "DJPOINT11500");
                newMedals[16] = new Medal(16, 1, "DJPOINT12000");
                newMedals[17] = new Medal(16, 1, "DJPOINT12500");

                newMedals[18] = new Medal(18, 0, "ライバル挑戦状10回撃破");
                newMedals[19] = new Medal(18, 0, "ライバル挑戦状15回撃破");
                newMedals[20] = new Medal(18, 0, "ライバル挑戦状20回撃破");
                newMedals[21] = new Medal(18, 0, "ライバル挑戦状25回撃破");
                newMedals[22] = new Medal(18, 0, "ライバル挑戦状30回撃破");
                newMedals[23] = new Medal(18, 0, "ライバル挑戦状35回撃破");
                newMedals[24] = new Medal(18, 0, "ライバル挑戦状40回撃破");
                newMedals[25] = new Medal(18, 0, "ライバル挑戦状45回撃破");
                newMedals[26] = new Medal(18, 0, "ライバル挑戦状50回撃破");
                newMedals[27] = new Medal(18, 0, "ライバル挑戦状55回撃破");
                newMedals[28] = new Medal(18, 0, "ライバル挑戦状60回撃破");
                newMedals[29] = new Medal(18, 1, "ライバル挑戦状65回撃破");
                newMedals[30] = new Medal(18, 1, "ライバル挑戦状70回撃破");
                newMedals[31] = new Medal(18, 1, "ライバル挑戦状75回撃破");
                newMedals[32] = new Medal(18, 1, "ライバル挑戦状80回撃破");
                newMedals[33] = new Medal(18, 1, "ライバル挑戦状85回撃破");
                newMedals[34] = new Medal(18, 1, "ライバル挑戦状90回撃破");
                newMedals[35] = new Medal(18, 2, "ライバル挑戦状95回撃破");
                newMedals[36] = new Medal(18, 2, "ライバル挑戦状100回撃破");

                newMedals[37] = new Medal(19, 0, "今日のイチオシ5回コンプリート");

                for (Medal medal : newMedals) {
                    medalsDao.insert(medal);
                }

            case 2:
                numberOfMedals = 5;
                newMedals = new Medal[numberOfMedals];

                newMedals[0] = new Medal(16, 1, "DJPOINT13000");
                newMedals[1] = new Medal(16, 1, "DJPOINT13500");
                newMedals[2] = new Medal(16, 2, "DJPOINT14000");
                newMedals[3] = new Medal(16, 2, "DJPOINT14500");
                newMedals[4] = new Medal(16, 2, "DJPOINT15000");

                for (Medal medal : newMedals) {
                    medalsDao.insert(medal);
                }

            case 3:
                numberOfMedals = 17;
                newMedals = new Medal[numberOfMedals];

                newMedals[0] = new Medal(19, 0, "今日のイチオシ10回コンプリート");
                newMedals[1] = new Medal(19, 0, "今日のイチオシ15回コンプリート");
                newMedals[2] = new Medal(20, 0, "アストランの輝き1-Nゲット");
                newMedals[3] = new Medal(20, 0, "アストランの輝き2-Nゲット");
                newMedals[4] = new Medal(20, 0, "アストランの輝き3-Nゲット");
                newMedals[5] = new Medal(20, 0, "アストランの輝き4-Nゲット");
                newMedals[6] = new Medal(20, 0, "アストランの輝き5-Nゲット");
                newMedals[7] = new Medal(20, 0, "アストランの輝き1-Hゲット");
                newMedals[8] = new Medal(20, 0, "アストランの輝き2-Hゲット");
                newMedals[9] = new Medal(20, 0, "アストランの輝き3-Hゲット");
                newMedals[10] = new Medal(20, 0, "アストランの輝き4-Hゲット");
                newMedals[11] = new Medal(20, 0, "アストランの輝き5-Hゲット");
                newMedals[12] = new Medal(20, 0, "アストランの輝き1-Aゲット");
                newMedals[13] = new Medal(20, 0, "アストランの輝き2-Aゲット");
                newMedals[14] = new Medal(20, 0, "アストランの輝き3-Aゲット");
                newMedals[15] = new Medal(20, 0, "アストランの輝き4-Aゲット");
                newMedals[16] = new Medal(20, 0, "アストランの輝き5-Aゲット");

                for (Medal medal : newMedals) {
                    medalsDao.insert(medal);
                }

                e.putInt("VERSION", Constants.NEWEST_DB_VERSION);
                e.commit();
        }

        db.close();
    }
}
