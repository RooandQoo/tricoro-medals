
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

                newMedals[0] = new Medal(Constants.MEDAL_CATEGORY_ANGYA, 1, "岩手県行脚");
                newMedals[1] = new Medal(Constants.MEDAL_CATEGORY_ANGYA, 1, "富山県行脚");
                newMedals[2] = new Medal(Constants.MEDAL_CATEGORY_ANGYA, 1, "滋賀県行脚");

                newMedals[3] = new Medal(Constants.MEDAL_CATEGORY_DJP, 0, "DJPOINT5500");
                newMedals[4] = new Medal(Constants.MEDAL_CATEGORY_DJP, 0, "DJPOINT6000");
                newMedals[5] = new Medal(Constants.MEDAL_CATEGORY_DJP, 0, "DJPOINT6500");
                newMedals[6] = new Medal(Constants.MEDAL_CATEGORY_DJP, 0, "DJPOINT7000");
                newMedals[7] = new Medal(Constants.MEDAL_CATEGORY_DJP, 0, "DJPOINT7500");
                newMedals[8] = new Medal(Constants.MEDAL_CATEGORY_DJP, 0, "DJPOINT8000");
                newMedals[9] = new Medal(Constants.MEDAL_CATEGORY_DJP, 0, "DJPOINT8500");
                newMedals[10] = new Medal(Constants.MEDAL_CATEGORY_DJP, 0, "DJPOINT9000");
                newMedals[11] = new Medal(Constants.MEDAL_CATEGORY_DJP, 0, "DJPOINT9500");
                newMedals[12] = new Medal(Constants.MEDAL_CATEGORY_DJP, 0, "DJPOINT10000");
                newMedals[13] = new Medal(Constants.MEDAL_CATEGORY_DJP, 0, "DJPOINT10500");
                newMedals[14] = new Medal(Constants.MEDAL_CATEGORY_DJP, 1, "DJPOINT11000");
                newMedals[15] = new Medal(Constants.MEDAL_CATEGORY_DJP, 1, "DJPOINT11500");
                newMedals[16] = new Medal(Constants.MEDAL_CATEGORY_DJP, 1, "DJPOINT12000");
                newMedals[17] = new Medal(Constants.MEDAL_CATEGORY_DJP, 1, "DJPOINT12500");

                newMedals[18] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 0, "ライバル挑戦状10回撃破");
                newMedals[19] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 0, "ライバル挑戦状15回撃破");
                newMedals[20] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 0, "ライバル挑戦状20回撃破");
                newMedals[21] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 0, "ライバル挑戦状25回撃破");
                newMedals[22] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 0, "ライバル挑戦状30回撃破");
                newMedals[23] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 0, "ライバル挑戦状35回撃破");
                newMedals[24] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 0, "ライバル挑戦状40回撃破");
                newMedals[25] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 0, "ライバル挑戦状45回撃破");
                newMedals[26] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 0, "ライバル挑戦状50回撃破");
                newMedals[27] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 0, "ライバル挑戦状55回撃破");
                newMedals[28] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 0, "ライバル挑戦状60回撃破");
                newMedals[29] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 1, "ライバル挑戦状65回撃破");
                newMedals[30] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 1, "ライバル挑戦状70回撃破");
                newMedals[31] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 1, "ライバル挑戦状75回撃破");
                newMedals[32] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 1, "ライバル挑戦状80回撃破");
                newMedals[33] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 1, "ライバル挑戦状85回撃破");
                newMedals[34] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 1, "ライバル挑戦状90回撃破");
                newMedals[35] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 2, "ライバル挑戦状95回撃破");
                newMedals[36] = new Medal(Constants.MEDAL_CATEGORY_RIVAL, 2, "ライバル挑戦状100回撃破");

                newMedals[37] = new Medal(Constants.MEDAL_CATEGORY_RECOMMEND, 0, "今日のイチオシ5回コンプリート");

                for (Medal medal : newMedals) {
                    medalsDao.insert(medal);
                }

            case 2:
                numberOfMedals = 5;
                newMedals = new Medal[numberOfMedals];

                newMedals[0] = new Medal(Constants.MEDAL_CATEGORY_DJP, 1, "DJPOINT13000");
                newMedals[1] = new Medal(Constants.MEDAL_CATEGORY_DJP, 1, "DJPOINT13500");
                newMedals[2] = new Medal(Constants.MEDAL_CATEGORY_DJP, 2, "DJPOINT14000");
                newMedals[3] = new Medal(Constants.MEDAL_CATEGORY_DJP, 2, "DJPOINT14500");
                newMedals[4] = new Medal(Constants.MEDAL_CATEGORY_DJP, 2, "DJPOINT15000");

                for (Medal medal : newMedals) {
                    medalsDao.insert(medal);
                }

            case 3:
                numberOfMedals = 17;
                newMedals = new Medal[numberOfMedals];

                newMedals[0] = new Medal(Constants.MEDAL_CATEGORY_RECOMMEND, 0, "今日のイチオシ10回コンプリート");
                newMedals[1] = new Medal(Constants.MEDAL_CATEGORY_RECOMMEND, 0, "今日のイチオシ15回コンプリート");
                newMedals[2] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_1, 0, "アストランの輝き1-Nゲット");
                newMedals[3] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_1, 0, "アストランの輝き2-Nゲット");
                newMedals[4] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_1, 0, "アストランの輝き3-Nゲット");
                newMedals[5] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_1, 0, "アストランの輝き4-Nゲット");
                newMedals[6] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_1, 0, "アストランの輝き5-Nゲット");
                newMedals[7] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_1, 0, "アストランの輝き1-Hゲット");
                newMedals[8] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_1, 0, "アストランの輝き2-Hゲット");
                newMedals[9] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_1, 0, "アストランの輝き3-Hゲット");
                newMedals[10] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_1, 0, "アストランの輝き4-Hゲット");
                newMedals[11] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_1, 0, "アストランの輝き5-Hゲット");
                newMedals[12] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_1, 0, "アストランの輝き1-Aゲット");
                newMedals[13] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_1, 0, "アストランの輝き2-Aゲット");
                newMedals[14] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_1, 0, "アストランの輝き3-Aゲット");
                newMedals[15] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_1, 0, "アストランの輝き4-Aゲット");
                newMedals[16] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_1, 0, "アストランの輝き5-Aゲット");

                for (Medal medal : newMedals) {
                    medalsDao.insert(medal);
                }

            case 4:

                numberOfMedals = 27;
                newMedals = new Medal[numberOfMedals];

                newMedals[0] = new Medal(Constants.MEDAL_CATEGORY_RECOMMEND, 0, "今日のイチオシ20回コンプリート");
                newMedals[1] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 0, "アストランの輝き6-Nゲット");
                newMedals[2] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 0, "アストランの輝き7-Nゲット");
                newMedals[3] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 0, "アストランの輝き8-Nゲット");
                newMedals[4] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 0, "アストランの輝き9-Nゲット");
                newMedals[5] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 0, "アストランの輝き10-Nゲット");
                newMedals[6] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 0, "アストランの輝き6-Hゲット");
                newMedals[7] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 0, "アストランの輝き7-Hゲット");
                newMedals[8] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 0, "アストランの輝き8-Hゲット");
                newMedals[9] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 0, "アストランの輝き9-Hゲット");
                newMedals[10] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 0, "アストランの輝き10-Hゲット");
                newMedals[11] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 0, "アストランの輝き6-Aゲット");
                newMedals[12] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 0, "アストランの輝き7-Aゲット");
                newMedals[13] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 0, "アストランの輝き8-Aゲット");
                newMedals[14] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 0, "アストランの輝き9-Aゲット");
                newMedals[15] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 0, "アストランの輝き10-Aゲット");
                newMedals[16] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 1, "アストランの輝き11-Aゲット");
                newMedals[17] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_3, 0, "REDボス 1-A 撃破");
                newMedals[18] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_3, 0, "REDボス 2-A 撃破");
                newMedals[19] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_3, 0, "REDボス 3-A 撃破");
                newMedals[20] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_3, 0, "REDボス 4-A 撃破");
                newMedals[21] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_3, 0, "REDボス 5-A 撃破");
                newMedals[22] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_3, 0, "REDボス 6-A 撃破");
                newMedals[23] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_3, 0, "REDボス 7-A 撃破");
                newMedals[24] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_3, 0, "REDボス 8-A 撃破");
                newMedals[25] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_3, 0, "REDボス 9-A 撃破");
                newMedals[26] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_3, 0, "REDボス 10-A 撃破");

                for (Medal medal : newMedals) {
                    medalsDao.insert(medal);
                }

            case 5:

                numberOfMedals = 21;
                newMedals = new Medal[numberOfMedals];

                newMedals[0] = new Medal(Constants.MEDAL_CATEGORY_RECOMMEND, 0, "今日のイチオシ25回コンプリート");
                newMedals[1] = new Medal(Constants.MEDAL_CATEGORY_RECOMMEND, 0, "今日のイチオシ30回コンプリート");
                newMedals[2] = new Medal(Constants.MEDAL_CATEGORY_RECOMMEND, 0, "今日のイチオシ35回コンプリート");
                newMedals[3] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 1, "アストランの輝き11-Aゲット");
                newMedals[4] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 2, "アストランの輝き11-Aゲット");
                newMedals[5] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 1, "アストランの輝き11-Hゲット");
                newMedals[6] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_2, 2, "アストランの輝き12-Hゲット");
                newMedals[7] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_3, 1, "REDボス 11-A 撃破");
                newMedals[8] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_3, 2, "REDボス 12-A 撃破");
                newMedals[9] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_4, 0, "REDボス 1-H 撃破");
                newMedals[10] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_4, 0, "REDボス 2-H 撃破");
                newMedals[11] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_4, 0, "REDボス 3-H 撃破");
                newMedals[12] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_4, 0, "REDボス 4-H 撃破");
                newMedals[13] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_4, 0, "REDボス 5-H 撃破");
                newMedals[14] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_4, 0, "REDボス 6-H 撃破");
                newMedals[15] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_4, 0, "REDボス 7-H 撃破");
                newMedals[16] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_4, 0, "REDボス 8-H 撃破");
                newMedals[17] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_4, 0, "REDボス 9-H 撃破");
                newMedals[18] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_4, 0, "REDボス 10-H 撃破");
                newMedals[19] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_4, 1, "REDボス 11-H 撃破");
                newMedals[20] = new Medal(Constants.MEDAL_CATEGORY_LEGEND_4, 2, "REDボス 12-H 撃破");

                for (Medal medal : newMedals) {
                    medalsDao.insert(medal);
                }
                e.putInt(Constants.PREF_DB_VERSION, Constants.NEWEST_DB_VERSION);
                e.commit();
                break;

        }

        db.close();
    }
}
