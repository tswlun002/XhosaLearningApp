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
import com.example.wordgame.model_layer.ProgressReport;
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
public final class ProgressReportDao_Impl implements ProgressReportDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ProgressReport> __insertionAdapterOfProgressReport;

  private final EntityDeletionOrUpdateAdapter<ProgressReport> __updateAdapterOfProgressReport;

  public ProgressReportDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProgressReport = new EntityInsertionAdapter<ProgressReport>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `ProgressReport` (`progressId`,`userId`,`levelOneScore`,`levelTwoScore`,`levelThreeScore`,`AverageScore`,`status`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ProgressReport value) {
        stmt.bindLong(1, value.progressId);
        stmt.bindLong(2, value.getUserId());
        stmt.bindLong(3, value.getLevelOneScore());
        stmt.bindLong(4, value.getLevelTwoScore());
        stmt.bindLong(5, value.getLevelThreeScore());
        stmt.bindLong(6, value.getAverageScore());
        if (value.getStatus() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getStatus());
        }
      }
    };
    this.__updateAdapterOfProgressReport = new EntityDeletionOrUpdateAdapter<ProgressReport>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `ProgressReport` SET `progressId` = ?,`userId` = ?,`levelOneScore` = ?,`levelTwoScore` = ?,`levelThreeScore` = ?,`AverageScore` = ?,`status` = ? WHERE `progressId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ProgressReport value) {
        stmt.bindLong(1, value.progressId);
        stmt.bindLong(2, value.getUserId());
        stmt.bindLong(3, value.getLevelOneScore());
        stmt.bindLong(4, value.getLevelTwoScore());
        stmt.bindLong(5, value.getLevelThreeScore());
        stmt.bindLong(6, value.getAverageScore());
        if (value.getStatus() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getStatus());
        }
        stmt.bindLong(8, value.progressId);
      }
    };
  }

  @Override
  public void insert(final ProgressReport progressReport) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfProgressReport.insert(progressReport);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final ProgressReport progressReport) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfProgressReport.handle(progressReport);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<ProgressReport>> getAll() {
    final String _sql = "SELECT * FROM ProgressReport";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"ProgressReport"}, false, new Callable<List<ProgressReport>>() {
      @Override
      public List<ProgressReport> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfProgressId = CursorUtil.getColumnIndexOrThrow(_cursor, "progressId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfLevelOneScore = CursorUtil.getColumnIndexOrThrow(_cursor, "levelOneScore");
          final int _cursorIndexOfLevelTwoScore = CursorUtil.getColumnIndexOrThrow(_cursor, "levelTwoScore");
          final int _cursorIndexOfLevelThreeScore = CursorUtil.getColumnIndexOrThrow(_cursor, "levelThreeScore");
          final int _cursorIndexOfAverageScore = CursorUtil.getColumnIndexOrThrow(_cursor, "AverageScore");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<ProgressReport> _result = new ArrayList<ProgressReport>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ProgressReport _item;
            _item = new ProgressReport();
            _item.progressId = _cursor.getInt(_cursorIndexOfProgressId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            _item.setUserId(_tmpUserId);
            final int _tmpLevelOneScore;
            _tmpLevelOneScore = _cursor.getInt(_cursorIndexOfLevelOneScore);
            _item.setLevelOneScore(_tmpLevelOneScore);
            final int _tmpLevelTwoScore;
            _tmpLevelTwoScore = _cursor.getInt(_cursorIndexOfLevelTwoScore);
            _item.setLevelTwoScore(_tmpLevelTwoScore);
            final int _tmpLevelThreeScore;
            _tmpLevelThreeScore = _cursor.getInt(_cursorIndexOfLevelThreeScore);
            _item.setLevelThreeScore(_tmpLevelThreeScore);
            final int _tmpAverageScore;
            _tmpAverageScore = _cursor.getInt(_cursorIndexOfAverageScore);
            _item.setAverageScore(_tmpAverageScore);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item.setStatus(_tmpStatus);
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
  public LiveData<List<ProgressReport>> level1() {
    final String _sql = "SELECT * FROM ProgressReport WHERE  levelOneScore ==1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"ProgressReport"}, false, new Callable<List<ProgressReport>>() {
      @Override
      public List<ProgressReport> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfProgressId = CursorUtil.getColumnIndexOrThrow(_cursor, "progressId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfLevelOneScore = CursorUtil.getColumnIndexOrThrow(_cursor, "levelOneScore");
          final int _cursorIndexOfLevelTwoScore = CursorUtil.getColumnIndexOrThrow(_cursor, "levelTwoScore");
          final int _cursorIndexOfLevelThreeScore = CursorUtil.getColumnIndexOrThrow(_cursor, "levelThreeScore");
          final int _cursorIndexOfAverageScore = CursorUtil.getColumnIndexOrThrow(_cursor, "AverageScore");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<ProgressReport> _result = new ArrayList<ProgressReport>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ProgressReport _item;
            _item = new ProgressReport();
            _item.progressId = _cursor.getInt(_cursorIndexOfProgressId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            _item.setUserId(_tmpUserId);
            final int _tmpLevelOneScore;
            _tmpLevelOneScore = _cursor.getInt(_cursorIndexOfLevelOneScore);
            _item.setLevelOneScore(_tmpLevelOneScore);
            final int _tmpLevelTwoScore;
            _tmpLevelTwoScore = _cursor.getInt(_cursorIndexOfLevelTwoScore);
            _item.setLevelTwoScore(_tmpLevelTwoScore);
            final int _tmpLevelThreeScore;
            _tmpLevelThreeScore = _cursor.getInt(_cursorIndexOfLevelThreeScore);
            _item.setLevelThreeScore(_tmpLevelThreeScore);
            final int _tmpAverageScore;
            _tmpAverageScore = _cursor.getInt(_cursorIndexOfAverageScore);
            _item.setAverageScore(_tmpAverageScore);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item.setStatus(_tmpStatus);
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
  public LiveData<List<ProgressReport>> level2() {
    final String _sql = "SELECT * FROM ProgressReport WHERE levelTwoScore ==2";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"ProgressReport"}, false, new Callable<List<ProgressReport>>() {
      @Override
      public List<ProgressReport> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfProgressId = CursorUtil.getColumnIndexOrThrow(_cursor, "progressId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfLevelOneScore = CursorUtil.getColumnIndexOrThrow(_cursor, "levelOneScore");
          final int _cursorIndexOfLevelTwoScore = CursorUtil.getColumnIndexOrThrow(_cursor, "levelTwoScore");
          final int _cursorIndexOfLevelThreeScore = CursorUtil.getColumnIndexOrThrow(_cursor, "levelThreeScore");
          final int _cursorIndexOfAverageScore = CursorUtil.getColumnIndexOrThrow(_cursor, "AverageScore");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<ProgressReport> _result = new ArrayList<ProgressReport>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ProgressReport _item;
            _item = new ProgressReport();
            _item.progressId = _cursor.getInt(_cursorIndexOfProgressId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            _item.setUserId(_tmpUserId);
            final int _tmpLevelOneScore;
            _tmpLevelOneScore = _cursor.getInt(_cursorIndexOfLevelOneScore);
            _item.setLevelOneScore(_tmpLevelOneScore);
            final int _tmpLevelTwoScore;
            _tmpLevelTwoScore = _cursor.getInt(_cursorIndexOfLevelTwoScore);
            _item.setLevelTwoScore(_tmpLevelTwoScore);
            final int _tmpLevelThreeScore;
            _tmpLevelThreeScore = _cursor.getInt(_cursorIndexOfLevelThreeScore);
            _item.setLevelThreeScore(_tmpLevelThreeScore);
            final int _tmpAverageScore;
            _tmpAverageScore = _cursor.getInt(_cursorIndexOfAverageScore);
            _item.setAverageScore(_tmpAverageScore);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item.setStatus(_tmpStatus);
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
  public LiveData<List<ProgressReport>> level3() {
    final String _sql = "SELECT * FROM ProgressReport WHERE levelTwoScore ==3";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"ProgressReport"}, false, new Callable<List<ProgressReport>>() {
      @Override
      public List<ProgressReport> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfProgressId = CursorUtil.getColumnIndexOrThrow(_cursor, "progressId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfLevelOneScore = CursorUtil.getColumnIndexOrThrow(_cursor, "levelOneScore");
          final int _cursorIndexOfLevelTwoScore = CursorUtil.getColumnIndexOrThrow(_cursor, "levelTwoScore");
          final int _cursorIndexOfLevelThreeScore = CursorUtil.getColumnIndexOrThrow(_cursor, "levelThreeScore");
          final int _cursorIndexOfAverageScore = CursorUtil.getColumnIndexOrThrow(_cursor, "AverageScore");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<ProgressReport> _result = new ArrayList<ProgressReport>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ProgressReport _item;
            _item = new ProgressReport();
            _item.progressId = _cursor.getInt(_cursorIndexOfProgressId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            _item.setUserId(_tmpUserId);
            final int _tmpLevelOneScore;
            _tmpLevelOneScore = _cursor.getInt(_cursorIndexOfLevelOneScore);
            _item.setLevelOneScore(_tmpLevelOneScore);
            final int _tmpLevelTwoScore;
            _tmpLevelTwoScore = _cursor.getInt(_cursorIndexOfLevelTwoScore);
            _item.setLevelTwoScore(_tmpLevelTwoScore);
            final int _tmpLevelThreeScore;
            _tmpLevelThreeScore = _cursor.getInt(_cursorIndexOfLevelThreeScore);
            _item.setLevelThreeScore(_tmpLevelThreeScore);
            final int _tmpAverageScore;
            _tmpAverageScore = _cursor.getInt(_cursorIndexOfAverageScore);
            _item.setAverageScore(_tmpAverageScore);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item.setStatus(_tmpStatus);
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
  public ProgressReport findByName(final String first, final String last) {
    final String _sql = "SELECT * FROM ProgressReport WHERE userId LIKE ? AND levelTwoScore LIKE ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (first == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, first);
    }
    _argIndex = 2;
    if (last == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, last);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfProgressId = CursorUtil.getColumnIndexOrThrow(_cursor, "progressId");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfLevelOneScore = CursorUtil.getColumnIndexOrThrow(_cursor, "levelOneScore");
      final int _cursorIndexOfLevelTwoScore = CursorUtil.getColumnIndexOrThrow(_cursor, "levelTwoScore");
      final int _cursorIndexOfLevelThreeScore = CursorUtil.getColumnIndexOrThrow(_cursor, "levelThreeScore");
      final int _cursorIndexOfAverageScore = CursorUtil.getColumnIndexOrThrow(_cursor, "AverageScore");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final ProgressReport _result;
      if(_cursor.moveToFirst()) {
        _result = new ProgressReport();
        _result.progressId = _cursor.getInt(_cursorIndexOfProgressId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final int _tmpLevelOneScore;
        _tmpLevelOneScore = _cursor.getInt(_cursorIndexOfLevelOneScore);
        _result.setLevelOneScore(_tmpLevelOneScore);
        final int _tmpLevelTwoScore;
        _tmpLevelTwoScore = _cursor.getInt(_cursorIndexOfLevelTwoScore);
        _result.setLevelTwoScore(_tmpLevelTwoScore);
        final int _tmpLevelThreeScore;
        _tmpLevelThreeScore = _cursor.getInt(_cursorIndexOfLevelThreeScore);
        _result.setLevelThreeScore(_tmpLevelThreeScore);
        final int _tmpAverageScore;
        _tmpAverageScore = _cursor.getInt(_cursorIndexOfAverageScore);
        _result.setAverageScore(_tmpAverageScore);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        _result.setStatus(_tmpStatus);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
