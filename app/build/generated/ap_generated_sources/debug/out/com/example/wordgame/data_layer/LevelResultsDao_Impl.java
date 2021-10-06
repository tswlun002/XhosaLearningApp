package com.example.wordgame.data_layer;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.wordgame.model_layer.LevelResults;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LevelResultsDao_Impl implements LevelResultsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LevelResults> __insertionAdapterOfLevelResults;

  private final EntityDeletionOrUpdateAdapter<LevelResults> __updateAdapterOfLevelResults;

  public LevelResultsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLevelResults = new EntityInsertionAdapter<LevelResults>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `LevelResults` (`level_resultsId`,`gameId`,`userId`,`level`,`gameType`,`userMarks`,`totalMarks`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LevelResults value) {
        stmt.bindLong(1, value.level_resultsId);
        stmt.bindLong(2, value.getGameId());
        stmt.bindLong(3, value.getUserId());
        stmt.bindLong(4, value.getLevel());
        if (value.getGameType() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getGameType());
        }
        stmt.bindDouble(6, value.getUserMarks());
        stmt.bindLong(7, value.getTotalMarks());
      }
    };
    this.__updateAdapterOfLevelResults = new EntityDeletionOrUpdateAdapter<LevelResults>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `LevelResults` SET `level_resultsId` = ?,`gameId` = ?,`userId` = ?,`level` = ?,`gameType` = ?,`userMarks` = ?,`totalMarks` = ? WHERE `level_resultsId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LevelResults value) {
        stmt.bindLong(1, value.level_resultsId);
        stmt.bindLong(2, value.getGameId());
        stmt.bindLong(3, value.getUserId());
        stmt.bindLong(4, value.getLevel());
        if (value.getGameType() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getGameType());
        }
        stmt.bindDouble(6, value.getUserMarks());
        stmt.bindLong(7, value.getTotalMarks());
        stmt.bindLong(8, value.level_resultsId);
      }
    };
  }

  @Override
  public void insert(final LevelResults levelResults) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfLevelResults.insert(levelResults);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final LevelResults levelResults) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfLevelResults.handle(levelResults);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<LevelResults>> getAll() {
    final String _sql = "SELECT  DISTINCT level_resultsId,gameId,userId,level,gameType,userMarks,totalMarks FROM LevelResults";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"LevelResults"}, false, new Callable<List<LevelResults>>() {
      @Override
      public List<LevelResults> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLevelResultsId = CursorUtil.getColumnIndexOrThrow(_cursor, "level_resultsId");
          final int _cursorIndexOfGameId = CursorUtil.getColumnIndexOrThrow(_cursor, "gameId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfGameType = CursorUtil.getColumnIndexOrThrow(_cursor, "gameType");
          final int _cursorIndexOfUserMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "userMarks");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final List<LevelResults> _result = new ArrayList<LevelResults>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final LevelResults _item;
            final int _tmpGameId;
            _tmpGameId = _cursor.getInt(_cursorIndexOfGameId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpGameType;
            if (_cursor.isNull(_cursorIndexOfGameType)) {
              _tmpGameType = null;
            } else {
              _tmpGameType = _cursor.getString(_cursorIndexOfGameType);
            }
            final double _tmpUserMarks;
            _tmpUserMarks = _cursor.getDouble(_cursorIndexOfUserMarks);
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            _item = new LevelResults(_tmpGameId,_tmpUserId,_tmpLevel,_tmpGameType,_tmpUserMarks,_tmpTotalMarks);
            _item.level_resultsId = _cursor.getInt(_cursorIndexOfLevelResultsId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<LevelResults>> level1() {
    final String _sql = "SELECT  * FROM LevelResults  WHERE  userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'matching' and level ==1)or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'multiple choice'and level ==1) \n"
            + "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'translation'and level ==1)\n"
            + "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'true false'and level ==1) GROUP BY gameType ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"LevelResults"}, false, new Callable<List<LevelResults>>() {
      @Override
      public List<LevelResults> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLevelResultsId = CursorUtil.getColumnIndexOrThrow(_cursor, "level_resultsId");
          final int _cursorIndexOfGameId = CursorUtil.getColumnIndexOrThrow(_cursor, "gameId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfGameType = CursorUtil.getColumnIndexOrThrow(_cursor, "gameType");
          final int _cursorIndexOfUserMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "userMarks");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final List<LevelResults> _result = new ArrayList<LevelResults>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final LevelResults _item;
            final int _tmpGameId;
            _tmpGameId = _cursor.getInt(_cursorIndexOfGameId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpGameType;
            if (_cursor.isNull(_cursorIndexOfGameType)) {
              _tmpGameType = null;
            } else {
              _tmpGameType = _cursor.getString(_cursorIndexOfGameType);
            }
            final double _tmpUserMarks;
            _tmpUserMarks = _cursor.getDouble(_cursorIndexOfUserMarks);
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            _item = new LevelResults(_tmpGameId,_tmpUserId,_tmpLevel,_tmpGameType,_tmpUserMarks,_tmpTotalMarks);
            _item.level_resultsId = _cursor.getInt(_cursorIndexOfLevelResultsId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<LevelResults>> level2() {
    final String _sql = "SELECT  * FROM LevelResults  WHERE  userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'matching' and level ==2)or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'multiple choice'and level ==2) \n"
            + "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'translation'and level ==2)\n"
            + "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'true false'and level ==2) GROUP BY gameType ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"LevelResults"}, false, new Callable<List<LevelResults>>() {
      @Override
      public List<LevelResults> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLevelResultsId = CursorUtil.getColumnIndexOrThrow(_cursor, "level_resultsId");
          final int _cursorIndexOfGameId = CursorUtil.getColumnIndexOrThrow(_cursor, "gameId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfGameType = CursorUtil.getColumnIndexOrThrow(_cursor, "gameType");
          final int _cursorIndexOfUserMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "userMarks");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final List<LevelResults> _result = new ArrayList<LevelResults>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final LevelResults _item;
            final int _tmpGameId;
            _tmpGameId = _cursor.getInt(_cursorIndexOfGameId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpGameType;
            if (_cursor.isNull(_cursorIndexOfGameType)) {
              _tmpGameType = null;
            } else {
              _tmpGameType = _cursor.getString(_cursorIndexOfGameType);
            }
            final double _tmpUserMarks;
            _tmpUserMarks = _cursor.getDouble(_cursorIndexOfUserMarks);
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            _item = new LevelResults(_tmpGameId,_tmpUserId,_tmpLevel,_tmpGameType,_tmpUserMarks,_tmpTotalMarks);
            _item.level_resultsId = _cursor.getInt(_cursorIndexOfLevelResultsId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<LevelResults>> level3() {
    final String _sql = "SELECT  * FROM LevelResults  WHERE  userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'matching' and level ==3)or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'multiple choice'and level ==3) \n"
            + "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'translation'and level ==3)\n"
            + "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'true false'and level ==3) GROUP BY gameType ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"LevelResults"}, false, new Callable<List<LevelResults>>() {
      @Override
      public List<LevelResults> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLevelResultsId = CursorUtil.getColumnIndexOrThrow(_cursor, "level_resultsId");
          final int _cursorIndexOfGameId = CursorUtil.getColumnIndexOrThrow(_cursor, "gameId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfGameType = CursorUtil.getColumnIndexOrThrow(_cursor, "gameType");
          final int _cursorIndexOfUserMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "userMarks");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final List<LevelResults> _result = new ArrayList<LevelResults>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final LevelResults _item;
            final int _tmpGameId;
            _tmpGameId = _cursor.getInt(_cursorIndexOfGameId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpGameType;
            if (_cursor.isNull(_cursorIndexOfGameType)) {
              _tmpGameType = null;
            } else {
              _tmpGameType = _cursor.getString(_cursorIndexOfGameType);
            }
            final double _tmpUserMarks;
            _tmpUserMarks = _cursor.getDouble(_cursorIndexOfUserMarks);
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            _item = new LevelResults(_tmpGameId,_tmpUserId,_tmpLevel,_tmpGameType,_tmpUserMarks,_tmpTotalMarks);
            _item.level_resultsId = _cursor.getInt(_cursorIndexOfLevelResultsId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
